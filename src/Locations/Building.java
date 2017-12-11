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
public class Building extends Location{
    private Campus campus;
    private String name;
    private String shortCode;
    private HashMap<String, Floor> floors = new HashMap<>();
    
    public Building(String name, String shortCode){
        this.name = this.fullName = name;
        this.shortCode = shortCode;
    }

    public String GetName() {
        return name;
    }
    
    @Override
    public Floor GetChild(String name){
        if (!floors.containsKey(name))
            return null;
        else{
            return floors.get(name);
        }
    }

    public Campus GetCampus() {
        return campus;
    }

    @Override
    protected void ActualSetRoomState(LocationState newState) {
        super.ActualSetRoomState(newState);
        Iterator iterator = floors.values().iterator();
        while (iterator.hasNext()){
            ((Floor)iterator.next()).ActualSetRoomState(newState);
        }
    }
    
    public Floor AddFloor(){
        Floor floor = new Floor(Integer.toString(floors.size()));
        floor.SetFullName(this.fullName + " " + floor.GetFloorNumber());
        floors.put(floor.GetFloorNumber(), floor);
        return floor;
    }
}
