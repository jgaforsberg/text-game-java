package game;

import gameobjects.Character;
import gameobjects.Thing;
import gameobjects.ThingList;

import java.util.ArrayList;

class GameMessages {
    public static void showIntro() {
        System.out.println("""
                Welcome to the text adventure game, adventurer.
                To navigate use < n | s | e | w >.
                To win the game, you need to:
                Fight the dragon, find the treasure, and leave by the exit.
                Good luck!""");

    }
    public static void lookAround(Character player) {
        // TODO : add things, characters etc
        System.out.println("You are in the "+player.getCurrentRoom().getName()+".\n" +
                player.getCurrentRoom().getDescription()+".");
    }
    public static void showInventory(ThingList playerInventory) {
        System.out.println("You have: ");
        playerInventory.getThings();
    }
    /* Loop through available things in ArrayList of Things*/
    public static void lookThing(ArrayList<Thing> things) {
        if (things.size() == 0) System.out.println("Nothing.");
        else {
            for (Thing thing : things) {
                System.out.println(thing.getName()+": "+thing.getDescription());
            }
        }

    }
}
