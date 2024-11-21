package system.object.gameobject;

import java.awt.Graphics;
import java.util.ArrayList;

import system.Constante;
import system.GameSystem;
import system.enumeration.SortingLayer;
import system.object.IObject;
import system.object.component.Component;
import system.object.component.animation.Animator;
import system.object.component.monobehavior.MonoBehavior;
import system.object.component.renderer.IRenderer;
import system.object.component.rigidbody.Rigidbody;
import system.object.component.transform.Transform;

public class GameObject implements IObject, Comparable<GameObject> {

	public Transform transform;
	protected IRenderer renderer;
	protected Animator animator;
	protected Rigidbody rb;
	protected boolean isActive = true;
	protected String tag = "object";
	protected SortingLayer sortingLayer = SortingLayer.Default;
	protected ArrayList<MonoBehavior> components = new ArrayList<MonoBehavior>();

	public GameObject() {
		transform = new Transform(this);
		// components = components;
	}

	public void start() {
		for (MonoBehavior m : components) {
			m.start();
		}
	}

	public void update() {
		for (MonoBehavior m : components) {
			m.update();
		}
		if (animator != null)
			animator.update();
		// if(rb != null) rb.update();
	}

	@Override
	public void destroy() {
		GameSystem.system.sceneManager.getCurrentScene().removeGameObject(this);
		if (rb != null)
			if (rb != null)
				GameSystem.system.physics.removeRigidbody(rb, rb.layer);
	}

	public void render(Graphics g) {
		if (renderer != null)
			renderer.render(g);
		if (Constante.ISDEBUGMODE) {
			for (MonoBehavior m : components) {
				m.onDrawGizmos(g);
			}
		}
	}

	public void SetActive(boolean isActive) {
		this.isActive = isActive;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getComponent(Class<?> c) {
		T t = null;
		for (Component component : this.components) {
			try {
				if (c.isInstance(component)) {
					t = (T) component;
				}
			} catch (ClassCastException cce) {
			}
		}
		return t;
	}

	@Override
	public <T> boolean tryGetComponent(T t) {
		// t = getComponent(t);
		return t != null;
	}

	public Rigidbody getRigidbody() {
		return rb;
	}

	public IRenderer getRenderer() {
		return renderer;
	}

	public Animator getAnimator() {
		return animator;
	}

	public int getSortingLayer() {
		return sortingLayer.getValue();
	}

	@Override
	public int compareTo(GameObject g) {
		if (sortingLayer.getValue() > g.getSortingLayer()) {
			return 1;
		} else if (sortingLayer.getValue() == g.getSortingLayer()) {
			return 0;
		} else {
			return -1;
		}
	}

}
