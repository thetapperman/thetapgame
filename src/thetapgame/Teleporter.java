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
    
    public Teleporter(){
        Random rng = new Random();
        int isTeleporterBrokenRNG = rng.nextInt(10);
        
        if(isTeleporterBrokenRNG < 2){
            teleporterIsBroken = true;
            return;
        } else {
            teleporterIsBroken = false;
        }
        
        newXCoord = rng.nextInt(9) + 1;
        newYCoord = rng.nextInt(9) + 1;
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
        
    public static void main(String[] arg0)throws IOException{
        Teleporter tp = new Teleporter();
    }
}
