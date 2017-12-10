/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Locations.States;

import People.Keycard;
import People.Role;

/**
 *
 * @author tsmith10
 */
public class NormalState implements ILocationState {

    @Override
    public boolean AccessRequest(Keycard keycard) {
        Role role = keycard.GetRole();
        
        switch (role){
            case VISITOR:
            case STAFFMEMBER:
            case STUDENT:
            case CLEANER:
            case MANAGER:
            case SECURITY:
                    return true;
            default:
                return false;
        }
        
    }
    
}
