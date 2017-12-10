/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Locations.Building;
import Locations.Campus;
import Locations.Floor;
import Locations.Room;
import static Locations.RoomType.*;
import People.Keycard;
import static People.Role.*;

/**
 *
 * @author tsmith10
 */
public class UniversityKeycards {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Campus campus1 = new Campus("Main Campus");
        Building building1 = campus1.AddBuilding("Babbage", "BBG");
        
        Floor floor0 = building1.AddFloor();
        floor0.AddRoom(STUDENTLAB);
        floor0.AddRoom(STUDENTLAB);
        floor0.AddRoom(STAFFROOM);
        floor0.AddRoom(SECUREROOM);
        floor0.AddRoom(STUDENTLAB);
        
        Floor floor1 = building1.AddFloor();
        floor1.AddRoom(STUDENTLAB);
        Room subject = floor1.AddRoom(STUDENTLAB);
        floor1.AddRoom(STUDENTLAB);
        floor1.AddRoom(SECUREROOM);
        floor1.AddRoom(RESEARCHLAB);
        
        Keycard card = new Keycard(STUDENT, "Dave", "0000001");
        
        subject.AccessRequest(card);
    }    
}
