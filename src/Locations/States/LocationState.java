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
     * The default state for a room. Designed to allow the normal use of a room
     * and deny access to emergency responders.
     */
    NOEMERGENCY,

    /**
     * A non-default state for a room. Designed to allow the emergency access
     * use of a room only. This means denying access to a majority of people but
     * allows access to emergency responders and other specific university
     * <code>Roles</code>s.
     */
    EMERGENCY;
    
    /**
     * This returns the specific state and the functionality associated with 
     * the given enum value.
     * @return The state class
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
     * Returns a <code>Log</code> and user-friendly version of this state's
     * name.
     * @return The name of this state
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
