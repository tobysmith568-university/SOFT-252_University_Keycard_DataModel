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
    
    /**
     *
     * @param name
     */
    public Campus(String name){
        super();
        this.name = this.fullName = name;
    }

    /**
     *
     * @return
     */
    public String GetName() {
        return name;
    }
    
    /**
     *
     * @param name
     * @return
     */
    @Override
    public Building GetChild(String name){
        if (!buildings.containsKey(name))
            return null;
        else
            return buildings.get(name);
    }
    
    /**
     *
     * @return
     */
    @Override
    public Building[] GetAllChildren(){
        return buildings.values().toArray(new Building[0]);
    }
    
    /**
     *
     * @param newState
     */
    @Override
    protected void ActualSetRoomState(LocationState newState){
        super.ActualSetRoomState(newState);
        Iterator iterator = buildings.values().iterator();
        while (iterator.hasNext()){
            ((Building)iterator.next()).ActualSetRoomState(newState);
        }
        
    }
    
    /**
     *
     * @param name
     * @param shortCode
     * @return
     */
    public Building AddBuilding(String name, String shortCode){
        Building building = new Building(name, shortCode);
        building.SetFullName(this.name + " " + shortCode);
        buildings.put(building.GetName(), building);
        return building;
    }
}
