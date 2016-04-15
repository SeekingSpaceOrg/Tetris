
package tetris;

import java.awt.Color;
import java.awt.Font;
import javax.swing.*;

/**
 *
 * @author Admin
 */
class TetrisStats extends JPanel{
    
    JPanel miniPanel[][],extra;
    JLabel labels[];
    int scale,score,level,levelCounter;
    Color defaultColor;
    TetrisPiece next;
    Font font;
    
    TetrisStats(){
        scale=20;
        setBounds(304, 2, 4*scale, 30*scale);
        setBackground(Color.decode("#555555"));
        setLayout(null);
        
        font=new Font("Sans", 0, 30);
                
        defaultColor=Color.decode("#000000");
        
        
        labels=new JLabel[5];
        for (int s = 0; s < labels.length; s++) {
            labels[s]=new JLabel();
            labels[s].setBounds(0,0,0,0);
            labels[s].setFont(font);
            labels[s].setForeground(Color.decode("#F0F0F0"));
            labels[s].setHorizontalAlignment((int)CENTER_ALIGNMENT);
            add(labels[s]);
        }
        labels[0].setText("Next");
        labels[0].setBounds(0,0,80,40);
        
        labels[1].setText("Score");
        labels[1].setBounds(0,300,80,40);
        
        labels[2].setText("0");
        labels[2].setBounds(0,335,80,40);
        
        labels[3].setText("Level");
        labels[3].setBounds(0,170,80,40);
        
        labels[4].setText("1");
        labels[4].setBounds(0,205,80,40);
        
        score=0;
        level=1;
        
        miniPanel=new JPanel[4][4];
        for (int m = 0; m < 4; m++) {
            for (int n = 0; n < 4; n++) {
                miniPanel[m][n] = new JPanel(null);
                miniPanel[m][n].setBounds(scale*m,scale*(n+2),scale,scale);
                miniPanel[m][n].setBorder(BorderFactory.createLineBorder(defaultColor));
                this.add(miniPanel[m][n]);
            }
        }
    }
    
    public void upLevelCounter(){
        if(levelCounter>=level*level && level<10)level++;
        levelCounter++;
    }
    
    public void update(int nextPieceType){
        labels[2].setText(""+score);
        labels[4].setText(""+level);
        
        next=new TetrisPiece(nextPieceType);
        int xPan=(int)(4-next.dataMtrx.length)/2;
        int yPan=(int)(4-next.dataMtrx[0].length)/2;
        //System.out.println(""+xPan+" "+yPan);
        
        for (int m = 0; m < 4; m++)for (int n = 0; n < 4; n++)
            miniPanel[m][n].setBackground(defaultColor);
        
        for (int m = 0; m < next.dataMtrx.length; m++) {
            for (int n = 0; n < next.dataMtrx[0].length; n++) {
                if(next.dataMtrx[m][n]==1){
                    miniPanel[m+xPan][n+yPan].setBackground(next.color);
                }else if(next.dataMtrx[m][n]==0){
                        miniPanel[m+xPan][n+yPan].setBackground(defaultColor);
                }else{
                    System.out.println("Error en UPDATE");
                }
            }
        }
    }
}
