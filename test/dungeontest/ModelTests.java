package dungeontest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import model.Direction;
import model.Dungeon;
import model.Location;
import model.Maze;
import model.RandomInterface;
import model.RandomNumber;
import model.RandomPredictor;
import model.WrappingStyle;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * A Junit test class for Dungeon Interface.
 */
public class ModelTests {
  Dungeon d1;

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidRowsInput() {
    RandomInterface rand = new RandomNumber();
    d1 = new Maze(WrappingStyle.NONWRAPPING, 4, -4, 4,
            50, rand, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidColumnsInput() {
    RandomInterface rand = new RandomNumber();
    d1 = new Maze(WrappingStyle.NONWRAPPING, 4, 4, -4,
            50, rand, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidInterconnectivityInput() {
    RandomInterface rand = new RandomNumber();
    d1 = new Maze(WrappingStyle.NONWRAPPING, -4, 4, 4,
            50, rand, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidNoOfMonsters() {
    RandomInterface rand = new RandomNumber();
    d1 = new Maze(WrappingStyle.NONWRAPPING, -4, 4, 4,
            50, rand, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidInterconnectivity() {
    RandomInterface rand = new RandomNumber();
    Dungeon d = new Maze(WrappingStyle.NONWRAPPING, 30, 5,
            5, 50, rand, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidItemsPercentage() {
    RandomInterface rand = new RandomNumber();
    Dungeon d = new Maze(WrappingStyle.WRAPPING, 3, 5,
            5, 110, rand, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidItemsPercentage2() {
    RandomInterface rand = new RandomNumber();
    Dungeon d = new Maze(WrappingStyle.WRAPPING, 3, 5,
            5, -10, rand, 4);
  }

  @Test
  public void testNoOtyughAtStart() {
    RandomInterface rand2 = new RandomNumber();
    Dungeon d = new Maze(WrappingStyle.WRAPPING, 3, 8, 8,
            50, rand2, 4);
    assertNotSame(d.getPlayerCurrentLocation(), d.getEndLocation());
    assertFalse(d.isGameOver());
  }

  @Test
  public void testPlayerStartsAtStartingPosition() {
    RandomInterface rand = new RandomNumber();
    Dungeon d = new Maze(WrappingStyle.WRAPPING, 6, 7, 7,
            50, rand, 4);
    assertEquals(d.getStartLocation().getLocationId(),
            d.getPlayerCurrentLocation().getLocationId());
  }

  @Test
  public void testPlayerPicksTreasure() {
    RandomInterface rand2 = new RandomPredictor(50, 15, 10, 10, 9, 34, 2, 19, 35, 15, 9, 23,
            21, 30, 10, 27, 12, 6, 5, 16, 20, 34, 31, 13, 22, 4, 22, 20, 6, 21, 9, 15, 0, 20, 25,
            14, 0, 19, 9, 1, 1, 10, 14, 13, 9, 13, 0, 3, 6, 1, 5, 5, 1, 1, 0, 1, 1, 1, 0, 0, 5, 6,
            9, 3, 1, 6, 2, 2, 5, 3, 1, 13, 1, 2, 15, 1, 3, 2, 3, 3, 12, 2, 3, 7, 2, 1, 3, 3, 1,
            6, 1, 1, 8, 1, 1, 6, 1, 1, 3, 1, 1, 7, 1, 3, 3, 2, 3, 4, 3, 2, 1, 2, 3, 23, 1, 28, 1,
            3, 2, 19, 2, 16, 3, 17, 2, 13, 1, 25, 2, 21, 2, 3, 2, 15, 1, 6, 2, 15, 1, 13, 1, 4, 3,
            13, 3, 0, 3, 1, 2, 1, 2, 9, 3, 11, 3, 13, 1, 4, 2, 4, 3, 3, 3, 10, 3, 9,
            2, 6, 3, 2, 1, 18, 13, 13, 5, 13);
    Dungeon d = new Maze(WrappingStyle.NONWRAPPING, 6, 6, 6,
            80, rand2, 4);
    assertEquals(d.getPlayerCurrentLocation().getLocationId(),
            d.getStartLocation().getLocationId());
    assertEquals("Treasures: {DIAMONDS=0, RUBIES=0, SAPPHIRES=0}",
            d.getPlayerDescription().get(0));
    assertEquals("[SAPPHIRES, DIAMONDS]",
            d.getPlayerCurrentLocation().getTreasureList().toString());
    d.pickUpTreasure();
    assertEquals("Treasures: {DIAMONDS=1, RUBIES=0, SAPPHIRES=1}",
            d.getPlayerDescription().get(0));
  }

  @Test
  public void testPlayerPicksArrows() {
    RandomInterface rand2 = new RandomPredictor(50, 15, 10, 10, 9, 34, 2, 19, 35, 15, 9, 23,
            21, 30, 10, 27, 12, 6, 5, 16, 20, 34, 31, 13, 22, 4, 22, 20, 6, 21, 9, 15, 0, 20, 25,
            14, 0, 19, 9, 1, 1, 10, 14, 13, 9, 13, 0, 3, 6, 1, 5, 5, 1, 1, 0, 1, 1, 1, 0, 0, 5, 6,
            9, 3, 1, 6, 2, 2, 5, 3, 1, 13, 1, 2, 15, 1, 3, 2, 3, 3, 12, 2, 3, 7, 2, 1, 3, 3, 1,
            6, 1, 1, 8, 1, 1, 6, 1, 1, 3, 1, 1, 7, 1, 3, 3, 2, 3, 4, 3, 2, 1, 2, 3, 23, 1, 28, 1,
            3, 2, 19, 2, 16, 3, 17, 2, 13, 1, 25, 2, 21, 2, 3, 2, 15, 1, 6, 2, 15, 1, 13, 1, 4, 3,
            13, 3, 0, 3, 1, 2, 1, 2, 9, 3, 11, 3, 13, 1, 4, 2, 4, 3, 3, 3, 10, 3, 9,
            2, 6, 3, 2, 1, 18, 13, 13, 5, 13);
    Dungeon d = new Maze(WrappingStyle.NONWRAPPING, 6, 6, 6,
            80, rand2, 4);
    assertEquals(d.getPlayerCurrentLocation().getLocationId(),
            d.getStartLocation().getLocationId());
    assertEquals("Arrows: 3", d.getPlayerDescription().get(1));
    assertEquals(3,d.getPlayerCurrentLocation().getArrowCount());
    d.pickUpArrows();
    assertEquals("Arrows: 6", d.getPlayerDescription().get(1));
    assertEquals(0,d.getPlayerCurrentLocation().getArrowCount());
  }

  @Test
  public void testPlayerMoves() {
    RandomInterface rand2 = new RandomPredictor(50, 15, 10, 10, 9, 34, 2, 19, 35, 15, 9, 23,
            21, 30, 10, 27, 12, 6, 5, 16, 20, 34, 31, 13, 22, 4, 22, 20, 6, 21, 9, 15, 0, 20, 25,
            14, 0, 19, 9, 1, 1, 10, 14, 13, 9, 13, 0, 3, 6, 1, 5, 5, 1, 1, 0, 1, 1, 1, 0, 0, 5, 6,
            9, 3, 1, 6, 2, 2, 5, 3, 1, 13, 1, 2, 15, 1, 3, 2, 3, 3, 12, 2, 3, 7, 2, 1, 3, 3, 1,
            6, 1, 1, 8, 1, 1, 6, 1, 1, 3, 1, 1, 7, 1, 3, 3, 2, 3, 4, 3, 2, 1, 2, 3, 23, 1, 28, 1,
            3, 2, 19, 2, 16, 3, 17, 2, 13, 1, 25, 2, 21, 2, 3, 2, 15, 1, 6, 2, 15, 1, 13, 1, 4, 3,
            13, 3, 0, 3, 1, 2, 1, 2, 9, 3, 11, 3, 13, 1, 4, 2, 4, 3, 3, 3, 10, 3, 9,
            2, 6, 3, 2, 1, 18, 13, 13, 5, 13);
    Dungeon d = new Maze(WrappingStyle.NONWRAPPING, 6, 6, 6,
            80, rand2, 4);
    assertEquals(d.getPlayerCurrentLocation().getLocationId(),
            d.getStartLocation().getLocationId());
    d.movePlayerTo(Direction.EAST);
    assertEquals(d.getStartLocation().getNeighbours().get(Direction.EAST).getLocationId(),
            d.getPlayerCurrentLocation().getLocationId());
  }

  @Test
  public void testPlayerDescription() {
    RandomInterface rand2 = new RandomPredictor(50, 15, 10, 10, 9, 34, 2, 19, 35, 15, 9, 23,
            21, 30, 10, 27, 12, 6, 5, 16, 20, 34, 31, 13, 22, 4, 22, 20, 6, 21, 9, 15, 0, 20, 25,
            14, 0, 19, 9, 1, 1, 10, 14, 13, 9, 13, 0, 3, 6, 1, 5, 5, 1, 1, 0, 1, 1, 1, 0, 0, 5, 6,
            9, 3, 1, 6, 2, 2, 5, 3, 1, 13, 1, 2, 15, 1, 3, 2, 3, 3, 12, 2, 3, 7, 2, 1, 3, 3, 1,
            6, 1, 1, 8, 1, 1, 6, 1, 1, 3, 1, 1, 7, 1, 3, 3, 2, 3, 4, 3, 2, 1, 2, 3, 23, 1, 28, 1,
            3, 2, 19, 2, 16, 3, 17, 2, 13, 1, 25, 2, 21, 2, 3, 2, 15, 1, 6, 2, 15, 1, 13, 1, 4, 3,
            13, 3, 0, 3, 1, 2, 1, 2, 9, 3, 11, 3, 13, 1, 4, 2, 4, 3, 3, 3, 10, 3, 9,
            2, 6, 3, 2, 1, 18, 13, 13, 5, 13);
    Dungeon d = new Maze(WrappingStyle.NONWRAPPING, 6, 6, 6,
            80, rand2, 4);
    assertEquals(d.getPlayerCurrentLocation().getLocationId(),
            d.getStartLocation().getLocationId());
    assertEquals("Treasures: {DIAMONDS=0, RUBIES=0, SAPPHIRES=0}",
            d.getPlayerDescription().get(0));
    assertEquals("Arrows: 3", d.getPlayerDescription().get(1));
    d.pickUpArrows();
    d.pickUpTreasure();
    assertEquals("Treasures: {DIAMONDS=1, RUBIES=0, SAPPHIRES=1}",
            d.getPlayerDescription().get(0));
    assertEquals("Arrows: 6", d.getPlayerDescription().get(1));
  }

  @Test
  public void testPlayerLocationStatus() {
    RandomInterface rand2 = new RandomPredictor(29, 2, 39, 47, 2, 38, 29, 7, 4, 34, 20, 7,
            17, 25, 18, 25, 27, 22, 40, 33, 25, 0, 17, 14, 22, 26, 9, 22, 0, 1, 22, 14, 24, 14,
            12, 8, 19, 16, 2, 4, 2, 7, 9, 10, 2, 9, 10, 8, 3, 2, 3, 3, 2, 6, 0, 2, 2, 1, 0, 0, 2,
            3, 4, 1, 1, 7, 3, 2, 8, 1, 3, 6, 2, 3, 5, 2, 3, 9, 1, 1, 7, 2, 3, 9, 3, 3, 9, 1, 2, 5,
            3, 1, 3, 2, 1, 6, 3, 3, 1, 1, 2, 2, 1, 2, 1, 2, 1, 17, 2, 3, 2, 17, 2, 9, 3, 23, 2, 23,
            2, 4, 3, 21, 2, 22, 1, 23, 2, 19, 3, 16, 1, 18, 3, 2, 3, 18, 3, 17, 1, 10, 1, 16, 1,
            2, 1, 14, 1, 6, 2, 9, 3, 2, 2, 12, 1, 2, 2, 9, 3, 9, 2, 7, 2, 7, 1, 2, 13, 7, 14);
    Dungeon d = new Maze(WrappingStyle.NONWRAPPING, 6, 6, 6,
            80, rand2, 4);
    assertEquals(d.getPlayerCurrentLocation().getLocationId(),
            d.getStartLocation().getLocationId());
    d.movePlayerTo(Direction.WEST);
    assertNotEquals(d.getPlayerCurrentLocation().getLocationId(),
            d.getStartLocation().getLocationId());
    assertEquals("You are in a Cave", d.getPlayerLocationStatus().get(0));
    assertEquals("Doors lead to [EAST]",
            d.getPlayerLocationStatus().get(1));
    assertEquals("You find 2 arrows here", d.getPlayerLocationStatus().get(2));
    assertEquals("You find [DIAMONDS, DIAMONDS, RUBIES] treasures here",
            d.getPlayerLocationStatus().get(3));
  }

  @Test
  public void testStench() {
    RandomInterface rand2 = new RandomPredictor(44, 16, 5, 5, 26, 33, 19, 37, 31, 21, 43,
            28, 12, 34, 33, 3, 19, 19, 12, 10, 12, 38, 12, 35, 24, 33, 30, 26, 22, 8, 12, 4, 11,
            18, 19, 1, 10, 19, 13, 20, 17, 9, 13, 9, 9, 2, 3, 5, 11, 2, 9, 6, 2, 1, 2, 1, 0, 0, 0,
            0, 5, 3, 3, 1, 2, 9, 2, 1, 15, 3, 1, 4, 1, 1, 8, 2, 2, 11, 1, 3, 7, 3, 1, 4, 1, 3, 7,
            1, 1, 2, 2, 2, 5, 1, 2, 6, 2, 1, 6, 1, 1, 1, 3, 2, 3, 1, 2, 2, 1, 1, 29, 1, 26, 2,
            21, 3, 23, 2, 18, 3, 7, 1, 3, 3, 13, 2, 25, 3, 15, 3, 17, 2, 9, 3, 6, 2, 14, 3, 6,
            1, 12, 1, 10, 3, 8, 3, 9, 2, 0, 3, 2, 3, 13, 2, 4, 1, 12, 1, 3, 3, 2, 1, 6, 2, 2, 1,
            1, 3, 6, 8, 9);
    Dungeon d = new Maze(WrappingStyle.NONWRAPPING, 6, 6, 6,
            80, rand2, 4);
    //There is a monster from the current location at a distance of 2 in South-east path of
    //the player, hence less pungent smell is detected.
    assertEquals("You smell a Less Pungent smell", d.getInitialGameStatus().get(1));
    //The player moves towards in the south.
    d.movePlayerTo(Direction.SOUTH);
    //As there is a monster in the east direction, more pungent smell is detected.
    assertEquals("You smell a More Pungent smell", d.getPlayerLocationStatus().get(0));
    //This proves that there is a monster in the East direction of the player.
    d.shoot(Direction.EAST, 1);
    assertEquals("You hear a small howl in the distance",
            d.getPlayerLocationStatus().get(0));

    //Next, the player moves in the West direction away from the monster present in the East.
    d.movePlayerTo(Direction.WEST);
    //But now there are two monsters at the distance of 2 and hence we get a more pungent smell.
    assertEquals("You smell a More Pungent smell", d.getPlayerLocationStatus().get(0));
    //Let's check the presence of two monsters: one is the old monster that is to east-east,
    //other one is towards south-west
    d.movePlayerTo(Direction.SOUTH);
    d.shoot(Direction.WEST, 1);
    //This proves that there is a monster in the west location of the players after making the
    //move to south.
    assertEquals("You hear a small howl in the distance",
            d.getPlayerLocationStatus().get(0));
  }

  @Test
  public void testArrowsPresenceBothCavesAndTunnels() {
    RandomInterface rand2 = new RandomPredictor(44, 16, 5, 5, 26, 33, 19, 37, 31, 21, 43,
            28, 12, 34, 33, 3, 19, 19, 12, 10, 12, 38, 12, 35, 24, 33, 30, 26, 22, 8, 12, 4, 11,
            18, 19, 1, 10, 19, 13, 20, 17, 9, 13, 9, 9, 2, 3, 5, 11, 2, 9, 6, 2, 1, 2, 1, 0, 0, 0,
            0, 5, 3, 3, 1, 2, 9, 2, 1, 15, 3, 1, 4, 1, 1, 8, 2, 2, 11, 1, 3, 7, 3, 1, 4, 1, 3, 7,
            1, 1, 2, 2, 2, 5, 1, 2, 6, 2, 1, 6, 1, 1, 1, 3, 2, 3, 1, 2, 2, 1, 1, 29, 1, 26, 2,
            21, 3, 23, 2, 18, 3, 7, 1, 3, 3, 13, 2, 25, 3, 15, 3, 17, 2, 9, 3, 6, 2, 14, 3, 6,
            1, 12, 1, 10, 3, 8, 3, 9, 2, 0, 3, 2, 3, 13, 2, 4, 1, 12, 1, 3, 3, 2, 1, 6, 2, 2, 1,
            1, 3, 6, 8, 9);
    Dungeon d = new Maze(WrappingStyle.NONWRAPPING, 6, 6, 6,
            80, rand2, 4);
    assertTrue(!d.getPlayerCurrentLocation().getIsTunnel()
            && d.getPlayerCurrentLocation().getArrowCount() > 0);
    d.movePlayerTo(Direction.WEST);
    d.movePlayerTo(Direction.WEST);
    assertTrue(d.getPlayerCurrentLocation().getIsTunnel()
            && d.getPlayerCurrentLocation().getArrowCount() > 0);
  }

  @Test
  public void testNoOfMonsters() {
    RandomInterface pre = new RandomPredictor(16, 18, 16, 5, 12, 12, 16, 5, 8, 10, 12, 6, 8,
            2, 6, 3, 1, 2, 0, 0, 3, 2, 1, 0, 5, 0, 11, 1, 1, 11, 1, 1, 8, 1, 2, 3, 1, 1, 5, 3, 2,
            5, 1, 2, 4, 3, 1, 0, 1, 2, 2, 1, 3, 0, 2, 3, 2, 1, 1, 4, 2, 13, 2, 8, 2, 12, 1, 10, 1,
            9, 1, 2, 3, 6, 1, 5, 1, 3, 1, 5, 2, 3, 1, 3, 2, 0, 1, 0);
    Dungeon d = new Maze(WrappingStyle.NONWRAPPING, 6, 4, 4,
            80, pre, 3);
    assertEquals(d.getPlayerCurrentLocation().getLocationId(),
            d.getStartLocation().getLocationId());
    Set<Integer> pathExpected = new HashSet<>();
    boolean monsterFoundInEndCave = false;
    pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    int monsterCount = 0;
    d.pickUpArrows();
    d.movePlayerTo(Direction.NORTH);
    pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    d.shoot(Direction.WEST, 1);
    d.shoot(Direction.WEST, 1);
    if (d.getPlayerLocationStatus().get(0).equals("You hear a great howl in the distance")) {
      monsterCount++;
    }
    d.movePlayerTo(Direction.WEST);
    assertFalse(d.isGameOver());
    pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    d.pickUpArrows();
    d.shoot(Direction.WEST, 1);
    d.shoot(Direction.WEST, 1);
    if (d.getPlayerLocationStatus().get(0).equals("You hear a great howl in the distance")) {
      monsterCount++;
    }
    d.movePlayerTo(Direction.WEST);
    assertFalse(d.isGameOver());
    pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    d.movePlayerTo(Direction.WEST);
    pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    d.movePlayerTo(Direction.EAST);
    pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    d.movePlayerTo(Direction.SOUTH);
    pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    d.movePlayerTo(Direction.SOUTH);
    pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    d.movePlayerTo(Direction.WEST);
    pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    d.movePlayerTo(Direction.SOUTH);
    pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    d.movePlayerTo(Direction.EAST);
    pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    d.movePlayerTo(Direction.EAST);
    pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    d.movePlayerTo(Direction.EAST);
    pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    d.movePlayerTo(Direction.WEST);
    pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    d.movePlayerTo(Direction.NORTH);
    pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    d.movePlayerTo(Direction.NORTH);
    pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    d.movePlayerTo(Direction.EAST);
    pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    d.movePlayerTo(Direction.SOUTH);
    pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    d.movePlayerTo(Direction.NORTH);
    pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    d.movePlayerTo(Direction.NORTH);
    pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    d.movePlayerTo(Direction.WEST);
    pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    d.movePlayerTo(Direction.WEST);
    pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    d.movePlayerTo(Direction.WEST);
    pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    d.shoot(Direction.SOUTH, 1);
    d.shoot(Direction.SOUTH, 1);
    if (d.getPlayerLocationStatus().get(0).equals("You hear a great howl in the distance")
            && d.getPlayerCurrentLocation().getNeighbours().get(Direction.SOUTH).getLocationId()
            == d.getEndLocation().getLocationId()) {
      monsterCount++;
      monsterFoundInEndCave = true;
    }
    d.movePlayerTo(Direction.SOUTH);
    pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    assertEquals(d.getPlayerCurrentLocation().getLocationId(), d.getEndLocation().getLocationId());
    assertEquals(4 * 4, pathExpected.size());
    assertEquals(3, monsterCount);
    assertTrue(monsterFoundInEndCave);
    assertEquals("Congo! You have reached the end, the game is over",
            d.getPlayerLocationStatus().get(0));
    assertTrue(d.isGameOver());
  }

  @Test
  public void testMonstersOnlyInCaves() {
    RandomInterface pre = new RandomPredictor(16, 18, 16, 5, 12, 12, 16, 5, 8, 10, 12, 6, 8,
            2, 6, 3, 1, 2, 0, 0, 3, 2, 1, 0, 5, 0, 11, 1, 1, 11, 1, 1, 8, 1, 2, 3, 1, 1, 5, 3, 2,
            5, 1, 2, 4, 3, 1, 0, 1, 2, 2, 1, 3, 0, 2, 3, 2, 1, 1, 4, 2, 13, 2, 8, 2, 12, 1, 10, 1,
            9, 1, 2, 3, 6, 1, 5, 1, 3, 1, 5, 2, 3, 1, 3, 2, 0, 1, 0);
    Dungeon d = new Maze(WrappingStyle.NONWRAPPING, 6, 4, 4,
            80, pre, 3);
    Set<Integer> pathExpected = new HashSet<>();
    pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    int monsterInCave = 0;
    int monsterInTunnel = 0;
    d.pickUpArrows();
    d.movePlayerTo(Direction.NORTH);
    pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    d.shoot(Direction.WEST, 1);
    d.shoot(Direction.WEST, 1);
    if (d.getPlayerLocationStatus().get(0).equals("You hear a great howl in the distance")) {
      d.movePlayerTo(Direction.WEST);
      pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
      if (d.getPlayerCurrentLocation().getIsTunnel()) {
        monsterInTunnel++;
      } else {
        monsterInCave++;
      }
    }
    pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    d.pickUpArrows();
    d.shoot(Direction.WEST, 1);
    d.shoot(Direction.WEST, 1);
    if (d.getPlayerLocationStatus().get(0).equals("You hear a great howl in the distance")) {
      d.movePlayerTo(Direction.WEST);
      pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
      if (d.getPlayerCurrentLocation().getIsTunnel()) {
        monsterInTunnel++;
      } else {
        monsterInCave++;
      }
    }
    d.movePlayerTo(Direction.WEST);
    pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    d.movePlayerTo(Direction.EAST);
    pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    d.movePlayerTo(Direction.SOUTH);
    pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    d.movePlayerTo(Direction.SOUTH);
    pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    d.movePlayerTo(Direction.WEST);
    pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    d.movePlayerTo(Direction.SOUTH);
    pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    d.movePlayerTo(Direction.EAST);
    pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    d.movePlayerTo(Direction.EAST);
    pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    d.movePlayerTo(Direction.EAST);
    pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    d.movePlayerTo(Direction.WEST);
    pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    d.movePlayerTo(Direction.NORTH);
    pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    d.movePlayerTo(Direction.NORTH);
    pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    d.movePlayerTo(Direction.EAST);
    pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    d.movePlayerTo(Direction.SOUTH);
    pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    d.movePlayerTo(Direction.NORTH);
    pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    d.movePlayerTo(Direction.NORTH);
    pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    d.movePlayerTo(Direction.WEST);
    pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    d.movePlayerTo(Direction.WEST);
    pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    d.movePlayerTo(Direction.WEST);
    pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    d.shoot(Direction.SOUTH, 1);
    d.shoot(Direction.SOUTH, 1);
    if (d.getPlayerLocationStatus().get(0).equals("You hear a great howl in the distance")) {
      d.movePlayerTo(Direction.SOUTH);
      pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
      if (d.getPlayerCurrentLocation().getIsTunnel()) {
        monsterInTunnel++;
      } else {
        monsterInCave++;
      }
    }
    assertEquals(3, monsterInCave);
    assertEquals(0, monsterInTunnel);
    assertEquals(16, pathExpected.size());
  }

  @Test
  public void testArrowsCountDecreases() {
    RandomInterface pre = new RandomPredictor(16, 18, 16, 5, 12, 12, 16, 5, 8, 10, 12, 6, 8,
            2, 6, 3, 1, 2, 0, 0, 3, 2, 1, 0, 5, 0, 11, 1, 1, 11, 1, 1, 8, 1, 2, 3, 1, 1, 5, 3, 2,
            5, 1, 2, 4, 3, 1, 0, 1, 2, 2, 1, 3, 0, 2, 3, 2, 1, 1, 4, 2, 13, 2, 8, 2, 12, 1, 10, 1,
            9, 1, 2, 3, 6, 1, 5, 1, 3, 1, 5, 2, 3, 1, 3, 2, 0, 1, 0);
    Dungeon d = new Maze(WrappingStyle.NONWRAPPING, 6, 4, 4,
            80, pre, 3);
    d.movePlayerTo(Direction.NORTH);
    assertEquals("Arrows: 3", d.getPlayerDescription().get(1));
    d.shoot(Direction.WEST, 1);
    assertEquals("Arrows: 2", d.getPlayerDescription().get(1));
  }

  @Test
  public void testPlayerGetsKilledWhenEnteredIntoAliveMonsterCave() {
    RandomInterface rand2 = new RandomPredictor(44, 16, 5, 5, 26, 33, 19, 37, 31, 21, 43,
            28, 12, 34, 33, 3, 19, 19, 12, 10, 12, 38, 12, 35, 24, 33, 30, 26, 22, 8, 12, 4, 11,
            18, 19, 1, 10, 19, 13, 20, 17, 9, 13, 9, 9, 2, 3, 5, 11, 2, 9, 6, 2, 1, 2, 1, 0, 0, 0,
            0, 5, 3, 3, 1, 2, 9, 2, 1, 15, 3, 1, 4, 1, 1, 8, 2, 2, 11, 1, 3, 7, 3, 1, 4, 1, 3, 7,
            1, 1, 2, 2, 2, 5, 1, 2, 6, 2, 1, 6, 1, 1, 1, 3, 2, 3, 1, 2, 2, 1, 1, 29, 1, 26, 2,
            21, 3, 23, 2, 18, 3, 7, 1, 3, 3, 13, 2, 25, 3, 15, 3, 17, 2, 9, 3, 6, 2, 14, 3, 6,
            1, 12, 1, 10, 3, 8, 3, 9, 2, 0, 3, 2, 3, 13, 2, 4, 1, 12, 1, 3, 3, 2, 1, 6, 2, 2, 1,
            1, 3, 6, 8, 9);
    Dungeon d = new Maze(WrappingStyle.NONWRAPPING, 6, 6, 6,
            80, rand2, 4);
    assertFalse(d.isGameOver());
    d.movePlayerTo(Direction.SOUTH);
    d.movePlayerTo(Direction.EAST);
    assertEquals("Chomp, chomp, chomp, you are eaten by an Otyugh!\n"
            + "Better luck next time", d.getPlayerLocationStatus().get(0));
    assertNotEquals(d.getPlayerCurrentLocation().getLocationId(),
            d.getEndLocation().getLocationId());
    assertTrue(d.isGameOver());
  }

  @Test
  public void test2ArrowsNeededToKillOtyugh() {
    RandomInterface pre = new RandomPredictor(16, 18, 16, 5, 12, 12, 16, 5, 8, 10, 12, 6, 8,
            2, 6, 3, 1, 2, 0, 0, 3, 2, 1, 0, 5, 0, 11, 1, 1, 11, 1, 1, 8, 1, 2, 3, 1, 1, 5, 3, 2,
            5, 1, 2, 4, 3, 1, 0, 1, 2, 2, 1, 3, 0, 2, 3, 2, 1, 1, 4, 2, 13, 2, 8, 2, 12, 1, 10, 1,
            9, 1, 2, 3, 6, 1, 5, 1, 3, 1, 5, 2, 3, 1, 3, 2, 0, 1, 0);
    Dungeon d = new Maze(WrappingStyle.NONWRAPPING, 6, 4, 4,
            80, pre, 3);
    d.movePlayerTo(Direction.NORTH);
    assertEquals("Arrows: 3", d.getPlayerDescription().get(1));
    d.shoot(Direction.WEST, 1);
    assertEquals("Arrows: 2", d.getPlayerDescription().get(1));
    assertEquals("You hear a small howl in the distance",
            d.getPlayerLocationStatus().get(0));
    d.shoot(Direction.WEST, 1);
    assertEquals("Arrows: 1", d.getPlayerDescription().get(1));
    assertEquals("You hear a great howl in the distance",
            d.getPlayerLocationStatus().get(0));
  }

  @Test
  public void testCrookedArrowPassesTunnel() {
    RandomInterface pre = new RandomPredictor(16, 18, 16, 5, 12, 12, 16, 5, 8, 10, 12, 6, 8,
            2, 6, 3, 1, 2, 0, 0, 3, 2, 1, 0, 5, 0, 11, 1, 1, 11, 1, 1, 8, 1, 2, 3, 1, 1, 5, 3, 2,
            5, 1, 2, 4, 3, 1, 0, 1, 2, 2, 1, 3, 0, 2, 3, 2, 1, 1, 4, 2, 13, 2, 8, 2, 12, 1, 10, 1,
            9, 1, 2, 3, 6, 1, 5, 1, 3, 1, 5, 2, 3, 1, 3, 2, 0, 1, 0);
    Dungeon d = new Maze(WrappingStyle.NONWRAPPING, 6, 4, 4,
            80, pre, 3);
    assertEquals("Doors lead to [NORTH, SOUTH, WEST]", d.getInitialGameStatus().get(3));
    d.shoot(Direction.NORTH, 1);
    assertEquals("You hear a small howl in the distance",
            d.getPlayerLocationStatus().get(0));
    //This shows that player arrow hits monster when shot in north direction but there
    //is a tunnel in north location and cave containing a monster from west of this tunnel, in
    //which the arrow finally reached.
    assertTrue(d.getPlayerCurrentLocation().getNeighbours().get(Direction.NORTH).getIsTunnel());
    assertFalse(d.getPlayerCurrentLocation().getNeighbours().get(Direction.NORTH).getNeighbours()
            .get(Direction.WEST).getIsTunnel());
    //The player moves towards north and this location which is a tunnel has two outgoings:
    // SOUTH and WEST.
    //As our arrow enters through south outgoing, it leaves the tunnel through west and reaches a
    //cave.
    d.movePlayerTo(Direction.NORTH);
    assertEquals("Doors lead to [SOUTH, WEST]", d.getPlayerLocationStatus().get(2));
    d.shoot(Direction.WEST, 1);
    assertEquals("You hear a great howl in the distance",
            d.getPlayerLocationStatus().get(0));
    //This proves that our initial hit injured our monster and second hit kills the monster, which
    //is shot from the tunnel, and hence shows us that initial arrows passed through tunnel and
    //changed its direction to west as it is the only other outgoing way for that tunnel.
    d.movePlayerTo(Direction.WEST);
    assertFalse(d.getPlayerCurrentLocation().getIsTunnel());
  }

  @Test
  public void testArrowDontChangeDirectionInCave() {
    RandomInterface pre = new RandomPredictor(16, 18, 16, 5, 12, 12, 16, 5, 8, 10, 12, 6, 8,
            2, 6, 3, 1, 2, 0, 0, 3, 2, 1, 0, 5, 0, 11, 1, 1, 11, 1, 1, 8, 1, 2, 3, 1, 1, 5, 3, 2,
            5, 1, 2, 4, 3, 1, 0, 1, 2, 2, 1, 3, 0, 2, 3, 2, 1, 1, 4, 2, 13, 2, 8, 2, 12, 1, 10, 1,
            9, 1, 2, 3, 6, 1, 5, 1, 3, 1, 5, 2, 3, 1, 3, 2, 0, 1, 0);
    Dungeon d = new Maze(WrappingStyle.NONWRAPPING, 6, 4, 4,
            80, pre, 3);
    d.movePlayerTo(Direction.WEST);
    d.shoot(Direction.NORTH, 1);
    assertEquals("You hear a small howl in the distance",
            d.getPlayerLocationStatus().get(0));
    //The above assertion proves that there's a monster in the North cave of player current location
    // at a distance.
    //The player's current location don't have path to west.
    assertNull(d.getPlayerCurrentLocation().getNeighbours().get(Direction.WEST));
    d.movePlayerTo(Direction.EAST);
    //After moving to east we test whether we hit the injured monster which is in the West-North
    // form player current location. As the cave to the west don't have path to west the arrow stops
    //there and unlike tunnel it cannot hit the monster at the north.
    // The below assertion proves that.
    d.shoot(Direction.WEST, 2);
    assertEquals("You didn't hit any Otyugh",
            d.getPlayerLocationStatus().get(0));
  }

  @Test
  public void testExactDistance() {
    RandomInterface pre = new RandomPredictor(16, 18, 16, 5, 12, 12, 16, 5, 8, 10, 12, 6, 8,
            2, 6, 3, 1, 2, 0, 0, 3, 2, 1, 0, 5, 0, 11, 1, 1, 11, 1, 1, 8, 1, 2, 3, 1, 1, 5, 3, 2,
            5, 1, 2, 4, 3, 1, 0, 1, 2, 2, 1, 3, 0, 2, 3, 2, 1, 1, 4, 2, 13, 2, 8, 2, 12, 1, 10, 1,
            9, 1, 2, 3, 6, 1, 5, 1, 3, 1, 5, 2, 3, 1, 3, 2, 0, 1, 0);
    Dungeon d = new Maze(WrappingStyle.NONWRAPPING, 6, 4, 4,
            80, pre, 3);
    d.movePlayerTo(Direction.WEST);
    d.shoot(Direction.NORTH, 1);
    assertEquals("You hear a small howl in the distance",
            d.getPlayerLocationStatus().get(0));
    d.shoot(Direction.NORTH, 2);
    assertEquals("You didn't hit any Otyugh",
            d.getPlayerLocationStatus().get(0));
  }

  @Test
  public void testExactDistanceMonsterIsHit() {
    RandomInterface rand2 = new RandomPredictor(44, 16, 5, 5, 26, 33, 19, 37, 31, 21, 43,
            28, 12, 34, 33, 3, 19, 19, 12, 10, 12, 38, 12, 35, 24, 33, 30, 26, 22, 8, 12, 4, 11,
            18, 19, 1, 10, 19, 13, 20, 17, 9, 13, 9, 9, 2, 3, 5, 11, 2, 9, 6, 2, 1, 2, 1, 0, 0, 0,
            0, 5, 3, 3, 1, 2, 9, 2, 1, 15, 3, 1, 4, 1, 1, 8, 2, 2, 11, 1, 3, 7, 3, 1, 4, 1, 3, 7,
            1, 1, 2, 2, 2, 5, 1, 2, 6, 2, 1, 6, 1, 1, 1, 3, 2, 3, 1, 2, 2, 1, 1, 29, 1, 26, 2,
            21, 3, 23, 2, 18, 3, 7, 1, 3, 3, 13, 2, 25, 3, 15, 3, 17, 2, 9, 3, 6, 2, 14, 3, 6,
            1, 12, 1, 10, 3, 8, 3, 9, 2, 0, 3, 2, 3, 13, 2, 4, 1, 12, 1, 3, 3, 2, 1, 6, 2, 2, 1,
            1, 3, 6, 8, 9);
    Dungeon d = new Maze(WrappingStyle.NONWRAPPING, 6, 6, 6,
            80, rand2, 4);
    d.movePlayerTo(Direction.SOUTH);
    d.movePlayerTo(Direction.WEST);
    d.movePlayerTo(Direction.WEST);
    d.shoot(Direction.SOUTH, 1);
    assertEquals("You hear a small howl in the distance",
            d.getPlayerLocationStatus().get(0));
    assertFalse(d.getPlayerCurrentLocation().getNeighbours().get(Direction.SOUTH).getIsTunnel());
    //The above assertion proves that there's a monster in the south cave of player current location
    // at a distance 1.
    //Now the player shoots an arrow with distance of two caves.
    d.shoot(Direction.SOUTH, 2);
    assertEquals("You didn't hit any Otyugh",
            d.getPlayerLocationStatus().get(0));
    //The above assertion proves that the injured monster is not hit again as the distance of shoot
    //makes the arrow cross the monster below and reaches other cave.
  }

  @Test
  public void testArrowsDistributedCorrectly() {
    RandomInterface pre = new RandomPredictor(16, 18, 16, 5, 12, 12, 16, 5, 8, 10, 12, 6, 8,
            2, 6, 3, 1, 2, 0, 0, 3, 2, 1, 0, 5, 0, 11, 1, 1, 11, 1, 1, 8, 1, 2, 3, 1, 1, 5, 3, 2,
            5, 1, 2, 4, 3, 1, 0, 1, 2, 2, 1, 3, 0, 2, 3, 2, 1, 1, 4, 2, 13, 2, 8, 2, 12, 1, 10, 1,
            9, 1, 2, 3, 6, 1, 5, 1, 3, 1, 5, 2, 3, 1, 3, 2, 0, 1, 0);
    Dungeon d = new Maze(WrappingStyle.NONWRAPPING, 6, 4, 4,
            80, pre, 3);
    Set<Integer> arrowedCaves = new HashSet<>();
    Set<Integer> pathExpected = new HashSet<>();
    checkPresenceOfArrows(d.getPlayerCurrentLocation(), arrowedCaves);
    pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    d.pickUpArrows();
    d.movePlayerTo(Direction.NORTH);
    checkPresenceOfArrows(d.getPlayerCurrentLocation(), arrowedCaves);
    pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    d.shoot(Direction.WEST, 1);
    d.shoot(Direction.WEST, 1);
    if (d.getPlayerLocationStatus().get(0).equals("You hear a great howl in the distance")) {
      d.movePlayerTo(Direction.WEST);
      checkPresenceOfArrows(d.getPlayerCurrentLocation(), arrowedCaves);
      pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    }
    pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    d.pickUpArrows();
    d.shoot(Direction.WEST, 1);
    d.shoot(Direction.WEST, 1);
    if (d.getPlayerLocationStatus().get(0).equals("You hear a great howl in the distance")) {
      d.movePlayerTo(Direction.WEST);
      checkPresenceOfArrows(d.getPlayerCurrentLocation(), arrowedCaves);
      pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    }
    d.movePlayerTo(Direction.WEST);
    checkPresenceOfArrows(d.getPlayerCurrentLocation(), arrowedCaves);
    pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    d.movePlayerTo(Direction.EAST);
    checkPresenceOfArrows(d.getPlayerCurrentLocation(), arrowedCaves);
    pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    d.movePlayerTo(Direction.SOUTH);
    checkPresenceOfArrows(d.getPlayerCurrentLocation(), arrowedCaves);
    pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    d.movePlayerTo(Direction.SOUTH);
    checkPresenceOfArrows(d.getPlayerCurrentLocation(), arrowedCaves);
    pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    d.movePlayerTo(Direction.WEST);
    checkPresenceOfArrows(d.getPlayerCurrentLocation(), arrowedCaves);
    pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    d.movePlayerTo(Direction.SOUTH);
    checkPresenceOfArrows(d.getPlayerCurrentLocation(), arrowedCaves);
    pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    d.movePlayerTo(Direction.EAST);
    checkPresenceOfArrows(d.getPlayerCurrentLocation(), arrowedCaves);
    pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    d.movePlayerTo(Direction.EAST);
    checkPresenceOfArrows(d.getPlayerCurrentLocation(), arrowedCaves);
    pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    d.movePlayerTo(Direction.EAST);
    checkPresenceOfArrows(d.getPlayerCurrentLocation(), arrowedCaves);
    pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    d.movePlayerTo(Direction.WEST);
    checkPresenceOfArrows(d.getPlayerCurrentLocation(), arrowedCaves);
    pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    d.movePlayerTo(Direction.NORTH);
    checkPresenceOfArrows(d.getPlayerCurrentLocation(), arrowedCaves);
    pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    d.movePlayerTo(Direction.NORTH);
    checkPresenceOfArrows(d.getPlayerCurrentLocation(), arrowedCaves);
    pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    d.movePlayerTo(Direction.EAST);
    checkPresenceOfArrows(d.getPlayerCurrentLocation(), arrowedCaves);
    pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    d.movePlayerTo(Direction.SOUTH);
    checkPresenceOfArrows(d.getPlayerCurrentLocation(), arrowedCaves);
    pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    d.movePlayerTo(Direction.NORTH);
    checkPresenceOfArrows(d.getPlayerCurrentLocation(), arrowedCaves);
    pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    d.movePlayerTo(Direction.NORTH);
    checkPresenceOfArrows(d.getPlayerCurrentLocation(), arrowedCaves);
    pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    d.movePlayerTo(Direction.WEST);
    checkPresenceOfArrows(d.getPlayerCurrentLocation(), arrowedCaves);
    pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    d.movePlayerTo(Direction.WEST);
    checkPresenceOfArrows(d.getPlayerCurrentLocation(), arrowedCaves);
    pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    d.movePlayerTo(Direction.WEST);
    checkPresenceOfArrows(d.getPlayerCurrentLocation(), arrowedCaves);
    pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    d.shoot(Direction.SOUTH, 1);
    d.shoot(Direction.SOUTH, 1);
    if (d.getPlayerLocationStatus().get(0).equals("You hear a great howl in the distance")) {
      d.movePlayerTo(Direction.SOUTH);
      checkPresenceOfArrows(d.getPlayerCurrentLocation(), arrowedCaves);
      pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    }
    int actual = (int) (pathExpected.size() * 0.01 * d.getItemsPercentage()) + 1;
    assertEquals(arrowedCaves.size(), actual);
    assertEquals(4 * 4, pathExpected.size());
  }

  @Test
  public void testTreasuresDistributedCorrectly() {
    RandomInterface pre = new RandomPredictor(16, 18, 16, 5, 12, 12, 16, 5, 8, 10, 12, 6, 8,
            2, 6, 3, 1, 2, 0, 0, 3, 2, 1, 0, 5, 0, 11, 1, 1, 11, 1, 1, 8, 1, 2, 3, 1, 1, 5, 3, 2,
            5, 1, 2, 4, 3, 1, 0, 1, 2, 2, 1, 3, 0, 2, 3, 2, 1, 1, 4, 2, 13, 2, 8, 2, 12, 1, 10, 1,
            9, 1, 2, 3, 6, 1, 5, 1, 3, 1, 5, 2, 3, 1, 3, 2, 0, 1, 0);
    Dungeon d = new Maze(WrappingStyle.NONWRAPPING, 6, 4, 4,
            80, pre, 3);
    Set<Integer> treasuredCaves = new HashSet<>();
    Set<Integer> caves = new HashSet<>();
    Set<Integer> pathExpected = new HashSet<>();
    checkPresenceOfTreasures(d.getPlayerCurrentLocation(), treasuredCaves);
    checkIfCave(d.getPlayerCurrentLocation(), caves);
    pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    d.pickUpArrows();
    d.movePlayerTo(Direction.NORTH);
    checkPresenceOfTreasures(d.getPlayerCurrentLocation(), treasuredCaves);
    checkIfCave(d.getPlayerCurrentLocation(), caves);
    pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    d.shoot(Direction.WEST, 1);
    d.shoot(Direction.WEST, 1);
    if (d.getPlayerLocationStatus().get(0).equals("You hear a great howl in the distance")) {
      d.movePlayerTo(Direction.WEST);
      checkPresenceOfTreasures(d.getPlayerCurrentLocation(), treasuredCaves);
      checkIfCave(d.getPlayerCurrentLocation(), caves);
      pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    }
    pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    assertFalse(d.isGameOver());
    d.pickUpArrows();
    d.shoot(Direction.WEST, 1);
    d.shoot(Direction.WEST, 1);
    if (d.getPlayerLocationStatus().get(0).equals("You hear a great howl in the distance")) {
      d.movePlayerTo(Direction.WEST);
      checkPresenceOfTreasures(d.getPlayerCurrentLocation(), treasuredCaves);
      checkIfCave(d.getPlayerCurrentLocation(), caves);
      pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    }
    d.movePlayerTo(Direction.WEST);
    checkPresenceOfTreasures(d.getPlayerCurrentLocation(), treasuredCaves);
    checkIfCave(d.getPlayerCurrentLocation(), caves);
    pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    d.movePlayerTo(Direction.EAST);
    checkPresenceOfTreasures(d.getPlayerCurrentLocation(), treasuredCaves);
    checkIfCave(d.getPlayerCurrentLocation(), caves);
    pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    d.movePlayerTo(Direction.SOUTH);
    checkPresenceOfTreasures(d.getPlayerCurrentLocation(), treasuredCaves);
    checkIfCave(d.getPlayerCurrentLocation(), caves);
    pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    d.movePlayerTo(Direction.SOUTH);
    checkPresenceOfTreasures(d.getPlayerCurrentLocation(), treasuredCaves);
    checkIfCave(d.getPlayerCurrentLocation(), caves);
    pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    d.movePlayerTo(Direction.WEST);
    checkPresenceOfTreasures(d.getPlayerCurrentLocation(), treasuredCaves);
    checkIfCave(d.getPlayerCurrentLocation(), caves);
    pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    d.movePlayerTo(Direction.SOUTH);
    checkPresenceOfTreasures(d.getPlayerCurrentLocation(), treasuredCaves);
    checkIfCave(d.getPlayerCurrentLocation(), caves);
    pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    d.movePlayerTo(Direction.EAST);
    checkPresenceOfTreasures(d.getPlayerCurrentLocation(), treasuredCaves);
    checkIfCave(d.getPlayerCurrentLocation(), caves);
    pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    d.movePlayerTo(Direction.EAST);
    checkPresenceOfTreasures(d.getPlayerCurrentLocation(), treasuredCaves);
    checkIfCave(d.getPlayerCurrentLocation(), caves);
    pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    d.movePlayerTo(Direction.EAST);
    checkPresenceOfTreasures(d.getPlayerCurrentLocation(), treasuredCaves);
    checkIfCave(d.getPlayerCurrentLocation(), caves);
    pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    d.movePlayerTo(Direction.WEST);
    checkPresenceOfTreasures(d.getPlayerCurrentLocation(), treasuredCaves);
    checkIfCave(d.getPlayerCurrentLocation(), caves);
    pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    d.movePlayerTo(Direction.NORTH);
    checkPresenceOfTreasures(d.getPlayerCurrentLocation(), treasuredCaves);
    checkIfCave(d.getPlayerCurrentLocation(), caves);
    pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    d.movePlayerTo(Direction.NORTH);
    checkPresenceOfTreasures(d.getPlayerCurrentLocation(), treasuredCaves);
    checkIfCave(d.getPlayerCurrentLocation(), caves);
    pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    d.movePlayerTo(Direction.EAST);
    checkPresenceOfTreasures(d.getPlayerCurrentLocation(), treasuredCaves);
    checkIfCave(d.getPlayerCurrentLocation(), caves);
    pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    d.movePlayerTo(Direction.SOUTH);
    checkPresenceOfTreasures(d.getPlayerCurrentLocation(), treasuredCaves);
    checkIfCave(d.getPlayerCurrentLocation(), caves);
    pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    d.movePlayerTo(Direction.NORTH);
    checkPresenceOfTreasures(d.getPlayerCurrentLocation(), treasuredCaves);
    checkIfCave(d.getPlayerCurrentLocation(), caves);
    pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    d.movePlayerTo(Direction.NORTH);
    checkPresenceOfTreasures(d.getPlayerCurrentLocation(), treasuredCaves);
    checkIfCave(d.getPlayerCurrentLocation(), caves);
    pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    d.movePlayerTo(Direction.WEST);
    checkPresenceOfTreasures(d.getPlayerCurrentLocation(), treasuredCaves);
    checkIfCave(d.getPlayerCurrentLocation(), caves);
    pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    d.movePlayerTo(Direction.WEST);
    checkPresenceOfTreasures(d.getPlayerCurrentLocation(), treasuredCaves);
    checkIfCave(d.getPlayerCurrentLocation(), caves);
    pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    d.movePlayerTo(Direction.WEST);
    checkPresenceOfTreasures(d.getPlayerCurrentLocation(), treasuredCaves);
    checkIfCave(d.getPlayerCurrentLocation(), caves);
    pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    d.shoot(Direction.SOUTH, 1);
    d.shoot(Direction.SOUTH, 1);
    if (d.getPlayerLocationStatus().get(0).equals("You hear a great howl in the distance")) {
      d.movePlayerTo(Direction.SOUTH);
      checkIfCave(d.getPlayerCurrentLocation(), caves);
      checkPresenceOfTreasures(d.getPlayerCurrentLocation(), treasuredCaves);
      pathExpected.add(d.getPlayerCurrentLocation().getLocationId());
    }
    int actual = (int) (caves.size() * 0.01 * d.getItemsPercentage()) + 1;
    assertEquals(treasuredCaves.size(), actual);
    assertEquals(4 * 4, pathExpected.size());
  }

  private void checkPresenceOfArrows(Location x, Set<Integer> s) {
    if (x.getArrowCount() > 0) {
      s.add(x.getLocationId());
    }
  }

  private void checkPresenceOfTreasures(Location x, Set<Integer> s) {
    if (x.getTreasureList().size() > 0 && !x.getIsTunnel()) {
      s.add(x.getLocationId());
    }
  }

  private void checkIfCave(Location x, Set<Integer> s) {
    if (!x.getIsTunnel()) {
      s.add(x.getLocationId());
    }
  }

  @Test
  public void testNoOtyughHit() {
    RandomInterface pre = new RandomPredictor(16, 18, 16, 5, 12, 12, 16, 5, 8, 10, 12, 6, 8,
            2, 6, 3, 1, 2, 0, 0, 3, 2, 1, 0, 5, 0, 11, 1, 1, 11, 1, 1, 8, 1, 2, 3, 1, 1, 5, 3, 2,
            5, 1, 2, 4, 3, 1, 0, 1, 2, 2, 1, 3, 0, 2, 3, 2, 1, 1, 4, 2, 13, 2, 8, 2, 12, 1, 10, 1,
            9, 1, 2, 3, 6, 1, 5, 1, 3, 1, 5, 2, 3, 1, 3, 2, 0, 1, 0);
    Dungeon d = new Maze(WrappingStyle.NONWRAPPING, 6, 4, 4,
            80, pre, 3);
    d.shoot(Direction.WEST, 1);
    assertEquals("You didn't hit any Otyugh", d.getPlayerLocationStatus().get(0));
  }

  @Test
  public void testPlayerEscapesWith50PercentChance() {
    RandomInterface pre = new RandomPredictor(16, 18, 16, 5, 12, 12, 16, 5, 8, 10, 12, 6, 8,
            2, 6, 3, 1, 2, 0, 0, 3, 2, 1, 0, 5, 0, 11, 1, 1, 11, 1, 1, 8, 1, 2, 3, 1, 1, 5, 3, 2,
            5, 1, 2, 4, 3, 1, 0, 1, 2, 2, 1, 3, 0, 2, 3, 2, 1, 1, 4, 2, 13, 2, 8, 2, 12, 1, 10, 1,
            9, 1, 2, 3, 6, 1, 5, 1, 3, 1, 5, 2, 3, 1, 3, 2, 0, 1, 0, 2);
    Dungeon d = new Maze(WrappingStyle.NONWRAPPING, 6, 4, 4,
            80, pre, 3);
    d.movePlayerTo(Direction.NORTH);
    d.shoot(Direction.WEST, 1);
    assertEquals("You hear a small howl in the distance",
            d.getPlayerLocationStatus().get(0));
    d.movePlayerTo(Direction.WEST);
    assertEquals("You are Lucky, you escaped an injured Otyugh",
            d.getPlayerLocationStatus().get(0));
    assertNotEquals(d.getPlayerCurrentLocation().getLocationId(),
            d.getEndLocation().getLocationId());
    assertFalse(d.isGameOver());
  }

  @Test
  public void testPlayerGetsEatenWith50PercentChance() {
    RandomInterface pre = new RandomPredictor(16, 18, 16, 5, 12, 12, 16, 5, 8, 10, 12, 6, 8,
            2, 6, 3, 1, 2, 0, 0, 3, 2, 1, 0, 5, 0, 11, 1, 1, 11, 1, 1, 8, 1, 2, 3, 1, 1, 5, 3, 2,
            5, 1, 2, 4, 3, 1, 0, 1, 2, 2, 1, 3, 0, 2, 3, 2, 1, 1, 4, 2, 13, 2, 8, 2, 12, 1, 10, 1,
            9, 1, 2, 3, 6, 1, 5, 1, 3, 1, 5, 2, 3, 1, 3, 2, 0, 1, 0, 1);
    Dungeon d = new Maze(WrappingStyle.NONWRAPPING, 6, 4, 4,
            80, pre, 3);
    d.movePlayerTo(Direction.NORTH);
    d.shoot(Direction.WEST, 1);
    assertEquals("You hear a small howl in the distance",
            d.getPlayerLocationStatus().get(0));
    d.movePlayerTo(Direction.WEST);
    assertEquals("Chomp, chomp, chomp, you are eaten by an Otyugh!\n"
                    + "Better luck next time",
            d.getPlayerLocationStatus().get(0));
    assertNotEquals(d.getPlayerCurrentLocation().getLocationId(),
            d.getEndLocation().getLocationId());
    assertTrue(d.isGameOver());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveInInvalidDirection() {
    RandomInterface rand2 = new RandomPredictor(44, 16, 5, 5, 26, 33, 19, 37, 31, 21, 43,
            28, 12, 34, 33, 3, 19, 19, 12, 10, 12, 38, 12, 35, 24, 33, 30, 26, 22, 8, 12, 4, 11,
            18, 19, 1, 10, 19, 13, 20, 17, 9, 13, 9, 9, 2, 3, 5, 11, 2, 9, 6, 2, 1, 2, 1, 0, 0, 0,
            0, 5, 3, 3, 1, 2, 9, 2, 1, 15, 3, 1, 4, 1, 1, 8, 2, 2, 11, 1, 3, 7, 3, 1, 4, 1, 3, 7,
            1, 1, 2, 2, 2, 5, 1, 2, 6, 2, 1, 6, 1, 1, 1, 3, 2, 3, 1, 2, 2, 1, 1, 29, 1, 26, 2,
            21, 3, 23, 2, 18, 3, 7, 1, 3, 3, 13, 2, 25, 3, 15, 3, 17, 2, 9, 3, 6, 2, 14, 3, 6,
            1, 12, 1, 10, 3, 8, 3, 9, 2, 0, 3, 2, 3, 13, 2, 4, 1, 12, 1, 3, 3, 2, 1, 6, 2, 2, 1,
            1, 3, 6, 8, 9);
    Dungeon d = new Maze(WrappingStyle.NONWRAPPING, 6, 6, 6,
            80, rand2, 4);
    d.movePlayerTo(Direction.EAST);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullDirectionMove() {
    RandomInterface pre = new RandomPredictor(16, 18, 16, 5, 12, 12, 16, 5, 8, 10, 12, 6, 8,
            2, 6, 3, 1, 2, 0, 0, 3, 2, 1, 0, 5, 0, 11, 1, 1, 11, 1, 1, 8, 1, 2, 3, 1, 1, 5, 3, 2,
            5, 1, 2, 4, 3, 1, 0, 1, 2, 2, 1, 3, 0, 2, 3, 2, 1, 1, 4, 2, 13, 2, 8, 2, 12, 1, 10, 1,
            9, 1, 2, 3, 6, 1, 5, 1, 3, 1, 5, 2, 3, 1, 3, 2, 0, 1, 0);
    Dungeon d = new Maze(WrappingStyle.NONWRAPPING, 6, 4, 4,
            80, pre, 3);
    d.movePlayerTo(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testShootInInvalidDirection() {
    RandomInterface rand2 = new RandomPredictor(44, 16, 5, 5, 26, 33, 19, 37, 31, 21, 43,
            28, 12, 34, 33, 3, 19, 19, 12, 10, 12, 38, 12, 35, 24, 33, 30, 26, 22, 8, 12, 4, 11,
            18, 19, 1, 10, 19, 13, 20, 17, 9, 13, 9, 9, 2, 3, 5, 11, 2, 9, 6, 2, 1, 2, 1, 0, 0, 0,
            0, 5, 3, 3, 1, 2, 9, 2, 1, 15, 3, 1, 4, 1, 1, 8, 2, 2, 11, 1, 3, 7, 3, 1, 4, 1, 3, 7,
            1, 1, 2, 2, 2, 5, 1, 2, 6, 2, 1, 6, 1, 1, 1, 3, 2, 3, 1, 2, 2, 1, 1, 29, 1, 26, 2,
            21, 3, 23, 2, 18, 3, 7, 1, 3, 3, 13, 2, 25, 3, 15, 3, 17, 2, 9, 3, 6, 2, 14, 3, 6,
            1, 12, 1, 10, 3, 8, 3, 9, 2, 0, 3, 2, 3, 13, 2, 4, 1, 12, 1, 3, 3, 2, 1, 6, 2, 2, 1,
            1, 3, 6, 8, 9);
    Dungeon d = new Maze(WrappingStyle.NONWRAPPING, 6, 6, 6,
            80, rand2, 4);
    assertNull("", d.getPlayerCurrentLocation().getNeighbours().get(Direction.EAST));
    d.shoot(Direction.EAST, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testShootInNullDirection() {
    RandomInterface rand2 = new RandomPredictor(44, 16, 5, 5, 26, 33, 19, 37, 31, 21, 43,
            28, 12, 34, 33, 3, 19, 19, 12, 10, 12, 38, 12, 35, 24, 33, 30, 26, 22, 8, 12, 4, 11,
            18, 19, 1, 10, 19, 13, 20, 17, 9, 13, 9, 9, 2, 3, 5, 11, 2, 9, 6, 2, 1, 2, 1, 0, 0, 0,
            0, 5, 3, 3, 1, 2, 9, 2, 1, 15, 3, 1, 4, 1, 1, 8, 2, 2, 11, 1, 3, 7, 3, 1, 4, 1, 3, 7,
            1, 1, 2, 2, 2, 5, 1, 2, 6, 2, 1, 6, 1, 1, 1, 3, 2, 3, 1, 2, 2, 1, 1, 29, 1, 26, 2,
            21, 3, 23, 2, 18, 3, 7, 1, 3, 3, 13, 2, 25, 3, 15, 3, 17, 2, 9, 3, 6, 2, 14, 3, 6,
            1, 12, 1, 10, 3, 8, 3, 9, 2, 0, 3, 2, 3, 13, 2, 4, 1, 12, 1, 3, 3, 2, 1, 6, 2, 2, 1,
            1, 3, 6, 8, 9);
    Dungeon d = new Maze(WrappingStyle.NONWRAPPING, 6, 6, 6,
            80, rand2, 4);
    d.shoot(null, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testShootInNegativeCaves() {
    RandomInterface rand2 = new RandomPredictor(44, 16, 5, 5, 26, 33, 19, 37, 31, 21, 43,
            28, 12, 34, 33, 3, 19, 19, 12, 10, 12, 38, 12, 35, 24, 33, 30, 26, 22, 8, 12, 4, 11,
            18, 19, 1, 10, 19, 13, 20, 17, 9, 13, 9, 9, 2, 3, 5, 11, 2, 9, 6, 2, 1, 2, 1, 0, 0, 0,
            0, 5, 3, 3, 1, 2, 9, 2, 1, 15, 3, 1, 4, 1, 1, 8, 2, 2, 11, 1, 3, 7, 3, 1, 4, 1, 3, 7,
            1, 1, 2, 2, 2, 5, 1, 2, 6, 2, 1, 6, 1, 1, 1, 3, 2, 3, 1, 2, 2, 1, 1, 29, 1, 26, 2,
            21, 3, 23, 2, 18, 3, 7, 1, 3, 3, 13, 2, 25, 3, 15, 3, 17, 2, 9, 3, 6, 2, 14, 3, 6,
            1, 12, 1, 10, 3, 8, 3, 9, 2, 0, 3, 2, 3, 13, 2, 4, 1, 12, 1, 3, 3, 2, 1, 6, 2, 2, 1,
            1, 3, 6, 8, 9);
    Dungeon d = new Maze(WrappingStyle.NONWRAPPING, 6, 6, 6,
            80, rand2, 4);
    d.shoot(Direction.NORTH, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testShootWithNoOfCaves() {
    RandomInterface rand2 = new RandomPredictor(44, 16, 5, 5, 26, 33, 19, 37, 31, 21, 43,
            28, 12, 34, 33, 3, 19, 19, 12, 10, 12, 38, 12, 35, 24, 33, 30, 26, 22, 8, 12, 4, 11,
            18, 19, 1, 10, 19, 13, 20, 17, 9, 13, 9, 9, 2, 3, 5, 11, 2, 9, 6, 2, 1, 2, 1, 0, 0, 0,
            0, 5, 3, 3, 1, 2, 9, 2, 1, 15, 3, 1, 4, 1, 1, 8, 2, 2, 11, 1, 3, 7, 3, 1, 4, 1, 3, 7,
            1, 1, 2, 2, 2, 5, 1, 2, 6, 2, 1, 6, 1, 1, 1, 3, 2, 3, 1, 2, 2, 1, 1, 29, 1, 26, 2,
            21, 3, 23, 2, 18, 3, 7, 1, 3, 3, 13, 2, 25, 3, 15, 3, 17, 2, 9, 3, 6, 2, 14, 3, 6,
            1, 12, 1, 10, 3, 8, 3, 9, 2, 0, 3, 2, 3, 13, 2, 4, 1, 12, 1, 3, 3, 2, 1, 6, 2, 2, 1,
            1, 3, 6, 8, 9);
    Dungeon d = new Maze(WrappingStyle.NONWRAPPING, 6, 6, 6,
            80, rand2, 4);
    d.shoot(Direction.WEST, 0);
  }

  @Test(expected = IllegalStateException.class)
  public void testPickUpEmptyTreasure() {
    RandomInterface rand2 = new RandomPredictor(44, 16, 5, 5, 26, 33, 19, 37, 31, 21, 43,
            28, 12, 34, 33, 3, 19, 19, 12, 10, 12, 38, 12, 35, 24, 33, 30, 26, 22, 8, 12, 4, 11,
            18, 19, 1, 10, 19, 13, 20, 17, 9, 13, 9, 9, 2, 3, 5, 11, 2, 9, 6, 2, 1, 2, 1, 0, 0, 0,
            0, 5, 3, 3, 1, 2, 9, 2, 1, 15, 3, 1, 4, 1, 1, 8, 2, 2, 11, 1, 3, 7, 3, 1, 4, 1, 3, 7,
            1, 1, 2, 2, 2, 5, 1, 2, 6, 2, 1, 6, 1, 1, 1, 3, 2, 3, 1, 2, 2, 1, 1, 29, 1, 26, 2,
            21, 3, 23, 2, 18, 3, 7, 1, 3, 3, 13, 2, 25, 3, 15, 3, 17, 2, 9, 3, 6, 2, 14, 3, 6,
            1, 12, 1, 10, 3, 8, 3, 9, 2, 0, 3, 2, 3, 13, 2, 4, 1, 12, 1, 3, 3, 2, 1, 6, 2, 2, 1,
            1, 3, 6, 8, 9);
    Dungeon d = new Maze(WrappingStyle.NONWRAPPING, 6, 6, 6,
            80, rand2, 4);
    d.movePlayerTo(Direction.WEST);
    assertEquals(0, d.getPlayerCurrentLocation().getTreasureList().size());
    d.pickUpTreasure();
  }

  @Test(expected = IllegalStateException.class)
  public void testPickUpEmptyArrowsLocation() {
    RandomInterface rand2 = new RandomPredictor(44, 16, 5, 5, 26, 33, 19, 37, 31, 21, 43,
            28, 12, 34, 33, 3, 19, 19, 12, 10, 12, 38, 12, 35, 24, 33, 30, 26, 22, 8, 12, 4, 11,
            18, 19, 1, 10, 19, 13, 20, 17, 9, 13, 9, 9, 2, 3, 5, 11, 2, 9, 6, 2, 1, 2, 1, 0, 0, 0,
            0, 5, 3, 3, 1, 2, 9, 2, 1, 15, 3, 1, 4, 1, 1, 8, 2, 2, 11, 1, 3, 7, 3, 1, 4, 1, 3, 7,
            1, 1, 2, 2, 2, 5, 1, 2, 6, 2, 1, 6, 1, 1, 1, 3, 2, 3, 1, 2, 2, 1, 1, 29, 1, 26, 2,
            21, 3, 23, 2, 18, 3, 7, 1, 3, 3, 13, 2, 25, 3, 15, 3, 17, 2, 9, 3, 6, 2, 14, 3, 6,
            1, 12, 1, 10, 3, 8, 3, 9, 2, 0, 3, 2, 3, 13, 2, 4, 1, 12, 1, 3, 3, 2, 1, 6, 2, 2, 1,
            1, 3, 6, 8, 9);
    Dungeon d = new Maze(WrappingStyle.NONWRAPPING, 6, 6, 6,
            80, rand2, 4);
    d.movePlayerTo(Direction.SOUTH);
    assertEquals(0, d.getPlayerCurrentLocation().getArrowCount());
    d.pickUpArrows();
  }

  @Test(expected = IllegalStateException.class)
  public void testPlayerShootsWithNoArrows() {
    RandomInterface rand2 = new RandomPredictor(44, 16, 5, 5, 26, 33, 19, 37, 31, 21, 43,
            28, 12, 34, 33, 3, 19, 19, 12, 10, 12, 38, 12, 35, 24, 33, 30, 26, 22, 8, 12, 4, 11,
            18, 19, 1, 10, 19, 13, 20, 17, 9, 13, 9, 9, 2, 3, 5, 11, 2, 9, 6, 2, 1, 2, 1, 0, 0, 0,
            0, 5, 3, 3, 1, 2, 9, 2, 1, 15, 3, 1, 4, 1, 1, 8, 2, 2, 11, 1, 3, 7, 3, 1, 4, 1, 3, 7,
            1, 1, 2, 2, 2, 5, 1, 2, 6, 2, 1, 6, 1, 1, 1, 3, 2, 3, 1, 2, 2, 1, 1, 29, 1, 26, 2,
            21, 3, 23, 2, 18, 3, 7, 1, 3, 3, 13, 2, 25, 3, 15, 3, 17, 2, 9, 3, 6, 2, 14, 3, 6,
            1, 12, 1, 10, 3, 8, 3, 9, 2, 0, 3, 2, 3, 13, 2, 4, 1, 12, 1, 3, 3, 2, 1, 6, 2, 2, 1,
            1, 3, 6, 8, 9);
    Dungeon d = new Maze(WrappingStyle.NONWRAPPING, 6, 6, 6,
            80, rand2, 4);
    d.movePlayerTo(Direction.SOUTH);
    d.shoot(Direction.NORTH, 1);
    d.shoot(Direction.SOUTH, 1);
    d.shoot(Direction.EAST, 1);
    assertEquals(0, d.getPlayerCurrentLocation().getArrowCount());
    d.shoot(Direction.WEST, 1);
  }

  @Test
  public void testPlayerStartsWith3Arrows() {
    RandomInterface r = new RandomNumber();
    Dungeon d = new Maze(WrappingStyle.WRAPPING, 6, 7, 7,
            80, r, 3);
    assertEquals("Arrows: 3", d.getPlayerDescription().get(1));
  }

  @Test
  public void testStartAndEndAreCaves() {
    RandomInterface rand = new RandomNumber();
    Dungeon d = new Maze(WrappingStyle.NONWRAPPING, 6, 8, 8,
            80, rand, 3);
    assertFalse(d.getStartLocation().getIsTunnel());
    assertFalse(d.getEndLocation().getIsTunnel());
  }

  @Test
  public void testPathLengthIs5() {
    RandomInterface r = new RandomPredictor(13, 14, 16, 3, 10, 3, 11, 3, 6, 1, 5, 9, 5, 9, 9,
            0, 2, 2, 5, 1, 2, 2, 0, 0, 0, 1, 0, 2, 3, 1, 2, 3, 3, 1, 1, 2, 1, 1, 0, 2, 3, 15, 1,
            8, 1, 12, 1, 0, 2, 6, 1, 0, 1, 2, 2, 7, 1, 0, 3, 5, 3, 3, 1, 2, 2, 3, 2, 4, 4);
    Dungeon d = new Maze(WrappingStyle.NONWRAPPING, 0, 4, 4,
            80, r, 2);
    assertEquals(d.getPlayerCurrentLocation().getLocationId(),
            d.getStartLocation().getLocationId());
    int pathLength = 0;
    d.movePlayerTo(Direction.WEST);
    pathLength++;
    d.movePlayerTo(Direction.SOUTH);
    pathLength++;
    d.shoot(Direction.WEST, 1);
    d.shoot(Direction.WEST, 1);
    d.movePlayerTo(Direction.WEST);
    pathLength++;
    d.movePlayerTo(Direction.NORTH);
    pathLength++;
    d.movePlayerTo(Direction.WEST);
    pathLength++;
    d.movePlayerTo(Direction.SOUTH);
    pathLength++;
    d.movePlayerTo(Direction.SOUTH);
    pathLength++;
    d.movePlayerTo(Direction.SOUTH);
    pathLength++;
    assertEquals(d.getPlayerCurrentLocation().getLocationId(), d.getEndLocation().getLocationId());
    assertTrue(d.isGameOver());
    assertEquals(8, pathLength);
  }

  @Test
  public void testWrappingDungeon() {
    RandomInterface r = new RandomPredictor(4, 14, 6, 3, 9, 2, 13, 14, 14, 11, 12, 6, 8, 16,
            14, 1, 3, 9, 5, 2, 11, 4, 3, 2, 5, 4, 0, 3, 2, 1, 0, 0, 7, 1, 4, 3, 3, 4, 3, 3, 4, 1,
            2, 3, 2, 1, 0, 3, 3, 2, 2, 3, 0, 2, 1, 9, 2, 2, 2, 0, 1, 9, 2, 1, 3, 8, 3, 2, 2, 7, 1,
            1, 2, 4, 3, 1, 1, 2, 3, 3, 3, 7, 3);
    Dungeon d = new Maze(WrappingStyle.WRAPPING, 0, 4, 4,
            80, r, 2);
    d.movePlayerTo(Direction.NORTH);
    d.movePlayerTo(Direction.NORTH);
    assertEquals(7, d.getPlayerCurrentLocation().getLocationId());
    d.movePlayerTo(Direction.EAST);
    assertEquals(4, d.getPlayerCurrentLocation().getLocationId());
  }

}
