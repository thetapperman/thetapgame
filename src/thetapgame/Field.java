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
public class Field {
    private int xValue;
    private int yValue;
    
    private boolean hasTask;
    private boolean hasLadder;
    
    private String moveDir;
    private String tapChallenge;
   
    public Field(int xValue,int yValue, boolean hasTask,boolean hasLadder, String tapChallenge){
        this.xValue = xValue;
        this.yValue = yValue;
        
        this.hasTask = hasTask;
        this.hasLadder = hasLadder;
        
        this.tapChallenge = tapChallenge;
        this.moveDir = "";
    }
    
    public void setTapChallenge(String tapChallenge){
        this.tapChallenge = tapChallenge;
    }
    
    public String getTapChallenge(){
        System.out.println("TapchallengeInFieldsClass: "+this.tapChallenge);
        return this.tapChallenge;
    }
    
    public void setHasTask(boolean task){
        this.hasTask = task;
    }
    
    public String getMoveDir(){
        return this.moveDir;
    }
    
    public void setMoveDir(String moveDir){
        this.moveDir = moveDir;
    }
    
    public boolean getHasTask(){
        return this.hasTask;
    }
    
    public void setHasLadder(boolean hasLadder){
        this.hasLadder = hasLadder;
    }
    
    public boolean getHasLadder(){
        return this.hasLadder;
    }
    
    
    public int getXValue(){
        return this.xValue;
    }
    
    public int getYValue(){
        return this.yValue;
    }
    
    public void printField(){
        System.out.println("x: "+this.xValue);
        System.out.println("y: "+this.yValue);
        System.out.println("hasTask?: "+this.hasTask);
        System.out.println("hasLadder?: "+this.hasLadder);  
        System.out.println("MoveDir: "+this.moveDir);
    }
}
