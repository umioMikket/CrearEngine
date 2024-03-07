package com.umiomikket.crearengine.utils.vectors;

import java.awt.*;

public class VectorRotated {
    public int x, y;
    public float rotation;

    public VectorRotated(int x, int y, float rotation) {
        this.x = x;
        this.y = y;
        this.rotation = rotation;
    }

    public Point getPosition() { return new Point(x, y); }
    public void setPosition(Point position) { x = position.x; y = position.y; }
    public void setPosition(int x, int y) { this.x = x; this.y = y; }

    public int getX() { return x; }
    public void setX(int value) { x = value; }

    public int getY() { return y; }
    public void setY(int value) { y = value; }

    public float getRotation() { return rotation; }
    public void setRotation(float rotation) { this.rotation = rotation % 360; }
}
