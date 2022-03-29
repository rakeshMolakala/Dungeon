package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class represents all the operations of Locations in the Dungeon, that may be tunnels or
 * caves with a unique locationId. The class is made package private as there's no need for the
 * location objects to be created outside the dungeon.
 */
class LocationC implements Location {

  private final Map<Direction, Location> neighbours;
  private final List<TreasureType> treasureList;
  private final int locationId;
  private Monster otyugh;
  private int arrowCount;
  private boolean hasPit;

  /**
   * Constructor that instantiates the location objects with the given locationId and initialises
   * the neighbours and treasureList associated with each location.
   *
   * @param locationId unique locationId for each location.
   */
  public LocationC(int locationId) {
    this.locationId = locationId;
    this.neighbours = new HashMap<>();
    this.treasureList = new ArrayList<>();
    this.otyugh = null;
    Monster movingMonster = null;
    this.arrowCount = 0;
    hasPit = false;
  }

  @Override
  public int getLocationId() {
    return this.locationId;
  }

  @Override
  public boolean getIsTunnel() {
    return this.getNeighbours().size() == 2;
  }

  @Override
  public List<TreasureType> getTreasureList() {
    return new ArrayList<>(this.treasureList);
  }

  @Override
  public Map<Direction, Location> getNeighbours() {
    return new HashMap<>(neighbours);
  }

  @Override
  public int getArrowCount() {
    return arrowCount;
  }

  @Override
  public boolean hasPit() {
    return hasPit;
  }

  @Override
  public boolean hasOtyugh() {
    return otyugh != null;
  }

  //All the below methods are made package private.
  void addTreasure(TreasureType treasure) {
    if (treasure == null) {
      throw new IllegalArgumentException("null treasure");
    }
    this.treasureList.add(treasure);
  }

  void removeTreasure(TreasureType treasure) {
    if (treasure == null) {
      throw new IllegalArgumentException("null treasure");
    }
    this.treasureList.remove(treasure);
  }

  void assignNeighbours(Direction d, Location l) {
    if (d == null || l == null) {
      throw new IllegalArgumentException("null location or direction enum");
    }
    this.neighbours.put(d, l);
  }

  void updateArrowsCount(int newCount) {
    this.arrowCount = newCount;
  }

  void addOtyugh(Monster otyugh) {
    this.otyugh = otyugh;
  }

  void killOtyugh() {
    this.otyugh = null;
  }

  Monster getOtyugh() {
    return this.otyugh;
  }

  void installPit() {
    this.hasPit = true;
  }

  /**
   * Returns the toString of the location.
   *
   * @return the string.
   */
  @Override
  public String toString() {
    if (this.getIsTunnel()) {
      return "Tunnel";
    } else {
      return "Cave";
    }
  }
}
