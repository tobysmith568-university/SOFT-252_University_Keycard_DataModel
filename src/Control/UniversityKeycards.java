/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Locations.Building;
import Locations.Campus;
import Locations.Floor;
import Locations.Location;
import Locations.ParentLocation;
import Locations.Room;
import static Locations.RoomType.*;
import static Locations.States.LocationState.*;
import People.Keycard;
import static People.Role.*;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Main method / entry point
 * @author Student
 */
public class UniversityKeycards {
    
    /**
     *
     */
    public static HashMap<String, Keycard> allKeycards = new HashMap<String, Keycard>();

    /**
     *
     */
    public static ArrayList<Campus> allCampuses;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {     
        
        Save save = Save.LoadState("Current.state");
        if (save != null){
            allCampuses = save.campuses;
            allKeycards = save.keycards;
        }
        else
            SetDefaultState();  
        
        Campus campus1 = allCampuses.get(0);        
        Building building1 = campus1.GetChild("Building 1");
        Floor floor1 = building1.GetChild("1");
        
        Keycard card = (Keycard)allKeycards.values().toArray()[1];
        Keycard card2 = (Keycard)allKeycards.values().toArray()[0];     
        
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
        
        Save.SaveState("Current.state", allCampuses, allKeycards);
    }

    private static void SetDefaultState(){
        allCampuses = new ArrayList<>();
        
        allCampuses.add(new Campus("Main Campus"));
        
        Campus campus1 = allCampuses.get(0);
        campus1.AddBuilding("Building 1", "ONE");
        
        Building building1 = campus1.GetChild("Building 1");
        building1.AddFloor();
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
        
        KeycardFactory.Create(STUDENT, "Dave");
        KeycardFactory.Create(EMERGENCYRESPONDER, "Fireman");
    }
}
