
package tetris;

import java.awt.Container;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author David
 *         Sergio
 */

public class Tetris extends JFrame implements KeyListener,ActionListener{
    
    JMenu menuGame;
    JMenuBar bar;
    JMenuItem itemEnd,itemNewGame;
    Container contentPane;
    
    Timer time;
    boolean isPaused;
    int ticks;
    final int winWidth=395, winHeight=652;
    TetrisField field;
        
    public Tetris(){
        contentPane = getContentPane();
        setResizable(false);
        setBounds(0, 0, winWidth, winHeight);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        contentPane.setLayout(null);
        addKeyListener(this);
                
        isPaused=false;
        
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
        menuGame.add(itemNewGame);
        itemEnd=new JMenuItem("End");
        menuGame.add(itemEnd);
        setJMenuBar(bar);
        
        time.start();
        field.parent=this;
    }
    
    private void togglePause(){
        if(isPaused==false){
            time.stop();
            isPaused=true;
        }else{
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
            ticks++;
            field.update();
            if(ticks>=25/field.stats.level){
                field.gameTick();
                ticks=0;
            }
        }else if(true){
            
        }
    }

        @Override
    public void keyTyped(KeyEvent ke) {
        //System.out.println(ke.getKeyChar());
        if(isPaused==false)
        field.getTypo(Character.toUpperCase(ke.getKeyChar()));
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        if(ke.getKeyCode()==78){
            remove(field);
            field=new TetrisField();
            add(field);
        }
        if(ke.getKeyCode()==27)togglePause();
        if(isPaused==false)
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
    }

    @Override
    public void keyReleased(KeyEvent ke) {}
    
    public void gameOver(){
        time.stop();
        
    }
}
