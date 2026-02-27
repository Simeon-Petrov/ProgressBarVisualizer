package com.progressbar.ui;

import com.progressbar.config.AppColors;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class BarPanel extends JPanel {

    private int   pct       = 0;
    private Color fillColor = AppColors.COLOR_START;

    public BarPanel() {
        setPreferredSize(new Dimension(600, 20));
        setBackground(AppColors.BG_DARK);
        setOpaque(false);
    }

    public void setProgress(int pct, Color color) {
        this.pct       = pct;
        this.fillColor = color;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int w   = getWidth();
        int h   = getHeight();
        int arc = h;

        g2.setColor(new Color(15, 23, 42));
        g2.fill(new RoundRectangle2D.Float(0, 0, w, h, arc, arc));

        // Рамка
        g2.setColor(AppColors.BORDER_COLOR);
        g2.setStroke(new BasicStroke(1f));
        g2.draw(new RoundRectangle2D.Float(0, 0, w - 1, h - 1, arc, arc));
        if (pct > 0) {
            int fillW = (int)(w * pct / 100.0);


            GradientPaint gp = new GradientPaint(
                    0, 0,
                    new Color(fillColor.getRed(), fillColor.getGreen(), fillColor.getBlue(), 140),
                    fillW, 0,
                    fillColor
            );
            g2.setPaint(gp);
            g2.fill(new RoundRectangle2D.Float(0, 0, fillW, h, arc, arc));


            g2.setColor(new Color(255, 255, 255, 30));
            g2.fillRect(0, 0, fillW, h / 2);


            if (pct < 100) {
                int glowX = fillW - 6;
                RadialGradientPaint glow = new RadialGradientPaint(
                        glowX, h / 2f, 10,
                        new float[]{ 0f, 1f },
                        new Color[]{
                                new Color(fillColor.getRed(), fillColor.getGreen(), fillColor.getBlue(), 180),
                                new Color(0, 0, 0, 0)
                        }
                );
                g2.setPaint(glow);
                g2.fillOval(glowX - 10, h / 2 - 10, 20, 20);
            }
        }

        g2.dispose();
    }
}

