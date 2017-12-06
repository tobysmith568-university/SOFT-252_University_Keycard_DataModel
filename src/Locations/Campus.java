/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Locations;

import Listeners.IObserver;
import Locations.States.EmergencyStatus;
import Locations.States.IRoomState;
import java.util.ArrayList;

/**
 *
 * @author tsmith10
 */
public class Campus extends Location{
    private String name;
    private ArrayList<Building> buildings = new ArrayList<Building>();
    
    public Campus(String name){
        this.name = name;
    }
    
    public void CreateBuilding(String name, String shortCode){
        buildings.add(new Building(name, shortCode));
    }

    @Override
    public void SetRoomState(EmergencyStatus newState) {
        for (int i = 0; i < buildings.size(); i++) {
            buildings.get(i).SetRoomState(newState);
        }
    }

    @Override
    public void UpdateObservers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void RegisterObserver(IObserver observer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void RemoveObserver(IObserver observer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ObservedUpdate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
