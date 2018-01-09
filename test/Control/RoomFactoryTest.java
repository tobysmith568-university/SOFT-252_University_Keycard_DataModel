/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Locations.Room;
import static Locations.RoomType.*;
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
public class RoomFactoryTest {
    
    public RoomFactoryTest() {
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
    public void testCreate() {
        System.out.println("Testing Create()");
        
        Room r1 = RoomFactory.Create("1", LECTUREHALL);
        Room r2 = RoomFactory.Create("2", SECUREROOM);
        
        assertEquals("01", r1.GetNumber());
        assertEquals("Lecture Hall", r1.GetRoomType());
        
        assertEquals("02", r2.GetNumber());
        assertEquals("Secure Room", r2.GetRoomType());
    }
    
}
