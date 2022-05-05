/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package castler;

import castler.Buttons.ActionButton;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Menu extends JFrame {

    private int size;
    private int swampcnt;
    private int swampsize;
    private int mountaincnt;
    private int mountainsize;

    private JPanel mainpanel;
    private JPanel menupanel;
    private JButton newGameButton;
    private JButton exitGameButton;
    private JButton customMapButton;
    private JButton okButton;
    private JLabel logo;
    private CastlerFrame game;
    private Menu menuMe;

    JPanel SwampTempPanel;
    JPanel MountainTempPanel;

    private JPanel SwampPanel;
    private JLabel SwampLabel;
    private JLabel SwampSizeLabel;
    private JLabel SwampCountLabel;
    private JSpinner SwampSpinner;
    private JSlider SwampSlider;

    private JPanel SizePanel;
    private JLabel SizeLabel;
    private JSpinner SizeSpinner;

    private JPanel MountainPanel;
    private JLabel MountainLabel;
    private JLabel MountainSizeLabel;
    private JLabel MountainCountLabel;
    private JSpinner MountainSpinner;
    private JSlider MountainSlider;

    private JPanel p1Panel;
    private JLabel p1Label;
    private JTextField p1Text;

    private JPanel p2Panel;
    private JLabel p2Label;
    private JTextField p2Text;

    private JButton start;
    private JButton back;

    public Menu() {
        super("Castler");
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setSize(new Dimension(300, 300));
        this.setLocationRelativeTo(null);

        SpinnerNumberModel model1 = new SpinnerNumberModel(5, 0, 10, 1);
        SpinnerNumberModel model2 = new SpinnerNumberModel(5, 0, 10, 1);
        SpinnerNumberModel model3 = new SpinnerNumberModel(15, 10, 30, 1);

        menuMe = this;

        size = 15;
        swampcnt = 7;
        swampsize = 70;
        mountaincnt = 2;
        mountainsize = 50;

        setIconImage(new ImageIcon(getClass().getResource("/images/icon.png")).getImage());

        logo = new JLabel();
        logo.setIcon(new ImageIcon(getClass().getResource("/images/name.png")));
        logo.setSize(10, 10);
        logo.setVisible(true);

        SwampPanel = new JPanel();
        SwampTempPanel = new JPanel();
        SwampPanel.setLayout(new GridLayout(1, 2));
        SwampTempPanel.setLayout(new GridLayout(2, 2));
        SwampLabel = new JLabel(" Mocsár:");
        SwampCountLabel = new JLabel(" Száma:");
        SwampSpinner = new JSpinner(model1);
        SwampSpinner.setValue(7);
        SwampSlider = new JSlider();
        SwampSizeLabel = new JLabel(" Mérete: " + SwampSlider.getValue());
        SwampTempPanel.add(SwampCountLabel);
        SwampTempPanel.add(SwampSizeLabel);
        SwampTempPanel.add(SwampSpinner);
        SwampTempPanel.add(SwampSlider);

        SwampPanel.add(SwampLabel);
        SwampPanel.add(SwampTempPanel);

        SizePanel = new JPanel();
        SizePanel.setLayout(new GridLayout(1, 2));
        SizeLabel = new JLabel(" Pálya mérete:");
        SizeSpinner = new JSpinner(model3);
        SizePanel.add(SizeLabel);
        SizePanel.add(SizeSpinner);

        MountainPanel = new JPanel();
        MountainPanel.setLayout(new GridLayout(1, 2));
        MountainTempPanel = new JPanel();
        MountainTempPanel.setLayout(new GridLayout(2, 2));
        MountainLabel = new JLabel(" Hegy:");
        MountainCountLabel = new JLabel(" Száma:");
        MountainSpinner = new JSpinner(model2);
        MountainSpinner.setValue(2);
        MountainSlider = new JSlider();
        MountainSizeLabel = new JLabel(" Mérete: " + SwampSlider.getValue());
        MountainTempPanel.add(MountainCountLabel);
        MountainTempPanel.add(MountainSizeLabel);
        MountainTempPanel.add(MountainSpinner);
        MountainTempPanel.add(MountainSlider);

        MountainPanel.add(MountainLabel);
        MountainPanel.add(MountainTempPanel);

        p1Panel = new JPanel();
        p1Panel.setLayout(new GridLayout(1, 2));
        p1Label = new JLabel("1. játékos neve:");
        p1Text = new JTextField("Blue");
        p1Panel.add(p1Label);
        p1Panel.add(p1Text);

        p2Panel = new JPanel();
        p2Panel.setLayout(new GridLayout(1, 2));
        p2Label = new JLabel("2. játékos neve:");
        p2Text = new JTextField("Red");
        p2Panel.add(p2Label);
        p2Panel.add(p2Text);

        SwampSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent event) {
                SwampSizeLabel.setText("Mérete: " + SwampSlider.getValue());
            }
        });

        MountainSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent event) {
                MountainSizeLabel.setText("Mérete: " + MountainSlider.getValue());
            }
        });

        start = new JButton("Start");
        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println(swampcnt);
                System.out.println(swampsize);
                System.out.println(mountaincnt);
                System.out.println(mountainsize);
                setVisible(false);
                if (p1Text.getText().isEmpty()) {
                    p1Text.setText("Blue");
                }
                if (p2Text.getText().isEmpty()) {
                    p2Text.setText("Red");
                }
                game = new CastlerFrame(menuMe, p1Text.getText(), p2Text.getText(), "Castler", size, swampcnt, swampsize, mountaincnt, mountainsize);
            }
        });

        back = new JButton("Back");
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menupanel.removeAll();
                menupanel.revalidate();
                menupanel.repaint();
                menupanel.setLayout(new GridLayout(4, 1));
                menupanel.add(logo);
                menupanel.add(newGameButton);
                menupanel.add(customMapButton);
                menupanel.add(exitGameButton);
            }
        });

        customMapButton = new JButton("Customize Map");
        customMapButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menupanel.removeAll();
                menupanel.revalidate();
                menupanel.repaint();
                menupanel.setLayout(new GridLayout(5, 1));
                menupanel.add(SizePanel);
                menupanel.add(SwampPanel);
                menupanel.add(MountainPanel);
                menupanel.add(okButton);
                menupanel.add(back);
            }
        });

        okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                size = (Integer) SizeSpinner.getValue();
                swampcnt = (Integer) SwampSpinner.getValue();
                swampsize = SwampSlider.getValue();
                mountaincnt = (Integer) MountainSpinner.getValue();
                mountainsize = MountainSlider.getValue();
                System.out.println(swampcnt);
                System.out.println(mountaincnt);
                menupanel.removeAll();
                menupanel.revalidate();
                menupanel.repaint();
                menupanel.setLayout(new GridLayout(4, 1));
                menupanel.add(logo);
                menupanel.add(newGameButton);
                menupanel.add(customMapButton);
                menupanel.add(exitGameButton);
            }
        });

        mainpanel = new JPanel();
        mainpanel.setLayout(new BorderLayout());
        this.setContentPane(mainpanel);
        menupanel = new JPanel();
        menupanel.setLayout(new GridLayout(4, 1));
        newGameButton = new JButton("New Game");
        newGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menupanel.removeAll();
                menupanel.revalidate();
                menupanel.repaint();
                p1Text.setText("Blue");
                p2Text.setText("Red");
                menupanel.setLayout(new GridLayout(4, 1));
                menupanel.add(p1Panel);
                menupanel.add(p2Panel);
                menupanel.add(start);
                menupanel.add(back);

            }
        });

        exitGameButton = new JButton("Exit");
        exitGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        start.setMargin(new Insets(0, 0, 0, 0));
        back.setMargin(new Insets(0, 0, 0, 0));
        newGameButton.setMargin(new Insets(0, 0, 0, 0));
        exitGameButton.setMargin(new Insets(0, 0, 0, 0));

        mainpanel.add(menupanel, BorderLayout.CENTER);
        menupanel.add(logo);
        menupanel.add(newGameButton);
        menupanel.add(customMapButton);
        menupanel.add(exitGameButton);
        this.setVisible(true);
        this.pack();
    }

}
