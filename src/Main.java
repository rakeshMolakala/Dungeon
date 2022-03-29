import controller.GuiDungeonController;
import controller.GuiFeatures;
import controller.TextDungeonConsoleController;
import model.Dungeon;
import model.Maze;
import model.RandomInterface;
import model.RandomNumber;
import model.WrappingStyle;

import java.io.InputStreamReader;

/**
 * Main class that facilitates the test run of the dungeon game.
 */
public class Main {

  /**
   * Main function of the main class that enables the GUI view or starts the text based game.
   */
  public static void main(String[] args) {
    if (args.length > 0) {
      if (args.length == 6) {
        Readable input = new InputStreamReader(System.in);
        Appendable output = System.out;
        RandomInterface har = new RandomNumber();
        WrappingStyle wS;
        if (args[0].equalsIgnoreCase("true")) {
          wS = WrappingStyle.WRAPPING;
        } else {
          wS = WrappingStyle.NONWRAPPING;
        }
        int interConn = Integer.parseInt(args[1]);
        int rows = Integer.parseInt(args[2]);
        int columns = Integer.parseInt(args[3]);
        double percentage = Double.parseDouble(args[4]);
        int noOfMonsters = Integer.parseInt(args[5]);
        Dungeon x1 = new Maze(wS, interConn, rows, columns,
                percentage, har, noOfMonsters);
        new TextDungeonConsoleController(input, output).playGame(x1);
        System.out.println();
      } else {
        System.out.println("Invalid number of arguments for console based game");
        System.exit(0);
      }
    } else {
      GuiFeatures controller = new GuiDungeonController();
      controller.startGame();
    }
  }

}
