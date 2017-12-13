/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Listeners;

import Locations.Room;
import People.Keycard;
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
public class IAccessSubjectTest {
    
    public IAccessSubjectTest() {
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
    public void testAddAccessObserver() {
    }

    @Test
    public void testRemoveAccessObserver() {
    }

    @Test
    public void testUpdateAccessObservers() {
    }

    public class IAccessSubjectImpl implements IAccessSubject {

        public boolean AddAccessObserver(IAccessObserver observer) {
            return false;
        }

        public boolean RemoveAccessObserver(IAccessObserver observer) {
            return false;
        }

        public void UpdateAccessObservers(Keycard keycard, Room room, boolean wasSuccessful) {
        }
    }
    
}
