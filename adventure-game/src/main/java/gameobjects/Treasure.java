package gameobjects;

public class Treasure extends Thing{
    private final int value;

    public Treasure(String name, String description, int value) {
        super(name, description);
        this.value = value;
    }
    @SuppressWarnings("unused")
    public int getValue() {
        return value;
    }
}
