/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Locations;

import Locations.States.LocationState;
import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author tsmith10
 */
public class Campus extends Location{
    private String name;
    private HashMap<String, Building> buildings = new HashMap<>();
    
    public Campus(String name){
        this.name = name;
        this.fullName = name;
    }

    public String GetName() {
        return name;
    }

    @Override
    public void SetRoomState(LocationState newState) {
        Iterator iterator = buildings.entrySet().iterator();
        while (iterator.hasNext()){
            ((Building)iterator.next()).SetRoomState(newState);
        }
    }
    
    public Building AddBuilding(String name, String shortCode){
        Building building = new Building(name, shortCode);
        buildings.put(name, building);
        building.SetFullName(this.name + " " + shortCode);
        return building;
    }
}
