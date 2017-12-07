/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package People;

import Locations.RoomType;
import static Locations.RoomType.*;
import Locations.States.EmergencyStatus;
import static Locations.States.EmergencyStatus.*;
import java.time.LocalTime;

/**
 *
 * @author tsmith10
 */
public enum Role {
    VISITOR,
    STAFFMEMBER,
    STUDENT,
    CLEANER,
    MANAGER,
    SECURITY,
    EMERGENCYRESPONDER;
    
    public boolean HasTimeAccess() {
        switch (this){
            case VISITOR:
            case STUDENT:
                return IsNowBetween(8, 30, 00, 22, 00, 00);
            case STAFFMEMBER:
                return IsNowBetween(5, 30, 00, 23, 59, 59);
            case CLEANER:
                return IsNowBetween(5, 30, 00, 10, 30, 00) ||
                       IsNowBetween(17, 30, 00, 22, 30, 00);
            case MANAGER:
            case SECURITY:
            case EMERGENCYRESPONDER:
                return true;
            default:
                return false;
        }
    }
    
    private boolean IsNowBetween(int startHours, int startMinutes, int startSeconds, int endHours, int endMinutes, int endSeconds){
        return LocalTime.now().compareTo(LocalTime.of(startHours, startMinutes, startSeconds)) >= 0 &&
               LocalTime.now().compareTo(LocalTime.of(endHours,   endMinutes,   endSeconds  )) < 0;
    }
}
