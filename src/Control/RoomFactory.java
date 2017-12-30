/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Listeners.IAccessObserver;
import Locations.Floor;
import Locations.Room;
import Locations.RoomType;
import static Locations.States.LocationState.NOEMERGENCY;

/**
 * Factory for creating <code>Room</code> objects and setting them up with the
 * default properties and method calls.
 * @author Student
 */
public class RoomFactory {
    
    private static final IAccessObserver log = Log.Logger();
    
    /**
     * Creates a <code>Room</code> object.
     * 
     * Once the room is created the following methods are ran on it:<ul><li>Sets
     * the floor</li><li>Sets the room type</li><li>Adds the access observer</li>
     * <li>Sets the room state to <code>NOEMERGENCY</code></ul>
     * @param number The room number to be assigned to the <code>Room</code>
     * @param type The type of <code>Room</code> to be made
     * @return The finished <code>Room</code> object
     */
    public static Room Create(String number, RoomType type){
        Room room = new Room(number);
              
        room.SetRoomType(type);
        room.AddAccessObserver(log);
        
        return room;
    }
}
