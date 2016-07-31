/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thetapgame;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Kraugen
 */
public class Busroute {
    private ArrayList<Card> cardDeck;
    private JFrame f;
    private ImageIcon img;
    private HashMap <Integer, JLabel> labelHashing;
    //private String finished;
    
    //private HashMap <Integer, ArrayList<Integer>> coordinateHashMap;
    
    public Busroute() throws IOException{
        
        
        //this.finished = "";
        this.labelHashing = new HashMap <Integer, JLabel>();
        this.cardDeck = initDeck();
        this.f = new JFrame();
        this.img = new ImageIcon(ImageIO.read(new File("busroute_background.jpg")));
        createBoard();
        paintRouteCards();
        shuffleDeck();
        performBusRoute();
    }
    
    public ArrayList<Card> initDeck(){
        ArrayList<Card> deck = new ArrayList<Card>();
        
        String[] suits = {"spades", "hearts", "diamonds", "clubs"};
        String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        
        for(int rank = 0;rank<ranks.length;rank++){
            for(int suit = 0;suit<suits.length;suit++){
                Card card = new Card(ranks[rank],suits[suit]);
                deck.add(card);
            }
        }
        return deck;
    }
    
    public void createBoard() {
        this.f.setContentPane(new JLabel(this.img));
    	this.f.pack();
        this.f.setLayout(null);
        this.f.setResizable(false);
        this.f.setDefaultCloseOperation(0);
        this.f.setVisible(true);
    }
    
    public ArrayList<Card> getDeck(){
        return this.cardDeck;
    }
    
    public boolean hasToDrink(Card card){
        return (card.getRank().equals("A")|| card.getRank().equals("J")||card.getRank().equals("Q")||card.getRank().equals("K"));
    }
    
    public void shuffleDeck(){
        Collections.shuffle(this.cardDeck);
    }
    
    public void moveCardFromDeckTodrawnCards(String rank, String suit){
        if(this.cardDeck.size()>0){
            for(int i = 0;i<this.cardDeck.size();i++){
                if(this.cardDeck.get(i).getRank().equals(rank) && this.cardDeck.get(i).getSuit().equals(suit)){
                    this.cardDeck.remove(this.cardDeck.get(i));
                }
            }
        }
    }
    
    public Card getRandomCardIMG(){
        if(this.cardDeck.size()==0){
            initDeck();
            shuffleDeck();
        }
            
            Card card = this.cardDeck.get(0);
            String rank = card.getRank();
            String suit = card.getSuit();
            moveCardFromDeckTodrawnCards(rank,suit);
            //String cardIMG = rank+suit+".jpg";
            //return cardIMG;
            
            return card;
        
    }
    
    public void printDeck(){
        for(int i = 0;i<this.cardDeck.size();i++){
            System.out.println("Card: ");
            this.cardDeck.get(i).printCard();
        }
    }
    
