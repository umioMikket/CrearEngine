package com.umiomikket.crearengine.utils.vectors;

import java.awt.geom.Point2D;

public class VectorRotatedDouble {
    public double x, y;
    public float rotation;

    public VectorRotatedDouble(double x, double y, float rotation) {
        this.x = x;
        this.y = y;
        this.rotation = rotation;
    }

    public Point2D.Double getPosition() { return new Point2D.Double(x, y); }
    public void setPosition(Point2D.Double position) { x = position.x; y = position.y; }
    public void setPosition(double x, double y) { this.x = x; this.y = y; }

    public double getX() { return x; }
    public void setX(double value) { x = value; }

    public double getY() { return y; }
    public void setY(double value) { y = value; }

    public float getRotation() { return rotation; }
    public void setRotation(float rotation) { this.rotation = rotation % 360; }
}
