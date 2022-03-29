package controller;

import model.Dungeon;

import java.io.IOException;

/**
 * Represents a Controller for Dungeon game and handle user moves by executing them using the model;
 * convey move outcomes to the user in some form.
 */
public interface TextDungeonController {

  /**
   * Execute a single game of Dungeon game given a Dungeon Model. When the game is over,
   * the playGame method ends.
   *
   * @param m a non-null Dungeon Model
   */
  void playGame(Dungeon m) throws IOException;
}
