package dungeontest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import controller.TextDungeonConsoleController;
import controller.TextDungeonController;
import model.Dungeon;
import model.Maze;
import model.RandomInterface;
import model.RandomNumber;
import model.RandomPredictor;
import model.WrappingStyle;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;

/**
 * Test cases for the Dungeon controller, using mocks for readable and
 * appendable.
 */
public class TextControllerTests {

  @Test(expected = IllegalStateException.class)
  public void testFailingAppendable() throws IOException {
    // Testing when something goes wrong with the Appendable
    // Here we are passing it a mock of an Appendable that always fails
    RandomInterface rand = new RandomNumber();
    Dungeon m = new Maze(WrappingStyle.NONWRAPPING, 6, 6, 6,
            80, rand, 4);
    StringReader input = new StringReader("M");
    Appendable gameLog = new FailingAppendable();
    TextDungeonController c = new TextDungeonConsoleController(input, gameLog);
    c.playGame(m);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidModel() throws IOException {
    Dungeon m = null;
    StringReader input = new StringReader("M");
    Appendable gameLog = new StringBuilder();
    TextDungeonController c = new TextDungeonConsoleController(input, gameLog);
    c.playGame(m);
  }

  @Test
  public void testInvalidMpsInput() throws IOException {
    RandomInterface pre = new RandomPredictor(16, 18, 16, 5, 12, 12, 16, 5, 8, 10, 12, 6, 8,
            2, 6, 3, 1, 2, 0, 0, 3, 2, 1, 0, 5, 0, 11, 1, 1, 11, 1, 1, 8, 1, 2, 3, 1, 1, 5, 3, 2,
            5, 1, 2, 4, 3, 1, 0, 1, 2, 2, 1, 3, 0, 2, 3, 2, 1, 1, 4, 2, 13, 2, 8, 2, 12, 1, 10, 1,
            9, 1, 2, 3, 6, 1, 5, 1, 3, 1, 5, 2, 3, 1, 3, 2, 0, 1, 0);
    Dungeon d = new Maze(WrappingStyle.NONWRAPPING, 6, 4, 4,
            80, pre, 3);
    Readable input = new StringReader("x3 q");
    Appendable gameLog = new StringBuilder();
    TextDungeonController c = new TextDungeonConsoleController(input, gameLog);
    c.playGame(d);
    String expected = "Welcome to the Dungeon Game\n"
            + "You smell a Less Pungent smell\n"
            + "You are in a Cave\n"
            + "Doors lead to [NORTH, SOUTH, WEST]\n"
            + "You find 1 arrows here\n"
            + "You find [SAPPHIRES, DIAMONDS] treasures here\n"
            + "Move, Pickup, or Shoot (M-P-S)?\n"
            + "Unknown Command, try again\n"
            + "Move, Pickup, or Shoot (M-P-S)?\n"
            + "You have quit the game\n";
    assertEquals(expected, gameLog.toString());
  }

  @Test
  public void testQuit() throws IOException {
    RandomInterface pre = new RandomPredictor(16, 18, 16, 5, 12, 12, 16, 5, 8, 10, 12, 6, 8,
            2, 6, 3, 1, 2, 0, 0, 3, 2, 1, 0, 5, 0, 11, 1, 1, 11, 1, 1, 8, 1, 2, 3, 1, 1, 5, 3, 2,
            5, 1, 2, 4, 3, 1, 0, 1, 2, 2, 1, 3, 0, 2, 3, 2, 1, 1, 4, 2, 13, 2, 8, 2, 12, 1, 10, 1,
            9, 1, 2, 3, 6, 1, 5, 1, 3, 1, 5, 2, 3, 1, 3, 2, 0, 1, 0);
    Dungeon d = new Maze(WrappingStyle.NONWRAPPING, 6, 4, 4,
            80, pre, 3);
    Readable input = new StringReader("q");
    Appendable gameLog = new StringBuilder();
    TextDungeonController c = new TextDungeonConsoleController(input, gameLog);
    c.playGame(d);
    String expected = "Welcome to the Dungeon Game\n"
            + "You smell a Less Pungent smell\n"
            + "You are in a Cave\n"
            + "Doors lead to [NORTH, SOUTH, WEST]\n"
            + "You find 1 arrows here\n"
            + "You find [SAPPHIRES, DIAMONDS] treasures here\n"
            + "Move, Pickup, or Shoot (M-P-S)?\n"
            + "You have quit the game\n";
    assertEquals(expected, gameLog.toString());
  }

  @Test
  public void testControllerRunsAfterInvalidMpsInput() throws IOException {
    RandomInterface pre = new RandomPredictor(16, 18, 16, 5, 12, 12, 16, 5, 8, 10, 12, 6, 8,
            2, 6, 3, 1, 2, 0, 0, 3, 2, 1, 0, 5, 0, 11, 1, 1, 11, 1, 1, 8, 1, 2, 3, 1, 1, 5, 3, 2,
            5, 1, 2, 4, 3, 1, 0, 1, 2, 2, 1, 3, 0, 2, 3, 2, 1, 1, 4, 2, 13, 2, 8, 2, 12, 1, 10, 1,
            9, 1, 2, 3, 6, 1, 5, 1, 3, 1, 5, 2, 3, 1, 3, 2, 0, 1, 0);
    Dungeon d = new Maze(WrappingStyle.NONWRAPPING, 6, 4, 4,
            80, pre, 3);
    Readable input = new StringReader("x3 M N q");
    Appendable gameLog = new StringBuilder();
    TextDungeonController c = new TextDungeonConsoleController(input, gameLog);
    c.playGame(d);
    String expected = "Welcome to the Dungeon Game\n"
            + "You smell a Less Pungent smell\n"
            + "You are in a Cave\n"
            + "Doors lead to [NORTH, SOUTH, WEST]\n"
            + "You find 1 arrows here\n"
            + "You find [SAPPHIRES, DIAMONDS] treasures here\n"
            + "Move, Pickup, or Shoot (M-P-S)?\n"
            + "Unknown Command, try again\n"
            + "Move, Pickup, or Shoot (M-P-S)?\n"
            + "Where to (N-S-E-W)?\n"
            + "You smell a More Pungent smell\n"
            + "You are in a Tunnel\n"
            + "Doors lead to [SOUTH, WEST]\n"
            + "You find 0 arrows here\n"
            + "You find [] treasures here\n"
            + "Move, Pickup, or Shoot (M-P-S)?\n"
            + "You have quit the game\n";
    assertEquals(expected, gameLog.toString());
  }

  @Test
  public void testInvalidDirectionInputAfterM() throws IOException {
    RandomInterface pre = new RandomPredictor(16, 18, 16, 5, 12, 12, 16, 5, 8, 10, 12, 6, 8,
            2, 6, 3, 1, 2, 0, 0, 3, 2, 1, 0, 5, 0, 11, 1, 1, 11, 1, 1, 8, 1, 2, 3, 1, 1, 5, 3, 2,
            5, 1, 2, 4, 3, 1, 0, 1, 2, 2, 1, 3, 0, 2, 3, 2, 1, 1, 4, 2, 13, 2, 8, 2, 12, 1, 10, 1,
            9, 1, 2, 3, 6, 1, 5, 1, 3, 1, 5, 2, 3, 1, 3, 2, 0, 1, 0);
    Dungeon d = new Maze(WrappingStyle.NONWRAPPING, 6, 4, 4,
            80, pre, 3);
    Readable input = new StringReader("M we 1 N Q");
    Appendable gameLog = new StringBuilder();
    TextDungeonController c = new TextDungeonConsoleController(input, gameLog);
    c.playGame(d);
    String expected = "Welcome to the Dungeon Game\n"
            + "You smell a Less Pungent smell\n"
            + "You are in a Cave\n"
            + "Doors lead to [NORTH, SOUTH, WEST]\n"
            + "You find 1 arrows here\n"
            + "You find [SAPPHIRES, DIAMONDS] treasures here\n"
            + "Move, Pickup, or Shoot (M-P-S)?\n"
            + "Where to (N-S-E-W)?\n"
            + "Not a valid direction, enter again\n"
            + "Not a valid direction, enter again\n"
            + "You smell a More Pungent smell\n"
            + "You are in a Tunnel\n"
            + "Doors lead to [SOUTH, WEST]\n"
            + "You find 0 arrows here\n"
            + "You find [] treasures here\n"
            + "Move, Pickup, or Shoot (M-P-S)?\n"
            + "You have quit the game\n";
    assertEquals(expected, gameLog.toString());
  }

  @Test
  public void testNotPossibleDirectionInputAfterM() throws IOException {
    RandomInterface pre = new RandomPredictor(16, 18, 16, 5, 12, 12, 16, 5, 8, 10, 12, 6, 8,
            2, 6, 3, 1, 2, 0, 0, 3, 2, 1, 0, 5, 0, 11, 1, 1, 11, 1, 1, 8, 1, 2, 3, 1, 1, 5, 3, 2,
            5, 1, 2, 4, 3, 1, 0, 1, 2, 2, 1, 3, 0, 2, 3, 2, 1, 1, 4, 2, 13, 2, 8, 2, 12, 1, 10, 1,
            9, 1, 2, 3, 6, 1, 5, 1, 3, 1, 5, 2, 3, 1, 3, 2, 0, 1, 0);
    Dungeon d = new Maze(WrappingStyle.NONWRAPPING, 6, 4, 4,
            80, pre, 3);
    Readable input = new StringReader("M E Q");
    Appendable gameLog = new StringBuilder();
    TextDungeonController c = new TextDungeonConsoleController(input, gameLog);
    c.playGame(d);
    String expected = "Welcome to the Dungeon Game\n"
            + "You smell a Less Pungent smell\n"
            + "You are in a Cave\n"
            + "Doors lead to [NORTH, SOUTH, WEST]\n"
            + "You find 1 arrows here\n"
            + "You find [SAPPHIRES, DIAMONDS] treasures here\n"
            + "Move, Pickup, or Shoot (M-P-S)?\n"
            + "Where to (N-S-E-W)?\n"
            + "There's no possible path in the direction you have mentioned\n"
            + "Move, Pickup, or Shoot (M-P-S)?\n"
            + "You have quit the game\n";
    assertEquals(expected, gameLog.toString());
  }

  @Test
  public void testInvalidInputAfterP() throws IOException {
    RandomInterface pre = new RandomPredictor(16, 18, 16, 5, 12, 12, 16, 5, 8, 10, 12, 6, 8,
            2, 6, 3, 1, 2, 0, 0, 3, 2, 1, 0, 5, 0, 11, 1, 1, 11, 1, 1, 8, 1, 2, 3, 1, 1, 5, 3, 2,
            5, 1, 2, 4, 3, 1, 0, 1, 2, 2, 1, 3, 0, 2, 3, 2, 1, 1, 4, 2, 13, 2, 8, 2, 12, 1, 10, 1,
            9, 1, 2, 3, 6, 1, 5, 1, 3, 1, 5, 2, 3, 1, 3, 2, 0, 1, 0);
    Dungeon d = new Maze(WrappingStyle.NONWRAPPING, 6, 4, 4,
            80, pre, 3);
    Readable input = new StringReader("P vf 1 A Q");
    Appendable gameLog = new StringBuilder();
    TextDungeonController c = new TextDungeonConsoleController(input, gameLog);
    c.playGame(d);
    String expected = "Welcome to the Dungeon Game\n"
            + "You smell a Less Pungent smell\n"
            + "You are in a Cave\n"
            + "Doors lead to [NORTH, SOUTH, WEST]\n"
            + "You find 1 arrows here\n"
            + "You find [SAPPHIRES, DIAMONDS] treasures here\n"
            + "Move, Pickup, or Shoot (M-P-S)?\n"
            + "Treasures or Arrows (T-A)?\n"
            + "Not a valid pickup specification, enter again\n"
            + "Not a valid pickup specification, enter again\n"
            + "After pickup the player has following items\n"
            + "Treasures: {DIAMONDS=0, RUBIES=0, SAPPHIRES=0}\n"
            + "Arrows: 4\n"
            + "Move, Pickup, or Shoot (M-P-S)?\n"
            + "You have quit the game\n";
    assertEquals(expected, gameLog.toString());
  }

  @Test
  public void testInvalidNumberOfCavesAfterShoot() throws IOException {
    RandomInterface pre = new RandomPredictor(16, 18, 16, 5, 12, 12, 16, 5, 8, 10, 12, 6, 8,
            2, 6, 3, 1, 2, 0, 0, 3, 2, 1, 0, 5, 0, 11, 1, 1, 11, 1, 1, 8, 1, 2, 3, 1, 1, 5, 3, 2,
            5, 1, 2, 4, 3, 1, 0, 1, 2, 2, 1, 3, 0, 2, 3, 2, 1, 1, 4, 2, 13, 2, 8, 2, 12, 1, 10, 1,
            9, 1, 2, 3, 6, 1, 5, 1, 3, 1, 5, 2, 3, 1, 3, 2, 0, 1, 0);
    Dungeon d = new Maze(WrappingStyle.NONWRAPPING, 6, 4, 4,
            80, pre, 3);
    Readable input = new StringReader("S xy 1 N Q");
    Appendable gameLog = new StringBuilder();
    TextDungeonController c = new TextDungeonConsoleController(input, gameLog);
    c.playGame(d);
    String expected = "Welcome to the Dungeon Game\n"
            + "You smell a Less Pungent smell\n"
            + "You are in a Cave\n"
            + "Doors lead to [NORTH, SOUTH, WEST]\n"
            + "You find 1 arrows here\n"
            + "You find [SAPPHIRES, DIAMONDS] treasures here\n"
            + "Move, Pickup, or Shoot (M-P-S)?\n"
            + "No. of caves (1-5)?\n"
            + "Not a valid number of caves, enter again\n"
            + "Where to (N-S-E-W)?\n"
            + "You hear a small howl in the distance\n"
            + "Move, Pickup, or Shoot (M-P-S)?\n"
            + "You have quit the game\n";
    assertEquals(expected, gameLog.toString());
  }

  @Test
  public void testNegativeNumberOfCavesAfterShoot() throws IOException {
    RandomInterface pre = new RandomPredictor(16, 18, 16, 5, 12, 12, 16, 5, 8, 10, 12, 6, 8,
            2, 6, 3, 1, 2, 0, 0, 3, 2, 1, 0, 5, 0, 11, 1, 1, 11, 1, 1, 8, 1, 2, 3, 1, 1, 5, 3, 2,
            5, 1, 2, 4, 3, 1, 0, 1, 2, 2, 1, 3, 0, 2, 3, 2, 1, 1, 4, 2, 13, 2, 8, 2, 12, 1, 10, 1,
            9, 1, 2, 3, 6, 1, 5, 1, 3, 1, 5, 2, 3, 1, 3, 2, 0, 1, 0);
    Dungeon d = new Maze(WrappingStyle.NONWRAPPING, 6, 4, 4,
            80, pre, 3);
    Readable input = new StringReader("S -4 N Q");
    Appendable gameLog = new StringBuilder();
    TextDungeonController c = new TextDungeonConsoleController(input, gameLog);
    c.playGame(d);
    String expected = "Welcome to the Dungeon Game\n"
            + "You smell a Less Pungent smell\n"
            + "You are in a Cave\n"
            + "Doors lead to [NORTH, SOUTH, WEST]\n"
            + "You find 1 arrows here\n"
            + "You find [SAPPHIRES, DIAMONDS] treasures here\n"
            + "Move, Pickup, or Shoot (M-P-S)?\n"
            + "No. of caves (1-5)?\n"
            + "Where to (N-S-E-W)?\n"
            + "CAN'T SHOOT, Number of caves should be greater than 0\n"
            + "Move, Pickup, or Shoot (M-P-S)?\n"
            + "You have quit the game\n";
    assertEquals(expected, gameLog.toString());
  }

  @Test
  public void test0numberOfCavesInputAfterShoot() throws IOException {
    RandomInterface pre = new RandomPredictor(16, 18, 16, 5, 12, 12, 16, 5, 8, 10, 12, 6, 8,
            2, 6, 3, 1, 2, 0, 0, 3, 2, 1, 0, 5, 0, 11, 1, 1, 11, 1, 1, 8, 1, 2, 3, 1, 1, 5, 3, 2,
            5, 1, 2, 4, 3, 1, 0, 1, 2, 2, 1, 3, 0, 2, 3, 2, 1, 1, 4, 2, 13, 2, 8, 2, 12, 1, 10, 1,
            9, 1, 2, 3, 6, 1, 5, 1, 3, 1, 5, 2, 3, 1, 3, 2, 0, 1, 0);
    Dungeon d = new Maze(WrappingStyle.NONWRAPPING, 6, 4, 4,
            80, pre, 3);
    Readable input = new StringReader("S 0 E Q");
    Appendable gameLog = new StringBuilder();
    TextDungeonController c = new TextDungeonConsoleController(input, gameLog);
    c.playGame(d);
    String expected = "Welcome to the Dungeon Game\n"
            + "You smell a Less Pungent smell\n"
            + "You are in a Cave\n"
            + "Doors lead to [NORTH, SOUTH, WEST]\n"
            + "You find 1 arrows here\n"
            + "You find [SAPPHIRES, DIAMONDS] treasures here\n"
            + "Move, Pickup, or Shoot (M-P-S)?\n"
            + "No. of caves (1-5)?\n"
            + "Where to (N-S-E-W)?\n"
            + "CAN'T SHOOT, Number of caves should be greater than 0\n"
            + "Move, Pickup, or Shoot (M-P-S)?\n"
            + "You have quit the game\n";
    assertEquals(expected, gameLog.toString());
  }

  @Test
  public void testMoreThan5NumberOfCavesInputAfterShoot() throws IOException {
    RandomInterface pre = new RandomPredictor(16, 18, 16, 5, 12, 12, 16, 5, 8, 10, 12, 6, 8,
            2, 6, 3, 1, 2, 0, 0, 3, 2, 1, 0, 5, 0, 11, 1, 1, 11, 1, 1, 8, 1, 2, 3, 1, 1, 5, 3, 2,
            5, 1, 2, 4, 3, 1, 0, 1, 2, 2, 1, 3, 0, 2, 3, 2, 1, 1, 4, 2, 13, 2, 8, 2, 12, 1, 10, 1,
            9, 1, 2, 3, 6, 1, 5, 1, 3, 1, 5, 2, 3, 1, 3, 2, 0, 1, 0);
    Dungeon d = new Maze(WrappingStyle.NONWRAPPING, 6, 4, 4,
            80, pre, 3);
    Readable input = new StringReader("S 7 E Q");
    Appendable gameLog = new StringBuilder();
    TextDungeonController c = new TextDungeonConsoleController(input, gameLog);
    c.playGame(d);
    String expected = "Welcome to the Dungeon Game\n"
            + "You smell a Less Pungent smell\n"
            + "You are in a Cave\n"
            + "Doors lead to [NORTH, SOUTH, WEST]\n"
            + "You find 1 arrows here\n"
            + "You find [SAPPHIRES, DIAMONDS] treasures here\n"
            + "Move, Pickup, or Shoot (M-P-S)?\n"
            + "No. of caves (1-5)?\n"
            + "Where to (N-S-E-W)?\n"
            + "CAN'T SHOOT, you cannot shoot at distance greater than 5\n"
            + "Move, Pickup, or Shoot (M-P-S)?\n"
            + "You have quit the game\n";
    assertEquals(expected, gameLog.toString());
  }

  @Test
  public void testInvalidDirectionInputAfterShoot() throws IOException {
    RandomInterface pre = new RandomPredictor(16, 18, 16, 5, 12, 12, 16, 5, 8, 10, 12, 6, 8,
            2, 6, 3, 1, 2, 0, 0, 3, 2, 1, 0, 5, 0, 11, 1, 1, 11, 1, 1, 8, 1, 2, 3, 1, 1, 5, 3, 2,
            5, 1, 2, 4, 3, 1, 0, 1, 2, 2, 1, 3, 0, 2, 3, 2, 1, 1, 4, 2, 13, 2, 8, 2, 12, 1, 10, 1,
            9, 1, 2, 3, 6, 1, 5, 1, 3, 1, 5, 2, 3, 1, 3, 2, 0, 1, 0);
    Dungeon d = new Maze(WrappingStyle.NONWRAPPING, 6, 4, 4,
            80, pre, 3);
    Readable input = new StringReader("S 1 df N Q");
    Appendable gameLog = new StringBuilder();
    TextDungeonController c = new TextDungeonConsoleController(input, gameLog);
    c.playGame(d);
    String expected = "Welcome to the Dungeon Game\n"
            + "You smell a Less Pungent smell\n"
            + "You are in a Cave\n"
            + "Doors lead to [NORTH, SOUTH, WEST]\n"
            + "You find 1 arrows here\n"
            + "You find [SAPPHIRES, DIAMONDS] treasures here\n"
            + "Move, Pickup, or Shoot (M-P-S)?\n"
            + "No. of caves (1-5)?\n"
            + "Where to (N-S-E-W)?\n"
            + "Not a valid direction, enter again\n"
            + "You hear a small howl in the distance\n"
            + "Move, Pickup, or Shoot (M-P-S)?\n"
            + "You have quit the game\n";
    assertEquals(expected, gameLog.toString());
  }

  @Test
  public void testNoPossibleDirectionInputAfterShoot() throws IOException {
    RandomInterface pre = new RandomPredictor(16, 18, 16, 5, 12, 12, 16, 5, 8, 10, 12, 6, 8,
            2, 6, 3, 1, 2, 0, 0, 3, 2, 1, 0, 5, 0, 11, 1, 1, 11, 1, 1, 8, 1, 2, 3, 1, 1, 5, 3, 2,
            5, 1, 2, 4, 3, 1, 0, 1, 2, 2, 1, 3, 0, 2, 3, 2, 1, 1, 4, 2, 13, 2, 8, 2, 12, 1, 10, 1,
            9, 1, 2, 3, 6, 1, 5, 1, 3, 1, 5, 2, 3, 1, 3, 2, 0, 1, 0);
    Dungeon d = new Maze(WrappingStyle.NONWRAPPING, 6, 4, 4,
            80, pre, 3);
    Readable input = new StringReader("S 1 E Q");
    Appendable gameLog = new StringBuilder();
    TextDungeonController c = new TextDungeonConsoleController(input, gameLog);
    c.playGame(d);
    String expected = "Welcome to the Dungeon Game\n"
            + "You smell a Less Pungent smell\n"
            + "You are in a Cave\n"
            + "Doors lead to [NORTH, SOUTH, WEST]\n"
            + "You find 1 arrows here\n"
            + "You find [SAPPHIRES, DIAMONDS] treasures here\n"
            + "Move, Pickup, or Shoot (M-P-S)?\n"
            + "No. of caves (1-5)?\n"
            + "Where to (N-S-E-W)?\n"
            + "CAN'T SHOOT, No path exists in the given direction\n"
            + "Move, Pickup, or Shoot (M-P-S)?\n"
            + "You have quit the game\n";
    assertEquals(expected, gameLog.toString());
  }

  @Test
  public void testShootWithZeroArrows() throws IOException {
    RandomInterface pre = new RandomPredictor(16, 18, 16, 5, 12, 12, 16, 5, 8, 10, 12, 6, 8,
            2, 6, 3, 1, 2, 0, 0, 3, 2, 1, 0, 5, 0, 11, 1, 1, 11, 1, 1, 8, 1, 2, 3, 1, 1, 5, 3, 2,
            5, 1, 2, 4, 3, 1, 0, 1, 2, 2, 1, 3, 0, 2, 3, 2, 1, 1, 4, 2, 13, 2, 8, 2, 12, 1, 10, 1,
            9, 1, 2, 3, 6, 1, 5, 1, 3, 1, 5, 2, 3, 1, 3, 2, 0, 1, 0);
    Dungeon d = new Maze(WrappingStyle.NONWRAPPING, 6, 4, 4,
            80, pre, 3);
    Readable input = new StringReader("S 1 N S 1 N S 1 N S 1 N Q");
    Appendable gameLog = new StringBuilder();
    TextDungeonController c = new TextDungeonConsoleController(input, gameLog);
    c.playGame(d);
    String expected = "Welcome to the Dungeon Game\n"
            + "You smell a Less Pungent smell\n"
            + "You are in a Cave\n"
            + "Doors lead to [NORTH, SOUTH, WEST]\n"
            + "You find 1 arrows here\n"
            + "You find [SAPPHIRES, DIAMONDS] treasures here\n"
            + "Move, Pickup, or Shoot (M-P-S)?\n"
            + "No. of caves (1-5)?\n"
            + "Where to (N-S-E-W)?\n"
            + "You hear a small howl in the distance\n"
            + "Move, Pickup, or Shoot (M-P-S)?\n"
            + "No. of caves (1-5)?\n"
            + "Where to (N-S-E-W)?\n"
            + "You hear a great howl in the distance\n"
            + "Move, Pickup, or Shoot (M-P-S)?\n"
            + "No. of caves (1-5)?\n"
            + "Where to (N-S-E-W)?\n"
            + "You didn't hit any Otyugh\n"
            + "Move, Pickup, or Shoot (M-P-S)?\n"
            + "No. of caves (1-5)?\n"
            + "Where to (N-S-E-W)?\n"
            + "CAN'T SHOOT, You have no arrows to shoot\n"
            + "Move, Pickup, or Shoot (M-P-S)?\n"
            + "You have quit the game\n";
    assertEquals(expected, gameLog.toString());
  }

  @Test
  public void testPickUpWithZeroArrows() throws IOException {
    RandomInterface pre = new RandomPredictor(16, 18, 16, 5, 12, 12, 16, 5, 8, 10, 12, 6, 8,
            2, 6, 3, 1, 2, 0, 0, 3, 2, 1, 0, 5, 0, 11, 1, 1, 11, 1, 1, 8, 1, 2, 3, 1, 1, 5, 3, 2,
            5, 1, 2, 4, 3, 1, 0, 1, 2, 2, 1, 3, 0, 2, 3, 2, 1, 1, 4, 2, 13, 2, 8, 2, 12, 1, 10, 1,
            9, 1, 2, 3, 6, 1, 5, 1, 3, 1, 5, 2, 3, 1, 3, 2, 0, 1, 0);
    Dungeon d = new Maze(WrappingStyle.NONWRAPPING, 6, 4, 4,
            80, pre, 3);
    Readable input = new StringReader("P A P A Q");
    Appendable gameLog = new StringBuilder();
    TextDungeonController c = new TextDungeonConsoleController(input, gameLog);
    c.playGame(d);
    String expected = "Welcome to the Dungeon Game\n"
            + "You smell a Less Pungent smell\n"
            + "You are in a Cave\n"
            + "Doors lead to [NORTH, SOUTH, WEST]\n"
            + "You find 1 arrows here\n"
            + "You find [SAPPHIRES, DIAMONDS] treasures here\n"
            + "Move, Pickup, or Shoot (M-P-S)?\n"
            + "Treasures or Arrows (T-A)?\n"
            + "After pickup the player has following items\n"
            + "Treasures: {DIAMONDS=0, RUBIES=0, SAPPHIRES=0}\n"
            + "Arrows: 4\n"
            + "Move, Pickup, or Shoot (M-P-S)?\n"
            + "Treasures or Arrows (T-A)?\n"
            + "CAN'T PICKUP, There are no requested items in the cave\n"
            + "Move, Pickup, or Shoot (M-P-S)?\n"
            + "You have quit the game\n";
    assertEquals(expected, gameLog.toString());
  }

  @Test
  public void testPickUpWithZeroTreasures() throws IOException {
    RandomInterface pre = new RandomPredictor(16, 18, 16, 5, 12, 12, 16, 5, 8, 10, 12, 6, 8,
            2, 6, 3, 1, 2, 0, 0, 3, 2, 1, 0, 5, 0, 11, 1, 1, 11, 1, 1, 8, 1, 2, 3, 1, 1, 5, 3, 2,
            5, 1, 2, 4, 3, 1, 0, 1, 2, 2, 1, 3, 0, 2, 3, 2, 1, 1, 4, 2, 13, 2, 8, 2, 12, 1, 10, 1,
            9, 1, 2, 3, 6, 1, 5, 1, 3, 1, 5, 2, 3, 1, 3, 2, 0, 1, 0);
    Dungeon d = new Maze(WrappingStyle.NONWRAPPING, 6, 4, 4,
            80, pre, 3);
    Readable input = new StringReader("P T P T Q");
    Appendable gameLog = new StringBuilder();
    TextDungeonController c = new TextDungeonConsoleController(input, gameLog);
    c.playGame(d);
    String expected = "Welcome to the Dungeon Game\n"
            + "You smell a Less Pungent smell\n"
            + "You are in a Cave\n"
            + "Doors lead to [NORTH, SOUTH, WEST]\n"
            + "You find 1 arrows here\n"
            + "You find [SAPPHIRES, DIAMONDS] treasures here\n"
            + "Move, Pickup, or Shoot (M-P-S)?\n"
            + "Treasures or Arrows (T-A)?\n"
            + "After pickup the player has following items\n"
            + "Treasures: {DIAMONDS=1, RUBIES=0, SAPPHIRES=1}\n"
            + "Arrows: 3\n"
            + "Move, Pickup, or Shoot (M-P-S)?\n"
            + "Treasures or Arrows (T-A)?\n"
            + "CAN'T PICKUP, There are no requested items in the cave\n"
            + "Move, Pickup, or Shoot (M-P-S)?\n"
            + "You have quit the game\n";
    assertEquals(expected, gameLog.toString());
  }

  @Test
  public void testPlayerWins() throws IOException {
    RandomInterface pre = new RandomPredictor(16, 18, 16, 5, 12, 12, 16, 5, 8, 10, 12, 6, 8,
            2, 6, 3, 1, 2, 0, 0, 3, 2, 1, 0, 5, 0, 11, 1, 1, 11, 1, 1, 8, 1, 2, 3, 1, 1, 5, 3, 2,
            5, 1, 2, 4, 3, 1, 0, 1, 2, 2, 1, 3, 0, 2, 3, 2, 1, 1, 4, 2, 13, 2, 8, 2, 12, 1, 10, 1,
            9, 1, 2, 3, 6, 1, 5, 1, 3, 1, 5, 2, 3, 1, 3, 2, 0, 1, 0);
    Dungeon d = new Maze(WrappingStyle.NONWRAPPING, 6, 4, 4,
            80, pre, 3);
    Readable input = new StringReader("P A P T M W M S M W M N S 1 W S 1 W M W");
    Appendable gameLog = new StringBuilder();
    TextDungeonController c = new TextDungeonConsoleController(input, gameLog);
    c.playGame(d);
    String expected = "Welcome to the Dungeon Game\n"
            + "You smell a Less Pungent smell\n"
            + "You are in a Cave\n"
            + "Doors lead to [NORTH, SOUTH, WEST]\n"
            + "You find 1 arrows here\n"
            + "You find [SAPPHIRES, DIAMONDS] treasures here\n"
            + "Move, Pickup, or Shoot (M-P-S)?\n"
            + "Treasures or Arrows (T-A)?\n"
            + "After pickup the player has following items\n"
            + "Treasures: {DIAMONDS=0, RUBIES=0, SAPPHIRES=0}\n"
            + "Arrows: 4\n"
            + "Move, Pickup, or Shoot (M-P-S)?\n"
            + "Treasures or Arrows (T-A)?\n"
            + "After pickup the player has following items\n"
            + "Treasures: {DIAMONDS=1, RUBIES=0, SAPPHIRES=1}\n"
            + "Arrows: 4\n"
            + "Move, Pickup, or Shoot (M-P-S)?\n"
            + "Where to (N-S-E-W)?\n"
            + "You smell a More Pungent smell\n"
            + "You are in a Cave\n"
            + "Doors lead to [EAST, NORTH, SOUTH]\n"
            + "You find 1 arrows here\n"
            + "You find [DIAMONDS, DIAMONDS, DIAMONDS, RUBIES] treasures here\n"
            + "Move, Pickup, or Shoot (M-P-S)?\n"
            + "Where to (N-S-E-W)?\n"
            + "You smell a Less Pungent smell\n"
            + "You are in a Cave\n"
            + "Doors lead to [NORTH, SOUTH, WEST]\n"
            + "You find 2 arrows here\n"
            + "You find [DIAMONDS, DIAMONDS, RUBIES] treasures here\n"
            + "Move, Pickup, or Shoot (M-P-S)?\n"
            + "Where to (N-S-E-W)?\n"
            + "You smell a More Pungent smell\n"
            + "You are in a Cave\n"
            + "Doors lead to [EAST, NORTH, SOUTH, WEST]\n"
            + "You find 2 arrows here\n"
            + "You find [DIAMONDS, DIAMONDS, RUBIES] treasures here\n"
            + "Move, Pickup, or Shoot (M-P-S)?\n"
            + "Where to (N-S-E-W)?\n"
            + "You smell a More Pungent smell\n"
            + "You are in a Cave\n"
            + "Doors lead to [NORTH, SOUTH, WEST]\n"
            + "You find 1 arrows here\n"
            + "You find [DIAMONDS, RUBIES] treasures here\n"
            + "Move, Pickup, or Shoot (M-P-S)?\n"
            + "No. of caves (1-5)?\n"
            + "Where to (N-S-E-W)?\n"
            + "You hear a small howl in the distance\n"
            + "Move, Pickup, or Shoot (M-P-S)?\n"
            + "No. of caves (1-5)?\n"
            + "Where to (N-S-E-W)?\n"
            + "You hear a great howl in the distance\n"
            + "Move, Pickup, or Shoot (M-P-S)?\n"
            + "Where to (N-S-E-W)?\n"
            + "Congo! You have reached the end, the game is over\n";
    assertEquals(expected, gameLog.toString());
    assertTrue(d.isGameOver());
  }

  @Test
  public void testPlayerLoses() throws IOException {
    RandomInterface pre = new RandomPredictor(16, 18, 16, 5, 12, 12, 16, 5, 8, 10, 12, 6, 8,
            2, 6, 3, 1, 2, 0, 0, 3, 2, 1, 0, 5, 0, 11, 1, 1, 11, 1, 1, 8, 1, 2, 3, 1, 1, 5, 3, 2,
            5, 1, 2, 4, 3, 1, 0, 1, 2, 2, 1, 3, 0, 2, 3, 2, 1, 1, 4, 2, 13, 2, 8, 2, 12, 1, 10, 1,
            9, 1, 2, 3, 6, 1, 5, 1, 3, 1, 5, 2, 3, 1, 3, 2, 0, 1, 0);
    Dungeon d = new Maze(WrappingStyle.NONWRAPPING, 6, 4, 4,
            80, pre, 3);
    Readable input = new StringReader("M N M W");
    Appendable gameLog = new StringBuilder();
    TextDungeonController c = new TextDungeonConsoleController(input, gameLog);
    c.playGame(d);
    String expected = "Welcome to the Dungeon Game\n"
            + "You smell a Less Pungent smell\n"
            + "You are in a Cave\n"
            + "Doors lead to [NORTH, SOUTH, WEST]\n"
            + "You find 1 arrows here\n"
            + "You find [SAPPHIRES, DIAMONDS] treasures here\n"
            + "Move, Pickup, or Shoot (M-P-S)?\n"
            + "Where to (N-S-E-W)?\n"
            + "You smell a More Pungent smell\n"
            + "You are in a Tunnel\n"
            + "Doors lead to [SOUTH, WEST]\n"
            + "You find 0 arrows here\n"
            + "You find [] treasures here\n"
            + "Move, Pickup, or Shoot (M-P-S)?\n"
            + "Where to (N-S-E-W)?\n"
            + "Chomp, chomp, chomp, you are eaten by an Otyugh!\n"
            + "Better luck next time\n";
    assertEquals(expected, gameLog.toString());
    assertTrue(d.isGameOver());
  }

  @Test
  public void testMovePlayerToIsCalledCorrectly() throws IOException {
    Dungeon mock = new MockModel();
    Readable input = new StringReader("M N Q");
    Appendable gameLog = new StringBuilder();
    TextDungeonController c = new TextDungeonConsoleController(input, gameLog);
    c.playGame(mock);
    assertEquals("movePlayerToNORTH", MockModel.S);
  }

  @Test
  public void testPickUpArrowsIsCalledCorrectly() throws IOException {
    Dungeon mock = new MockModel();
    Readable input = new StringReader("P A Q");
    Appendable gameLog = new StringBuilder();
    TextDungeonController c = new TextDungeonConsoleController(input, gameLog);
    c.playGame(mock);
    assertEquals("pickUpArrows", MockModel.S);
  }

  @Test
  public void testPickUpTreasuresIsCalledCorrectly() throws IOException {
    Dungeon mock = new MockModel();
    Readable input = new StringReader("P T Q");
    Appendable gameLog = new StringBuilder();
    TextDungeonController c = new TextDungeonConsoleController(input, gameLog);
    c.playGame(mock);
    assertEquals("pickUpTreasure", MockModel.S);
  }

  @Test
  public void testShootIsCalledCorrectly() throws IOException {
    Dungeon mock = new MockModel();
    Readable input = new StringReader("S 1 N Q");
    Appendable gameLog = new StringBuilder();
    TextDungeonController c = new TextDungeonConsoleController(input, gameLog);
    c.playGame(mock);
    assertEquals("shootNORTH1", MockModel.S);
  }

}
