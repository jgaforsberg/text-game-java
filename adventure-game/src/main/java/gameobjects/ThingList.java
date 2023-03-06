package gameobjects;

import java.util.ArrayList;

public class ThingList extends ArrayList<Thing> {
    public String getThings() {
        String string = "";
        if(this.size() == 0) string = "nothing\n";
        else for (Thing thing : this) string += (thing.getName()+": "+thing.getDescription()+", \n");
        return string;
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
