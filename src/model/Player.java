package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * This class represents functionality associated with the Player objects that are passed to the
 * dungeon to play the game and each player is associated with a name and a treasure list.
 */
class Player implements PlayerInterface {
  private List<TreasureType> treasureList;
  private int arrows;
  private int health;

  /**
   * Constructs the Player interface object and assigns the parameter.
   *
   * @throws IllegalArgumentException for invalid argument.
   */
  public Player() {
    this.treasureList = new ArrayList<>();
    this.arrows = 3;
    this.health = 100;
  }

  /**
   * Returns the treasure list collected by the player.
   *
   * @return the copy treasure list.
   */
  @Override
  public List<TreasureType> getTreasureCollected() {
    return new ArrayList<>(this.treasureList);
  }

  /**
   * Gives the description of the player so far in the game.
   *
   * @return the list of strings containing the required information.
   */
  @Override
  public List<String> getPlayerDescription() {
    List<String> playerDescription = new ArrayList<>();
    playerDescription.add(String.format("Treasures: %s",
            getCombinedTreasure(this.getTreasureCollected())));
    playerDescription.add(String.format("Arrows: %s", this.getArrows()));
    return new ArrayList<>(playerDescription);
  }

  @Override
  public int getArrows() {
    return this.arrows;
  }

  @Override
  public int getHealth() {
    if (this.health < 0) {
      return 0;
    }
    return this.health;
  }

  @Override
  public void reduceHealth(int healthToReduce) {
    this.health = this.health - healthToReduce;
  }

  @Override
  public void addTreasure(TreasureType treasure) {
    if (treasure == null) {
      throw new IllegalArgumentException("null treasure");
    }
    this.treasureList.add(treasure);
  }

  @Override
  public void updateArrows(int newArrowsCount) {
    this.arrows = newArrowsCount;
  }

  @Override
  public void lootTreasure() {
    this.treasureList = new ArrayList<>();
  }

  @Override
  public boolean isAlive() {
    return this.health > 0;
  }

  private Map<TreasureType, Integer> getCombinedTreasure(List<TreasureType> x) {
    if (x == null) {
      throw new IllegalArgumentException("null value");
    }
    Map<TreasureType, Integer> total = new HashMap<>();
    int d = 0;
    int r = 0;
    int s = 0;
    for (TreasureType tt : x) {
      if (tt == TreasureType.DIAMONDS) {
        d = d + 1;
      }
      if (tt == TreasureType.RUBIES) {
        r = r + 1;
      }
      if (tt == TreasureType.SAPPHIRES) {
        s = s + 1;
      }
    }
    total.put(TreasureType.DIAMONDS, d);
    total.put(TreasureType.RUBIES, r);
    total.put(TreasureType.SAPPHIRES, s);
    return new TreeMap<>(total);
  }
}
