package dungeontest;

import static org.junit.Assert.assertEquals;

import controller.GuiDungeonController;
import model.Direction;
import model.DungeonObstacle;
import model.WrappingStyle;
import org.junit.Test;
import view.IView;

/**
 * Test cases for the Dungeon GUI based controller, using mock model and mock view.
 */
public class GuiDungeonControllerTests {

  @Test
  public void testMoveByKeyClicksIsExecutedCorrectly() {
    Appendable log = new StringBuilder();
    DungeonObstacle mockModel = new NewMockModel(log);
    IView mockView = new MockView(log);
    GuiDungeonController c = new GuiDungeonController(mockModel, mockView);
    c.move(Direction.NORTH);
    assertEquals("movePlayerToNORTH\n"
            + "restartGameBoard\n", log.toString());
  }

  @Test
  public void testPickUpTreasureExecutedCorrectly() {
    Appendable log = new StringBuilder();
    DungeonObstacle mockModel = new NewMockModel(log);
    IView mockView = new MockView(log);
    GuiDungeonController c = new GuiDungeonController(mockModel, mockView);
    c.pickUpTreasure();
    assertEquals("pickUpTreasure\n"
            + "restartGameBoard\n", log.toString());
  }

  @Test
  public void testPickUpArrowsExecutedCorrectly() {
    Appendable log = new StringBuilder();
    DungeonObstacle mockModel = new NewMockModel(log);
    IView mockView = new MockView(log);
    GuiDungeonController c = new GuiDungeonController(mockModel, mockView);
    c.pickUpArrows();
    assertEquals("pickUpArrows\n"
            + "restartGameBoard\n", log.toString());
  }

  @Test
  public void testShootExecutedCorrectly() {
    Appendable log = new StringBuilder();
    DungeonObstacle mockModel = new NewMockModel(log);
    IView mockView = new MockView(log);
    GuiDungeonController c = new GuiDungeonController(mockModel, mockView);
    c.shoot(Direction.NORTH, 2);
    assertEquals("shootNORTH2\n"
            + "restartGameBoard\n", log.toString());
  }

  @Test
  public void testMoveByMouseClicks() {
    Appendable log = new StringBuilder();
    DungeonObstacle mockModel = new NewMockModel(log);
    IView mockView = new MockView(log);
    GuiDungeonController c = new GuiDungeonController(mockModel, mockView);
    c.movePlayerTo(12);
    assertEquals("movePlayerTo12\n"
            + "restartGameBoard\n", log.toString());
  }

  @Test
  public void testReuseDoneCorrectly() {
    Appendable log = new StringBuilder();
    DungeonObstacle mockModel = new NewMockModel(log);
    IView mockView = new MockView(log);
    GuiDungeonController c = new GuiDungeonController(mockModel, mockView);
    c.reuse();
    assertEquals("setModel\n"
            + "turnPageToGame\n"
            + "setFeaturesForGame\n", log.toString());
  }

  @Test
  public void testFightCalledCorrectly() {
    Appendable log = new StringBuilder();
    DungeonObstacle mockModel = new NewMockModel(log);
    IView mockView = new MockView(log);
    GuiDungeonController c = new GuiDungeonController(mockModel, mockView);
    c.fightMonster();
    assertEquals("fight\n"
            + "restartGameBoard\n", log.toString());
  }

  @Test
  public void sequenceCalledCorrectly() {
    Appendable log = new StringBuilder();
    DungeonObstacle mockModel = new NewMockModel(log);
    IView mockView = new MockView(log);
    GuiDungeonController c = new GuiDungeonController(mockModel, mockView);
    c.move(Direction.NORTH);
    c.pickUpTreasure();
    c.pickUpArrows();
    c.shoot(Direction.NORTH, 4);
    assertEquals("movePlayerToNORTH\n"
            + "restartGameBoard\n"
            + "pickUpTreasure\n"
            + "restartGameBoard\n"
            + "pickUpArrows\n"
            + "restartGameBoard\n"
            + "shootNORTH4\n"
            + "restartGameBoard\n", log.toString());
  }

  @Test
  public void testMoveInNullDirection() {
    Appendable log = new StringBuilder();
    DungeonObstacle mockModel = new NewMockModel(log);
    IView mockView = new MockView(log);
    GuiDungeonController c = new GuiDungeonController(mockModel, mockView);
    c.move(null);
    assertEquals("movePlayerTonull\n"
            + "restartGameBoard\n", log.toString());
  }

