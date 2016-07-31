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
public class Ping {
    private final String name;
    private final int beautyPoints;
    private final String brahSize;
    private double weight;
    private double height;
    private String hairColor;
    private final String countryOfOrigin;
    
    public Ping(String name, int beautyPoints, String brahSize,double weight, double height,String hairColor,String countryOfOrigin){
                this.name = name;
                this.beautyPoints = beautyPoints;
                this.brahSize = brahSize;
                this.weight = weight;
                this.height = height;
                this.hairColor = hairColor;
                this.countryOfOrigin = countryOfOrigin;
    }
    
    public String getName(){
        return this.name;
    }
    
    public double getWeight(){
        return this.weight;
    }
    
    public void setWeight(double weight){
        this.weight = weight;
    }
    
    public void setHeight(double height){
        this.height = height;
    }
    
    public double getHeight(){
        return this.height;
    }
    
    public int getBeautyPoints(){
        return this.beautyPoints;
    }
    
    public String getBrahSize(){
        return this.brahSize;
    }
    
    public String getHairColor(){
        return this.hairColor;
    }
    
    public void setHairColor(String hairColor){
        this.hairColor = hairColor;
    }
    
    public String getCountryOfOrigin(){
        return this.countryOfOrigin;
    }
    
    public void printPing(){
        System.out.println("name: "+this.name);
        System.out.println("beautyPoints: "+this.beautyPoints);
        System.out.println("brahSize: "+this.brahSize);
        System.out.println("weight: "+this.weight);
        System.out.println("height: "+this.height);
        System.out.println("hairColor: "+this.hairColor);
        System.out.println("country: "+this.countryOfOrigin);
    }
}
