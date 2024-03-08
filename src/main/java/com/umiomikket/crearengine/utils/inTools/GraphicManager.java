package com.umiomikket.crearengine.utils.inTools;

import com.umiomikket.crearengine.abstact.RenderAbstract;
import com.umiomikket.crearengine.graphics.*;

import java.awt.*;

public class GraphicManager {
    private final RenderAbstract renderComponent;

    public GraphicManager(RenderAbstract renderComponent) {
        this.renderComponent = renderComponent;
    }

    public ImageRender createImage() { return new ImageRender(renderComponent); }
    public LineRender createLine() { return new LineRender(renderComponent); }
    public OvalRender createOval() { return new OvalRender(renderComponent); }
    public OvalBorderRender createOvalBorder() { return new OvalBorderRender(renderComponent); }
    public TriangleRender createTriangle() { return new TriangleRender(renderComponent); }
    public TriangleBorderRender createTriangleBorder() { return new TriangleBorderRender(renderComponent); }
    public RectangleRender createRectangle() { return new RectangleRender(renderComponent); }
    public RectangleBorderRender createRectangleBorder() { return new RectangleBorderRender(renderComponent); }
    public TextRender createText() { return new TextRender(renderComponent); }

    public void draw(ImageRender image) { image.render(); }
    public void draw(ImageRender image, int width, int height) { image.render(width, height); }
    public void draw(ImageRender image, double scaleX, double scaleY) { image.render(scaleX, scaleY); }

    public void draw(OvalRender oval) { oval.render(); }
    public void draw(OvalRender oval, int width, int height) { oval.render(width, height); }
    public void draw(OvalRender oval, double scaleX, double scaleY) { oval.render(scaleX, scaleY); }

    public void draw(TriangleRender polygon) { polygon.render(); }

    public void draw(RectangleRender rectangle) { rectangle.render(); }
    public void draw(RectangleRender rectangle, int width, int height) { rectangle.render(width, height); }
    public void draw(RectangleRender rectangle, double scaleX, double scaleY) { rectangle.render(scaleX, scaleY); }

    public void draw(TextRender text) { text.render(); }
    public void draw(TextRender text, double scaleX, double scaleY) { text.render(scaleX, scaleY); }

    public void draw(Color color, int x, int y) { draw(color.hashCode(), x, y); }

    public void draw(int color, int x, int y) {
        if (x <= 0 || y <= 0 ||
                x >= renderComponent.getWidth() || y >= renderComponent.getHeight()
        ) return;

        renderComponent.getImage().setRGB(x, y, color);
    }
}
