/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thetapgame;

import java.io.IOException;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kraugen
 */
public class BusrouteIT {
    
    public BusrouteIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of shuffleDeck method, of class Busroute.
     */
    @Test
    public void testShuffleDeck() throws IOException {
        System.out.println("shuffleDeck");
        //Busroute bus = new Busroute();
        //bus.printDeck();
        //bus.shuffleDeck();
        //bus.printDeck();
        
        
    }

    /**
     * Test of printDeck method, of class Busroute.
     */
    @Test
    public void testPrintDeck() {
        //System.out.println("printDeck");
       
        
    }

    /**
     * Test of moveCardFromDeckTodrawnCards method, of class Busroute.
     */
    @Test
    public void testMoveCardFromDeckTodrawnCards() throws IOException {
        System.out.println("moveCardFromDeckTodrawnCards");
        Busroute bus = new Busroute();
        //bus.shuffleDeck();
        //System.out.println(bus.getDeck().size());
        //bus.moveCardFromDeckTodrawnCards("3","Spade");
        //System.out.println(bus.getDeck().size());
    }

    /**
     * Test of getDeck method, of class Busroute.
     */
    @Test
    public void testGetDeck() {
        //System.out.println("getDeck");
        
    }

    /**
     * Test of getDrawnCards method, of class Busroute.
     */
    @Test
    public void testGetDrawnCards() {
        //System.out.println("getDrawnCards");
        
    }

    /**
     * Test of hasToDrink method, of class Busroute.
     */
    @Test
    public void testHasToDrink() throws IOException {
        //System.out.println("hasToDrink");
        //Card card = null;
        //Busroute instance = new Busroute();
        //System.out.println("hasToDrink:");
        //System.out.println(instance.hasToDrink(new Card("2","Heart")));
    }

    /**
     * Test of CreateBoard method, of class Busroute.
     */
    @Test
    public void testCreateBoard() {
        
    }
    
}
