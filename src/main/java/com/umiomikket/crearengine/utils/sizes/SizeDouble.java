package com.umiomikket.crearengine.utils.sizes;

public class SizeDouble {
    public double width, height;

    public SizeDouble(float width, float height) {
        this.width = width;
        this.height = height;
    }

    public void setSize(float width, float height) { this.width = width; this.height = height; }

    public double getWidth() { return width; }
    public void setWidth(float value) { width = value; }

    public double getHeight() { return height; }
    public void setHeight(float value) { height = value; }
}
