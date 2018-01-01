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
import java.io.Serializable;
import java.util.HashMap;

/**
 * An object containing an <code>ArrayList</code> of <code>Campus</code>s and a
 * <code>HashMap</code> of <code>Keycard</code>s. This is used so that both
 * can be serialised and saved together in the same file.
 * @author Student
 */
public class Data implements Serializable {
    
    /**
     * All the <code>Keycard</code> objects stored in the system
     */
    public static HashMap<String, Keycard> allKeycards = new HashMap<String, Keycard>();

    /**
     * All the <code>Campus</code> objects stored in the system
     */
    public static HashMap<String, Campus> allCampuses;
    
    private final HashMap<String, Campus> campuses;
    private final HashMap<String, Keycard> keycards;
    
    private Data(HashMap<String, Campus> campuses, HashMap<String, Keycard> keycards){        
        this.campuses = campuses;
        this.keycards = keycards;
    }

    public HashMap<String, Campus> GetCampuses() {
        return campuses;
    }

    public HashMap<String, Keycard> GetKeycards() {
        return keycards;
    }

    /**
     * Takes <code>Campus</code>s and <code>Keycard</code>s and saves their
     * states to a given file path.
     * @param path The path of where to save the states
     * @param campuses The <code>Campus</code> objects to save
     * @param keycards The <code>Keycard</code> objects to save
     * @return <code>True</code> if the states of the objects were successfully
     * saved to the file
     */
    public static boolean SaveState(String path, HashMap<String, Campus> campuses, HashMap<String, Keycard> keycards){
        File objFile = new File(path);
        
        try (ObjectOutputStream objOut = new ObjectOutputStream(
                                            new BufferedOutputStream(
                                            new FileOutputStream(objFile)))){
            objOut.writeObject(new Data(campuses, keycards));
            Log.Log("All data written to \"" + path + "\".");
        } catch (IOException ex) {
            Log.Log("ERROR: " + ex.getMessage());
            return false;
        }
        return true;
    }
    
    /**
     * Loads a states of <code>Campus</code> and <code>Keycard</code> objects
     * 
     * Looks for a file at the location given and tries to parse it's data into
     * a <code>Data</code> object containing an <code>ArrayList</code> of
     * <code>Campus</code> objects and a <code>HasMap</code> of
     * <code>Keycard</code> objects. If this is successful it recurringly
     * assigns the <code>Logger</code> as an state observer to each
     * <code>Location</code> in the <code>ArrayList&lt;Campus&gt;</code>. It also
     * add the <code>Logger</code> as an access observer to each
     * <code>Room</code> contained within the <code>Campus</code>s.
     * @param path The file location of the file to load
     * @param overwriteAll <code>True</code> if all the data in the active program
     * should be overwritten with what is being returned
     * @return The object serialised from the file
     */
    public static Data LoadState(String path, boolean overwriteAll){
        Data newSave = null;
        File objFile = new File(path);
        if(!objFile.exists() || !objFile.canRead())
            Log.Log("ERROR: Problem accessing file");
        
        try(ObjectInputStream objIn = new ObjectInputStream(
                new BufferedInputStream(
                new FileInputStream(objFile))))
        {
            Object data = objIn.readObject();
            newSave = (Data)data;
            
            if(newSave == null){
                Log.Log("Error: Problem reading file");
            } else {
                newSave.campuses.values().forEach((campus) -> {
                    AssignObserver(campus);
                });
            }
            
            Log.Log("All data read from file.");
        }
        catch(ClassNotFoundException | IOException | ClassCastException ex)
        {
            Log.Log("ERROR: " + ex.getMessage());
        }
        
        if (overwriteAll){            
            if (newSave == null)
                SetDefaultState();
            else {
                allCampuses = newSave.campuses;
                allKeycards = newSave.keycards;
            }
        } else if (newSave == null){
            Log.Log("ERROR: Unable to open that file!");
        }
        
        return newSave;
    }
    
