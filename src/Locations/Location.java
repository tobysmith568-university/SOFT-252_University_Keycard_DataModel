/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Locations;

import Listeners.IObserver;
import Listeners.IStateSubject;
import Locations.States.LocationState;


/**
 *
 * @author tsmith10
 */
public abstract class Location implements IStateSubject{
    
    protected String fullName;
        
    protected abstract void SetRoomState(LocationState newState);
    
    public abstract String GetFullName();
    
    public void SetFullName(String name){
        this.fullName = name;
    }

    @Override
    public void UpdateObservers(Location location, LocationState locationState) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean RegisterObserver(IObserver observer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean RemoveObserver(IObserver observer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
