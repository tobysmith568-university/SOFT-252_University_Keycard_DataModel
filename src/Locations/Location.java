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
import static Locations.States.LocationState.NOEMERGENCY;
import java.io.Serializable;


/**
 * A place in the hierarchy of locations. This contains all the basic functionality
 * shared by all locations: campuses, buildings, floors, and rooms. Functionality
 * includes getting their data and updating their states
 * @author Student
 */
public abstract class Location implements IStateSubject, Serializable{

    /**
     * The collection of objects which are observing the state of this object
     */
    protected transient ArrayList<IStateObserver> stateObservers;

    /**
     * The full name of this <code>Location</code>, this will include the names
     * of it's parent <code>Location</code> objects too.
     */
    protected String fullName;    

    /**
     * The current state of this <code>Location</code>
     */
    protected LocationState state;

    /**
     * The class of functionality retrieved from the <code>LocationState state</code>
     * in this object.
     */
    protected ILocationState iState;   
    
    /**
     * If this object is in a 'mixed state'. This results from it's child
     * <code>Location</code> objects have different states to each other. For
     * example if one child is in an emergency state but another is in a normal
     * state then this should have a mixed state.
     * 
     * A mixed state has no effect on functionality and this object still uses
     * it's <code>state</code> and <code>istate</code> as before but this should
     * effect how this <code>Location</code> should display graphically.
     */
    protected boolean isMixedState;
    
    private static final IStateObserver log = Log.Logger();
    
    /**
     * Creates a new <code>Location</code> and adds the <code>Logger</code> as
     * an observer of it's state.
     */
    protected Location(){
        stateObservers = new ArrayList<>();
        state = NOEMERGENCY;
        iState = state.GetLocationState();
        AddStateObserver(log);
    }

    /**
     * Returns if this object is in a mixed state or not.
     * @return If this is in a mixed state
     */
    public boolean GetIsMixedState() {
        return isMixedState;
    }

    /**
     * Sets if this object is in a mixed state.
     * @param isMixedState <code>True</code> if this should be in a mixed state
     */
    public void SetIsMixedState(boolean isMixedState) {
        this.isMixedState = isMixedState;
    }
    
    /**
     * Changes the current state of the <code>Location</code> and sends the
     * change to this objects observers.
     * @param newState The new state the <code>Location</code> is set to
     */
    public void SetRoomState(LocationState newState){
        ActualSetRoomState(newState);
        UpdateStateObservers(this, state);
    }
    
    /**
     * Called by the <code>SetRoomState()</code>. This contains the actual
     * functionality for changing the <code>Location</code>s state.<p>The reason
     * this is separated out into a separate <code>protected</code> method
     * rather than doing in in the <code>public</code> one is so that parent and
     * child <code>Location</code>s can call the protected one without it going
     * to the <code>Logger</code>.
     * @param newState The new state the <code>Location</code> is set to
     */
    protected void ActualSetRoomState(LocationState newState){
        state = newState;
        iState = state.GetLocationState();
        isMixedState = false;
    }
    
    /**
     * Gets the full name of this <code>Location</code>, this will include the
     * names of it's parent <code>Location</code> objects too.
     * @return The full name of this <code>Location</code>
     */
    public String GetFullName(){
        return fullName;
    }
    
    /**
     * Get the current state of this <code>Location</code>.
     * @return The current state of this <code>Location</code>
     */
    public LocationState GetState(){
        return state;
    }
    
    /**
     * Sets the full name of this <code>Location</code>, this should include the
     * names of it's parent <code>Location</code> objects too.
     * @param name What the full name will be set to
     */
    public void SetFullName(String name){
        this.fullName = name;
    }

    /**
     * Adds a new <code>IStateObserver</code> to the object. This observes
     * changes in the <code>Location</code>s emergency state.
     * @param observer The observer to be added
     * @return If the observer was successfully added
     */
    @Override
    public final boolean AddStateObserver(IStateObserver observer) {
        if (stateObservers == null) stateObservers = new ArrayList<>();
        if (stateObservers.contains(observer))
            return false;
        else {
            stateObservers.add(0, observer);
            return stateObservers.contains(observer);
        }
    }    

    /**
     * Removes a given <code>IStateObserver</code> from the object.
     * @param observer The observer to be removed
     * @return If the observer was successfully removed from the object
     */
    @Override
    public final boolean RemoveStateObserver(IStateObserver observer) {
        return stateObservers.remove(observer);
    }

    /**
     * Alerts all of <code>IStateObserver</code>s of this object that there has
     * been a state change.
     * @param location The <code>Location</code> which has changed
     * @param locationState The new state of the <code>Location</code>
     */
    @Override
    public final void UpdateStateObservers(Location location, LocationState locationState) {
        stateObservers
                .forEach((observer) -> {
                    observer.ObservedStateUpdate(location, locationState);
        });
    }
}
