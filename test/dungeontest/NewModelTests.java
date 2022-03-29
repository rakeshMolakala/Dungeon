package dungeontest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import model.Direction;
import model.DungeonObstacle;
import model.Location;
import model.MazeWithObstacles;
import model.RandomInterface;
import model.RandomNumber;
import model.RandomPredictor;
import model.WrappingStyle;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Junit tests for the new functionalities added in the model.
 */
public class NewModelTests {

  @Test
  public void testPitDetection() {
    RandomInterface pred = new RandomPredictor(54, 23, 5, 4, 67, 29, 68, 46, 5, 66, 42, 24,
            43, 5, 45, 7, 41, 0, 42, 36, 7, 18, 36, 42, 48, 58, 12, 43, 10, 14, 25, 26, 21, 47,
            29, 17, 26, 17, 34, 30, 20, 3, 19, 36, 29, 21, 3, 5, 34, 8, 25, 15, 18, 20, 13, 12,
            11, 22, 10, 4, 11, 15, 8, 19, 17, 16, 1, 13, 0, 9, 3, 4, 0, 10, 1, 6, 0, 3, 4, 3,
            0, 2, 1, 0, 1, 6, 12, 2, 3, 10, 3, 3, 7, 2, 3, 0, 1, 3, 10, 2, 3, 1, 3, 3, 17, 1,
            3, 19, 3, 3, 3, 3, 3, 17, 1, 2, 7, 2, 2, 9, 3, 2, 4, 3, 2, 9, 2, 2, 1, 2, 1, 0, 2,
            2, 3, 3, 1, 1, 1, 3, 5, 3, 1, 33, 3, 47, 1, 10, 2, 15, 2, 8, 1, 10, 2, 8, 2, 19, 3,
            28, 1, 29, 2, 7, 1, 8, 1, 4, 2, 15, 1, 22, 1, 8, 3, 1, 2, 25, 1, 4, 2, 0, 3, 19, 2,
            2, 3, 14, 3, 24, 2, 24, 3, 0, 3, 3, 3, 6, 3, 13, 1, 19, 3, 0, 3, 16, 1, 16, 1, 11,
            3, 13, 2, 26, 10, 2, 2, 2);
    DungeonObstacle d = new MazeWithObstacles(WrappingStyle.NONWRAPPING, 6,
            7, 7, 70, pred, 2);
    assertTrue(d.isPitNearBy());
    assertEquals(100, d.getPlayerHealth());
    assertTrue(d.getPlayerCurrentLocation().getNeighbours().get(Direction.EAST).hasPit());
    d.movePlayerTo(Direction.EAST);
    assertEquals(80, d.getPlayerHealth());
  }

  @Test
  public void testPitsAddedCorrectly() {
    RandomInterface rand = new RandomNumber();
    DungeonObstacle d = new MazeWithObstacles(WrappingStyle.NONWRAPPING, 6,
            7, 7, 70, rand, 6);
    Location[][] x = d.get2dDungeon();
    int pits = 0;
    for (int i = 0; i < d.getRows(); i++) {
      for (int j = 0; j < d.getColumns(); j++) {
        if (x[i][j].hasPit()) {
          pits++;
        }
      }
    }
    assertEquals(d.getDifficulty() - 1, pits);
  }

