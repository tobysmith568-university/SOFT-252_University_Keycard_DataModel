/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import People.Keycard;
import People.Role;
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
public class KeycardFactoryTest {
    
    public KeycardFactoryTest() {
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
    public void testCreate_RoleArr_String() {
        System.out.println("Testing Create() (Role Array)");
        
        Keycard k1 = KeycardFactory.Create(new Role[] { CLEANER }, "test name 1");
        Keycard k2 = KeycardFactory.Create(new Role[] { CLEANER, STUDENT }, "test name 2");
        
        assertEquals("test name 1", k1.GetName());
        assertArrayEquals(new Role[] { CLEANER }, k1.GetRoles());
        
        assertEquals("test name 2", k2.GetName());
        assertArrayEquals(new Role[] { CLEANER, STUDENT }, k2.GetRoles());
    }

    @Test
    public void testCreate_Role_String() {
        System.out.println("Testing Create() (Single Role)");
        
        Keycard k1 = KeycardFactory.Create(CLEANER, "test name 1");
        Keycard k2 = KeycardFactory.Create(STUDENT, "test name 2");
        
        assertEquals("test name 1", k1.GetName());
        assertArrayEquals(new Role[] { CLEANER }, k1.GetRoles());
        
        assertEquals("test name 2", k2.GetName());
        assertArrayEquals(new Role[] { STUDENT }, k2.GetRoles());
    }
    
}
