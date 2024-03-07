package com.umiomikket.crearengine.utils.vectors;

import java.awt.geom.Point2D;

public class VectorRotatedFloat {
    public float x, y;
    public float rotation;

    public VectorRotatedFloat(float x, float y, float rotation) {
        this.x = x;
        this.y = y;
        this.rotation = rotation;
    }

    public Point2D.Float getPosition() { return new Point2D.Float(x, y); }
    public void setPosition(Point2D.Float position) { x = position.x; y = position.y; }
    public void setPosition(float x, float y) { this.x = x; this.y = y; }

    public float getX() { return x; }
    public void setX(float value) { x = value; }

    public float getY() { return y; }
    public void setY(float value) { y = value; }

    public float getRotation() { return rotation; }
    public void setRotation(float rotation) { this.rotation = rotation % 360; }
}
