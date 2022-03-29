package dungeontest;

import controller.GuiFeatures;
import model.ReadOnlyDungeonObstacle;
import view.IView;

import java.io.IOException;

/**
 * A mock view that is used for testing.
 */
public class MockView implements IView {
  private final Appendable log;

  /**
   * Constructor that creates mockView with an appendable as parameter.
   *
   * @param l log
   */
  public MockView(Appendable l) {
    this.log = l;
  }

  @Override
  public void makeVisible() {
    try {
      log.append("makeVisible\n");
    } catch (IOException ignored) { }
  }

  @Override
  public void setFeaturesForHome(GuiFeatures f) {
    try {
      log.append("setFeaturesForHome\n");
    } catch (IOException ignored) { }
  }

  @Override
  public void setFeaturesForGame(GuiFeatures f) {
    try {
      log.append("setFeaturesForGame\n");
    } catch (IOException ignored) { }
  }

  @Override
  public void setModel(ReadOnlyDungeonObstacle m) {
    try {
      log.append("setModel\n");
    } catch (IOException ignored) { }
  }

  @Override
  public void turnPageToHome() {
    try {
      log.append("turnPageToHome\n");
    } catch (IOException ignored) { }
  }

  @Override
  public void turnPageToGame() {
    try {
      log.append("turnPageToGame\n");
    } catch (IOException ignored) { }
  }

  @Override
  public void restartGameBoard() {
    try {
      log.append("restartGameBoard\n");
    }
    catch (IOException ignored) { }
  }

  @Override
  public void displayError(String s) {
    try {
      log.append("displayError").append(s).append("\n");
    } catch (IOException ignored) { }
  }

  @Override
  public void quitGame() {
    try {
      log.append("quitGame\n");
    } catch (IOException ignored) { }
  }

}
