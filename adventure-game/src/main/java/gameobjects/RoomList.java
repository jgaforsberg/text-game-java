package gameobjects;

import globals.Rooms;

import java.util.HashMap;
import java.io.Serializable;
import java.util.Iterator;
/*
*   TODO : Implement HashMap map
* */
public class RoomList extends HashMap<Rooms, Room> implements Serializable{
    public RoomList() {}
    public Room roomAt(Rooms id) {
        return this.get(id);
    }
    public String roomDescription() {
        String string = "";
        if(this.size() == 0) string = "nothing\n";
        else {
            Iterator<Entry<Rooms, Room>> iterator = this.entrySet().iterator();
            while (iterator.hasNext()) {
                Entry<Rooms, Room> entry = iterator.next();
                string += entry.getValue().getDescription() + "\r\n";
            }
        }
        return string;
    }

}
