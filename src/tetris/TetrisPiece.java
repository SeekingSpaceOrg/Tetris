
package tetris;

import java.awt.*;
import static java.util.Arrays.*;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 *
 * @author David
 *         Sergio
 */
public class TetrisPiece{
    int typePiece;
    /**
    * 0=Square
    * 1=Bar
    * 2=L
    * 3=J
    * 4=T
    * 5=S
    * 6=Z
    */
    Color color;
    int[][] dataMtrx;
    int orientation;
    /**
     * 0=Horizontal -default-
     * 1=90 degree
     * 2=180 degree
     * 3=270 degree
     */
    int xOrigin,yOrigin;
    JPanel[][] rectangles;
    int scale;
    
    
    public TetrisPiece(int typePiece, TetrisField field){
        this.typePiece=typePiece;
        xOrigin=4;
        yOrigin=0;
        orientation=0;
        scale=field.scale;
        
        switch(this.typePiece){
            case 0:
                dataMtrx=new int[2][2];
                dataMtrx[0][0]=1;
                dataMtrx[0][1]=1;
                dataMtrx[1][0]=1;
                dataMtrx[1][1]=1;
                color=Color.decode("#E8EB3C");
                break;
                
            case 1:
                dataMtrx=new int[4][1];
                dataMtrx[0][0]=1;
                dataMtrx[1][0]=1;
                dataMtrx[2][0]=1;
                dataMtrx[3][0]=1;
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
        rectangles=new JPanel[dataMtrx.length][dataMtrx[0].length];
        for(int i=0; i!=dataMtrx.length;i++){
            for(int j=0; j!=dataMtrx[0].length;j++){
                rectangles[i][j]=new JPanel(null);
            }
        }
        update();
        for(int i=0; i!=dataMtrx.length;i++){
            for(int j=0; j!=dataMtrx[0].length;j++){
                rectangles[i][j].setBackground(color);
                rectangles[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                field.add(rectangles[i][j]);
            }
        } 
    }

    public void update() {
        for(int i=0; i!=dataMtrx.length;i++){
            for(int j=0; j!=dataMtrx[0].length;j++){
                if(dataMtrx[i][j]==1){
                    rectangles[i][j].setBounds(scale*(xOrigin+i),scale*(yOrigin+j),scale,scale);
                }
            }
        }
    }
    
    public void fall(){
        yOrigin++;
        update();
    }
}
