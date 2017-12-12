/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Listeners;

import Locations.Room;
import People.Keycard;

/**
 * The observer in an observer pattern which keeps track of the access requests
 * of <code>Room</code> objects and reports the access requests to any observing
 * objects.
 * @author Student
 */
public interface IAccessObserver{

    /**
     * Called by the subject of the observer pattern when a keycard tries to
     * gain access to it
     * @param keycard The keycard making the access request
     * @param room The room the keycard tried to access
     * @param wasSuccessful If access was allowed to the room or not
     */
    public void ObservedAccessUpdate(Keycard keycard, Room room, boolean wasSuccessful);
}
