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
    int w,h,scale,nextPieceType;//Matrix Dimensions
    TetrisPiece actualPiece;
        
    public TetrisField(){
        scale=20;
        w=10;
        h=22;
        //Campo Obscuro
        setLayout(null);
        setBounds(1, 2, w*scale, (h-2)*scale);
        setBackground(Color.decode("#FFFFFF"));
        
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
        //System.out.println(actualPiece.typePiece);
        //System.out.println(actualPiece.color);
        nextPieceType=(int) Math.rint(Math.random()*7);
        updatePieceData();
    }
    private void newPiece(int type) {
        actualPiece=new TetrisPiece(type,this);
        nextPieceType=(int) Math.rint(Math.random()*7);
        updatePieceData();
    }
    
    private void deletePieceData(){
        for (int i = 0; i < actualPiece.dataMtrx.length; i++) {
            for (int j = 0; j < actualPiece.dataMtrx[0].length; j++) {
                if(actualPiece.dataMtrx[i][j]==1){
                    tetrisMatrixInt[actualPiece.xOrigin+i][actualPiece.yOrigin+j]=0;
                    tetrisMatrixColor[actualPiece.xOrigin+i][actualPiece.yOrigin+j]=Color.decode("#000000");
                }
            }    
        }
    }
    private void updatePieceData() {
        for (int i = 0; i < actualPiece.dataMtrx.length; i++) {
            for (int j = 0; j < actualPiece.dataMtrx[0].length; j++) {
                if(actualPiece.dataMtrx[i][j]==1){
                    tetrisMatrixInt[actualPiece.xOrigin+i][actualPiece.yOrigin+j]=actualPiece.dataMtrx[i][j];
                    tetrisMatrixColor[actualPiece.xOrigin+i][actualPiece.yOrigin+j]=actualPiece.color;
                }
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
        if(canFall()){
            actualPiece.fall();
            updatePieceData();
        }else{
            //System.out.println("GAMETICK COLLISION");
            collision();
        }
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
                if(canLeft())actualPiece.left();
                updatePieceData();
                break;
            case 'S':
                deletePieceData();
                if(canFall()){
                    actualPiece.fall();
                    updatePieceData();
                }
                break;
            case 'D':
                deletePieceData();
                if(canRight())actualPiece.right();
                updatePieceData();
                break;
            case ' ':
                deletePieceData();
                while (canFall()==true) {                    
                    actualPiece.fall();
                }
                break;
            default:
                System.out.println("Tecla no reconocida.");
                break;    
        }
    }
    
    private boolean canLeft(){
        boolean result=true;
        for (int i = 0; i < actualPiece.dataMtrx.length; i++) {
            for (int j = 0; j < actualPiece.dataMtrx[0].length; j++) {
                int x=actualPiece.xOrigin+i;
                int y=actualPiece.yOrigin+j;
                if(x-1<0)result=false;
                else if(tetrisMatrixInt[x-1][y]+actualPiece.dataMtrx[i][j]==2)result=false;
            }
        }
        return result;
    }
    private boolean canRight(){
        boolean result=true;
        for (int i = 0; i < actualPiece.dataMtrx.length; i++) {
            for (int j = 0; j < actualPiece.dataMtrx[0].length; j++) {
                int x=actualPiece.xOrigin+i;
                int y=actualPiece.yOrigin+j;
                if(x+1>=tetrisMatrixInt.length)result=false;
                else if(tetrisMatrixInt[x+1][y]+actualPiece.dataMtrx[i][j]==2)result=false;
            }
        }
        return result;
    }
    private boolean canFall() {
        boolean result=true;
        for (int i = 0; i < actualPiece.dataMtrx.length; i++) {
            for (int j = 0; j < actualPiece.dataMtrx[0].length; j++) {
                int x=actualPiece.xOrigin+i;
                int y=actualPiece.yOrigin+j;
                if(y+1>=tetrisMatrixInt[0].length)result=false;
                else if(tetrisMatrixInt[x][y+1]+actualPiece.dataMtrx[i][j]==2){result=false;}
            }
        }
        return result;
    }
    
    private void collision(){
        updatePieceData();
        //printMtrx();
        actualPiece=null;
        
        int count=0;
        for (int i = 0;  i < tetrisMatrixInt[0].length; i++) {
            for (int j = 0; j < tetrisMatrixInt.length; j++) {
                if(tetrisMatrixInt[j][i]==1)count++;
            }
            //System.out.println("Count ="+count);
            if(count==tetrisMatrixInt.length)deleteLine(i);
            count=0;
        }
        newPiece();
    }
    
    private void deleteLine(int line){
        System.out.println("Deleting line"+line);
        while (line!=1) {
            for (int k = 0; k < tetrisMatrixInt.length; k++) {
                tetrisMatrixInt[k][line]=tetrisMatrixInt[k][line-1];
            }
            line--;
        }
        for (int i = 0; i < tetrisMatrixInt.length; i++) {tetrisMatrixInt[i][0]=0;}
    }
}
