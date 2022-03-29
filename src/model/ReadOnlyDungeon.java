package model;

import java.util.List;

/**
 * Read only Dungeon interface that is responsible for getting information from the model and model
 * state, but cannot facilitate functionalities that changes the state of the model.
 */
public interface ReadOnlyDungeon {

  /**
   * Gives the information regarding present location status of the player and possible moves from
   * the location and the treasure content of the location.
   *
   * @return the list of strings containing the required information.
   */
  List<String> getPlayerLocationStatus();

  /**
   * Gives the information regarding treasure collected by the player so far in the game.
   *
   * @return the list of strings containing the required information.
   */
  List<String> getInitialGameStatus();

  /**
   * Gives the location of the player, in which the player currently resides in.
   *
   * @return the location
   */
  Location getPlayerCurrentLocation();

  /**
   * Gives the percentage of caves to be filled with treasure..
   *
   * @return the treasure percentage
   */
  double getItemsPercentage();

  /**
   * Gives the location of the ending location of game, where the player finally reaches.
   *
   * @return the location
   */
  Location getStartLocation();

  /**
   * Gives the location of the starting location of game, where the player starts the game.
   *
   * @return the location
   */
  Location getEndLocation();

  /**
   * Gives the player description containing the status of player's items.
   *
   * @return the list of strings containing the required information.
   */
  List<String> getPlayerDescription();

  /**
   * Tells whether the game is over or not.
   *
   * @return boolean value indicating the game status.
   */
  boolean isGameOver();

  /**
   * Tells whether the player is alive or not.
   *
   * @return boolean value accordingly.
   */
  boolean isPlayerAlive();

  /**
   * Returns the number of rows used in creating the dungeon.
   *
   * @return integer value of rows
   */
  int getRows();

  /**
   * Returns the number of columns used in creating the dungeon.
   *
   * @return integer value of columns
   */
  int getColumns();

  /**
   * Returns the interconnectivity degree value used in creating the dungeon.
   *
   * @return interconnectivity degree
   */
  int getInterconnectivityDegree();

  /**
   * Tells whether the dungeon is a wrapping dungeon or not.
   *
   * @return boolean value accordingly.
   */
  boolean isWrapping();

  /**
   * Returns the difficulty number value used in creating the dungeon.
   *
   * @return difficulty value
   */
  int getDifficulty();

  /**
   * Returns the copy of the dungeon maze.
   *
   * @return 2D array of maze structure
   */
  Location[][] get2dDungeon();

  /**
   * Returns the locationIds of locations that player has visited.
   *
   * @return list of integers.
   */
  List<Integer> getVisited();

  /**
   * Returns the list of treasures that player has collected till that point.
   *
   * @return list of treasures.
   */
  List<TreasureType> getPlayerTreasure();

  /**
   * Returns the player's current health value.
   *
   * @return health value.
   */
  int getPlayerHealth();

  /**
   * Returns the type of stench at a given location.
   *
   * @param  location particular location in dungeon.
   * @return stench enum value.
   */
  Stench stenchCalculation(Location location);

}
