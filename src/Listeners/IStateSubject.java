/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Listeners;

import Locations.Location;
import Locations.States.LocationState;

/**
 * The subject in an observer pattern which keeps track of the emergency state
 * of <code>Location</code> objects and reports their state to any observing
 * objects.
 * @author Student
 */
public interface IStateSubject {

    /**
     * Adds a new <code>IStateObserver</code> to the object.
     * @param observer The observer to be added
     * @return If the observer was successfully added
     */
    public boolean AddStateObserver(IStateObserver observer);

    /**
     * Removes a given <code>IStateObserver</code> from the object.
     * @param observer The observer to be removed
     * @return If the observer was successfully removed from the object
     */
    public boolean RemoveStateObserver(IStateObserver observer);

    /**
     * Alerts all of <code>IStateObserver</code>s of this object that there has
     * been a state change.
     * @param location The <code>Location</code> which has changed
     * @param locationState The new state of the <code>Location</code>
     * @param reason The reason for the state being changed
     */
    public void UpdateStateObservers(Location location, LocationState locationState, String reason);
}