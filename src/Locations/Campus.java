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
        super();
        this.name = this.fullName = name;
    }

    public String GetName() {
        return name;
    }
    
    @Override
    protected void ActualSetRoomState(LocationState newState){
        super.ActualSetRoomState(newState);
        Iterator iterator = buildings.values().iterator();
        while (iterator.hasNext()){
            ((Building)iterator.next()).ActualSetRoomState(newState);
        }
        
    }
    
    public Building AddBuilding(String name, String shortCode){
        Building building = new Building(name, shortCode);
        building.SetFullName(this.name + " " + shortCode);
        return building;
    }
}
