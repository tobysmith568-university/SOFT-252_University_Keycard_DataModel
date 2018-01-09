/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Locations.RoomTypes;

import Control.RoomFactory;
import Locations.Floor;
import Locations.Room;
import Locations.RoomType;
import Locations.States.LocationState;
import People.Keycard;
import People.Role;
import static People.Role.CLEANER;
import static People.Role.EMERGENCYRESPONDER;
import static People.Role.MANAGER;
import static People.Role.SECURITY;
import static People.Role.STAFFMEMBER;
import static People.Role.STUDENT;
import static People.Role.VISITOR;
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
public class ResearchLabTest {
    
    Floor testFloor = new Floor("1");
    Room room = RoomFactory.Create("01", RoomType.RESEARCHLAB);
    Keycard c1, c2, c3, c4, c5, c6, c7;
    
    public ResearchLabTest() {
        c1 = new Keycard(new Role[] { VISITOR }, "A visitor", "01");
        c2 = new Keycard(new Role[] { STAFFMEMBER }, "A staff member", "02");
        c3 = new Keycard(new Role[] { STUDENT }, "A student", "03");
        c4 = new Keycard(new Role[] { CLEANER }, "A cleaner", "04");
        c5 = new Keycard(new Role[] { MANAGER }, "A manager", "05");
        c6 = new Keycard(new Role[] { SECURITY }, "A security", "06");
        c7 = new Keycard(new Role[] { EMERGENCYRESPONDER }, "A responder", "07"); 
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
    public void testAccessRequest() {
        System.out.println("Testing AccessRequest()");
        
        assertEquals(false, TestAll(c1));
        assertEquals(true, TestAll(c2));
        assertEquals(false, TestAll(c3));
        assertEquals(true, TestAll(c4));
        assertEquals(true, TestAll(c5));
        assertEquals(true, TestAll(c6));
        assertEquals(true, TestAll(c7));
    }
    
    private boolean TestAll(Keycard keycard){
        for (LocationState state : LocationState.values()) {
            room.SetRoomState(state, "test reason");
            if (room.AccessRequest(keycard))
                return true;
        }
        return false;
    }    
}
