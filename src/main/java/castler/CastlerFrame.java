package castler;

import castler.Buttons.*;
import castler.Sprites.Structures.Barrack;
import castler.Sprites.Structures.Tower;
import castler.Sprites.Units.Fighter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.basic.BasicArrowButton;

public class CastlerFrame extends JFrame {

    private String p1name;
    private String p2name;
    private JPanel mainPanel;
    private SellButton sellButton;
    private TowerUpgradeButton towerUpgradeButton;
    private UpgradeButton upgradeButton;
    private EndTurnButton endTurn;
    private TowerButton towerButton;
    private FighterButton soldierButton;
    private DemolisherButton demolisherButton;
    private MountaineerButton mountaineerButton;
    private BarrackButton barrackButton;
    private AreaTowerButton areaTowerButton;
    private LineTowerButton lineTowerButton;
    private ExploreTowerButton exploreTowerButton;
    private WayPointButton waypointButton;
    private DirectionButton directionLeftButton;
    private DirectionButton directionRightButton;
    private DirectionButton directionUpButton;
    private DirectionButton directionDownButton;
    private JProgressBar redHealth;
    private JProgressBar blueHealth;
    private JLabel bluePlayerLabel;
    private JLabel redPlayerLabel;
    private JLabel blueGoldLabel;
    private JLabel redGoldLabel;
    private JLabel blueTowers;
    private JLabel redTowers;
    private JLabel blueTowerscnt;
    private JLabel redTowerscnt;
    private JLabel roundLabel;
    private JLabel errorLabel;
    private JPanel infoPanel;
    private GamePanel gamePanel;
    private JPanel actionPanel;
    private Menu menu;
    private Timer timer;
    private Boolean fighterVisible = false;
    private Boolean towerVisible = false;
    private Boolean demolisherVisible = false;
    private Boolean mountaineerVisible = false;
    private Boolean barrackVisible = false;
    private Boolean AreaTowerVisible = false;
    private Boolean LineTowerVisible = false;
    private Boolean ExploreTowerVisible = false;
    private Boolean upgradeVisible = false;
    private Boolean upgradeTowerVisible = false;
    private Boolean directionLeftVisible = false;
    private Boolean directionRightVisible = false;
    private Boolean directionUpVisible = false;
    private Boolean directionDownVisible = false;
    private Boolean sellbuttonVisible = false;
    private Boolean WaypointbuttonVisible = false;
    private Boolean endSwitch = false;

    private String htmlStylePart1 = "<html><div style='text-align: center; padding: auto;'>";
    private String htmlStylePart2 = "</div><br/><div style='text-align: center; padding: 10px;'>";
    private String htmlStylePart3 = "</div></html>";

    public CastlerFrame(Menu m, String n1, String n2, String title, int size, int swampcnt, int swampsize, int mountaincnt, int mountainsize) {
        super(title);
        this.menu = m;
        this.setSize(new Dimension(1000, 1000));
        this.setLocationRelativeTo(null);
        this.p1name = n1;
        this.p2name = n2;
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);

        setIconImage(new ImageIcon(getClass().getResource("/images/icon.png")).getImage());

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        this.setContentPane(mainPanel);
        gamePanel = new GamePanel(p1name, p2name, size, swampcnt, swampsize, mountaincnt, mountainsize);
        mainPanel.add(gamePanel, BorderLayout.CENTER);

        gamePanel.getGame().getPlayers().get(0).setName(p1name);
        gamePanel.getGame().getPlayers().get(1).setName(p2name);

        actionPanel = new JPanel();
        actionPanel.setLayout(new GridLayout(1, 1));
        mainPanel.add(actionPanel, BorderLayout.EAST);

        sellButton = new SellButton(this);
        sellButton.setText("Upgrade");
        sellButton.setText(htmlStylePart1 + "Sell structure" + htmlStylePart3);
        sellButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        sellButton.setHorizontalTextPosition(SwingConstants.CENTER);
        sellButton.setIcon(new ImageIcon(getClass().getResource("/images/sell.png")));
        sellButton.setBorder(new MatteBorder(1, 1, 1, 1, Color.black));
        sellButton.setBackground(Color.decode("#CCF375"));

