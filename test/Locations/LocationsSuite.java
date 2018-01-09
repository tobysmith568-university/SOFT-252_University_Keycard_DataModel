/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Locations;

import Locations.RoomTypes.RoomTypesSuite;
import Locations.States.StatesSuite;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Student
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({FloorTest.class, IRoomTypeTest.class, BuildingTest.class, LocationTest.class, ParentLocationTest.class, RoomTypesSuite.class, RoomTypeTest.class, CampusTest.class, StatesSuite.class, RoomTest.class})
public class LocationsSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}
