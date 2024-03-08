package com.umiomikket.crearengine.graphics;

import com.umiomikket.crearengine.abstact.RenderAbstract;
import com.umiomikket.crearengine.utils.vectors.Vector;
import com.umiomikket.crearengine.utils.vectors.VectorRotated;

import java.awt.*;
import java.io.File;

public class TextRender {
    private final RenderAbstract render;

    private String text;
    private Color color;
    private Font font;

    public final VectorRotated positionRotated;
    public final Vector offset;

    public TextRender(RenderAbstract render) {
        this.render = render;

        text = "";
        color = Color.WHITE;
        font = new Font("Times New Roman", Font.PLAIN, 15);

        positionRotated = new VectorRotated(0, 0, 0f);
        offset = new Vector(0, 0);
    }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public Color getColor() { return color; }
    public void setColor(Color color) { this.color = color; }
    public void setColor(int color) { this.color = new Color(color); }

    public Font getFont() { return font; }
    public void setFont(Font font) { this.font = font; }

    public void setFont(File file) {
        font = null;

        try { this.font = Font.createFont(Font.TRUETYPE_FONT, file); }
        catch (Exception e) { e.printStackTrace(); }
    }

    public void setFont(String path) {
        font = null;

        try { font = Font.createFont(Font.TRUETYPE_FONT, new File(path)); }
        catch (Exception e) { e.printStackTrace(); }
    }

    public Dimension getTextSize() {
        Graphics2D g2d = render.getGraphics();
        g2d.setFont(font);
        FontMetrics fm = g2d.getFontMetrics();
        g2d.dispose();

        return new Dimension(fm.stringWidth(text), fm.getHeight());
    }

    public int getTextWidth() {
        Graphics2D g2d = render.getGraphics();
        g2d.setFont(font);
        FontMetrics fm = g2d.getFontMetrics();
        g2d.dispose();

        return fm.stringWidth(text);
    }

    public int getTextHeight() {
        Graphics2D g2d = render.getGraphics();
        g2d.setFont(font);
        FontMetrics fm = g2d.getFontMetrics();
        g2d.dispose();

        return fm.getHeight();
    }

    public void render() { render(1d, 1d); }

    public void render(double scaleX, double scaleY) {
        if (text == null) return;

        Graphics2D g2d = render.getGraphics();

        g2d.translate(positionRotated.getX() - offset.getX(), positionRotated.getY() - offset.getY());
        g2d.rotate(Math.toRadians(positionRotated.getRotation()), offset.getX(), offset.getY());
        g2d.scale(scaleX, scaleY);
        g2d.translate(-positionRotated.getX() + offset.getX(), -positionRotated.getY() + offset.getY());

        g2d.setColor(color);
        g2d.setFont(font);
        g2d.drawString(text, positionRotated.getX() - offset.getX(), positionRotated.getY() - offset.getY());

        g2d.dispose();
    }
}
