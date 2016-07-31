/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thetapgame;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Kraugen
 */
public class Brick {
    private JLabel lbl;
    private String type;
    private String belongsToPlayer;
    private Field onField;
    private JFrame f;
    private String filename;
    
    public Brick(JFrame f,String filename) throws IOException{
        this.lbl = new JLabel(new ImageIcon(filename));
        this.filename = filename;
        this.belongsToPlayer = "";
        this.onField = new Field(1,1,false,false,false,false);
        this.onField.setMoveDir("right");
        this.f = f;
        this.type = initType();
    }
    
    public String initType(){
        String t = this.filename.split("_")[1];
        System.out.println("Type: "+t.split(".gif")[0]);
        return t.split(".gif")[0];
    } 
    
    public void displayPiece(int x,int y){
        int width_board = 1155;
        int height_board = 724;
        this.lbl.setBounds(x, y, width_board, height_board);
        this.f.add(this.lbl);
        //this.lbl.setVisible(true);
    }
    
    public Field getOnField(){
        return this.onField;
    }
    
    public void setOnField(Field onField){
        this.onField = onField;
    }
    
    public String getType(){
        return this.type;
    }
    
    public String getBelongsToPlayer(){
        return this.belongsToPlayer;
    }
    
    public JLabel getBrick(){
        return this.lbl;
    }
    
    public void setBelongsToPlayer(String player){
        this.belongsToPlayer = player;
    }
    
    public void setType(String type){
        this.type = type;
    }
    
    public void printBrick(){
        System.out.println("Type: "+this.type);
        System.out.println("Player: "+this.belongsToPlayer);
        System.out.println("On the field: ");
        this.onField.printField();
    }
}
