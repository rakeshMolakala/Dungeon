package model;

/**
 * Read only Dungeon Obstacle interface which extends the Read only dungeon model by providing
 * additional information regarding the certain additional functionalities.
 */
public interface ReadOnlyDungeonObstacle extends ReadOnlyDungeon {

  /**
   * Tells whether there is a pit in surroundings to player current location.
   *
   * @return boolean value accordingly.
   */
  boolean isPitNearBy();

  /**
   * Gives the location id of the thief current location.
   *
   * @return integer value of location id.
   */
  int getThiefLocationId();

  /**
   * Gives the location id of the moving monster's current location.
   *
   * @return integer value of location id.
   */
  int getMovingMonsterLocationId();

  /**
   * Gives the current health of moving monster.
   *
   * @return integer value of monster's health..
   */
  int getMovingMonsterHealth();

  /**
   * Tells whether the moving monster in alive or not.
   *
   * @return boolean value accordingly.
   */
  boolean isMovingMonsterAlive();
}
