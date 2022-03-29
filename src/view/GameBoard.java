package view;

import controller.GuiFeatures;
import model.Direction;
import model.Location;
import model.ReadOnlyDungeonObstacle;
import model.Stench;
import model.TreasureType;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;

/**
 * Dungeon Game board that displays the GUI where player plays the game.
 */
class GameBoard extends JPanel {

  private final ReadOnlyDungeonObstacle model;
  private final GuiFeatures guiFeatures;
  private boolean shootCalled;
  private JMenuItem home;
  private JMenuItem restart;
  private JMenuItem reuse;
  private JMenuItem exit;

  /**
   * GameBoard Constructor that takes in the ReadOnly model and the controller.
   *
   * @param model Dungeon model
   * @param f     controller
   */
  GameBoard(ReadOnlyDungeonObstacle model, GuiFeatures f) {
    this.model = model;
    this.guiFeatures = f;
    shootCalled = false;
    prepareGameBoard();
  }

  void prepareGameBoard() {
    setLayout(new BorderLayout(0, 0));
    JMenuBar menuBar = new JMenuBar();
    menuBar.setSize(new Dimension(100, 200));
    JMenu menu = new JMenu("Dungeon Information");
    JMenuItem m1 = new JMenuItem("Rows - " + model.getRows());
    JMenuItem m2 = new JMenuItem("Columns - " + model.getColumns());

    JMenuItem m4;
    if (model.isWrapping()) {
      m4 = new JMenuItem("Wrapping Dungeon");
    } else {
      m4 = new JMenuItem("Non Wrapping Dungeon");
    }
    JMenuItem m5 = new JMenuItem("Otyughs - " + model.getDifficulty());
    JMenuItem m6 = new JMenuItem("Items Percentage - " + model.getItemsPercentage());
    JMenuItem m7 = new JMenuItem("One moving Monster");
    JMenuItem m8 = new JMenuItem("One moving Thief");
    JMenuItem m9 = new JMenuItem("Pits - " + (model.getDifficulty() - 1));
    menu.add(m1);
    menu.add(m2);
    JMenuItem m3 = new JMenuItem("Interconnectivity - " + model.getInterconnectivityDegree());
    menu.add(m3);
    menu.add(m4);
    menu.add(m5);
    menu.add(m6);
    menu.add(m7);
    menu.add(m8);
    menu.add(m9);
    menuBar.add(menu);
    menu.setFont(getFont().deriveFont(20f));
    JPanel j2 = new JPanel();
    j2.add(menuBar);


    home = new JMenuItem("Home");
    restart = new JMenuItem("Restart");
    exit = new JMenuItem("Quit");
    reuse = new JMenuItem("Reuse");
    JMenu menu2 = new JMenu("Game Controls");
    menu2.add(home);
    menu2.add(restart);
    menu2.add(reuse);
    menu2.add(exit);
    JMenuBar menuBar2 = new JMenuBar();
    menuBar2.add(menu2);
    menu2.setFont(getFont().deriveFont(20f));
    j2.add(menuBar2);
    j2.setBackground(Color.WHITE);
    this.add(j2, BorderLayout.NORTH);



    //Player Description and Location
    //Player Description
    JPanel pd3 = new JPanel();
    pd3.setLayout(null);
    pd3.setBackground(Color.WHITE);
    pd3.setPreferredSize(new Dimension(300, 140));
    BufferedImage player = null;
    try {
      player = ImageIO.read(ClassLoader.getSystemResource("Images/raki.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    Image player2 = player.getScaledInstance(190, 190, Image.SCALE_DEFAULT);
    JLabel pl5 = new JLabel(new ImageIcon(player2));
    pl5.setSize(230, 230);
    pl5.setLocation(-30, -30);
    pd3.add(pl5);
    BufferedImage health = null;
    try {
      health = ImageIO.read(ClassLoader.getSystemResource("Images/heart.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    Image health2 = health.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
    JLabel pH = new JLabel(new ImageIcon(health2));
    pH.setSize(50, 50);
    pH.setLocation(20, 130);
    pd3.add(pH);
    JLabel lH = new JLabel();
    lH.setFont(getFont().deriveFont(10f));
    lH.setForeground(Color.RED);
    lH.setFont(getFont().deriveFont(20f));
    lH.setSize(40, 40);
    lH.setLocation(70, 130);
    pd3.add(lH);
    BufferedImage arrow = null;
    try {
      arrow = ImageIO.read(ClassLoader.getSystemResource("Images/arrow-black.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    JLabel pl7 = new JLabel(new ImageIcon(arrow));
    pl7.setSize(100, 30);
    pl7.setLocation(110, 30);
    pd3.add(pl7);
    JLabel pl6 = new JLabel();
    pl6.setFont(getFont().deriveFont(20f));
    pl6.setSize(100, 30);
    pl6.setLocation(220, 30);
    pd3.add(pl6);
    BufferedImage ruby = null;
    try {
      ruby = ImageIO.read(ClassLoader.getSystemResource("Images/ruby.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    JLabel pl8 = new JLabel(new ImageIcon(ruby));
    pl8.setSize(100, 30);
    pl8.setLocation(110, 70);
    pd3.add(pl8);
    JLabel pl9 = new JLabel();
    pl9.setFont(getFont().deriveFont(20f));
    pl9.setSize(100, 30);
    pl9.setLocation(220, 70);
    pd3.add(pl9);
    BufferedImage sapphire = null;
    try {
      sapphire = ImageIO.read(ClassLoader.getSystemResource("Images/s2.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    JLabel pl10 = new JLabel(new ImageIcon(sapphire));
    pl10.setSize(100, 30);
    pl10.setLocation(110, 110);
    pd3.add(pl10);
    JLabel pl11 = new JLabel();

    pl11.setFont(getFont().deriveFont(20f));
    pl11.setSize(100, 30);
    pl11.setLocation(220, 110);
    pd3.add(pl11);
    BufferedImage diamonds = null;
    try {
      diamonds = ImageIO.read(ClassLoader.getSystemResource("Images/diamond.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    JLabel pl12 = new JLabel(new ImageIcon(diamonds));
    pl12.setSize(100, 30);
    pl12.setLocation(110, 150);
    pd3.add(pl12);
    JLabel pl13 = new JLabel();
    pl13.setFont(getFont().deriveFont(20f));
    pl13.setSize(100, 30);
    pl13.setLocation(220, 150);
    pd3.add(pl13);
    JLabel g1 = new JLabel();
    g1.setForeground(Color.RED);
    JLabel g2 = new JLabel();
    g2.setForeground(Color.RED);
    g1.setFont(getFont().deriveFont(18f));
    g2.setFont(getFont().deriveFont(18f));
    g1.setSize(200, 50);
    g1.setLocation(80, 180);
    g2.setSize(200, 50);
    g2.setLocation(60, 210);
    pd3.add(g1);
    pd3.add(g2);


    // Attack description
    JPanel pd5 = new JPanel();
    pd5.setLayout(null);
    JLabel attack = new JLabel("Attack Status");
    attack.setFont(getFont().deriveFont(20f));
    attack.setSize(200, 30);
    attack.setLocation(70, -7);
    pd5.add(attack);

    BufferedImage singleHit = null;
    BufferedImage doubleHit = null;
    BufferedImage noHit = null;
    BufferedImage wrong = null;
    BufferedImage mm = null;
    try {
      noHit = ImageIO.read(ClassLoader.getSystemResource("Images/otyugh.png"));
      wrong = ImageIO.read(ClassLoader.getSystemResource("Images/no3.png"));
      mm = ImageIO.read(ClassLoader.getSystemResource("Images/mm.png"));
      singleHit = overlay(noHit, ClassLoader.getSystemResource("Images/arrow-black.png"),
              2, 13, 35, 9);
      doubleHit = overlay(singleHit, ClassLoader.getSystemResource("Images/arrow-black.png"),
              2, 24, 35, 9);
    } catch (IOException e) {
      e.printStackTrace();
    }
    Image noHit2 = noHit.getScaledInstance(60, 60, Image.SCALE_DEFAULT);
    JLabel a1 = new JLabel(new ImageIcon(noHit2));
    a1.setSize(100, 100);
    a1.setLocation(20, 5);
    pd5.add(a1);

    Image mm2 = mm.getScaledInstance(85, 85, Image.SCALE_DEFAULT);
    JLabel f1 = new JLabel(new ImageIcon(mm2));
    f1.setSize(100, 100);
    f1.setLocation(160, 5);
    pd5.add(f1);

    final JLabel[] a2 = {new JLabel()};
    a2[0].setSize(100, 200);
    a2[0].setLocation(35, 20);


    Image health3 = health2.getScaledInstance(40, 40, Image.SCALE_DEFAULT);
    JLabel mHealth = new JLabel(new ImageIcon(health3));
    mHealth.setSize(50, 50);
    mHealth.setLocation(170, 95);
    pd5.add(mHealth);

    JLabel healthBar = new JLabel();
    healthBar.setFont(getFont().deriveFont(19f));
    healthBar.setForeground(Color.red);
    healthBar.setSize(100, 100);
    healthBar.setLocation(220, 70);
    pd5.add(healthBar);

    //Player Location status
    JPanel pd4 = new JPanel();
    pd4.setLayout(null);
    pd5.setPreferredSize(new Dimension(300, 200));
    JLabel ps5 = new JLabel("Player Location Status");
    ps5.setFont(getFont().deriveFont(20f));
    ps5.setSize(350, 30);
    ps5.setLocation(50, -7);
    pd4.add(ps5);
    BufferedImage arrow2 = null;
    try {
      arrow2 = ImageIO.read(ClassLoader.getSystemResource("Images/arrow-black.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    JLabel ps6 = new JLabel(new ImageIcon(arrow2));
    ps6.setSize(100, 30);
    ps6.setLocation(20, 30);
    pd4.add(ps6);
    JLabel ps7 = new JLabel();
    ps7.setFont(getFont().deriveFont(17f));
    ps7.setSize(100, 30);
    ps7.setLocation(150, 30);
    pd4.add(ps7);

    //stench
    BufferedImage st = null;
    BufferedImage st2 = null;
    try {
      st = ImageIO.read(ClassLoader.getSystemResource("Images/stench01.png"));
      st2 = ImageIO.read(ClassLoader.getSystemResource("Images/stench02.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }

    final JLabel[] lp = {new JLabel()};
    lp[0].setSize(130, 130);
    lp[0].setLocation(200, 10);

    BufferedImage mud = null;
    try {
      mud = ImageIO.read(ClassLoader.getSystemResource("Images/caution2.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    final JLabel[] mudL = {new JLabel()};
    mudL[0].setSize(60, 60);
    mudL[0].setLocation(190, 105);

    BufferedImage ruby2 = null;
    try {
      ruby2 = ImageIO.read(ClassLoader.getSystemResource("Images/ruby.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    JLabel ps8 = new JLabel(new ImageIcon(ruby2));
    ps8.setSize(100, 30);
    ps8.setLocation(20, 65);
    pd4.add(ps8);
    JLabel ps9 = new JLabel();

    ps9.setFont(getFont().deriveFont(20f));
    ps9.setSize(100, 30);
    ps9.setLocation(150, 65);
    pd4.add(ps9);

    BufferedImage sapphire2 = null;
    try {
      sapphire2 = ImageIO.read(ClassLoader.getSystemResource("Images/s2.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    JLabel ps10 = new JLabel(new ImageIcon(sapphire2));
    ps10.setSize(100, 30);
    ps10.setLocation(20, 100);
    pd4.add(ps10);
    JLabel ps11 = new JLabel();

    ps11.setFont(getFont().deriveFont(20f));
    ps11.setSize(100, 30);
    ps11.setLocation(150, 100);
    pd4.add(ps11);

    BufferedImage diamonds2 = null;
    try {
      diamonds2 = ImageIO.read(ClassLoader.getSystemResource("Images/diamond.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    JLabel ps12 = new JLabel(new ImageIcon(diamonds2));
    ps12.setSize(100, 30);
    ps12.setLocation(20, 135);
    pd4.add(ps12);
    JLabel ps13 = new JLabel();

    ps13.setFont(getFont().deriveFont(20f));
    ps13.setSize(100, 30);
    ps13.setLocation(150, 135);
    pd4.add(ps13);

    JLabel ps14 = new JLabel("Exits - ");
    ps14.setForeground(Color.RED);
    ps14.setFont(getFont().deriveFont(15f));
    ps14.setSize(300, 30);
    ps14.setLocation(15, 175);
    pd4.add(ps14);

    JLabel ps15 = new JLabel();
    ps15.setFont(getFont().deriveFont(15f));
    final StringBuilder[] s = {new StringBuilder()};
    ps15.setForeground(Color.blue);
    ps15.setSize(300, 30);
    ps15.setLocation(70, 175);
    pd4.add(ps15);


    BufferedImage finalWrong = wrong;
    BufferedImage finalSingleHit = singleHit;
    BufferedImage finalDoubleHit = doubleHit;
    Image stSmall = st.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
    Image st2Small = st2.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
    Image mud2 = mud.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
    JPanel pd2 = new JPanel() {
      @Override
      public void paintComponent(Graphics a) {
        lH.setText("" + model.getPlayerHealth());
        pl6.setText(model.getPlayerDescription().get(1).substring(8));
        Map<TreasureType, Integer> pt = getCombinedTreasure(model.getPlayerTreasure());
        pl9.setText("" + pt.get(TreasureType.RUBIES));
        pl11.setText("" + pt.get(TreasureType.SAPPHIRES));
        pl13.setText("" + pt.get(TreasureType.DIAMONDS));
        if (model.isGameOver()) {
          if (model.isPlayerAlive()) {
            g1.setText("You Won!");
            g2.setText("Game Over");
          } else {
            g1.setText("You are Dead!");
            g2.setText("Game Over");
            g1.setLocation(40, 180);
          }
        }
        if (model.getPlayerLocationStatus().size() > 0
                && model.getPlayerLocationStatus().get(0).contains("small howl")) {
          ImageIcon icon = new ImageIcon(finalSingleHit);
          a2[0].setIcon(icon);
        } else if (model.getPlayerLocationStatus().size() > 0
                && model.getPlayerLocationStatus().get(0).contains("great howl")) {
          ImageIcon icon = new ImageIcon(finalDoubleHit);
          a2[0].setIcon(icon);
        } else if (model.getPlayerLocationStatus().size() > 0
                && model.getPlayerLocationStatus().get(0).contains("didn't hit")) {
          ImageIcon icon = new ImageIcon(finalWrong);
          a2[0].setIcon(icon);
        } else {
          a2[0].setIcon(null);
        }
        ps7.setText("" + model.getPlayerCurrentLocation().getArrowCount());
        if (model.stenchCalculation(model.getPlayerCurrentLocation()) == Stench.MOREPUNGENT) {
          ImageIcon icon = new ImageIcon(st2Small);
          lp[0].setIcon(icon);
        } else if (model.stenchCalculation(model.getPlayerCurrentLocation())
                == Stench.LESSPUNGENT) {
          ImageIcon icon = new ImageIcon(stSmall);
          lp[0].setIcon(icon);
        } else {
          lp[0].setIcon(null);
        }
        if (model.isPitNearBy()) {
          ImageIcon icon = new ImageIcon(mud2);
          mudL[0].setIcon(icon);
        } else {
          mudL[0].setIcon(null);
        }
        Map<TreasureType, Integer> pt2 = getCombinedTreasure(model
                .getPlayerCurrentLocation().getTreasureList());
        ps9.setText("" + pt2.get(TreasureType.RUBIES));
        ps11.setText("" + pt2.get(TreasureType.SAPPHIRES));
        ps13.setText("" + pt2.get(TreasureType.DIAMONDS));
        s[0] = new StringBuilder("");
        for (Direction d : model.getPlayerCurrentLocation().getNeighbours().keySet()) {
          s[0].append(d.toString()).append(" ");
        }
        ps15.setText(s[0].toString());
        healthBar.setText("" + model.getMovingMonsterHealth());
      }
    };
    pd2.setLayout(new BoxLayout(pd2, BoxLayout.Y_AXIS));
    pd5.setBackground(Color.WHITE);
    pd4.setBackground(Color.WHITE);
    pd2.add(pd3);
    pd2.add(pd5);
    pd2.add(pd4);
    pd5.add(a2[0]);
    pd4.add(lp[0]);
    pd4.add(mudL[0]);
    pd4.setPreferredSize(new Dimension(300, 150));
    pd5.setPreferredSize(new Dimension(300, 50));
    this.add(pd2, BorderLayout.WEST);

    //Dungeon
    JPanel jp = new JPanel();
    jp.setLayout(new BoxLayout(jp, BoxLayout.PAGE_AXIS));
    JPanel jpp = new JPanel();
    jpp.setBackground(Color.black);
    jpp.add(jp);
    JPanel j1 = new JPanel();
    jp.add(j1);
    jp.setPreferredSize(new Dimension(model.getColumns() * 120,
            model.getRows() * 120));
    JScrollPane scroll = new JScrollPane(jpp);
    scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    scroll.getVerticalScrollBar().setUnitIncrement(10);
    scroll.getHorizontalScrollBar().setUnitIncrement(10);
    this.add(scroll, BorderLayout.CENTER);
    j1.setLayout(new GridLayout(model.getRows(), model.getColumns(), 0, 0));
    j1.setBackground(Color.BLACK);

    Location[][] maze = model.get2dDungeon();
    for (int i = 0; i < model.getRows(); i++) {
      for (int j = 0; j < model.getColumns(); j++) {
        Location x = maze[i][j];
        int xOff = 0;
        int yOff = 0;
        if (x.getIsTunnel() && x.getNeighbours().containsKey(Direction.NORTH)
                && x.getNeighbours().containsKey(Direction.WEST)) {
          xOff = -10;
          yOff = -9;
        }
        if (x.getIsTunnel() && x.getNeighbours().containsKey(Direction.SOUTH)
                && x.getNeighbours().containsKey(Direction.WEST)) {
          xOff = -10;
          yOff = -7;
        }
        int finalXOff = xOff;
        int finalYOff = yOff;
        JPanel p = new JPanel() {
          @Override
          public void paintComponent(Graphics gOld) {
            super.paintComponent(gOld);
            Graphics2D g = (Graphics2D) gOld;
            List<String> f = new ArrayList<>();
            for (Direction d : x.getNeighbours().keySet()) {
              f.add(d.toString().substring(0, 1));
            }
            Collections.sort(f);
            StringBuilder s = new StringBuilder();
            for (String str : f) {
              s.append(str);
            }
            s.append(".png");
            try {
              BufferedImage blank = ImageIO.read(
                      ClassLoader.getSystemResource("Images/blank.png"));
              BufferedImage finalImage = ImageIO.read(
                      ClassLoader.getSystemResource("Images/" + s.toString()));
              if (x.hasPit()) {
                finalImage = overlay(finalImage,
                        ClassLoader.getSystemResource("Images/pit4.png"),
                        23, 22, 20, 20);
              }
              if (x.getLocationId() == model.getThiefLocationId()) {
                finalImage = overlay(finalImage,
                        ClassLoader.getSystemResource("Images/thief.png"),
                        21, 21, 30, 30);
              }
              if (x.getLocationId() == model.getMovingMonsterLocationId()
                      && model.isMovingMonsterAlive()) {
                finalImage = overlay(finalImage,
                        ClassLoader.getSystemResource("Images/mm.png"),
                        21, 21, 30, 30);
              }
              if (x.hasOtyugh()) {
                finalImage = overlay(finalImage,
                        ClassLoader.getSystemResource("Images/otyugh.png"),
                        10, 10, 22, 22);
              }
              if (model.stenchCalculation(x) == Stench.MOREPUNGENT) {
                finalImage = overlay(finalImage, ClassLoader.getSystemResource(
                        "Images/stench02.png"), 18, 18, 40, 40);
              }
              if (model.stenchCalculation(x) == Stench.LESSPUNGENT) {
                finalImage = overlay(finalImage, ClassLoader.getSystemResource(
                        "Images/stench01.png"), 18, 18, 40, 40);
              }
              if (model.getPlayerCurrentLocation().getLocationId() == x.getLocationId()) {
                finalImage = overlay(finalImage, ClassLoader.getSystemResource(
                                "Images/p2.png"), 19 + finalXOff,
                        19 + finalYOff, 35, 35);
              }
              if (x.getTreasureList().size() > 0) {
                if (x.getTreasureList().contains(TreasureType.RUBIES)) {
                  finalImage = overlay(finalImage, ClassLoader.getSystemResource(
                          "Images/ruby.png"), 40, 6, 12, 12);
                }
                if (x.getTreasureList().contains(TreasureType.DIAMONDS)) {
                  finalImage = overlay(finalImage, ClassLoader.getSystemResource(
                          "Images/diamond.png"), 35, 10, 12, 12);
                }
                if (x.getTreasureList().contains(TreasureType.SAPPHIRES)) {
                  finalImage = overlay(finalImage, ClassLoader.getSystemResource(
                          "Images/s2.png"), 45, 10, 12, 12);
                }
              }
              if (x.getArrowCount() > 0) {
                if (x.getIsTunnel() && x.getNeighbours().containsKey(Direction.NORTH)
                        && x.getNeighbours().containsKey(Direction.WEST)) {
                  finalImage = overlay(finalImage, ClassLoader.getSystemResource(
                                  "Images/arrow-white.png"), 26, 28,
                          20, 7);
                } else {
                  finalImage = overlay(finalImage, ClassLoader.getSystemResource(
                                  "Images/arrow-white.png"), 38, 35,
                          20, 7);
                }
              }
              if (!model.getVisited().contains(x.getLocationId())) {
                g.drawImage(blank, 0, 0, 120, 120, null);
              } else {
                g.drawImage(finalImage, 0, 0, 120, 120, null);
              }
            } catch (IOException e) {
              e.printStackTrace();
            }
          }
        };
        p.setName("" + x.getLocationId());
        MouseAdapter clickAdapter = new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            int l = Integer.parseInt(e.getComponent().getName());
            guiFeatures.movePlayerTo(l);
          }
        };
        p.addMouseListener(clickAdapter);
        j1.add(p);
      }
    }
  }

  private BufferedImage overlay(BufferedImage starting, URL fpath, int offsetX,
                                int offsetY, int width, int height) throws IOException {
    BufferedImage overlay = ImageIO.read(fpath);
    Image overlayScaled = overlay.getScaledInstance(width, height, Image.SCALE_DEFAULT);
    int w = Math.max(starting.getWidth(), overlay.getWidth());
    int h = Math.max(starting.getHeight(), overlay.getHeight());
    BufferedImage combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
    Graphics g = combined.getGraphics();
    g.drawImage(starting, 0, 0, null);
    g.drawImage(overlayScaled, offsetX, offsetY, null);
    return combined;
  }

  void setFeaturesInGame(GuiFeatures f) {
    exit.addActionListener(l -> f.exitProgram());
    home.addActionListener(l -> f.goToHome());
    reuse.addActionListener(l -> f.reuse());
    restart.addActionListener(l -> f.restart());
    getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP,
            0, false), "UP");
    getActionMap().put("UP", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (shootCalled) {
          int caves = 0;
          String c = JOptionPane.showInputDialog((Component) e.getSource(),
                  "Enter the number of Caves");
          try {
            caves = Integer.parseInt(c);
          } catch (NumberFormatException xl) {
            JOptionPane.showMessageDialog((Component) e.getSource(),
                    "Invalid number of caves");
          }
          f.shoot(Direction.NORTH, caves);
          shootCalled = false;
        } else {
          f.move(Direction.NORTH);
        }
      }
    });
    getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN,
            0, false), "DOWN");
    getActionMap().put("DOWN", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (shootCalled) {
          int caves = 0;
          String c = JOptionPane.showInputDialog((Component) e.getSource(),
                  "Enter the number of Caves");
          try {
            caves = Integer.parseInt(c);
          } catch (NumberFormatException xl) {
            JOptionPane.showMessageDialog((Component) e.getSource(),
                    "Invalid number of caves");
          }
          f.shoot(Direction.SOUTH, caves);
          shootCalled = false;
        } else {
          f.move(Direction.SOUTH);
        }
      }
    });
    getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT,
            0, false), "LEFT");
    getActionMap().put("LEFT", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (shootCalled) {
          int caves = 0;
          String c = JOptionPane.showInputDialog((Component) e.getSource(),
                  "Enter the number of Caves");
          try {
            caves = Integer.parseInt(c);
          } catch (NumberFormatException xl) {
            JOptionPane.showMessageDialog((Component) e.getSource(),
                    "Invalid number of caves");
          }
          f.shoot(Direction.WEST, caves);
          shootCalled = false;
        } else {
          f.move(Direction.WEST);
        }
      }
    });
    getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT,
            0, false), "RIGHT");
    getActionMap().put("RIGHT", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (shootCalled) {
          int caves = 0;
          String c = JOptionPane.showInputDialog((Component) e.getSource(),
                  "Enter the number of Caves");
          try {
            caves = Integer.parseInt(c);
          } catch (NumberFormatException xl) {
            JOptionPane.showMessageDialog((Component) e.getSource(),
                    "Invalid number of caves");
          }
          f.shoot(Direction.EAST, caves);
          shootCalled = false;
        } else {
          f.move(Direction.EAST);
        }
      }
    });
    getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_T,
            0, false), "T");
    getActionMap().put("T", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        f.pickUpTreasure();
      }
    });
    getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_A,
            0, false), "A");
    getActionMap().put("A", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        f.pickUpArrows();
      }
    });
    getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_S,
            0, false), "S");
    getActionMap().put("S", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        shootCalled = true;
      }
    });
    getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F,
            0, false), "F");
    getActionMap().put("F", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        f.fightMonster();
      }
    });
  }

  private Map<TreasureType, Integer> getCombinedTreasure(List<TreasureType> x) {
    Map<TreasureType, Integer> total = new HashMap<>();
    int d = 0;
    int r = 0;
    int s = 0;
    for (TreasureType tt : x) {
      if (tt == TreasureType.DIAMONDS) {
        d = d + 1;
      }
      if (tt == TreasureType.RUBIES) {
        r = r + 1;
      }
      if (tt == TreasureType.SAPPHIRES) {
        s = s + 1;
      }
    }
    total.put(TreasureType.DIAMONDS, d);
    total.put(TreasureType.RUBIES, r);
    total.put(TreasureType.SAPPHIRES, s);
    return new TreeMap<>(total);
  }
}
