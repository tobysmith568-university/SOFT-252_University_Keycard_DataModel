/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Locations;

import Locations.States.LocationState;
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
public class FloorTest {
    
    Floor one;
    Floor two;
    
    public FloorTest() {
        this.one = new Floor("1");
        this.two = new Floor("2");
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
    public void testGetBuilding() {
        System.out.println("Testing GetBuilding()");
        
        Building b = new Building("test building", "TB");
        Floor f = b.AddFloor();
        
        assertEquals(b, f.GetBuilding());
    }

    @Test
    public void testGetFloorNumber() {
        System.out.println("Testing GetFloorNumber()");
        
        Floor f1 = new Floor("1");
        Floor f2 = new Floor("2");
        
        assertEquals("1", f1.GetFloorNumber());
        assertEquals("2", f2.GetFloorNumber());
    }

    @Test
    public void testGetChild() {
        System.out.println("Testing GetChild()");
        this.one = new Floor("1");
        this.two = new Floor("2");
        
        one.AddRoom(RoomType.STUDENTLAB);
        Room r1 = one.AddRoom(RoomType.STUDENTLAB);
        
        assertEquals(r1, one.GetChild("01"));
        assertEquals(null, two.GetChild("01"));
    }

    @Test
    public void testGetAllChildren() {
        System.out.println("Testing GetAllChildren()");
        this.one = new Floor("1");
        this.two = new Floor("2");
        
        one.AddRoom(RoomType.STUDENTLAB);
        one.AddRoom(RoomType.STUDENTLAB);
        
        assertEquals(2, one.GetAllChildren().length);
        assertEquals(0, two.GetAllChildren().length);
    }

    @Test
    public void testActualSetRoomState() {
        System.out.println("Testing ActualSetRoomState()");
        this.one = new Floor("1");
        this.two = new Floor("2");
        
        one.ActualSetRoomState(LocationState.EMERGENCY, "Test reason");        
        
        assertEquals(LocationState.EMERGENCY, one.GetState());
        assertEquals(LocationState.NOEMERGENCY, two.GetState());
        assertEquals("Test reason", one.GetStateChangeReason());
        assertEquals("", two.GetStateChangeReason());   
    }

    @Test
    public void testAddRoom() {
        System.out.println("Testing AddFloor()");
        this.one = new Floor("1");
        this.two = new Floor("2");
        
        one.AddRoom(RoomType.STUDENTLAB);
        
        assertEquals(1, one.GetAllChildren().length);        
        assertEquals(0, two.GetAllChildren().length);  
    }

    @Test
    public void testSetBuilding() {
        System.out.println("Testing SetBuilding()");
        
        Building b = new Building("test building", "TB");
        Floor f = new Floor("1");
        f.SetBuilding(b);
        
        assertEquals(b, f.GetBuilding());
    }

    @Test
    public void testSetIsMixedState() {
        System.out.println("Testing SetIsMixedState()");
        this.one = new Floor("1");
        this.two = new Floor("2");
        
        one.AddRoom(RoomType.STUDENTLAB);
        Room r1 = one.AddRoom(RoomType.STUDENTLAB);
        r1.SetRoomState(LocationState.EMERGENCY, "test reason");
        
        assertEquals(true, one.GetIsMixedState());
        assertEquals(false, two.GetIsMixedState());
    }

    @Test
    public void testRemoveRoom() {
        System.out.println("Testing RemoveFloor()");
        this.one = new Floor("1");
        this.two = new Floor("2");
                
        one.AddRoom(RoomType.STUDENTLAB);
        two.AddRoom(RoomType.STUDENTLAB);
        
        one.RemoveRoom(one.GetChild("00"));
        
        assertEquals(0, one.GetAllChildren().length);        
        assertEquals(1, two.GetAllChildren().length);
                
        one.RemoveRoom(new Room("3"));
        
        assertEquals(1, two.GetAllChildren().length);
    }

    @Test
    public void testObservedStateUpdate() {
    }
    
}
