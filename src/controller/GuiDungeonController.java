package controller;

import model.Direction;
import model.DungeonObstacle;
import model.MazeWithObstacles;
import model.RandomInterface;
import model.RandomNumber;
import model.WrappingStyle;
import view.IView;
import view.JFrameView;

/**
 * Controller class that facilitates the functionality of user moves in GUI based Dungeon game.
 */
public class GuiDungeonController implements GuiFeatures {

  private final IView view;
  private DungeonObstacle model;

  /**
   * Constructor for the GUI based controller.
   */
  public GuiDungeonController() {
    view = new JFrameView();
    view.setFeaturesForHome(this);
  }

  /**
   * Constructor for the GUI based controller that takes in the model and view and starts the GUI.
   *
   * @param m  model
   * @param v view
   */
  public GuiDungeonController(DungeonObstacle m, IView v) {
    this.model = m;
    this.view = v;
  }

  @Override
  public void createDungeon(WrappingStyle wrap, int interconnectivity, int rows, int columns,
                            double itemsPercentage, int difficulty) {
    RandomInterface rand = new RandomNumber();
    try {
      model = new MazeWithObstacles(wrap, interconnectivity, rows, columns, itemsPercentage,
              rand, difficulty);
      view.setModel(model);
      view.turnPageToGame();
      view.setFeaturesForGame(this);
    }
    catch (IllegalArgumentException | IllegalStateException e) {
      view.displayError(e.getMessage());
    }
  }

  @Override
  public void startGame() {
    view.makeVisible();
  }

  @Override
  public void goToHome() {
    view.turnPageToHome();
  }

  @Override
  public void restart() {
    RandomInterface rand = new RandomNumber();
    WrappingStyle w;
    if (model.isWrapping()) {
      w = WrappingStyle.WRAPPING;
    } else {
      w = WrappingStyle.NONWRAPPING;
    }
    model = new MazeWithObstacles(w, model.getInterconnectivityDegree(), model.getRows(),
            model.getColumns(),
            model.getItemsPercentage(), rand, model.getDifficulty());
    view.setModel(model);
    view.turnPageToGame();
    view.setFeaturesForGame(this);
  }

  @Override
  public void move(Direction d) {
    try {
      model.movePlayerTo(d);
      view.restartGameBoard();
      if (model.isGameOver()) {
        if (model.isPlayerAlive()) {
          view.displayError("You have reached the End, Game Over");
        } else {
          view.displayError("You are Dead, Game Over");
        }
      }
    }
    catch (IllegalArgumentException ignored) { }

  }

  @Override
  public void pickUpTreasure() {
    try {
      model.pickUpTreasure();
      view.restartGameBoard();
    }
    catch (IllegalStateException e) {
      view.displayError(e.getMessage()); }
  }

  @Override
  public void pickUpArrows() {
    try {
      model.pickUpArrows();
      view.restartGameBoard();
    }
    catch (IllegalStateException e) {
      view.displayError(e.getMessage());
    }
  }

  @Override
  public void shoot(Direction d, int caves) {
    try {
      model.shoot(d,caves);
      view.restartGameBoard();
    }
    catch (IllegalStateException | IllegalArgumentException e) {
      view.displayError(e.getMessage());
    }
  }

  @Override
  public void exitProgram() {
    view.quitGame();
  }

  @Override
  public void movePlayerTo(int locationId) {
    try {
      model.moveToLocation(locationId);
      view.restartGameBoard();
      if (model.isGameOver()) {
        if (model.isPlayerAlive()) {
          view.displayError("You have reached the End, Game Over");
        } else {
          view.displayError("You are Dead, Game Over");
        }
      }
    }
    catch (IllegalArgumentException ignored) { }
  }

  @Override
  public void fightMonster() {
    model.fight();
    view.restartGameBoard();
  }

  @Override
  public void reuse() {
    model = model.getInitialModel();
    view.setModel(model);
    view.turnPageToGame();
    view.setFeaturesForGame(this);
  }
}


