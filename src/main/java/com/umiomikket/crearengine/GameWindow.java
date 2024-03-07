package com.umiomikket.crearengine;

import javax.swing.*;
import java.awt.*;

public class GameWindow {
    public final JFrame frame;
    public final Canvas canvas;

    public GameWindow() {
        frame = new JFrame("Game window");

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(screenSize.width / 4, screenSize.height / 4);
        frame.setSize(screenSize.width / 2, screenSize.height / 2);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        canvas = new Canvas();
        canvas.setSize(frame.getSize());
        canvas.setBackground(Color.BLACK);

        frame.getContentPane().add(canvas);
    }
}
