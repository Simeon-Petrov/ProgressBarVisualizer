package com.progressbar.controller;

import com.progressbar.config.AppColors;
import com.progressbar.ui.AppFrame;

import javax.swing.*;
import java.awt.*;


public class TimerController {

    private final AppFrame frame;

    private int     progress = 0;
    private boolean running  = false;
    private boolean paused   = false;
    private int     delayMs  = 30;
    private long    startTime;

    private Timer mainTimer;
    private Timer clockTimer;

    public TimerController(AppFrame frame) {
        this.frame = frame;
        buildTimers();
    }


    private void buildTimers() {

        mainTimer = new Timer(delayMs, e -> {
            if (progress < 100) {
                progress++;
                updateUI();
            } else {
                stop();
                frame.onComplete();
            }
        });

        clockTimer = new Timer(100, e -> {
            double elapsed = (System.currentTimeMillis() - startTime) / 1000.0;
            frame.updateElapsed(elapsed);
        });
    }

    public void start() {
        if (!running && progress < 100) {
            running   = true;
            paused    = false;
            startTime = System.currentTimeMillis();
            mainTimer.setDelay(delayMs);
            mainTimer.start();
            clockTimer.start();
            frame.onStart();
        }
    }

    public void pause() {
        if (running && !paused) {
            mainTimer.stop();
            clockTimer.stop();
            running = false;
            paused  = true;
            frame.onPause(progress);
        }
    }

    public void reset() {
        mainTimer.stop();
        clockTimer.stop();
        running  = false;
        paused   = false;
        progress = 0;
        frame.onReset();
    }

    public void setSpeed(int delayMs) {
        this.delayMs = delayMs;
        if (running) mainTimer.setDelay(delayMs);
    }


    private void updateUI() {
        Color color;
        if (progress < 50) {
            color = AppColors.interpolate(AppColors.COLOR_START, AppColors.COLOR_MID, progress / 50f);
        } else {
            color = AppColors.interpolate(AppColors.COLOR_MID, AppColors.COLOR_END, (progress - 50) / 50f);
        }
        frame.updateProgress(progress, color);
    }

    private void stop() {
        mainTimer.stop();
        clockTimer.stop();
        running = false;
    }

    public boolean isRunning() { return running; }
    public int     getProgress() { return progress; }
}