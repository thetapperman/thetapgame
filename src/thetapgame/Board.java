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
    HashMap<Field, String> challengeMap = new HashMap<Field, String>();
    HashMap<Field, String> busMap = new HashMap<Field, String>();
    HashMap<Field, String> RouletteMap = new HashMap<Field, String>();
    
    
    public Board(ArrayList<String> playerNames, String gameName) throws IOException{
        this.gameName = gameName;
        this.playerNames = playerNames;
        this.dice = initDice();
        this.players = initPlayers();
        this.gameFields = initFields();
        this.ladders = placeLadders();
        this.tasks = placeTasks(); //DENNE FUNKER IKKE ENDA!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        this.busMap = placeBusesRandomly();
        this.RouletteMap = placeRoulettesRandomly();
        this.g = new GameBoard(players.size(),this.playerNames);
        g.CreateBoard();
        g.setPiecesStart();
        g.paintChallenges(this.challengeMap);
        g.paintBuses(this.busMap);
        g.paintRoulettes(RouletteMap);
        setHasTasksInit();
        setBusesOnBusGameFields();
        setRoulettesOnRouletteFields();
        startGame();
    }
    
    public Dice initDice(){
        return new Dice();
    }
    
    private ArrayList<Task> chooseChallengeFieldsRandomly(ArrayList<String> challenges){
        int amountOfChallenges = 30;
        
        for(int i=0;i<amountOfChallenges;i++){
            Random randomizer = new Random();
            Field randField = new Field(randomizer.nextInt(9) + 1,randomizer.nextInt(9) + 1,false,false,false,false);
            String randChallenge = challenges.get(randomizer.nextInt(challenges.size())+0);
            
            if(!this.challengeMap.containsKey(randField) && !this.challengeMap.containsValue(randChallenge)){
                this.challengeMap.put(randField, randChallenge);
            }else{
                System.out.println("Challenge already exists. ");
            }   
        }
       
        ArrayList<Task> tasks = new ArrayList<Task>();
      
        for (Entry<Field, String> taskIterator : this.challengeMap.entrySet()) {
            Task task = new Task(taskIterator.getValue(),10,0,taskIterator.getKey());
            tasks.add(task);
        }
        
        return tasks;
        
    }
    
    private HashMap<Field, String> placeRoulettesRandomly(){
        HashMap<Field, String> roulette = new HashMap<Field, String>();
        
        boolean finishedPlacingRoulettes = false;
        int roulettesPlaced = 0;
        System.out.println("handling roulettes");
        
        while (!finishedPlacingRoulettes){
            Random randomizer = new Random();
            int xValue = randomizer.nextInt(9) + 1;
            int yValue = randomizer.nextInt(9) + 1;
            Field randField = new Field(xValue,yValue,false,false,false,false);
            boolean availableField = true;
            
            
            for (Entry<Field, String> taskIterator : this.challengeMap.entrySet()) {
                if(taskIterator.getKey().getXValue()==xValue && taskIterator.getKey().getYValue()==yValue){
                    availableField = false;
                }
            }
            
            for (Entry<Field, String> taskIterator : this.busMap.entrySet()) {
                if(taskIterator.getKey().getXValue()==xValue && taskIterator.getKey().getYValue()==yValue){
                    availableField = false;
                }
            }
            
            if(availableField){
                roulette.put(randField, "roulette.gif");
                roulettesPlaced++;
            }
            
            if(roulettesPlaced == 5){
                finishedPlacingRoulettes = true;
                System.out.println("there are roulettes");
            }
        }
        
        return roulette;
        
    }
    
    public void handleRoulette(int player) throws IOException{
        Field playerfield = this.players.get(player).getActiveField();
        for(int i = 0;i<this.gameFields.size();i++){
            if(this.gameFields.get(i).getXValue()==playerfield.getXValue() && this.gameFields.get(i).getYValue()==playerfield.getYValue() && this.gameFields.get(i).getHasRoulette()==true){
                RussianRoulette roulette = new RussianRoulette();
            }
        }
        
    
    }
    
    private HashMap<Field, String> placeBusesRandomly(){ 
        HashMap<Field, String> bus = new HashMap<Field, String>();
        
        boolean finishedPlacingBuses = false;
        int busPlaced = 0;
        System.out.println("handling buses");
        while (!finishedPlacingBuses){
            Random randomizer = new Random();
            int xValue = randomizer.nextInt(9) + 1;
            int yValue = randomizer.nextInt(9) + 1;
            Field randField = new Field(xValue,yValue,false,false,false,false);
            boolean availableField = true;
            
            
            for (Entry<Field, String> taskIterator : this.challengeMap.entrySet()) {
                if(taskIterator.getKey().getXValue()==xValue && taskIterator.getKey().getYValue()==yValue){
                    availableField = false;
                }
            }
            
            if(availableField){
                bus.put(randField, "bus.gif");
                busPlaced++;
            }
            
            if(busPlaced == 10){
                finishedPlacingBuses = true;
                System.out.println("there are buses");
            }
        }
        
        return bus;
    }
    
    private ArrayList<Task> placeTasks(){
        
        ArrayList<Task> tasks = new ArrayList<Task>();
        
        String challenge_1 = "Tre slurker til den som ikke har tappet på over en mnd. ";
        String challenge_2 = "Hvis det eksisterer en jomfru i salen: Venligst CHUG snarest.";
        String challenge_3 = "Personen med minst matches på Tinder: Drikk 4 slurker. Den med flest matches: Gi bort 4 slurker. De i forhold er fritatt.";
        String challenge_4="Alle som IKKE har tappet svenskt må drikke 2 slurker.";
        String challenge_5="OPPKJØRING. Den med færrest alkoholenheter innabords må CHUGGE. Vil vedkommende få lappen? ->Ta å kjør deg opp.";
        String challenge_6="GENTLEMAN BONUS deles ut til personene som har tappet innen en uke. Denne bonusen innebærer at personene som fikk den når som helst kan kalle inn til årsmøtet. Dersom personen kaller inn til årsmøte må de personene som ikke har tappet innen en uke fjerne seg fra rommet i 10 sekunder. Deltakerne i årsmøtet skal så bli enige om personen som må ta 10 slurker. Dersom de ikke kommer til enighet, er det opp til personsn som ble tildelt gentleman-bonusen å avgjøre. ";
        String challenge_7="De som aldri har hustlet i utlandet: 10 pushups.";
        String challenge_8="5 slurker til personen med MINST erfaring på sjekkern. Dette skal fellesskapet bli enige om. ";
        String challenge_9="RUTINEKONTROLL. Når tappet du sist? Dersom du tappet for mindre enn et døgn siden slipper du bot. Et forelegg på 5 slurker vil bli tildelt dersom vedkommende ikke har tappet det siste døgnet.";
        String challenge_10="Drink 10 zips";
        String challenge_11="RIM. Vedkommende som havnet på dette feltet starter. Retning: Med klokken. Rim på et ord som har med tapping å gjøre.";
        String challenge_12="DU BLE TRUFFET AV ET CUMSHOT. Drikk 3 slurker.";
        String challenge_13="INSPEKSJON. Har du tappet flere enn 10? Hvis ikke, drikk 5 slurker.";
        String challenge_14="Personene som er i et forhold må drikke 3 slurker. De som har vært i et forhold i mer enn 2 år må drikke 5 slurker.";
        String challenge_15="FOKUS! Du er nå nødt til å servere TIDENES sjekkereplikk til salen. Dersom salen mener den er for tam(at den ikke ville resultert i tappings), drikk 5 slurker.";
        String challenge_16="FARTSKONTROLL. Du har tappet raskere enn tillatt hastighet. Drikk 4 slurker. ";
        String challenge_17="BLAZERBONUS. Har du på deg blazer: Del ut 5 slurker. Mangler du derimot blazer: Drikk 5 slurker. Dersom du kompenserer nypussede sko: Del ut 3 slurker.";
        String challenge_18="KRISEMØTE!!!! Det er blitt tappet for lite. Alle må drikke 3 slurker. ";
        String challenge_19="Kategori! Retning: Med klokken. Si en kategori i forbindelse med tapping(f.eks kondom-brands). Neste person i rekken har så to sekunder på å nevne et ord tilsvarende kategorien. Failer denne personen må han drikke 5 slurker.";
        String challenge_20 = "KNALLHARD doggy er observert på gutterommet. Del ut 5 slurker.";
        String challenge_21 = "Beertime for blondes. All players having blonde hair-> Drink 5 zips.";
        String challenge_22 = "ALLAHU AKBAR!!!!! Players go around the table and name off as many gun brands as they can until one player repeats a brand or fails. that player needs to drink an amount of zips decided by Kraugerud. ";
        String challenge_23 = "Going clockwise each player names a Pokemon. The player which repeats or cannot think of any Pokemon has to finish his/her drink.";
        String challenge_24 = "If there is any player around the table named Vigdis, Børre, Raymond or Ruth: finish your drinks.";
        String challenge_25 = "Triana-crush!!!! If you would have had sex with Triana from Paradise hotel choose another player to finish his/her drink.";
        String challenge_26 = "All players turn the wifi on and leave the phone on the table. Every time a person recieves a snapchat, that person must drink 5 zips";
        String challenge_27 = "GOLDDIGGER. All male players count how much money they have on them. The poor looser with the least amount of cash must finish his drink, then refill it and finish it again.";
        String challenge_28 = "All players having a beard with a length above 1cm have to finish their drink.";
        String challenge_29 = "Every player must perform a silly walk at the same time. The person drawing this card has to Snapchat it and put it on his/her MyStory. ";
        String challenge_30 = "SHITSTORM IS COMING. You have to say one degrading thing about all players around the table. If that person replies by saying fuck you, you need to drink 5 zips";
        String challenge_31 = "THE SALESMAN. If Erdalen is in the room, he has to finish his drink. If not, the drawer of this card needs to call him and ask him to choose a player in the room. That player must finish his/her drink.";
        String challenge_32 = "Snap, clap and point your fingers at the person sitting across you. You must both chug. Loser drinks another.";
        String challenge_33 = "If you have ever solved a rubics cube, DRINK!! 5 zips should do.";
        String challenge_34 = "All players that have Pokemon Go on their phone, finish your drink.";
        String challenge_35 = "All players wearing green must drink 3 zips. ";
        String challenge_36 = "Any time this player drinks when not ordered by a card, he/she has to finish the drink";
        String challenge_37 = "Dicaprio!!! going clockwise, mention a movie with Leonardo Dicaprio until a player fails. that player must take 3 zips.";
        String challenge_38 = "Players that have ever puked from drinking must drink 3 zips. ";
        String challenge_39 = "Next time you swear you have to chug. ";
        String challenge_40 = "If you have had sex with anbody around the table, drink 5 zips and kiss that person.";
        
        ArrayList<String> challenges = new ArrayList<String>();
        
        challenges.add(challenge_1);
        challenges.add(challenge_2);
        challenges.add(challenge_3);
        challenges.add(challenge_4);
        challenges.add(challenge_5);
        challenges.add(challenge_6);
        challenges.add(challenge_7);
        challenges.add(challenge_8);
        challenges.add(challenge_9);
        challenges.add(challenge_10);
        challenges.add(challenge_11);
        challenges.add(challenge_12);
        challenges.add(challenge_13);
        challenges.add(challenge_14);
        challenges.add(challenge_15);
        challenges.add(challenge_16);
        challenges.add(challenge_17);
        challenges.add(challenge_18);
        challenges.add(challenge_19);
        challenges.add(challenge_20);
        challenges.add(challenge_21);
        challenges.add(challenge_22);
        challenges.add(challenge_23);
        challenges.add(challenge_24);
        challenges.add(challenge_25);
        challenges.add(challenge_26);
        challenges.add(challenge_27);
        challenges.add(challenge_28);
        challenges.add(challenge_29);
        challenges.add(challenge_30);
        challenges.add(challenge_31);
        challenges.add(challenge_32);
        challenges.add(challenge_33);
        challenges.add(challenge_34);
        challenges.add(challenge_35);
        challenges.add(challenge_36);
        challenges.add(challenge_37);
        challenges.add(challenge_38);
        challenges.add(challenge_39);
        challenges.add(challenge_40);
        
        ArrayList<Task> boardTasks =  chooseChallengeFieldsRandomly(challenges);
        
        return boardTasks;
    }
    
    public void setRoulettesOnRouletteFields(){
        for (Entry<Field, String> taskIterator : this.RouletteMap.entrySet()) {
            for(int i  = 0;i<this.gameFields.size();i++){
                if(this.gameFields.get(i).getXValue()==taskIterator.getKey().getXValue() && this.gameFields.get(i).getYValue()==taskIterator.getKey().getYValue()){
                    this.gameFields.get(i).setHasRoulette(true);
                }
            }
        }
    }
    
    public void setBusesOnBusGameFields(){
        for (Entry<Field, String> taskIterator : this.busMap.entrySet()) {
            for(int i  = 0;i<this.gameFields.size();i++){
                if(this.gameFields.get(i).getXValue()==taskIterator.getKey().getXValue() && this.gameFields.get(i).getYValue()==taskIterator.getKey().getYValue()){
                    this.gameFields.get(i).setHasBus(true);
                }
            }
        }
    }
    
    public void setHasTasksInit(){
        for(int taskNr = 0;taskNr<this.tasks.size();taskNr++){
            for(int fieldNr = 0;fieldNr<this.gameFields.size();fieldNr++){
                if(this.gameFields.get(fieldNr).getXValue()==this.tasks.get(taskNr).getTaskField().getXValue() && this.gameFields.get(fieldNr).getYValue()==this.tasks.get(taskNr).getTaskField().getYValue()){
                    this.gameFields.get(fieldNr).setHasTask(true);
                }
            }
        }
    }
    
    private Field MoveRightAdvantage(Field field_player,Task task){
        Field newField = new Field(0,0,false,false,false,false);
        //System.out.println("___________dir: Right___________");
        
        if((9-field_player.getXValue())<task.getAdvantage()){
                        
            if(field_player.getXValue()==9){
                newField = new Field(9-task.getAdvantage()+1,field_player.getYValue()+1,false,false,false,false);
            }else{
                            
                if(task.getAdvantage()-(9-field_player.getXValue())==1){
                    newField = new Field(9,field_player.getYValue()+1,false,false,false,false);
                }else{     
                    int fieldsToMove = task.getAdvantage()-(9-field_player.getXValue());
                    newField = new Field(9-fieldsToMove+1,field_player.getYValue()+1,false,false,false,false);
                    }
                }
                   
        }else{
            newField = new Field(field_player.getXValue()+task.getAdvantage(),field_player.getYValue(),false,false,false,false);
            }
        return newField;
    }
    
    private Field MoveLeftAdvantage(Field field_player,Task task){
        Field newField = new Field(0,0,false,false,false,false);
        //System.out.println("___________Dir: left___________");
                    
        if(task.getAdvantage()>(field_player.getXValue()-1)){
            if(field_player.getXValue()==1){
                newField = new Field(task.getAdvantage(),field_player.getYValue()+1,false,false,false,false);
            }else{
                newField = new Field(task.getAdvantage()-(field_player.getXValue()-1),field_player.getYValue()+1,false,false,false,false);
            }  
        }else{
            newField = new Field(field_player.getXValue()-task.getAdvantage(),field_player.getYValue(),false,false,false,false);
        }
                    
        return newField;
    }
    
    private Field MoveBackToStart(){
        return new Field(1,1,false,false,false,false);
    }
    
    private Field MoveRightDisAdvantage(Field field_player,Task task){
        Field newField = new Field(0,0,false,false,false,false);
        
        if(task.getPunishment()>(field_player.getXValue()-1)){  
            
            if(task.getPunishment() == 1 && field_player.getXValue()==1){
                newField = new Field(1,field_player.getYValue()-1,false,false,false,false);
            }else{
                newField = new Field(task.getPunishment() - (field_player.getXValue()-1),field_player.getYValue()-1,false,false,false,false);
            }
        }else{
            newField = new Field(field_player.getXValue()-task.getPunishment(),field_player.getYValue(),false,false,false,false);
        }
        return newField;
    }
    
     private Field MoveLeftDisAdvantage(Field field_player,Task task){
        Field newField = new Field(0,0,false,false,false,false);
        
        if((9-field_player.getXValue())<task.getPunishment()){
            
            if(task.getPunishment()==1 && field_player.getXValue()==9){
                newField = new Field(9,field_player.getYValue()-1,false,false,false,false);
            }else{
            newField = new Field(9-(task.getPunishment()-(9-field_player.getXValue()))+1,field_player.getYValue()-1,false,false,false,false);
            }      
        }else{
            newField = new Field(field_player.getXValue()+task.getPunishment(),field_player.getYValue(),false,false,false,false);
        }
        
        return newField;
    }
    
    public Field SubtractFieldsInTask(Field field_player,Task task){
        Field newField = new Field(0,0,false,false,false,false);
        
        if(task.getAdvantage()!=0){
            System.out.println("___________Advantage discovered___________");
            
            if(field_player.getMoveDir()=="right"){
                newField = MoveRightAdvantage(field_player,task);
                    
            }else if(field_player.getMoveDir()=="left"){
                newField = MoveLeftAdvantage(field_player,task);
            }
               
        }else if(task.getPunishment()!=0){
            if(task.getAdvantage()==-1){
                newField = MoveBackToStart();
            }else{
                if(field_player.getMoveDir()=="right"){
                    
                    newField = MoveRightDisAdvantage(field_player,task);
                    
                }else if(field_player.getMoveDir()=="left"){
                    newField = MoveLeftDisAdvantage(field_player,task);
                } 
            }
        }
        
        return newField;
    }
    
    public Field movePlayer(int diceValue,Field field_player){ 
        
        Field newField = new Field(0,0,false,false,false,false);

        if(field_player.getMoveDir()=="right"){
            newField = MoveRightAdvantage(field_player,new Task("",diceValue,0,new Field(1,2,true,false,false,false)));
        }else if(field_player.getMoveDir()=="left"){
            newField = MoveLeftAdvantage(field_player,new Task("",diceValue,0,new Field(1,2,true,false,false,false)));
        }else if(field_player.getMoveDir()==null){
            //System.out.println("No direction yields for player's field");
            //System.out.println("Player's field dir: "+field_player.getMoveDir());
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
        Field newField = new Field(0,0,false,false,false,false);
        if(isCloseGoal(player_field)){
            System.out.println("______Calculating goal______is close goal_________");
            if((player_field.getXValue()+diceValue)==9){
                newField = new Field(9,9,false,false,false,false);
            }else{
                
                if((player_field.getXValue()+diceValue)<9){
                    newField = new Field(player_field.getXValue()+diceValue,9,false,false,false,false);
                }else{
                    newField = new Field(9-(diceValue-(9-player_field.getXValue())),9,false,false,false,false);
                }
            }
        }else{
            System.out.println("______Calculating goal______not close goal_________");
            return player_field;
        }
        if(newField.getXValue()!=0){
            return newField;
        }
        //return newField;
        return null;
    }
    
    public Field moveLadder(Field ladderField, Ladder ladder){
        Field newField = new Field(0,0,false,false,false,false);
        
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
        return new Field(ladderField.getXValue(),ladderField.getYValue()+ladder.getAdvantage().getYValue(),false,false,false,false);
    }
    
    public Field moveLadderDisAdvantage(Field ladderField, Ladder ladder){
        return new Field(ladderField.getXValue(),ladderField.getYValue()-ladder.getDisAdvantage().getYValue(),false,false,false,false);
    }
    
    private ArrayList<Ladder> placeLadders(){
        //System.out.println("PLACING LADDER!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!0");
        Field ladderField_1 = new Field(3,1,false,true,false,false);
        Field ladderField_2 = new Field(7,2,false,true,false,false);
        Field ladderField_3 = new Field(5,4,false,true,false,false);
        Field ladderField_4 = new Field(7,5,false,true,false,false);
        Field ladderField_5 = new Field(3,6,false,true,false,false);
        Field ladderField_6 = new Field(6,8,false,true,false,false);
        
        Field ladderAdvantageSmallLadder = new Field(0,1,false,false,false,false);
        
        Field ladderDisadvantageSmallLadder = new Field(0,0,false,false,false,false);
        
        Ladder ladder_1 = new Ladder("small_ladder",ladderAdvantageSmallLadder,ladderDisadvantageSmallLadder,ladderField_1);
        Ladder ladder_2 = new Ladder("small_ladder",ladderAdvantageSmallLadder,ladderDisadvantageSmallLadder,ladderField_2);
        Ladder ladder_3 = new Ladder("small_ladder",ladderAdvantageSmallLadder,ladderDisadvantageSmallLadder,ladderField_3);
        Ladder ladder_4 = new Ladder("small_ladder",ladderAdvantageSmallLadder,ladderDisadvantageSmallLadder,ladderField_4);
        Ladder ladder_5 = new Ladder("small_ladder",ladderAdvantageSmallLadder,ladderDisadvantageSmallLadder,ladderField_5);
        Ladder ladder_6 = new Ladder("small_ladder",ladderAdvantageSmallLadder,ladderDisadvantageSmallLadder,ladderField_6);
        //Ladder ladder_2 = new Ladder("medium_ladder",ladderAdvantageMediumLadder,ladderDisadvantageMediumLadder,ladderField_2);
        //Ladder ladder_3 = new Ladder("big_ladder",ladderAdvantageLargeLadder,ladderDisadvantageLargeLadder,ladderField_3);
        
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
               Field field = new Field(fieldxNumber,fieldyNumber,false,false,false,false);
                
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
           /*Console console = System.console();

           Scanner sc = new Scanner(System.in);
           
           System.out.println("Welcome to the game, "+this.playerNames[playerNumber]+". LETS START:");
           System.out.println("");
            
            System.out.println("Enter number of facials last week: ");
            int facialsLastWeek = sc.nextInt();;
            
            System.out.println("Enter number of matches on Tinder: ");
            int tinderMatchNumber = sc.nextInt();
            
            System.out.println("Please register number of taps: ");
            int tappedPeople = sc.nextInt();
            
            ArrayList<Ping> pings = new ArrayList<Ping>();
            
            if(tappedPeople>0){
                System.out.println("WELCOME TO REGISTRATION OF TAPPED BITCHES");
                System.out.println("All BITCHES tapped have to be registered!!!!!!!!");
                System.out.println("Please register bitches!");
                System.out.println("");
                System.out.println("");
                
            }
            
            for(int ping_number = 0;ping_number<tappedPeople;ping_number++){
                System.out.println("______________Welcome to tap-registration for "+this.playerNames[playerNumber]+"_____________");
                
                System.out.println("Enter pingName: ");
                String pingName = sc.next();
                
                System.out.println("Enter beautyPoints from 1 to 10: ");
                int beautyPointsNumber = sc.nextInt();
                
                System.out.println("brahSize: ");
                String brahSize = sc.next();
                
                System.out.println("Enter weight: ");
                double weight = (double)sc.nextInt();
                
                System.out.println("Enter height: ");
                double height = (double)sc.nextInt();
                
                System.out.println("Enter hairColor: ");
                String hairColor = sc.next();
                
                System.out.println("Enter country of origin: ");
                String country = sc.next();
                
                Ping ping = new Ping(pingName,beautyPointsNumber,brahSize,weight,height,hairColor,country);
                pings.add(ping);
                }
            */
            //Player player = new Player(playerNames[playerNumber],tappedPeople,facialsLastWeek,tinderMatchNumber,pings);
            Player player = new Player(this.playerNames.get(playerNumber));
            player.setActiveField(new Field(1,1,false,false,false,false));
            player.getActiveField().setMoveDir("right");
            registeredPlayers.add(player);
            //System.out.println("");
            //System.out.println("");
            System.out.println("");
        }
        return registeredPlayers;
    }
    
    public void printBoard(){
        for(int player_nr = 0;player_nr<this.players.size();player_nr++){
            this.players.get(player_nr).printPlayer();
        }
        
        for(int fieldNr = 0;fieldNr<this.gameFields.size();fieldNr++){
            this.gameFields.get(fieldNr).printField();
        }
        
        
    }
    
    public void updatePlayersMoveDir(int player){
        //System.out.println("___________UpdateMoveDir???_________");
        for(int dir = 0;dir<this.gameFields.size();dir++){
            if(this.gameFields.get(dir).getXValue()==this.players.get(player).getActiveField().getXValue() && this.gameFields.get(dir).getYValue()==this.players.get(player).getActiveField().getYValue()){
                //System.out.println("___________I AM INSIDE. UPDATING MOVE_DIR_________");
                this.players.get(player).getActiveField().setMoveDir(this.gameFields.get(dir).getMoveDir());
                //this.players.get(player).getActiveField().setMoveDir(moveDir);
                System.out.println("");
                //System.out.println("Name: "+this.players.get(player).getName());
                //System.out.println("Updating to direction: "+this.gameFields.get(dir).getMoveDir());
                //System.out.println("Players y-coordinate: "+this.players.get(player).getActiveField().getYValue());
                //System.out.println("Players x-coordinate: "+this.players.get(player).getActiveField().getXValue());
                //System.out.println("Dice value: "+this.dice.getValue());
                System.out.println("");
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
        Field newField = new Field(0,0,false,false,false,false);
        Ladder ladder = hasLadder(field);
        
        if(ladder!=null){
            //System.out.println("_______________________________LADDER ALARM__________________________");
            //System.out.println("Player: ");
            //this.players.get(player).printPlayer();
            
            if(field.getHasBus()){
                Busroute route = new Busroute();
            }else if(field.getHasRoulette()){
                RussianRoulette roulette = new RussianRoulette();
            }
            
            newField = moveLadder(field,ladder);
            this.players.get(player).setActiveField(newField);
            updatePlayersMoveDir(player);
            this.g.moveLadder(field,this.players.get(player).getName(),newField);
            
            /*System.out.println("");
            ladder.printLadder();
            System.out.println("Moving from: ");
            field.printField();
            System.out.println("Moving to: ");
            newField.printField();
            System.out.println("___________________LADDER_END________________________");
            System.out.println("");*/
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
    
    private void handleBus(int player) throws IOException{
        Field playerfield = this.players.get(player).getActiveField();
        for(int i = 0;i<this.gameFields.size();i++){
            if(this.gameFields.get(i).getXValue()==playerfield.getXValue() && this.gameFields.get(i).getYValue()==playerfield.getYValue() && this.gameFields.get(i).getHasBus()==true){
                Busroute route = new Busroute();
            }
        }
        
    }
    public void handleTasks(int player){
        Task task = hasTask(this.players.get(player).getActiveField());
        if(task!=null){
            //System.out.println("");
            //System.out.println("");
            //System.out.println("_____________________TASK START____________________");
            
            task.printTask();
            AppearWindow maink = new AppearWindow(task.getTask());
            maink.ShowFrame(true);
            maink.whileConnected();
            //System.out.println("_____________________TASK END____________________");
            //System.out.println("");
            //System.out.println("");
        }
    }
    
    public void startGame() throws IOException{
        Field newField;// = new Field(0,0,false,false);
        boolean isWinner = false;
        
        while(isWinner==false){
            for(int player=0;player<this.playerNames.size();player++){
                dice.ThrowDice();
                System.out.println("______________________________________________");
                System.out.println("____________________DICE__________________________");
                System.out.println("dice:::: "+this.dice.getValue());
                System.out.println("______________________________________________");
                System.out.println("______________________________________________");
                this.g.paintDice(dice.getValue());
                
                if(isCloseGoal(this.players.get(player).getActiveField())==true){
                    Field playersOldFieldBeforeGoal = this.players.get(player).getActiveField();
                    newField = calculateGoal(dice.getValue(), this.players.get(player).getActiveField());
                    this.players.get(player).setActiveField(newField);
                    this.g.moveGoal(playersOldFieldBeforeGoal,this.players.get(player).getName(),newField,this.dice.getValue());
                    
                    //System.out.println(this.players.get(player).getName());
                    //this.players.get(player).getActiveField().printField();
                    //System.out.println("dice: "+this.dice.getValue());
                    
                    if(isWinner(this.players.get(player))==true){
                        isWinner = true;
                        System.out.println("_______________field for winning:__________________");
                        
                        System.out.println(this.players.get(player).getName());
                        this.players.get(player).getActiveField().printField();
                        System.out.println("dice: "+this.dice.getValue());
                        String winnerString = "DU HAR TAPPET DEG TIL TOPPS, "+this.players.get(player).getName()+". DEL UT EN BONSKIE TIL EN AV DE ANDRE.";
                        AppearWindow maink = new AppearWindow(winnerString);
                        maink.ShowFrame(true);
                        maink.whileConnected();
                        break;
                    }
                }else{
                    newField = movePlayer(dice.getValue(),this.players.get(player).getActiveField());
                    this.players.get(player).setActiveField(newField);
                    updatePlayersMoveDir(player);
                    this.g.movePiece(this.players.get(player).getName(),newField);
                    
                    //Check Ladder
                    handleLadders(player);
                    handleTasks(player);
                    handleBus(player);
                    handleRoulette(player);
                    /*if(hasLadder(this.players.get(player).getActiveField())!=null){
                        this.players.get(player).setActiveField(moveLadderAdvantage(this.players.get(player).getActiveField(), ));
                    }*/
                    //System.out.println(this.players.get(player).getName());
                    //this.players.get(player).getActiveField().printField();
                    //System.out.println("dice: "+this.dice.getValue());
                }
                
                
                //sjekk om det er task/stige på feltet.IKKE GJORT ENDA
                /*
                for(int i=0;i<this.gameFields.size();i++){
                    if(this.gameFields.get(i).getHasTask()==true && this.gameFields.get(i).getXValue()==this.players.get(player).getActiveField().getXValue() && this.gameFields.get(i).getYValue()==this.players.get(player).getActiveField().getYValue()){
                        System.out.println("Field has task"+this.gameFields.get(i).getHasTask());
                        for(int task_nr=0;task_nr<this.tasks.size();task_nr++){
                            if(this.tasks.get(task_nr).getTaskField().getXValue()==this.gameFields.get(i).getXValue() && this.tasks.get(task_nr).getTaskField().getYValue()==this.gameFields.get(i).getYValue()){
                                //updatePlayersMoveDir(player);
                                newField = SubtractFieldsInTask(this.players.get(player).getActiveField(),this.tasks.get(task_nr));
                                this.players.get(player).setActiveField(newField);
                            }
                        }
                        
                    }else if(true==false){
                        //SJEKK STIGER
                    }
                    System.out.println("Locked while checking tasks");
                }*/
                
                if(dice.getValue()==6){
                    this.dice.ThrowDice();
                    this.g.paintDice(dice.getValue());
                    System.out.println("______________________________________________");
                    System.out.println("____________________DICE__________________________");
                    System.out.println("dice:::: "+this.dice.getValue());
                    System.out.println("______________________________________________");
                    System.out.println("______________________________________________");
                   
                    if(isCloseGoal(this.players.get(player).getActiveField())==true){
                        Field playersOldFieldBeforeGoal = this.players.get(player).getActiveField();
                        newField = calculateGoal(dice.getValue(), this.players.get(player).getActiveField());
                        this.players.get(player).setActiveField(newField);
                        System.out.println(this.players.get(player).getName());
                        this.players.get(player).getActiveField().printField();
                        this.g.moveGoal(playersOldFieldBeforeGoal,this.players.get(player).getName(),newField,this.dice.getValue());
                        
                        System.out.println("dice: "+this.dice.getValue());
                        if(isWinner(this.players.get(player))==true){
                            isWinner = true;
                            System.out.println(this.players.get(player).getName());
                            this.players.get(player).getActiveField().printField();
                            System.out.println("dice: "+this.dice.getValue());
                            String winnerString = "DU HAR TAPPET DEG TIL TOPPS, "+this.players.get(player).getName()+". DEL UT EN BONSKIE TIL EN AV DE ANDRE.";
                            AppearWindow maink = new AppearWindow(winnerString);
                            maink.ShowFrame(true);
                            maink.whileConnected();
                            break;
                        }
                    }else{
                        
                        newField = movePlayer(dice.getValue(),this.players.get(player).getActiveField());
                        this.players.get(player).setActiveField(newField);
                        updatePlayersMoveDir(player);
                        this.g.movePiece(this.players.get(player).getName(),newField);
                        handleLadders(player);
                        handleTasks(player);
                        handleBus(player);
                        handleRoulette(player);
                        //System.out.println(this.players.get(player).getName());
                        //this.players.get(player).getActiveField().printField();
                        //System.out.println("dice: "+this.dice.getValue());
                    }
                }
            }
        }
    }
}