    public void paintRouteCards(){
        
        HashMap <Integer, ArrayList<Integer>> coordinateHashMap = new HashMap <Integer, ArrayList<Integer>>();
        int constantOffsetX = 70;
        int constantOffsetY = 100;
        
        HashMap <Integer, JLabel> labelHashing = new HashMap <Integer, JLabel>();
        HashMap <Integer, ArrayList<Integer>> coordinateHashing = new HashMap <Integer, ArrayList<Integer>>();
        
        for(int i  = 0;i<5;i++){
            ArrayList<Integer> co = new ArrayList<Integer>(); 
            co.add(-370+i*constantOffsetX);
            co.add(290);
            
            //coordinateHashMap.put(i+1, co);
            coordinateHashing.put(i+1, co);
            
            JLabel lbl = new JLabel(new ImageIcon(Integer.toString(i+1)+"_card.jpg"));
            this.labelHashing.put(i+1, lbl);
            lbl.setBounds(-370+i*constantOffsetX, 290, this.img.getIconWidth(), this.img.getIconHeight());
            
            this.f.add(lbl);
            lbl.setVisible(true);
            this.f.repaint();
        }
        
        for(int i =0;i<4;i++){
            ArrayList<Integer> co = new ArrayList<Integer>(); 
          
            co.add(-370+constantOffsetX/2+i*constantOffsetX);
            co.add(190);
            //coordinateHashMap.put(i+6, co);
            coordinateHashing.put(i+6, co);
            
            JLabel lbl = new JLabel(new ImageIcon(Integer.toString(i+6)+"_card.jpg"));
            lbl.setBounds(-370+constantOffsetX/2+i*constantOffsetX, 190, this.img.getIconWidth(), this.img.getIconHeight());
            this.labelHashing.put(i+6, lbl);
            this.f.add(lbl);
            lbl.setVisible(true);
            this.f.repaint();
        }
        
        for(int i =0;i<3;i++){
            ArrayList<Integer> co = new ArrayList<Integer>(); 
          
            co.add(-370+(i+1)*constantOffsetX);
            co.add(90);
            //coordinateHashMap.put(i+10, co);
            coordinateHashing.put(i+10, co);
            
            JLabel lbl = new JLabel(new ImageIcon(Integer.toString(i+10)+"_card.jpg"));
            lbl.setBounds(-370+(i+1)*constantOffsetX, 90, this.img.getIconWidth(), this.img.getIconHeight());
            this.labelHashing.put(i+10, lbl);
            
            this.f.add(lbl);
            lbl.setVisible(true);
            this.f.repaint();
        }
        
        for(int i = 0;i<2;i++){
            ArrayList<Integer> co = new ArrayList<Integer>(); 
          
            co.add(-370+(i+1)*constantOffsetX);
            co.add(90);
            //coordinateHashMap.put(i+13, co);
            coordinateHashing.put(i+13, co);
            
            JLabel lbl = new JLabel(new ImageIcon(Integer.toString(i+13)+"_card.jpg"));
            lbl.setBounds(-370+(i+1)*constantOffsetX+constantOffsetX/2, -10, this.img.getIconWidth(), this.img.getIconHeight());
            this.labelHashing.put(i+13, lbl);
            this.f.add(lbl);
            lbl.setVisible(true);
            this.f.repaint();
        }
        
        ArrayList<Integer> co = new ArrayList<Integer>(); 
          
        co.add(-370+2*constantOffsetX);
        co.add(-110);
        coordinateHashing.put(15, co);
        JLabel lbl = new JLabel(new ImageIcon("15_card.jpg"));
        lbl.setBounds(-370+constantOffsetX*2, -110, this.img.getIconWidth(), this.img.getIconHeight());
        this.labelHashing.put(15, lbl);
        this.f.add(lbl);
        lbl.setVisible(true);
        this.f.repaint();
        
       // this.labelHashing.get(4).setVisible(false);
    }
    
    public void stateSlurps(int slurps){
        AppearWindow stateSlurps = new AppearWindow("Drink "+ Integer.toString(slurps) +" zips");
        stateSlurps.ShowFrame(true);
        stateSlurps.whileConnected();
    }
    
    public void displayBusrouteDone(){
        AppearWindow stateSlurps = new AppearWindow("BUSROUTE COMPLETED. WELL DONE");
        stateSlurps.ShowFrame(true);
        stateSlurps.whileConnected();
        
    }
    
    public void sleepProgram(int seconds){
        int milliSeconds = seconds*1000;
        
        try {
            Thread.sleep(milliSeconds);                 
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
    
    public void showDrawnCard(String cardString){
        JLabel lbl = new JLabel(new ImageIcon(cardString));
        lbl.setBounds(100, 100, this.img.getIconWidth(), this.img.getIconHeight());
        this.f.add(lbl);
        lbl.setVisible(true);
        this.f.repaint();
        sleepProgram(2);
        lbl.setVisible(false);
        this.f.repaint();
        
    }
    
    public void performBusRoute(){
        int rowCounter = 1;
        boolean finished = false;
        
        while(finished==false){
            //Do busroute
            BusControl atb = new BusControl(rowCounter);
            atb.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            atb.whileConnected();
            
            int cardNumber = atb.getCardNumber();
            System.out.println("Cardnumber: "+cardNumber);
            
            sleepProgram(1);
            
            this.labelHashing.get(cardNumber).setVisible(false);
            Card c = getRandomCardIMG();
            String cardIMG = c.getRank()+c.getSuit()+".jpg";
            showDrawnCard(cardIMG);
            
            
            if(hasToDrink(c)){
                stateSlurps(rowCounter);
                
                for(int i = 1;i<this.labelHashing.size()+1;i++){
                    this.labelHashing.get(i).setVisible(true);
                }
                rowCounter= 1;
                
            }else{
                rowCounter++;
            }
            
            
            if(rowCounter==6){
                finished = true;
                displayBusrouteDone();
                this.f.setVisible(false);
            }
            
        }
    }
    
    public static void main(String[] args) throws IOException {
        Busroute bus = new Busroute();
    }
}
