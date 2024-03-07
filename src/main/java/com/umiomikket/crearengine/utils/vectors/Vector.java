package com.umiomikket.crearengine.utils.vectors;

import java.awt.*;

public class Vector {
    public int x, y;

    public Vector(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point getPosition() { return new Point(x, y); }
    public void setPosition(Point position) { x = position.x; y = position.y; }
    public void setPosition(int x, int y) { this.x = x; this.y = y; }

    public int getX() { return x; }
    public void setX(int value) { x = value; }

    public int getY() { return y; }
    public void setY(int value) { y = value; }
}
