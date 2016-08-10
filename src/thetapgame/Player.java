/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thetapgame;

import java.util.ArrayList;

/**
 *
 * @author Kraugen
 */
public class Player {
    
    private final String name;
    private int tappedPeople;
    private int facialsLastWeek;
    private int tinderMatchNumber;
    private ArrayList<Ping> tappedPing;
    
    private Field activeField;
    
    private int bonusPoints;
    
    //public Player(String name, int tappedPeople, int facialsLastWeek, int tinderMatchNumber,ArrayList<Ping> tappedPing){
    public Player(String name){
        this.name = name;
        //this.tappedPeople = tappedPeople;
        //this.facialsLastWeek = facialsLastWeek;
        //this.tinderMatchNumber = tinderMatchNumber;
        //this.bonusPoints = 0;
        this.activeField = new Field(0,0, false,false,"");
        //this.tappedPing = tappedPing;
    }
    
    
    
    public String getName(){
        return this.name;
    }
    
    public ArrayList<Ping> getPing(){
        return this.tappedPing;
    }
    
    public void setActiveField(Field activeField){
        this.activeField = activeField;
    }
    
    public Field getActiveField(){
        return this.activeField;
    }
    
    public int getTappedPeople(){
        return this.tappedPeople;
    }
    
    public int getFacialsLastWeek(){
        return this.facialsLastWeek;
    }
    
    public int getTinderMatchNumber(){
        return this.tinderMatchNumber;
    }
    
    public void setBonusPoints(int bonusPoints){
        this.bonusPoints = bonusPoints;
    }
    
    public void printPlayer(){
        System.out.println("Name: "+this.name);
        //System.out.println("Tapped amount of bitches: "+this.tappedPeople);
        //System.out.println("Number of matches on Tinder: "+this.tinderMatchNumber);
        //System.out.println("Number of facials last week: "+this.facialsLastWeek);
        
        //System.out.println("Bonuspoints: "+this.bonusPoints);
        System.out.println("Active field: ");
        this.activeField.printField();
        
        //System.out.println("Ping: ");
        
        /*for(int ping_number = 0;ping_number<tappedPing.size();ping_number++){
            this.tappedPing.get(ping_number).printPing();
        }*/
    }
    
}
