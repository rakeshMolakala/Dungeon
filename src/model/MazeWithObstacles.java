package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * This class incorporates all the functionalities associated with the dungeon with obstacles
 * and entire game's functionality is handled by this class.
 */
public class MazeWithObstacles extends Maze implements DungeonObstacle {

  private boolean isPlayerTurn;
  private boolean isMovingMonsterAlive;
  private List<Integer> prev;
  private Monster movingMonster;

  /**
   * Constructs the Dungeon Obstacle Interface object and assigns all the parameters.
   *
   * @param w                       wrapping style of dungeon to be created.
   * @param interconnectivityDegree interconnectivity degree of the maze to be created.
   * @param xLength                 x dimension of the dungeon to be created.
   * @param yLength                 y dimension of the dungeon to be created.
   * @param itemsPercentage         percentage of caves to be filled with treasure.
   * @param rand                    RandomInterface object to generate the random number
   * @param noOfMonsters            No of monsters to be placed in dungeon.
   * @throws IllegalArgumentException when the invalid values of parameters are passed
   */
  public MazeWithObstacles(WrappingStyle w, int interconnectivityDegree, int xLength, int yLength,
                           double itemsPercentage, RandomInterface rand, int noOfMonsters) {
    super(w, interconnectivityDegree, xLength, yLength, itemsPercentage, rand, noOfMonsters);
    isPlayerTurn = false;
    isMovingMonsterAlive = true;
    prev = new ArrayList<>();
  }

  private MazeWithObstacles(WrappingStyle w, int interconnectivityDegree, int xLength, int yLength,
                            double itemsPercentage, RandomInterface rand, int noOfMonsters,
                            List<Integer> track) {
    super(w, interconnectivityDegree, xLength, yLength, itemsPercentage, rand, noOfMonsters);
    isPlayerTurn = false;
    isMovingMonsterAlive = true;
    this.prev = track;
  }

  private void addPits() {
    int pits = this.noOfMonsters - 1;
    List<Integer> caveId = new ArrayList<>(caveIdList);
    while (pits > 0) {
      int r = rand.getRandomNumber(0, caveId.size() - 1);
      Location l = getLocation(caveId.get(r));
      if (l == null) {
        break;
      }
      if (l.getLocationId() != start.getLocationId() && l.getLocationId() != end.getLocationId()) {
        ((LocationC) l).installPit();
        pits--;
      }
      caveId.remove(Integer.valueOf(l.getLocationId()));
    }
    prev = rand.getTrack();
  }

  @Override
  protected void addMonsters(int noOfMonsters) {
    int monsterId = 1;
    List<Integer> caveId = new ArrayList<>(caveIdList);
    int monsters = noOfMonsters + 1;
    if (monsters > caveId.size() - 1) {
      throw new IllegalStateException("Dungeon cannot facilitate the given number of monsters");
    }
    while (monsters > 1) {
      int r = rand.getRandomNumber(0, caveId.size() - 1);
      Location l = getLocation(caveId.get(r));
      if (l == null) {
        break;
      }
      if (l.getLocationId() != start.getLocationId() && l.getLocationId() != end.getLocationId()
              && !l.hasPit()) {
        if (monsters == 2) {
          this.movingMonster = new MovingMonster(monsterId, l);
        } else {
          ((LocationC) l).addOtyugh(new Otyugh(monsterId, l));
        }
        monsterId++;
        monsters--;
      }
      caveId.remove(Integer.valueOf(l.getLocationId()));
    }
    ((LocationC) end).addOtyugh(new Otyugh(monsterId, end));
    addPits();
  }

