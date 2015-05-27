package fr.donjon.sound;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.*;

/**
 * Testing code used to play music in the game
 * @author chwat
 *
 */

/*
 * Snippets to add to a given class:
 * import fr.donjon.sound.SoundLoop;
 * SoundLoop sound=new SoundLoop("Ressources/Sounds/Main Theme.aif");
 */
public class SoundLoop{

	
	public final static String CLIC = "Ressources/sounds/metalClick.wav";
	public final static String DOOR = "Ressources/sounds/doorClose_4.wav";
	public final static String DOOROPENS = "Ressources/sounds/metalLatch.wav";
	public final static String GOT = "Ressources/sounds/Main Theme.aif";
	public final static String STARWARS = "Ressources/Sounds/RPG Theme.wav";
	public final static String TELEPORT = "Ressources/sounds/shoot.wav";
	
	Clip audioClip;		//Le clip qui est créé à partir de l'audioFilePath fourni en paramètre dans le constructeur.
	
	/**
	 * this flag indicates whether the playback completes or not.
	 */
	boolean playCompleted;
	
	/**
	 * Creates a playable sound in the game.
	 * @param audioFilePath Path of the audio file from which we need to read.
	 */
	public SoundLoop(String audioFilePath) {
		File audioFile = new File(audioFilePath);

		try {
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

			AudioFormat format = audioStream.getFormat();

			DataLine.Info info = new DataLine.Info(Clip.class, format);

			audioClip = (Clip) AudioSystem.getLine(info);

			audioClip.open(audioStream);

		} catch (UnsupportedAudioFileException ex) {
			System.out.println("The specified audio file is not supported.");
			ex.printStackTrace();
		} catch (LineUnavailableException ex) {
			System.out.println("Audio line for playing back is unavailable.");
			ex.printStackTrace();
		} catch (IOException ex) {
			System.out.println("Error playing the audio file.");
			ex.printStackTrace();
		}

	}
	
	/**
	 * Permet de lancer le fichier son en lecture en boucle
	 */
	public void loop(){
		audioClip.loop(Clip.LOOP_CONTINUOUSLY);
		audioClip.start();
	}
	
	/**
	 * Joue une seule fois le son. 
	 * A chaque appel de cette methode, on rembobine le son et on le lance
	 */
	public void playOnce(){
		audioClip.setMicrosecondPosition(0);
		audioClip.start();
	}
	
	/**
	 * Permet d'interrompre la lecture d'un son et de le remettre à zéro
	 */
	public void stop(){
		audioClip.stop();
		audioClip.setMicrosecondPosition(0);
	}
}