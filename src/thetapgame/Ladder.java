/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thetapgame;

/**
 *
 * @author Kraugen
 */
public class Ladder {
    private String ladderType;
    private Field advantage;
    private Field disadvantage;
    private Field ladderField;
    
    
    public Ladder(String ladderType, Field advantage,Field disadvantage, Field ladderField){
        this.advantage = advantage;
        this.ladderType = ladderType;
        this.ladderField = ladderField;
        this.disadvantage = disadvantage;
    }
    
    public String getladderType(){
        return this.ladderType;
    }
    
     public Field getAdvantage(){
        return this.advantage;
    }
     
    public Field getDisAdvantage(){
        return this.disadvantage;
    }
     
    public Field getLadderField(){
        return this.ladderField;
    }
    
    public void printLadder(){
        System.out.println("ladderType: "+this.ladderType);
        System.out.println("advantage: ");
        this.advantage.printField();
        System.out.println("ladderField: ");
        this.ladderField.printField();
    }
}
