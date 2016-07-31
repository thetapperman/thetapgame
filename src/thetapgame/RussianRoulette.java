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
 * @author Kraugen
 */
public class RussianRoulette {
    private int dangerousNumber;
    private String gunString;
    private String gunBackGround;
    private String displayIfShot;
    private int chosenShot;
    private ImageIcon img;
    private JFrame f;
    
    public RussianRoulette() throws IOException{
        Random randomizer = new Random();
        this.dangerousNumber = randomizer.nextInt(5) + 1;
        this.gunBackGround = "gun_.jpg";
        this.f = new JFrame();
        this.img = new ImageIcon(ImageIO.read(new File(this.gunBackGround)));
        createBoard();
        startRoulette();
        
        
    }
    
    public void createBoard() {
        this.f.setContentPane(new JLabel(this.img));
    	this.f.pack();
        this.f.setLayout(null);
        this.f.setResizable(false);
        this.f.setDefaultCloseOperation(0);
        this.f.setVisible(true);
    }
    
    public int getChosenShot(){
        return this.chosenShot;
    }
    
    public int getdangerousNumber(){
        return this.dangerousNumber;
    }
    
    
    
    public boolean checkIfShot(){
        
        return this.chosenShot==this.dangerousNumber;
    }
    
    public void drawCounter(){
        for(int i = 3;i>0;i--){
            JLabel lbl = new JLabel(new ImageIcon(Integer.toString(i)+"_count.gif"));
            lbl.setBounds(100, 100, this.img.getIconWidth(), this.img.getIconHeight());
            this.f.add(lbl);
            lbl.setVisible(true);
            this.f.repaint();
            sleepProgram(1);
            lbl.setVisible(false);
            this.f.repaint();
        }
    }
    
    public void drawBullet(){
        JLabel lbl = new JLabel(new ImageIcon("bulletGif.gif"));
        lbl.setBounds(185, -52, this.img.getIconWidth(), this.img.getIconHeight());
        this.f.add(lbl);
        lbl.setVisible(true);
        this.f.repaint();
    }
    
    public void drawFlag(){
        JLabel lbl = new JLabel(new ImageIcon("flagGif.gif"));
        lbl.setBounds(178, -5, this.img.getIconWidth(), this.img.getIconHeight());
        this.f.add(lbl);
        lbl.setVisible(true);
        this.f.repaint();
    }
    
    public void showDialog(String text){
        AppearWindow roulette = new AppearWindow(text);
        roulette.ShowFrame(true);
        roulette.whileConnected();
    }
    
    public void startRoulette(){
        AppearWindow roulette = new AppearWindow("Welcome to russian roulette\nThe gun has a capacity of 5 rounds\nChoose a number between 1 and 5\n There is one bullet inside.\nIf you choose the wrong one, finish your drink. ");
        roulette.ShowFrame(true);
        roulette.whileConnected();
        
        RussianRouletteControl control = new RussianRouletteControl(this.dangerousNumber);
        control.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        control.whileConnected();
        this.chosenShot = control.getChosenNumber();
        
        drawCounter();
        
        if(checkIfShot()){
        
            drawBullet();
            sleepProgram(2);
            showDialog("YOU GOT SHOT----> CHUG!!!");
            
        }else{
            
            drawFlag();
            sleepProgram(2);
            showDialog("Lucky bastard. Game goes on.");
                    
        }
        
        this.f.setVisible(false);
        
    }
    
    public void sleepProgram(int seconds){
        int milliSeconds = seconds*1000;
        
        try {
            Thread.sleep(milliSeconds);                 
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
    
    public static void main(String[] args) throws IOException {
        RussianRoulette gun = new RussianRoulette();
    }
    
}
