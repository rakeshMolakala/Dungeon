package dungeontest;

import model.Direction;

import java.io.IOException;

/**
 * A mock Dungeon Obstacle model that throws Illegal Argument Exception for move, shoot methods.
 */
public class NewMockModelIae extends NewMockModel {

  /**
   * Constructor that creates mockModel with an appendable as parameter.
   *
   * @param l log
   */
  public NewMockModelIae(Appendable l) {
    super(l);
  }

  @Override
  public void movePlayerTo(Direction direction) {
    try {
      log.append("move called and IAE thrown\n");
    } catch (IOException ignored) { }
    throw new IllegalArgumentException("IAE thrown from move");
  }

  @Override
  public void shoot(Direction d, int caves) {
    try {
      log.append("shoot called and IAE thrown\n");
    } catch (IOException ignored) { }
    throw new IllegalArgumentException("IAE thrown from shoot");
  }

}
