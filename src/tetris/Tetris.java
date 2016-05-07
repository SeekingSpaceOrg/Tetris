
package tetris;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.*;

/**
 *
 * @author David
 *         Sergio
 * @Version 2.0
 */

public class Tetris extends JFrame implements KeyListener,ActionListener,Serializable{
    
    JMenu menuGame;
    JMenuBar bar;
    JMenuItem itemEnd,itemNewGame,itemSaveGame,itemLoadGame;
    JPanel pausePanel;
    JLabel pauseLabel;
    Container contentPane;
    
    Timer time;
    boolean isPaused;
    int ticks;
    final int winWidth=395, winHeight=652;
    TetrisField field;
        
    public Tetris(){
        setTitle("Tetris by David Rojas and Sergio Noriega");
        contentPane = getContentPane();
        setResizable(false);
        setBounds(0, 0, winWidth, winHeight);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        contentPane.setLayout(null);
        addKeyListener(this);
        
        //Empieza el tiempo
        ticks=0;
        time=new Timer(40,this);
        
        //Empieza el campo
        field=new TetrisField();
        contentPane.add(field);
        //Contadores
        contentPane.add(field.stats);
        
        //Barra de Menus
        bar=new JMenuBar();
        
        menuGame=new JMenu("Game");
        bar.add(menuGame);
        
        itemNewGame=new JMenuItem("New Game");
        itemNewGame.addActionListener(this);
        menuGame.add(itemNewGame);
        menuGame.addSeparator();
        itemSaveGame=new JMenuItem("Save Game");
        itemSaveGame.addActionListener(this);
        menuGame.add(itemSaveGame);
        itemLoadGame=new JMenuItem("Load Game");
        itemLoadGame.addActionListener(this);
        menuGame.add(itemLoadGame);
        menuGame.addSeparator();
        itemEnd=new JMenuItem("End");
        itemEnd.addActionListener(this);
        menuGame.add(itemEnd);
        setJMenuBar(bar);
        
        
        pauseLabel=new JLabel("Game Paused");
        pauseLabel.setForeground(Color.decode("#F0F0F0"));
        pauseLabel.setFont(new Font("Sans", 0, 50));
        pauseLabel.setBounds(0,0,winWidth,winHeight);
        pauseLabel.setHorizontalAlignment((int)CENTER_ALIGNMENT);
        pauseLabel.setVerticalAlignment((int)CENTER_ALIGNMENT);
        pausePanel=new JPanel(null);
        pausePanel.setBounds(0,0,winWidth,winHeight);
        pausePanel.add(pauseLabel);
        pausePanel.setToolTipText("Paused");
        pausePanel.setBackground(new Color(0,0,0,220));
        isPaused=false;
        
        time.start();
    }
    
    private void togglePause(){
        if(isPaused==false){
            time.stop();
            contentPane.add(pausePanel);
            pausePanel.updateUI();
            isPaused=true;
        }else{
            contentPane.remove(pausePanel);
            contentPane.repaint();
            time.start();
            isPaused=false;
        }
    }
    
    public static void main(String[] args) {
        Tetris tetris=new Tetris();
        tetris.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==time){
            if(field.gameOver==true)gameOver();
            ticks++;
            field.update();
        if(ticks>=25/field.stats.level){
            field.gameTick();
            ticks=0;
        }
        }else if(ae.getActionCommand().equals("New Game")){
            newGame();
        }else if(ae.getActionCommand().equals("End")){
            time.stop();
            this.dispose();
        }else if(ae.getActionCommand().equals("Save Game")){
            saveGame();
        }else if(ae.getActionCommand().equals("Load Game")){
            openFile();
        }
    }

        @Override
    public void keyTyped(KeyEvent ke) {}

    @Override
    public void keyPressed(KeyEvent ke) {
        if(ke.getKeyCode()==78){
            newGame();
        }
        else if(ke.getKeyCode()==27){if(field.gameOver==false)togglePause();}
        else if(isPaused==false){
        switch(ke.getKeyCode()){
            case 38:
                field.getTypo('W');
                break;
            case 37:
                field.getTypo('A');
                break;
            case 40:
                field.getTypo('S');
                break;
            case 39:
                field.getTypo('D');
                break;
            default:
                //System.out.println(ke.getKeyCode());
                break;
        }
        field.getTypo(Character.toUpperCase(ke.getKeyChar()));
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {}
    
    public void gameOver(){
        togglePause();
        JOptionPane.showMessageDialog(this, "Game Over\n"
                + "N = New Game", "Tetris", JOptionPane.WARNING_MESSAGE);
    }
    
    public void newGame(){
        contentPane.removeAll();
        field=new TetrisField();
        contentPane.repaint();
        contentPane.add(field);
        contentPane.add(field.stats);
        if(isPaused==true)togglePause();
    }
    
    public void saveGame(){
        if(isPaused==false)togglePause();
        File save;
        FileOutputStream fileStream;
        ObjectOutputStream objectStream;
        try{
            FileSystemView.getFileSystemView();
            JFileChooser fileChooser=new JFileChooser(FileSystemView.getFileSystemView());
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            fileChooser.setMultiSelectionEnabled(false);
            fileChooser.showSaveDialog(this);
            save=new File(""+fileChooser.getSelectedFile()+File.separatorChar+"tetris_save_0.txt");
            fileStream=new FileOutputStream(save);
            objectStream=new ObjectOutputStream(fileStream);
            objectStream.writeObject("Tetris V2 File @Authors David & Sergio\n"
                    + "david.rojasao@udlap.mx\n"
                    + "serchnh17@hotmail.com");
            objectStream.writeObject(field);
            objectStream.close();
            System.out.println("Game Saved.");
        }catch(Exception ex){
            System.out.println("The game was not saved.");
            ex.printStackTrace();
        }
        togglePause();
    }
    
    public void openFile(){
        if(isPaused==false)togglePause();
        File open;
        FileInputStream fileStream;
        ObjectInputStream objectStream;
        try{
            JFileChooser fileChooser=new JFileChooser();
            fileChooser.setFileFilter(new FileNameExtensionFilter("Tetris Files", "txt")); 
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.setMultiSelectionEnabled(false);
            fileChooser.showOpenDialog(this);
            open=fileChooser.getSelectedFile();
            fileStream=new FileInputStream(open);
            objectStream=new ObjectInputStream(fileStream);
            System.out.println(fileChooser.getSelectedFile());
            System.out.println((String)objectStream.readObject());
            field=(TetrisField)objectStream.readObject();
            contentPane.removeAll();
            contentPane.repaint();
            contentPane.add(field);
            contentPane.add(field.stats);
            fileStream.close();
        }catch(Exception ex){
            System.out.println("Couldn´t load file.");
            ex.printStackTrace();
        }
        togglePause();
    }
}
