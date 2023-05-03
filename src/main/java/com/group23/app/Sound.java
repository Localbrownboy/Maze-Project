package com.group23.app;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * Class that manages game sounds
 */
public class Sound {

    Clip clip;
    Clip clip_bgm;
    URL soundURL[] = new URL [30];

    /**
     * constructor for sound class , loads the sound clips into the url array
     */
    public Sound(){
        soundURL[0] = getClass().getResource("/sound/big_giant_circles.wav");
        soundURL[1] = getClass().getResource("/sound/fire_spell.wav");
        soundURL[2] = getClass().getResource("/sound/ice_spell.wav");
        soundURL[3] = getClass().getResource("/sound/key_pickup.wav");
        soundURL[4] = getClass().getResource("/sound/attacked.wav");
    }

    /**
     * Sets the files for the correct sound effect.
     * @param i is the index for sound file that we want.
     */
    public void setFile(int i){

        // Sets the song here.
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        }

        catch(Exception e){

        }

    }

    /**
     * Sets the background song.
     * @param i is the index for sound file that we want.
     */
    public void setBgmFile(int i){

        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip_bgm = AudioSystem.getClip();
            clip_bgm.open(ais);
        }
        catch(Exception e){

        }

    }

    /**
     * Function to play the song.
     */
    public void play() {

        clip.start();

    }

    /**
     * Function to play the background music.
     */
    public void playBgm() {

        clip_bgm.start();

    }


    /**
     *  Function to loop the song.
     */
    public void loopBgm(){

        clip_bgm.loop(Clip.LOOP_CONTINUOUSLY);

    }


    /**
     * Function to stop the song.
     */
    public void stop(){
        System.out.print("Stop Music Ran in actual method");

        clip.stop();
        
    }

        /**
     * Function to stop the song.
     */
    public void stopBgm(){
        System.out.print("Stop Music Ran in actual method");

        clip_bgm.stop();
        
    }
}
