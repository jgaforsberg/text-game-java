package game;

import gameobjects.*;
import gameobjects.Character;
import globals.Direction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import static game.GameMessages.*;
import static game.GameMessages.showInventory;

public class Game {
    /*
     * Initialize map, characters, commands, objects
     * */
    private Character player, monster, dragon;
    private ThingList playerInventory;
    private ArrayList<Room> alMap;
    /*
     * For parsing user string input
     */
    protected List <String> commands = new ArrayList<>(Arrays.asList(
            "take", "drop", "look",
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
        ThingList testRoomList = new ThingList();
        testRoomList.add(new Treasure("snake", "A tiny copper snake", 0));
        ThingList mapRoomList = new ThingList();
        mapRoomList.add(new Treasure("ring", "A shiny copper ring", 1));
        ThingList instRoomList = new ThingList();
        instRoomList.add(new Treasure("sword", "A sword, maybe of destiny", 10));
        this.alMap = new ArrayList<Room>();
        alMap.add(new Room("Start Room", "This is a start room description", -1, 2, -1, 1, testRoomList));
        alMap.add(new Room("Map Room", "This is a map room description", -1, -1, 0, -1, mapRoomList));
        alMap.add(new Room("Declaration Room", "This is a declaration room description", 0, -1, -1, 3, new ThingList()));
        alMap.add(new Room("Instigator Room", "This is a instigator room description", -1, -1, 2, -1, instRoomList));
        /* Initialize the characters */
        playerInventory = new ThingList();
        player = new Character("You", "A too old for this shit player character", alMap.get(0), 10, 1, playerInventory);
        monster = new Character("Monster", "A monster", alMap.get(3), 5, 1, new ThingList());
        dragon = new Character("Dragon", "The dragon", alMap.get(3), 20, 2, new ThingList());
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
        return moveCharacterTo(monster, direction);
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
                case "n":
                    goN();
                    break;
                case "s":
                    goS();
                    break;
                case "e":
                    goE();
                    break;
                case "w":
                    goW();
                    break;
                case "look":
                case "l":
                    GameMessages.lookAround(player);
                    break;
                case "inventory":
                case "i":
                    showInventory(playerInventory);
                    break;
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