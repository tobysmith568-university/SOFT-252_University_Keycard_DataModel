/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package People;

import java.time.LocalTime;

/**
 * Defines the type of access a <code>Keycard</code> can have. This is based off
 * of the personal or professional role the owner of the <code>Keycard</code>
 * has within the university.
 * @author Student
 */
public enum Role {

    /**
     * All non-staff or student users.
     * In “normal mode” visitors have time restricted access to lecture halls
     * only. Visitors can access these rooms between 08:30 and 22:00. In
     * “emergency mode” they have no access to any room.
     */
    VISITOR,

    /**
     * A member of the teaching staff.
     * In “normal mode” staff have time restricted access to lecture halls,
     * student labs, research labs and staff rooms. Staff members can access
     * these rooms between 05:30 and 23:59:59. In “emergency mode” they have no
     * access to any room.
     */
    STAFFMEMBER,

    /**
     * A student enrolled on a course at the university.
     * In “normal mode” students have time restricted access to lecture halls
     * and student labs. Students can only access these rooms between 08:30 and
     * 22:00. In “emergency mode” they have no access to any room.
     */
    STUDENT,

    /**
     * A cleaner who visits the university at certain times.
     * In “normal mode” cleaners have time restricted access to all rooms EXCEPT
     * for secured rooms which they cannot access. Cleaners access is restricted
     * to the following time periods:<ul><li>Morning – 05:30 to 10:30</li><li>
     * Evening – 17:30 to 22:30</li></ul>In “emergency mode” cleaners cannot
     * access any room at any time.
     */
    CLEANER,

    /**
     * A University management team member with extra access privileges. A
     * manager can access any room at any time UNLESS the system is in
     * “emergency mode” when they have no access to any room.
     */
    MANAGER,

    /**
     * A member of the university security team.
     * Security staff can access any room at any time regardless of the systems
     * operating mode.
     */
    SECURITY,

    /**
     * An emergency “first responder” – police, fire and ambulance staff. This
     * type of user can access any room when it is in “emergency mode”. In 
     * “normal mode” they have no access to any room.
     */
    EMERGENCYRESPONDER;
    
    /**
     * Determines if the <code>Role</code> currently has access to <code>Room</code>s.
     * This is determined according to it's own time restrictions.
     * @return If the <code>Role</code> does currently has time access to it's
     * allowed <code>Room</code>s
     */
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
    
    public String GetName(){
        switch (this){
            case VISITOR:
                return "Visitor";
            case STUDENT:
                return "Student";
            case STAFFMEMBER:
                return "Staff Member";
            case CLEANER:
                return "Cleaner";
            case MANAGER:
                return "Manager";
            case SECURITY:
                return "Security";
            case EMERGENCYRESPONDER:
                return "Emergency Responder";
            default:
                return "Unnamed role!";
        }
    }
    
    public String GetPluralName(){
        switch (this){
            case VISITOR:
                return "Visitors";
            case STUDENT:
                return "Students";
            case STAFFMEMBER:
                return "Staff Members";
            case CLEANER:
                return "Cleaners";
            case MANAGER:
                return "Managers";
            case SECURITY:
                return "Security";
            case EMERGENCYRESPONDER:
                return "Emergency Responders";
            default:
                return "Unnamed role!";
        }
    }
    
    private boolean IsNowBetween(int startHours, int startMinutes, int startSeconds, int endHours, int endMinutes, int endSeconds){
        return LocalTime.now().compareTo(LocalTime.of(startHours, startMinutes, startSeconds)) >= 0 &&
               LocalTime.now().compareTo(LocalTime.of(endHours,   endMinutes,   endSeconds  )) < 0;
    }
}
