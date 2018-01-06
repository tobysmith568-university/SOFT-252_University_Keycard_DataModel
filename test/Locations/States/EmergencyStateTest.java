/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Locations.States;

import People.Keycard;
import People.Role;
import static People.Role.CLEANER;
import static People.Role.EMERGENCYRESPONDER;
import static People.Role.STUDENT;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tsmith10
 */
public class EmergencyStateTest {
    
    EmergencyState state;
    Keycard card1, card2;
    
    public EmergencyStateTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        state = new EmergencyState();
        card1 = new Keycard(new Role[] { STUDENT }, "Dave", "0006");
        card2 = new Keycard(new Role[] { EMERGENCYRESPONDER }, "Fireman", "0007");
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testAccessRequest() {
        System.out.println("Testing AccessRequest()");
        assertEquals(false, state.AccessRequest(card1));
        assertEquals(true, state.AccessRequest(card2));
    }
    
}
