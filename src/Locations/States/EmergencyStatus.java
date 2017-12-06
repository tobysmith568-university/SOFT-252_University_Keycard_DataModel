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
public enum EmergencyStatus {
    NOEMERGENCY,
    EMERGENCY;
    
    public IRoomState getEmergencyState(){
        throw new UnsupportedOperationException();
    }
}
