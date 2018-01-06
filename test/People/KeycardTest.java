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
 * @author tsmith10
 */
public class KeycardTest {
    
    private Keycard keycard1;
    private Keycard keycard2;
    private Keycard keycard3;
    
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
        keycard1 = new Keycard(new Role[] { CLEANER }, "Mike", "0004");
        keycard2 = new Keycard(new Role[] { STUDENT }, "Dave", "0005");
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testGetRole() {
        System.out.println("Testing GetRole()");
        assertEquals(CLEANER, keycard1.GetRoles()[0]);  
        assertEquals(STUDENT, keycard2.GetRoles()[0]);  
        assertNull(keycard3.GetRoles());
    }

    @Test
    public void testGetName() {
        System.out.println("Testing GetName()");
        assertEquals("Mike", keycard1.GetName());  
        assertEquals("Dave", keycard2.GetName());  
        assertEquals("", keycard3.GetName()); 
    }

    @Test
    public void testGetCardID() {
        System.out.println("Testing GetCardID()");
        assertEquals("0004", keycard1.GetCardID());  
        assertEquals("0005", keycard2.GetCardID());  
        assertEquals("", keycard3.GetCardID());  
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
