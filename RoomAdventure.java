//||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||\\
// Members: William Deere - Rooms 3-8, handleDrop method
//          Drew Sylve - visual for items when picked up, 
//          RJ - 
//          Rajan - 
// Date: 5/16/2025
// Assignment: Pi Activity - Room Adventure
//||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||\\

import java.util.Scanner;  // Import Scanner for reading user input

public class RoomAdventure {  // Main class containing game logic

    // class variables
    private static Room currentRoom;  // The room the player is currently in
    private static String[] inventory = {null, null, null, null, null, null};  // Player inventory slots
    private static String status;  // Message to display after each action

    // constants                                 // Default error message
    final private static String DEFAULT_STATUS = "Sorry, I do not understand. Try [verb] [noun]. Valid verbs include 'go', 'look', and 'take'."; 

    //method for item visuals
    private static String getAsciiArt(String itemName) {
    switch (itemName) {
        case "key":
            return "  __\n /o \\_____ \n \\__/-='='`\n    Key"; 
        case "coal":
            return "  ( )\n (   )\n(     )\n  Coal";
        case "book":
            return " _______\n/      /,\n\\_____/ / \n     /_/  \n   Book";
        case "dagger":
            return "  /\\\n ||\n ||\n ||\n ||\n ||\n/==\\\nDagger";
        case "lantern":
            return "  .-.\n (   )\n  '-' \n  ||| \n Lantern";
        default:
            return "";  // No visual if item doesn't have art
        }
    }
    
    //|||||||||||||||||||||||||||||||||||\\
    // Method: handle{verb}                                                                                                                       
    // Purpose: Processes player actions    
    //|||||||||||||||||||||||||||||||||||\\                                                                                          
    public static void handleGo(String noun) {  // Handles moving between rooms
        String[] exitDirections = currentRoom.getExitDirections();      // Get available directions
        Room[] exitDestinations = currentRoom.getExitDestinations();    // Get rooms in those directons
        status = "I don't see that room.";      // Default if direction not found
        for (int i = 0; i < exitDirections.length; i++) {  // Loop through direction
            if (noun.equals(exitDirections[i])) {          // If user direction matches
                currentRoom = exitDestinations[i];         // Change current room
                status = "Changed Room";                   // Update status 
            }
        }
    }
    
    private static void handleLook(String noun) {   // Handles inspecting things
        String[] items = currentRoom.getItems();    // Visible items in current room
        String[] itemDescriptions = currentRoom.getItemDescriptions();      // Descriptions for each item
        status = "I don't see that item.";         // Default if item not found
        for (int i = 0; i < items.length; i++) {   // Loop through items
            if (noun.equals(items[i])) {           // If item matches noun
                status = itemDescriptions[i];      // Update status
            }
        }
    }

    private static void handleTake(String noun) {       // Handles taking things
        String[] grabbables = currentRoom.getGrabbables();          // Get list of grabbable items
        status = "I can't grab that.";      // Default if item not found
        for (String item : grabbables) {    // Loop through grabbable items
            if (noun.equals(item)) {        // If item matches noun
                for (int j = 0; j< inventory.length; j++){          // Loop through inventory slots
                    if (inventory[j] == null) {         // If empty slot found
                        inventory [j] = noun;           // Add item to inventory
                        status = "Added it to the inventory.\n"+getAsciiArt(item);      // Update status
                        break;                                         
                    }
                }
            }
        }
    }

    private static void handleDrop(String noun) {      // Handles dropping things
        status = "You don't have that item.";          // Default message
        for (int i = 0; i < inventory.length; i++) {   // Loop through inventory
            if (noun.equals(inventory[i])) {           // If item matches
                inventory[i] = null;                   // Remove it from inventory
                status = "Dropped the item.";          // Update status
                break;
            }
        }
    }

    //||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||\\
    // Method: setupGame                                                                                                                          
    // Purpose: Initializes the game world by creating and configuring all rooms, their connections, items, item descriptions, and grabbables   
    //||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||\\

