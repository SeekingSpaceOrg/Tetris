
package tetris;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
    int level;
    int ticks;
    final int winWidth=300, winHeight=600;
    TetrisField field;
        
    public Tetris(){
        contentPane = getContentPane();
        setResizable(true);
        setBounds(0, 0, winWidth, winHeight);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        contentPane.setLayout(null);
        addKeyListener(this);
                
        level=1;
        
        //Empieza el tiempo
        ticks=0;
        time=new Timer(100,this);
        
        //Empieza el campo
        field=new TetrisField();
        contentPane.add(field);
        
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
    }
    
    public static void main(String[] args) {
        Tetris tetris=new Tetris();
        tetris.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        ticks++;
        field.update();
        if(ticks>=10/level){
            field.gameTick();
            ticks=0;
        }
    }

        @Override
    public void keyTyped(KeyEvent ke) {
        //System.out.println(ke.getKeyChar());
        switch(ke.getKeyChar()){
            case 'w':
                field.getTypo('W');
                break;
            case 'a':
                field.getTypo('A');
                break;
            case 's':
                field.getTypo('S');
                break;
            case 'd':
                field.getTypo('D');
                break;
            case ' ':
                field.getTypo(' ');
                break;
            default:
                System.out.println("Otra tecla fue apretada");
                break;
        }
    }

    @Override
    public void keyPressed(KeyEvent ke) {}

    @Override
    public void keyReleased(KeyEvent ke) {}
}
