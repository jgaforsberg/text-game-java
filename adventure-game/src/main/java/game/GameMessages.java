package game;

import gameobjects.Character;
import gameobjects.Thing;
import gameobjects.ThingList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/*
* TODO :
*       quit game message
*       win game message
*       lose game message
* */
class GameMessages {
    protected static void showIntro() {
        System.out.println("""
                Welcome to the text adventure game, adventurer.
                To navigate use < n | s | e | w >.
                To win the game, you need to:
                Fight the dragon, find the treasure, and leave by the exit.
                Good luck!""");

    }
    protected static void lookAround(Character player) {
        // TODO : add things, characters etc
        System.out.println("You are in the "+player.getCurrentRoom().getName()+".\n" +
                player.getCurrentRoom().getDescription()+".");
    }
    protected static void showPlayerInventory(ThingList playerInventory) {
        String string = "You have: \n";
        string += playerInventory.getThings();
        string += "in your inventory!";
        System.out.println(string);
    }
    protected static void showHelp(List<String> commandList) {
        String string = "Available commands: ";
        Iterator<String> commandIterator = commandList.iterator();
        while (commandIterator.hasNext()) {
            string += commandIterator.next() + ", ";
        }
        System.out.println(string);
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
    public class VisualPrints {
        public void printTreasure() {
            System.out.println(
                    "                  _.--.\n" +
                            "              _.-'_:-'||\n" +
                            "          _.-'_.-::::'||\n" +
                            "     _.-:'_.-::::::'  ||\n" +
                            "   .'`-.-:::::::'     ||\n" +
                            "  /.'`;|:::::::'      ||_\n" +
                            " ||   ||::::::'      _.;._'-._\n" +
                            " ||   ||:::::'   _.-!oo @.!-._'-.\n" +
                            " \'.  ||:::::.-!() oo @!()@.-'_.||\n" +
                            "   '.'-;|:.-'.&$@.& ()$%-'o.'\\U||\n" +
                            "     `>'-.!@%()@'@_%-'_.-o _.|'||\n" +
                            "      ||-._'-.@.-'_.-' _.-o  |'||\n" +
                            "      ||=[ '-._.-\\U/.-'    o |'||\n" +
                            "      || '-.]=|| |'|      o  |'||\n" +
                            "      ||      || |'|        _| ';\n" +
                            "      ||      || |'|    _.-'_.-'\n" +
                            "      |'-._   || |'|_.-'_.-'\n" +
                            "      '-._'-.|| |' `_.-'\n" +
                            "           '-.||_/.-'\n");
        }
        public void printDragon() {
            System.out.println(
                    "                                                  .~))>>\n"+
                    "                                                 .~)>>\n"+
                    "                                               .~))))>>>\n"+
                    "                                             .~))>>             ___\n"+
                    "                                           .~))>>)))>>      .-~))>>\n"+
                    "                                         .~)))))>>       .-~))>>)>\n"+
                    "                                       .~)))>>))))>>  .-~)>>)>\n"+
                    "                   )                 .~))>>))))>>  .-~)))))>>)>\n"+
                    "                ( )@@*)             //)>))))))  .-~))))>>)>\n"+
                    "              ).@(@@               //))>>))) .-~))>>)))))>>)>\n"+
                    "            (( @.@).              //))))) .-~)>>)))))>>)>\n"+
                    "          ))  )@@*.@@ )          //)>))) //))))))>>))))>>)>\n"+
                    "       ((  ((@@@.@@             |/))))) //)))))>>)))>>)>\n"+
                    "      )) @@*. )@@ )   (\\_(\\-\\b  |))>)) //)))>>)))))))>>)>\n"+
                    "    (( @@@(.@(@ .    _/`-`  ~|b |>))) //)>>)))))))>>)>\n"+
                    "     )* @@@ )@*     (@)  (@) /\\b|))) //))))))>>))))>>\n"+
                    "   (( @. )@( @ .   _/  /    /  \\b)) //))>>)))))>>>_._\n"+
                    "    )@@ (@@*)@@.  (6///6)- / ^  \\b)//))))))>>)))>>   ~~-.\n"+
                    " ( @jgs@@. @@@.*@_ VvvvvV//  ^  \\b/)>>))))>>      _.     `bb\n"+
                    " ((@@ @@@*.(@@ . - | o |' \\ (  ^   \\b)))>>        .'       b`,\n"+
                    "   ((@@).*@@ )@ )   \\^^^/  ((   ^  ~)_        \\  /           b `,\n"+
                    "     (@@. (@@ ).     `-'   (((   ^    `\\ \\ \\ \\ \\|             b  `.\n"+
                    "       (*.@*              / ((((        \\| | |  \\       .       b `.\n"+
                    "                         / / (((((  \\    \\ /  _.-~\\     Y,      b  ;\n"+
                    "                        / / / (((((( \\    \\.-~   _.`\" _.-~`,    b  ;\n"+
                    "                       /   /   `(((((()    )    (((((~      `,  b  ;\n"+
                    "                     _/  _/      `\"\"\"/   /'                  ; b   ;\n"+
                    "                 _.-~_.-~           /  /'                _.'~bb _.'\n"+
                    "               ((((~~              / /'              _.'~bb.--~\n"+
                    "                                  ((((          __.-~bb.-~\n"+
                    "                                              .'  b .~~\n"+
                    "                                              :bb ,' \n"+
                    "                                              ~~~~\n");
        }
    }
}
