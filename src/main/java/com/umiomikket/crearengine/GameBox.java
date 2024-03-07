package com.umiomikket.crearengine;

import com.umiomikket.crearengine.listeners.ExitListener;
import com.umiomikket.crearengine.listeners.RenderListener;
import com.umiomikket.crearengine.listeners.RunListener;
import com.umiomikket.crearengine.listeners.UpdateListener;
import com.umiomikket.crearengine.managers.*;
import com.umiomikket.crearengine.utils.Logger;

import java.util.ArrayList;

public class GameBox {
    private final ArrayList<UpdateListener> updateListeners;
    private final ArrayList<RenderListener> renderListeners;
    private final ArrayList<RunListener> runListeners;
    private final ArrayList<ExitListener> exitListeners;

    public final InputManager inputManager;
    public final RenderManager renderManager;
    public final WindowManager windowManager;
    public final GameUpdateLoop updateLoop;
    public final GameRenderLoop renderLoop;
    public final GameWindow window;

    public GameBox() {
        window = new GameWindow();

        Logger.info("Hello CrearEngine! Version: [0.6R]");

        updateListeners = new ArrayList<>();
        renderListeners = new ArrayList<>();
        runListeners = new ArrayList<>();
        exitListeners = new ArrayList<>();

        inputManager = new InputManager(this);
        updateLoop = new GameUpdateLoop(this);
        renderManager = new RenderManager(this);
        windowManager = new WindowManager(this);
        renderLoop = new GameRenderLoop(this);
    }

    public void run() {
        window.frame.setVisible(true);

        if (!renderLoop.isRunning()) runRenderLoop();
        if (!updateLoop.isRunning() && haveUpdateListeners()) runUpdateLoop();
        playRunListeners();
    }

    public void stop() {
        playExitListeners();
        if (renderLoop.isRunning()) renderLoop.stop();
        if (updateLoop.isRunning()) updateLoop.stop();
        window.frame.setVisible(false);
    }

    public void runUpdateLoop() { updateLoop.start(); }
    public void runRenderLoop() { renderLoop.start(); }
    public void stopUpdateLoop() { updateLoop.start(); }
    public void stopRenderLoop() { renderLoop.start(); }

    public boolean haveUpdateListeners() { return updateListeners.size() != 0; }
    public boolean haveRenderListeners() { return renderListeners.size() != 0; }
    public boolean haveRunListeners() { return runListeners.size() != 0; }
    public boolean haveExitListeners() { return exitListeners.size() != 0; }

    public void addUpdateListener(UpdateListener updateListener) {
        updateListeners.add(updateListener);
    }

    public void addRenderListener(RenderListener renderListener) {
        renderListeners.add(renderListener);
    }

    public void addURunListener(RunListener runListener) {
        runListeners.add(runListener);
    }

    public void addExitListener(ExitListener exitListener) {
        exitListeners.add(exitListener);
    }

    public void playUpdateListeners(int frameNumber, boolean endFrame) {
        if (!haveUpdateListeners()) return;
        updateListeners.forEach(ul -> ul.update(this, frameNumber, endFrame));
    }

    public void playRenderListeners(int frameNumber, boolean endFrame) {
        if (!haveRenderListeners()) return;
        renderListeners.forEach(rl -> rl.render(this, frameNumber, endFrame));
    }

    public void playRunListeners() {
        if (!haveRenderListeners()) return;
        runListeners.forEach(rl -> rl.run(this));
    }

    public void playExitListeners() {
        if (!haveExitListeners()) return;
        exitListeners.forEach(el -> el.exit(this));
    }
}
