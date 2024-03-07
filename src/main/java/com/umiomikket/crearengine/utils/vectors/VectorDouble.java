package com.umiomikket.crearengine.utils.vectors;

import java.awt.geom.Point2D;

public class VectorDouble {
    public double x, y;

    public VectorDouble(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point2D.Double getPosition() { return new Point2D.Double(x, y); }
    public void setPosition(Point2D.Double position) { x = position.x; y = position.y; }
    public void setPosition(double x, double y) { this.x = x; this.y = y; }

    public double getX() { return x; }
    public void setX(double value) { x = value; }

    public double getY() { return y; }
    public void setY(double value) { y = value; }
}