  @Test
  public void thiefChangesLocationAndMeetsPlayerAndLootsTreasure() {
    RandomInterface pred2 = new RandomPredictor(29, 34, 18, 23, 29, 23, 29, 13, 4, 23, 9,
            28, 8, 8, 23, 24, 23, 11, 10, 4, 0, 14, 16, 6, 8, 4, 7, 0, 4, 9, 0, 2, 0, 6, 1, 1,
            2, 2, 1, 0, 3, 1, 3, 1, 2, 9, 3, 1, 2, 3, 3, 10, 1, 3, 0, 1, 3, 5, 3, 1, 3, 2, 1,
            5, 2, 3, 4, 2, 3, 1, 1, 1, 21, 1, 16, 2, 9, 1, 12, 3, 18, 1, 11, 2, 5, 1, 14, 1,
            11, 3, 0, 2, 6, 3, 3, 2, 12, 3, 6, 3, 0, 3, 3, 3, 4, 1, 0, 3, 2, 2, 2, 2, 2, 1, 1,
            2, 2, 1, 1, 1, 0, 1, 2, 2, 1, 1, 2, 1, 1, 2, 2, 2, 2);
    DungeonObstacle d = new MazeWithObstacles(WrappingStyle.NONWRAPPING, 6,
            5, 5, 70, pred2, 1);
    d.movePlayerTo(Direction.EAST);
    d.movePlayerTo(Direction.EAST);
    d.movePlayerTo(Direction.SOUTH);
    d.movePlayerTo(Direction.SOUTH);
    d.pickUpTreasure();
    d.movePlayerTo(Direction.SOUTH);
    d.pickUpTreasure();
    assertEquals("Treasures: {DIAMONDS=3, RUBIES=4, SAPPHIRES=1}",
            d.getPlayerDescription().get(0));
    d.movePlayerTo(Direction.WEST);
    assertEquals(20, d.getThiefLocationId());
    d.movePlayerTo(Direction.WEST);
    d.movePlayerTo(Direction.WEST);
    d.movePlayerTo(Direction.NORTH);
    assertEquals(15, d.getThiefLocationId());
    assertEquals("Treasures: {DIAMONDS=0, RUBIES=0, SAPPHIRES=0}",
            d.getPlayerDescription().get(0));
  }

  @Test
  public void playerKillingMovingMonster() {
    RandomInterface pred5 = new RandomPredictor(3, 12, 15, 5, 28, 25, 10, 28, 24, 19, 1, 15,
            3, 8, 18, 14, 14, 6, 4, 8, 2, 16, 16, 11, 2, 12, 8, 7, 5, 5, 3, 8, 6, 3, 3, 4, 1, 0,
            1, 0, 5, 0, 11, 2, 3, 4, 1, 1, 3, 1, 3, 6, 1, 1, 3, 2, 3, 1, 1, 3, 0, 2, 1, 3, 1, 3,
            5, 2, 2, 1, 2, 3, 19, 3, 20, 2, 17, 3, 21, 2, 5, 2, 13, 1, 3, 2, 15, 3, 12, 3, 11, 1,
            1, 3, 7, 3, 0, 3, 1, 3, 3, 1, 6, 3, 1, 1, 7, 1, 6, 2, 1, 0, 1, 1, 2, 2, 2, 0, 2, 2,
            2, 1, 2, 2, 1);
    DungeonObstacle d = new MazeWithObstacles(WrappingStyle.NONWRAPPING, 6,
            5, 5, 70, pred5, 1);
    assertEquals(100, d.getPlayerHealth());
    assertEquals(100, d.getMovingMonsterHealth());
    d.movePlayerTo(Direction.EAST);
    assertEquals(d.getPlayerCurrentLocation().getLocationId(), d.getMovingMonsterLocationId());
    assertEquals(100, d.getPlayerHealth());
    d.fight();
    d.movePlayerTo(Direction.NORTH);
    assertEquals(d.getPlayerCurrentLocation().getLocationId(), d.getMovingMonsterLocationId());
    assertEquals(85, d.getPlayerHealth());
    d.fight();
    d.movePlayerTo(Direction.EAST);
    assertEquals(d.getPlayerCurrentLocation().getLocationId(), d.getMovingMonsterLocationId());
    assertEquals(70, d.getPlayerHealth());
    d.fight();
    d.movePlayerTo(Direction.SOUTH);
    assertEquals(d.getPlayerCurrentLocation().getLocationId(), d.getMovingMonsterLocationId());
    assertEquals(70, d.getPlayerHealth());
    d.fight();
    assertEquals(70, d.getPlayerHealth());
    assertEquals(0, d.getMovingMonsterHealth());
  }

