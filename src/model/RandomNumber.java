package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * RandomNumber class that facilitates the RandomInterface in generating a random number.
 */
public class RandomNumber implements RandomInterface {
  private Random rand;
  private List<Integer> track;

  /**
   * Constructor that creates ta Random Interface object with a field of type Random.
   */
  public RandomNumber() {
    this.track = new ArrayList<>();
    this.rand = new Random();
  }

  /**
   * The getRandomNumber of the Random Interface object
   * gives the random number between two integer values.
   *
   * @param min minimum limit of the random number to be generated
   * @param max maximum limit of the random number to be generated(inclusive)
   */
  @Override
  public int getRandomNumber(int min, int max) {
    int x = rand.nextInt(max + 1 - min) + min;
    track.add(x);
    return x;
  }

  @Override
  public List<Integer> getTrack() {
    return track;
  }


}