        upgradeButton = new UpgradeButton(this);
        upgradeButton.setText("Upgrade");
        upgradeButton.setText(htmlStylePart1 + "Upgrade castle" + htmlStylePart2 + "Cost: 500" + htmlStylePart3);
        upgradeButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        upgradeButton.setHorizontalTextPosition(SwingConstants.CENTER);
        upgradeButton.setIcon(new ImageIcon(getClass().getResource("/images/upgrade.png")));
        upgradeButton.setBorder(new MatteBorder(1, 1, 1, 1, Color.black));
        upgradeButton.setBackground(Color.decode("#CCF375"));

        towerUpgradeButton = new TowerUpgradeButton(this);
        towerUpgradeButton.setText("Upgrade");
        towerUpgradeButton.setText(htmlStylePart1 + "Upgrade Tower" + htmlStylePart2 + "Cost: 500" + htmlStylePart3);
        towerUpgradeButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        towerUpgradeButton.setHorizontalTextPosition(SwingConstants.CENTER);
        towerUpgradeButton.setIcon(new ImageIcon(getClass().getResource("/images/upgrade.png")));
        towerUpgradeButton.setBorder(new MatteBorder(1, 1, 1, 1, Color.black));
        towerUpgradeButton.setBackground(Color.decode("#CCF375"));

        towerButton = new TowerButton(this);
        towerButton.setText(htmlStylePart1 + "Tower" + htmlStylePart2 + "Cost: " + Tower.getPrice() + "g" + htmlStylePart3);
        towerButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        towerButton.setHorizontalTextPosition(SwingConstants.CENTER);
        towerButton.setIcon(new ImageIcon(getClass().getResource("/images/tower_base.png")));
        towerButton.setBorder(new MatteBorder(1, 1, 1, 1, Color.black));
        towerButton.setBackground(Color.decode("#CCF391"));

        areaTowerButton = new AreaTowerButton(this);
        areaTowerButton.setText(htmlStylePart1 + "Area Tower" + htmlStylePart2 + "Cost: " + Tower.getPrice() + "g" + htmlStylePart3);
        areaTowerButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        areaTowerButton.setHorizontalTextPosition(SwingConstants.CENTER);
        areaTowerButton.setIcon(new ImageIcon(getClass().getResource("/images/tower_area_base.png")));
        areaTowerButton.setBorder(new MatteBorder(1, 1, 1, 1, Color.black));
        areaTowerButton.setBackground(Color.decode("#CCF391"));

        lineTowerButton = new LineTowerButton(this);
        lineTowerButton.setText(htmlStylePart1 + "Line Tower" + htmlStylePart2 + "Cost: " + Tower.getPrice() + "g" + htmlStylePart3);
        lineTowerButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        lineTowerButton.setHorizontalTextPosition(SwingConstants.CENTER);
        lineTowerButton.setIcon(new ImageIcon(getClass().getResource("/images/tower_line_base.png")));
        lineTowerButton.setBorder(new MatteBorder(1, 1, 1, 1, Color.black));
        lineTowerButton.setBackground(Color.decode("#CCF391"));

        exploreTowerButton = new ExploreTowerButton(this);
        exploreTowerButton.setText(htmlStylePart1 + "Explore Tower" + htmlStylePart2 + "Cost: " + Tower.getPrice() + "g" + htmlStylePart3);
        exploreTowerButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        exploreTowerButton.setHorizontalTextPosition(SwingConstants.CENTER);
        exploreTowerButton.setIcon(new ImageIcon(getClass().getResource("/images/tower_explore_base.png")));
        exploreTowerButton.setBorder(new MatteBorder(1, 1, 1, 1, Color.black));
        exploreTowerButton.setBackground(Color.decode("#CCF391"));

        soldierButton = new FighterButton(this);
        soldierButton.setText(htmlStylePart1 + "Soldier" + htmlStylePart2 + "Cost: 50g" + htmlStylePart3);
        soldierButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        soldierButton.setHorizontalTextPosition(SwingConstants.CENTER);
        soldierButton.setIcon(new ImageIcon(getClass().getResource("/images/spearman_base.png")));
        soldierButton.setBorder(new MatteBorder(1, 1, 1, 1, Color.black));
        soldierButton.setBackground(Color.decode("#CCE371"));

