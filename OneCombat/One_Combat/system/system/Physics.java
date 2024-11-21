package system;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import system.enumeration.Layer;
import system.enumeration.ShapeType;
import system.object.component.rigidbody.Rigidbody;
import system.physics.CollisionMatrice;
import system.physics.Collisions;
import system.physics.FlatAABB;
import system.physics.FlatManifold;
import system.physics.FlatMath;
import system.physics.FlatVector;
import system.physics.Region;

public class Physics {

	private int nbLayers;

	private FlatVector gravity;
	private static ArrayList<Rigidbody> bodyList;
	private ArrayList<FlatManifold> contactPairs;
	private FlatVector[] contactList;
	private FlatVector[] impulseList;
    private FlatVector[] raList;
    private FlatVector[] rbList;

	// Region
	private Map<Region, List<Rigidbody>> regionMap;
	private int regionSize;

	public int BodyCount() {
		return bodyList.size();
	}

	public Physics() {

		nbLayers = Layer.values().length;

		this.gravity = new FlatVector(0f, Constante.GRAVITY);
		this.bodyList = new ArrayList<Rigidbody>(); 
		this.contactPairs = new ArrayList<FlatManifold>();
		this.contactList = new FlatVector[2];
        this.impulseList = new FlatVector[2];
        this.raList = new FlatVector[2];
        this.rbList = new FlatVector[2];

		this.regionSize = Constante.REGIONSIZE;
		regionMap = new HashMap<>();
	}

	public void addRigidbody(Rigidbody rb, Layer layer) {
		bodyList.add(rb);
	}

	public void removeRigidbody(Rigidbody rb, Layer layer) {
		bodyList.remove(rb);
	}

	public void clearScene() {
		bodyList.clear();
		regionMap.clear();
	}

	public void update(int iterations) {
		float elapsed = Time.deltaTime;
		iterations = FlatMath.clamp(iterations, Constante.MINITERATIONS, Constante.MAXITERATIONS);

		for (int it = 0; it < iterations; it++) {

			contactPairs.clear();
			stepBodies(elapsed, iterations);
			broadPhase();
			narrowPhase();
		}
	}

	private void broadPhase() {
		// Réinitialiser les régions
		regionMap.clear();

		// Placer les objets dans les régions correspondantes
		for (Rigidbody rigidbody : bodyList) {
			// Calculer la région correspondante en fonction de la position de l'objet
			Region region = calculateRegion(rigidbody.position);

			// Ajouter l'objet à la liste des objets de la région correspondante
			List<Rigidbody> regionObjects = regionMap.computeIfAbsent(region, k -> new ArrayList<>());
			regionObjects.add(rigidbody);
		}

		// Vérifier les collisions entre les objets des régions voisines
		for (Region region : regionMap.keySet()) {
			List<Rigidbody> regionObjects = regionMap.get(region);

			// Vérifier les collisions avec les objets de la région courante
			for (int i = 0; i < regionObjects.size(); i++) {
				Rigidbody rb1 = regionObjects.get(i);
				for (int j = i + 1; j < regionObjects.size(); j++) {
					Rigidbody rb2 = regionObjects.get(j);
					checkCollision(rb1, rb2);
				}
			}

			// Vérifier les collisions avec les objets des régions voisines
			List<Region> neighborRegions = calculateNeighborRegions(region);
			for (Region neighborRegion : neighborRegions) {
				List<Rigidbody> neighborObjects = regionMap.get(neighborRegion);
				if (neighborObjects != null) {
					for (Rigidbody rb1 : regionObjects) {
						for (Rigidbody rb2 : neighborObjects) {
							checkCollision(rb1, rb2);
						}
					}
				}
			}
		}
	}

	private void checkCollision(Rigidbody bodyA, Rigidbody bodyB) {

		if (bodyA.isStatic && bodyB.isStatic)
			return;

		if (!CollisionMatrice.isCollide(bodyA.layer, bodyB.layer))
			return;

		FlatAABB bodyAaabb = bodyA.getAABB();
		FlatAABB bodyBaabb = bodyB.getAABB();
		FlatVector normal = FlatVector.Zero();

		if (!Collisions.IntersectAABBs(bodyAaabb, bodyBaabb))
			return;

		float depth = Collisions.Collide(bodyA, bodyB, normal);
		if (depth != 0) {

			separateBodies(bodyA, bodyB, FlatVector.times(normal, depth));

			FlatVector contact1 = FlatVector.Zero();
			FlatVector contact2 = FlatVector.Zero();
			int contactCount = Collisions.FindContactPoints(bodyA, bodyB, contact1, contact2);
			FlatManifold contact = new FlatManifold(bodyA, bodyB, normal, depth, contact1, contact2, contactCount);
			contactPairs.add(contact);
		}
	}

