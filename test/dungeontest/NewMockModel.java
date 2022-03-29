package dungeontest;

import model.Direction;
import model.DungeonObstacle;
import model.Location;
import model.Stench;
import model.TreasureType;

import java.io.IOException;
import java.util.List;

/**
 * A mock Dungeon Obstacle model that is used for testing.
 */
public class NewMockModel implements DungeonObstacle {
  protected Appendable log;

  /**
   * Constructor that creates mockModel with an appendable as parameter.
   *
   * @param l log
   */
  public NewMockModel(Appendable l) {
    this.log = l;
  }

  @Override
  public void movePlayerTo(Direction direction) {
    try {
      log.append("movePlayerTo").append(String.valueOf(direction)).append("\n");
    } catch (IOException ignored) {
    }
  }

  @Override
  public void pickUpTreasure() {
    try {
      log.append("pickUpTreasure\n");
    } catch (IOException ignored) {
    }
  }

  @Override
  public void pickUpArrows() {
    try {
      log.append("pickUpArrows\n");
    } catch (IOException ignored) {
    }
  }

  @Override
  public void shoot(Direction d, int caves) {
    try {
      log.append("shoot").append(String.valueOf(d)).append(String.valueOf(caves)).append("\n");
    } catch (IOException ignored) {
    }
  }

  @Override
  public void moveToLocation(int locationId) {
    try {
      log.append("movePlayerTo").append(String.valueOf(locationId)).append("\n");
    } catch (IOException ignored) {
    }
  }

  @Override
  public void fight() {
    try {
      log.append("fight\n");
    } catch (IOException ignored) {
    }
  }

  @Override
  public DungeonObstacle getInitialModel() {
    return this;
  }

  @Override
  public void setRandom() {
    //MockModel implementation
  }

  @Override
  public List<Integer> getPrevRandom() {
    return null;
  }

  @Override
  public List<String> getPlayerLocationStatus() {
    return null;
  }

  @Override
  public List<String> getInitialGameStatus() {
    return null;
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
    return null;
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
    try {
      log.append("getRows called\n");
    } catch (IOException ignored) {
    }
    return 0;
  }

  @Override
  public int getColumns() {
    try {
      log.append("getColumns called\n");
    } catch (IOException ignored) {
    }
    return 0;
  }

  @Override
  public int getInterconnectivityDegree() {
    try {
      log.append("getInterconnectivityDegree called\n");
    } catch (IOException ignored) {
    }
    return 0;
  }

  @Override
  public boolean isWrapping() {
    try {
      log.append("isWrapping called\n");
    } catch (IOException ignored) {
    }
    return false;
  }

  @Override
  public int getDifficulty() {
    try {
      log.append("getDifficulty called\n");
    } catch (IOException ignored) {
    }
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
  public Stench stenchCalculation(Location location) {
    return null;
  }

  @Override
  public boolean isPitNearBy() {
    return false;
  }

  @Override
  public int getThiefLocationId() {
    return 0;
  }

  @Override
  public int getMovingMonsterLocationId() {
    return 0;
  }

  @Override
  public int getMovingMonsterHealth() {
    return 0;
  }

  @Override
  public boolean isMovingMonsterAlive() {
    return false;
  }
}
