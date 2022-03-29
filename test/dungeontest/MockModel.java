package dungeontest;

import model.Direction;
import model.Dungeon;
import model.Location;
import model.Stench;
import model.TreasureType;

import java.util.ArrayList;
import java.util.List;

/**
 * A mock Dungeon model that is used for testing.
 */
class MockModel implements Dungeon {
  public static String S;

  @Override
  public List<String> getPlayerLocationStatus() {
    List<String> a = new ArrayList<>();
    a.add("playerLocationStatus");
    return a;
  }

  @Override
  public List<String> getInitialGameStatus() {
    return new ArrayList<>();
  }

  @Override
  public Stench stenchCalculation(Location location) {
    return null;
  }

  @Override
  public void movePlayerTo(Direction direction) {
    S = "movePlayerTo" + direction;
  }

  @Override
  public Location getPlayerCurrentLocation() {
    return null;
  }

  @Override
  public double getItemsPercentage() {
    return 0;
  }

  @Override
  public Location getStartLocation() {
    return null;
  }

  @Override
  public Location getEndLocation() {
    return null;
  }

  @Override
  public List<String> getPlayerDescription() {
    return new ArrayList<>();
  }

  @Override
  public void pickUpTreasure() {
    S = "";
    S = "pickUpTreasure";
  }

  @Override
  public void pickUpArrows() {
    S = "";
    S = "pickUpArrows";
  }

  @Override
  public void shoot(Direction d, int x) {
    S = "";
    S = "shoot" + d + x;
  }

  @Override
  public boolean isGameOver() {
    return false;
  }

  @Override
  public boolean isPlayerAlive() {
    return false;
  }

  @Override
  public int getRows() {
    return 0;
  }

  @Override
  public int getColumns() {
    return 0;
  }

  @Override
  public int getInterconnectivityDegree() {
    return 0;
  }

  @Override
  public boolean isWrapping() {
    return false;
  }

  @Override
  public int getDifficulty() {
    return 0;
  }

  @Override
  public Location[][] get2dDungeon() {
    return new Location[0][];
  }

  @Override
  public List<Integer> getVisited() {
    return null;
  }

  @Override
  public List<TreasureType> getPlayerTreasure() {
    return null;
  }

  @Override
  public int getPlayerHealth() {
    return 0;
  }

  @Override
  public void moveToLocation(int locationId) {
    //MockModel implementation
  }

}
