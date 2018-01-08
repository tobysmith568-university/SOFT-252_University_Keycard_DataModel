/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package People;

import static People.Role.*;
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
public class KeycardTest {
    
    private Keycard k1;
    private Keycard k2;
    
    public KeycardTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        k1 = new Keycard(new Role[] { CLEANER }, "Mike", "0004");
        k2 = new Keycard(new Role[] { STUDENT }, "Dave", "0005");
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testGetRole() {
        System.out.println("Testing GetRole()");
        k1 = new Keycard(new Role[] { CLEANER }, "Mike", "0004");
        k2 = new Keycard(new Role[] { STUDENT }, "Dave", "0005");
        
        assertEquals(CLEANER, k1.GetRoles()[0]);  
        assertEquals(STUDENT, k2.GetRoles()[0]); 
    }

    @Test
    public void testGetName() {
        System.out.println("Testing GetName()");
        k1 = new Keycard(new Role[] { CLEANER }, "Mike", "0004");
        k2 = new Keycard(new Role[] { STUDENT }, "Dave", "0005");
        
        assertEquals("Mike", k1.GetName());  
        assertEquals("Dave", k2.GetName());
    }

    @Test
    public void testGetCardID() {
        System.out.println("Testing GetCardID()");
        k1 = new Keycard(new Role[] { CLEANER }, "Mike", "0004");
        k2 = new Keycard(new Role[] { STUDENT }, "Dave", "0005");
        
        assertEquals("0004", k1.GetCardID());  
        assertEquals("0005", k2.GetCardID());
    }

    @Test
    public void testGetRoles() {
    }

    @Test
    public void testGetRolesString() {
    }

    @Test
    public void testAddRole() {
    }

    @Test
    public void testRemoveRole() {
    }

    @Test
    public void testSetName() {
    }
    
}
