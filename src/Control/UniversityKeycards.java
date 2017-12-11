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
import static Locations.States.LocationState.*;
import People.Keycard;
import static People.Role.*;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author tsmith10
 */
public class UniversityKeycards {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        ArrayList<Campus> campuses = new ArrayList<>();
        
        campuses.add(new Campus("Main Campus"));
        
        Campus campus1 = campuses.get(0);
        campus1.AddBuilding("Babbage", "BGB");
        
        Building building1 = campus1.GetChild("Babbage");
        building1.AddFloor();
                
        Floor floor0 = building1.GetChild("0");
        floor0.AddRoom(STUDENTLAB);
        floor0.AddRoom(STUDENTLAB);
        floor0.AddRoom(STAFFROOM);
        floor0.AddRoom(SECUREROOM);
        floor0.AddRoom(STUDENTLAB);
        
        Floor floor1 = building1.GetChild("1");
        floor1.AddRoom(STUDENTLAB);
        floor1.AddRoom(STUDENTLAB);
        floor1.AddRoom(STUDENTLAB);
        floor1.AddRoom(SECUREROOM);
        floor1.AddRoom(RESEARCHLAB);
        
        Keycard card = new Keycard(STUDENT, "Dave", "0000001");
        Keycard card2 = new Keycard(EMERGENCYRESPONDER, "Fireman", "0000002");
        
        Room room1 = floor1.GetChild("01");
        Room room2 = floor1.GetChild("02");
        
        room1.AccessRequest(card);
        room1.AccessRequest(card2);     
        room2.AccessRequest(card);
        room2.AccessRequest(card2);
        building1.SetRoomState(EMERGENCY);        
        room1.AccessRequest(card);
        room1.AccessRequest(card2);        
        room2.AccessRequest(card);
        room2.AccessRequest(card2);
        room1.SetRoomState(NOEMERGENCY);        
        room1.AccessRequest(card);
        room1.AccessRequest(card2);     
        room2.AccessRequest(card);
        room2.AccessRequest(card2);
        
        SaveState(campuses);
    }

    public static boolean SaveState(ArrayList<Campus> campuses){
        File objFile = new File("STATE");
        
        try (ObjectOutputStream objOut = new ObjectOutputStream(
                                            new BufferedOutputStream(
                                            new FileOutputStream(objFile)))){
            objOut.writeObject(campuses);
            Log.Log("All Locations written to file.");
        } catch (IOException ex) {
            Log.Log("ERROR: " + ex.getMessage());
            return false;
        }
        return true;
    }
}
