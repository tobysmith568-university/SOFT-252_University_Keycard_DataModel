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
public interface IAccessSubject{

    /**
     *
     * @param observer
     * @return
     */
    public boolean AddAccessObserver(IAccessObserver observer);

    /**
     *
     * @param observer
     * @return
     */
    public boolean RemoveAccessObserver(IAccessObserver observer);

    /**
     *
     * @param keycard
     * @param room
     * @param wasSuccessful
     */
    public void UpdateAccessObservers(Keycard keycard, Room room, boolean wasSuccessful);
}
