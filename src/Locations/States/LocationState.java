/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Locations.States;

/**
 *
 * @author tsmith10
 */
public enum LocationState {
    NOEMERGENCY,
    EMERGENCY;
    
    public ILocationState GetLocationState(){
        switch (this){
            case EMERGENCY:
                return new EmergencyState();
            default:
                return new NormalState();
        }
    }
    
    public String GetName(){
        switch (this){
            case NOEMERGENCY:
                return "No Emergency";
            case EMERGENCY:
                return "Emergency";
            default:
                return this.toString();
        }
    }
}
