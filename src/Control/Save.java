/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import static Control.UniversityKeycards.allCampuses;
import static Control.UniversityKeycards.allKeycards;
import Locations.Campus;
import Locations.Location;
import Locations.ParentLocation;
import Locations.Room;
import People.Keycard;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * An object containing an <code>ArrayList</code> of <code>Campus</code>s and a
 * <code>HashMap</code> of <code>Keycard</code>s. This is used so that both
 * can be serialised and saved together in the same file.
 * @author Student
 */
public class Save implements Serializable {
    
    public ArrayList<Campus> campuses;
    public HashMap<String, Keycard> keycards;
    
    private Save(ArrayList<Campus> campuses, HashMap<String, Keycard> keycards){        
        this.campuses = campuses;
        this.keycards = keycards;
    }

    /**
     * Takes <code>Campus</code>s and <code>Keycard</code>s and saves their
     * states to a given file path.
     * @param path The path of where to save the states
     * @param campuses The <code>Campus</code> objects to save
     * @param Keycards The <code>Keycard</code> objects to save
     * @return <code>True</code> if the states of the objects were successfully
     * saved to the file
     */
    public static boolean SaveState(String path, ArrayList<Campus> campuses, HashMap<String, Keycard> Keycards){
        File objFile = new File(path);
        
        try (ObjectOutputStream objOut = new ObjectOutputStream(
                                            new BufferedOutputStream(
                                            new FileOutputStream(objFile)))){
            objOut.writeObject(new Save(campuses, Keycards));
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
     * a <code>Save</code> object containing an <code>ArrayList</code> of
     * <code>Campus</code> objects and a <code>HasMap</code> of
     * <code>Keycard</code> objects. If this is successful it recurringly
     * assigns the <code>Logger</code> as an state observer to each
     * <code>Location</code> in the <code>ArrayList\<Campus\></code>. It also
     * add the <code>Logger</code> as an access observer to each
     * <code>Room</code> contained within the <code>Campus</code>s.
     * @param path The file location of the file to load
     * @return The object serialised from the file
     */
    public static Save LoadState(String path){
        Save newSave = null;
        File objFile = new File(path);
        if(!objFile.exists() || !objFile.canRead())
            Log.Log("ERROR: Problem accessing file");
        
        try(ObjectInputStream objIn = new ObjectInputStream(
                new BufferedInputStream(
                new FileInputStream(objFile))))
        {
            Object data = objIn.readObject();
            newSave = (Save)data;
            
            if(newSave == null){
                Log.Log("Error: Problem reading file");
            } else {
                newSave.campuses.forEach((campus) -> {
                    AssignObserver(campus);
                });
            }
            
            Log.Log("All Locations read from file.");
        }
        catch(ClassNotFoundException | IOException | ClassCastException ex)
        {
            Log.Log("ERROR: " + ex.getMessage());
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
        }
    }
}