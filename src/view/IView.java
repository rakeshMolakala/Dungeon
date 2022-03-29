package view;

import controller.GuiFeatures;
import model.ReadOnlyDungeonObstacle;

/**
 * Represents the View for Dungeon game by displaying all the necessary components
 * and responsible for interacting with GUI controller upon user actions on the GUI interface.
 */
public interface IView {

  /**
   * Makes the GUI become visible to the user which is called by the controller.
   */
  void makeVisible();

  /**
   * Responsible for setting action listeners in home board which is called by the controller.
   *
   * @param f controller object
   */
  void setFeaturesForHome(GuiFeatures f);

  /**
   * Responsible for setting action listeners in game board which is called by the controller.
   *
   * @param f controller object
   */
  void setFeaturesForGame(GuiFeatures f);

  /**
   * Sets the readonly model from the controller and uses to display the information on GUI.
   *
   * @param m readonly dungeon obstacle object
   */
  void setModel(ReadOnlyDungeonObstacle m);

  /**
   * Changes the GUI from Game board to Home board when goHome() is called by the controller.
   */
  void turnPageToHome();

  /**
   * Changes the GUI from Home board to Game board when controller creates a model and displays the
   * game board.
   */
  void turnPageToGame();

  /**
   * After every valid action on the GUI by the user, game board gets restarted to represent the
   * new model state changes which is called by the controller.
   */
  void restartGameBoard();

  /**
   * Displays the error message received from the controller in model creation by using JOptionPane.
   *
   * @param s error message
   */
  void displayError(String s);

  /**
   * Disposes the GUI, and it is called by the controller when user clicks quit option.
   */
  void quitGame();
}