	private void narrowPhase() {
		for (int i = 0; i < contactPairs.size(); i++) {
			FlatManifold contact = contactPairs.get(i);
			ResolveCollisionBasic(contact);
		}
	}

	private void stepBodies(float elapsed, int iterations) {
		for (int i = 0; i < bodyList.size(); i++) {
			bodyList.get(i).update(elapsed / (float) iterations, gravity);
		}
	}

	private void separateBodies(Rigidbody bodyA, Rigidbody bodyB, FlatVector mtv) {

		if (bodyA.isStatic) {
			bodyB.move(mtv);
		}

		else if (bodyB.isStatic) {
			bodyA.move(FlatVector.unaryMinus(mtv));
		}
		
		else if(bodyA.freezePosition) {
			bodyB.move(mtv);
		}
		
		else if(bodyB.freezePosition) {
			bodyA.move(FlatVector.unaryMinus(mtv));
		}

		else {
			bodyA.move(FlatVector.times(FlatVector.unaryMinus(mtv), 1 / 2f));
			bodyB.move(FlatVector.times(mtv, 1 / 2f));
		}
	}

	private Region calculateRegion(FlatVector position) {
		int regionX = (int) Math.floor((position.x / regionSize));
		int regionY = (int) Math.floor((position.y / regionSize));
		return new Region(regionX, regionY);
	}

	private List<Region> calculateNeighborRegions(Region region) {
		List<Region> neighbors = new ArrayList<>();
		int regionX = region.getX();
		int regionY = region.getY();

		// Ajouter les régions voisines
		neighbors.add(new Region(regionX + 1, regionY));
		neighbors.add(new Region(regionX - 1, regionY));
		neighbors.add(new Region(regionX, regionY + 1));
		neighbors.add(new Region(regionX, regionY - 1));

		// Ajouter les régions en diagonale si nécessaire
		neighbors.add(new Region(regionX + 1, regionY + 1));
		neighbors.add(new Region(regionX + 1, regionY - 1));
		neighbors.add(new Region(regionX - 1, regionY + 1));
		neighbors.add(new Region(regionX - 1, regionY - 1));

		return neighbors;
	}

	public void render(Graphics g) {
		float scale = GameSystem.system.cameraManager.getScale();
		g.setColor(Color.green);

		for (int i = 0; i < BodyCount(); i++) {

			Rigidbody body = bodyList.get(i);

			float newWidth = (body.width) / scale;
			float newHeight = (body.height) / scale;

			float left = body.position.x - newWidth / 2;
			float top = body.position.y - newHeight / 2;

			if (!GameSystem.system.cameraManager.inView(left, top, newWidth, newHeight))
				continue;

			float[] pos = GameSystem.system.cameraManager.getPosInView(body.position.x, body.position.y);

			// g.fillRect((int)pos[0], (int)pos[1], (int)width, (int)height);

			Rectangle2D rect = new Rectangle2D.Double(0, 0, newWidth, newHeight);

			AffineTransform affineTransform = new AffineTransform();
			affineTransform.translate(pos[0] - newWidth / 2, pos[1] - newHeight / 2);
			affineTransform.rotate(body.rotation, newWidth / 2f, newHeight / 2f);
			// it's been while, you might have to perform the rotation and translate in the
			// opposite order

			Shape rotatedRect = affineTransform.createTransformedShape(rect);

			Graphics2D graphics = (Graphics2D) g; // get it from whatever you're drawing to

			graphics.draw(rotatedRect);
		}

		for (Entry<Region, List<Rigidbody>> entry : regionMap.entrySet()) {
			Region region = entry.getKey();
			region.render(g, scale, regionSize);
		}
	}

