
package tetris;

import java.awt.Color;
import javax.swing.*;

/**
 *
 * @author David
 *         Sergio
 */
public class TetrisField extends JPanel{
    
    int tetrisMatrixInt[][];
    JPanel tetrisMatrixPanel[][];
    Color tetrisMarixColor[][];
    boolean canPlace;
    int w,h,scale;//Matrix Dimensions
    TetrisPiece actualPiece;
        
    public TetrisField(){
        scale=20;
        w=10;
        h=22;
        //Campo Obscuro
        setLayout(null);
        setBounds(1, 2, w*scale, h*scale);
        setBackground(Color.decode("#0000000"));
        
        //Matriz interna
        tetrisMatrixInt=new int[10][22];
        tetrisMatrixPanel=new JPanel[10][20];
        tetrisMarixColor=new Color[10][20];
        canPlace=true;
        for(int i=0;i!=w;i++)for(int j=0;j!=h;j++)tetrisMatrixInt[i][j]=0;
        for(int i=0;i!=w;i++)for(int j=0;j!=h-2;j++){
            tetrisMatrixPanel[i][j]=new JPanel(null);
            tetrisMatrixPanel[i][j].setBounds(scale*tetrisMatrixInt[i][j],scale*(tetrisMatrixInt[i][j]-2),scale,scale);
            tetrisMatrixPanel[i][j].setBorder(BorderFactory.createLineBorder(Color.decode("#000000")));
            this.add(tetrisMatrixPanel[i][j]);
        }
        for(int i=0;i!=w;i++)for(int j=0;j!=h-2;j++)tetrisMarixColor[i][j]=Color.decode("#000000");
        
        actualPiece=new TetrisPiece(1,this);
        actualPiece.xOrigin=4;
        actualPiece.yOrigin=4;
    }
    
    public void update() {
        for(int i=0; i!=tetrisMatrixInt.length;i++){
            for(int j=0; j!=tetrisMatrixInt[0].length;j++){
                if(tetrisMatrixInt[i][j]==1){
                    tetrisMatrixPanel[i][j].setBackground(tetrisMarixColor[i][j]);
                    
                }else if(tetrisMatrixInt[i][j]==1){
                    
                }
            }
        }
    }
    
    /*public void updateFall(){
        if(canFall()){
            actualPiece.fall();   
        }
    }*/
    
    public void getTypo(char wasd){
        switch(wasd){
            case 'W':
                actualPiece.rotate();
                break;
            case 'A':
                actualPiece.left();
                break;
            case 'S':
                actualPiece.throwPiece();
                break;
            case 'D':
                actualPiece.right();
                break;
            default:
                System.out.println("Otra tecla fue apretada");
                break;    
        }
    }
    
    private boolean canLeft(){
        boolean result=false;
        for (int i = 0; i < actualPiece.dataMtrx.length; i++) {
            for (int j = 0; j < actualPiece.dataMtrx[0].length; j++) {
                
            }
        }
        return result;
    }

    private boolean canRight(){
        return true;
    }
    
    private boolean canFall() {
        
        return true;
    }
}
