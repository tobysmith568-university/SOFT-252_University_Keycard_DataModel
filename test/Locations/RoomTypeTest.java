/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Locations;

import static Locations.RoomType.*;
import Locations.RoomTypes.*;
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
public class RoomTypeTest {
    
    public RoomTypeTest() {
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
    public void testGetRoomType() {
        System.out.println("Testing GetRoomType()");
        
        assertEquals(true, LECTUREHALL.GetRoomType() instanceof LectureHall);
        assertEquals(true, STUDENTLAB.GetRoomType() instanceof StudentLab);
        assertEquals(true, RESEARCHLAB.GetRoomType() instanceof ResearchLab);
        assertEquals(true, STAFFROOM.GetRoomType() instanceof StaffRoom);
        assertEquals(true, SECUREROOM.GetRoomType() instanceof SecureRoom);
    }

    @Test
    public void testGetName() {
        System.out.println("Testing GetName()");
        
        assertEquals("Student Lab", STUDENTLAB.GetName());
        assertEquals("Secure Room", SECUREROOM.GetName());
        assertEquals("Staff Room", STAFFROOM.GetName());
    }
    
}
