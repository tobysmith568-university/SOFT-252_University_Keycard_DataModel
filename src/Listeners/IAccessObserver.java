/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Listeners;

import Locations.Room;
import People.Keycard;

/**
 *
 * @author Toby
 */
public interface IAccessObserver{

    /**
     *
     * @param keycard
     * @param room
     * @param wasSuccessful
     */
    public void ObservedAccessUpdate(Keycard keycard, Room room, boolean wasSuccessful);
}
