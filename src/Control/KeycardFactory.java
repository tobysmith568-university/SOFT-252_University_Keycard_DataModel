/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import People.Keycard;
import People.Role;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

/**
 * Factory for creating <code>Keycard</code> objects and setting them up with the
 * default properties and method calls.
 * @author Student
 */
public class KeycardFactory implements Serializable{
    
    private static Long previousCardID;
    private static final String FILELOCATION = "PreviousID.state";
    
    /**
     * Creates a new <code>keycard</code> object
     * @param roles The roles of the new <code>Keycard</code>
     * @param name The name of the owner of the <code>Keycard</code>
     * @return The new <code>Keycard</code> object
     */
    public static Keycard Create(Role[] roles, String name) {
        //Find the previously used ID
        if (previousCardID == null)
            ReadPrevious();
        previousCardID++;
        WritePrevious();
        
        //Add preceeding zeros to ensure the number is at least 8 digits long
        String newCardID = "" + previousCardID;
        if (newCardID.length() < 8) {
            newCardID = new String(new char[8 - newCardID.length()]).replace("\0", "0") + newCardID;
        }
        
        //Create the new keycard
        Keycard newCard = new Keycard(roles, name, newCardID);
        Data.allKeycards.put(newCardID, newCard);
        return newCard;
    }
    
    /**
     * Creates a new <code>keycard</code> object
     * @param role The role of the new <code>Keycard</code>
     * @param name The name of the owner of the <code>Keycard</code>
     * @return The new <code>Keycard</code> object
     */
    public static Keycard Create(Role role, String name) {
        //Find the previously used ID
        if (previousCardID == null)
            ReadPrevious();
        previousCardID++;
        WritePrevious();
        
        //Add preceeding zeros to ensure the number is at least 8 digits long
        String newCardID = "" + previousCardID;
        if (newCardID.length() < 8) {
            newCardID = new String(new char[8 - newCardID.length()]).replace("\0", "0") + newCardID;
        }
        
        //Create the new keycard
        Keycard newCard = new Keycard(role, name, newCardID);
        Data.allKeycards.put(newCardID, newCard);
        return newCard;
    }
    
    private static boolean WritePrevious() {
        Path path = Paths.get(FILELOCATION);
        
        try {
            //Write the previous keycard ID used by either erasing or creating the file depending on of it already exists or not
            Files.write(path, Arrays.asList("" + previousCardID), Files.exists(path) ? StandardOpenOption.TRUNCATE_EXISTING : StandardOpenOption.CREATE);
        } catch (IOException e) {
            Log.Log("ERROR: " + e.getMessage());
            return false;
        }
        return true;
    }
    
    private static boolean ReadPrevious() {
        Path path = Paths.get(FILELOCATION);
        
        if (!Files.exists(path))
            previousCardID = 0L;
        else
            try {
                //Read the previous keycard ID
                previousCardID = Long.parseLong(Files.readAllLines(path).get(0));
            } catch (IOException e) {
                Log.Log("ERROR: " + e.getMessage());
                return false;
            }
        return true;
    }
}
