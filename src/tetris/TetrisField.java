
package tetris;

import java.awt.Color;
import javax.swing.*;

/**
 *
 * @author David
 *         Sergio
 */
public class TetrisField extends JPanel{
    
    int tetrisMatrix[][];
    boolean canPlace;
    
    public TetrisField(){
        //Matriz interna
        tetrisMatrix=new int[10][22];
        canPlace=true;
        for(int i=0;i!=10;i++)for(int j=0;j!=22;j++)tetrisMatrix[i][j]=0;
        //Campo Obscuro
        setLayout(null);
        setBounds(10, 50, 200, 440);
        setBackground(Color.decode("#0000000"));
        
    }
    
}
