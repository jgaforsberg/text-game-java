package game;

import gameobjects.*;
import gameobjects.Character;
import globals.Direction;
import globals.Rooms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static game.GameMessages.*;
/*
* TODO :
*       other characters
*       items
*       item interaction
*
* */
public class Game {
    /*
     * Initialize map, characters, commands, objects
     * */
    private Character player, minorMonster, mediumMonster, dragon;
    private ThingList playerInventory;
    private ArrayList<Room> alMap;
    //private HashMap<Rooms, Room> hmMap = new HashMap<>();
    //private RoomList<Rooms, Room> hmMap;
    /*
     * For parsing user string input
     */
    protected List <String> commands = new ArrayList<>(Arrays.asList(
            "take", "drop", "l", "look", "h", "help", "i", "inventory",
            "n", "s", "e", "w"
    ));
    protected List <String> objects = new ArrayList<>(Arrays.asList(
            "sword", "ring", "snake"
    ));
    public Game() throws IOException {
        initGame();
        startGame();
    }
    private void initGame() {
        /*
         * Declare and init rooms & map, including items
         * The exits are integers that represents the index in the map array to another room
         * e.g., r0 S = 2, meaning this exit refers to map[2]. -1 indicates no exit
         * */
        ThingList potionRoomList = new ThingList();
        potionRoomList.add(new Item("potion", "A glass vial with a potion glowing red"));
        ThingList keyRoomList = new ThingList();
        keyRoomList.add(new Item("key", "A dirty brass ring"));
        ThingList sageRoomList = new ThingList();
        sageRoomList.add(new Item("sword", "A sword, maybe of destiny"));
        ThingList treasureRoomList = new ThingList();
        treasureRoomList.add(new Treasure("dragon hoard", "A great dragon hoard to rival any great dragon treasure", 1000));

        /* ArrayList map */
        this.alMap = new ArrayList<Room>();
        alMap.add(new Room("Entrance Room", "There is a great door to the north", 1, -1, -1, -1, new ThingList()));
        alMap.add(new Room("Corridor Room", "A long empty corridor that you can barely see the end of", 2, 0, -1, -1, keyRoomList));
        alMap.add(new Room("Crossroads Room", "A crossroads with exits to the east, west and south", -1, 1, 3, 8, new ThingList()));
        alMap.add(new Room("Library Room", "Moldy, dusty, shiny and every other variant of books cover every wall of the room", -1, 4, 5, 2, new ThingList()));
        alMap.add(new Room("Potion Room", "The walls of this room are almost caving in", 3, -1, -1, -1, potionRoomList));
        alMap.add(new Room("Monster Room", "There is little light and it smells dank and musky in here", -1, 6, -1, 3, new ThingList()));
        alMap.add(new Room("Trap Room", "Rare paintings cover the walls and pedestals with jewels, gold and silver trinkets are easily accessible", 5, 7, -1, -1, new ThingList()));
        alMap.add(new Room("Sage Room", "There's a man hunched over a desk to the south wall. It's bright and the air is light in here", 6, -1, -1, -1, sageRoomList));
        alMap.add(new Room("Locked Room", "There's a sturdy oak door to the south that seems impenetrable", -1, -1, 2, 9, new ThingList()));
        alMap.add(new Room("Fountain Room", "Water streams down the walls in complicated aqueducts to a fountain in the center of the room", -1, 10, 8, -1, new ThingList()));
        alMap.add(new Room("Dark Room", "All light have seemingly disappeared, it's pitch dark with a small glimmer of light coming from the south", 9, 11, -1, -1, new ThingList()));
        alMap.add(new Room("Monster Room", "It smells rancid and sour in here, eyes seem to glow from the shadows", 12, -1, 10, -1, new ThingList()));
        alMap.add(new Room("Key Room", "Tapestries of keys - golden, silver, brass, large, small, simple and complex - cover the walls", 9, 1, -1, -1, keyRoomList));
        alMap.add(new Room("Dragon Room", "Bones, coins and charred remains of previous adventurers litter the room, the walls seem to have melted time and again", 8, 14, -1, -1, new ThingList()));
        alMap.add(new Room("Treasure Room", "Gold and jewels cover both floor and walls, if only there was a cart around", 13, -1, 0, -1, treasureRoomList));
        /*
        * Possible HashMap implementation
        //        Key(Enum Rooms)  Value(Room)     Room name               Room description                    North               South           West            East        ThingList
        hmMap.put(Rooms.StartRoom, new Room("Start Room", "This is a start room description", Rooms.CorridorRoom, Rooms.NOEXIT, Rooms.NOEXIT, Rooms.NOEXIT, new ThingList()));
        hmMap.put(Rooms.CorridorRoom, new Room("Corridor Room", "A long empty corridor", Rooms.CrossroadsRoom, Rooms.StartRoom, Rooms.NOEXIT, Rooms.NOEXIT, new ThingList()));
        hmMap.put(Rooms.CrossroadsRoom, new Room("Crossroads Room", "A crossroads with several exits", Rooms.NOEXIT, Rooms.CorridorRoom, Rooms.LibraryRoom, Rooms.LockedRoom, new ThingList()));
        hmMap.put(Rooms.LibraryRoom, new Room("Library Room", "Books cover every wall of the room", Rooms.NOEXIT, Rooms.PotionRoom, Rooms.MediumMonsterRoom, Rooms.CrossroadsRoom, new ThingList()));
        hmMap.put(Rooms.PotionRoom, new Room("Potion Room", "The walls are almost caving in", Rooms.LibraryRoom, Rooms.NOEXIT, Rooms.NOEXIT, Rooms.NOEXIT, potionRoomList));
        hmMap.put(Rooms.MediumMonsterRoom, new Room("Monster Room", "It smells dank and musky in here", Rooms.NOEXIT, Rooms.TrapRoom, Rooms.NOEXIT, Rooms.LibraryRoom, new ThingList()));
        hmMap.put(Rooms.TrapRoom, new Room("Trap Room", "There's something iffy about this room", Rooms.MediumMonsterRoom, Rooms.WiseSageRoom, Rooms.NOEXIT, Rooms.NOEXIT, new ThingList()));
        hmMap.put(Rooms.WiseSageRoom, new Room("Sage Room", "It's bright and the air is light in here", Rooms.TrapRoom, Rooms.NOEXIT, Rooms.NOEXIT, Rooms.NOEXIT, sageRoomList));
        hmMap.put(Rooms.LockedRoom, new Room("Locked Room", "There's a sturdy oak door to the south", Rooms.NOEXIT, Rooms.NOEXIT, Rooms.CrossroadsRoom, Rooms.FountainRoom, new ThingList()));
        hmMap.put(Rooms.FountainRoom, new Room("Fountain Room", "Water streams down the walls to a fountain", Rooms.NOEXIT, Rooms.DarkRoom, Rooms.NOEXIT, Rooms.NOEXIT, new ThingList()));
        hmMap.put(Rooms.DarkRoom, new Room("Dark Room", "All light have disappeared, it's pitch dark", Rooms.FountainRoom, Rooms.MinorMonsterRoom, Rooms.NOEXIT, Rooms.NOEXIT, new ThingList()));
        hmMap.put(Rooms.MinorMonsterRoom, new Room("Monster Room", "It smells rancid and sour in here", Rooms.TreasureKeyRoom, Rooms.NOEXIT, Rooms.DarkRoom, Rooms.NOEXIT, new ThingList()));
        hmMap.put(Rooms.TreasureKeyRoom, new Room("Key Room", "Tapestries of keys cover the walls", Rooms.FountainRoom, Rooms.MinorMonsterRoom, Rooms.NOEXIT, Rooms.NOEXIT, keyRoomList));
        hmMap.put(Rooms.DragonRoom, new Room("Dragon Room", "Bones, coins and charred remains litter the room", Rooms.LockedRoom, Rooms.TreasureRoom, Rooms.NOEXIT, Rooms.NOEXIT, new ThingList()));
        hmMap.put(Rooms.TreasureRoom, new Room("Treasure Room", "Gold and jewels cover both floor and walls", Rooms.DragonRoom, Rooms.NOEXIT, Rooms.StartRoom, Rooms.NOEXIT, new ThingList()));
        */
        /* Initialize the characters */
        playerInventory = new ThingList();
        player = new Character("You", "A too old for this shit player character", alMap.get(0), 10, 1, playerInventory);
        minorMonster = new Character("Monster", "A monster", alMap.get(11), 5, 1, new ThingList());
        mediumMonster = new Character("Monster", "A monster", alMap.get(5), 12, 1, new ThingList());
        dragon = new Character("Dragon", "The dragon", alMap.get(13), 20, 2, new ThingList());

    }
    private void startGame() throws IOException {
        String string, input = "", output = "";
        GameMessages.showIntro();
        /* Take user input */
        BufferedReader bufferedReader;
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        /*
         * For the main game loop
         * BufferedReader.readLine() allows for user input to be read, parsed and used in-game inside the main game loop
         * */
        do {
            System.out.print("> ");
            input = bufferedReader.readLine();
            output = runCommand(input);
            System.out.println(output);
        }while (!input.equalsIgnoreCase("q"));
    }
    /* Getters and setters for objects */
    /* Map */
    public ArrayList getMap() {
        return alMap;
    }
    public void setMap(ArrayList map) {
        alMap = map;
    }
    /* Player */
    public Character getPlayer() {
        return player;
    }
    public void setPlayer(Character player) {
        this.player = player;
    }
    /* Move characters between rooms */
    public void moveCharacter(Character character, Room room) {
        character.setCurrentRoom(room);
    }
    /* Move characters in direction x */
    int moveCharacterTo(Character character, Direction direction) {
        Room currentRoom = character.getCurrentRoom();
        int exit;

        switch (direction) {
            case NORTH:
                exit = currentRoom.getN();
                break;
            case SOUTH:
                exit = currentRoom.getS();
                break;
            case EAST:
                exit = currentRoom.getE();
                break;
            case WEST:
                exit = currentRoom.getW();
                break;
            default:
                exit = Direction.NOEXIT;
                break;
        }
        if(exit != Direction.NOEXIT) moveCharacter(character, alMap.get(exit));
        return exit;
    }
    public int movePlayerTo(Direction direction) {
        /* Returns constant representing room number */
        return moveCharacterTo(player, direction);
    }
    public int moveMonsterTo(Direction direction) {
        return moveCharacterTo(mediumMonster, direction);
    }
    private void goN() {
        updateOutput(movePlayerTo(Direction.NORTH));
    }
    private void goS() {
        updateOutput(movePlayerTo(Direction.SOUTH));
    }
    private void goE() {
        updateOutput(movePlayerTo(Direction.EAST));
    }
    private void goW() {
        updateOutput(movePlayerTo(Direction.WEST));
    }
    void updateOutput(int roomNumber) {
        if(roomNumber == Direction.NOEXIT) System.out.println("No Exit!");
        else GameMessages.lookAround(player);
    }
    /* Object interaction */
    /* Take and drop - transfer between ThingLists */
    private void transferObject(Thing thing, ThingList fromList, ThingList toList) {
        fromList.remove(thing);
        toList.add(thing);
    }
    private String takeObject(String name) {
        String string = "";
        Thing thing = player.getCurrentRoom().getThingList().thisThing(name);
        if(name.equals("")) name = "nameless object";
        if(thing == null) string = "There is no "+name+" here!";
        else {
            transferObject(thing, player.getCurrentRoom().getThingList(), player.getThingList()); // transfer from room inventory to player inventory
            string = name + " taken!";
        }
        return string;
    }
    private String dropObject(String name)  {
        String string = "";
        /* Object from player inventory */
        Thing thing = player.getThingList().thisThing(name);
        if(name.equals("")) string = "What object do you want to drop?";
        if(thing == null) string = "You don't have that object in your inventory";
        else {
            transferObject(thing, player.getThingList(), player.getCurrentRoom().getThingList());
            string = name + " dropped!";
        }
        return string;
    }
    /* Parse and process user input */
    private String processVerb(List<String> wordList) {
        String verb;
        verb = wordList.get(0);
        if (!commands.contains(verb)) System.out.println(verb+" is not a known verb!");
        else {
            switch (verb) {
                case "north":
                case "n":
                    goN();
                    break;
                case "south":
                case "s":
                    goS();
                    break;
                case "east":
                case "e":
                    goE();
                    break;
                case "west":
                case "w":
                    goW();
                    break;
                case "look":
                case "l":
                    GameMessages.lookAround(player);
                    break;
                case "inventory":
                case "i":
                    showPlayerInventory(playerInventory);
                    break;
                case "help":
                case "h":
                    showHelp(commands);
                default:
                    return verb+" [Not yet implemented]";
            }
        }
        return "";
    }
    private String processVerbNoun(List<String> wordList) {
        String verb, noun;
        verb = wordList.get(0);
        noun = wordList.get(1);
        if(!commands.contains(verb)) return verb+" is not a known verb!";
        if(!objects.contains(noun)) return noun+" is not a known noun!";
        return "";
    }
    public String parseCommand(List<String> wordList){
        String string = "";
        if(wordList.size() == 1) string = processVerb(wordList);
        else if (wordList.size() == 2) string += processVerbNoun(wordList);
        else string = "Only 2 word commands are allowed!";
        return string;
    }
    public List<String> wordList(String input) {
        String delimiters = "[ \t,.:;?!\"']+"; /* declare delimiters */
        List<String> wordList = new ArrayList<>();
        /* split input based on delimiters */
        StringTokenizer stringTokenizer = new StringTokenizer(input, delimiters);
        String string;
        while (stringTokenizer.hasMoreTokens()){
            string = stringTokenizer.nextToken();
            wordList.add(string);
        }
        return wordList;
    }
    public String runCommand(String input)  {
        List<String> wordList;
        String response = "ok";
        input = input.trim().toLowerCase();
        if(!input.equals("q")) {
            if (input.equals("")) response.equals("You must enter a command");
            else{
                wordList = wordList(input);
                parseCommand(wordList);
            }
        }
        return response;
    }
}