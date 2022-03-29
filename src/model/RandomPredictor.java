package model;

import java.util.LinkedList;
import java.util.List;

/**
 * RandomPredictor class that facilitates the RandomInterface in generating a random number.
 */
public class RandomPredictor implements RandomInterface {
  private LinkedList<Integer> preRand;

  /**
   * Constructor that initialises the RandomInterface object that helps in getting the predicted
   * random number using varargs.
   *
   * @param n list of possible values for a Random number
   */
  public RandomPredictor(int... n) {
    preRand = new LinkedList<>();
    for (int i : n) {
      this.preRand.add(i);
    }
  }

  /**
   * Constructor that initialises the RandomInterface object that helps in getting the predicted
   * random number using the passed list of random numbers.
   *
   * @param x list of predictive values of random.
   */
  public RandomPredictor(List<Integer> x) {
    preRand = new LinkedList<>(x);
  }

  /**
   * The getRandomNumber of the Random Interface object
   * gives the predictable random number between two integer values.
   *
   * @param min minimum limit of the predictable random number to be generated
   * @param max maximum limit of the predictable random number to be generated(inclusive)
   */
  @Override
  public int getRandomNumber(int min, int max) {
    int x = this.preRand.get(0);
    preRand.remove();
    return x;
  }

  @Override
  public List<Integer> getTrack() {
    return null;
  }
}

