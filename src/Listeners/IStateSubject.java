/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Listeners;

import Locations.Location;
import Locations.States.LocationState;

/**
 *
 * @author Toby
 */
public interface IStateSubject {
    public boolean AddStateObserver(IStateObserver observer);
    public boolean RemoveStateObserver(IStateObserver observer);
    public void UpdateStateObservers(Location location, LocationState locationState);
}
