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

/**
 * Main method / entry point
 * @author Student
 */
public class UniversityKeycards {

    private static ArrayList<Campus> AddChildren(){
        ArrayList<Campus> campuses = new ArrayList<>();
        
        campuses.add(new Campus("Main Campus"));
        
        Campus campus1 = campuses.get(0);
        campus1.AddBuilding("Babbage", "BGB");
        
        Building building1 = campus1.GetChild("Babbage");
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
        
        return campuses;
    }
    private static ArrayList<Campus> LoadChildren(){
        ArrayList<Campus> campuses = LoadState();
        
        
        return campuses;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {     
        
        //ArrayList<Campus> campuses = AddChildren();
        ArrayList<Campus> campuses = LoadChildren();
        
        
        Campus campus1 = campuses.get(0);        
        Building building1 = campus1.GetChild("Babbage");                
        Floor floor0 = building1.GetChild("0");        
        Floor floor1 = building1.GetChild("1");
        
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

    /**
     * Takes an <code>ArrayList</code> of <code>Campus</code> objects and saves
     * their states to a file called <code>Current.state</code>. The file's
     * location is the same is a executables. 
     * @param campuses The <code>Campus</code>s to be saved to the file
     * @return If the states of the objects were successfully saved to the file
     */
    public static boolean SaveState(ArrayList<Campus> campuses){
        File objFile = new File("Current.state");
        
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
    
    /**
     * Loads a state of an ArrayList of Campus objects.
     * 
     * Looks for a file called <code>Current.state</code> in the same directory
     * as the executable and tries to parse it's data to an <code>ArrayList</code>
     * of <code>Campus</code> objects. If this is successful it assigns the
     * <code>Logger</code> as an state observer to each <code>Location</code>
     * and as an access observer to each <code>Room</code> contained within them.
     * @return The <code>ArrayList</code> of <code>Campus</code> objects
     */
    public static ArrayList<Campus> LoadState(){
        File objFile = new File("Current.state");
        if(!objFile.exists() || !objFile.canRead())
            Log.Log("ERROR: Problem accessing file");
            
        try(ObjectInputStream objIn = new ObjectInputStream(
                new BufferedInputStream(
                new FileInputStream(objFile))))
        {
            Object data = objIn.readObject();
            ArrayList<Campus> newCampuses = (ArrayList<Campus>)data;
            
            if(newCampuses == null){
                Log.Log("Error: Problem reading file");
            } else {
                newCampuses.forEach((campus) -> {
                    AssignObserver(campus);
                });
            }
            
            Log.Log("All Locations read from file.");
            return newCampuses;
        }
        catch(ClassNotFoundException | IOException | ClassCastException ex)
        {
            Log.Log("ERROR: " + ex.getMessage());
            return null;
        }
    }
    
    private static void AssignObserver(Location location){
        Log logger = Log.Logger();
        
        if (location != null){
            location.AddStateObserver(logger);
            
            if (location instanceof Room)
                ((Room) location).AddAccessObserver(logger);
            
            for (Location child : location.GetAllChildren()) {
                    AssignObserver(child);
            }            
        }
    }
}
