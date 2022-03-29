package dungeontest;

import model.Direction;

import java.io.IOException;

/**
 * A mock Dungeon Obstacle model that throws Illegal State Exception for pickup and shoot methods.
 */
public class NewMockModelIse extends NewMockModel {

  /**
   * Constructor that creates mockModel with an appendable as parameter.
   *
   * @param l log
   */
  public NewMockModelIse(Appendable l) {
    super(l);
  }

  @Override
  public void pickUpTreasure() {
    try {
      log.append("pickUpTreasure called and ISE thrown\n");
    } catch (IOException ignored) { }
    throw new IllegalStateException("ISE thrown from pickUpTreasure");
  }

  @Override
  public void pickUpArrows() {
    try {
      log.append("pickUpArrows called and ISE thrown\n");
    } catch (IOException ignored) { }
    throw new IllegalStateException("ISE thrown from pickUpArrows");
  }

  @Override
  public void shoot(Direction d, int caves) {
    try {
      log.append("shoot called and ISE thrown\n");
    } catch (IOException ignored) { }
    throw new IllegalStateException("ISE thrown from shoot");
  }

}
