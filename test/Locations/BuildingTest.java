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
public class BuildingTest {
    
    Building one;
    Building two;
    
    public BuildingTest() {
        this.one = new Building("Test building 1", "TB1");
        this.two = new Building("Test building 2", "TB2");
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
    public void testGetName() {
    }

    @Test
    public void testGetChild() {
        System.out.println("Testing GetChild()");
        this.one = new Building("Test building 1", "TB1");
        this.two = new Building("Test building 2", "TB2");
        
        one.AddFloor();
        Floor f2 = one.AddFloor();
        
        assertEquals(f2, one.GetChild("1"));
        assertEquals(null, two.GetChild("1"));
    }

    @Test
    public void testGetAllChildren() {
        System.out.println("Testing GetAllChildren()");
        this.one = new Building("Test building 1", "TB1");
        this.two = new Building("Test building 2", "TB2");
        
        one.AddFloor();
        one.AddFloor();
        
        assertEquals(2, one.GetAllChildren().length);
        assertEquals(0, two.GetAllChildren().length);
    }

    @Test
    public void testGetCampus() {
    }

    @Test
    public void testActualSetRoomState() {
        System.out.println("Testing ActualSetRoomState()");
        this.one = new Building("Test building 1", "TB1");
        this.two = new Building("Test building 2", "TB2");
        
        one.ActualSetRoomState(LocationState.EMERGENCY, "Test reason");        
        
        assertEquals(LocationState.EMERGENCY, one.GetState());
        assertEquals(LocationState.NOEMERGENCY, two.GetState());
        assertEquals("Test reason", one.GetStateChangeReason());
        assertEquals("", two.GetStateChangeReason());        
    }

    @Test
    public void testAddFloor() {
        System.out.println("Testing AddFloor()");
        this.one = new Building("Test building 1", "TB1");
        this.two = new Building("Test building 2", "TB2");
        
        one.AddFloor();
        
        assertEquals(1, one.GetAllChildren().length);        
        assertEquals(0, two.GetAllChildren().length);        
    }

    @Test
    public void testGetShortcode() {
    }

    @Test
    public void testSetCampus() {     
    }

    @Test
    public void testSetIsMixedState() {
        System.out.println("Testing SetIsMixedState()");
        this.one = new Building("Test building 1", "TB1");
        this.two = new Building("Test building 2", "TB2");
        
        one.AddFloor();
        Floor f1 = one.AddFloor();
        f1.SetRoomState(LocationState.EMERGENCY, "test reason");
        
        assertEquals(true, one.GetIsMixedState());
        assertEquals(false, two.GetIsMixedState());
    }

    @Test
    public void testRemoveFloor() {
        System.out.println("Testing RemoveFloor()");
        this.one = new Building("Test building 1", "TB1");
        this.two = new Building("Test building 2", "TB2");
                
        one.AddFloor();
        two.AddFloor();
        
        one.RemoveFloor(one.GetChild("0"));
        
        assertEquals(0, one.GetAllChildren().length);        
        assertEquals(1, two.GetAllChildren().length);
                
        one.RemoveFloor(new Floor("3"));
        
        assertEquals(1, two.GetAllChildren().length);
    }

    @Test
    public void testObservedStateUpdate() {
    }
    
}