  @Override
  public void movePlayerTo(Direction direction) {
    if (playerLocation.getNeighbours().get(direction) == null || direction == null) {
      throw new IllegalArgumentException("There's no possible path in the direction you"
              + " have mentioned");
    }
    if (!gameOver) {
      playerLocationStatus = new ArrayList<>();
      playerLocation = playerLocation.getNeighbours().get(direction);
      locationTrack.add(playerLocation.getLocationId());
      int m = rand.getRandomNumber(1, 2);
      if (m == 1 && movingMonster.getArrowHit() < 4) {
        movingMonster.setLocation(movingMonster.getLocation().getNeighbours()
                .get(getRandomDirection(movingMonster.getLocation())));
      }
      int p = rand.getRandomNumber(1, 2);
      if (p == 1) {
        thiefLocation = thiefLocation.getNeighbours().get(getRandomDirection(thiefLocation));
      }
      if (playerLocation.hasPit()) {
        playerLocationStatus.add("Oops, you have fallen in a pit and injured your legs");
        player.reduceHealth(20);
      }
      if (((LocationC) playerLocation).getOtyugh() != null) {
        if (((LocationC) playerLocation).getOtyugh().getArrowHit() == 1) {
          int a = rand.getRandomNumber(1, 2);
          if (a == 1) {
            gameOver = true;
          } else {
            playerLocationStatus.add("You are Lucky, you escaped an injured Otyugh");
          }
        } else {
          playerLocationStatus.add("Chomp, chomp, chomp, you are eaten by an Otyugh!\n"
                  + "Better luck next time");
          player.reduceHealth(100);
          gameOver = true;
        }
      }
      if (movingMonster.getLocation().getLocationId() == playerLocation.getLocationId()
              && movingMonster.getArrowHit() < 4) {
        playerLocationStatus.add("You have encountered a moving monster");
        int turn = rand.getRandomNumber(1, 2);
        if (turn != 1) {
          player.reduceHealth(15);
        }
        isPlayerTurn = true;
      }
      if (thiefLocation.getLocationId() == playerLocation.getLocationId()) {
        player.lootTreasure();
        playerLocationStatus.add("You have encountered a thief and got looted all your treasures");
      }
      if (!gameOver && player.getHealth() == 0) {
        playerLocationStatus.add("You are dead by loosing all health");
        gameOver = true;
      }
      if (!gameOver) {
        if (playerLocation.getLocationId() == end.getLocationId()) {
          playerLocationStatus.add("Congo! You have reached the end, the game is over");
          gameOver = true;
        } else {
          locationStatus();
        }
      }
    }
  }


  private Direction getRandomDirection(Location l) {
    List<Direction> possibleDirections = new ArrayList<>(l.getNeighbours().keySet());
    possibleDirections.sort(Comparator.comparing(Enum::toString));
    int r = rand.getRandomNumber(0, possibleDirections.size() - 1);
    return possibleDirections.get(r);
  }

  @Override
  protected void locationStatus() {
    super.locationStatus();
    if (pitDetection(playerLocation)) {
      playerLocationStatus.add("There's a pit near by, be careful");
    }
  }

  private boolean pitDetection(Location l) {
    if (l == null) {
      throw new IllegalArgumentException("null values");
    }
    Location north = l.getNeighbours().get(Direction.NORTH);
    if (north != null && north.hasPit()) {
      return true;
    }
    Location south = l.getNeighbours().get(Direction.SOUTH);
    if (south != null && south.hasPit()) {
      return true;
    }
    Location east = l.getNeighbours().get(Direction.EAST);
    if (east != null && east.hasPit()) {
      return true;
    }
    Location west = l.getNeighbours().get(Direction.WEST);
    return west != null && west.hasPit();
  }

  @Override
  public boolean isPitNearBy() {
    return pitDetection(playerLocation);
  }

  @Override
  public int getThiefLocationId() {
    return thiefLocation.getLocationId();
  }

  @Override
  public int getMovingMonsterLocationId() {
    return movingMonster.getLocation().getLocationId();
  }

  @Override
  public int getMovingMonsterHealth() {
    int a = 4 - movingMonster.getArrowHit();
    if (a > 0) {
      return a * 25;
    } else {
      return 0;
    }
  }

  @Override
  public boolean isMovingMonsterAlive() {
    return isMovingMonsterAlive;
  }

  @Override
  public void fight() {
    if (!gameOver) {
      if (isPlayerTurn) {
        movingMonster.recordHit();
        if (movingMonster.getArrowHit() == 4) {
          isMovingMonsterAlive = false;
        }
        if (movingMonster.getArrowHit() < 4) {
          movingMonster.setLocation(movingMonster.getLocation().getNeighbours()
                  .get(getRandomDirection(movingMonster.getLocation())));
        }
        isPlayerTurn = false;
      }
    }
  }

  @Override
  public DungeonObstacle getInitialModel() {
    if (prev.size() == 0) {
      prev = rand.getTrack();
    }
    RandomInterface randPred = new RandomPredictor(prev);
    WrappingStyle w;
    if (this.isWrapping()) {
      w = WrappingStyle.WRAPPING;
    } else {
      w = WrappingStyle.NONWRAPPING;
    }
    DungeonObstacle x = new MazeWithObstacles(w, this.getInterconnectivityDegree(), this.getRows(),
            this.getColumns(), this.getItemsPercentage(), randPred, noOfMonsters, prev);
    x.setRandom();
    return x;
  }

  @Override
  public void setRandom() {
    this.rand = new RandomNumber();
  }

  @Override
  public List<Integer> getPrevRandom() {
    if (prev.size() == 0) {
      return rand.getTrack();
    }
    return new ArrayList<>(prev);
  }


}
