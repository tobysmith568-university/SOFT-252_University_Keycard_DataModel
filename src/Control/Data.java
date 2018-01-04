/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Locations.*;
import static Locations.RoomType.*;
import People.Keycard;
import static People.Role.*;
import java.io.*;
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
    
    private Data(HashMap<String, Campus> campuses, HashMap<String, Keycard> keycards) {        
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
    public static boolean SaveState(String path, HashMap<String, Campus> campuses, HashMap<String, Keycard> keycards) {
        File objFile = new File(path);
        
        //Create a new ObjectOutputStream
        try (ObjectOutputStream objOut = new ObjectOutputStream(
                                            new BufferedOutputStream(
                                            new FileOutputStream(objFile)))) {
            
            //Write the campus and keycard objects
            objOut.writeObject(new Data(campuses, keycards));
            
            //Tell the logger
            Log.Log("All data written to \"" + path + "\".");
        } catch (IOException ex) {
            Log.Log("ERROR: " + ex.getMessage());
            return false;
        }
        return true;
    }
    
    /**
     * Loads a states of <code>Campus</code> and <code>Keycard</code> objects
     * into the main data of the program.
     * 
     * <h2>Do not confuse with <code>ReadState()</code></h2>
     * 
     * Looks for a file at the location given and tries to parse it's data into
     * a <code>Data</code> object containing an <code>ArrayList</code> of
     * <code>Campus</code> objects and a <code>HasMap</code> of
     * <code>Keycard</code> objects. If this is successful it recurringly
     * assigns the <code>Logger</code> as an state observer to each
     * <code>Location</code> in the <code>ArrayList&lt;Campus&gt;</code>. It also
     * add the <code>Logger</code> as an access observer to each
     * <code>Room</code> contained within the <code>Campus</code>s.
     * 
     * <h2>This data is then used to overwrite the primary data in the program!</h2>
     * @param path The location of the <code>.state</code> file
     * @return The newly read campus and keycard Data
     */
    public static Data LoadState(String path) {
        //Read the data in the given path
        Data newData = ReadState(path);
        
        //If it's not successful load in default data
        if (newData == null)
            SetDefaultState();
        
        //If it is successful load in the read data
        else {
            allCampuses = newData.campuses;
            allKeycards = newData.keycards;
        }
        
        return newData;
    }
    
    /**
     * Reads <code>Campus</code> and <code>Keycard</code> objects from a given
     * file location.
     * 
     * <h2>Do not confuse with <code>SaveState()</code></h2>
     * 
     * Looks for a file at the location given and tries to parse it's data into
     * a <code>Data</code> object containing an <code>ArrayList</code> of
     * <code>Campus</code> objects and a <code>HasMap</code> of
     * <code>Keycard</code> objects. If this is successful it recurringly
     * assigns the <code>Logger</code> as an state observer to each
     * <code>Location</code> in the <code>ArrayList&lt;Campus&gt;</code>. It also
     * add the <code>Logger</code> as an access observer to each
     * <code>Room</code> contained within the <code>Campus</code>s.
     * @param path The location of the <code>.state</code> file
     * @return The newly read campus and keycard Data
     */
    public static Data ReadState(String path) {
        Data newSave = null;
        File objFile = new File(path);
        
        //Ensure the wanted file exists and is readable
        if(!objFile.exists() || !objFile.canRead())
            Log.Log("ERROR: Problem accessing file");
        
        //Create a new ObjectInputStream
        try(ObjectInputStream objIn = new ObjectInputStream(
                new BufferedInputStream(
                new FileInputStream(objFile)))) {
            
            //Read the data in the file and cast it to a Data object
            Object data = objIn.readObject();
            newSave = (Data)data;
            
            //If it's null tell the logger
            if(newSave == null) {
                Log.Log("Error: Problem reading file");
                
            //If it's not null recursively add the logger as an observer to
            //all locations and child locations
            } else {
                newSave.campuses.values().forEach((campus) -> {
                    AssignObserver(campus);
                });
            }
            
            //Tell the logger
            Log.Log("All data read from file.");
        } catch(ClassNotFoundException | IOException | ClassCastException ex) {
            Log.Log("ERROR: " + ex.getMessage());
        }
        
        return newSave;
    }
    
    private static void AssignObserver(Location location) {
        //Find the logger
        Log logger = Log.Logger();
        
        if (location != null) {
            //Add the logger as a state observer of the location
            location.AddStateObserver(logger);
            
            //If the location is a parent to other location, call this method on it's children
            if (location instanceof ParentLocation)
                for (Location child : ((ParentLocation)location).GetAllChildren()) {
                        AssignObserver(child);
                }
            
            //Else if the location isn't a parent - ie, it's a room,
            //Add the logger as an access observer too
            else
                ((Room)location).AddAccessObserver(logger);
            
            //Set all parent objects of locations to their exact same vaules.
            //this will also re-add them as state observers of their children.
            //This is used to calculate mixed states
            
            //If the location is a building, set it's campus to it's current campus
            if (location instanceof Building)
                ((Building)location).SetCampus(((Building)location).GetCampus());
            
            //If the location is a floor, set it's building to it's current building
            else if (location instanceof Floor)
                ((Floor)location).SetBuilding(((Floor)location).GetBuilding());
            
            //If the location is a room, set it's floor to it's current floor
            else if (location instanceof Room)
                ((Room)location).SetFloor(((Room)location).GetFloor());
        }
    }

    private static void SetDefaultState() {
        Log.Log("Creating default data sets.");
        
        Data.allCampuses = new HashMap<>();
        
        Data.allCampuses.put("Main Campus", new Campus("Main Campus"));
        
        Campus campus = Data.allCampuses.values().toArray(new Campus[0])[0];
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
        
        Log.Log("Finished creating default data sets.");
    }
}
