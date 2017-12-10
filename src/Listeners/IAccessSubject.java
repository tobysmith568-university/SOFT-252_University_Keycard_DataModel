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
public interface IAccessSubject extends ISubject{
    public void UpdateObservers(Keycard keycard, Room room, boolean wasSuccessful);
}
