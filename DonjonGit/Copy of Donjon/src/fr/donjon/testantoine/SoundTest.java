package fr.donjon.testantoine;

import java.io.*;
import javax.sound.sampled.*;

public class SoundTest{
	
	public static Clip clip;
	
	 public static void main(String[] args) {
		 try
		    {
		        clip = AudioSystem.getClip();
		        clip.open(AudioSystem.getAudioInputStream(new File("Ressources/Sounds/RPG Theme.wav")));
		    }
		    catch (Exception exc)
		    {
		        exc.printStackTrace(System.out);
		    }
		 clip.setFramePosition(0);
         clip.loop(0);
         clip.start();
	   }
	
}