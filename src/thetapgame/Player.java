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
    private Field activeField;
    
    
    public Player(String name){
        this.name = name;
        this.activeField = new Field(0,0, false,false,"");
    }
    
    public String getName(){
        return this.name;
    }
    
    public void setActiveField(Field activeField){
        this.activeField = activeField;
    }
    
    public Field getActiveField(){
        return this.activeField;
    }
    
    public void printPlayer(){
        System.out.println("Name: "+this.name);
        System.out.println("Active field: ");
        this.activeField.printField();
    }
    
}