    private static void AssignObserver(Location location){
        Log logger = Log.Logger();
        
        if (location != null){
            location.AddStateObserver(logger);
            
            if (location instanceof ParentLocation)
                for (Location child : ((ParentLocation)location).GetAllChildren()) {
                        AssignObserver(child);
                }
            else                
                ((Room) location).AddAccessObserver(logger);
            
            if (location instanceof Building)
                ((Building)location).SetCampus(((Building)location).GetCampus());
            else if (location instanceof Floor)
                ((Floor)location).SetBuilding(((Floor)location).GetBuilding());
            else if (location instanceof Room)
                ((Room)location).SetFloor(((Room)location).GetFloor());
        }
    }

    private static void SetDefaultState(){
        Log.Log("Creating default data sets.");
        
        Data.allCampuses = new HashMap<>();
        
        Data.allCampuses.put("Main Campus", new Campus("Main Campus"));
        
        Campus campus = (Campus)Data.allCampuses.values().toArray()[0];
        campus.AddBuilding("Babbage", "BGB");
        campus.AddBuilding("Roland Levinsky", "RLB");
        
        Building building = campus.GetChild("Babbage");
        building.AddFloor();
        building.AddFloor();
                
        Floor floor = building.GetChild("0");
        floor.AddRoom(STUDENTLAB);
        floor.AddRoom(STUDENTLAB);
        floor.AddRoom(STAFFROOM);
        floor.AddRoom(SECUREROOM);
        floor.AddRoom(STUDENTLAB);
        
        floor = building.GetChild("1");
        floor.AddRoom(STUDENTLAB);
        floor.AddRoom(STUDENTLAB);
        floor.AddRoom(STUDENTLAB);
        floor.AddRoom(SECUREROOM);
        floor.AddRoom(RESEARCHLAB);   
        
        building = campus.GetChild("Roland Levinsky");
        building.AddFloor();
        building.AddFloor();
        building.AddFloor();
                
        floor = building.GetChild("0");
        floor.AddRoom(LECTUREHALL);
        floor.AddRoom(LECTUREHALL);
        floor.AddRoom(STAFFROOM);
        floor.AddRoom(SECUREROOM);
        floor.AddRoom(LECTUREHALL);
        
        floor = building.GetChild("1");
        floor.AddRoom(LECTUREHALL);
        floor.AddRoom(LECTUREHALL);
        floor.AddRoom(LECTUREHALL);
        floor.AddRoom(LECTUREHALL);
        floor.AddRoom(RESEARCHLAB);
        
        floor = building.GetChild("2");
        floor.AddRoom(LECTUREHALL);
        floor.AddRoom(LECTUREHALL);
        floor.AddRoom(LECTUREHALL);
        floor.AddRoom(SECUREROOM);
        floor.AddRoom(RESEARCHLAB);
        
        KeycardFactory.Create(VISITOR, "Guest card #1");
        KeycardFactory.Create(VISITOR, "Guest card #2");
        KeycardFactory.Create(VISITOR, "Guest card #3");
        KeycardFactory.Create(STUDENT, "Dave");
        KeycardFactory.Create(STUDENT, "Adam");
        KeycardFactory.Create(STUDENT, "John");
        KeycardFactory.Create(STUDENT, "Toby");
        KeycardFactory.Create(STUDENT, "Sam");
        KeycardFactory.Create(STUDENT, "Zac");
        KeycardFactory.Create(STUDENT, "Jake");
        KeycardFactory.Create(STAFFMEMBER, "Serge");
        KeycardFactory.Create(STAFFMEMBER, "Chris");
        KeycardFactory.Create(SECURITY, "Mike");
        KeycardFactory.Create(EMERGENCYRESPONDER, "Fireman");
        KeycardFactory.Create(EMERGENCYRESPONDER, "Fireman");
        KeycardFactory.Create(EMERGENCYRESPONDER, "Fireman");
    }
}
