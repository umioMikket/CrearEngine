package com.umiomikket.crearengine.managers;

import com.umiomikket.crearengine.GameBox;

import javax.swing.*;
import java.awt.*;

public class WindowManager {
    private final JFrame frame;

    public WindowManager(GameBox gameBox) {
        frame = gameBox.window.frame;
    }

    public String getTitle() {
        return frame.getTitle();
    }

    public void setTitle(String title) {
        frame.setTitle(title);
    }

    public Dimension getSize() {
        return frame.getSize();
    }

    public void setSize(int width, int height) {
        frame.setSize(width, height);
    }

    public void setSize(Dimension size) {
        frame.setSize(size.width, size.height);
    }

    public int getWidth() {
        return frame.getWidth();
    }

    public void setWidth(int width) {
        frame.setSize(width, frame.getHeight());
    }

    public int getHeight() {
        return frame.getHeight();
    }

    public void setHeight(int height) {
        frame.setSize(frame.getWidth(), height);
    }

    public Point getPosition() {
        return frame.getLocation();
    }

    public void setPosition(int x, int y) {
        frame.setLocation(x, y);
    }

    public int getPositionX() {
        return getPosition().x;
    }

    public void setPositionX(int x) {
        setPosition(x, getPosition().y);
    }

    public int getPositionY() {
        return getPosition().y;
    }

    public void setPositionY(int y) {
        setPosition(getPosition().x, y);
    }
}
