package gameobjects;

public class Character extends ThingHolder {
    /*
     * Archetype of all characters in-game, e.g., player, monsters
     * Current location of character
     * */
    private Room currentRoom;
    private int health, damage;
    public Character(String name, String description, Room currentRoom, int health, int damage, ThingList thingList) {
        super(name, description, thingList);
        this.currentRoom = currentRoom;
        this.health = health;
        this.damage = damage;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
