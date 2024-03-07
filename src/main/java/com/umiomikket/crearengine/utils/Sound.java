package com.umiomikket.crearengine.utils;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.InputStream;

public class Sound {
    private Clip clip;
    private FloatControl control;

    public Sound(String path) {
        clip = null;
        control = null;

        try {
            InputStream audioScr = Sound.class.getResourceAsStream(path);
            InputStream bufferedIn = new BufferedInputStream(audioScr);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(bufferedIn);
            AudioFormat baseFormat = audioInputStream.getFormat();

            AudioFormat decodeFormat = new AudioFormat(
                AudioFormat.Encoding.PCM_SIGNED,
                baseFormat.getSampleRate(),
                16,
                baseFormat.getChannels(),
                baseFormat.getChannels() * 2,
                baseFormat.getSampleRate(), false
            );

            AudioInputStream decodeAudioInputStream = AudioSystem.getAudioInputStream(decodeFormat, audioInputStream);
            clip = AudioSystem.getClip();
            clip.open(decodeAudioInputStream);

            control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() { play(0); }
    public void play(int startFrame) { play(startFrame, clip.getFrameLength() - 1); }

    public void play(int startFrame, int endFrame) {
        if (clip == null) return;
        clip.setLoopPoints(startFrame, endFrame);
        while (!clip.isRunning()) clip.start();
    }

    public void loop() { loop(0, clip.getFrameLength() - 1); }
    public void loop(int startFrame) { loop(startFrame, clip.getFrameLength()); }

    public void loop(int startFrame, int endFrame) {
        if (clip == null) return;
        clip.setLoopPoints(startFrame, endFrame);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        play();
    }

    public void stop() { if(clip.isRunning()) clip.stop(); }

    public void clear() { stop(); clip.drain(); clip.close(); }

    public boolean isRunning() { return clip.isActive(); }
    public void setVolume(int volume) { control.setValue(-volume); }
}
