/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package People;

import static People.Role.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
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
        System.out.println("Testing GetRoles()");
        k1 = new Keycard(new Role[] { CLEANER }, "Mike", "0004");
        k2 = new Keycard(new Role[] { STUDENT, SECURITY }, "Dave", "0005");
        
        Assert.assertArrayEquals(new Role[] { CLEANER }, k1.GetRoles());  
        Assert.assertArrayEquals(new Role[] { STUDENT, SECURITY }, k2.GetRoles());
    }

    @Test
    public void testGetRolesString() {
        System.out.println("Testing GetRolesString()");
        k1 = new Keycard(new Role[] { CLEANER }, "Mike", "0004");
        k2 = new Keycard(new Role[] { STUDENT, SECURITY }, "Dave", "0005");
        
        assertEquals("Cleaner", k1.GetRolesString(" / "));
        assertEquals("Student / Security", k2.GetRolesString(" / "));
        assertEquals("Student test Security", k2.GetRolesString(" test "));
    }

    @Test
    public void testAddRole() {
        System.out.println("Testing GetAddRole()");
        k1 = new Keycard(new Role[] { CLEANER }, "Mike", "0004");
        k2 = new Keycard(new Role[] { STUDENT }, "Dave", "0005");
        
        k1.AddRole(SECURITY);
        k2.AddRole(VISITOR);
        k2.AddRole(SECURITY);
        k2.AddRole(SECURITY);
        
        assertEquals(2, k1.GetRoles().length);
        assertEquals(3, k2.GetRoles().length);
    }

    @Test
    public void testRemoveRole() {
        System.out.println("Testing GetRemoveRole()");
        k1 = new Keycard(new Role[] { CLEANER }, "Mike", "0004");
        k2 = new Keycard(new Role[] { STUDENT }, "Dave", "0005");
        
        k1.AddRole(SECURITY);
        k2.AddRole(VISITOR);
        
        k1.RemoveRole(SECURITY);
        k2.RemoveRole(STUDENT);
        k2.RemoveRole(VISITOR);
        
        assertEquals(1, k1.GetRoles().length);
        assertEquals(0, k2.GetRoles().length);
    }

    @Test
    public void testSetName() {
        System.out.println("Testing GetSetName()");
        k1 = new Keycard(new Role[] { CLEANER }, "Mike", "0004");
        k2 = new Keycard(new Role[] { STUDENT }, "Dave", "0005");
        
        k1.SetName("Test name");
        k2.SetName(null);
        
        assertEquals("Test name", k1.GetName());
        assertNull(k2.GetName());
    }
}