        mountaineerButton = new MountaineerButton(this);
        mountaineerButton.setText(htmlStylePart1 + "Mountaineer" + htmlStylePart2 + "Cost: 75g" + htmlStylePart3);
        mountaineerButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        mountaineerButton.setHorizontalTextPosition(SwingConstants.CENTER);
        mountaineerButton.setIcon(new ImageIcon(getClass().getResource("/images/archer_base.png")));
        mountaineerButton.setBorder(new MatteBorder(1, 1, 1, 1, Color.black));
        mountaineerButton.setBackground(Color.decode("#C0FF85"));

        demolisherButton = new DemolisherButton(this);
        demolisherButton.setText(htmlStylePart1 + "Demolisher" + htmlStylePart2 + "Cost: 75g" + htmlStylePart3);
        demolisherButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        demolisherButton.setHorizontalTextPosition(SwingConstants.CENTER);
        demolisherButton.setIcon(new ImageIcon(getClass().getResource("/images/ram_base.png")));
        demolisherButton.setBorder(new MatteBorder(1, 1, 1, 1, Color.black));
        demolisherButton.setBackground(Color.decode("#CCF089"));

        barrackButton = new BarrackButton(this);
        barrackButton.setText(htmlStylePart1 + "Barrack" + htmlStylePart2 + "Cost: " + Barrack.getPrice() + "g" + htmlStylePart3);
        barrackButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        barrackButton.setHorizontalTextPosition(SwingConstants.CENTER);
        barrackButton.setIcon(new ImageIcon(getClass().getResource("/images/barakk_base.png")));
        barrackButton.setBorder(new MatteBorder(1, 1, 1, 1, Color.black));
        barrackButton.setBackground(Color.decode("#CCF375"));

        waypointButton = new WayPointButton(this);
        waypointButton.setText(htmlStylePart1 + "WayPoint" + htmlStylePart3);
        waypointButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        waypointButton.setHorizontalTextPosition(SwingConstants.CENTER);
        waypointButton.setIcon(new ImageIcon(getClass().getResource("/images/waypoint.png")));
        waypointButton.setBorder(new MatteBorder(1, 1, 1, 1, Color.black));
        waypointButton.setBackground(Color.decode("#CCF375"));

        directionLeftButton = new DirectionButton(this, BasicArrowButton.WEST);
        directionLeftButton.setBorder(new MatteBorder(1, 1, 1, 1, Color.black));
        directionLeftButton.setBackground(Color.decode("#CCF389"));

        directionRightButton = new DirectionButton(this, BasicArrowButton.EAST);
        directionRightButton.setBorder(new MatteBorder(1, 1, 1, 1, Color.black));
        directionRightButton.setBackground(Color.decode("#CCF395"));

        directionUpButton = new DirectionButton(this, BasicArrowButton.NORTH);
        directionUpButton.setBorder(new MatteBorder(1, 1, 1, 1, Color.black));
        directionUpButton.setBackground(Color.decode("#CCF375"));

        directionDownButton = new DirectionButton(this, BasicArrowButton.SOUTH);
        directionDownButton.setBorder(new MatteBorder(1, 1, 1, 1, Color.black));
        directionDownButton.setBackground(Color.decode("#CCF358"));

        endTurn = new EndTurnButton(this);
        endTurn.setText(htmlStylePart1 + "End turn" + htmlStylePart2 + "Gain: 100g" + htmlStylePart3);
        endTurn.setVerticalTextPosition(SwingConstants.BOTTOM);
        endTurn.setHorizontalTextPosition(SwingConstants.CENTER);
        endTurn.setIcon(new ImageIcon(getClass().getResource("/images/ok.png")));
        endTurn.setBorder(new MatteBorder(1, 1, 1, 1, Color.black));
        endTurn.setBackground(Color.decode("#CCF375"));
        actionPanel.add(endTurn);

        infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(2, 6));
        mainPanel.add(infoPanel, BorderLayout.NORTH);

        bluePlayerLabel = new JLabel();
        bluePlayerLabel.setText("Blue Player: ");
        bluePlayerLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        bluePlayerLabel.setForeground(Color.blue);
        bluePlayerLabel.setOpaque(true);

        blueGoldLabel = new JLabel();
        blueGoldLabel.setText(gamePanel.getGame().getPlayers().get(0).getGold() + "g");

        blueTowers = new JLabel();
        blueTowers.setText("Level: " + this.getGamePanel().getGame().getPlayers().get(0).getCastleLevel() + " T: " + getGamePanel().getGame().getPlayers().get(0).getTowerCount() + "/" + getGamePanel().getGame().getPlayers().get(0).getMaxTower());
        blueTowerscnt = new JLabel();
        blueTowerscnt.setText(" Tornyok: " + getGamePanel().getGame().getPlayers().get(0).getTowerCount() + "/" + getGamePanel().getGame().getPlayers().get(0).getMaxTower());
        blueHealth = new JProgressBar();
        roundLabel = new JLabel();
        roundLabel.setText("Kör: 1");
        roundLabel.setHorizontalAlignment(SwingConstants.CENTER);
        redPlayerLabel = new JLabel();
        redPlayerLabel.setText("Red Player: ");
        redPlayerLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        redPlayerLabel.setForeground(Color.red);
        redPlayerLabel.setOpaque(true);

        redGoldLabel = new JLabel();
        redGoldLabel.setText(gamePanel.getGame().getPlayers().get(1).getGold() + "g");

        redTowers = new JLabel();
        redTowers.setText("Level: " + this.getGamePanel().getGame().getPlayers().get(1).getCastleLevel() + " Tornyok: " + getGamePanel().getGame().getPlayers().get(1).getTowerCount() + "/" + getGamePanel().getGame().getPlayers().get(1).getMaxTower());
        redTowerscnt = new JLabel();
        redTowerscnt.setText(" T: " + getGamePanel().getGame().getPlayers().get(1).getTowerCount() + "/" + getGamePanel().getGame().getPlayers().get(1).getMaxTower());

        //infoPanel.add(redTowerscnt);
        redHealth = new JProgressBar();

        errorLabel = new JLabel();
        errorLabel.setText("");
        errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
        errorLabel.setForeground(Color.red);

        redHealth.setStringPainted(true);
        blueHealth.setStringPainted(true);
        redHealth.setForeground(Color.RED);
        blueHealth.setForeground(Color.RED);

        if (gamePanel.getGame().getMapSize() < 15) {
            infoPanel.setLayout(new GridLayout(3, 4));
            infoPanel.add(bluePlayerLabel);
            infoPanel.add(blueGoldLabel);
            infoPanel.add(blueTowers);
            infoPanel.add(blueHealth);
            infoPanel.add(redPlayerLabel);
            infoPanel.add(redGoldLabel);
            infoPanel.add(redTowers);
            infoPanel.add(redHealth);
            infoPanel.add(roundLabel);
            infoPanel.add(errorLabel);
        } else {
            infoPanel.setLayout(new GridLayout(2, 5));
            infoPanel.add(bluePlayerLabel);
            infoPanel.add(blueGoldLabel);
            infoPanel.add(blueTowers);
            infoPanel.add(blueHealth);
            infoPanel.add(roundLabel);
            infoPanel.add(redPlayerLabel);
            infoPanel.add(redGoldLabel);
            infoPanel.add(redTowers);
            infoPanel.add(redHealth);
            infoPanel.add(errorLabel);
        }
        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (!gamePanel.getGame().gameEnded()) {

                    if (gamePanel.getGame().getCurrentActiveTile() != null && gamePanel.getGame().getCurrentActiveTile().getEntity() != null) {
                        towerUpgradeButton.setText(htmlStylePart1 + "Upgrade Tower" + htmlStylePart2 + "Cost: " + gamePanel.getGame().getCurrentActiveTile().getEntity().getLvl() * 30 + htmlStylePart3);
                    }
                    blueHealth.setValue((int) (gamePanel.getGame().getPlayers().get(0).getCastle().getHitPoint() / gamePanel.getGame().getPlayers().get(0).getCastle().getMaxHp() * 100));
                    redHealth.setValue((int) (gamePanel.getGame().getPlayers().get(1).getCastle().getHitPoint() / gamePanel.getGame().getPlayers().get(1).getCastle().getMaxHp() * 100));

                    blueTowers.setText("Castle lvl: " + (getGamePanel().getGame().getPlayers().get(0).getCastleLevel() == 3 ? " MAX " : getGamePanel().getGame().getPlayers().get(0).getCastleLevel()) + "   Torony: " + getGamePanel().getGame().getPlayers().get(0).getTowerCount() + "/" + getGamePanel().getGame().getPlayers().get(0).getMaxTower());
                    redTowers.setText("Castle lvl: " + (getGamePanel().getGame().getPlayers().get(1).getCastleLevel() == 3 ? " MAX " : getGamePanel().getGame().getPlayers().get(1).getCastleLevel()) + "   Torony: " + getGamePanel().getGame().getPlayers().get(1).getTowerCount() + "/" + getGamePanel().getGame().getPlayers().get(1).getMaxTower());
                    blueTowerscnt.setText(" Tornyok: " + getGamePanel().getGame().getPlayers().get(0).getTowerCount() + "/" + getGamePanel().getGame().getPlayers().get(0).getMaxTower());
                    redTowerscnt.setText(" Tornyok: " + getGamePanel().getGame().getPlayers().get(1).getTowerCount() + "/" + getGamePanel().getGame().getPlayers().get(1).getMaxTower());
                    blueGoldLabel.setText(gamePanel.getGame().getPlayers().get(0).getGold() + "g");
                    blueGoldLabel.setPreferredSize(new Dimension(10, 20));
                    redGoldLabel.setText(gamePanel.getGame().getPlayers().get(1).getGold() + "g");
                    redGoldLabel.setSize(new Dimension(30, 20));
                    roundLabel.setText("Kör: " + (int) Math.ceil(gamePanel.getGame().getRoundNumber() / 2.0f));
                    if (gamePanel.getGame().getError() != "-1") {
                        errorLabel.setText("Hiba: " + gamePanel.getGame().getError());
                    } else {
                        errorLabel.setText("");
                    }
                    if (gamePanel.getGame().getCurrentPlayer() != null && gamePanel.getGame().getCurrentPlayer().equals(gamePanel.getGame().getPlayers().get(0))) {
                        bluePlayerLabel.setBackground(Color.yellow);
                        bluePlayerLabel.setText("Aktív: " + gamePanel.getGame().getCurrentPlayer().getName() + " - Pénze: ");
                        redPlayerLabel.setBackground(Color.white);
                        redPlayerLabel.setText(gamePanel.getGame().getPlayers().get(1).getName() + " - Pénze: ");
                    } else {
                        redPlayerLabel.setBackground(Color.yellow);
                        bluePlayerLabel.setBackground(Color.white);
                        redPlayerLabel.setText("Aktív: " + gamePanel.getGame().getCurrentPlayer().getName() + " - Pénze: ");
                        bluePlayerLabel.setText(gamePanel.getGame().getPlayers().get(0).getName() + " - Pénze: ");
                    }
                    //actionPanel.setLayout(new GridLayout(3, 3));
                    if (gamePanel.getGame().getCurrentActiveTile() != null) {

                        if (fighterVisible) {
                            actionPanel.remove(soldierButton);
                            fighterVisible = false;
                        }
                        if (demolisherVisible) {
                            actionPanel.remove(demolisherButton);
                            demolisherVisible = false;
                        }
                        if (mountaineerVisible) {
                            actionPanel.remove(mountaineerButton);
                            mountaineerVisible = false;
                        }
                        if (towerVisible) {
                            actionPanel.remove(towerButton);
                            towerVisible = false;
                        }
                        if (barrackVisible) {
                            actionPanel.remove(barrackButton);
                            barrackVisible = false;
                        }
                        if (LineTowerVisible) {
                            actionPanel.remove(lineTowerButton);
                            LineTowerVisible = false;
                        }
                        if (AreaTowerVisible) {
                            actionPanel.remove(areaTowerButton);
                            AreaTowerVisible = false;
                        }
                        if (ExploreTowerVisible) {
                            actionPanel.remove(exploreTowerButton);
                            ExploreTowerVisible = false;
                        }
                        if (upgradeVisible) {
                            actionPanel.remove(upgradeButton);
                            upgradeVisible = false;
                        }
                        if (upgradeTowerVisible) {
                            actionPanel.remove(towerUpgradeButton);
                            upgradeTowerVisible = false;
                        }
                        if (directionLeftVisible) {
                            actionPanel.remove(directionLeftButton);
                            directionLeftVisible = false;
                        }
                        if (directionRightVisible) {
                            actionPanel.remove(directionRightButton);
                            directionRightVisible = false;
                        }
                        if (directionUpVisible) {
                            actionPanel.remove(directionUpButton);
                            directionUpVisible = false;
                        }
                        if (directionDownVisible) {
                            actionPanel.remove(directionDownButton);
                            directionDownVisible = false;
                        }
                        if (sellbuttonVisible) {
                            actionPanel.remove(sellButton);
                            sellbuttonVisible = false;
                        }
                        if (WaypointbuttonVisible) {
                            actionPanel.remove(waypointButton);
                            WaypointbuttonVisible = false;
                        }

                        if (gamePanel.getGame().getCurrentActiveTile().getEntity() != null
                                && gamePanel.getGame().getCurrentActiveTile().getEntity().whatami() == "Tower"
                                && gamePanel.getGame().getCurrentActiveTile().getEntity().getOwner().equals(gamePanel.getGame().getCurrentPlayer())) {
                            actionPanel.setLayout(new GridLayout(5, 1));
                            if (!AreaTowerVisible) {
                                actionPanel.add(areaTowerButton);
                                AreaTowerVisible = true;
                            }
                            if (!LineTowerVisible) {
                                actionPanel.add(lineTowerButton);
                                LineTowerVisible = true;
                            }
                            if (!ExploreTowerVisible) {
                                actionPanel.add(exploreTowerButton);
                                ExploreTowerVisible = true;
                            }
                            if (!sellbuttonVisible) {
                                actionPanel.add(sellButton);
                                sellbuttonVisible = true;
                            }
                        } else if (gamePanel.getGame().getCurrentActiveTile().getEntity() != null
                                && gamePanel.getGame().getCurrentActiveTile().getEntity().whatami() == "Barrack"
                                && gamePanel.getGame().getCurrentActiveTile().getEntity().getOwner().equals(gamePanel.getGame().getCurrentPlayer())) {
                            actionPanel.setLayout(new GridLayout(5, 1));
                            if (!fighterVisible) {
                                actionPanel.add(soldierButton);
                                fighterVisible = true;
                            }
                            if (!demolisherVisible) {
                                actionPanel.add(demolisherButton);
                                demolisherVisible = true;
                            }
                            if (!mountaineerVisible) {
                                actionPanel.add(mountaineerButton);
                                mountaineerVisible = true;
                            }
                            if (!sellbuttonVisible) {
                                actionPanel.add(sellButton);
                                sellbuttonVisible = true;
                            }
                        } else if (gamePanel.getGame().getCurrentActiveTile().getEntity() != null
                                && (gamePanel.getGame().getCurrentActiveTile().getEntity().whatami() == "line")
                                && (gamePanel.getGame().getCurrentActiveTile().getEntity().getLvl() == 0)
                                && gamePanel.getGame().getCurrentActiveTile().getEntity().getOwner().equals(gamePanel.getGame().getCurrentPlayer())) {
                            actionPanel.setLayout(new GridLayout(5, 1));
                            if (!directionLeftVisible) {
                                actionPanel.add(directionLeftButton);
                                directionLeftVisible = true;
                            }
                            if (!directionRightVisible) {
                                actionPanel.add(directionRightButton);
                                directionRightVisible = true;
                            }
                            if (!directionUpVisible) {
                                actionPanel.add(directionUpButton);
                                directionUpVisible = true;
                            }
                            if (!directionDownVisible) {
                                actionPanel.add(directionDownButton);
                                directionDownVisible = true;
                            }

                        } else if (gamePanel.getGame().getCurrentActiveTile().getEntity() != null
                                && (gamePanel.getGame().getCurrentActiveTile().getEntity().whatami() == "area"
                                || gamePanel.getGame().getCurrentActiveTile().getEntity().whatami() == "explore"
                                || gamePanel.getGame().getCurrentActiveTile().getEntity().whatami() == "line")
                                && gamePanel.getGame().getCurrentActiveTile().getEntity().getOwner().equals(gamePanel.getGame().getCurrentPlayer())) {
                            if (!upgradeTowerVisible) {
                                actionPanel.setLayout(new GridLayout(3, 1));
                                actionPanel.add(towerUpgradeButton);
                                upgradeTowerVisible = true;
                            }
                            if (!sellbuttonVisible) {
                                actionPanel.setLayout(new GridLayout(3, 1));
                                actionPanel.add(sellButton);
                                sellbuttonVisible = true;
                            }

                        } else if (gamePanel.getGame().getCurrentActiveTile().getEntity() != null
                                && gamePanel.getGame().getCurrentActiveTile().getEntity().whatami() == "Castle"
                                && gamePanel.getGame().getCurrentActiveTile().getEntity().getOwner().equals(gamePanel.getGame().getCurrentPlayer())) {
                            if (!fighterVisible) {
                                actionPanel.setLayout(new GridLayout(3, 1));
                                actionPanel.add(soldierButton);
                                fighterVisible = true;
                            }
                            if (!upgradeVisible) {
                                actionPanel.setLayout(new GridLayout(3, 1));
                                actionPanel.add(upgradeButton);
                                upgradeVisible = true;
                            }
                        } else if (gamePanel.getGame().getCurrentActiveTile().getEntity() != null
                                && (gamePanel.getGame().getCurrentActiveTile().getEntity().whatami() == "Fighter"
                                || gamePanel.getGame().getCurrentActiveTile().getEntity().whatami() == "Mountaineer"
                                || gamePanel.getGame().getCurrentActiveTile().getEntity().whatami() == "Demolisher")
                                && gamePanel.getGame().getCurrentActiveTile().getEntity().getOwner().equals(gamePanel.getGame().getCurrentPlayer())) {
                            if (!WaypointbuttonVisible) {
                                actionPanel.setLayout(new GridLayout(2, 1));
                                actionPanel.add(waypointButton);
                                WaypointbuttonVisible = true;
                            }
                        } else if (gamePanel.getGame().getCurrentActiveTile().getEntity() == null) {
                            if (!towerVisible) {
                                actionPanel.setLayout(new GridLayout(3, 1));
                                actionPanel.add(towerButton);
                                towerVisible = true;
                            }
                            if (!barrackVisible) {
                                actionPanel.setLayout(new GridLayout(3, 1));
                                actionPanel.add(barrackButton);
                                barrackVisible = true;
                            }
                        } else {
                            actionPanel.setLayout(new GridLayout(1, 1));
                        }

                    }
                    setVisible(true);
                    actionPanel.repaint();
                    infoPanel.repaint();
                    if (gamePanel.isAnimationActive()) {
                        for (Component c : actionPanel.getComponents()) {
                            c.setEnabled(false);
                        }
                    } else {
                        for (Component c : actionPanel.getComponents()) {
                            c.setEnabled(true);
                        }
                        gamePanel.repaint();
                    }
                    gamePanel.getGame().checkWinner();
                } else if (!endSwitch) {
                    JOptionPane.showMessageDialog(null, gamePanel.getGame().getWinner() + " nevű játékos nyert!");
                    menu.setVisible(true);
                    endSwitch = true;
                    dispose();
                }

            }
        }
        );
        timer.start();

        setVisible(
                true);

        this.pack();
    }

    public GamePanel getGamePanel() {
        return (GamePanel) this.gamePanel;
    }

    public void setBlueHealth(int a) {

    }

    public void setRedHealth(int a) {

    }
}