  @Test
  public void testDungeonIsCreatedCorrectly() {
    Appendable log = new StringBuilder();
    DungeonObstacle mockModel = new NewMockModel(log);
    IView mockView = new MockView(log);
    GuiDungeonController c = new GuiDungeonController(mockModel, mockView);
    c.createDungeon(WrappingStyle.WRAPPING, 7, 7, 7,
            70, 4);
    assertEquals("setModel\n"
            + "turnPageToGame\n"
            + "setFeaturesForGame\n", log.toString());
  }

  @Test
  public void testControllerStartedGameCorrectly() {
    Appendable log = new StringBuilder();
    DungeonObstacle mockModel = new NewMockModel(log);
    IView mockView = new MockView(log);
    GuiDungeonController c = new GuiDungeonController(mockModel, mockView);
    c.startGame();
    assertEquals("makeVisible\n", log.toString());
  }

  @Test
  public void testDisplaysErrorMessageForInvalidDungeonCreation() {
    Appendable log = new StringBuilder();
    DungeonObstacle mockModel = new NewMockModel(log);
    IView mockView = new MockView(log);
    GuiDungeonController c = new GuiDungeonController(mockModel, mockView);
    c.createDungeon(WrappingStyle.WRAPPING, -7, 7, 7,
            70, 4);
    assertEquals("displayErrorInvalid Dungeon Creation\n", log.toString());
  }

  @Test
  public void testQuit() {
    Appendable log = new StringBuilder();
    DungeonObstacle mockModel = new NewMockModel(log);
    IView mockView = new MockView(log);
    GuiDungeonController c = new GuiDungeonController(mockModel, mockView);
    c.exitProgram();
    assertEquals("quitGame\n", log.toString());
  }

  @Test
  public void turnPageToHome() {
    Appendable log = new StringBuilder();
    DungeonObstacle mockModel = new NewMockModel(log);
    IView mockView = new MockView(log);
    GuiDungeonController c = new GuiDungeonController(mockModel, mockView);
    c.goToHome();
    assertEquals("turnPageToHome\n", log.toString());
  }

  @Test
  public void testIaeFromMove() {
    Appendable log = new StringBuilder();
    DungeonObstacle mockModel = new NewMockModelIae(log);
    IView mockView = new MockView(log);
    GuiDungeonController c = new GuiDungeonController(mockModel, mockView);
    c.move(Direction.NORTH);
    assertEquals("move called and IAE thrown\n", log.toString());
  }

  @Test
  public void testIseFromPickUpTreasure() {
    Appendable log = new StringBuilder();
    DungeonObstacle mockModel = new NewMockModelIse(log);
    IView mockView = new MockView(log);
    GuiDungeonController c = new GuiDungeonController(mockModel, mockView);
    c.pickUpTreasure();
    assertEquals("pickUpTreasure called and ISE thrown\n"
            + "displayErrorISE thrown from pickUpTreasure\n", log.toString());
  }

  @Test
  public void testIseFromPickUpArrows() {
    Appendable log = new StringBuilder();
    DungeonObstacle mockModel = new NewMockModelIse(log);
    IView mockView = new MockView(log);
    GuiDungeonController c = new GuiDungeonController(mockModel, mockView);
    c.pickUpArrows();
    assertEquals("pickUpArrows called and ISE thrown\n"
            + "displayErrorISE thrown from pickUpArrows\n", log.toString());
  }

  @Test
  public void testIaeFromShoot() {
    Appendable log = new StringBuilder();
    DungeonObstacle mockModel = new NewMockModelIae(log);
    IView mockView = new MockView(log);
    GuiDungeonController c = new GuiDungeonController(mockModel, mockView);
    c.shoot(Direction.NORTH, 2);
    assertEquals("shoot called and IAE thrown\n"
            + "displayErrorIAE thrown from shoot\n", log.toString());
  }

  @Test
  public void testIseFromShoot() {
    Appendable log = new StringBuilder();
    DungeonObstacle mockModel = new NewMockModelIse(log);
    IView mockView = new MockView(log);
    GuiDungeonController c = new GuiDungeonController(mockModel, mockView);
    c.shoot(Direction.NORTH, 2);
    assertEquals("shoot called and ISE thrown\n"
            + "displayErrorISE thrown from shoot\n", log.toString());
  }

}
