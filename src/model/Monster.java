package model;

//This Interface is made package private as it serves no useful purpose for the user.
/**
 * Represents the monsters that are placed in caves of the dungeon.
 */
interface Monster {

  /**
   * Returns the unique monsterId allotted to every monster.
   *
   * @return the monsterId
   */
  int getMonsterId();

  /**
   * Returns the number of arrows/blows that the monster got hit.
   *
   * @return number of arrows
   */
  int getArrowHit();

  /**
   * Record hit when a monster is hit by an arrow or by player in hand to hand comabt .
   */
  void recordHit();

  /**
   * Returns the location that the monster currently resided.
   *
   * @return location object
   */
  Location getLocation();

  /**
   * Updates the location of moving monster.
   *
   * @param l new location
   */
  void setLocation(Location l);


}
