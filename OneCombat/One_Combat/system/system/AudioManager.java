package system;

import java.io.RandomAccessFile;

import info3.game.sound.RandomFileInputStream;

public class AudioManager {

	private int musicIndex = 0;
	private String[] musicNames = new String[] { "ressources/Music/Music1.ogg" };
	String musicName;
	boolean expired;

	public AudioManager() {
		loadMusic();
	}

	public void loadMusic() {
		musicName = musicNames[musicIndex];
		musicIndex = (musicIndex + 1) % musicNames.length;
		playMusic(musicName);
	}

	public void endOfPlay(String name) {
		if (expired) // only reload if it was a forced reload by timer
			loadMusic();
		expired = false;
	}

	public void expired() {
		expired = true;
		loadMusic();
	}

	public static void playMusic(String path) {
		try {
			RandomAccessFile file = new RandomAccessFile(path, "r");
			RandomFileInputStream fis = new RandomFileInputStream(file);
			GameSystem.system.canvas.playMusic(fis, 0, Constante.MUSICVOLUME);
		} catch (Throwable th) {
			th.printStackTrace(System.err);
		}
	}

	public static void playSound(String name, String path, float volume) {
		try {
			RandomAccessFile file = new RandomAccessFile(path, "r");
			RandomFileInputStream fis = new RandomFileInputStream(file);
			GameSystem.system.canvas.playSound(name, fis, 0, volume);
		} catch (Throwable th) {
			th.printStackTrace(System.err);
		}
	}

	public static void playSound(String name, String path) {
		try {
			RandomAccessFile file = new RandomAccessFile(path, "r");
			RandomFileInputStream fis = new RandomFileInputStream(file);
			GameSystem.system.canvas.playSound(name, fis, 0, Constante.SOUNDVOLUME);
		} catch (Throwable th) {
			th.printStackTrace(System.err);
		}
	}

}
