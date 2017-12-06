/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Locations;

import java.util.ArrayList;

/**
 *
 * @author tsmith10
 */
class Building extends Location{
    private String name;
    private String shortCode;
    private ArrayList<Floor> floors = new ArrayList<Floor>();
    
    public Building(String name, String shortCode){
        this.name = name;
        this.shortCode = shortCode;
    }
    
    public void CreateFloor(String floorNumber){
        floors.add(new Floor(floorNumber));
    }
}
