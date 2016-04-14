
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
    Color tetrisMatrixColor[][];
    boolean canPlace;
    int w,h,scale;//Matrix Dimensions
    TetrisPiece actualPiece;
        
    public TetrisField(){
        scale=20;
        w=10;
        h=22;
        //Campo Obscuro
        setLayout(null);
        setBounds(1, 2, w*scale, (h-2)*scale);
        setBackground(Color.decode("#FF0000"));
        
        //Matriz interna
        tetrisMatrixInt=new int[10][22];
        tetrisMatrixPanel=new JPanel[10][20];
        tetrisMatrixColor=new Color[10][22];
        canPlace=true;
        for(int i=0;i!=w;i++)for(int j=0;j!=h;j++)tetrisMatrixInt[i][j]=0;
        for(int i=0;i!=w;i++)for(int j=2;j!=h;j++){
            tetrisMatrixPanel[i][j-2]=new JPanel(null);
            tetrisMatrixPanel[i][j-2].setBounds(scale*i,scale*(j-2),scale,scale);
            tetrisMatrixPanel[i][j-2].setBorder(BorderFactory.createLineBorder(Color.decode("#000000")));
            this.add(tetrisMatrixPanel[i][j-2]);
        }
        for(int i=0;i!=w;i++)for(int j=0;j!=h;j++)tetrisMatrixColor[i][j]=Color.decode("#000000");
        
        newPiece();
    }
    
    private void newPiece() {
        actualPiece=new TetrisPiece((int) Math.rint(Math.random()*7),this);
        //actualPiece=new TetrisPiece(2,this);
        System.out.println(actualPiece.typePiece);
        System.out.println(actualPiece.color);
        //actualPiece.xOrigin=5;
        //actualPiece.yOrigin=5;
        updatePieceData();
    }
    
    private void deletePieceData(){
        for (int i = 0; i < actualPiece.dataMtrx.length; i++) {
            for (int j = 0; j < actualPiece.dataMtrx[0].length; j++) {
                tetrisMatrixInt[actualPiece.xOrigin+i][actualPiece.yOrigin+j]=0;
                tetrisMatrixColor[actualPiece.xOrigin+i][actualPiece.yOrigin+j]=Color.decode("#000000");
            }
        }
    }
    
    private void updatePieceData() {
        for (int i = 0; i < actualPiece.dataMtrx.length; i++) {
            for (int j = 0; j < actualPiece.dataMtrx[0].length; j++) {
                tetrisMatrixInt[actualPiece.xOrigin+i][actualPiece.yOrigin+j]=actualPiece.dataMtrx[i][j];
                tetrisMatrixColor[actualPiece.xOrigin+i][actualPiece.yOrigin+j]=actualPiece.color;
            }
        }
    }
    
    public void update() {
        for(int i=0; i<tetrisMatrixInt.length;i++){
            for(int j=2; j<tetrisMatrixInt[0].length;j++){
                if(tetrisMatrixInt[i][j]==1){
                    tetrisMatrixPanel[i][j-2].setBackground(tetrisMatrixColor[i][j]);
                }else if(tetrisMatrixInt[i][j]==0){
                    tetrisMatrixPanel[i][j-2].setBackground(Color.decode("#000000"));
                }else{
                    System.out.println("Error en UPDATE");
                }
            }
        }
    }
    
    public void gameTick(){
        deletePieceData();
        actualPiece.fall();
        updatePieceData();
    }
            
    public void getTypo(char wasd){
        switch(wasd){
            case 'W':
                deletePieceData();
                actualPiece.rotate();
                updatePieceData();
                break;
            case 'A':
                deletePieceData();
                actualPiece.left();
                updatePieceData();
                break;
            case 'S':
                deletePieceData();
                actualPiece.fall();
                updatePieceData();
                break;
            case 'D':
                deletePieceData();
                actualPiece.right();
                updatePieceData();
                break;
            case ' ':
                deletePieceData();
                actualPiece.throwPiece();
                updatePieceData();
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
