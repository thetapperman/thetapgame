/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thetapgame;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Kraugen
 */
public class JokerNord {
    private JFrame f; 
    private HashMap<String,ArrayList<Integer>> numberControl;//up: 28362, down: 28433, middle:284830
    private HashMap<Integer,HashMap<String,JLabel>> lblControl = new HashMap<Integer,HashMap<String,JLabel>>();
    private HashMap<Integer,HashMap<String,ArrayList<Integer>>> coordinates = new HashMap<Integer,HashMap<String,ArrayList<Integer>>>(); //koordinater. samme konsept med å slå opp som på bussturen.
    private ImageIcon img;
    private HashMap<Integer,String> finalAppearWindow;
    private HashMap<Integer,ArrayList<Integer>> markerData;
    private HashMap<Integer,JLabel> markerLabelData;
    public JokerNord() throws IOException{
        this.numberControl = getGameNumbers();
        this.markerData = initMarkerData();
        this.finalAppearWindow = initAppearFinal();
        this.f = new JFrame();
        this.img = new ImageIcon(ImageIO.read(new File("img/jokernord/jokernord_background.jpg")));
        CreateBoard();
        this.coordinates = initCoordinates();
        this.lblControl = initLblControl();
        this.markerLabelData = initMarkerLabelData();
        startJokerNord();
    }
    
    private void showMarkerLbl(int number){
        
        for(int i=0;i<5;i++){
            this.markerLabelData.get(i+1).setVisible(false);
        }
        sleepProgram(1);
        
        this.markerLabelData.get(number).setVisible(true);
    }
    
    private HashMap<Integer,JLabel> initMarkerLabelData(){
        HashMap<Integer,JLabel> l = new HashMap<Integer,JLabel>();
        
        for(int i = 0;i<5;i++){
            l.put(i+1,returnMarkerLbl(this.markerData.get(i+1).get(0),this.markerData.get(i+1).get(1)));
        }
        
        return l;
    }
    
    private HashMap<Integer,ArrayList<Integer>> initMarkerData(){
        HashMap<Integer,ArrayList<Integer>> mData = new HashMap<Integer,ArrayList<Integer>>();
        int xGeneral = -220;
        int yStart = 107;
        int yOffset = 34;
        
        for(int i = 0;i<5;i++){
            ArrayList<Integer> coData = new ArrayList<Integer>();
            int x = xGeneral;
            int y = yStart-(i*yOffset);
            coData.add(x);
            coData.add(y);
            mData.put(i+1,coData);
        }
        
        return mData;
    }
    
    private void printCoordinates(){
        String[] nrTypes = {"up","down","middle"};
        int counter = 1;
        for (Map.Entry<Integer, HashMap<String,ArrayList<Integer>>> iterator : this.coordinates.entrySet()) {
            System.out.println("row: "+ Integer.toString(counter));
            for(int n = 0;n<nrTypes.length;n++){
                System.out.println("type: "+nrTypes[n]+". Data: "+iterator.getValue().get(nrTypes[n]));
            }
            counter++;
            System.out.println("_____________________________________________________________");
        }
    }
    
    private HashMap<Integer,String> initAppearFinal(){
       HashMap<Integer,String> finalAppearWindow = new HashMap<Integer,String>();
       finalAppearWindow.put(1,"You suck. You really suck. Fill up your drink and chug three times.");
       finalAppearWindow.put(2,"2 chugs for you....... You are hopeless");
       finalAppearWindow.put(3,"You are damn lucky that you only have to chug once. CHUG!!!!!");
       finalAppearWindow.put(4, "You were really close. 5 zips for not making it to the top.");
       finalAppearWindow.put(5, "WOW!!! You lucky little scumbag. Pick another person to chug. If Erdalen is in the room, he also needs to chug.");
    
       return finalAppearWindow;
    }
    
    public void CreateBoard(){
        this.f.setContentPane(new JLabel(this.img));
    	this.f.pack();
        this.f.setLayout(null);
        this.f.setResizable(false);
        this.f.setDefaultCloseOperation(0);
        this.f.setVisible(true);
    }
    
    private void paintObject(int x, int y, String path){
        JLabel lbl = new JLabel(new ImageIcon(path));
        lbl.setBounds(x, y, this.img.getIconWidth(), this.img.getIconHeight());
        this.f.add(lbl);
        lbl.setVisible(true);
        this.f.repaint();
    }
    
    private void showLbl(int nr, String type){
        this.lblControl.get(nr).get(type).setVisible(true);
    }
    
    private JLabel returnLbllbl(int x, int y){
        String path = "img/jokernord/ringmarker.gif";
        JLabel lbl = new JLabel(new ImageIcon(path));
        lbl.setBounds(x, y, this.img.getIconWidth(), this.img.getIconHeight());
        this.f.add(lbl);
        lbl.setVisible(false);
        this.f.repaint();
        return lbl;
    }
    
    private JLabel returnMarkerLbl(int x, int y){
        String path = "img/jokernord/main_marker.gif";
        JLabel lbl = new JLabel(new ImageIcon(path));
        lbl.setBounds(x, y, this.img.getIconWidth(), this.img.getIconHeight());
        this.f.add(lbl);
        lbl.setVisible(false);
        this.f.repaint();
        return lbl;
    }
    
