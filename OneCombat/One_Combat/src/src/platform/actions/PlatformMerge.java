package src.platform.actions;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import src.manager.PlatformManager;
import system.Physics;
import system.Time;
import system.enumeration.Layer;
import system.object.component.monobehavior.MonoBehavior;
import system.object.component.rigidbody.Rigidbody;
import system.object.component.transform.Transform;
import system.object.gameobject.GameObject;

public class PlatformMerge extends MonoBehavior{
	
	private float collisionAreaDetectionX = 0.2f;
	private float collisionAreaDetectionY = 0.2f;
	private boolean isMerging = false;
	private boolean initialStatut = true;
	private float initialTimer = 0f;
	private float initialTime = 25f;
	private final static float MAX_WIDTH = 20f;
	private final static float MAX_HEIGHT = 5f;
	
	private float platformWidth;
	private float platformHeight;

	public PlatformMerge(GameObject gameObject, Transform transform) {
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
			this.mergeX();
			this.mergeY();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		if(this.initialStatut == true) {
			this.initialTimer += Time.deltaTime;
			if(this.initialTimer > this.initialTime) {
				this.initialStatut = false;
			}
		}
	}
	
	public void MergeOrHit() throws IOException {
		if(Math.random()*2 < 1) {
			mergeX();
			mergeY();
		}
	}
	
	public void mergeX() throws IOException {
		if(PlatformManager.instance.prioritizeMerge) {
			ArrayList<Rigidbody> listOfTriggeredEntitiesDown = Physics.OverlapPolygon(this.transform.position, this.collisionAreaDetectionX*2+this.platformWidth, this.platformHeight, Layer.Platform, transform.rotation);
			
			//Colision a gauche de la platform
			if(!listOfTriggeredEntitiesDown.isEmpty()) {
				Iterator<Rigidbody> platformToMergeIterator = listOfTriggeredEntitiesDown.iterator();
				while(platformToMergeIterator.hasNext()) {
					Rigidbody currentEntityRigidBody = platformToMergeIterator.next();
					if(currentEntityRigidBody == this.gameObject.getRigidbody()) {
						continue;	// Pour se cibler soi meme
					}
					PlatformMerge mergablePlatform = currentEntityRigidBody.gameObject.getComponent(PlatformMerge.class);
					if(mergablePlatform != null) {
						if((!mergablePlatform.isMerging && !this.isMerging) || !this.initialStatut) {
							float newWidth = this.platformWidth + currentEntityRigidBody.width; // On fusione la longueur
							float newHeight = Math.max(this.platformHeight, currentEntityRigidBody.height); //On prend le plus grand des deux
							if(newWidth <= MAX_WIDTH && newHeight <= MAX_HEIGHT) {
								mergablePlatform.isMerging = true;
								this.isMerging = true;
								this.gameObject.destroy();
								float newPosX = this.transform.getPositionX();
								float newPosY = this.transform.getPositionY();
								currentEntityRigidBody.destroy();
								PlatformManager.instance.generateRandomPlatformAtPos(newPosX, newPosY, newWidth, newHeight);
								PlatformManager.instance.currentActivePlatform--;
								break;
							}
						}
					}
				}
			}
		}
	}
	
	public void mergeY() throws IOException {
		if(PlatformManager.instance.prioritizeMerge) {
			ArrayList<Rigidbody> listOfTriggeredEntitiesDown = Physics.OverlapPolygon(this.transform.position, this.platformWidth, this.collisionAreaDetectionY*2+this.platformHeight, Layer.Platform, transform.rotation);
			
			//Colision en bas de la platform
			if(!listOfTriggeredEntitiesDown.isEmpty()) {
				Iterator<Rigidbody> platformToMergeIterator = listOfTriggeredEntitiesDown.iterator();
				while(platformToMergeIterator.hasNext()) {
					Rigidbody currentEntityRigidBody = platformToMergeIterator.next();
					if(currentEntityRigidBody == this.gameObject.getRigidbody()) {
						continue;	// Pour se cibler soi meme
					}
					PlatformMerge mergablePlatform = currentEntityRigidBody.gameObject.getComponent(PlatformMerge.class);
					//System.out.println("MergeY OK");
					if(mergablePlatform != null) {
						if((!mergablePlatform.isMerging && !this.isMerging) || !this.initialStatut) {
							float newHeight = this.platformHeight + currentEntityRigidBody.height;
							float newWidth = Math.max(this.platformWidth, currentEntityRigidBody.width);
							if(newHeight <= MAX_WIDTH && newWidth <= MAX_HEIGHT) {
								mergablePlatform.isMerging = true;
								this.isMerging = true;
								float newPosX = this.transform.getPositionX();
								float newPosY = this.transform.getPositionY();
								this.gameObject.destroy();
								currentEntityRigidBody.destroy();
								PlatformManager.instance.generateRandomPlatformAtPos(newPosX, newPosY, newWidth, newHeight);
								PlatformManager.instance.currentActivePlatform--;
								break;
							}
						}
					}
				}
			}
		}
	}
	
	public void onDrawGizmos(Graphics g) {
		// Debug X
		drawRectGizmos(g, this.transform.position, this.collisionAreaDetectionX*2+this.platformWidth, this.platformHeight, Color.red, transform.rotation);
				
		// Debug Y
		drawRectGizmos(g, this.transform.position, this.platformWidth, this.collisionAreaDetectionY*2+this.platformHeight, Color.red, transform.rotation);
	}
}