	public void ResolveCollisionBasic(FlatManifold contact) {

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

	public void ResolveCollisionWithRotation(FlatManifold contact) {
		Rigidbody bodyA = contact.bodyA;
		Rigidbody bodyB = contact.bodyB;
		FlatVector normal = contact.normal;
		FlatVector contact1 = contact.contact1;
		FlatVector contact2 = contact.contact2;
		int contactCount = contact.contactCount;

		float e = Math.min(bodyA.restitution, bodyB.restitution);

		this.contactList[0] = contact1;
		this.contactList[1] = contact2;

		for (int i = 0; i < contactCount; i++) {
			this.impulseList[i] = FlatVector.Zero();
			this.raList[i] = FlatVector.Zero();
			this.rbList[i] = FlatVector.Zero();
		}

		for (int i = 0; i < contactCount; i++) {
			FlatVector ra = FlatVector.minus(contactList[i], bodyA.position);
			FlatVector rb = FlatVector.minus(contactList[i], bodyB.position);
			raList[i] = ra;
			rbList[i] = rb;

			FlatVector raPerp = new FlatVector(-ra.y, ra.x);
			FlatVector rbPerp = new FlatVector(-rb.y, rb.x);

			FlatVector angularLinearVelocityA = FlatVector.times(raPerp, bodyA.rotationalVelocity);
			FlatVector angularLinearVelocityB = FlatVector.times(rbPerp, bodyB.rotationalVelocity);

			FlatVector relativeVelocity = FlatVector.minus(FlatVector.plus(bodyB.linearVelocity, angularLinearVelocityB)
					, (FlatVector.plus(bodyA.linearVelocity, angularLinearVelocityA)));

			float contactVelocityMag = FlatMath.dot(relativeVelocity, normal);

			if (contactVelocityMag > 0f) {
				continue;
			}

			float raPerpDotN = FlatMath.dot(raPerp, normal);
			float rbPerpDotN = FlatMath.dot(rbPerp, normal);

			float denom = bodyA.invMass + bodyB.invMass + (raPerpDotN * raPerpDotN) * bodyA.invInertia
					+ (rbPerpDotN * rbPerpDotN) * bodyB.invInertia;

			float j = -(1f + e) * contactVelocityMag;
			j /= denom;
			j /= (float) contactCount;

			FlatVector impulse = FlatVector.times(normal, j);
			impulseList[i] = impulse;
		}

		for (int i = 0; i < contactCount; i++) {
			FlatVector impulse = impulseList[i];
			FlatVector ra = raList[i];
			FlatVector rb = rbList[i];

			bodyA.linearVelocity = FlatVector.minus(bodyA.linearVelocity, FlatVector.times(impulse, bodyA.invMass));
			bodyA.rotationalVelocity += -FlatMath.cross(ra, impulse) * bodyA.invInertia;
			bodyB.linearVelocity = FlatVector.minus(bodyB.linearVelocity, FlatVector.times(impulse, bodyB.invMass));
			bodyB.rotationalVelocity += FlatMath.cross(rb, impulse) * bodyB.invInertia;
		}
	}

	public static ArrayList<Rigidbody> OverlapCircle(FlatVector position, float radius, Layer[] layers) {

		ArrayList<Rigidbody> rbs = new ArrayList<Rigidbody>();

		for (int i = 0; i < layers.length; i++) {
			for (int j = 0; j < bodyList.size(); j++) {
				Rigidbody body = bodyList.get(j);
				FlatVector normal = FlatVector.Zero();

				if (body.layer != layers[i])
					continue;

				float depth = 0f;

				if (body.shapeType == ShapeType.Box) {
					depth = Collisions.IntersectCirclePolygon(position, radius, body.getTransformVertices(),
							body.position, normal);
				} else {
					depth = Collisions.IntersectCircles(position, body.position, radius, body.radius, normal);
				}
				if (depth != 0) {
					rbs.add(body);
				}
			}
		}

		return rbs;
	}

	public static ArrayList<Rigidbody> OverlapCircle(FlatVector position, float radius, Layer layer) {

		ArrayList<Rigidbody> rbs = new ArrayList<Rigidbody>();

		for (int j = 0; j < bodyList.size(); j++) {
			Rigidbody body = bodyList.get(j);
			FlatVector normal = FlatVector.Zero();

			if (body.layer != layer)
				continue;

			float depth = 0f;

			if (body.shapeType == ShapeType.Box) {
				depth = Collisions.IntersectCirclePolygon(position, radius, body.getTransformVertices(), body.position,
						normal);
			} else {
				depth = Collisions.IntersectCircles(position, body.position, radius, body.radius, normal);
			}
			if (depth != 0) {
				rbs.add(body);
			}
		}

		return rbs;
	}

	public static ArrayList<Rigidbody> OverlapCircle(FlatVector position, float radius) {

		ArrayList<Rigidbody> rbs = new ArrayList<Rigidbody>();

		for (int j = 0; j < bodyList.size(); j++) {
			Rigidbody body = bodyList.get(j);
			FlatVector normal = FlatVector.Zero();

			float depth = 0f;

			if (body.shapeType == ShapeType.Box) {
				depth = Collisions.IntersectCirclePolygon(position, radius, body.getTransformVertices(), body.position,
						normal);
			} else {
				depth = Collisions.IntersectCircles(position, body.position, radius, body.radius, normal);
			}
			if (depth != 0) {
				rbs.add(body);
			}
		}
		return rbs;
	}

	public static ArrayList<Rigidbody> OverlapPolygon(FlatVector position, float width, float height, Layer[] layers) {

		ArrayList<Rigidbody> rbs = new ArrayList<Rigidbody>();
		FlatVector[] vertices = Rigidbody.CreateTransformVertices(position, width, height);

		for (int i = 0; i < layers.length; i++) {
			for (int j = 0; j < bodyList.size(); j++) {
				Rigidbody body = bodyList.get(j);
				FlatVector normal = FlatVector.Zero();

				if (body.layer != layers[i])
					continue;

				float depth = 0f;

				if (body.shapeType == ShapeType.Box) {
					depth = Collisions.IntersectPolygons(body.getTransformVertices(), vertices, body.position, position,
							normal);
				} else {
					depth = Collisions.IntersectCirclePolygon(body.position, body.radius, vertices, position, normal);
				}
				if (depth != 0) {
					rbs.add(body);
				}
			}
		}

		return rbs;
	}

	public static ArrayList<Rigidbody> OverlapPolygon(FlatVector position, float width, float height, Layer layer) {

		ArrayList<Rigidbody> rbs = new ArrayList<Rigidbody>();
		FlatVector[] vertices = Rigidbody.CreateTransformVertices(position, width, height);

		for (int j = 0; j < bodyList.size(); j++) {
			Rigidbody body = bodyList.get(j);
			FlatVector normal = FlatVector.Zero();

			if (body.layer != layer)
				continue;

			float depth = 0f;

			if (body.shapeType == ShapeType.Box) {
				depth = Collisions.IntersectPolygons(body.getTransformVertices(), vertices, body.position, position,
						normal);
			} else {
				depth = Collisions.IntersectCirclePolygon(body.position, body.radius, vertices, position, normal);
			}
			if (depth != 0) {
				rbs.add(body);
			}
		}

		return rbs;
	}
	
	public static ArrayList<Rigidbody> OverlapPolygon(FlatVector position, float width, float height, Layer layer, float rotation) {

		ArrayList<Rigidbody> rbs = new ArrayList<Rigidbody>();
		FlatVector[] vertices = Rigidbody.CreateTransformVertices(position, width, height, rotation);

		for (int j = 0; j < bodyList.size(); j++) {
			Rigidbody body = bodyList.get(j);
			FlatVector normal = FlatVector.Zero();

			if (body.layer != layer)
				continue;

			float depth = 0f;

			if (body.shapeType == ShapeType.Box) {
				depth = Collisions.IntersectPolygons(body.getTransformVertices(), vertices, body.position, position,
						normal);
			} else {
				depth = Collisions.IntersectCirclePolygon(body.position, body.radius, vertices, position, normal);
			}
			if (depth != 0) {
				rbs.add(body);
			}
		}

		return rbs;
	}

	public static ArrayList<Rigidbody> OverlapPolygon(FlatVector position, float width, float height) {

		ArrayList<Rigidbody> rbs = new ArrayList<Rigidbody>();
		FlatVector[] vertices = Rigidbody.CreateTransformVertices(position, width, height);

		for (int j = 0; j < bodyList.size(); j++) {
			Rigidbody body = bodyList.get(j);
			FlatVector normal = FlatVector.Zero();

			float depth = 0f;

			if (body.shapeType == ShapeType.Box) {
				depth = Collisions.IntersectPolygons(body.getTransformVertices(), vertices, body.position, position,
						normal);
			} else {
				depth = Collisions.IntersectCirclePolygon(body.position, body.radius, vertices, position, normal);
			}
			if (depth != 0) {
				rbs.add(body);
			}
		}

		return rbs;
	}
}