    private JLabel returnLbl(int number, int x, int y){
        String path = "img/jokernord/"+Integer.toString(number)+"_num.gif";
        JLabel lbl = new JLabel(new ImageIcon(path));
        lbl.setBounds(x, y, this.img.getIconWidth(), this.img.getIconHeight());
        this.f.add(lbl);
        lbl.setVisible(false);
        this.f.repaint();
        return lbl;
    }
    
    private void showMiddleGameNumbers(){
    
        for(int i = 1;i<6;i++){
            showLbl(i, "middle");
            sleepProgram(1);
        }
    }
    
    private void printContentOfArrayList(ArrayList<Integer> array){
        System.out.println("________________printing array_list_________________");
        for(int i =0;i<array.size();i++){
            System.out.println("element: "+array.get(i));
        }
        System.out.println("_________________________________");
    }
    
    private HashMap<Integer,HashMap<String,ArrayList<Integer>>> initCoordinates(){
        HashMap<Integer,HashMap<String,ArrayList<Integer>>> markerData = new HashMap<Integer,HashMap<String,ArrayList<Integer>>>();
        String[] nrTypes = {"up","down","middle"};
        int offsetxGeneral = 87;
        
        int xStartMiddle = -127;
        int xStartUp = -123;
        int xStartDown = -125;
        
        int yMiddle = -30;
        int yDown = 70;
        int yUp = -123;
        
        for(int i=0;i<5;i++){
            HashMap<String,ArrayList<Integer>> coData = new HashMap<String,ArrayList<Integer>>();
            ArrayList<Integer> inCo1 = new ArrayList<Integer>();
            inCo1.add(xStartUp + i*(offsetxGeneral));
            inCo1.add(yUp);
            
            ArrayList<Integer> inCo2 = new ArrayList<Integer>();
            inCo2.add(xStartDown + i*(offsetxGeneral));
            inCo2.add(yDown);
            
            ArrayList<Integer> inCo3 = new ArrayList<Integer>();
            inCo3.add(xStartMiddle + i*(offsetxGeneral));
            inCo3.add(yMiddle);
            
            coData.put("up",inCo1);
            coData.put("down",inCo2);
            coData.put("middle",inCo3);
            
            markerData.put(i+1, coData);
        }
        
        return markerData;
    }
    
    private HashMap<Integer,HashMap<String,JLabel>> initLblControl(){     //print i denne funksjonen
        
        System.out.println("\n\nEntering label control: \n\n");
        HashMap<Integer,HashMap<String,JLabel>> lblControl = new HashMap<Integer,HashMap<String,JLabel>>();
        String[] nrTypes = {"up","down","middle"};
        
        for(int i=0;i<5;i++){
            HashMap<String,JLabel> lbl = new HashMap<String,JLabel>();
            for(int type=0;type<nrTypes.length;type++){
                
                String dirType = nrTypes[type];
                int x = this.coordinates.get(i+1).get(dirType).get(0);
                int y = this.coordinates.get(i+1).get(dirType).get(1);
                JLabel getLbl = returnLbl(this.numberControl.get(dirType).get(i), x, y);
                lbl.put(dirType, getLbl);
                
            }
            lblControl.put(i+1,lbl);
        }
        
        return lblControl;
    }
    
    private boolean checkIfJoker(){
        int nr = getRandNr();
        int jokerNr = getRandNr();
        return nr==jokerNr;
    }
    
    private HashMap<String,ArrayList<Integer>> getGameNumbers(){
        HashMap<String,ArrayList<Integer>> nr = new HashMap<String,ArrayList<Integer>>();
        String[] nrTypes = {"up","down","middle"};
        
        for(int numberTypes = 0;numberTypes<nrTypes.length;numberTypes++){
            ArrayList<Integer> ints = new ArrayList<Integer>();
            for(int i = 0;i<5;i++){
                ints.add(getRandNr());
            }
            nr.put(nrTypes[numberTypes], ints);
        }
        return nr;
    }
    
    private int getRandNr(){
        Random randomizer = new Random();
        return (randomizer.nextInt(9) + 1);
    }
    
    private boolean evaluateChoice(int numberMiddle, int nrToEvaluate,  String type){
        return ((type.equals("up") && nrToEvaluate>numberMiddle) || (type.equals("down") && nrToEvaluate<numberMiddle) || nrToEvaluate==numberMiddle);
    }
    
    
    public void sleepProgram(int seconds){
        int milliSeconds = seconds*1000;
        
        try {
            Thread.sleep(milliSeconds);                 
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
    
    public void startJokerNord(){
       int markerNr = 1;
       showMiddleGameNumbers();
       for(int i = 1;i<6;i++){
           
           JokerNordControl k = new JokerNordControl("");
           k.ShowFrame(true);
           k.whileConnected();
           //ta input fra brukeren. kan være "up"/"down"
           String type = k.getMsg(); //kun for test
           showLbl(i,type);
           
           boolean eval = evaluateChoice(this.numberControl.get("middle").get(i-1), this.numberControl.get(type).get(i-1), type);
                if(eval==false && markerNr!=1){
                    markerNr--;
                    showMarkerLbl(markerNr);
                }else if(eval == true){
                    if(markerNr<5){
                        markerNr++;
                    }
                    showMarkerLbl(markerNr);
                }
                sleepProgram(2);
           }
       Util.showDialog(this.finalAppearWindow.get(markerNr));
       this.f.setVisible(false);
    }
    
    public static void main(String[] args) throws IOException {
        JokerNord joker = new JokerNord();
    }
    
}
