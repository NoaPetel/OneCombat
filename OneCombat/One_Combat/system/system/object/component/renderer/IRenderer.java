package system.object.component.renderer;

import java.awt.Color;
import java.awt.Graphics;

public interface IRenderer {
	
	public void setColor(Color color);
	public void render(Graphics g);
}
