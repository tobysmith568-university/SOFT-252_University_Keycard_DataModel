/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Locations;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author tsmith10
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({Locations.RoomTest.class, Locations.RoomTypeTest.class, Locations.IRoomTypeTest.class, Locations.CampusTest.class, Locations.LocationTest.class, Locations.RoomTypes.RoomTypesSuite.class, Locations.FloorTest.class, Locations.BuildingTest.class, Locations.States.StatesSuite.class})
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