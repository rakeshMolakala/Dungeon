package model;

import java.util.List;
import java.util.Map;

/**
 * This interface represents the location that may be a Cave or Tunnel in the dungeon and is
 * associated with all the functionality of holding connections, treasures, arrows, monster.
 */
public interface Location {

  /**
   * Gives the locationId of the location.
   *
   * @return the locationId.
   */
  int getLocationId();

  /**
   * Gives the boolean indicating whether the location is tunnel or cave.
   *
   * @return the boolean value.
   */
  boolean getIsTunnel();

  /**
   * Gives the treasure list available in the location.
   *
   * @return the copy of list of treasures.
   */
  List<TreasureType> getTreasureList();

  /**
   * Gives the information regarding the connections associated with the location.
   *
   * @return the copy of Map object of directions as keys and connected location objects as values.
   */
  Map<Direction, Location> getNeighbours();

  /**
   * Returns the number of arrows the cave has.
   *
   * @return arrows count
   */
  int getArrowCount();

  /**
   * Tells whether the location has a pit or not.
   *
   * @return boolean value accordingly
   */
  boolean hasPit();

  /**
   * Tells whether the location has an Otyugh or not.
   *
   * @return boolean value accordingly
   */
  boolean hasOtyugh();
}
