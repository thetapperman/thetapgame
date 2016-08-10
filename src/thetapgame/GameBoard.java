/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thetapgame;

import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRootPane;

/**
 *
 * @author Kraugen
 */
public class GameBoard {
    
    private JFrame f;
    private ImageIcon img;
    private ArrayList<Brick> bricks;
    private String[] brickFileNames;
    private int players;
    private ArrayList<String> playerNames;
    private HashMap <ArrayList<Integer>, ArrayList<Integer>> coordinateHashMap;
    
    public GameBoard(int players,ArrayList<String> playerNames) throws IOException{
        this.f = new JFrame();
        this.coordinateHashMap = new HashMap<ArrayList<Integer>, ArrayList<Integer>>();
        initCoordinatesHashMap();
        this.players = players;
        this.playerNames = playerNames;
        this.img = new ImageIcon(ImageIO.read(new File("ladder_tap.jpg")));
        this.bricks = new ArrayList<Brick>();
        this.brickFileNames = new String[5];
        this.brickFileNames = new String[]{"brikke_ass.gif","brikke_blaa.gif","brikke_ol.gif","brikke_dildo.gif","brikke_kondom.gif"};
        this.bricks=initPieces();
    }
    
    public void paintDice(int diceNum){
        ArrayList<Integer> co = new ArrayList<Integer>();
        co.add(5);
        co.add(5);
        
        String imagePath = Integer.toString(diceNum)+".jpg";
        JLabel lbl = new JLabel(new ImageIcon(imagePath));
        
        lbl.setBounds(this.coordinateHashMap.get(co).get(0)+540, this.coordinateHashMap.get(co).get(1)+30, this.img.getIconWidth(), this.img.getIconHeight());
        this.f.add(lbl);
        lbl.setVisible(true);
        this.f.repaint();
        
        sleepProgram(2);
        
        lbl.setVisible(false);
        this.f.repaint();
    }
    
    public void paintTapChallengeLogosOnGameBoard(HashMap<Field, String> tapChallenges){
        String busFile = "bus.gif";
        String wheelFile = "wheel_logo.gif";
        String rouletteFile = "dood.gif";
        String horseFile = "horse_logo.gif";
        String starFile = "star_challenge.gif";
        
        for (Map.Entry<Field, String> taskIterator : tapChallenges.entrySet()) {
            ArrayList<Integer> coordinateToLookUp = new ArrayList<Integer>();
            coordinateToLookUp.add(taskIterator.getKey().getXValue());
            coordinateToLookUp.add(taskIterator.getKey().getYValue());
            
            if(taskIterator.getValue().equals("bus")){
                JLabel lbl = new JLabel(new ImageIcon(busFile));
                lbl.setBounds(this.coordinateHashMap.get(coordinateToLookUp).get(0)-20, this.coordinateHashMap.get(coordinateToLookUp).get(1)-20, this.img.getIconWidth(), this.img.getIconHeight());
                this.f.add(lbl);
            }else if(taskIterator.getValue().equals("wheel")){
                JLabel lbl = new JLabel(new ImageIcon(wheelFile));
                lbl.setBounds(this.coordinateHashMap.get(coordinateToLookUp).get(0), this.coordinateHashMap.get(coordinateToLookUp).get(1), this.img.getIconWidth(), this.img.getIconHeight());
                this.f.add(lbl);
            }else if(taskIterator.getValue().equals("horse")){
                JLabel lbl = new JLabel(new ImageIcon(horseFile));
                lbl.setBounds(this.coordinateHashMap.get(coordinateToLookUp).get(0), this.coordinateHashMap.get(coordinateToLookUp).get(1), this.img.getIconWidth(), this.img.getIconHeight());
                this.f.add(lbl);
            }else if(taskIterator.getValue().equals("roulette")){
                JLabel lbl = new JLabel(new ImageIcon(rouletteFile));
                lbl.setBounds(this.coordinateHashMap.get(coordinateToLookUp).get(0), this.coordinateHashMap.get(coordinateToLookUp).get(1), this.img.getIconWidth(), this.img.getIconHeight());
                this.f.add(lbl);
            }else if(taskIterator.getValue().equals("text")){
                JLabel lbl = new JLabel(new ImageIcon(starFile));
                lbl.setBounds(this.coordinateHashMap.get(coordinateToLookUp).get(0)-20, this.coordinateHashMap.get(coordinateToLookUp).get(1)-20, this.img.getIconWidth(), this.img.getIconHeight());
                this.f.add(lbl);
            }
        }
    }
    
