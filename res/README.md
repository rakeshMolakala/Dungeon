1.  About/Overview Q    
    The problem statement's demand is to provide a GUI for the Dungeon model with the additional functionalities and implement a full-fledged MVC design pattern. One of the important tasks is to create a user-friendly GUI so that player can play the game with no technical knowledge and that is implemented accurately. Another important task is to delegate the functionality of execution by abiding by MVC principles and the controller is designed in such a way that it handles all the state changes of model and feedback is sent to view.

2.  List of Features 
    The features of the MVC Project includes:
    a) User can create a dungeon on his own parameters by entering the parameters in a form that pops up when the program is run.
    b) Users can quit the game at any point.
    c) User can go back to the initial game state by clicking the "Reuse" option from the "Game Controls" Jmenu.
    d) User can restart the game with the same configuration but a different dungeon by clicking the "Restart" option from "Game Controls" Jmenu.
    e) User can go back home by clicking the "Home" option from the "Game Controls" Jmenu.
    f) User can use mouse clicks on neighboring locations to move the player or can use arrow keys for the same movement.
    g) User can pick up treasure in a present location.
    h) User can pickup arrows in a present location.
    i) User can shoot an Otyugh at any point in time until he's dead.
    j) There's a moving thief in the dungeon and loots players' treasure when they are encountered.
    k) There's a moving monster in the dungeon and when the player is encountered with it, the user can take a hit on a moving monster or a monster can hit the player.
    l) Player Location status is displayed clearly at each location the player visits.
    m) Player Description status is displayed at every point of the game.
    n) Attack status section is made to view the feedback after the player performs any shoot action on otyugh or fight action on moving monster.
    o) Clear pop with message after game over is displayed or when the player gets killed.

3.  How to Run 
    For Text based Controller:
    The JAR file can be used by importing to IntelliJ or using Terminal/CMD by giving command line arguments
    Java -jar <filename> arg[0] arg[1] arg[2] arg[3] arg[4] arg[5]
    Command line arguments are in the following order:
    (true if Wrapping/false for NonWrapping, interconnectivity degree, number of rows, number of columns, itemsPercentage, number of monsters)
    Eg: Java -jar DungeonFinal.jar false 6 6 6 80 4;

4.  How to Use the Program 
    Run the JAR file 
    --Home screen pops up where the user can give input fields for dungeon creation.
    --After successful dungeon creation, the game screen pops up with the player residing in starting location.
    --Two JMenus are placed in the top middle section:
      "Dungeon Information" - which shows the dungeon information and respective parameters.
      "Game Controls" - which consists of following options:
            "Home" - takes the GUI to the Home screen.
            "Restart" - restarts the game with the same initial configuration but not the same dungeon.
            "Reuse" - resets the game with the initial state with the same dungeon and maze structure.
            "Quit" - closes the GUI and quits the game.
    --Game keys: 
      Keyboard keys
      Up arrow - Moves the player to North direction if the path exists.
      Down arrow - Moves the player to South direction if the path exists.
      Left arrow - Moves player to West direction if path exists.
      Right arrow - Moves player to East direction if path exists.
      T - picks up the treasure in the present location.
      A - picks up the arrows in the present location.
      S followed by respective arrows in above-mentioned directions generates a pop for the user to enter the number of caves, and this enables the player to shoot an arrow.
      --When a moving monster is encountered with the player, either the player or moving monster gets the chance to attack first, when it is of monster's chance it reduces the player health by 15 automatically and next the player turns come where he can press "F" keyboard key to launch a hit which reduces the moving monster's health by 25. After getting hit the moving monster escapes from the location. This happens throughout the game whenever they are encountered.
      --There's a moving thief inside the dungeon, he just keeps roaming through the dungeon and when encountered with the player he loots the player and player treasures get to zero.
      --There are also pits inside the dungeon where a player can fall and lose health of 20 points and there's also a pit detection that can be displayed in the location status section when there is a pit from the player's current location with a distance of 1.
    --Left part of GUI is dedicated to display three types of information about the game.
      "Left Top" - Displays the player description that consists of the player's treasure, arrow count, and player's health. (Heart symbol indicates health parameter) 
      "Left Middle" - Displays the attack status with two kinds of monsters. One is Otyugh and other is the Moving monster and there's health associated with moving monster which is right there. (Heart symbol indicates health parameter). When player hits an Otyugh, an image with Otyugh got struck with one arrows is dispalyed in this section right below the Otyugh icon.
      When the injured otyugh gets hit by another arrow, an image with Otyugh got struck with two arrows is dispalyed in this section right below the Otyugh icon. In the same way the health of the moving monster gets changed when player hits the monster by pressing key "F".
      "Left Bottom" - This section of the GUI displays player location status that displays the treasures available in the current location, arrows, smell detection, pit detection thorugh images.
    --In each graphical representation of location of dungeon, the corresponding treasure, arrows, and presence of monster in clealry displayed.
    --Player icon is displayed in player description.
    --Moving monster looks like Dinosaur.
    --Theif icon looks like a player icon with gold color.

    --For text based game:
    --M- Move- which asks for Direction as (N-S-W-E) which equivalents to (NORTH-SOUTH-WEST-EAST) and if unknown command other (N-S-W-E) is given, the textcontroller asks the user to enter again a valid direction.
    --P- Pick up items- which asks for (A-T) which equivalents to (ARROWS-TREASURES) and if unknown command other (A-T) is given, the textcontroller asks the user to enter again a valid specification for pickup.
    --S- Shoot an arrow- which asks for the number of caves in the range of (1-5) and if any invalid input like string is passed, the textcontroller asks the user to enter again a number of caves. Next, the textcontroller asks for Direction to shoot as (N-S-W-E) which equivalents to (NORTH-SOUTH-WEST-EAST) and if unknown command other (N-S-W-E) is given, the textcontroller asks the user to enter again a valid direction.
    --After each and every (M-P-S)  a particular feedback is returned and the play is expected to use that information and proceed for the next command to enter.
    --Game ends when the player kills the monster in the final cave and enters the final cave or when he gets eaten by a monster.
    --The textcontroller keep on taking inputs from the user until he finishes the game/loses the game/gives the quit comma
    
