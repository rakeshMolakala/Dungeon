# Dungeon

## Description

The world of our game consists of a dungeon, a network of tunnels and caves that are interconnected so that player can explore the entire world by traveling from cave to cave through the tunnels that connect them. Each location in the maze represents a location in the dungeon where a player can explore and can be connected to at most four (4) other locations: one to the north, one to the east, one to the south, and one to the west. The player is provided with initial settings of the game to create a customized dungeon to play with. The game begins by player starting from the start cave and need to reach the uncertain end cave to complete the game. Several obstacles like monsters, pits, theifs are placed in the caves to prevent player from reaching the end and player has his trust worthy arrow to shoot and kill the monsters on his way.

This project facilitates the use of many advanced design patterns like MVC, Command, Square, Singleton, Observer and Builder to write and maintain code in extensible and loosely coupled fashion. All the solid principles and proper object oriented design practices in developing the application. Delegation of functionality of each class in proper section is carried out well abiding the MVC principles.

## Design 

<img width="1512" alt="design" src="https://user-images.githubusercontent.com/91920989/185758149-5e7865f2-1273-4aac-ba01-cb36a5aa6667.png">

## Demo

https://user-images.githubusercontent.com/91920989/185759162-c45fd5bc-3565-437f-b08a-1593158a4782.mov


## List of Features

* User can create a dungeon on his own parameters by entering the parameters in a form that pops up when the program is run. 
* Users can quit the game at any point. 
* User can go back to the initial game state by clicking the "Reuse" option from the "Game Controls" Jmenu. 
* User can restart the game with the same configuration but a different dungeon by clicking the "Restart" option from "Game Controls" Jmenu. 
* User can go back home by clicking the "Home" option from the "Game Controls" Jmenu. 
* User can use mouse clicks on neighboring locations to move the player or can use arrow keys for the same movement. 
* User can pick up treasure in a present location. 
* User can pickup arrows in a present location. 
* User can shoot an Otyugh at any point in time until he's dead. 
* There's a moving thief in the dungeon and loots players' treasure when they are encountered.
* There's a moving monster in the dungeon and when the player is encountered with it, the user can take a hit on a moving monster or a monster can hit the player. 
* Player Location status is displayed clearly at each location the player visits. 
* Player Description status is displayed at every point of the game. 
* Attack status section is made to view the feedback after the player performs any shoot action on otyugh or fight action on moving monster. 
* Clear pop with message after game over is displayed or when the player gets killed.

## Dependencies

* Independent of OS 
* Supports Java 8 and any later versions 

## Executing program

* Download the JAR file in res folder of repo
* Run the JAR file
```
java -jar DungeonFinal.jar
```

## Game controls

* Run the JAR file 
* Home screen pops up where the user can give input fields for dungeon creation. 
* After successful dungeon creation, the game screen pops up with the player residing in starting location. 
* Two JMenus are placed in the top middle section: 
    * "Dungeon Information" - which shows the dungeon information and respective parameters. 
    * "Game Controls" - which consists of following options: 
    * "Home" - takes the GUI to the Home screen. 
    * "Restart" - restarts the game with the same initial configuration but not the same dungeon.
    * "Reuse" - resets the game with the initial state with the same dungeon and maze structure.
    * "Quit" - closes the GUI and quits the game. 
* Game keys: 
    * Keyboard keys 
        * Up arrow - Moves the player to North direction if the path exists. 
        * Down arrow - Moves the player to South direction if the path exists. 
        * Left arrow - Moves player to West direction if path exists. 
        * Right arrow - Moves player to East direction if path exists. 
    * T - picks up the treasure in the present location. 
    * A - picks up the arrows in the present location. 
    * S followed by respective arrows in above-mentioned directions generates a pop up for the user to enter the number of caves, and this enables the player to shoot an arrow. 
    * When a moving monster is encountered with the player, either the player or moving monster gets the chance to attack first, when it is of monster's chance it reduces the player health by 15 automatically and next the player turns come where he can press "F" keyboard key to launch a hit which reduces the moving monster's health by 25. After getting hit the moving monster escapes from the location. This happens throughout the game whenever they are encountered. 
    * There's a moving thief inside the dungeon, he just keeps roaming through the dungeon and when encountered with the player he loots the player and player treasures get to zero. 
    * There are also pits inside the dungeon where a player can fall and lose health of 20 points and there's also a pit detection that can be displayed in the location status section when there is a pit from the player's current location with a distance of 1. 
    * Left part of GUI is dedicated to display three types of information about the game. 
       * "Left Top" - Displays the player description that consists of the player's treasure, arrow count, and player's health. (Heart symbol indicates health parameter) 
       * "Left Middle" - Displays the attack status with two kinds of monsters. One is Otyugh and other is the Moving monster and there's health associated with moving monster which is right there. (Heart symbol indicates health parameter). When player hits an Otyugh, an image with Otyugh got struck with one arrows is dispalyed in this section right below the Otyugh icon. When the injured otyugh gets hit by another arrow, an image with Otyugh got struck with two arrows is dispalyed in this section right below the Otyugh icon. In the same way the health of the moving monster gets changed when player hits the monster by pressing key "F". 
       * "Left Bottom" - This section of the GUI displays player location status that displays the treasures available in the current location, arrows, smell detection, pit detection thorugh images. 
* In each graphical representation of location of dungeon, the corresponding treasure, arrows, and presence of monster in clealry displayed. 
* Player icon is displayed in player description. --Moving monster looks like Dinosaur. 
* Theif icon looks like a player icon with gold color.



## Limitations
 * Model won’t facilitate player choosing the number of items or specific items to pick up.
 * Moving monster and player engages in hand to hand combat for one round when they are met. 
 * Less space to display each and every detail in cave.
 * There exists an invalid state where a player can win a game by not killing the Otyugh at the end completely, and the chance of this scenario is to happen is 50%, which seems not meaningful.


## Authors

@RakeshMolakala


## Citations
* Bloch, J. (2018). Effective Java
* https://www.geeksforgeeks.org/disjoint-set-data-structures/
* https://stackoverflow.com/questions/9460325/setting-the-maximum-size-of-a-jframe-while-launching-the-application 
* https://www.geeksforgeeks.org/java-swing-simple-user-registration-form/ 
* https://docs.oracle.com/javase/tutorial/uiswing/misc/keybinding.html

