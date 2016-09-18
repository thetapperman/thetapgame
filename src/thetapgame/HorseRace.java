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
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Kraugen
 */
public class HorseRace {
    private int rows;
    private ArrayList<Card> deck; //card: isUsed
    private HashMap <Card, Integer> aces;
    private ImageIcon img;
    private JFrame f;
    private HashMap <Integer, JLabel> labelHashingNormalBackCards;
    private HashMap <Integer, JLabel> labelHashingDrugBackCards;
    private HashMap <String, JLabel> labelHashingAces;
    private HashMap <String, Integer> acePos;
   
    public HorseRace() throws IOException{
        this.rows = 5;
        this.deck = initDeck();
        shuffleDeck();
        this.aces = initAces();
        this.f = new JFrame();
        this.img = new ImageIcon(ImageIO.read(new File("img/horse_background.jpg")));
        createBoard();
        this.labelHashingNormalBackCards = createLabelHashingNormalBack();
        this.labelHashingDrugBackCards = createLabelHashingDrugs();
        this.labelHashingAces = createLabelHashingAces();
        startHorseRace();
        
    }
   
    public void printBackCardsLabel(){
        for (Map.Entry<Integer, JLabel> iterator : this.labelHashingNormalBackCards.entrySet()) {
            System.out.println(iterator.getKey()+":"+iterator.getValue());
        } 
    }
    
    public void moveAce(int index, String suit){
        this.labelHashingAces.get(suit).setBounds(this.acePos.get(suit), 360 - index*100, this.img.getIconWidth(), this.img.getIconHeight());
    }
    
     public HashMap <String, JLabel> createLabelHashingAces(){
        HashMap <String, JLabel> labelHashingAces = new HashMap <String, JLabel>();
        HashMap <String, Integer> acePos = new HashMap <String, Integer>();
         String[] suits = {"spades", "hearts", "diamonds", "clubs"};
        
         for(int i = 0;i<suits.length;i++){
             String imagePath = "img/Ah";
             imagePath+=suits[i];
             imagePath+=".gif";
             labelHashingAces.put(suits[i], paintRouteCards(imagePath,-100+i*100,360));
             acePos.put(suits[i], -100+i*100);
             
         }
        this.acePos = acePos;
        return labelHashingAces;
    }
    
    public HashMap <Integer, JLabel> createLabelHashingDrugs(){
        HashMap <Integer, JLabel> hash = new HashMap <Integer, JLabel>();
        
        for(int i = 1;i<this.rows;i++){
            if(i% 2 == 0){
                hash.put(i,paintRouteCards("img/backhcard.gif",-280,260-(i)*100));
            }
        }
        return hash;
    }
    
    public HashMap <Integer, JLabel> createLabelHashingNormalBack(){
        HashMap <Integer, JLabel> hash = new HashMap <Integer, JLabel>();
        
        for(int i = 0;i<this.rows;i++){
            
            hash.put(i+1,paintRouteCards("img/backhcard.gif",-200,260-i*100));
        }
        
        
        return hash;
        
    }
    
    public void setNormalBackCardInvisible(int nr){
        this.labelHashingNormalBackCards.get(nr).setVisible(false);
    }
    
    public void setDrugCardInvisible(int nr){
        this.labelHashingDrugBackCards.get(nr).setVisible(false);
        
    }
    
    public JLabel paintRouteCards(String card,int x,int y){
        
            JLabel lbl = new JLabel(new ImageIcon(card));
            lbl.setBounds(x, y, this.img.getIconWidth(), this.img.getIconHeight());
            
            this.f.add(lbl);
            lbl.setVisible(true);
            this.f.repaint();
            
            return lbl;
    }
    
    public void displayDrawnBackCard(Card card, int nr){
        setNormalBackCardInvisible(nr);
        
        JLabel lbl = new JLabel(new ImageIcon("img/"+card.getRank()+"h"+card.getSuit()+".gif"));
        
        lbl.setBounds(-200, 260-(nr-1)*100, this.img.getIconWidth(), this.img.getIconHeight());
        this.f.add(lbl);
        lbl.setVisible(true);
        this.f.repaint();
        sleepProgram(1);
    }
    
    public void gg(){
        for (Map.Entry<Integer, JLabel> iterator : this.labelHashingDrugBackCards.entrySet()) {
            System.out.println("drug: "+iterator.getKey()+", "+iterator.getValue());
        }
    }
    
    public void createBoard() {
        this.f.setContentPane(new JLabel(this.img));
    	this.f.pack();
        this.f.setLayout(null);
        this.f.setResizable(false);
        this.f.setDefaultCloseOperation(0);
        this.f.setVisible(true);
    }
    
