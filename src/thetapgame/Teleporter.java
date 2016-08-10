/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thetapgame;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
/**
 *
 * @author larstoc
 */
public class Teleporter {
    
    private String teleporterBackground;
    private String brokenTeleporterBackground;
    private String teleportationDisplay;
    private Field teleportationCoordinate;
    private boolean teleporterIsBroken;
    private ImageIcon img;
    private JFrame f;
    private int newXCoord;
    private int newYCoord;
    private JLabel explosion;
    private GameBoard gb;
    
    public Teleporter(Player player, GameBoard gBoard)throws IOException{
        Random rng = new Random();
        int isTeleporterBrokenRNG = rng.nextInt(10);
        
        this.teleporterBackground = "Images/Teleporter/teleporterBackground.jpg";
        this.f = new JFrame();
        this.img = new ImageIcon(ImageIO.read(new File(this.teleporterBackground)));
        
        if(isTeleporterBrokenRNG < 3){
            this.teleporterIsBroken = true;
        } else {
            this.teleporterIsBroken = false;
        }
        
        this.newXCoord = rng.nextInt(9) + 1;
        this.newYCoord = rng.nextInt(9) + 1;
        this.gb = gBoard;
        createBoard();
        this.explosion = getLabel();
        startTeleporter(player.getName(), this.gb);
    }
    
    public JLabel getLabel(){
        
    }
    
        public void createBoard() {
        this.f.setContentPane(new JLabel(this.img));
    	this.f.pack();
        this.f.setLayout(null);
        this.f.setResizable(false);
        this.f.setDefaultCloseOperation(0);
        this.f.setVisible(true);
    }
        
        public void sleepProgram(int seconds){
        int milliSeconds = seconds*1000;
        
        try {
            Thread.sleep(milliSeconds);                 
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }    
    
        /*
    public static void main(String[] arg0)throws IOException{
        Player player = new Player("testRolf");
        
        Teleporter tp = new Teleporter(player);
    }*/
    
    public void teleporterExploded(){
        /*
        Insert explosion image
        */
    }    
    
    public void showDialog(String msg){
        AppearWindow result = new AppearWindow(msg);
        result.ShowFrame(true);
        result.whileConnected();
    }
    
    public void startTeleporter(String playerName, GameBoard gBoard){
        showDialog("Welcome to the teleporter!\n It can take you anywhere, but it is a bit rusty.");
        
        
        if(!teleporterIsBroken){
            Field toField = new Field(this.newXCoord, this.newYCoord, false, false,"");
            gBoard.movePiece(playerName, toField);
            showDialog("You are going to a new place! ");
            
        } else {
            showDialog("Oops!\n Looks like it exploded!\n Chug now.");
            
        }
    }
}
