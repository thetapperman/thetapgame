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
    private boolean hasBus;
    private boolean hasRoulette;
   
    public Field(int xValue,int yValue, boolean hasTask,boolean hasLadder,boolean hasBus,boolean hasRoulette){
        this.xValue = xValue;
        this.yValue = yValue;
        this.hasTask = hasTask;
        this.hasLadder = hasLadder;
        this.hasBus = hasBus;
        this.moveDir = "";
    }
    
    public void setHasRoulette(boolean hasRoulette){
        this.hasRoulette = hasRoulette;
    }
    
    public boolean getHasRoulette(){
        return this.hasRoulette;
    }
    
    public void setHasTask(boolean task){
        this.hasTask = task;
    }
    
    public void setHasBus(boolean hasBus){
        this.hasBus = hasBus;
    }
    
    public boolean getHasBus(){
        return this.hasBus;
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
