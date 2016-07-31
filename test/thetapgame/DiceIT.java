/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thetapgame;

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
public class DiceIT {
    
    public DiceIT() {
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
     * Test of ThrowDice method, of class Dice.
     */
    @Test
    public void testThrowDice() {
        System.out.println("ThrowDice");
        Dice instance = new Dice();
        instance.PrintDice();
        instance.ThrowDice();
        instance.PrintDice();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getValue method, of class Dice.
     */
    @Test
    public void testGetValue() {
        System.out.println("getValue");
        Dice instance = new Dice();
        
    }

    /**
     * Test of PrintDice method, of class Dice.
     */
    @Test
    public void testPrintDice() {
        System.out.println("PrintDice");
        Dice instance = new Dice();
        //instance.PrintDice();
        
    }
    
}