    private static void setupGame() {          // Initialize game world
        Room room1 = new Room("Room 1");  // Create Room 1 
        Room room2 = new Room("Room 2");  // Create Room 2
        Room room3 = new Room("Room 3");  // Create Room 3
        Room room4 = new Room("Room 4");  // Create Room 4 
        Room room5 = new Room("Room 5");  // Create Room 5
        Room room6 = new Room("Room 6");  // Create Room 6
        Room room7 = new Room("Room 7");  // Create Room 7
        Room room8 = new Room("Room 8");  // Create Room 8


        // Room 1 Config \\
        /* ###################################################################################### */
        String[] room1ExitDirections = {"east"};    // Room 1 exits
        Room[] room1ExitDestinations = {room2};     // Destination rooms for Room 1 
        String[] room1Items = {"chair", "desk"};    // Items in Room 1 
        String[] room1ItemDescriptions = {          // Descriptions for Room 1 items
            "It is a chair", 
            "It's a desk, there is a key on it."    
        };
        String[] room1Grabbables = {"key"}; 
        room1.setExitDirections(room1ExitDirections);
        room1.setExitDestinations(room1ExitDestinations);
        room1.setItems(room1Items);
        room1.setItemDescriptions(room1ItemDescriptions);
        room1.setGrabbables(room1Grabbables);
        /* ###################################################################################### */


        // Room 2 Config \\
        /* ###################################################################################### */
        String[] room2ExitDirections = {"west", "east"};  // Room 2 exits
        Room[] room2ExitDestinations = {room1, room3};    // Destination rooms for Room 2
        String[] room2Items = {"fireplace", "rug"};       // Itmes in Room 2 
        String[] room2ItemDescriptions = {                // Descriptions for Room 2 items
            "It's on fire.", 
            "There is a lump of coal on it."    
        };
        String[] room2Grabbables = {"coal"}; 
        room2.setExitDirections(room2ExitDirections);
        room2.setExitDestinations(room2ExitDestinations);
        room2.setItems(room2Items);
        room2.setItemDescriptions(room2ItemDescriptions);
        room2.setGrabbables(room2Grabbables);             // Set grabbable items
        /* ###################################################################################### */


        // Room 3 Config \\
        /* ###################################################################################### */
        String[] room3ExitDirections = {"west", "north"}; // Room 3 exits
        Room[] room3ExitDestinations = {room2, room4};    // Destination rooms for Room 3
        String[] room3Items = {"bookshelf", "painting"};  // Items in Room 3
        String[] room3ItemDescriptions = {                // Descriptions for Room 3 items
            "It's full of old dusty books.", 
            "It's a painting of a strange eye. You feel watched."    
        };
        String[] room3Grabbables = {"book"}; 
        room3.setExitDirections(room3ExitDirections);
        room3.setExitDestinations(room3ExitDestinations);
        room3.setItems(room3Items);
        room3.setItemDescriptions(room3ItemDescriptions);
        room3.setGrabbables(room3Grabbables);              // Set grabbable items
        /* ###################################################################################### */


        // Room 4 Config \\
        /* ###################################################################################### */
        String[] room4ExitDirections = {"south", "north"};      // Room 4 exits
        Room[] room4ExitDestinations = {room3, room5};          // Destination rooms for Room 4
        String[] room4Items = {"cabinet", "mirror"};            // Items in Room 4
        String[] room4ItemDescriptions = {                      // Descriptions for Room 4 items
            "The cabinet is missing all of its drawers, and is quite dusty", 
            "The mirror is cracked full of shards. Your reflection blinks, but you didn't..."    
        };
        String[] room4Grabbables = {"shard"}; 
        room4.setExitDirections(room4ExitDirections);
        room4.setExitDestinations(room4ExitDestinations);
        room4.setItems(room4Items);
        room4.setItemDescriptions(room4ItemDescriptions);
        room4.setGrabbables(room4Grabbables);                    // Set grabbable items
        /* ###################################################################################### */

        
        // Room 5 Config \\
        /* ###################################################################################### */
        String[] room5ExitDirections = {"south", "east"};    // Room 5 exits
        Room[] room5ExitDestinations = {room4, room6};       // Destination rooms for Room 5
        String[] room5Items = {"statue", "wall"};            // Items in Room 5
        String[] room5ItemDescriptions = {                   // Descriptions for Room 5 items
            "A marble statue with a missing hand.",
            "The wall has a knife-pinned note that reads: 'What's 1000 - 7?' "
        };
        String[] room5Grabbables = {"note"}; 
        room5.setExitDirections(room5ExitDirections);
        room5.setExitDestinations(room5ExitDestinations);
        room5.setItems(room5Items);
        room5.setItemDescriptions(room5ItemDescriptions);
        room5.setGrabbables(room5Grabbables);
        /* ###################################################################################### */


        // Room 6 Config \\
        /* ###################################################################################### */
        String[] room6ExitDirections = {"west", "east"};      // Room 6 exits
        Room[] room6ExitDestinations = {room5, room7};        // Destination rooms for Room 6
        String[] room6Items = {"table", "cupboard"};          // Items in Room 6
        String[] room6ItemDescriptions = {                    // Descriptions for Room 6 items
            "The table is scratched and stained. A marble hand lays on a plate.",
            "Inside is a cup, chipped and full of dust."
        };
        String[] room6Grabbables = {"cup", "hand"}; 
        room6.setExitDirections(room6ExitDirections);
        room6.setExitDestinations(room6ExitDestinations);
        room6.setItems(room6Items);
        room6.setItemDescriptions(room6ItemDescriptions);
        room6.setGrabbables(room6Grabbables);
        /* ###################################################################################### */


        // Room 7 Config \\
        /* ###################################################################################### */
        String[] room7ExitDirections = {"west", "south"};  // Room 7 exits
        Room[] room7ExitDestinations = {room6, room8};     // Destination rooms for Room 7
        String[] room7Items = {"books", "lantern"};        // Items in Room 7
        String[] room7ItemDescriptions = {                 // Descriptions for Room 7 items
            "Books are scattered and torn.",
            "The lantern flickers despite having no fuel."
        };
        String[] room7Grabbables = {"lantern"}; 
        room7.setExitDirections(room7ExitDirections);
        room7.setExitDestinations(room7ExitDestinations);
        room7.setItems(room7Items);
        room7.setItemDescriptions(room7ItemDescriptions);
        room7.setGrabbables(room7Grabbables);
        /* ###################################################################################### */


        // Room 8 Config \\
        /* ###################################################################################### */
        String[] room8ExitDirections = {"north"};      // Room 8 exits
        Room[] room8ExitDestinations = {room7};        // Destination rooms for Room 8
        String[] room8Items = {"door", "bed"};         // Items in Room 8
        String[] room8ItemDescriptions = {             // Descriptions for Room 8 items
            "The door is rusted shut.",
            "The bed is ancient, but perfectly made. A thin layer of dust coats the top, and beneath the pillow you spot a dagger."
        };
        String[] room8Grabbables = {"dagger"}; 
        room8.setExitDirections(room8ExitDirections);
        room8.setExitDestinations(room8ExitDestinations);
        room8.setItems(room8Items);
        room8.setItemDescriptions(room8ItemDescriptions);
        room8.setGrabbables(room8Grabbables);
        /* ###################################################################################### */


        currentRoom = room1; // Start game in Room 1

    }


