package src.platform.actions;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import system.Physics;
import system.enumeration.Layer;
import system.object.component.monobehavior.MonoBehavior;
import system.object.component.rigidbody.Rigidbody;
import system.object.component.transform.Transform;
import system.object.gameobject.GameObject;
import system.physics.FlatVector;

public class PlatformBump extends MonoBehavior {

	private float collisionAreaDetectionX = 0.3f;
	private float collisionAreaDetectionY = 0.3f;
	private boolean isBumping = false;

	private float platformWidth;
	private float platformHeight;

	public PlatformBump(GameObject gameObject, Transform transform) {
		super(gameObject, transform);
	}

	@Override
	public void start() {
		this.platformWidth = gameObject.getRigidbody().width;
		this.platformHeight = gameObject.getRigidbody().height;
	}

	@Override
	public void update() {
		try {
			this.bumpX();
			this.bumpY();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void bumpX() throws IOException {
		
		FlatVector newPostionX = FlatVector.plus(this.transform.position, new FlatVector((float) (this.platformWidth*0.5), 0));
		ArrayList<Rigidbody> listOfTriggeredEntitiesDown = Physics.OverlapPolygon(newPostionX,
				this.collisionAreaDetectionX, this.platformHeight, Layer.Character, transform.rotation);

		// Colision a gauche de la platform
		if (!listOfTriggeredEntitiesDown.isEmpty()) {
			Iterator<Rigidbody> platformToBumpIterator = listOfTriggeredEntitiesDown.iterator();
			while (platformToBumpIterator.hasNext()) {
				Rigidbody currentEntityRigidBody = platformToBumpIterator.next();
				if (currentEntityRigidBody == this.gameObject.getRigidbody()) {
					continue; // Pour se cibler soi meme
				}
				PlatformMove bumpablePlatform = currentEntityRigidBody.gameObject.getComponent(PlatformMove.class);
				if (bumpablePlatform != null) {
					bumpablePlatform.reverseMouvement();
					break;
				}
			}
		}
	}

	public void bumpY() throws IOException {
		FlatVector newPostionY = FlatVector.plus(this.transform.position, new FlatVector(0, (float) (this.platformHeight*0.5)));
		ArrayList<Rigidbody> listOfTriggeredEntitiesDown = Physics.OverlapPolygon(newPostionY, this.platformWidth, this.collisionAreaDetectionY, Layer.Character, transform.rotation);

		// Colision en bas de la platform
		if (!listOfTriggeredEntitiesDown.isEmpty()) {
			Iterator<Rigidbody> platformToBumpIterator = listOfTriggeredEntitiesDown.iterator();
			while (platformToBumpIterator.hasNext()) {
				Rigidbody currentEntityRigidBody = platformToBumpIterator.next();
				if (currentEntityRigidBody == this.gameObject.getRigidbody()) {
					continue; // Pour se cibler soi meme
				}
				PlatformMove bumpablePlatform = currentEntityRigidBody.gameObject.getComponent(PlatformMove.class);
				if (bumpablePlatform != null) {
					bumpablePlatform.reverseMouvement();
					break;
				}
			}
		}
	}

	public void onDrawGizmos(Graphics g) {
		// Debug X
		FlatVector newPostionX = FlatVector.plus(this.transform.position, new FlatVector((float) (this.platformWidth*0.5), 0));
		drawRectGizmos(g, newPostionX, this.collisionAreaDetectionX, this.platformHeight, Color.red, transform.rotation);

		// Debug Y
		FlatVector newPostionY = FlatVector.plus(this.transform.position, new FlatVector(0, (float) (this.platformHeight*0.5)));
		drawRectGizmos(g, newPostionY, this.platformWidth, this.collisionAreaDetectionY, Color.red, transform.rotation);
	}
}
