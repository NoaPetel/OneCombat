package system.physics;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Objects;

import system.GameSystem;

public class Region {

	private int x;
	private int y;

	public Region(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Region region = (Region) o;
		return x == region.x && y == region.y;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

	public void render(Graphics g, float scale, float w) {

		float newWidth = (w) / scale;
		float newHeight = (w) / scale;
		
		float left = x*w;
		float top = y*w;

		if (!GameSystem.system.cameraManager.inView(left, top, newWidth, newHeight))
			return;

		float[] pos = GameSystem.system.cameraManager.getPosInView(x*w, y*w);

		g.setColor(Color.orange);
		g.drawRect((int) (pos[0]), (int) (pos[1]), (int) newWidth, (int) newHeight);
	}
}
