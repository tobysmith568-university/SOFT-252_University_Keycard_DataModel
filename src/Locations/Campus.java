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
public class Campus extends Location{
    private String name;
    private ArrayList<Building> buildings = new ArrayList<Building>();
    
    public Campus(String name){
        this.name = name;
    }
    
    public void CreateBuilding(String name, String shortCode){
        buildings.add(new Building(name, shortCode));
    }
}
