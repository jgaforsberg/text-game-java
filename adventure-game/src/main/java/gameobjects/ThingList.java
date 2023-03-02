package gameobjects;

import java.util.ArrayList;

public class ThingList extends ArrayList<Thing> {
    public void getThings() {
        if(this.size() == 0) System.out.println("Nothing.\n");
        else for (Thing thing : this) System.out.println(thing.getName()+": "+thing.getDescription());
    }
    public Thing thisThing(String name) {
        Thing thing = null;
        String loopName = "";
        name = name.trim().toLowerCase();
        for (Thing i : this) {
            name = i.getName().trim().toLowerCase();
            if (loopName.equals(name));
            thing = i;
        }
        return thing;
    }
}
