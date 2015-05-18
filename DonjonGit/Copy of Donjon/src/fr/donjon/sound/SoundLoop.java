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

	/**
	 * this flag indicates whether the playback completes or not.
	 */
	boolean playCompleted;

	/**
	 * Play a given audio file.
	 * @param audioFilePath Path of the audio file.
	 */
	public SoundLoop(String FilePath) {
		File audioFile = new File(FilePath);

		try {
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

			AudioFormat format = audioStream.getFormat();

			DataLine.Info info = new DataLine.Info(Clip.class, format);

			Clip audioClip = (Clip) AudioSystem.getLine(info);

			audioClip.open(audioStream);

			audioClip.loop(Clip.LOOP_CONTINUOUSLY);

			audioClip.start();

			while (!playCompleted) {
				// wait for the playback to complete
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			}

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
}