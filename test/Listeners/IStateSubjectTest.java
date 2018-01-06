/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Listeners;

import Locations.Location;
import Locations.States.LocationState;
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
public class IStateSubjectTest {
    
    public IStateSubjectTest() {
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
    public void testAddStateObserver() {
    }

    @Test
    public void testRemoveStateObserver() {
    }

    @Test
    public void testUpdateStateObservers() {
    }

    public class IStateSubjectImpl implements IStateSubject {

        public boolean AddStateObserver(IStateObserver observer) {
            return false;
        }

        public boolean RemoveStateObserver(IStateObserver observer) {
            return false;
        }

        public void UpdateStateObservers(Location location, LocationState locationState, String reason) {
        }
    }    
}
