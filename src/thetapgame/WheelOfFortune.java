/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thetapgame;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
    private ArrayList<JLabel> lbl;
    
    public WheelOfFortune() throws IOException{
        this.f = new JFrame();
        this.lbl = new ArrayList<JLabel>();
        this.challenges = initChallenges();
        this.img = new ImageIcon(ImageIO.read(new File("wheel/wheel_of_fortune_background.jpg")));
        startWheelOfFortune();
    }
    
    public void createBoard() {
        this.f.setContentPane(new JLabel(this.img));
    	this.f.pack();
        this.f.setLayout(null);
        this.f.setResizable(false);
        this.f.setDefaultCloseOperation(0);
        this.f.setVisible(true);
    }
    
    public void paintWheel(int spinCounter){
        String picPath = "wheel/"+Integer.toString(spinCounter)+".gif";
        System.out.println("path: "+picPath);
        System.out.println("painting");
        
        JLabel newJ = new JLabel(new ImageIcon(picPath));
        if(!this.lbl.isEmpty()){
            this.lbl.get(this.lbl.size()-1).setBounds(0, 20, this.img.getIconWidth(), this.img.getIconHeight());
            System.out.println("Labels: "+this.lbl.size());
            this.f.add(this.lbl.get(this.lbl.size()-1));
            this.lbl.get(this.lbl.size()-1).setVisible(true);
            if(this.lbl.size()>=2){//sett forrige bilde usynlig
                this.lbl.get(this.lbl.size()-2).setVisible(false);
            }
            this.f.repaint();
            
        }
        this.lbl.add(newJ);
        JLabel needle = new JLabel(new ImageIcon("wheel/needle.gif"));
        needle.setBounds(-22, -270, this.img.getIconWidth(), this.img.getIconHeight());
        needle.setVisible(true);
        this.f.add(needle);
        this.f.repaint();
    }
    
    public HashMap <Integer, String> initChallenges(){
        HashMap <Integer, String> challenges = new HashMap <Integer, String>();
        challenges.put(1,"Girls drink!!");
        challenges.put(2,"Girls drink!!");
        challenges.put(3,"Girls drink!!");
        challenges.put(4,"Jeg har aldri..........");
        challenges.put(5,"Jeg har aldri..........");
        challenges.put(6,"Jeg har aldri..........");
        challenges.put(7,"All Norwegians drink 3 zips.");
        challenges.put(8,"All Norwegians drink 3 zips.");
        challenges.put(9,"All Norwegians drink 3 zips.");
        challenges.put(10,"All boys drink");
        challenges.put(11,"All boys drink");
        challenges.put(12,"All boys drink");
        challenges.put(13,"Are you really voting Trump?\nDo you support ISIS?\nOMG!!--->CHUG!!!");
        challenges.put(14,"Are you really voting Trump?\nDo you support ISIS?\nOMG!!--->CHUG!!!");
        challenges.put(15,"Are you really voting Trump?\nDo you support ISIS?\nOMG!!--->CHUG!!!");
        challenges.put(16,"Russian roulette.\nGood luck.");
        challenges.put(17,"Russian roulette.\nGood luck.");
        challenges.put(18,"Russian roulette.\nGood luck.");
        challenges.put(19,"10 zips for YOU!!!");
        challenges.put(20,"10 zips for YOU!!!");
        challenges.put(21,"10 zips for YOU!!!");
        challenges.put(22,"Lucky you...\nNothing happens...");
        challenges.put(23,"Lucky you...\nNothing happens...");
        challenges.put(24,"Lucky you...\nNothing happens...");
        challenges.put(25,"HAHAHAHAHAHA, thats a busride!!\nLets start....");
        challenges.put(26,"HAHAHAHAHAHA, thats a busride!!\nLets start....");
        challenges.put(27,"HAHAHAHAHAHA, thats a busride!!\nLets start....");
        challenges.put(28,"FREE PASS!!\nNext time you have to drink, you can refuse to.");
        challenges.put(29,"FREE PASS!!\nNext time you have to drink, you can refuse to.");
        challenges.put(30,"FREE PASS!!\nNext time you have to drink, you can refuse to.");
        challenges.put(31,"SOPRA STERIA ALARM!!\nDrink 5 zips if you work in that company....");
        challenges.put(32,"SOPRA STERIA ALARM!!\nDrink 5 zips if you work in that company....");
        challenges.put(33,"SOPRA STERIA ALARM!!\nDrink 5 zips if you work in that company....");
        challenges.put(34,"DICE! Lets see how lucky you are.\nDrink the amount on the dice...");
        challenges.put(35,"DICE! Lets see how lucky you are.\nDrink the amount on the dice...");
        challenges.put(36,"DICE! Lets see how lucky you are.\nDrink the amount on the dice...");
        challenges.put(37,"HAH!!!! GAYYYYYYY\nTake 5 zips. After that, find a video of Ronaldo crying on youtube. Watch it.");
        challenges.put(38,"HAH!!!! GAYYYYYYY\nTake 5 zips. After that, find a video of Ronaldo crying on youtube. Watch it.");
        challenges.put(39,"HAH!!!! GAYYYYYYY\nTake 5 zips. After that, find a video of Ronaldo crying on youtube. Watch it.");
        challenges.put(40,"Really? Are you sober?\nDrink 5 zips.");
        challenges.put(41,"Really? Are you sober?\nDrink 5 zips.");
        challenges.put(42,"Really? Are you sober?\nDrink 5 zips.");
        challenges.put(43,"SHOE BRUSH INSPECTION!!\nHave you brushed your shoes today?\nIf not->>5 zips");
        challenges.put(44,"SHOE BRUSH INSPECTION!!\nHave you brushed your shoes today?\nIf not->>5 zips");
        challenges.put(45,"SHOE BRUSH INSPECTION!!\nHave you brushed your shoes today?\nIf not->>5 zips");
        challenges.put(46,"You fatass!!!!\n5situps. If not-> 5 zips.");
        challenges.put(47,"You fatass!!!!\n5situps. If not-> 5 zips.");
        challenges.put(48,"You fatass!!!!\n5situps. If not-> 5 zips.");
        challenges.put(49,"All Pokemon go players must drink 3 zips.\nGrow up people......");
        challenges.put(50,"All Pokemon go players must drink 3 zips.\nGrow up people......");
        challenges.put(51,"All Pokemon go players must drink 3 zips.\nGrow up people......");
        challenges.put(52,"HAHA, RBK supporter?\nIf you are->CHUG!!");
        challenges.put(53,"HAHA, RBK supporter?\nIf you are->CHUG!!");
        challenges.put(54,"HAHA, RBK supporter?\nIf you are->CHUG!!");
        challenges.put(55,"You are DAMN sober.... \nCHUG you little scumbag");
        challenges.put(56,"You are DAMN sober.... \nCHUG you little scumbag");
        challenges.put(57,"You are DAMN sober.... \nCHUG you little scumbag");
        challenges.put(58,"That guns are like needles...\n10 pushups!");
        challenges.put(59,"That guns are like needles...\n10 pushups!");
        challenges.put(60,"That guns are like needles...\n10 pushups!");
        challenges.put(61,"BLAZER INSPECTION.\nDid you bring a blazer today?\nIf not->5 zips. ");
        challenges.put(62,"BLAZER INSPECTION.\nDid you bring a blazer today?\nIf not->5 zips. ");
        challenges.put(63,"BLAZER INSPECTION.\nDid you bring a blazer today?\nIf not->5 zips. ");
        challenges.put(64,"If you have tinder -> Swipe ten times right in a row.\nIf any matches, text them AS WE SPEAK");
        challenges.put(65,"If you have tinder -> Swipe ten times right in a row.\nIf any matches, text them AS WE SPEAK");
        challenges.put(66,"If you have tinder -> Swipe ten times right in a row.\nIf any matches, text them AS WE SPEAK");
        challenges.put(67,"You are a damn good consultant.\nIf you are working in Knowit, make another person drink 10 zips.");
        challenges.put(68,"You are a damn good consultant.\nIf you are working in Knowit, make another person drink 10 zips.");
        challenges.put(69,"You are a damn good consultant.\nIf you are working in Knowit, make another person drink 10 zips.");
        challenges.put(70,"BIEBER FAN. \n5 zips...");
        challenges.put(71,"BIEBER FAN. \n5 zips...");
        challenges.put(72,"BIEBER FAN. \n5 zips...");
        return challenges;
    }
    
    public void startWheelOfFortune() throws IOException{
        showDialog("Welcome to wheel of fortune. \nStart when ready.");
        createBoard();
        Random randomizer = new Random();
        int spinSecs = randomizer.nextInt(20) + 3;
        
        long startTime = System.currentTimeMillis();
        
        int spinCounter = 1;
        
        while(((System.currentTimeMillis()-startTime)/1000)<spinSecs){
            
            paintWheel(spinCounter);
            System.out.println("counter: "+spinCounter);
            sleepProgram(0.025);
            if(spinCounter>=72){
                spinCounter = 1;
            }else{
                spinCounter+=1;
            }
            
        }
        sleepProgram(2);
        
        String challenge = this.challenges.get(spinCounter);
        showDialog(challenge);
        sleepProgram(1);
        this.f.setVisible(false);
        
        
        if(challenge.contains("busride")){
            Busroute route = new Busroute();
        }else if(challenge.contains("roulette")){
            RussianRoulette roulette = new RussianRoulette();
        }else if(challenge.contains("dice")){
            sleepProgram(2);
            int diceNr = randomizer.nextInt(6) + 1;
            String imgPath = Integer.toString(diceNr)+".jpg";
            this.f = new JFrame();
            this.f.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("wheel/wheel_of_fortune_background.jpg")))));
            this.f.pack();
            this.f.setLayout(null);
            this.f.setResizable(false);
            this.f.setVisible(true);
            
            JLabel newJ = new JLabel(new ImageIcon(imgPath));
            newJ.setBounds(0, 0, this.img.getIconWidth(), this.img.getIconHeight());
            this.f.add(newJ);
            newJ.setVisible(true);
            this.f.repaint();
            sleepProgram(2);
            this.f.setVisible(false);
            showDialog(Integer.toString(diceNr)+" zips!!!!!!");
        }
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
