
package tetris;

import javax.swing.*;

/**
 *
 * @author David
 *         Sergio
 */
public class Tetris extends JFrame{
    JMenuBar bar;
    JMenu menuGame;
    JMenuItem itemEnd,itemNewGame;
    
    
    TetrisField field;
    
    final int winWidth=300, winHeight=600;
    
    public Tetris(){
        setResizable(true);
        setVisible(true);
        setBounds(0, 0, winWidth, winHeight);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
       
        
        //Empieza el campo
        field=new TetrisField();
        getContentPane().add(field);
        
        //Barra de Menus
        bar=new JMenuBar();
        
        menuGame=new JMenu("Game");
        bar.add(menuGame);
        
        itemEnd=new JMenuItem("End");
        menuGame.add(itemEnd);
        
        getContentPane().add(bar);
        setJMenuBar(bar);
    }
    
    public static void main(String[] args) {
        Tetris tetris=new Tetris();
    }
    
}
