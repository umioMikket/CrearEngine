package com.umiomikket.crearengine.listeners;

import com.umiomikket.crearengine.GameBox;

public abstract class UpdateListener {
    public abstract void update(GameBox gameBox, int updateNumber, boolean endUpdate);
}
