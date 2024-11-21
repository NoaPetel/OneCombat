package system;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import info3.game.graphics.GameCanvasListener;

public class Listener implements GameCanvasListener {
	
	private GameSystem gameSystem;
	private InputManager inputManager;
	

	public Listener (GameSystem gs, InputManager im) {
		gameSystem = gs;
		inputManager = im;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		inputManager.mouseClicked(e);
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		inputManager.mousePressed(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		inputManager.mouseReleased(e);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		inputManager.mouseEntered(e);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		inputManager.mouseExited(e);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		inputManager.mouseDragged(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		inputManager.mouseMoved(e);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		inputManager.keyTyped(e);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		inputManager.keyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		inputManager.keyReleased(e);
	}

	@Override
	public void windowOpened() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tick(long elapsed) {
		gameSystem.update(elapsed);
	}

	@Override
	public void paint(Graphics g) {
		gameSystem.render(g);
	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void endOfPlay(String name) {
		if(GameSystem.system != null)
			GameSystem.system.audioManager.endOfPlay(name);
	}

	@Override
	public void expired() {
		if(GameSystem.system != null)
			GameSystem.system.audioManager.expired();
	}

}