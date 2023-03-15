package gameobjects;

/*
* For possible HashMap map
* import globals.Rooms;
 */

public class Room extends ThingHolder{
    private int n, s, w, e;

    public Room(String name, String description, int n, int s, int w, int e, ThingList thingList) {
        super(name, description, thingList);
        this.n = n;
        this.s = s;
        this.w = w;
        this.e = e;
    }
    public int getN() {
        return n;
    }
    public void setN(int n) {
        this.n = n;
    }
    public int getS() {
        return s;
    }
    public void setS(int s) {
        this.s = s;
    }
    public int getW() {
        return w;
    }
    public void setW(int w) {
        this.w = w;
    }
    public int getE() {
        return e;
    }
    public void setE(int e) {
        this.e = e;
    }
}
