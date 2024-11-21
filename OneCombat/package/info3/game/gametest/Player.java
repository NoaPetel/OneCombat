package info3.game.gametest;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player {
	float x = 10;
	float y = 10;
	float width = 10;
	float height = 10;
	float scale = 2;
	float speed = 0.5f;
	float inputX;
	float inputY;
	BufferedImage[] m_images;
	BufferedImage[] m_images_idle;
	BufferedImage[] m_images_run;
	BufferedImage[] m_images_attack;
	long m_imageElapsed;
	int m_imageIndex;
	int state = 0;
	boolean isFlip = false;

	Player() throws IOException {
		m_images_idle = loadSprite("resources/Ace/AceIdle.png", 1, 4);
		m_images_run = loadSprite("resources/Ace/AceRun.png", 1, 8);
		m_images_attack = loadSprite("resources/Ace/Ace(Air)LongAttack.png", 5, 6);
		m_images = m_images_idle;
	}

	void tick(long elapsed) {
		if(state != 2) {
			x += elapsed * speed * inputX;
			y += elapsed * speed * inputY;
		}
		
		if((inputX == 0 && state != 2) || (state == 2 && m_imageIndex == 27)) {
			if(state != 0) m_imageIndex = 0;
			state = 0;
			m_images = m_images_idle;
		}
		else if(state == 0) {
			if(state == 0) m_imageIndex = 0;
			state = 1;
			m_images = m_images_run;
		}
		
		isFlip = inputX < 0;

		m_imageElapsed += elapsed;
		if (m_imageElapsed > 100) {
			m_imageElapsed = 0;
			m_imageIndex = (m_imageIndex + 1) % m_images.length;
		}
	}

	public void setInputX(float iX) {
		this.inputX = iX;
	}

	public void setInputY(float iY) {
		this.inputY = iY;
	}
	
	public void Attack() {
		if(state == 2) return;
		
		m_images = m_images_attack;
		state = 2;
	}

	public void paint(Graphics g, int width, int height) {
		BufferedImage img = m_images[m_imageIndex];
		int flip = isFlip ? -1 : 1;
		g.drawImage(img, (int)x, (int)y, (int)scale * img.getWidth() * flip, (int)scale * img.getHeight(), null);
	}

	public static BufferedImage[] loadSprite(String filename, int nrows, int ncols) throws IOException {
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
