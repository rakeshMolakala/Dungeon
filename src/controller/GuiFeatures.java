package controller;

import model.Direction;
import model.WrappingStyle;

/**
 * Represents a GUI Controller interface for Dungeon game and handles user moves and actions
 * by passing to the model and passing the corresponding feedback to the view.
 */
public interface GuiFeatures {

  /**
   * Creates the model object with the parameters received from the view.
   *
   * @param wrap wrapping style
   * @param interconnectivity interconnectivity degree value
   * @param rows number of rows
   * @param columns number of columns
   * @param itemsPercentage itemsPercentage value
   * @param difficulty difficulty value
   */
  void createDungeon(WrappingStyle wrap, int interconnectivity, int rows, int columns,
                     double itemsPercentage, int difficulty);

  /**
   * Starts the GUI interface.
   */
  void startGame();

  /**
   * Calls the view to go to home page where dungeon creation input fields are taken from user.
   */
  void goToHome();

  /**
   * Restarts the game with the same dungeon configuration but not the same dungeon.
   */
  void restart();

  /**
   * Responsible for moving the player from the input action done on the view through keyboard
   * keys and calls the appropriate model function.
   *
   * @param d direction enum
   */
  void move(Direction d);

  /**
   * Responsible for player picking up the treasure from the input action done on the view through
   * keyboard keys and calls the appropriate model function.
   */
  void pickUpTreasure();

  /**
   * Responsible for player picking up the arrows from the input action done on the view through
   * keyboard keys and calls the appropriate model function.
   */
  void pickUpArrows();

  /**
   * Responsible for player shooting an arrow from the input action done on the view
   * and calls the appropriate model function.
   *
   * @param d direction enum
   * @param caves number of caves
   */
  void shoot(Direction d, int caves);

  /**
   * Quits the game.
   */
  void exitProgram();

  /**
   * Responsible for moving the player from the input action done on the view through mouse clicks
   * and calls the appropriate model function.
   *
   * @param locationId locationID
   */
  void movePlayerTo(int locationId);

  /**
   * Responsible for player fighting the moving monster from the input action done on the view
   * through keyboard keys and calls the appropriate model function.
   */
  void fightMonster();

  /**
   * Restarts the game with the same dungeon configuration and with the same dungeon.
   */
  void reuse();
}

