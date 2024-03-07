package com.umiomikket.crearengine.utils.sizes;

public class SizeFloat {
    public float width, height;

    public SizeFloat(float width, float height) {
        this.width = width;
        this.height = height;
    }

    public void setSize(float width, float height) { this.width = width; this.height = height; }

    public float getWidth() { return width; }
    public void setWidth(float value) { width = value; }

    public float getHeight() { return height; }
    public void setHeight(float value) { height = value; }
}
