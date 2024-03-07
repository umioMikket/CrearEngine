package com.umiomikket.crearengine.utils.sizes;

public class Size {
    public int width, height;

    public Size(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void setSize(int width, int height) { this.width = width; this.height = height; }

    public int getWidth() { return width; }
    public void setWidth(int value) { width = value; }

    public int getHeight() { return height; }
    public void setHeight(int value) { height = value; }
}
