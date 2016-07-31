/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thetapgame;

import java.util.Random;

/**
 *
 * @author Kraugen
 */
public class Dice {
 
    private int value;
    
    public Dice(){
        ThrowDice();
    }
    
    public void ThrowDice(){
        Random randomizer = new Random();
        int random_value = randomizer.nextInt(6) + 1;
        this.value = random_value;
    }
    
    public int getValue(){
        return this.value;
    }
    
    public void PrintDice(){
        System.out.println("Dice value :"+this.value);
    }
    
}
