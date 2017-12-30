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
import People.Keycard;
import static People.Role.*;
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
public class LectureHallTest {
    
    Floor testFloor = new Floor("1");
    Room room = RoomFactory.Create("01", RoomType.LECTUREHALL);
    Keycard card1, card2, card3, card4, card5, card6, card7;
    
    public LectureHallTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        card1 = new Keycard(VISITOR, "A visitor", "01");
        card2 = new Keycard(STAFFMEMBER, "A staff member", "02");
        card3 = new Keycard(STUDENT, "A student", "03");
        card4 = new Keycard(CLEANER, "A cleaner", "04");
        card5 = new Keycard(MANAGER, "A manager", "05");
        card6 = new Keycard(SECURITY, "A security", "06");
        card7 = new Keycard(EMERGENCYRESPONDER, "A responder", "07");        
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testAccessRequest() {
        System.out.println("Testing AccessRequest()");
        assertEquals(true, room.AccessRequest(card1));
        assertEquals(true, room.AccessRequest(card2));
        assertEquals(true, room.AccessRequest(card3));
        assertEquals(true, room.AccessRequest(card4));
        assertEquals(true, room.AccessRequest(card5));
        assertEquals(true, room.AccessRequest(card6));
        assertEquals(true, room.AccessRequest(card7));
    }
    
}
