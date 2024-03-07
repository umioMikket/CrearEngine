package com.umiomikket.crearengine.utils;

import com.umiomikket.crearengine.GameBox;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class Cursor {
    private final GameBox gameBox;

    private final ArrayList<java.awt.Cursor> cursors;

    public Cursor(GameBox gameBox) {
        this.gameBox = gameBox;
        cursors = new ArrayList<>();
    }

    public boolean hasCursor(String id) {
        for (java.awt.Cursor cursor: cursors)
            if (cursor.getName().equals(id))
                return true;
        return false;
    }

    public void addCursor(String id, Image image) { addCursor(id, (BufferedImage) image); }

    public void addCursor(String id, String path) {
        BufferedImage image = null;

        try { image = ImageIO.read(new File(path)); }
        catch (Exception e) { e.printStackTrace(); }

        addCursor(id, image);
    }

    public void addCursor(String id, BufferedImage image) {
        cursors.add(Toolkit.getDefaultToolkit().createCustomCursor(image, new Point(0, 0), "cursor"));
    }

    public void removeCursor(String id) {
        cursors.removeIf(cursor -> cursor.getName().equals(id));
    }

    public void applyCursor(String id) {
        for (java.awt.Cursor cursor: cursors)
            if (cursor.getName().equals(id))
                gameBox.window.frame.setCursor(cursor);
    }

    public void reset() {
        gameBox.window.frame.setCursor(null);
    }
}
