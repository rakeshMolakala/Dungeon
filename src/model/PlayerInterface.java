package model;

import java.util.List;

/**
 * Represents the Player to be played in the dungeon game.
 */
interface PlayerInterface {

  /**
   * Returns the treasure list collected by the user.
   *
   * @return the copy of the treasure list.
   */
  List<TreasureType> getTreasureCollected();

  /**
   * Gives the description of the player so far in the game.
   *
   * @return the list of strings containing the required information.
   */
  List<String> getPlayerDescription();

  /**
   * Gives the number of arrows that the player currently has.
   *
   * @return the count of arrows.
   */
  int getArrows();

  /**
   * Gives the player's current health.
   *
   * @return health value.
   */
  int getHealth();

  /**
   * Reduces the health when the player is hit by a moving monster or get killed by an Otyugh
   * or when fallen into a pit.
   *
   * @param healthToReduce damage value the player took.
   */
  void reduceHealth(int healthToReduce);

  /**
   * Adds the treasure to player treasure list when picked up from the cave.
   *
   * @param treasure enum.
   */
  void addTreasure(TreasureType treasure);

  /**
   * Updates player's arrow count when they are used or picked up.
   *
   * @param newArrowsCount integer value.
   */
  void updateArrows(int newArrowsCount);

  /**
   * Updates player treasure when thief is encountered in the dungeon.
   *
   * */
  void lootTreasure();

  /**
   * Tells whether player is alive or not.
   *
   * @return boolean value accordingly.
   */
  boolean isAlive();
}
