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
public class CampusTest {
    
    Campus one;
    Campus two;
    
    public CampusTest() {
        this.one = new Campus("Test campus 1");
        this.two = new Campus("Test campus 2");
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
        System.out.println("Testing GetName()");
        this.one = new Campus("Test campus 1");
        this.two = new Campus("Test campus 2");
        
        assertEquals("Test campus 1", one.GetName());
        assertEquals("Test campus 2", two.GetName());
    }

    @Test
    public void testGetChild() {
        System.out.println("Testing GetChild()");
        this.one = new Campus("Test campus 1");
        this.two = new Campus("Test campus 2");
        
        one.AddBuilding("test building 1", "tb1");
        Building b2 = one.AddBuilding("test building 2", "tb2");
        
        assertEquals(b2, one.GetChild("test building 2"));
        assertEquals(null, two.GetChild("1"));
    }

    @Test
    public void testGetAllChildren() {
        System.out.println("Testing GetAllChildren()");
        this.one = new Campus("Test campus 1");
        this.two = new Campus("Test campus 2");
        
        
        one.AddBuilding("test building 1", "tb1");
        one.AddBuilding("test building 2", "tb2");
        
        assertEquals(2, one.GetAllChildren().length);
        assertEquals(0, two.GetAllChildren().length);
    }

    @Test
    public void testActualSetRoomState() {
        System.out.println("Testing ActualSetroomState()");
        this.one = new Campus("Test campus 1");
        this.two = new Campus("Test campus 2");
        
        one.ActualSetRoomState(LocationState.EMERGENCY, "Test reason");        
        
        assertEquals(LocationState.EMERGENCY, one.GetState());
        assertEquals(LocationState.NOEMERGENCY, two.GetState());
        assertEquals("Test reason", one.GetStateChangeReason());
        assertEquals("", two.GetStateChangeReason());   
    }

    @Test
    public void testAddBuilding() {
        System.out.println("Testing AddBuilding()");
        this.one = new Campus("Test campus 1");
        this.two = new Campus("Test campus 2");
        
        one.AddBuilding("Test building 1", "TB1");
        
        assertEquals(1, one.GetAllChildren().length);        
        assertEquals(0, two.GetAllChildren().length);
    }

    @Test
    public void testRemoveBuilding() {
        System.out.println("Testing RemoveFloor()");
        this.one = new Campus("Test campus 1");
        this.two = new Campus("Test campus 2");
        
        one.AddBuilding("Test building 1", "TB1");
        two.AddBuilding("Test building 2", "TB2");
        
        one.RemoveBuilding(one.GetChild("Test building 1"));
        
        assertEquals(0, one.GetAllChildren().length);        
        assertEquals(1, two.GetAllChildren().length);
                
        one.RemoveBuilding(new Building("Test building 3", "TB3"));
        
        assertEquals(1, two.GetAllChildren().length);
    }

    @Test
    public void testObservedStateUpdate() {
    }
    
}
