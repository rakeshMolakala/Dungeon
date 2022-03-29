package view;

import controller.GuiFeatures;
import model.ReadOnlyDungeonObstacle;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

/**
 * JFrameView that extends JFrame and enables player to play the Dungeon game.
 */
public class JFrameView extends JFrame implements IView {

  private final HomeBoard homePanel;
  private ReadOnlyDungeonObstacle model;
  private GameBoard gameBoard;
  private GuiFeatures f;

  /**
   * View Constructor that initialises the JFrame and sets up the GUI.
   */
  public JFrameView() {
    super("Dungeon Game");
    Dimension dimMax = Toolkit.getDefaultToolkit().getScreenSize();
    this.setMaximumSize(dimMax);
    this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());
    homePanel = new HomeBoard();
    add(homePanel);
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public void setFeaturesForHome(GuiFeatures f) {
    this.f = f;
    homePanel.setFeaturesInHome(f);
  }

  @Override
  public void setFeaturesForGame(GuiFeatures f) {
    gameBoard.setFeaturesInGame(f);
  }

  @Override
  public void setModel(ReadOnlyDungeonObstacle m) {
    if (m != null) {
      model = m;
      if (gameBoard != null) {
        remove(gameBoard);
      }
    }
  }

  @Override
  public void turnPageToHome() {
    remove(gameBoard);
    add(homePanel);
    homePanel.setVisible(true);
    revalidate();
    repaint();
  }

  @Override
  public void turnPageToGame() {
    remove(homePanel);
    gameBoard = new GameBoard(model, f);
    add(gameBoard);
    gameBoard.repaint();
    revalidate();
    repaint();
  }

  @Override
  public void restartGameBoard() {
    revalidate();
    repaint();
  }

  @Override
  public void displayError(String s) {
    JOptionPane.showMessageDialog(this, s);
  }

  @Override
  public void quitGame() {
    this.dispose();
  }

}
