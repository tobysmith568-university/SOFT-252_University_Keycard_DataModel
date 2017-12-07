/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Locations;

import Locations.RoomTypes.*;

/**
 *
 * @author tsmith10
 */
public enum RoomType {
    LECTUREHALL,
    STUDENTLAB,
    RESEARCHLAB,
    STAFFROOM,
    SECUREROOM;    
    
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
