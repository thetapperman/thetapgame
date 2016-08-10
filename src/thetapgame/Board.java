 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thetapgame;

import static com.sun.org.apache.xalan.internal.lib.ExsltDynamic.map;
import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Kraugen
 */
public class Board {
    private String gameName;
    private ArrayList<Field> gameFields;
    private Dice dice;
    private ArrayList<String> playerNames;
    private ArrayList<Player> players;
    private ArrayList<Ladder> ladders;
    private ArrayList<Task> tasks;
    private GameBoard g;
    private HashMap<Field, String> challengeMap = new HashMap<Field, String>();
    private HashMap<Field, String> tapChallengeMap;
    
    public Board(ArrayList<String> playerNames, String gameName) throws IOException{
        this.gameName = gameName;
        this.playerNames = playerNames;
        this.dice = initDice();
        this.players = initPlayers();
        this.gameFields = initFields();
        this.ladders = placeLadders(); 
        this.tapChallengeMap = setTapChallengesOnBoard();
        this.g = new GameBoard(players.size(),this.playerNames);
        
        g.CreateBoard();
        g.setPiecesStart();
        g.paintTapChallengeLogosOnGameBoard(tapChallengeMap);
        setChallengeFlagOnGameBoardFields();
        startGame();
    }
    
    public boolean searchForFieldInTapChallenges(Field field,HashMap<Field, String> tapChallengeFields){
        boolean hasField = false;
        for (Entry<Field, String> taskIterator : tapChallengeFields.entrySet()) {
            if(taskIterator.getKey().getXValue()==field.getXValue() && taskIterator.getKey().getYValue()==field.getYValue()){
                hasField = true;
            }
        }
        return hasField;
    }
    
    public HashMap<Field, String> setTapChallengesOnBoard(){
        HashMap<Field, String> tapChallengeFields = new HashMap<Field, String>();
        Limitations challengeLimits = new Limitations();
        int nrOfChallengesOnBoard = challengeLimits.getNumberOfTapChallengeTypes();
        ArrayList<String> challengeTypes = challengeLimits.getStringMapOfChallenges();
        
        for(int challenge = 0;challenge<nrOfChallengesOnBoard;challenge++){
            boolean finishedPlacingTapChallenge = false;
            int challengePlaced = 0;
            int challengeLimit = challengeLimits.getChallengeTypes().get(challengeTypes.get(challenge));
            
            while (!finishedPlacingTapChallenge){
                Field randField = returnRandField();
                
                if(!searchForFieldInTapChallenges(randField,tapChallengeFields)){
                    tapChallengeFields.put(randField,challengeTypes.get(challenge));
                    challengePlaced++;
                }
                
                if(challengePlaced == challengeLimit){
                    finishedPlacingTapChallenge = true;
                }
            }
        }
        return tapChallengeFields;
    }
    
    public Field returnRandField(){
        Random randomizer = new Random();
        return new Field(randomizer.nextInt(9) + 1,randomizer.nextInt(9) + 1,false,false,"");
    }
    
    public Dice initDice(){
        return new Dice();
    }
    
    public void setChallengeFlagOnGameBoardFields(){
        for (Entry<Field, String> taskIterator : this.tapChallengeMap.entrySet()) {
            for(int i  = 0;i<this.gameFields.size();i++){
                if(this.gameFields.get(i).getXValue()==taskIterator.getKey().getXValue() && this.gameFields.get(i).getYValue()==taskIterator.getKey().getYValue()){
                    this.gameFields.get(i).setTapChallenge(taskIterator.getValue());
                    System.out.println("setting challenge. it is: "+taskIterator.getValue());
                    System.out.println("Gamefield: "+this.gameFields.get(i).getTapChallenge());
                }
            }
        }
    }
    
    private Field MoveRightAdvantage(Field field_player,Task task){
        Field newField = new Field(0,0,false,false,"");
        
        if((9-field_player.getXValue())<task.getAdvantage()){
                        
            if(field_player.getXValue()==9){
                newField = new Field(9-task.getAdvantage()+1,field_player.getYValue()+1,false,false,"");
            }else{
                            
                if(task.getAdvantage()-(9-field_player.getXValue())==1){
                    newField = new Field(9,field_player.getYValue()+1,false,false,"");
                }else{     
                    int fieldsToMove = task.getAdvantage()-(9-field_player.getXValue());
                    newField = new Field(9-fieldsToMove+1,field_player.getYValue()+1,false,false,"");
                    }
                }
                   
        }else{
            newField = new Field(field_player.getXValue()+task.getAdvantage(),field_player.getYValue(),false,false,"");
            }
        return newField;
    }
    
