
package tetris;

import java.awt.*;
import static java.util.Arrays.*;

/**
 *
 * @author David
 *         Serg¡o
 */
public class TetrisPiece{
    int typePiece;
    /**
    * 0=Cuadrado
    * 1=Barra
    * 2=L
    * 3=J
    * 4=T
    * 5=S
    * 6=Z
    */
    Color color;
    int[][] dataMtrx;
    int position;
    /**
     * 0=Horizontal -default-
     * 1=90 grados
     * 2=180 grados
     * 3=270 grados
     */
    int xOrigin,yOrigin;
    Rectangle[][] rectangles;
    
    
    public TetrisPiece(int typePiece){
        this.typePiece=typePiece;
        xOrigin=4;
        yOrigin=0;
        position=0;
        
        switch(this.typePiece){
            case 0:
                dataMtrx=new int[2][2];
                fill(dataMtrx,1);
                color=Color.decode("#E8EB3C");
                break;
                
            case 1:
                dataMtrx=new int[4][1];
                fill(dataMtrx,1);
                color=Color.decode("#E8EB3C");
                break;
            
            case 2:
                dataMtrx=new int[3][2];
                dataMtrx[0][1]=1;
                dataMtrx[1][1]=1;
                dataMtrx[2][1]=1;
                dataMtrx[2][0]=1;
                color=Color.decode("#E8EB3C");
                break;
            
            case 3:
                dataMtrx=new int[3][2];
                dataMtrx[0][0]=1;
                dataMtrx[1][0]=1;
                dataMtrx[2][1]=1;
                dataMtrx[2][0]=1;
                color=Color.decode("#E8EB3C");
                break;
            
            case 4:
                dataMtrx=new int[3][2];
                dataMtrx[0][0]=1;
                dataMtrx[1][0]=1;
                dataMtrx[2][0]=1;
                dataMtrx[1][1]=1;
                color=Color.decode("#E8EB3C");
                break;
            
            case 5:
                dataMtrx=new int[3][2];
                dataMtrx[0][1]=1;
                dataMtrx[1][1]=1;
                dataMtrx[1][0]=1;
                dataMtrx[2][0]=1;
                color=Color.decode("#E8EB3C");
                break;
            
            case 6:
                dataMtrx=new int[3][2];
                dataMtrx[0][0]=1;
                dataMtrx[1][0]=1;
                dataMtrx[1][1]=1;
                dataMtrx[2][1]=1;
                color=Color.decode("#E8EB3C");
                break;
            
            default:
                dataMtrx=new int[1][1];
                dataMtrx[0][0]=1;
                color=Color.decode("#ffffff");
                break;
        }
        
        rectangles=new Rectangle[dataMtrx.length][dataMtrx[0].length];
        
    }
}
