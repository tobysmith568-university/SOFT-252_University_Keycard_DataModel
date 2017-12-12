/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Locations.States;

/**
 *
 * @author Student
 */
public enum LocationState {

    /**
     *
     */
    NOEMERGENCY,

    /**
     *
     */
    EMERGENCY;
    
    /**
     *
     * @return
     */
    public ILocationState GetLocationState(){
        switch (this){
            case EMERGENCY:
                return new EmergencyState();
            default:
                return new NormalState();
        }
    }
    
    /**
     *
     * @return
     */
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
