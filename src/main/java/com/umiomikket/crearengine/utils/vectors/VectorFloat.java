package com.umiomikket.crearengine.utils.vectors;

import java.awt.geom.Point2D;

public class VectorFloat {
    public float x, y;

    public VectorFloat(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Point2D.Float getPosition() { return new Point2D.Float(x, y); }
    public void setPosition(Point2D.Float position) { x = position.x; y = position.y; }
    public void setPosition(float x, float y) { this.x = x; this.y = y; }

    public float getX() { return x; }
    public void setX(float value) { x = value; }

    public float getY() { return y; }
    public void setY(float value) { y = value; }
}
