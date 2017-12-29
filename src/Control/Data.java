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
     *
     */
    public static HashMap<String, Keycard> allKeycards = new HashMap<String, Keycard>();

    /**
     *
     */
    public static HashMap<String, Campus> allCampuses;
    
    public HashMap<String, Campus> campuses;
    public HashMap<String, Keycard> keycards;
    
    private Data(HashMap<String, Campus> campuses, HashMap<String, Keycard> keycards){        
        this.campuses = campuses;
        this.keycards = keycards;
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
            Log.Log("All Locations written to file.");
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
     * <code>Location</code> in the <code>ArrayList\<Campus\></code>. It also
     * add the <code>Logger</code> as an access observer to each
     * <code>Room</code> contained within the <code>Campus</code>s.
     * @param path The file location of the file to load
     * @return The object serialised from the file
     */
    public static Data LoadState(String path){
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
            
            Log.Log("All Locations read from file.");
        }
        catch(ClassNotFoundException | IOException | ClassCastException ex)
        {
            Log.Log("ERROR: " + ex.getMessage());
        }
        
        if (newSave == null)
            SetDefaultState();
        else {
            allCampuses = newSave.campuses;
            allKeycards = newSave.keycards;
        }
        
        
//SetDefaultState();//DEBUG
//System.out.println("Did the nasty");//DEBUG
        
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
        }
    }

    private static void SetDefaultState(){
        Data.allCampuses = new HashMap<>();
        
        Data.allCampuses.put("Main Campus", new Campus("Main Campus"));
        
        Campus campus1 = (Campus)Data.allCampuses.values().toArray()[0];
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
