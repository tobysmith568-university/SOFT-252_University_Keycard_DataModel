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
    
    /**
     * Creates a new <code>keycard</code> object
     * @param roles The roles of the new <code>Keycard</code>
     * @param name The name of the owner of the <code>Keycard</code>
     * @return The new <code>Keycard</code> object
     */
    public static Keycard Create(Role[] roles, String name){
        if (previousCardID == null)
            ReadState();
        previousCardID++;
        WriteState();
        
        String newCardID = "" + previousCardID;
        if (newCardID.length() < 8){
            newCardID = new String(new char[8 - newCardID.length()]).replace("\0", "0") + newCardID;
        }
        
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
    public static Keycard Create(Role role, String name){
        if (previousCardID == null)
            ReadState();
        previousCardID++;
        WriteState();
        
        String newCardID = "" + previousCardID;
        if (newCardID.length() < 8){
            newCardID = new String(new char[8 - newCardID.length()]).replace("\0", "0") + newCardID;
        }
        
        Keycard newCard = new Keycard(role, name, newCardID);
        Data.allKeycards.put(newCardID, newCard);
        return newCard;
    }
    
    private static boolean WriteState(){
        String fileName = "PreviousID.state";
        Path path = Paths.get(fileName);
        try {                  
            Files.write(path, Arrays.asList("" + previousCardID), Files.exists(path) ? StandardOpenOption.TRUNCATE_EXISTING : StandardOpenOption.CREATE);
        } catch (IOException e) {
            return false;
        }
        return true;
    }
    
    private static boolean ReadState(){
        String fileName = "PreviousID.state";
        Path path = Paths.get(fileName);
        
        if (!Files.exists(path))
            previousCardID = 0L;
        else
            try {            
                previousCardID = Long.parseLong(Files.readAllLines(path).get(0));
            } catch (IOException e) {
                return false;
            }
        return true;
    }
}
