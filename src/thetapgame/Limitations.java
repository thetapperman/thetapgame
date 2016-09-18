/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thetapgame;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JLabel;

/**
 *
 * @author Kraugen
 */
public class Limitations {
    private HashMap<String,Integer> tapChallenges = new HashMap<String,Integer>();
    
    public Limitations(){
        this.tapChallenges = initTapChallenges();
    }
    
    public HashMap<String,Integer> initTapChallenges(){
         HashMap<String,Integer> challenges = new  HashMap<String,Integer> ();
         
         //ADD HER FOR Å LEGGE TIL NYE CHALLENGES
         challenges.put("text",20);
         challenges.put("bus",10);
         challenges.put("horse",15);
         challenges.put("roulette",10);
         challenges.put("wheel",15);
         challenges.put("jokernord",10);
         
         
         return challenges;
    }
    
    public void mapChallengesToEvents(String challenge) throws IOException{
        if(challenge.equals("bus")){
            Busroute route = new Busroute();
        }else if(challenge.equals("horse")){
            HorseRace horse = new HorseRace();
        }else if(challenge.equals("roulette")){
            RussianRoulette roulette = new RussianRoulette();
        }else if(challenge.equals("wheel")){
            WheelOfFortune wheel = new WheelOfFortune();
        }else if(challenge.equals("text")){
            TextChallengeDatabase db = new TextChallengeDatabase();
            Util.showDialog(db.getRandChallenge());
        }else if(challenge.equals("jokernord")){
            JokerNord jn = new JokerNord();
        }else if(challenge.equals("")){
            System.out.println("not challenge here");
        }
    }
    
    public int getNumberOfTapChallengeTypes(){
        return this.tapChallenges.size();
    }
    
    public int getSumOfChallengesOnBoard(){
        int summer = 0;
        for (Map.Entry<String, Integer> iterator : this.tapChallenges.entrySet()) {
            summer+=iterator.getValue();
        }
        return summer;
    }
    
    public ArrayList<String> getStringMapOfChallenges(){
        ArrayList<String> stringMap = new ArrayList<String>();
        for (Map.Entry<String, Integer> iterator : this.tapChallenges.entrySet()) {
            stringMap.add(iterator.getKey());
        }
        return stringMap;
    }
    
    public HashMap<String,Integer>  getChallengeTypes(){
        return this.tapChallenges;
    }
}