    public HashMap <Card, Integer> initAces(){
        HashMap <Card, Integer> aces = new HashMap <Card, Integer>();
        String[] suits = {"spades", "hearts", "diamonds", "clubs"};
        String rank = "A";
        
        for(int i= 0;i<suits.length;i++){
            Card card = new Card(rank,suits[i]);
            aces.put(card, 0);
        }
        
        return aces;
    }
    
    public void updatePositionOfAce(String rank,String suit){
        for (Map.Entry<Card, Integer> iterator : this.aces.entrySet()) {
            this.aces.put(iterator.getKey(),iterator.getValue()+1);
        }
    }
    
    public void shuffleDeck(){
        Collections.shuffle(this.deck);
    }
    
    public void moveCardFromDeckTodrawnCards(String rank, String suit){
        if(this.deck.size()>0){
            for(int i = 0;i<this.deck.size();i++){
                if(this.deck.get(i).getRank().equals(rank) && this.deck.get(i).getSuit().equals(suit)){
                    this.deck.remove(this.deck.get(i));
                }
            }
        }
    }
    
   public void paintDrawCard(String path){
        
        JLabel lbl = new JLabel(new ImageIcon(path));
        lbl.setBounds(100, -200, this.img.getIconWidth(), this.img.getIconHeight());

        this.f.add(lbl);
        lbl.setVisible(true);
        this.f.repaint();
        sleepProgram(1);
        this.f.add(lbl);
        lbl.setVisible(false);
        this.f.repaint();
        sleepProgram(1);
   }
   
   public Card getRandomCard(boolean flag){
       
        if(this.deck.size()==0){
            this.deck = initDeck();
            shuffleDeck();
        }
            Card card = this.deck.get(0);
            String rank = card.getRank();
            String suit = card.getSuit();
            moveCardFromDeckTodrawnCards(rank,suit);
            
            if(flag){
                String cardPath = "img/"+card.getRank()+card.getSuit()+".jpg";
                paintDrawCard(cardPath);
            }
            
            return card;
    }
    
