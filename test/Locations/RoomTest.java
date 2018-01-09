/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Locations;

import Control.RoomFactory;
import static Locations.RoomType.SECUREROOM;
import static Locations.RoomType.STUDENTLAB;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Student
 */
public class RoomTest {
    
    Room one;
    Room two;
    
    public RoomTest() {
        this.one = RoomFactory.Create("01", RoomType.LECTUREHALL);
        this.two = RoomFactory.Create("02", RoomType.LECTUREHALL);
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
    public void testGetNumber() {
        System.out.println("Testing GetName()");
        this.one = RoomFactory.Create("01", RoomType.LECTUREHALL);
        this.two = RoomFactory.Create("02", RoomType.LECTUREHALL);
        
        assertEquals("01", one.GetNumber());
        assertEquals("02", two.GetNumber());
    }

    @Test
    public void testSetRoomType() {
        System.out.println("Testing SetRoomType()");
        this.one = RoomFactory.Create("01", RoomType.LECTUREHALL);
        this.two = RoomFactory.Create("02", RoomType.LECTUREHALL);
        
        one.SetRoomType(STUDENTLAB);
        two.SetRoomType(SECUREROOM);
        
        assertEquals("Student Lab", one.GetRoomType());
        assertEquals("Secure Room", two.GetRoomType());
    }

    @Test
    public void testAccessRequest() {
    }

    @Test
    public void testAddAccessObserver() {
    }

    @Test
    public void testRemoveAccessObserver() {
    }

    @Test
    public void testUpdateAccessObservers() {
    }

    @Test
    public void testGetRoomType() {
        System.out.println("Testing SetRoomType()");
        this.one = RoomFactory.Create("01", RoomType.STUDENTLAB);
        this.two = RoomFactory.Create("02", RoomType.SECUREROOM);
        
        assertEquals("Student Lab", one.GetRoomType());
        assertEquals("Secure Room", two.GetRoomType());
    }

    @Test
    public void testGetFloor() {
        System.out.println("Testing GetFloor()");
        
        Floor f = new Floor("1");
        Room r = f.AddRoom(SECUREROOM);
        
        assertEquals(f, r.GetFloor());
    }

    @Test
    public void testSetFloor() {
        System.out.println("Testing SetFloor()");
        
        Floor f = new Floor("4");
        Room r = new Room("test room");
        r.SetFloor(f);
        
        assertEquals(f, r.GetFloor());
    }
}
