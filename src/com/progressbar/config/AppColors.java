package com.progressbar.config;

import java.awt.*;

public class AppColors {

    public static final Color BG_DARK = new Color(7, 8, 15);
    public static final Color BG_PANEL = new Color(13, 13, 24);
    public static final Color BORDER_COLOR = new Color(30, 41, 59);
    public static final Color TEXT_DIM = new Color(71, 85, 105);
    public static final Color TEXT_LIGHT = new Color(203, 213, 225);
    public static final Color COLOR_START = new Color(56, 189, 248);
    public static final Color COLOR_MID = new Color(129, 140, 248);
    public static final Color COLOR_END = new Color(74, 222, 128);
    public static final Color COLOR_PAUSE = new Color(245, 158, 11);


    public static Color interpolate(Color a, Color b, float t) {
        int r = (int) (a.getRed() + (b.getRed() - a.getRed()) * t);
        int g = (int) (a.getGreen() + (b.getGreen() - a.getGreen()) * t);
        int bv = (int) (a.getBlue() + (b.getBlue() - a.getBlue()) * t);
        return new Color(
                Math.max(0, Math.min(255, r)),
                Math.max(0, Math.min(255, g)),
                Math.max(0, Math.min(255, bv))
        );
    }

    private AppColors() {
    }
}
