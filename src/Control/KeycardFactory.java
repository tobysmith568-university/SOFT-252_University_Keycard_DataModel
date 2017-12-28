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
 *
 * @author Toby
 */
public class KeycardFactory implements Serializable{
    
    private static Long previousCardID;
    
    /**
     *
     * @param role
     * @param name
     * @return
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
