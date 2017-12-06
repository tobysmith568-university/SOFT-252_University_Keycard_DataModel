/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Locations;

import Listeners.IObserver;
import Listeners.ISubject;
import Locations.States.EmergencyStatus;
import Locations.States.IRoomState;


/**
 *
 * @author tsmith10
 */
public abstract class Location implements ISubject, IObserver {
    private IRoomState currentState;
    
    public abstract void SetRoomState(EmergencyStatus newState);
}
