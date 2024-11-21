package system;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class InputManager {
	
	public static ArrayList<Integer> keysEvent = new ArrayList<Integer>();
	
	public static boolean IsPressed(int key) {return keysEvent.contains((Integer)key);}
	public static boolean isMousePressed;
	public static float mouseX;
	public static float mouseY;
	
	public void mouseClicked(MouseEvent e) {
//		System.out.println("Mouse clicked: (" + e.getX() + "," + e.getY() + ")");
//		System.out.println("   modifiers=" + e.getModifiersEx());
//		System.out.println("   buttons=" + e.getButton());
		isMousePressed = false;
	}


	public void mousePressed(MouseEvent e) {
//		System.out.println("Mouse pressed: (" + e.getX() + "," + e.getY() + ")");
//		System.out.println("   modifiers=" + e.getModifiersEx());
//		System.out.println("   buttons=" + e.getButton());
		isMousePressed = true;
	}


	public void mouseReleased(MouseEvent e) {
//		System.out.println("Mouse released: (" + e.getX() + "," + e.getY() + ")");
//		System.out.println("   modifiers=" + e.getModifiersEx());
//		System.out.println("   buttons=" + e.getButton());
		isMousePressed = false;
	}


	public void mouseEntered(MouseEvent e) {
//		System.out.println("Mouse entered: (" + e.getX() + "," + e.getY() + ")");
//		System.out.println("   modifiers=" + e.getModifiersEx());
//		System.out.println("   buttons=" + e.getButton());
	}


	public void mouseExited(MouseEvent e) {
//		System.out.println("Mouse exited: (" + e.getX() + "," + e.getY() + ")");
//		System.out.println("   modifiers=" + e.getModifiersEx());
//		System.out.println("   buttons=" + e.getButton());
	}


	public void mouseDragged(MouseEvent e) {
//		System.out.println("Mouse dragged: (" + e.getX() + "," + e.getY() + ")");
//		System.out.println("   modifiers=" + e.getModifiersEx());
//		System.out.println("   buttons=" + e.getButton());
	}


	public void mouseMoved(MouseEvent e) {
//		System.out.println("Mouse moved: (" + e.getX() + "," + e.getY() + ")");
//		System.out.println("   modifiers=" + e.getModifiersEx());
//		System.out.println("   buttons=" + e.getButton());
		mouseX = e.getX();
		mouseY = e.getY();
	}


	public void keyTyped(KeyEvent e) {
//		System.out.println("Key typed: " + e.getKeyChar() + " code=" + e.getKeyCode());
		if(!keysEvent.contains((Integer)e.getKeyCode())) keysEvent.add(e.getKeyCode());
	}


	public void keyPressed(KeyEvent e) {
//		System.out.println("Key pressed: " + e.getKeyChar() + " code=" + e.getKeyCode());
		if(!keysEvent.contains((Integer)e.getKeyCode())) keysEvent.add(e.getKeyCode());
//		System.out.println(keysEvent);
	}


	public void keyReleased(KeyEvent e) {
//		System.out.println("Key released: " + e.getKeyChar() + " code=" + e.getKeyCode());
		keysEvent.remove((Integer)e.getKeyCode());
	}
}
