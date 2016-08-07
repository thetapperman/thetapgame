/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thetapgame;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Kraugen
 */
public class WheelOfFortune {
    private ImageIcon img;
    private JFrame f;
    private HashMap <Integer, String> challenges;
    
    public WheelOfFortune(){
        this.f = new JFrame();
        this.challenges = initChallenges();
        this.img = new ImageIcon(ImageIO.read(new File("wheel_background.jpg")));
        startWheelOfFortune();
    }
    
    public void paintWheel(int spinCounter){
        String picPath = Integer.toString(spinCounter)+".gif";
        
        JLabel lbl = new JLabel(new ImageIcon(picPath));
        lbl.setBounds(100, -200, this.img.getIconWidth(), this.img.getIconHeight());

        this.f.add(lbl);
        lbl.setVisible(true);
        this.f.repaint();
    }
    
    public HashMap <Integer, String> initChallenges(){
        HashMap <Integer, String> challenges = new HashMap <Integer, String>();
        
        return challenges;
    }
    
    public void startWheelOfFortune(){
        Random randomizer = new Random();
        int spinSecs = randomizer.nextInt(50) + 1;
        
        long startTime = System.currentTimeMillis();
        System.out.println("numSpins: "+spinSecs);
        
        int spinCounter = 0;
        while(((System.currentTimeMillis()-startTime)/1000)<spinSecs){
            System.out.println("naaam");
            
            paintWheel(spinCounter);
            
            sleepProgram(0.1);
            if(spinCounter>=50){
                spinCounter = 0;
            }else{
                spinCounter+=1;
            }
        }
        
        showDialog(this.challenges.get(spinCounter));
        System.out.println("RIIIIIGHT");
        
        
    }
    
    public void showDialog(String challenge){
        AppearWindow stateSlurps = new AppearWindow(challenge);
        stateSlurps.ShowFrame(true);
        stateSlurps.whileConnected();
    }
    
    public void sleepProgram(double seconds){
        double milliSeconds = seconds*1000;
        
        try {
            Thread.sleep((long) milliSeconds);                 
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
    
    
    public static void main(String[] args) throws IOException {
        WheelOfFortune wheel = new WheelOfFortune();
    }
}
