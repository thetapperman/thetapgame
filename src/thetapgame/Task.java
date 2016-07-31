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
public class Task {
    private final String task;
    private final int advantage;
    private final int punishment;
    private Field taskField;
    
    public Task(String task,int advantage,int punishment,Field taskField){
        this.task = task;
        this.advantage = advantage;
        this.punishment = punishment;
        this.taskField = taskField;
    }
    
    public String getTask(){
        return this.task;
    }
    
    
    public Field getTaskField(){
        return this.taskField;
    }
    
    public int getAdvantage(){
        return this.advantage;
    }
    
    public int getPunishment(){
        return this.punishment;
    }
    
    public void printTask(){
        System.out.println("Task: "+this.task);
        System.out.println("Punishment: "+this.punishment);
        System.out.println("Advantage: "+this.advantage);
    }
}