    public void initCoordinatesHashMap(){ 
        HashMap h = new HashMap();
        int startX = -523;
        int offsetX = 73;
        int startY = 285;
        int offsetY = 71;
        
        for(int y = 1;y<10;y++){
            for(int x = 1;x<10;x++){
                ArrayList<Integer> co= new ArrayList<Integer>();
                co.add(startX+offsetX*(x-1));
                co.add(startY-offsetY*(y-1));
                ArrayList<Integer> fi= new ArrayList<Integer>();
                fi.add(x);
                fi.add(y);
                h.put(fi,co);
            }
        }
        
        this.coordinateHashMap = h;
    }
    
    public void CreateBoard() {
        this.f.setContentPane(new JLabel(this.img));
    	this.f.pack();
        this.f.setLayout(null);
        this.f.setResizable(false);
        this.f.setDefaultCloseOperation(0);
        this.f.setVisible(true);
    }
    
    public ArrayList<Brick> getBricks(){
        return this.bricks;
    }
    
    public void setPieceOnField(ArrayList<Integer> c,String player){
        JLabel l = new JLabel();
        for(int i = 0;i<this.bricks.size();i++){
            if(this.bricks.get(i).getBelongsToPlayer().equals(player)){
                l = this.bricks.get(i).getBrick();
            }
        }
        
        l.setBounds(this.coordinateHashMap.get(c).get(0), this.coordinateHashMap.get(c).get(1), this.img.getIconWidth(), this.img.getIconHeight());
        this.f.add(l);
        l.setVisible(true);
        this.f.repaint();
    }
    
    public void movePiece(String player,Field toField){
        
        Field playersField = new Field(0,0,false,false,"");
        JLabel l = new JLabel();
        int brickNr =  -1;
        
        for(int i = 0;i<this.bricks.size();i++){
            if(this.bricks.get(i).getBelongsToPlayer().equals(player)){
                l = this.bricks.get(i).getBrick();
                playersField = this.bricks.get(i).getOnField();
                brickNr = i;
            }
        }
        
        if(toField.getYValue()==playersField.getYValue()){
            moveSameRow(playersField,brickNr,toField,l);
            this.bricks.get(brickNr).setOnField(toField);
            
            //UPDATE MOVEDIR
            this.bricks.get(brickNr).getOnField().setMoveDir(getUpdateMoveDir(toField));
        }else{
            moveNotSameRow(playersField,brickNr,toField,l);
            this.bricks.get(brickNr).setOnField(toField);
            
            //UPDATE MOVEDIR
            this.bricks.get(brickNr).getOnField().setMoveDir(getUpdateMoveDir(toField));
        }
    }
    
    public String getUpdateMoveDir(Field field){
        String dir = "";
        if(field.getYValue()%2==1){
              dir = "right";
                    
        }else{
           dir = "left";
        }
        
        return dir;
    }
    
    public void moveSameRow(Field fromField,int brickNr, Field toField,JLabel l){
        int numFieldsToMove = calculateNumFieldsToMove(fromField,toField);
        
        if(fromField.getYValue()==toField.getYValue()){
            System.out.println("");
            if(fromField.getMoveDir().equals("right")){
                //System.out.println("dir right");
                for(int i = 1;i<numFieldsToMove+1;i++){
                    ArrayList<Integer> onField = new ArrayList<Integer>();
                    onField.add(fromField.getXValue()+i);
                    onField.add(fromField.getYValue());
                    l.setBounds(this.coordinateHashMap.get(onField).get(0), this.coordinateHashMap.get(onField).get(1),this.img.getIconWidth(), this.img.getIconHeight());
                    this.f.add(l);
                    l.setVisible(true);
                    this.f.repaint();
                    
                    //sleep for å kunne se at brikken flytter seg.
                    sleepProgram(1);
                    
                    //for at ikke brikken skal bli usynlig til slutt.
                    if(i!=numFieldsToMove){
                        l.setVisible(false);
                    }
                    this.f.repaint();
                }
                
            }else if(fromField.getMoveDir().equals("left")){
                for(int j = 1;j<numFieldsToMove+1;j++){
                    ArrayList<Integer> onField = new ArrayList<Integer>();
                    onField.add(fromField.getXValue()-j);
                    onField.add(fromField.getYValue());
                    l.setBounds(this.coordinateHashMap.get(onField).get(0), this.coordinateHashMap.get(onField).get(1),this.img.getIconWidth(), this.img.getIconHeight());
                    //System.out.println("loop number: "+j);
                    this.f.add(l);
                    l.setVisible(true);
                    this.f.repaint();
                    
                    //sleep for å kunne se at brikken flytter seg.
                    sleepProgram(1);
                    
                    //for at ikke brikken skal bli usynlig til slutt.
                    if(j!=numFieldsToMove){
                        l.setVisible(false);
                    }
                    this.f.repaint();
                }
            }
        
        }
    }
    