    private Field MoveLeftAdvantage(Field field_player,Task task){
        Field newField = new Field(0,0,false,false,"");
                    
        if(task.getAdvantage()>(field_player.getXValue()-1)){
            if(field_player.getXValue()==1){
                newField = new Field(task.getAdvantage(),field_player.getYValue()+1,false,false,"");
            }else{
                newField = new Field(task.getAdvantage()-(field_player.getXValue()-1),field_player.getYValue()+1,false,false,"");
            }  
        }else{
            newField = new Field(field_player.getXValue()-task.getAdvantage(),field_player.getYValue(),false,false,"");
        }
                    
        return newField;
    }
    
    public Field movePlayer(int diceValue,Field field_player){ 
        
        Field newField = new Field(0,0,false,false,"");

        if(field_player.getMoveDir()=="right"){
            newField = MoveRightAdvantage(field_player,new Task("",diceValue,0,new Field(1,2,true,false,"")));
        }else if(field_player.getMoveDir()=="left"){
            newField = MoveLeftAdvantage(field_player,new Task("",diceValue,0,new Field(1,2,true,false,"")));
        }else if(field_player.getMoveDir()==null){
        }
        return newField;
    }
    
    public boolean checkIfSix(int diceValue){
        if(diceValue == 6){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean isCloseGoal(Field player_field){
        if(player_field.getYValue()==9 && (9-player_field.getXValue())<=6){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean isWinner(Player player){
        if(player.getActiveField().getXValue()==9 && player.getActiveField().getYValue()==9){
            return true;
        }else{
            return false;
        }
    }
    
    public Field calculateGoal(int diceValue, Field player_field){
        Field newField = new Field(0,0,false,false,"");
        if(isCloseGoal(player_field)){
            if((player_field.getXValue()+diceValue)==9){
                newField = new Field(9,9,false,false,"");
            }else{
                
                if((player_field.getXValue()+diceValue)<9){
                    newField = new Field(player_field.getXValue()+diceValue,9,false,false,"");
                }else{
                    newField = new Field(9-(diceValue-(9-player_field.getXValue())),9,false,false,"");
                }
            }
        }else{
            return player_field;
        }
        if(newField.getXValue()!=0){
            return newField;
        }
        return null;
    }
    
    public Field moveLadder(Field ladderField, Ladder ladder){
        Field newField = new Field(0,0,false,false,"");
        
        if(ladder.getAdvantage().getYValue()!=0){
            newField = moveLadderAdvantage(ladderField,ladder);
        }else if(ladder.getDisAdvantage().getYValue()!=0){
            newField = moveLadderDisAdvantage(ladderField,ladder);
        }else{
            System.out.println("No ladderContent");
        }
        
        return newField;
    }
    
    public Field moveLadderAdvantage(Field ladderField, Ladder ladder){
        return new Field(ladderField.getXValue(),ladderField.getYValue()+ladder.getAdvantage().getYValue(),false,false,"");
    }
    
    public Field moveLadderDisAdvantage(Field ladderField, Ladder ladder){
        return new Field(ladderField.getXValue(),ladderField.getYValue()-ladder.getDisAdvantage().getYValue(),false,false,"");
    }
    
    private ArrayList<Ladder> placeLadders(){
        Field ladderField_1 = new Field(3,1,false,true,"");
        Field ladderField_2 = new Field(7,2,false,true,"");
        Field ladderField_3 = new Field(5,4,false,true,"");
        Field ladderField_4 = new Field(7,5,false,true,"");
        Field ladderField_5 = new Field(3,6,false,true,"");
        Field ladderField_6 = new Field(6,8,false,true,"");
        
        Field ladderAdvantageSmallLadder = new Field(0,1,false,false,"");
        
        Field ladderDisadvantageSmallLadder = new Field(0,0,false,false,"");
        
        Ladder ladder_1 = new Ladder("small_ladder",ladderAdvantageSmallLadder,ladderDisadvantageSmallLadder,ladderField_1);
        Ladder ladder_2 = new Ladder("small_ladder",ladderAdvantageSmallLadder,ladderDisadvantageSmallLadder,ladderField_2);
        Ladder ladder_3 = new Ladder("small_ladder",ladderAdvantageSmallLadder,ladderDisadvantageSmallLadder,ladderField_3);
        Ladder ladder_4 = new Ladder("small_ladder",ladderAdvantageSmallLadder,ladderDisadvantageSmallLadder,ladderField_4);
        Ladder ladder_5 = new Ladder("small_ladder",ladderAdvantageSmallLadder,ladderDisadvantageSmallLadder,ladderField_5);
        Ladder ladder_6 = new Ladder("small_ladder",ladderAdvantageSmallLadder,ladderDisadvantageSmallLadder,ladderField_6);
        
        ArrayList<Ladder> ladderList = new ArrayList<Ladder>();
        ladderList.add(ladder_1);
        ladderList.add(ladder_2);
        ladderList.add(ladder_3);
        ladderList.add(ladder_4);
        ladderList.add(ladder_5);
        ladderList.add(ladder_6);
        
        for(int ladderNr = 0;ladderNr<ladderList.size();ladderNr++){
            for(int i = 0;i<this.gameFields.size();i++){
                if(this.gameFields.get(i).getXValue()==ladderList.get(ladderNr).getLadderField().getXValue() && this.gameFields.get(i).getYValue()==ladderList.get(ladderNr).getLadderField().getYValue()){
                    this.gameFields.get(i).setHasLadder(true);
                }else{
                    continue;
                }
            }
        }
        
        return ladderList;
    }
    
    private ArrayList<Field> initFields(){
        ArrayList<Field> fields = new ArrayList<Field>();
        
        int boardSquareSize = 10;
        
        for(int fieldyNumber = 1;fieldyNumber<boardSquareSize;fieldyNumber++){
            for(int fieldxNumber = 1;fieldxNumber<boardSquareSize;fieldxNumber++){
               Field field = new Field(fieldxNumber,fieldyNumber,false,false,"");
                
               if(fieldyNumber%2==1){
                    field.setMoveDir("right");
                    
                }else{
                   field.setMoveDir("left");
                }
                fields.add(field);
            }
        }
        return fields;
    }
    
    private ArrayList<Player> initPlayers(){
        ArrayList<Player> registeredPlayers = new ArrayList<Player>();
        for(int playerNumber = 0;playerNumber<this.playerNames.size();playerNumber++){
            Player player = new Player(this.playerNames.get(playerNumber));
            player.setActiveField(new Field(1,1,false,false,""));
            player.getActiveField().setMoveDir("right");
            registeredPlayers.add(player);
        }
        return registeredPlayers;
    }
    
    public void updatePlayersMoveDir(int player){
        for(int dir = 0;dir<this.gameFields.size();dir++){
            if(this.gameFields.get(dir).getXValue()==this.players.get(player).getActiveField().getXValue() && this.gameFields.get(dir).getYValue()==this.players.get(player).getActiveField().getYValue()){
                this.players.get(player).getActiveField().setMoveDir(this.gameFields.get(dir).getMoveDir());
            }
        }
    }
    public Ladder hasLadder(Field field){
        
        for(int i = 0;i<this.ladders.size();i++){
            if(field.getXValue()==this.ladders.get(i).getLadderField().getXValue() && field.getYValue()==this.ladders.get(i).getLadderField().getYValue()){
                return this.ladders.get(i);
            }
        }
        return null;
    }
    
    public void handleLadders(int player) throws IOException{
        
        Field field = this.players.get(player).getActiveField();
        Field newField = new Field(0,0,false,false,"");
        Ladder ladder = hasLadder(field);
        
        if(ladder!=null){
            handleAllTypesOfChallenges(searchForFieldChallengeInGameBoard(this.players.get(player).getActiveField()));
            
            newField = moveLadder(field,ladder);
            this.players.get(player).setActiveField(newField);
            updatePlayersMoveDir(player);
            this.g.moveLadder(field,this.players.get(player).getName(),newField);
        }
    }
    
    public Task hasTask(Field field){
        
        for(int i = 0;i<this.tasks.size();i++){
            if(field.getXValue()==this.tasks.get(i).getTaskField().getXValue() && field.getYValue()==this.tasks.get(i).getTaskField().getYValue()){
                return this.tasks.get(i);
            }
        }
        return null;
    }
    
    public void showDialog(String msg){
        AppearWindow maink = new AppearWindow(msg);
        maink.ShowFrame(true);
        maink.whileConnected();
    }
    
    public void handleAllTypesOfChallenges(String challenge) throws IOException{
        Limitations limits = new Limitations();
        limits.mapChallengesToEvents(challenge);
    }
    
     public String searchForFieldChallengeInGameBoard(Field field){
        String tapChallenge = "";
        for (Entry<Field, String> taskIterator : this.tapChallengeMap.entrySet()) {
            if(taskIterator.getKey().getXValue()==field.getXValue() && taskIterator.getKey().getYValue()==field.getYValue()){
                tapChallenge = taskIterator.getValue();
            }
        }
        return tapChallenge;
    }
    
    public void startGame() throws IOException{
        Field newField;
        boolean isWinner = false;
        
        while(isWinner==false){
            for(int player=0;player<this.playerNames.size();player++){
                dice.ThrowDice();
                this.g.paintDice(dice.getValue());
                
                if(isCloseGoal(this.players.get(player).getActiveField())==true){
                    Field playersOldFieldBeforeGoal = this.players.get(player).getActiveField();
                    newField = calculateGoal(dice.getValue(), this.players.get(player).getActiveField());
                    this.players.get(player).setActiveField(newField);
                    this.g.moveGoal(playersOldFieldBeforeGoal,this.players.get(player).getName(),newField,this.dice.getValue());
                    
                    if(isWinner(this.players.get(player))==true){
                        isWinner = true;
                        this.players.get(player).getActiveField().printField();
                        showDialog("DU HAR TAPPET DEG TIL TOPPS, "+this.players.get(player).getName()+". DEL UT EN BONSKIE TIL EN AV DE ANDRE.");
                        break;
                    }
                }else{
                    newField = movePlayer(dice.getValue(),this.players.get(player).getActiveField());
                    this.players.get(player).setActiveField(newField);
                    updatePlayersMoveDir(player);
                    this.g.movePiece(this.players.get(player).getName(),newField);
                    
                    handleLadders(player);
                    handleAllTypesOfChallenges(searchForFieldChallengeInGameBoard(this.players.get(player).getActiveField()));
                }
                
                if(dice.getValue()==6){
                    this.dice.ThrowDice();
                    this.g.paintDice(dice.getValue());
                   
                    if(isCloseGoal(this.players.get(player).getActiveField())==true){
                        Field playersOldFieldBeforeGoal = this.players.get(player).getActiveField();
                        newField = calculateGoal(dice.getValue(), this.players.get(player).getActiveField());
                        this.players.get(player).setActiveField(newField);
                        System.out.println(this.players.get(player).getName());
                        this.players.get(player).getActiveField().printField();
                        this.g.moveGoal(playersOldFieldBeforeGoal,this.players.get(player).getName(),newField,this.dice.getValue());
                        handleAllTypesOfChallenges(searchForFieldChallengeInGameBoard(this.players.get(player).getActiveField()));
                        
                        if(isWinner(this.players.get(player))==true){
                            handleAllTypesOfChallenges(searchForFieldChallengeInGameBoard(this.players.get(player).getActiveField()));
                            isWinner = true;
                            System.out.println(this.players.get(player).getName());
                            this.players.get(player).getActiveField().printField();
                            showDialog("DU HAR TAPPET DEG TIL TOPPS, "+this.players.get(player).getName()+". DEL UT EN BONSKIE TIL EN AV DE ANDRE.");
                            
                            break;
                        }
                    }else{
                        
                        newField = movePlayer(dice.getValue(),this.players.get(player).getActiveField());
                        this.players.get(player).setActiveField(newField);
                        updatePlayersMoveDir(player);
                        this.g.movePiece(this.players.get(player).getName(),newField);
                        handleLadders(player);
                        handleAllTypesOfChallenges(searchForFieldChallengeInGameBoard(this.players.get(player).getActiveField()));
                    }
                }
            }
        }
    }
}

