
package tetris;

import java.awt.*;

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
    
    
    public TetrisPiece(int typePiece){
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
                color=Color.decode("#FFFF00");
                break;
                
            case 1:
                dataMtrx=new int[4][1];
                dataMtrx[0][0]=1;
                dataMtrx[1][0]=1;
                dataMtrx[2][0]=1;
                dataMtrx[3][0]=1;
                color=Color.decode("#00FFFF");
                break;
            
            case 2:
                dataMtrx=new int[3][2];
                dataMtrx[0][1]=1;
                dataMtrx[1][1]=1;
                dataMtrx[2][1]=1;
                dataMtrx[2][0]=1;
                color=Color.decode("#FF9F00");
                break;
            
            case 3:
                dataMtrx=new int[3][2];
                dataMtrx[0][0]=1;
                dataMtrx[1][0]=1;
                dataMtrx[2][1]=1;
                dataMtrx[2][0]=1;
                color=Color.decode("#0000FF");
                break;
            
            case 4:
                dataMtrx=new int[3][2];
                dataMtrx[0][0]=1;
                dataMtrx[1][0]=1;
                dataMtrx[2][0]=1;
                dataMtrx[1][1]=1;
                color=Color.decode("#CF00DF");
                break;
            
            case 5:
                dataMtrx=new int[3][2];
                dataMtrx[0][1]=1;
                dataMtrx[1][1]=1;
                dataMtrx[1][0]=1;
                dataMtrx[2][0]=1;
                color=Color.decode("#00FF00");
                break;
            
            case 6:
                dataMtrx=new int[3][2];
                dataMtrx[0][0]=1;
                dataMtrx[1][0]=1;
                dataMtrx[1][1]=1;
                dataMtrx[2][1]=1;
                color=Color.decode("#FF0000");
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
    public void rotate(){
        dataMtrx=generateRotatedDataMtrx();
        this.orientation++;
    }
    
    public int[][] generateRotatedDataMtrx(){
        int[][] newDataMtrx=new int[dataMtrx[0].length][dataMtrx.length];
        for (int i = 0; i < dataMtrx.length; i++) {
            for (int j = 0; j < dataMtrx[0].length; j++) {
                newDataMtrx[j][i]=dataMtrx[i][dataMtrx[0].length-1-j];
            }
        }
        return newDataMtrx;
    }

}

