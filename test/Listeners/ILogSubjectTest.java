/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Listeners;

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
public class ILogSubjectTest {
    
    public ILogSubjectTest() {
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
    public void testAddLogObserver() {
    }

    @Test
    public void testRemoveLogObserver() {
    }

    @Test
    public void testUpdateLogObservers() {
    }

    public class ILogSubjectImpl implements ILogSubject {

        public boolean AddLogObserver(ILogObserver observer) {
            return false;
        }

        public boolean RemoveLogObserver(ILogObserver observer) {
            return false;
        }

        public void UpdateLogObservers(String message) {
        }
    }
    
}
