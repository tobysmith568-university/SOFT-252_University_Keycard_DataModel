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
public class Floor {
    private String floorNumber;
    private ArrayList<Room> rooms = new ArrayList<Room>();
    
    public Floor(String floorNumber){
        this.floorNumber = floorNumber;
    }
    
    
}
