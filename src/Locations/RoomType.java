/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Locations;

import Locations.RoomTypes.*;

/**
 * This enumerator is used as a State Pattern to return different types of
 * <code>Room</code>. Each enum value can return a class with it's respective
 * functionality in.
 * @author Student
 */
public enum RoomType {

    /**
     * This room is used to deliver lectures to students
     */
    LECTUREHALL,

    /**
     * This room is used to deliver practical lessons to students.
     */
    STUDENTLAB,

    /**
     * This lab is used for the universities research projects. It is not
     * normally accessible by students.
     */
    RESEARCHLAB,

    /**
     * This room is accessible to the university staff and management teams.
     * These are lecturer’s offices and university administration rooms.
     * It is not normally accessible by students.
     */
    STAFFROOM,

    /**
     * These rooms hold sensitive or dangerous equipment or chemicals.
     * A member of the university security OR management team must grant access
     * to these rooms.
     */
    SECUREROOM;    
    
    /**
     * This returns the functionality associated with the given enum value.
     * @return The <code>Room</code> class
     */
    public IRoomType GetRoomType(){
        switch (this){
            case LECTUREHALL:
                return new LectureHall();
            case STUDENTLAB:
                return new StudentLab();
            case RESEARCHLAB:
                return new ResearchLab();
            case STAFFROOM:
                return new StaffRoom();
            default:
                return new SecureRoom();
        }
    }
}
