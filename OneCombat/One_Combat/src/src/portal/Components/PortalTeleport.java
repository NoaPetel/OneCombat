package src.portal.Components;

import java.io.IOException;
import java.util.ArrayList;

import Character.Components.CharacterLocomotion;
import Interfaces.IDamagable;
import src.portal.Portal;
import system.Physics;
import system.Time;
import system.enumeration.Layer;
import system.object.component.monobehavior.MonoBehavior;
import system.object.component.renderer.SpriteRenderer;
import system.object.component.rigidbody.Rigidbody;
import system.object.component.transform.Transform;
import system.object.gameobject.GameObject;
import system.physics.FlatVector;

public class PortalTeleport extends MonoBehavior{
	
	private Portal portal = null;
	private int direction = 0;
	
	
	private boolean isTeleport = false;
	private float timer = 1f;
	private float teleport = 0;
	
	CharacterLocomotion characterLocomotion;
	SpriteRenderer spriteRenderer;
	
	public PortalTeleport(GameObject gameObject, Transform transform, Portal portal, int direction) throws IOException{
		super(gameObject, transform);
		
		this.portal = portal;
		this.direction = direction;
		
	}
	
	@Override
	public void start(){
		spriteRenderer = (SpriteRenderer) gameObject.getRenderer();
		
		if(direction == -1) {
			this.spriteRenderer.setIsFlip(true);
		}
		characterLocomotion = gameObject.getComponent(CharacterLocomotion.class);
	}
	
	@Override
	public void update() {
		
		if(isTeleport) {
			if(teleport > 0)
			{
				teleport -= Time.deltaTime;
				characterLocomotion.teleport();
			}
			else if(teleport <= 0) {
				isTeleport = false;
				characterLocomotion.idle();
				
				
		}}
			
		FlatVector position = transform.position;
		ArrayList<Rigidbody> rbs = Physics.OverlapPolygon(position, 0.25f, 0.25f, Layer.getDamagableLayers());
		if(rbs.size() > 0) {
			for(Rigidbody rb : rbs) {
				IDamagable damagable = rb.gameObject.getComponent(IDamagable.class);
				if(damagable != null) {
					if(portal.twin != null)
					{		
							if(!isTeleport){
								
								PortalTeleport p = portal.twin.getComponent(PortalTeleport.class);
								

								p.onTeleport();
								
								
								rb.gameObject.transform.setPositionX(portal.twin.transform.getPositionX());
								rb.gameObject.transform.setPositionY(portal.twin.transform.getPositionY());
								onTeleport();
								
							}
						}
					}
				}
			}
		

		
	}
	
	
	
	public void onTeleport() {
		isTeleport = true;
		teleport = this.timer;
	}
	
	
	
	
	
	
	
	
	
	
}













