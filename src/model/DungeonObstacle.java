package model;

import java.util.List;


/**
 * Dungeon Obstacle interface that extends the Dungeon interface and adds certain additional
 * functionality.
 */
public interface DungeonObstacle extends ReadOnlyDungeonObstacle, Dungeon {

  /**
   * Enables the player to land a strike on moving monster whenever they are encountered.
   */
  void fight();

  /**
   * Returns the initial state of the model with no change in states.
   *
   * @return DungeonObstacle model object
   */
  DungeonObstacle getInitialModel();

  /**
   * Changes the random object used in the model to real random when random predictive is being
   * used.
   */
  void setRandom();

  /**
   * Gives the list of random numbers used in creating the dungeon.
   *
   * @return list of random numbers
   */
  List<Integer> getPrevRandom();
}