    public int calculateNumFieldsToMove(Field fromField, Field toField){
        int numFieldsToMove=-1;
        
        if(fromField.getYValue()==toField.getYValue()){
            numFieldsToMove = Math.abs(toField.getXValue()-fromField.getXValue());
        }else{
            if(fromField.getMoveDir().equals("right")){
                numFieldsToMove = Math.abs(9-fromField.getXValue())+Math.abs(9-toField.getXValue())+Math.abs(toField.getYValue()-fromField.getYValue());
            }else if(fromField.getMoveDir().equals("left")){
                numFieldsToMove = Math.abs(fromField.getXValue()-1)+Math.abs(toField.getXValue()-1)+Math.abs(toField.getYValue()-fromField.getYValue());
            }
        }
        
        return numFieldsToMove;
    }
    
    public void sleepProgram(int seconds){
        try {
            Thread.sleep(seconds*1000);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void moveNotSameRow(Field fromField,int brickNr, Field toField,JLabel l){
        int numFieldsToMove = calculateNumFieldsToMove(fromField,toField);
        int moveFieldsSameRow = 9-fromField.getXValue();
        int moveFieldsNextRow = numFieldsToMove - moveFieldsSameRow;
        
        if(fromField.getMoveDir()=="right"){
            
            for(int i = 1;i<moveFieldsSameRow+1;i++){
                    
                ArrayList<Integer> onField = new ArrayList<Integer>();
                onField.add(fromField.getXValue()+i);
                onField.add(fromField.getYValue());
                
                l.setBounds(this.coordinateHashMap.get(onField).get(0), this.coordinateHashMap.get(onField).get(1),this.img.getIconWidth(), this.img.getIconHeight());
                //System.out.println("loop number: "+i);
                this.f.add(l);
                l.setVisible(true);
                this.f.repaint();
                    
                    //sleep for å kunne se at brikken flytter seg.
                    sleepProgram(1);
                    
                    //for at ikke brikken skal bli usynlig til slutt.
                    if(i!=moveFieldsSameRow){
                        l.setVisible(false);
                    }
                    this.f.repaint();
                }
            
            for(int v = 1;v<moveFieldsNextRow+1;v++){
                ArrayList<Integer> onField_2 = new ArrayList<Integer>();
                onField_2.add(9-(v-1));
                onField_2.add(fromField.getYValue()+1);
                
                l.setBounds(this.coordinateHashMap.get(onField_2).get(0), this.coordinateHashMap.get(onField_2).get(1),this.img.getIconWidth(), this.img.getIconHeight());
               // System.out.println("loop number: "+v);
                this.f.add(l);
                l.setVisible(true);
                this.f.repaint();
                    
                    //sleep for å kunne se at brikken flytter seg.
                    sleepProgram(1);
                    
                    //for at ikke brikken skal bli usynlig til slutt.
                    if(v!=moveFieldsNextRow){
                        l.setVisible(false);
                    }
                    this.f.repaint();
            }
            
            
        }else if(fromField.getMoveDir()=="left"){
            moveFieldsSameRow = fromField.getXValue()-1;
            moveFieldsNextRow = numFieldsToMove - moveFieldsSameRow;
            
            for(int i = 1;i<moveFieldsSameRow+1;i++){
                    
                ArrayList<Integer> onField = new ArrayList<Integer>();
                onField.add(fromField.getXValue()-i);
                onField.add(fromField.getYValue());
                
                l.setBounds(this.coordinateHashMap.get(onField).get(0), this.coordinateHashMap.get(onField).get(1),this.img.getIconWidth(), this.img.getIconHeight());
                //System.out.println("loop number: "+i);
                this.f.add(l);
                l.setVisible(true);
                this.f.repaint();
                    
                    //sleep for å kunne se at brikken flytter seg.
                    sleepProgram(1);
                    
                    //for at ikke brikken skal bli usynlig til slutt.
                    if(i!=moveFieldsSameRow){
                        l.setVisible(false);
                    }
                    this.f.repaint();
                }
            
            for(int v = 1;v<moveFieldsNextRow+1;v++){
                ArrayList<Integer> onField_2 = new ArrayList<Integer>();
                onField_2.add(1+(v-1));
                onField_2.add(fromField.getYValue()+1);
                
                l.setBounds(this.coordinateHashMap.get(onField_2).get(0), this.coordinateHashMap.get(onField_2).get(1),this.img.getIconWidth(), this.img.getIconHeight());
                //System.out.println("loop number: "+v);
                this.f.add(l);
                l.setVisible(true);
                this.f.repaint();
                    
                    //sleep for å kunne se at brikken flytter seg.
                    sleepProgram(1);
                    
                    //for at ikke brikken skal bli usynlig til slutt.
                    if(v!=moveFieldsNextRow){
                        l.setVisible(false);
                    }
                    this.f.repaint();
            }
            
        }
    }
    
    public void moveGoal(Field fromField,String player, Field toField,int dice){
        JLabel l = new JLabel();
        int brickNr =  -1;
        
        for(int i = 0;i<this.bricks.size();i++){
            if(this.bricks.get(i).getBelongsToPlayer().equals(player)){
                l = this.bricks.get(i).getBrick();
                brickNr = i;
            }
        }
        
        int numToGoal = 9-fromField.getXValue();
        int numLeftAfterGoal = Math.abs(dice - numToGoal);
        
        
        for(int i = 1;i<numToGoal+1;i++){
                    
                ArrayList<Integer> onField = new ArrayList<Integer>();
                onField.add(fromField.getXValue()+i);
                onField.add(fromField.getYValue());
                
                l.setBounds(this.coordinateHashMap.get(onField).get(0), this.coordinateHashMap.get(onField).get(1),this.img.getIconWidth(), this.img.getIconHeight());
                //System.out.println("loop number: "+i);
                this.f.add(l);
                l.setVisible(true);
                this.f.repaint();
                    
                    //sleep for å kunne se at brikken flytter seg.
                    sleepProgram(1);
                    
                    //for at ikke brikken skal bli usynlig til slutt.
                    if(i!=numToGoal){
                        l.setVisible(false);
                    }
                    this.f.repaint();
                }
        
        for(int i = 1;i<numLeftAfterGoal+1;i++){
                    
                ArrayList<Integer> onField = new ArrayList<Integer>();
                
                onField.add(9-i);
                
                onField.add(fromField.getYValue());
                
                l.setBounds(this.coordinateHashMap.get(onField).get(0), this.coordinateHashMap.get(onField).get(1),this.img.getIconWidth(), this.img.getIconHeight());
                this.f.add(l);
                l.setVisible(true);
                this.f.repaint();
                    
                    //sleep for å kunne se at brikken flytter seg.
                    sleepProgram(1);
                    
                    //for at ikke brikken skal bli usynlig til slutt.
                    if(i!=numLeftAfterGoal){
                        l.setVisible(false);
                    }
                    this.f.repaint();
                }
        
        this.bricks.get(brickNr).setOnField(toField);
        this.bricks.get(brickNr).getOnField().setMoveDir(getUpdateMoveDir(toField));
        
    }
    
    public void setPiecesStart(){
        for(int i =0;i<this.bricks.size();i++){
            JLabel l  = this.bricks.get(i).getBrick();
            ArrayList<Integer> co = new ArrayList<Integer>();
            co.add(1);
            co.add(1);
            l.setBounds(this.coordinateHashMap.get(co).get(0), this.coordinateHashMap.get(co).get(1), this.img.getIconWidth(), this.img.getIconHeight());
            this.f.add(l);
            l.setVisible(true);
            this.f.repaint();
        }
    }
    
    public ArrayList<Brick> initPieces() throws IOException{
        ArrayList<Brick> brics = new ArrayList<Brick>();
        for(int i = 0;i<this.playerNames.size();i++){
            Brick b = new Brick(this.f,this.brickFileNames[i]);
            b.setBelongsToPlayer(playerNames.get(i));
            ArrayList<Integer> startCoordinates = new ArrayList<Integer>();
            startCoordinates.add(1);
            startCoordinates.add(1);
            brics.add(b);
        }
        return brics;
    }
    
    public void moveLadder(Field fromField,String player,Field toField){
        
        JLabel l = new JLabel();
        int brickNr =  -1;
        
        for(int i = 0;i<this.bricks.size();i++){
            if(this.bricks.get(i).getBelongsToPlayer().equals(player)){
                l = this.bricks.get(i).getBrick();
                brickNr = i;
            }
        }
        ArrayList<Integer> to = new ArrayList<Integer>();
        to.add(toField.getXValue());
        to.add(toField.getYValue());
        l.setBounds(this.coordinateHashMap.get(to).get(0), this.coordinateHashMap.get(to).get(1),this.img.getIconWidth(), this.img.getIconHeight());
        this.f.add(l);
        l.setVisible(true);
        this.f.repaint();
        
        this.bricks.get(brickNr).setOnField(toField);
            
        this.bricks.get(brickNr).getOnField().setMoveDir(getUpdateMoveDir(toField));
        
        
    }
    
    public HashMap <ArrayList<Integer>, ArrayList<Integer>> getHashMap(){
        return this.coordinateHashMap;
    }
}
