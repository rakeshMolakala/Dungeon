package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * This class incorporates all the functionalities associated with the dungeon and entire game's
 * functionality is handled by this class.
 */
public class Maze implements Dungeon {

  protected final PlayerInterface player;
  private final WrappingStyle wrappingStyle;
  private final int interconnectivityDegree;
  private final Location[][] dungeonMaze;
  protected Location playerLocation;
  protected Location start;
  protected Location end;
  protected boolean gameOver;
  private final double itemsPercentage;
  protected List<String> playerLocationStatus;
  protected RandomInterface rand;
  protected final List<Integer> caveIdList;
  protected final int noOfMonsters;
  protected Location thiefLocation;
  protected List<Integer> locationTrack;
  private final int rows;
  private final int columns;

  /**
   * Constructs the Dungeon Interface object and assigns all the parameters.
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
  public Maze(WrappingStyle w, int interconnectivityDegree, int xLength,
              int yLength, double itemsPercentage, RandomInterface rand, int noOfMonsters) {
    if (xLength <= 0 || yLength <= 0 || interconnectivityDegree < 0 || w == null
            || itemsPercentage < 0 || itemsPercentage > 100 || rand == null
            || noOfMonsters < 1) {
      throw new IllegalArgumentException("Invalid Dungeon Creation");
    }
    this.rows = xLength;
    this.columns = yLength;
    this.dungeonMaze = new Location[rows][columns];
    this.interconnectivityDegree = interconnectivityDegree;
    this.wrappingStyle = w;
    this.player = new Player();
    this.rand = rand;
    this.gameOver = false;
    this.itemsPercentage = itemsPercentage;
    this.playerLocationStatus = new ArrayList<>();
    this.caveIdList = new ArrayList<>();
    this.noOfMonsters = noOfMonsters;
    this.locationTrack = new ArrayList<>();
    createMaze();
  }

  private void addTreasure(double treasurePercentage) {
    List<Integer> caveId = new ArrayList<>(caveIdList);
    double d = treasurePercentage * 0.01 * caveId.size();
    int treasuredCaves;
    if (d % 1 == 0) {
      treasuredCaves = (int) d;
    } else {
      treasuredCaves = (int) d + 1;
    }
    while (treasuredCaves > 0) {
      int caveSelector = rand.getRandomNumber(0, caveId.size() - 1);
      Location cave = getLocation(caveId.get(caveSelector));
      if (cave == null) {
        break;
      }
      int r = rand.getRandomNumber(1, 3);
      int k = rand.getRandomNumber(1, 3);
      if (r == 1) {
        while (k > 0) {
          ((LocationC) cave).addTreasure(TreasureType.DIAMONDS);
          k--;
        }
        ((LocationC) cave).addTreasure(TreasureType.RUBIES);
      }
      if (r == 2) {
        while (k > 0) {
          ((LocationC) cave).addTreasure(TreasureType.RUBIES);
          k--;
        }
        ((LocationC) cave).addTreasure(TreasureType.SAPPHIRES);
      }
      if (r == 3) {
        while (k > 0) {
          ((LocationC) cave).addTreasure(TreasureType.SAPPHIRES);
          k--;
        }
        ((LocationC) cave).addTreasure(TreasureType.DIAMONDS);
      }
      treasuredCaves--;
      caveId.remove(Integer.valueOf(cave.getLocationId()));
    }
    addArrows();
  }

  private void addArrows() {
    double d = itemsPercentage * 0.01 * dungeonMaze.length * dungeonMaze[0].length;
    int arrowLocations;
    if (d % 1 == 0) {
      arrowLocations = (int) d;
    } else {
      arrowLocations = (int) d + 1;
    }
    List<Integer> a = new ArrayList<>();
    for (int i = 0; i < dungeonMaze.length * dungeonMaze[0].length; i++) {
      a.add(i);
    }
    while (arrowLocations > 0) {
      int r1 = rand.getRandomNumber(0, a.size() - 1);
      Location l = getLocation(a.get(r1));
      if (l == null) {
        break;
      }
      int r2 = rand.getRandomNumber(1, 3);
      ((LocationC) l).updateArrowsCount(r2);
      a.remove(Integer.valueOf(l.getLocationId()));
      arrowLocations--;
    }
    addMonsters(noOfMonsters);
  }

  protected void addMonsters(int noOfMonsters) {
    int monsterId = 1;
    List<Integer> caveId = new ArrayList<>(caveIdList);
    int monsters = noOfMonsters;
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
        ((LocationC) l).addOtyugh(new Otyugh(monsterId, l));
        monsterId++;
        monsters--;
      }
      caveId.remove(Integer.valueOf(l.getLocationId()));
    }
    ((LocationC) end).addOtyugh(new Otyugh(monsterId, end));
  }

  @Override
  public Stench stenchCalculation(Location location) {
    if (location == null) {
      throw new IllegalArgumentException("null location");
    }
    if (((LocationC) location).getOtyugh() != null) {
      return Stench.MOREPUNGENT;
    }
    Set<Integer> monsterSet = new HashSet<>();
    isMonsterNearBy(location, monsterSet);
    if (monsterSet.size() > 0) {
      return Stench.MOREPUNGENT;
    }
    if (location.getNeighbours().get(Direction.NORTH) != null) {
      isMonsterNearBy(location.getNeighbours().get(Direction.NORTH), monsterSet);
    }
    if (location.getNeighbours().get(Direction.SOUTH) != null) {
      isMonsterNearBy(location.getNeighbours().get(Direction.SOUTH), monsterSet);
    }
    if (location.getNeighbours().get(Direction.EAST) != null) {
      isMonsterNearBy(location.getNeighbours().get(Direction.EAST), monsterSet);
    }
    if (location.getNeighbours().get(Direction.WEST) != null) {
      isMonsterNearBy(location.getNeighbours().get(Direction.WEST), monsterSet);
    }
    if (monsterSet.size() == 1) {
      return Stench.LESSPUNGENT;
    }
    if (monsterSet.size() > 1) {
      return Stench.MOREPUNGENT;
    } else {
      return Stench.NOSTENCH;
    }
  }

  private void isMonsterNearBy(Location l, Set<Integer> m) {
    if (l == null || m == null) {
      throw new IllegalArgumentException("null values");
    }
    Location north = l.getNeighbours().get(Direction.NORTH);
    if (north != null && ((LocationC) north).getOtyugh() != null) {
      m.add(((LocationC) north).getOtyugh().getMonsterId());
    }
    Location south = l.getNeighbours().get(Direction.SOUTH);
    if (south != null && ((LocationC) south).getOtyugh() != null) {
      m.add(((LocationC) south).getOtyugh().getMonsterId());
    }
    Location east = l.getNeighbours().get(Direction.EAST);
    if (east != null && ((LocationC) east).getOtyugh() != null) {
      m.add(((LocationC) east).getOtyugh().getMonsterId());
    }
    Location west = l.getNeighbours().get(Direction.WEST);
    if (west != null && ((LocationC) west).getOtyugh() != null) {
      m.add(((LocationC) west).getOtyugh().getMonsterId());
    }
  }

  @Override
  public List<String> getPlayerLocationStatus() {
    return new ArrayList<>(this.playerLocationStatus);
  }

  @Override
  public List<String> getInitialGameStatus() {
    playerLocationStatus = new ArrayList<>();
    playerLocationStatus.add("Welcome to the Dungeon Game");
    locationStatus();
    return new ArrayList<>(playerLocationStatus);
  }

  protected void locationStatus() {
    Stench x = stenchCalculation(playerLocation);
    if (x == Stench.MOREPUNGENT) {
      playerLocationStatus.add("You smell a More Pungent smell");
    } else if (x == Stench.LESSPUNGENT) {
      playerLocationStatus.add("You smell a Less Pungent smell");
    }
    playerLocationStatus.add(String.format("You are in a %s",
            playerLocation.toString()));
    List<Direction> n = new ArrayList<>(playerLocation.getNeighbours().keySet());
    n.sort(Comparator.comparing(Enum::toString));
    playerLocationStatus.add(String.format("Doors lead to %s", n));
    playerLocationStatus.add(String.format("You find %s arrows here",
            playerLocation.getArrowCount()));
    playerLocationStatus.add(String.format("You find %s treasures here",
            playerLocation.getTreasureList()));
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
      if (((LocationC) playerLocation).getOtyugh() != null) {
        if (((LocationC) playerLocation).getOtyugh().getArrowHit() == 1) {
          int a = rand.getRandomNumber(1, 2);
          if (a == 1) {
            gameOver = true;
          } else {
            playerLocationStatus.add("You are Lucky, you escaped an injured Otyugh");
          }
        } else {
          gameOver = true;
        }
      }
      if (!gameOver) {
        if (playerLocation.getLocationId() == end.getLocationId()) {
          playerLocationStatus.add("Congo! You have reached the end, the game is over");
          gameOver = true;
        } else {
          locationStatus();
        }
      } else {
        playerLocationStatus.add("Chomp, chomp, chomp, you are eaten by an Otyugh!\n"
                + "Better luck next time");
        player.reduceHealth(100);
      }
    }
  }

  @Override
  public void pickUpTreasure() {
    if (playerLocation.getTreasureList().size() > 0 && !isGameOver()) {
      for (TreasureType t : playerLocation.getTreasureList()) {
        player.addTreasure(t);
        ((LocationC) playerLocation).removeTreasure(t);
      }
    } else {
      throw new IllegalStateException("CAN'T PICKUP, There are no requested items in the cave");
    }
  }

  @Override
  public void pickUpArrows() {
    if (playerLocation.getArrowCount() > 0 && !isGameOver()) {
      player.updateArrows(playerLocation.getArrowCount()
              + player.getArrows());
      ((LocationC) playerLocation).updateArrowsCount(0);
    } else {
      throw new IllegalStateException("CAN'T PICKUP, There are no requested items in the cave");
    }
  }

  @Override
  public void shoot(Direction d, int s) {
    if (!gameOver) {
      if (d == null) {
        throw new IllegalArgumentException("null direction");
      }
      if (s < 1) {
        throw new IllegalArgumentException("CAN'T SHOOT, Number of caves should be greater than 0");
      }
      if (s > 5) {
        throw new IllegalArgumentException("CAN'T SHOOT, you cannot shoot at distance"
                + " greater than 5");
      }
      if (player.getArrows() < 1) {
        throw new IllegalStateException("CAN'T SHOOT, You have no arrows to shoot");
      }
      playerLocationStatus = new ArrayList<>();
      Location arrowLocation = playerLocation;
      Map<Direction, Direction> inOut = new HashMap<>();
      inOut.put(Direction.NORTH, Direction.SOUTH);
      inOut.put(Direction.SOUTH, Direction.NORTH);
      inOut.put(Direction.EAST, Direction.WEST);
      inOut.put(Direction.WEST, Direction.EAST);
      if (playerLocation.getNeighbours().get(d) == null) {
        throw new IllegalArgumentException("CAN'T SHOOT, No path exists in the given direction");
      }
      player.updateArrows(player.getArrows() - 1);
      while (s > 0) {
        arrowLocation = arrowLocation.getNeighbours().get(d);
        if (!arrowLocation.getIsTunnel()) {
          s = s - 1;
          if (arrowLocation.getNeighbours().get(d) == null) {
            break;
          }
        } else {
          Map<Direction, Location> copy = new HashMap<>(arrowLocation.getNeighbours());
          copy.remove(inOut.get(d));
          for (Direction x : copy.keySet()) {
            d = x;
          }
        }
      }
      if (s == 0 && ((LocationC) arrowLocation).getOtyugh() != null) {
        ((LocationC) arrowLocation).getOtyugh().recordHit();
        if (((LocationC) arrowLocation).getOtyugh().getArrowHit() == 1) {
          playerLocationStatus.add("You hear a small howl in the distance");
        } else if (((LocationC) arrowLocation).getOtyugh().getArrowHit() == 2) {
          ((LocationC) arrowLocation).killOtyugh();
          playerLocationStatus.add("You hear a great howl in the distance");
        }
      } else {
        playerLocationStatus.add("You didn't hit any Otyugh");
      }
    }
  }

  private void beginGame() {
    List<List<Integer>> adj = new ArrayList<>();
    for (Location[] value : dungeonMaze) {
      for (Location location : value) {
        List<Integer> x = new ArrayList<>();
        if (location.getNeighbours().get(Direction.NORTH) != null) {
          x.add(location.getNeighbours().get(Direction.NORTH).getLocationId());
        }
        if (location.getNeighbours().get(Direction.SOUTH) != null) {
          x.add(location.getNeighbours().get(Direction.SOUTH).getLocationId());
        }
        if (location.getNeighbours().get(Direction.EAST) != null) {
          x.add(location.getNeighbours().get(Direction.EAST).getLocationId());
        }
        if (location.getNeighbours().get(Direction.WEST) != null) {
          x.add(location.getNeighbours().get(Direction.WEST).getLocationId());
        }
        adj.add(x);
      }
    }
    for (Location[] locations : dungeonMaze) {
      for (Location location : locations) {
        if (!location.getIsTunnel()) {
          caveIdList.add(location.getLocationId());
        }
      }
    }
    setStartEnd(adj);
  }

  private void setStartEnd(List<List<Integer>> adj) {
    if (adj == null) {
      throw new IllegalArgumentException("null list");
    }
    int source = caveIdList.get(rand.getRandomNumber(0, caveIdList.size() - 1));
    int[] shortestPathInfo = lengthOfPath(adj, source);
    List<Integer> validEndNodes = new ArrayList<>();
    for (int i = 0; i < shortestPathInfo.length; i++) {
      if (shortestPathInfo[i] >= 5 && caveIdList.contains(i)) {
        validEndNodes.add(i);
      }
    }
    if (validEndNodes.size() == 0) {
      throw new IllegalArgumentException("No path exists that have path length of minimum 5,"
              + " please try again");
    }
    int destination = rand.getRandomNumber(0, validEndNodes.size() - 1);
    this.start = getLocation(source);
    this.end = getLocation(validEndNodes.get(destination));
    this.playerLocation = start;
    locationTrack.add(playerLocation.getLocationId());
    this.thiefLocation = end;
    addTreasure(this.itemsPercentage);
  }

  @Override
  public Location getPlayerCurrentLocation() {
    return deepCopy(playerLocation);
  }

  private void createMaze() {
    int[] parent = new int[dungeonMaze.length * dungeonMaze[0].length];
    List<List<Integer>> edges = new ArrayList<>();
    int caveNumber = 0;
    for (int i = 0; i < dungeonMaze.length; i++) {
      for (int j = 0; j < dungeonMaze[i].length; j++) {
        dungeonMaze[i][j] = new LocationC(caveNumber);
        parent[caveNumber] = caveNumber;
        caveNumber = caveNumber + 1;
      }
    }
    for (int i = 0; i < dungeonMaze.length; i++) {
      for (int j = 0; j < dungeonMaze[i].length; j++) {
        if (j + 1 < dungeonMaze[i].length) {
          List<Integer> connection = new ArrayList<>();
          connection.add(dungeonMaze[i][j].getLocationId());
          connection.add(dungeonMaze[i][j + 1].getLocationId());
          edges.add(connection);
        }
        if (i + 1 < dungeonMaze.length) {
          List<Integer> connection = new ArrayList<>();
          connection.add(dungeonMaze[i][j].getLocationId());
          connection.add(dungeonMaze[i + 1][j].getLocationId());
          edges.add(connection);
        }
      }
    }
    if (wrappingStyle == WrappingStyle.WRAPPING) {
      for (int i = 0; i < dungeonMaze.length; i++) {
        for (int j = 0; j < dungeonMaze[i].length; j++) {
          if (j == 0) {
            List<Integer> connection = new ArrayList<>();
            connection.add(dungeonMaze[i][j].getLocationId());
            connection.add(dungeonMaze[i][j + dungeonMaze[0].length - 1].getLocationId());
            edges.add(connection);
          }
          if (i == 0) {
            List<Integer> connection = new ArrayList<>();
            connection.add(dungeonMaze[i][j].getLocationId());
            connection.add(dungeonMaze[i + dungeonMaze.length - 1][j].getLocationId());
            edges.add(connection);
          }
        }
      }
    }
    kruskal(edges, parent);
  }

  private void kruskal(List<List<Integer>> edges, int[] parent) {
    if (edges == null || parent == null) {
      throw new IllegalArgumentException("null inputs");
    }
    UnionFind u = new UnionFind(parent);
    List<List<Integer>> leftEdges = new ArrayList<>();
    while (edges.size() > 0) {
      int r = rand.getRandomNumber(0, edges.size() - 1);
      List<Integer> edge = edges.get(r);
      if (u.find(edge.get(0)) != u.find(edge.get(1))) {
        u.union(edge.get(0), edge.get(1));
        Location l1 = getLocation(edge.get(0));
        Location l2 = getLocation(edge.get(1));
        connectLocations(l1, l2);
      } else {
        leftEdges.add(edge);
      }
      edges.remove(r);
    }
    if (interconnectivityDegree > leftEdges.size()) {
      System.out.println("invalid inter");
      throw new IllegalArgumentException("Invalid Interconnectivity degree argument");
    }
    if (interconnectivityDegree > 0) {
      int x = interconnectivityDegree;
      while (x > 0 && leftEdges.size() > 0) {
        List<Integer> ed = leftEdges.get(0);
        Location l1 = getLocation(ed.get(0));
        Location l2 = getLocation(ed.get(1));
        connectLocations(l1, l2);
        leftEdges.remove(0);
        x = x - 1;
      }
    }
    this.beginGame();
  }

  private void connectLocations(Location l1, Location l2) {
    if (l1 == null || l2 == null) {
      throw new IllegalArgumentException("null locations");
    }
    int[] l1xy = getCoordinates(l1);
    int[] l2xy = getCoordinates(l2);
    if (l1xy[0] == l2xy[0] - 1) {
      ((LocationC) l1).assignNeighbours(Direction.SOUTH, l2);
      ((LocationC) l2).assignNeighbours(Direction.NORTH, l1);
    }
    if (l1xy[1] == l2xy[1] - 1) {
      ((LocationC) l1).assignNeighbours(Direction.EAST, l2);
      ((LocationC) l2).assignNeighbours(Direction.WEST, l1);
    }
    if (l1xy[1] == l2xy[1] - dungeonMaze[0].length + 1) {
      ((LocationC) l1).assignNeighbours(Direction.WEST, l2);
      ((LocationC) l2).assignNeighbours(Direction.EAST, l1);
    }
    if (l1xy[0] == l2xy[0] - dungeonMaze.length + 1) {
      ((LocationC) l1).assignNeighbours(Direction.NORTH, l2);
      ((LocationC) l2).assignNeighbours(Direction.SOUTH, l1);
    }
  }

  protected Location getLocation(int locationId) {
    for (Location[] locations : dungeonMaze) {
      for (Location location : locations) {
        if (location.getLocationId() == locationId) {
          return location;
        }
      }
    }
    return null;
  }

  private int[] lengthOfPath(List<List<Integer>> adj, int start) {
    if (adj == null) {
      throw new IllegalArgumentException("null adj");
    }
    int totalNodes = dungeonMaze.length * dungeonMaze[0].length;
    int[] dis = new int[totalNodes];
    int[] prev = new int[totalNodes];
    Arrays.fill(dis, -1);
    dis[start] = 0;
    prev[start] = start;
    Queue<Integer> q = new LinkedList<>();
    q.add(start);
    while (!q.isEmpty()) {
      int currentNode = q.poll();
      List<Integer> children = adj.get(currentNode);
      for (Integer child : children) {
        if (dis[child] == -1) {
          dis[child] = dis[currentNode] + 1;
          prev[child] = currentNode;
          q.add(child);
        }
      }
    }
    return dis;
  }

  @Override
  public String toString() {
    StringBuilder maze = new StringBuilder();
    for (Location[] locations : dungeonMaze) {
      for (int r = 0; r < 3; r++) {
        for (Location l : locations) {
          if (r == 0) {
            maze.append((l.getNeighbours().get(Direction.NORTH) == null) ? "   " : " | ");
          }
          if (r == 1) {
            maze.append((l.getNeighbours().get(Direction.WEST) == null) ? " " : "-");
            if (l.getIsTunnel()) {
              maze.append("T");
            } else {
              if (l.hasPit()) {
                maze.append("P");
              } else if (((LocationC) l).getOtyugh() != null
                      && l.getLocationId() == end.getLocationId()) {
                maze.append("M");
              } else if (((LocationC) l).getOtyugh() != null) {
                maze.append("m");
              } else {
                maze.append("C");
              }
            }
            maze.append((l.getNeighbours().get(Direction.EAST) == null) ? " " : "-");
          }
          if (r == 2) {
            maze.append((l.getNeighbours().get(Direction.SOUTH) == null) ? "   " : " | ");
          }
        }
        maze.append("\n");
      }
    }
    return maze.toString();
  }

  private int[] getCoordinates(Location l) {
    if (l == null) {
      throw new IllegalArgumentException("null location");
    }
    int[] r = new int[2];
    for (int i = 0; i < dungeonMaze.length; i++) {
      for (int j = 0; j < dungeonMaze[i].length; j++) {
        if (l.getLocationId() == dungeonMaze[i][j].getLocationId()) {
          r[0] = i;
          r[1] = j;
        }
      }
    }
    return r;
  }

  @Override
  public double getItemsPercentage() {
    return this.itemsPercentage;
  }

  @Override
  public Location getStartLocation() {
    return deepCopy(start);
  }

  private Location deepCopy(Location l) {
    Location copy = new LocationC(l.getLocationId());
    for (Map.Entry<Direction, Location> entry : l.getNeighbours().entrySet()) {
      ((LocationC) copy).assignNeighbours(entry.getKey(), entry.getValue());
    }
    for (TreasureType x : l.getTreasureList()) {
      ((LocationC) copy).addTreasure(x);
    }
    ((LocationC) copy).addOtyugh(((LocationC) l).getOtyugh());
    ((LocationC) copy).updateArrowsCount(l.getArrowCount());
    return copy;
  }

  @Override
  public Location getEndLocation() {
    return deepCopy(end);
  }

  @Override
  public List<String> getPlayerDescription() {
    return new ArrayList<>(player.getPlayerDescription());
  }

  @Override
  public boolean isGameOver() {
    return gameOver;
  }

  @Override
  public boolean isPlayerAlive() {
    return player.isAlive();
  }

  @Override
  public int getRows() {
    return rows;
  }

  @Override
  public int getColumns() {
    return columns;
  }

  @Override
  public int getInterconnectivityDegree() {
    return interconnectivityDegree;
  }

  @Override
  public boolean isWrapping() {
    return wrappingStyle == WrappingStyle.WRAPPING;
  }

  @Override
  public int getDifficulty() {
    return noOfMonsters;
  }

  @Override
  public Location[][] get2dDungeon() {
    Location[][] copy = new Location[dungeonMaze.length][dungeonMaze[0].length];
    for (int i = 0; i < dungeonMaze.length; i++) {
      System.arraycopy(dungeonMaze[i], 0, copy[i], 0, dungeonMaze[0].length);
    }
    return copy;
  }

  @Override
  public List<Integer> getVisited() {
    return locationTrack;
  }

  @Override
  public List<TreasureType> getPlayerTreasure() {
    return player.getTreasureCollected();
  }

  @Override
  public int getPlayerHealth() {
    return player.getHealth();
  }

  @Override
  public void moveToLocation(int l) {
    if (playerLocation.getNeighbours().containsKey(Direction.NORTH)
            && playerLocation.getNeighbours().get(Direction.NORTH).getLocationId() == l) {
      movePlayerTo(Direction.NORTH);
    } else if (playerLocation.getNeighbours().containsKey(Direction.EAST)
            && playerLocation.getNeighbours().get(Direction.EAST).getLocationId() == l) {
      movePlayerTo(Direction.EAST);
    }
    if (playerLocation.getNeighbours().containsKey(Direction.SOUTH)
            && playerLocation.getNeighbours().get(Direction.SOUTH).getLocationId() == l) {
      movePlayerTo(Direction.SOUTH);
    }
    if (playerLocation.getNeighbours().containsKey(Direction.WEST)
            && playerLocation.getNeighbours().get(Direction.WEST).getLocationId() == l) {
      movePlayerTo(Direction.WEST);
    }
  }

}
