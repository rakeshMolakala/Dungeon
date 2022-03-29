package model;

import java.util.List;

/**
 * Random interface helps us in getting a random number according to the requirement and
 * also the pseudo random number that makes the random number predictable.
 */
public interface RandomInterface {

  /**
   * The getRandomNumber gives the random number between two integer values.
   *
   * @param min minimum limit of the random number to be generated
   * @param max maximum limit of the random number to be generated(inclusive)
   */
  int getRandomNumber(int min, int max);

  /**
   * Returns the list of all the random numbers generated.
   *
   * @return list of integers of random numbers.
   */
  List<Integer> getTrack();

}
