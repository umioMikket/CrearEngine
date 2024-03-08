package com.umiomikket.crearengine.managers;

import com.umiomikket.crearengine.GameBox;

import java.awt.event.*;

public class InputManager {
    public final GameBox gameBox;

    private boolean[] key = new boolean[256];
    private boolean[] button = new boolean[5];

    private int mouseX, mouseY, mouseScreenX, mouseScreenY;

    private int wheelScroll;

    private boolean isMouseInWindow;
    private boolean isWindowShown;

    public InputManager(GameBox gameBox) {
        this.gameBox = gameBox;

        gameBox.window.canvas.addKeyListener(new KeyboardEvents());
        gameBox.window.canvas.addMouseListener(new MouseEvents());
        gameBox.window.canvas.addMouseMotionListener(new MouseMovedEvents());
        gameBox.window.canvas.addMouseWheelListener(new MouseWheelEvents());

        gameBox.window.frame.addComponentListener(new ComponentEvents());
        gameBox.window.frame.addWindowListener(new WindowEvents());
    }

    public boolean isKey(int keyCode) {
        return key[keyCode];
    }

    public boolean isButton(int buttonCode) {
        return button[buttonCode];
    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

    public int getMouseScreenX() {
        return mouseScreenX;
    }

    public int getMouseScreenY() {
        return mouseScreenY;
    }

    public int getWheelScroll() {
        return wheelScroll;
    }

    public boolean isMouseInWindow() {
        return isMouseInWindow;
    }

    public boolean isWindowShown() {
        return isWindowShown;
    }

    private class KeyboardEvents implements KeyListener {
        public void keyTyped(KeyEvent e) {
        }

        public void keyPressed(KeyEvent e) {
            key[e.getKeyCode()] = true;
        }

        public void keyReleased(KeyEvent e) {
            key[e.getKeyCode()] = false;
        }
    }

    private class MouseEvents implements MouseListener {
        public void mouseClicked(MouseEvent e) {}
        public void mousePressed(MouseEvent e) { button[e.getButton()] = true; }
        public void mouseReleased(MouseEvent e) { button[e.getButton()] = false; }
        public void mouseEntered(MouseEvent e) { isMouseInWindow = true; }
        public void mouseExited(MouseEvent e) { isMouseInWindow = false; }
    }

    private class MouseMovedEvents implements MouseMotionListener {
        public void mouseDragged(MouseEvent e) { setPosition(e); }
        public void mouseMoved(MouseEvent e) { setPosition(e); }

        private void setPosition(MouseEvent e) {
            mouseX = e.getX();
            mouseY = e.getY();
            mouseScreenX = e.getXOnScreen();
            mouseScreenY = e.getYOnScreen();
        }
    }

    private class MouseWheelEvents implements MouseWheelListener {
        public void mouseWheelMoved(MouseWheelEvent e) {
            wheelScroll = 0;
            wheelScroll += e.getWheelRotation();
        }
    }

    private class ComponentEvents implements ComponentListener {
        public void componentResized(ComponentEvent e) {
            gameBox.renderManager.resize();
        }

        public void componentMoved(ComponentEvent e) {}

        public void componentShown(ComponentEvent e) { isWindowShown = true; }
        public void componentHidden(ComponentEvent e) { isWindowShown = false; }

        public void windowOpened(WindowEvent e) {}
    }

    private class WindowEvents extends WindowAdapter {
        public void windowClosing(WindowEvent e) { gameBox.stop(); }

        public void windowClosed(WindowEvent e) {}

        public void windowIconified(WindowEvent e) {}
        public void windowDeiconified(WindowEvent e) {}

        public void windowActivated(WindowEvent e) {}
        public void windowDeactivated(WindowEvent e) {}
    }
}
