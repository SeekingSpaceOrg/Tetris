
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
    int w,h,scale;//Matrix Dimensions
    
    public TetrisField(){
        //
        scale=20;
        w=10;
        h=22;
        //Matriz interna
        tetrisMatrix=new int[10][22];
        canPlace=true;
        for(int i=0;i!=10;i++)for(int j=0;j!=22;j++)tetrisMatrix[i][j]=0;
        //Campo Obscuro
        setLayout(null);
        setBounds(10, 50, w*scale, h*scale);
        setBackground(Color.decode("#0000000"));
        
    }
}
