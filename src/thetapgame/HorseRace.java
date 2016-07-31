/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thetapgame;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JLabel;

/**
 *
 * @author Kraugen
 */
public class HorseRace {
    private int rows;
    private HashMap <Card, Integer> aces; //card: position
    private HashMap <Card, Boolean> deck; //card: isUsed
    private HashMap <Card, String> playCards; //card: String punishment
    
    public HorseRace(int rows){
        this.rows = rows;
        this.aces = initAces();
        this.deck = initDeck();
        this.playCards = initPlayCards();
    }
    
    public HashMap <Card, Boolean> initPlayCards(){
        ArrayList<Card> playcards = new ArrayList<Card>();
        
        for(int cardNum=0;cardNum<this.rows;cardNum++){
        
        }
    }
    
    public HashMap <Card, Integer> initAces(){
        HashMap <Card, Integer> aces = new HashMap<Card,Integer>();
        String[] suits = {"spades", "hearts", "diamonds", "clubs"};
        String rank = "A";
        for(int cardNumber = 0;cardNumber<suits.length;cardNumber++){
            Card ace = new Card(rank,suits[cardNumber]);
            aces.put(ace,0);
        }
        return aces;
    }
   
    
    public HashMap <Card, Boolean> initDeck(){
        HashMap <Card, Boolean> deck  = new HashMap<Card,Boolean>();
        
        String[] suits = {"spades", "hearts", "diamonds", "clubs"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        
        for(int rank = 0;rank<ranks.length;rank++){
            for(int suit = 0;suit<suits.length;suit++){
                Card card = new Card(ranks[rank],suits[suit]);
                deck.put(card,false);
            }
        }
        return deck;
    }
    
    
    
    public void startHorseRace(){
    
    }
    
     public static void main(String[] args) throws IOException {
        HorseRace horse = new HorseRace(10);
    }
    
}