  @Test
  public void playerGettingKilledByMovingMonster() {
    RandomInterface pred5 = new RandomPredictor(29, 18, 7, 32, 1, 23, 3, 16, 15, 1, 13,
            18, 7, 26, 11, 6, 12, 9, 3, 20, 3, 13, 0, 7, 7, 1, 13, 4, 11, 4, 6, 2, 3, 5, 0,
            2, 3, 0, 0, 0, 12, 0, 12, 3, 3, 3, 1, 2, 7, 2, 1, 5, 3, 1, 5, 1, 3, 3, 1, 1, 4,
            3, 2, 1, 1, 3, 5, 3, 1, 3, 3, 2, 3, 1, 1, 21, 3, 22, 2, 11, 2, 13, 2, 0, 1, 1, 1,
            11, 2, 11, 1, 3, 3, 15, 1, 10, 2, 7, 2, 4, 1, 2, 2, 7, 1, 7, 3, 4, 3, 6, 3, 3, 1,
            0, 2, 1, 2, 1, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 2, 1, 0, 2, 2, 2,
            2, 1, 0, 2, 1, 2, 2, 1, 2, 1, 0, 1, 1, 1, 1, 1, 1, 2, 1, 0, 1, 0, 2, 2, 1, 2, 2,
            2, 1, 0, 1, 1, 0, 2, 1, 2, 2, 2, 2, 2, 1, 0, 1, 0, 1, 1, 0, 2, 2, 2, 1, 2, 2, 2,
            2, 1, 1, 0, 2, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 2, 2, 2, 2, 2, 1, 0, 1, 1, 1, 2, 1,
            2, 1, 0, 2, 1, 1, 1, 0, 2, 1, 1, 2, 2, 1, 0, 1, 2, 1, 1, 2, 2, 2);
    DungeonObstacle d = new MazeWithObstacles(WrappingStyle.NONWRAPPING, 6,
            5, 5, 70, pred5, 1);
    assertEquals(100, d.getPlayerHealth());
    d.movePlayerTo(Direction.NORTH);
    d.movePlayerTo(Direction.NORTH);
    d.movePlayerTo(Direction.NORTH);
    d.movePlayerTo(Direction.WEST);
    d.movePlayerTo(Direction.WEST);
    d.movePlayerTo(Direction.EAST);
    d.movePlayerTo(Direction.WEST);
    d.movePlayerTo(Direction.EAST);
    d.movePlayerTo(Direction.EAST);
    d.movePlayerTo(Direction.SOUTH);
    d.movePlayerTo(Direction.NORTH);
    d.movePlayerTo(Direction.EAST);
    d.movePlayerTo(Direction.WEST);
    d.movePlayerTo(Direction.WEST);
    d.movePlayerTo(Direction.WEST);
    d.movePlayerTo(Direction.SOUTH);
    d.movePlayerTo(Direction.NORTH);
    d.movePlayerTo(Direction.EAST);
    d.movePlayerTo(Direction.WEST);
    d.movePlayerTo(Direction.EAST);
    d.movePlayerTo(Direction.EAST);
    d.movePlayerTo(Direction.WEST);
    d.movePlayerTo(Direction.EAST);
    d.movePlayerTo(Direction.WEST);
    d.movePlayerTo(Direction.EAST);
    d.movePlayerTo(Direction.EAST);
    d.movePlayerTo(Direction.WEST);
    d.movePlayerTo(Direction.WEST);
    d.movePlayerTo(Direction.EAST);
    d.movePlayerTo(Direction.EAST);
    d.movePlayerTo(Direction.SOUTH);
    d.movePlayerTo(Direction.NORTH);
    d.movePlayerTo(Direction.WEST);
    d.movePlayerTo(Direction.WEST);
    d.movePlayerTo(Direction.EAST);
    d.movePlayerTo(Direction.SOUTH);
    d.movePlayerTo(Direction.WEST);
    d.movePlayerTo(Direction.EAST);
    d.movePlayerTo(Direction.SOUTH);
    d.movePlayerTo(Direction.EAST);
    d.movePlayerTo(Direction.WEST);
    assertEquals(0, d.getPlayerHealth());
    assertTrue(d.isGameOver());
  }

  @Test
  public void testReuse() {
    RandomInterface rand = new RandomNumber();
    DungeonObstacle d = new MazeWithObstacles(WrappingStyle.NONWRAPPING, 6,
            8, 8, 70, rand, 1);
    List<Integer> initialSeed = new ArrayList<>(d.getPrevRandom());
    DungeonObstacle d2 = d.getInitialModel();
    List<Integer> finalSeed = new ArrayList<>(d2.getPrevRandom());
    assertEquals(initialSeed, finalSeed);
  }

}
