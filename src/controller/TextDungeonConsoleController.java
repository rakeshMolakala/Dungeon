package controller;

import model.Direction;
import model.Dungeon;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Controller class that facilitates the functionality of user moves in the Dungeon game.
 */
public class TextDungeonConsoleController implements TextDungeonController {

  private final Appendable output;
  private final Scanner input;

  /**
   * Constructor for the controller.
   *
   * @param in  the source to read from
   * @param out the target to print to
   */
  public TextDungeonConsoleController(Readable in, Appendable out) {
    if (in == null || out == null) {
      throw new IllegalArgumentException("Readable and Appendable can't be null");
    }
    this.output = out;
    this.input = new Scanner(in);
  }

  @Override
  public void playGame(Dungeon x) {
    if (x == null) {
      throw new IllegalArgumentException("Invalid model");
    }
    boolean quit = false;
    try {
      for (String s : x.getInitialGameStatus()) {
        output.append(s).append("\n");
      }
      while (!x.isGameOver() && !quit) {
        output.append("Move, Pickup, or Shoot (M-P-S)?\n");
        String in = input.next().toUpperCase();
        switch (in) {
          case "Q":
            quit = true;
            output.append("You have quit the game\n");
            break;
          case "M":
            try {
              output.append("Where to (N-S-E-W)?\n");
              boolean moveFound = false;
              while (!moveFound && input.hasNext()) {
                String d = input.next();
                if (d.equalsIgnoreCase("n")) {
                  x.movePlayerTo(Direction.NORTH);
                  moveFound = true;
                } else if (d.equalsIgnoreCase("s")) {
                  x.movePlayerTo(Direction.SOUTH);
                  moveFound = true;
                } else if (d.equalsIgnoreCase("w")) {
                  x.movePlayerTo(Direction.WEST);
                  moveFound = true;
                } else if (d.equalsIgnoreCase("e")) {
                  x.movePlayerTo(Direction.EAST);
                  moveFound = true;
                } else {
                  output.append("Not a valid direction, enter again\n");
                }
              }
              if (moveFound) {
                for (String s : x.getPlayerLocationStatus()) {
                  output.append(s).append("\n");
                }
              }
            } catch (IllegalArgumentException e) {
              output.append(e.getMessage()).append("\n");
            }
            catch (NoSuchElementException e) {
              break;
            }
            break;
          case "P":
            try {
              output.append("Treasures or Arrows (T-A)?\n");
              boolean validPFound = false;
              while (!validPFound && input.hasNext()) {
                String p = input.next();
                if (p.equalsIgnoreCase("a")) {
                  x.pickUpArrows();
                  validPFound = true;
                } else if (p.equalsIgnoreCase("t")) {
                  x.pickUpTreasure();
                  validPFound = true;
                } else {
                  output.append("Not a valid pickup specification, enter again\n");
                }
              }
              if (validPFound) {
                output.append("After pickup the player has following items\n");
                for (String s : x.getPlayerDescription()) {
                  output.append(s).append("\n");
                }
              }
            } catch (IllegalStateException e) {
              output.append(e.getMessage()).append("\n");
            }
            break;
          case "S":
            try {
              int number = 0;
              output.append("No. of caves (1-5)?\n");
              boolean validNFound = false;
              while (!validNFound && input.hasNext()) {
                String d = input.next();
                try {
                  number = Integer.parseInt(d);
                  validNFound = true;
                } catch (NumberFormatException e) {
                  output.append("Not a valid number of caves, enter again\n");
                }
              }
              output.append("Where to (N-S-E-W)?\n");
              boolean validDFound = false;
              while (!validDFound && input.hasNext()) {
                String d = input.next();
                if (d.equalsIgnoreCase("n")) {
                  x.shoot(Direction.NORTH, number);
                  validDFound = true;
                } else if (d.equalsIgnoreCase("s")) {
                  x.shoot(Direction.SOUTH, number);
                  validDFound = true;
                } else if (d.equalsIgnoreCase("w")) {
                  x.shoot(Direction.WEST, number);
                  validDFound = true;
                } else if (d.equalsIgnoreCase("e")) {
                  x.shoot(Direction.EAST, number);
                  validDFound = true;
                } else {
                  output.append("Not a valid direction, enter again\n");
                }
              }
              if (validDFound) {
                for (String s : x.getPlayerLocationStatus()) {
                  output.append(s).append("\n");
                }
              }
            } catch (IllegalArgumentException | IllegalStateException e1) {
              output.append(e1.getMessage()).append("\n");
            }
            break;
          default:
            output.append("Unknown Command, try again\n");
            break;
        }
      }
    }
    catch (IOException e) {
      throw new IllegalStateException("Append failed", e);
    }
  }
}
