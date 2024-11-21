package system;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import system.object.component.animation.Animation;

public class AnimationManager {

	private String entityPath = "ressources/entity-animations.txt";
	private String path = "ressources/";
	public HashMap<String, HashMap<String, Animation>> animationsDatabase = new HashMap<String, HashMap<String, Animation>>();

	public AnimationManager() throws IOException {
		BufferedReader lecteurAvecBuffer = null;
		String ligne;

		try {
			lecteurAvecBuffer = new BufferedReader(new FileReader(entityPath));
		} catch (FileNotFoundException exc) {
			System.out.println("Erreur d'ouverture");
		}
		while ((ligne = lecteurAvecBuffer.readLine()) != null) {
			System.out.println(ligne);
			
			String[] ligneInformations = ligne.split(";");
			String animationEntityName = ligneInformations[0];
			String animationName = ligneInformations[1];
			String animationPath = path + animationEntityName + "/" + animationName + ".png";
			int row = Integer.parseInt(ligneInformations[2]);
			int column = Integer.parseInt(ligneInformations[3]);
			int pivotX = Integer.parseInt(ligneInformations[4]);
			int pivotY = Integer.parseInt(ligneInformations[5]);
			int size = row * column;
			float[] frameTimers = new float[size];
			
			for(int i = 0; i < size; i++) {
				float time = (float)Integer.parseInt(ligneInformations[i+6]) / 1000f;
				frameTimers[i] = time;
			}
			boolean isLooping = ligneInformations[size + 6].compareTo("loop") == 0;
			BufferedImage[] sprites = loadSprites(animationPath, row, column);		
			
			if(animationsDatabase.get(animationEntityName) == null) {
				animationsDatabase.put(animationEntityName, new HashMap<String, Animation>());
			}
			animationsDatabase.get(animationEntityName).put(animationName, new Animation(sprites, frameTimers, animationName, isLooping, pivotX, pivotY));
		}
		lecteurAvecBuffer.close();
	}

	public HashMap<String, Animation> getAnimations(String animationsName){
		return animationsDatabase.get(animationsName);
	}
	
	public static BufferedImage[] loadSprites(String filename, int nrows, int ncols) throws IOException {
		File imageFile = new File(filename);
		if (imageFile.exists()) {
			BufferedImage image = ImageIO.read(imageFile);
			int width = image.getWidth(null) / ncols;
			int height = image.getHeight(null) / nrows;

			BufferedImage[] images = new BufferedImage[nrows * ncols];
			for (int i = 0; i < nrows; i++) {
				for (int j = 0; j < ncols; j++) {
					int x = j * width;
					int y = i * height;
					images[(i * ncols) + j] = image.getSubimage(x, y, width, height);
				}
			}
			return images;
		}
		return null;
	}

}
