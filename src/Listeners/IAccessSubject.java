/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Listeners;

import Locations.Room;
import People.Keycard;

/**
 * The subject in an observer pattern which keeps track of the access requests
 * of <code>Room</code> objects and reports the access requests to any observing
 * objects.
 * @author Student
 */
public interface IAccessSubject{

    /**
     * Adds a new <code>IAccessObserver</code> to the object.
     * @param observer The observer to be added
     * @return If the observer was successfully added
     */
    public boolean AddAccessObserver(IAccessObserver observer);

    /**
     * Removes a given <code>IAccessObserver</code> from the object.
     * @param observer The observer to be removed
     * @return If the observer was successfully removed from the object
     */
    public boolean RemoveAccessObserver(IAccessObserver observer);

    /**
     * Alerts all of <code>IAccessObserver</code>s of this object that there has
     * been an access request
     * @param keycard The <code>Keycard</code> making the access request
     * @param room The room the <code>Keycard</code> tried to access
     * @param wasSuccessful If access was allowed to the <code>Room</code> or not
     */
    public void UpdateAccessObservers(Keycard keycard, Room room, boolean wasSuccessful);
}
