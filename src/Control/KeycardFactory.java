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
    
    public static Keycard Create(Role role, String name){
        if (previousCardID == null)
            ReadState();
        previousCardID++;
        WriteState();
        return new Keycard(role, name, "" + previousCardID);
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
