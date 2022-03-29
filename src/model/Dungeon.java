package model;

/**
 * Dungeon interface that represents the model of the game and incorporates players movements and
 * many other functionalities.
 */
public interface Dungeon extends ReadOnlyDungeon {

  /**
   * Moves to the player in the direction given, if exists a location in that direction.
   *
   * @param direction enum.
   */
  void movePlayerTo(Direction direction);

  /**
   * Enables the player to pick up treasures at the current location.
   */
  void pickUpTreasure();

  /**
   * Enables the player to pick up arrows at the current location.
   */
  void pickUpArrows();

  /**
   * Enables the player to shoot an arrow in the given direction and the given distance.
   */
  void shoot(Direction d, int s);

  /**
   * Moves to the player to the given location id.
   *
   * @param locationId locationId
   */
  void moveToLocation(int locationId);

}
