/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thetapgame;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JLabel;

/**
 *
 * @author Kraugen
 */
public class HorseRace {
    private int rows;
    private ArrayList<Card> deck; //card: isUsed
    private HashMap <Card, Integer> aces;
   
    public HorseRace(){
        this.rows = 5;
        this.deck = initDeck();
        shuffleDeck();
        this.aces = initAces();
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
   
   public Card getRandomCard(){
       
        if(this.deck.size()==0){
            this.deck = initDeck();
            shuffleDeck();
        }
            Card card = this.deck.get(0);
            String rank = card.getRank();
            String suit = card.getSuit();
            moveCardFromDeckTodrawnCards(rank,suit);
            
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
    
    public String startHorseRace(){
        boolean isFinished = false;
        
        ArrayList<Integer> turnedCards = new ArrayList<Integer>();
        int highestReachedCard = 0;
        
        HashMap <Integer,Boolean> punishMap = new HashMap <Integer,Boolean>();
        punishMap = initPunishMap();
        
        String suit = "";
        while(!isFinished){
            
            int highest = updateHighestTurned();
            if(!turnedCards.contains(highest)&&highest!=0){//Hvis ikke kortet er flippet og alle har passert->flip. Straff de 
                turnedCards.add(highest);
                Card punishmentCard = getRandomCard();
                System.out.println("turned card: ____________"+punishmentCard.getSuit()+"______________");
                punishGuilty(punishmentCard.getSuit());
            }else{ //Gå videre
                Card card = getRandomCard();
                updatePositions(card.getSuit());
                highestReachedCard = updateHighestReachedCard();
                System.out.println("highest reached card: "+highestReachedCard);
            }
            
            //Dopingtest:
            if(punishMap.containsKey(highestReachedCard) && punishMap.get(highestReachedCard)==false){
                punishMap.put(highestReachedCard,true);
                Card drugCard = getRandomCard();
                System.out.println("Checking for drugs...");
                    if(returnGuiltySuit(highestReachedCard).equals(drugCard.getSuit())){
                        punishDrugathic(drugCard.getSuit());
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
        return suit;
    }
    
     public static void main(String[] args) throws IOException {
        HorseRace horse = new HorseRace();
        System.out.println("Starting tour");
        String winner = horse.startHorseRace();
        System.out.println("Winner: "+winner);
    }
}