/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Locations;

import Control.Log;
import Listeners.IStateSubject;
import Locations.States.ILocationState;
import Locations.States.LocationState;
import java.util.ArrayList;
import Listeners.IStateObserver;


/**
 *
 * @author tsmith10
 */
public abstract class Location implements IStateSubject{
    protected ArrayList<IStateObserver> stateObservers = new ArrayList<>();    
    protected String fullName;    
    protected LocationState state;
    protected ILocationState iState;
    
    private static IStateObserver log = Log.Logger();
    
    protected Location(){
        AddStateObserver(log);
    }
    
    public void SetRoomState(LocationState newState){
        ActualSetRoomState(newState);
        UpdateStateObservers(this, state);
    }
    
    protected void ActualSetRoomState(LocationState newState){
        state = newState;
        iState = state.GetLocationState();
    }
    
    public String GetFullName(){
        return fullName;
    }
    
    public LocationState GetState(){
        return state;
    }
    
    public void SetFullName(String name){
        this.fullName = name;
    }

    @Override
    public final boolean AddStateObserver(IStateObserver observer) {
        if (stateObservers.contains(observer))
            return false;
        else {
            stateObservers.add(observer);
            return stateObservers.contains(observer);
        }
    }    

    @Override
    public final boolean RemoveStateObserver(IStateObserver observer) {
        return stateObservers.remove(observer);
    }

    @Override
    public final void UpdateStateObservers(Location location, LocationState locationState) {
        stateObservers.forEach((observer) -> {
            observer.ObservedStateUpdate(location, locationState);
        });
    }
}