    public ArrayList<Card> initDeck(){
        ArrayList<Card> deck = new ArrayList<Card>();
        
        String[] suits = {"spades", "hearts", "diamonds", "clubs"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        
        for(int rank = 0;rank<ranks.length;rank++){
            for(int suit = 0;suit<suits.length;suit++){
                Card card = new Card(ranks[rank],suits[suit]);
                deck.add(card);
            }
        }
        return deck;
    }
    
    public String checkIfWinner(){
        String winnerSuit = "";
        for (Map.Entry<Card, Integer> iterator : this.aces.entrySet()) {
            if(iterator.getValue()==this.rows+1){
                winnerSuit = iterator.getKey().getSuit();
            }
        }
        return winnerSuit;
    }
    
    public void updatePositions(String suit){
        for (Map.Entry<Card, Integer> iterator : this.aces.entrySet()) {
            if(iterator.getKey().getSuit().equals(suit)){
                this.aces.put(iterator.getKey(),iterator.getValue()+1);
            }
        } 
    }
    
    public boolean isTurnTime(int highestTurnedCard){
        boolean isPunishment = true;
        
        for (Map.Entry<Card, Integer> iterator : this.aces.entrySet()) {
            if(iterator.getValue()<highestTurnedCard){
                isPunishment = false;
            }
        }
        return isPunishment;
    }
    
    public void punishGuilty(String suit){
        for (Map.Entry<Card, Integer> iterator : this.aces.entrySet()) {
            if(iterator.getKey().getSuit().equals(suit)){
                this.aces.put(iterator.getKey(),iterator.getValue()-1);
                System.out.println("\n\nloosing position_______________: "+iterator.getKey().getSuit());
            }
        } 
    }
    
    public int updateHighestReachedCard(){
        return Collections.max(this.aces.values());
    }
    
    public void printPositions(){
        for (Map.Entry<Card, Integer> iterator : this.aces.entrySet()) {
            System.out.println("Suit: "+iterator.getKey().getSuit());
            System.out.println("Position: "+iterator.getValue());
           
        } 
         System.out.println("\n\n_______________________________________________________");
    }
    
    public int updateHighestTurned(){
        return Collections.min(this.aces.values());
    }
    
    public void punishDrugathic(String suit){
        for (Map.Entry<Card, Integer> iterator : this.aces.entrySet()) {
            if(iterator.getKey().getSuit().equals(suit)){
                System.out.println("----------------->guilty-------------->"+suit);
                this.aces.put(iterator.getKey(),0);
            }
        } 
    }
    
    
    public String returnGuiltySuit(int highestReached){
        String suit = "";
        for (Map.Entry<Card, Integer> iterator : this.aces.entrySet()) {
            if(iterator.getValue()==highestReached){
                suit=iterator.getKey().getSuit();
            }
        } 
        return suit;
    }
    
    public HashMap <Integer,Boolean> initPunishMap(){
        HashMap <Integer,Boolean> p = new HashMap <Integer,Boolean>();
       
        for(int i = 2;i<this.rows+1;i++){
            if(i% 2 == 1){
                p.put(i,false);
            }
        }
        
        for (Map.Entry<Integer, Boolean> iterator : p.entrySet()) {
            System.out.println("card: "+iterator.getKey());
            System.out.println("isPunished: "+iterator.getValue());
        } 
        
        return p;
    }
    
    public void sleepProgram(int seconds){
        int milliSeconds = seconds*1000;
        
        try {
            Thread.sleep(milliSeconds);                 
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
    
    public void printAces(){
         for (Map.Entry<String, Integer> iterator : this.acePos.entrySet()) {
            System.out.println("ace: "+iterator.getValue());
        } 
    }
    
    public int getIndexByKey(String suit){
        int index=0;
        for (Map.Entry<Card, Integer> iterator : this.aces.entrySet()) {
            if(iterator.getKey().getSuit()==suit){
                index = iterator.getValue();
            }
        } 
        return index;
    }
    
    public void setVisibleDrugCard(int nr,Card drugCard){
        String path="img/"+drugCard.getRank()+"h"+drugCard.getSuit()+".gif";
        JLabel lbl = new JLabel(new ImageIcon(path));
        
        lbl.setBounds(-280, 260-(nr)*100, this.img.getIconWidth(), this.img.getIconHeight());
        this.f.add(lbl);
        lbl.setVisible(true);
        this.f.repaint();
     
        sleepProgram(1);
    }
    
    public void startHorseRace(){
        sleepProgram(1);
        String winner ="";
        boolean isFinished = false;
        Util.showDialog("Welcome to the horserace.\nAll players guess a suit for the cards.\nThe player(s) choosing the winning suit hands out a bonskie to another person");
        ArrayList<Integer> turnedCards = new ArrayList<Integer>();
        int highestReachedCard = 0;
        
        ArrayList<Integer> punMap = new ArrayList<Integer>();
        HashMap <Integer,Boolean> punishMap = new HashMap <Integer,Boolean>();
        punishMap = initPunishMap();
        
        String suit = "";
        while(!isFinished){
           // printBackCardsLabel();
            int highest = updateHighestTurned();
            if(!turnedCards.contains(highest)&&highest!=0){//Hvis ikke kortet er flippet og alle har passert->flip. Straff de 
                turnedCards.add(highest);
                Card punishmentCard = getRandomCard(false);
                displayDrawnBackCard(punishmentCard,highest);
                System.out.println("turned card: ____________"+punishmentCard.getSuit()+"______________");
                sleepProgram(1);
                punishGuilty(punishmentCard.getSuit());
                int index = getIndexByKey(punishmentCard.getSuit());
                
                moveAce(index, punishmentCard.getSuit());
                sleepProgram(1);
            }

            Card card = getRandomCard(true);
            updatePositions(card.getSuit());

            int index = getIndexByKey(card.getSuit());
            moveAce(index, card.getSuit());
            sleepProgram(1);
            highestReachedCard = updateHighestReachedCard();
            System.out.println("highest reached card: "+highestReachedCard);
            
            
            //Dopingtest:
            if(!punMap.contains(highestReachedCard) && highestReachedCard%2==1 && highestReachedCard>1 ){
                //punishMap.put(highestReachedCard,true);
                punMap.add(highestReachedCard);
                
                Card drugCard = getRandomCard(false);
                System.out.println("Checking for drugs...");
                System.out.println("highestReached: "+highestReachedCard);
                gg();
                setDrugCardInvisible(highestReachedCard-1);
                setVisibleDrugCard(highestReachedCard-1,drugCard);
                    if(returnGuiltySuit(highestReachedCard).equals(drugCard.getSuit())){
                        
                        punishDrugathic(drugCard.getSuit());
                        moveAce(0, drugCard.getSuit());
                        sleepProgram(1);
                        System.out.println("................punishing:..................");
                    }
            }
            
            
            String check = checkIfWinner();
            if(check!=""){
                isFinished = true;
                suit = check;
            }
            printPositions();
        }
        winner = suit;
        
        Util.showDialog("Winner: "+winner+"\nThe players guessing "+winner+" should choose a person to finish his/her drink.");
        this.f.setVisible(false);
        
    }
    
    
     public static void main(String[] args) throws IOException {
        HorseRace horse = new HorseRace();
    }
}