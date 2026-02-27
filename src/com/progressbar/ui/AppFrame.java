package com.progressbar.ui;

import com.progressbar.config.AppColors;
import com.progressbar.controller.TimerController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;


public class AppFrame extends JFrame {

    private JLabel  pctLabel;
    private JLabel  statusLabel;
    private JLabel  elapsedLabel;
    private JButton btnStart;
    private JButton btnPause;
    private JButton btnReset;
    private BarPanel barPanel;

    private TimerController controller;

    public AppFrame() {
        setTitle("Progress Bar Visualizer");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 420);
        setLocationRelativeTo(null);
        setResizable(false);
        setBackground(AppColors.BG_DARK);

        buildUI();

        controller = new TimerController(this);
        bindButtons();
    }


    private void buildUI() {
        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(AppColors.BG_DARK);

        root.add(buildHeader(),   BorderLayout.NORTH);
        root.add(buildCenter(),   BorderLayout.CENTER);
        root.add(buildControls(), BorderLayout.SOUTH);

        setContentPane(root);
    }

    private JPanel buildHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(7, 8, 16));
        header.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, AppColors.BORDER_COLOR),
                new EmptyBorder(14, 24, 14, 24)
        ));

        JLabel tag = new JLabel("JAVA SWING");
        tag.setFont(new Font("Monospaced", Font.PLAIN, 10));
        tag.setForeground(new Color(180, 180, 180));

        JLabel title = new JLabel("Progress Bar  Visualizer");
        title.setFont(new Font("Serif", Font.BOLD, 22));
        title.setForeground(AppColors.TEXT_LIGHT);

        JPanel left = new JPanel(new GridLayout(2, 1, 0, 2));
        left.setBackground(new Color(7, 8, 16));
        left.add(tag);
        left.add(title);

        header.add(left,          BorderLayout.WEST);
        header.add(buildSpeed(),  BorderLayout.EAST);
        return header;
    }

    private JPanel buildSpeed() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 6, 0));
        panel.setBackground(new Color(7, 8, 16));

        JLabel lbl = new JLabel("SPEED:");
        lbl.setFont(new Font("Monospaced", Font.PLAIN, 10));
        lbl.setForeground(new Color(180, 180, 180));
        panel.add(lbl);

        for (String s : new String[]{"Slow", "Normal", "Fast"}) {
            panel.add(makeSpeedBtn(s));
        }
        return panel;
    }

    private JPanel buildCenter() {
        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        center.setBackground(AppColors.BG_DARK);
        center.setBorder(new EmptyBorder(36, 48, 20, 48));

        pctLabel = new JLabel("0%");
        pctLabel.setFont(new Font("Serif", Font.BOLD, 96));
        pctLabel.setForeground(AppColors.COLOR_START);
        pctLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        statusLabel = new JLabel("Press START to begin");
        statusLabel.setFont(new Font("Monospaced", Font.PLAIN, 12));
        statusLabel.setForeground(new Color(180, 180, 180));
        statusLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        statusLabel.setBorder(new EmptyBorder(0, 0, 20, 0));

        barPanel = new BarPanel();
        barPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        barPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20));

        elapsedLabel = new JLabel("Elapsed: 0.0s");
        elapsedLabel.setFont(new Font("Monospaced", Font.PLAIN, 11));
        elapsedLabel.setForeground(new Color(180, 180, 180));
        elapsedLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        elapsedLabel.setBorder(new EmptyBorder(10, 0, 0, 0));

        center.add(pctLabel);
        center.add(statusLabel);
        center.add(barPanel);
        center.add(elapsedLabel);
        return center;
    }

    private JPanel buildControls() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 14));
        panel.setBackground(AppColors.BG_DARK);
        panel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, AppColors.BORDER_COLOR));

        btnStart = makeBtn("▶  START", AppColors.COLOR_START);
        btnPause = makeBtn("⏸  PAUSE", AppColors.TEXT_DIM);
        btnReset = makeBtn("↺  RESET", AppColors.TEXT_DIM);
        btnPause.setEnabled(false);

        panel.add(btnStart);
        panel.add(btnPause);
        panel.add(btnReset);
        return panel;
    }


    private void bindButtons() {
        btnStart.addActionListener(e -> controller.start());
        btnPause.addActionListener(e -> controller.pause());
        btnReset.addActionListener(e -> controller.reset());
    }


    public void updateProgress(int pct, java.awt.Color color) {
        pctLabel.setText(pct + "%");
        pctLabel.setForeground(color);
        barPanel.setProgress(pct, color);
        statusLabel.setText("Loading... " + pct + "%");
        statusLabel.setForeground(AppColors.COLOR_START);
    }

    public void updateElapsed(double seconds) {
        elapsedLabel.setText(String.format("Elapsed: %.1fs", seconds));
    }

    public void onStart() {
        btnStart.setEnabled(false);
        btnPause.setEnabled(true);
        statusLabel.setText("Running...");
        statusLabel.setForeground(AppColors.COLOR_START);
    }

    public void onPause(int pct) {
        btnStart.setEnabled(true);
        btnStart.setText("▶  RESUME");
        btnPause.setEnabled(false);
        statusLabel.setText("Paused at " + pct + "%");
        statusLabel.setForeground(AppColors.COLOR_PAUSE);
    }

    public void onReset() {
        pctLabel.setText("0%");
        pctLabel.setForeground(AppColors.COLOR_START);
        statusLabel.setText("Press START to begin");
        statusLabel.setForeground(AppColors.TEXT_DIM);
        elapsedLabel.setText("Elapsed: 0.0s");
        btnStart.setEnabled(true);
        btnStart.setText("▶  START");
        btnPause.setEnabled(false);
        barPanel.setProgress(0, AppColors.COLOR_START);
    }

    public void onComplete() {
        btnStart.setEnabled(false);
        btnPause.setEnabled(false);
        statusLabel.setText("✓  Complete!");
        statusLabel.setForeground(AppColors.COLOR_END);
        pctLabel.setForeground(AppColors.COLOR_END);
    }


    private JButton makeSpeedBtn(String label) {
        JButton b = new JButton(label);
        b.setFont(new Font("Monospaced", Font.PLAIN, 11));
        b.setForeground(AppColors.TEXT_DIM);
        b.setBackground(AppColors.BG_PANEL);
        b.setBorder(BorderFactory.createLineBorder(AppColors.BORDER_COLOR));
        b.setFocusPainted(false);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.setPreferredSize(new Dimension(70, 26));
        b.addActionListener(e -> {
            switch (label) {
                case "Slow":   controller.setSpeed(80); break;
                case "Normal": controller.setSpeed(30); break;
                case "Fast":   controller.setSpeed(8);  break;
            }
        });
        return b;
    }

    private JButton makeBtn(String label, Color fg) {
        JButton b = new JButton(label);
        b.setFont(new Font("Monospaced", Font.BOLD, 12));
        b.setForeground(fg);
        b.setBackground(AppColors.BG_PANEL);
        b.setBorder(BorderFactory.createLineBorder(AppColors.BORDER_COLOR));
        b.setFocusPainted(false);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.setPreferredSize(new Dimension(130, 36));
        return b;
    }
}