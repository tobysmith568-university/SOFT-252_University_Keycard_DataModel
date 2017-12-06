/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Locations;

import Locations.States.IRoomState;


/**
 *
 * @author tsmith10
 */
public abstract class Location {
    private IRoomState currentState;
    
    public void SetEmergencyState(IRoomState newState){
        throw new UnsupportedOperationException();
    }
}
