package com.umiomikket.crearengine.utils.inTools;

import java.awt.*;

public class RenderSettings {
    public static final int MODE_RENDER_SPEED = 0;
    public static final int MODE_RENDER_DEFAULT = 1;
    public static final int MODE_RENDER_QUALITY = 2;
    
    private int renderMode;
    private boolean isAntiAliasing;
    private boolean isAntiAliasingText;
    private boolean isInterpolation;
    
    public RenderSettings() {
        renderMode = MODE_RENDER_DEFAULT;
    }
    
    public void setRenderingSettings(Graphics2D graphics2D) {
        if (getRenderMode() == MODE_RENDER_SPEED)
            graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
        else if(getRenderMode() == MODE_RENDER_DEFAULT)
            graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_DEFAULT);
        else if(getRenderMode() == MODE_RENDER_QUALITY)
            graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        if (isAntiAliasing())
            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (isAntiAliasingText())
            graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        if (isInterpolation())
            graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
    }
    
    public int getRenderMode() { return renderMode; }
    public void setRenderMode(int renderMode) { this.renderMode = renderMode; }

    public boolean isAntiAliasing() { return isAntiAliasing; }
    public void setAntiAliasing(boolean value) { this.isAntiAliasing = value; }
    public void enableAntiAliasing() { isAntiAliasing = true; }
    public void disableAntiAliasing() { isAntiAliasing = false; }

    public boolean isAntiAliasingText() { return isAntiAliasingText; }
    public void setAntiAliasingText(boolean antiAliasingText) { isAntiAliasingText = antiAliasingText; }
    public void enableAntiAliasingText() { isAntiAliasingText = true; }
    public void disableAntiAliasingText() { isAntiAliasingText = false; }

    public boolean isInterpolation() { return isInterpolation; }
    public void setInterpolation(boolean value) { this.isInterpolation = value; }
    public void enableInterpolation() { isInterpolation = true; }
    public void disableInterpolation() { isInterpolation = false; }
}
