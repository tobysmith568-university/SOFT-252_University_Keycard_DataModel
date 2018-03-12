/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Locations.States;

import static Locations.States.LocationState.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Student
 */
public class LocationStateTest {
    
    public LocationStateTest() {
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

    @Test
    public void testValues() {
    }

    @Test
    public void testValueOf() {
    }

    @Test
    public void testGetLocationState() {
        System.out.println("Testing GetLocationState()");
        
        assertEquals(true, EMERGENCY.GetLocationState() instanceof EmergencyState);
        assertEquals(true, NOEMERGENCY.GetLocationState() instanceof NormalState);
    }

    @Test
    public void testGetName() {
        System.out.println("Testing GetName()");
        
        assertEquals("Emergency", EMERGENCY.GetName());
        assertEquals("No Emergency", NOEMERGENCY.GetName());
    }
    
}
