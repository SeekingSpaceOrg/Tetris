
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
    * 6=Z*/
    
    Color color;
    int[][] dataMtrx;
    int orientation;
    /**
     * 0=Horizontal -default-
     * 1=90 degree
     * 2=180 degree
     * 3=270 degree*/
     
    int xOrigin,yOrigin;
    
    
    public TetrisPiece(int typePiece, TetrisField field){
        this.typePiece=typePiece;
        xOrigin=4;
        yOrigin=0;
        orientation=0;
        
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
    }
    
    public void fall(){
        yOrigin++;
    }
    
    public void right(){
        xOrigin++;
    }
    public void left(){
        xOrigin--;
    }
    public void throwPiece(){
        System.out.println("Aventar ^-^");
    }
    public void rotate(){
        int orientation=this.orientation;
        switch(this.typePiece){
            case 0:
                orientation++;
                break;
                
            case 1:
                if(orientation==0||orientation==2){
                    System.out.println("Rotar :D");
                    dataMtrx=new int[1][4];
                    dataMtrx[0][0]=1;
                    dataMtrx[0][1]=1;
                    dataMtrx[0][2]=1;
                    dataMtrx[0][3]=1;
                }else{
                    dataMtrx=new int[4][1];
                    dataMtrx[0][0]=1;
                    dataMtrx[1][0]=1;
                    dataMtrx[2][0]=1;
                    dataMtrx[3][0]=1;
                }
                orientation++;
                break;
            
            case 2:
                
                break;
            
            case 3:
                
                break;
            
            case 4:
                
                break;
            
            case 5:
                
                break;
            
            case 6:
                
                break;
            
            default:
                System.out.println("Error");
                break;
        }
    }

}

