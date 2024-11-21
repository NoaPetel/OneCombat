package system.physics;

import java.util.ArrayList;

import system.Constante;
import system.object.component.rigidbody.Rigidbody;

public class FlatWorld {

	private FlatVector gravity;
	private ArrayList<Rigidbody> bodyList;
	private ArrayList<FlatManifold> contactList;
	
	//public ArrayList<FlatVector> contactPointsList; 

	public int BodyCount() {
		return bodyList.size();
	}

	public FlatWorld() {
		this.gravity = new FlatVector(0f, Constante.GRAVITY);
		this.bodyList = new ArrayList<Rigidbody>();
		this.contactList = new ArrayList<FlatManifold>();
		
//		this.contactPointsList = new ArrayList<FlatVector>();
	}

	public void addBody(Rigidbody body) {
		bodyList.add( body);
	}

	public void removeBody(Rigidbody body) {
		bodyList.remove(body);
	}

	public Rigidbody getBody(int index) {
		if(index < 0 || index >= BodyCount()) return null;
		return bodyList.get(index);
	}

	public void update(float elapsed, int iterations) {
		iterations = FlatMath.clamp(iterations, Constante.MINITERATIONS, Constante.MAXITERATIONS);
//		contactPointsList.clear();
		
		for(int it = 0; it < iterations; it++) {
			// MovementStep
			for (int i = 0; i < bodyList.size(); i++) {
				
				bodyList.get(i).update(elapsed/(float)iterations, gravity);
			}
			
			contactList.clear();
			// Collision
			for (int i = 0; i < bodyList.size() - 1; i++) {
				
				Rigidbody bodyA = bodyList.get(i);
				FlatAABB bodyAaabb = bodyA.getAABB();
				
				for (int j = i + 1; j < bodyList.size(); j++) {
					
					Rigidbody bodyB = bodyList.get(j);
					FlatAABB bodyBaabb = bodyB.getAABB();
					FlatVector normal = FlatVector.Zero();
					
					if (bodyA.isStatic && bodyB.isStatic)
						continue;
	
					if(!Collisions.IntersectAABBs(bodyAaabb, bodyBaabb)) 
						continue;
					
					float depth = Collisions.Collide(bodyA, bodyB, normal);
					
					if (depth != 0) {
	
						if (bodyA.isStatic) {
							bodyB.move(FlatVector.times(normal, depth));
						}
	
						else if (bodyB.isStatic) {
							bodyA.move(FlatVector.times(FlatVector.unaryMinus(normal), depth));
						}
	
						else {
							bodyA.move(FlatVector.times(FlatVector.unaryMinus(normal), depth / 2f));
							bodyB.move(FlatVector.times(normal, depth / 2f));
						}
						
						FlatVector contact1 = FlatVector.Zero();
						FlatVector contact2 = FlatVector.Zero();
						int contactCount = Collisions.FindContactPoints(bodyA, bodyB, contact1, contact2);
						FlatManifold contact = new FlatManifold(bodyA, bodyB, normal, depth, contact1,contact2, contactCount);
						contactList.add(contact);
					}
				}
			}
			for(int i = 0; i < contactList.size(); i++) {
				FlatManifold contact = contactList.get(i);
				ResolveCollision(contact);
				
//				if(contact.contactCount > 0) {
//					if(!contactPointsList.contains(contact.contact1))
//						contactPointsList.add(contact.contact1);
//					
//					if(contact.contactCount > 1) {
//						if(!contactPointsList.contains(contact.contact2))
//							contactPointsList.add(contact.contact2);
//					}
//				}
			}
		}
	}

	public void ResolveCollision(FlatManifold contact) {
		
		Rigidbody bodyA = contact.bodyA;
		Rigidbody bodyB = contact.bodyB;
		FlatVector normal = contact.normal;
		float depth = contact.depth;

		FlatVector relativeVelocity = FlatVector.minus(bodyB.linearVelocity, bodyA.linearVelocity);

		if (FlatMath.dot(relativeVelocity, normal) > 0f)
			return;

		float e = Math.min(bodyA.restitution, bodyB.restitution);
		float j = -(1f + e) * FlatMath.dot(relativeVelocity, normal);
		j /= bodyA.invMass + bodyB.invMass;

		FlatVector impulse = FlatVector.times(normal, j);

		bodyA.linearVelocity = FlatVector.minus(bodyA.linearVelocity, FlatVector.times(impulse, bodyA.invMass));
		bodyB.linearVelocity = FlatVector.plus(bodyB.linearVelocity, FlatVector.times(impulse, bodyB.invMass));
	}
}
