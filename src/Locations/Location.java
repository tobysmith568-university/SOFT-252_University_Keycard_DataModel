/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Locations;

import Listeners.IObserver;
import Locations.States.EmergencyStatus;


/**
 *
 * @author tsmith10
 */
public abstract class Location implements IObserver {
    
    protected abstract void SetRoomState(EmergencyStatus newState);
    
    @Override
    public <T> void ObservedUpdate(T data){
        if (data instanceof EmergencyStatus){
            SetRoomState((EmergencyStatus)data);
        }
    }
}
