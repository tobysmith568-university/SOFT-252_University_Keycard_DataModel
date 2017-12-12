/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Listeners;

import Locations.Location;
import Locations.States.LocationState;

/**
 * The observer in an observer pattern which keeps track of the emergency state
 * of <code>Location</code> objects and reports their state to any observing
 * objects.
 * @author Student
 */
public interface IStateObserver{

    /**
     * Called by the subject of the observer pattern when its emergency state is
     * changed.
     * @param location The <code>Location</code> which has changed
     * @param locationState The new state of the <code>Location</code>
     */
    public void ObservedStateUpdate(Location location, LocationState locationState);
    }
