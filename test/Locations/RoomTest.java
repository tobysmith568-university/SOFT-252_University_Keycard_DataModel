/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Locations;

import Control.RoomFactory;
import org.junit.After;
import org.junit.AfterClass;
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
        this.two = RoomFactory.Create("01", RoomType.LECTUREHALL);
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
    }

    @Test
    public void testSetRoomType() {
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
    }

    @Test
    public void testGetFloor() {
    }

    @Test
    public void testSetFloor() {
    }
    
}
