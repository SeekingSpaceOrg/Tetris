
package tetris;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Set;
import javax.swing.*;

/**
 *
 * @author David
 *         Sergio
 */
public class TetrisField extends JPanel implements KeyListener{
    
    int tetrisMatrix[][];
    boolean canPlace;
    int w,h,scale;//Matrix Dimensions
    TetrisPiece actualPiece;
        
    public TetrisField(){
        scale=20;
        w=10;
        h=22;
        //Matriz interna
        tetrisMatrix=new int[10][22];
        canPlace=true;
        for(int i=0;i!=10;i++)for(int j=0;j!=22;j++)tetrisMatrix[i][j]=0;
        //Campo Obscuro
        setLayout(null);
        setBounds(1, 2, w*scale, h*scale);
        setBackground(Color.decode("#0000000"));
        
        actualPiece=new TetrisPiece(0,this);
        actualPiece.xOrigin=5;
        actualPiece.yOrigin=5;
        actualPiece.update();
    }
        
    public void update(){
        //COLLISION
        actualPiece.fall();
    }
    
    @Override
    public void keyTyped(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