5.  Description of Examples 
    screenshots are placed in snap folder in res
    (1) double hit otyugh-min.png - displays the feedback after shooting an Otyugh twice in the left middle section of frame.
    (2) Dungeon info-min.png - displays the dungeon information JMenu at top of the frame.
    (3) game controls-min.png - displays game controls JMenu at top of the frame.
    (4) home-min.png - home panel where input parameters for dungeon creation is taken.
    (5) meeting move monster-min.png - displays player meeting moving monster.
    (6) meeting Thief-min.png - displays player meeting thief
    (7) pit and single hit Otyugh-min.png - displays player falling in thief and also a single hit Otyugh.
    (8) pit detection and less pungent-min.png - pit detection is displayed in the player location status section and less pungent smell.
    (9) player win-min.png - player winning by reaching end cave
    (10) player dead-min.png - player dead by getting eaten by Otyugh.

6.  Design/Model Changes 
    There have been a few changes in the design for this model due to the addition of additional functionalities. I have used square pattern to implement the additional functionalities and not by disturbing the previous model and controller. I have implemented a new Readonly model that extends the old one and new dungoen interface that extends the old interface and new readonly model and a new maze class that extends old maze class and implements new dungeonInterface. By doing so, I made the new functionality changes.

7.  Assumptions 
    Player picks up all the treasure in the cave when requested for a pickup.
    Player picks up all the arrows in the location when requested for a pickup.
    Player can only shoot at a maximum of 5 number of caves.
    Game overs when the player reaches the final cave or gets eaten by an Otyugh in the process or when health becomes zero due to continous fall in pits or when recieves continuous hits from moving monster.
    Number of pits added is equal to number of monsters minus 1.
    Only one moving thief in the dungoen
    Only one moving monster in the dungeon.
    Items percentage cannot be greater than 100 and less than zero.

8.  Limitations 
    Model won’t facilitate player choosing the number of items or specific items to pick up.
    Moving monster and player engages in hand to hand combat for one round when they are met.
    Less space to display each and every detail in cave.
    There exists an invalid state where a player can win a game by not killing the Otyugh at the end completely, and the chance of this scenario is to happen is 50%, which seems not meaningful.

9.  Citations 
    Bloch, J. (2018). Effective Java.
    https://www.geeksforgeeks.org/disjoint-set-data-structures/
    https://stackoverflow.com/questions/9460325/setting-the-maximum-size-of-a-jframe-while-launching-the-application
    https://www.geeksforgeeks.org/java-swing-simple-user-registration-form/
    https://docs.oracle.com/javase/tutorial/uiswing/misc/keybinding.html
    
