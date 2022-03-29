package model;

//This class is made package private as it serves no useful purpose for the user.
/**
 * Represents Otyugh, a particular type of monster associated in the dungeon.
 */
class Otyugh implements Monster {
  private final int monsterId;
  private int arrowHit;
  private Location location;

  /**
   * Constructs the Otyugh monster object and assigns the parameter monsterId and
   * current location.
   *
   * @param monsterId unique integer for every monster
   * @param location current location where the monster is added.
   */
  public Otyugh(int monsterId, Location location) {
    this.monsterId = monsterId;
    this.arrowHit = 0;
    this.location = location;
  }

  @Override
  public int getMonsterId() {
    return this.monsterId;
  }

  @Override
  public int getArrowHit() {
    return this.arrowHit;
  }

  @Override
  public void recordHit() {
    this.arrowHit = this.arrowHit + 1;
  }

  @Override
  public Location getLocation() {
    return location;
  }

  @Override
  public void setLocation(Location l) {
    this.location = l;
  }

}
