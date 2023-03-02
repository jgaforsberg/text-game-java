package gameobjects;

public class ThingHolder extends Thing{
    /*  */
    private ThingList thingList;
    public ThingHolder(String name, String description, ThingList thingList) {
        super(name, description);
        this.thingList = thingList;
    }
    public ThingList getThingList() {
        return thingList;
    }
    public void setThingList(ThingList thingList) {
        this.thingList = thingList;
    }
}
