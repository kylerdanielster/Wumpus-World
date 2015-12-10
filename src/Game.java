import java.util.Random;

/**>
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 *
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 *
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 *
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.08.08
 */

public class Game
{
    private Room[][] rooms;
    private Parser parser;
    private Room currentRoom;
    private Robot player;
    private Wumpus wumpus;
    private Random random;
    private int NUM_OF_PITS = 4;
    private int NUM_OF_COOKIES = 2;

    /**
     * Create the game and initialise its internal map.
     */
    public Game()
    {
        random = new Random();
        createRooms();
        setMapExits();
        parser = new Parser();
        player = Robot.getInstance();
        wumpus = Wumpus.getInstance();
        addObstaclesAndItems();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        // create the rooms
        rooms = new Room[8][8];

        for(int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                rooms[i][j] = new Room(i + "," + j, i, j);
                System.out.print(" " + rooms[i][j].getShortDescription());
            }
            System.out.println();
        }
        // start the game at 0,0
        currentRoom = rooms[0][0];
    }


    private void setMapExits()
    {
         for(int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                // if statements for exits
                if( i - 1 >= 0)// if row > 0
                    rooms[i][j].setExit("north", rooms[i-1][j]);
                if( i < 7 )//if rom < 7
                    rooms[i][j].setExit("south", rooms[i+1][j]);
                if( j - 1 >= 0)//if col > 0
                    rooms[i][j].setExit("west", rooms[i][j-1]);
                if(j < 7)//if col < 7
                    rooms[i][j].setExit("east", rooms[i][j+1]);
            }
        }

    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play()
    {
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (! finished && player.getLives() > 0 && !rooms[0][0].thisHasObject()) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Total score: " + player.getScore());
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the Wumpus world!");
        System.out.println("Your only hope of escape is to find some gold and bring it back here.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command)
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("shoot")) {
            shootRoom(command);
        }
        else if (commandWord.equals("grab")) {
            grabItem(command);
        }
        else if (commandWord.equals("drop")) {
            dropItem(command);
        }
        else if (commandWord.equals("items")) {
            items();
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        // else command not recognised.
        return wantToQuit;
    }

    private void items()
    {
        System.out.println("Items:");
        player.showItems();
    }

    private void dropItem(Command command) {
        if(player.hasItems()) {
            currentRoom.addItem(player.dropItem());
        } else {
            System.out.println("No items to drop.");
        }
    }

    private void grabItem(Command command) {
        if(currentRoom.getItemDescription().equals(command.getSecondWord())) {
            if (currentRoom.getItemDescription().equals("cookie"))
            {
                currentRoom.removeSignal(currentRoom.getItem().getSignal());
                player.setWeightLimit(player.getWeightLimit() + 10);
                player.setScore(player.getScore() + 50 );
                System.out.println("You can now carry more weight.");
                currentRoom.removeItem();
                //TODO: VERIFY IT WORKS: remove signals
            } else {
                System.out.println("You got the gold");
                player.setScore(player.getScore() + 100);
                player.addItem(currentRoom.getItem());
                //TODO: VERIFY IT WORKS: remove signals
            }
        } else {
            System.out.println("Item not present.");
        }
    }

    private void shootRoom(Command command) {


        if(player.getArrow() == 0)
        {
            System.out.println("You have no arrows.");
            return;
        }

        if (!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Shoot where?");
            return;
        }

        String direction = command.getSecondWord();

        // check for a room in the desired direction
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is nothing to shoot!");
            return;
        }

        player.setArrow(player.getArrow() - 1);

        if(nextRoom.hasWumpus() == null)
        {
            System.out.println("You missed the Wumpus.");
        }

        if(nextRoom.hasWumpus() != null) {
            System.out.println("You killed the Wumpus");
            nextRoom.killWumpus();
            removeRoomSignals(nextRoom, wumpus.getSignal());
        }
    }

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the
     * command words.
     */
    private void printHelp()
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around the Wumpus world.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /**
     * Try to in to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command)
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            checkCurrentRoom();
            System.out.println(currentRoom.getLongDescription());
        }
    }

    private void checkCurrentRoom() {
        for(String signal : currentRoom.getSignals())
            System.out.println("This room is " + signal);

        if(currentRoom.hasWumpus() != null)
        {
            System.out.println("The Wumpus found you and ate you!");
            player.setLives(player.getLives() - 1);
            if(player.getLives() > 0) {
                System.out.println("You have one more chance.");
                currentRoom = rooms[0][0];
            }
        }

        if(currentRoom.hasPit() != null)
        {
            System.out.println("You fell into a pit and died.");
            player.setLives(player.getLives() - 1);
            if (player.getLives() > 0) {
                System.out.println("You have one more chance.");
                currentRoom = rooms[0][0];
            }
        }
    }

    private void addObstaclesAndItems()
    {
        int r, c;

        for(int i = 0; i < NUM_OF_PITS;)
        {
            r = random.nextInt(7) + 1;
            c = random.nextInt(7) + 1;
            if(!rooms[r][c].thisHasObject())
            {
                Pit pit = new Pit();
                rooms[r][c].setPit(pit);
                setRoomSignals(pit.getSignal(), r, c);
                i++;
                System.out.println("Pit: " + r + " " + c);
            }
        }

        for(int i = 0; i < NUM_OF_COOKIES;)
        {
            r = random.nextInt(7) + 1;
            c = random.nextInt(7) + 1;
            if(!rooms[r][c].thisHasObject())
            {
                Cookie cookie = new Cookie();
                rooms[r][c].addItem(cookie);
                rooms[r][c].setSignals(cookie.getSignal());
                i++;
                System.out.println("Cookie: " + r + " " + c);
            }
        }

        boolean hasWumpus = false;
        while(!hasWumpus)
        {
            r = random.nextInt(7) + 1;
            c = random.nextInt(7) + 1;
            if(!rooms[r][c].thisHasObject())
            {
                rooms[r][c].setWumpus(wumpus);
                setRoomSignals(wumpus.getSignal(), r, c);
                hasWumpus = true;
                System.out.println("Wumpus: " + r + " " + c);
            }
        }

        boolean hasGold = false;
        while (!hasGold)
        {
            r = random.nextInt(4) + 4;
            c = random.nextInt(4) + 4;
            if(!rooms[r][c].thisHasObject())
            {
                Gold gold = new Gold();
                rooms[r][c].addItem(gold);
                rooms[r][c].setSignals(gold.getSignal());
                hasGold = true;
                System.out.println("Gold: " + r + " " + c);
            }
        }
    }

    private void setRoomSignals(String signal, int r, int c)
    {
        rooms[r][c].setSignals(signal);
        rooms[r-1][c].setSignals(signal);
        rooms[r][c-1].setSignals(signal);
        if(r + 1 <  7)
            rooms[r+1][c].setSignals(signal);
        if(c + 1 < 7)
            rooms[r][c+1].setSignals(signal);
    }

    private void removeRoomSignals(Room room, String signal ) {
        int r = room.getX();
        int c = room.getY();

       //TODO: remove surrounding room signals
        rooms[r][c].removeSignal(signal);
        rooms[r-1][c].removeSignal(signal);
        rooms[r][c-1].removeSignal(signal);
        if(r + 1 <  7)
            rooms[r+1][c].removeSignal(signal);
        if(c + 1 < 7)
            rooms[r][c+1].removeSignal(signal);
    }

    /**
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command)
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