    //||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||\\
    // Method: main                                                                                                                               
    // Purpose: Entry point for the game, handles user input and command processing                                                               
    //||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||\\

    public static void main(String[] args) {             // Entry point of the program
        setupGame();                                     // Initilaize rooms, items, and starting room
        while (true) {                                   // Game loop, runs until orogram is terminated
            System.out.print(currentRoom.toString());    // Display current room description
            System.out.print("Inventory: ");           // Prompt for inventory display

            for (int i = 0; i < inventory.length; i++) { // Loop through inventory slots
                System.out.print(inventory[i] + " ");    // Print each inventory item
            }

            System.out.println("\nWhat would you like to do? ");   // Prompt user for next action

            Scanner s = new Scanner(System.in);       // Create Scanner to read input
            String input = s.nextLine();              // Read the entire line of input 
            String[] words = input.split(" ");  // Split input into words

            if (words.length != 2) {                  // Check for proper two-word command
                status = DEFAULT_STATUS;              // Set status to error message
                continue;                             // Skip to the next loop iteration    
            }

            String verb = words[0];      // Frist word is the action verb
            String noun = words[1];      // Second word is the target noun

            switch (verb) {                  // Decide which action to take 
                case "go":                   // If verb is 'go'
                    handleGo(noun);
                    break;
                case "look":                 // If verb is 'look'
                    handleLook(noun);
                    break;
                case "take":                 // If verb is 'take'
                    handleTake(noun);
                    break;
                case "drop":
                    handleDrop(noun);
                    break;
                default:
                    status = DEFAULT_STATUS; // Set status to error message    
            }

            System.out.println(status); // Print the status message
        }

    }

}


//||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||\\
// Class: Room                                                                                                                                
// Purpose: Defines a room with exits, directions, destinations, items, grabbables, and descriptions.                                         
//||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||\\

class Room {                           // Represets a game room
    private String name;               // Room name
    private String[] exitDirections;   // Directions you can go
    private Room[] exitDestinations;   // Rooms reached by each direction
    private String[] items;            // Items visible in the room
    private String[] itemDescriptions; // Descriptions for those items
    private String[] grabbables;       // Items you can take

    public Room(String name) { // Constructor
        this.name = name;      // Set the room's name
    }

    public void setExitDirections(String[] exitDirections) {  // Setter for exits
        this.exitDirections = exitDirections;                 
    }                                                        
    public String[] getExitDirections() {  // Getter for exits
        return exitDirections;
    }
    
    public void setExitDestinations(Room[] exitDestinations) {  // Setter for exit destinations
        this.exitDestinations = exitDestinations;             
    }                                                        
    public Room[] getExitDestinations() {  // Getter for exit destinations
        return exitDestinations;                           
    }                                                     

    public void setItems(String[] items) { // Setter for items 
        this.items = items;
    }
    public String[] getItems() { // Getter for items
        return items;
    }

    public void setItemDescriptions(String[] itemDescriptions) { // Setter for descriptions
        this.itemDescriptions = itemDescriptions;
    }
    public String[] getItemDescriptions() { // Getter for descriptions
        return itemDescriptions;
    }

    public void setGrabbables(String[] grabbables) { // Setter for grabbable items 
        this.grabbables = grabbables;
    }
    public String[] getGrabbables() { // Getter for grabbable items
        return grabbables;
    }

    @Override
    public String toString() {  // Custom print for the room
        String result = "\nLocation: " + name;  // Show room name
        result += "\nYou See: ";     // List items
        for (String item : items) {  // Loop Items
            result += item + " ";    // Append each item
        }
        result += "\nExits: ";  // List exits
        for (String direction : exitDirections) {  // Loop exits
            result += direction + " ";  //Append each direction
        }
        return result + "\n";  //Return full description 
    }
}
