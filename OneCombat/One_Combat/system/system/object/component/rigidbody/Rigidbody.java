package system.object.component.rigidbody;

import system.Constante;
import system.GameSystem;
import system.enumeration.Layer;
import system.enumeration.ShapeType;
import system.object.component.Component;
import system.object.component.transform.Transform;
import system.object.gameobject.GameObject;
import system.physics.FlatAABB;
import system.physics.FlatMath;
import system.physics.FlatTransform;
import system.physics.FlatVector;
import system.physics.IBody;

public class Rigidbody extends Component implements IBody {

	public FlatVector position;
	public FlatVector linearVelocity;
	public float rotation;
	public float rotationalVelocity;
	private FlatVector force;

	public ShapeType shapeType;
	public float gravityScale;
	public float density;
	public float mass;
	public float restitution;
	public float area;
	public float invMass;
	public float inertia;
	public float invInertia;
	public boolean isStatic;
	public Layer layer;
	public float radius;
	public float width;
	public float height;

	public boolean freezePosition;
	public boolean freezeRotation;

	private FlatVector[] vertices;
	// public int[] triangles;
	private FlatVector[] transformVertices;
	private FlatAABB aabb;

	private boolean transformUpdateRequired;
	private boolean aabbUpdateRequired;

	private Rigidbody(float gravityScale, float density, float mass, float inertia, float restitution, float area,
			boolean isStatic, float radius, float width, float height, ShapeType shapeType, Layer layer, GameObject g,
			Transform t) {
		super(g, t);

		this.position = transform != null ? transform.position : FlatVector.Zero();
		this.linearVelocity = FlatVector.Zero();
		this.rotation = transform != null ? transform.rotation : 0f;
		this.rotationalVelocity = 0;
		this.force = FlatVector.Zero();

		this.gravityScale = gravityScale;
		this.density = density;
		this.mass = mass;
		this.restitution = restitution;
		this.area = area;

		this.isStatic = isStatic;
		this.layer = layer;

		this.radius = radius;
		this.width = width;
		this.height = height;
		this.shapeType = shapeType;
		this.inertia = inertia;

		if (!this.isStatic) {
			this.invMass = 1f / this.mass;
			this.invInertia = 1f / this.inertia;
		} else {
			this.invMass = 0f;
			this.invInertia = 0f;
		}

		if (this.shapeType == ShapeType.Box) {
			this.vertices = CreateBoxVertices(this.width, this.height);
			// this.triangles = CreateBoxTriangles();
			this.transformVertices = new FlatVector[this.vertices.length];
		} else {
			this.vertices = null;
			// this.triangles = null;
			this.transformVertices = null;
		}

		this.transformUpdateRequired = true;
		this.aabbUpdateRequired = true;

		GameSystem.system.physics.addRigidbody(this, layer);
	}

	private static FlatVector[] CreateBoxVertices(float width, float height) {
		float left = -width / 2;
		float right = left + width;
		float bottom = -height / 2;
		float top = bottom + height;

		FlatVector[] vertices = new FlatVector[4];
		vertices[0] = new FlatVector(left, top);
		vertices[1] = new FlatVector(right, top);
		vertices[2] = new FlatVector(right, bottom);
		vertices[3] = new FlatVector(left, bottom);
		return vertices;
	}

	private static int[] CreateBoxTriangles() {
		int[] triangles = new int[6];
		triangles[0] = 0;
		triangles[1] = 1;
		triangles[2] = 2;
		triangles[3] = 0;
		triangles[4] = 2;
		triangles[5] = 3;
		return triangles;
	}

	public static FlatVector[] CreateTransformVertices(FlatVector position, float width, float height) {

		FlatVector[] vertices = CreateBoxVertices(width, height);
		FlatTransform transform = new FlatTransform(position, 0f);

		for (int i = 0; i < vertices.length; i++) {
			FlatVector v = vertices[i];
			vertices[i] = FlatVector.Transform(v, transform);
		}
		return vertices;
	}

	public static FlatVector[] CreateTransformVertices(FlatVector position, float width, float height, float rotation) {

		FlatVector[] vertices = CreateBoxVertices(width, height);
		FlatTransform transform = new FlatTransform(position, rotation);

		for (int i = 0; i < vertices.length; i++) {
			FlatVector v = vertices[i];
			vertices[i] = FlatVector.Transform(v, transform);
		}
		return vertices;
	}

	public FlatVector[] getTransformVertices() {
		if (this.transformUpdateRequired) {
			FlatTransform transform = new FlatTransform(this.position, this.rotation);

			for (int i = 0; i < this.vertices.length; i++) {
				FlatVector v = this.vertices[i];
				this.transformVertices[i] = FlatVector.Transform(v, transform);
			}
		}
		this.transformUpdateRequired = false;
		return this.transformVertices;
	}

	public FlatAABB getAABB() {

		if (this.aabbUpdateRequired) {
			float minX = Float.MAX_VALUE;
			float minY = Float.MAX_VALUE;
			float maxX = Float.MIN_NORMAL;
			float maxY = Float.MIN_NORMAL;

			if (this.shapeType == ShapeType.Box) {

				FlatVector[] vertices = getTransformVertices();

				for (int i = 0; i < vertices.length; i++) {

					FlatVector v = vertices[i];

					if (v.x < minX)
						minX = v.x;
					if (v.y < minY)
						minY = v.y;
					if (v.x > maxX)
						maxX = v.x;
					if (v.y > maxY)
						maxY = v.y;

				}

			} else {

				minX = this.position.x - this.radius;
				minY = this.position.y - this.radius;
				maxX = this.position.x + this.radius;
				maxY = this.position.y + this.radius;

			}
			this.aabb = new FlatAABB(minX, minY, maxX, maxY);
		}

		this.aabbUpdateRequired = false;
		return aabb;
	}

	public void move(FlatVector amount) {
		position.x += amount.x;
		position.y += amount.y;
		transform.position = position;
		this.transformUpdateRequired = true;
		this.aabbUpdateRequired = true;
	}

	public void moveTo(FlatVector amount) {
		position = amount;
		transform.position = position;
		this.transformUpdateRequired = true;
		this.aabbUpdateRequired = true;
	}

	public void rotate(float amount) {
		rotation += amount;
		transform.rotation = rotation;
		this.transformUpdateRequired = true;
		this.aabbUpdateRequired = true;
	}

	public void rotateTo(float amount) {
		rotation = amount;
		transform.rotation = rotation;
		this.transformUpdateRequired = true;
		this.aabbUpdateRequired = true;
	}

	public void addForce(FlatVector amount) {
		force.x += amount.x;
		force.y += amount.y;
	}

	public void addRotationalForce(float amount) {
		this.rotationalVelocity += amount;
	}

	public void update(float elapsed, FlatVector gravity) {

		// force = mass * acc
		// acc = force / mass

		if (isStatic)
			return;

		position = transform.position;

		if (!freezePosition) {
			FlatVector acceleration = FlatVector.div(force, mass);
			linearVelocity = FlatVector.plus(linearVelocity, FlatVector.times(acceleration, elapsed));

			linearVelocity = FlatVector.plus(linearVelocity, FlatVector.times(gravity, elapsed * gravityScale));
			position = FlatVector.plus(position, FlatVector.times(linearVelocity, elapsed));
		}

		if (!freezeRotation) {
			rotation += rotationalVelocity * elapsed;
		}

		force = FlatVector.Zero();
		transform.position = position;
		transform.rotation = rotation;
		this.transformUpdateRequired = true;
		this.aabbUpdateRequired = true;
	}

	public static boolean CanCreateCircleBody(float radius, float density) {

		float area = radius * radius * (float) Math.PI;
		return area >= Constante.MINBODYSIZE && area <= Constante.MAXBODYSIZE && density >= Constante.MINDENSITY
				&& density <= Constante.MAXDENSITY;
	}

	public static Rigidbody CreateCircleBody(float gravityScale, float radius, float density, boolean isStatic,
			float restitution, Layer layer, GameObject g, Transform t) {

		float area = radius * radius * (float) Math.PI;
		restitution = FlatMath.clamp(restitution, 0f, 1f);
		density = FlatMath.clamp(density, Constante.MINDENSITY, Constante.MAXDENSITY);
		float mass = !isStatic ? area * density : 0f;
		float inertia = !isStatic ? (1f / 2f) * mass * radius * radius : 0f;
		if (CanCreateCircleBody(radius, density))
			return new Rigidbody(gravityScale, density, mass, inertia, restitution, area, isStatic, radius, 0f, 0f,
					ShapeType.Circle, layer, g, t);
		else {
			mass = 1f * 1f * (float) Math.PI * density;
			inertia = (1f / 2f) * mass * radius * radius;
			System.out.println("Body non respecté");
			return new Rigidbody(gravityScale, density, mass, inertia, restitution, area, isStatic, 1f, 0f, 0f,
					ShapeType.Circle, layer, g, t);
		}
	}

	public static boolean CanCreateBoxBody(float width, float height, float density) {

		float area = width * height;
		return area >= Constante.MINBODYSIZE && area <= Constante.MAXBODYSIZE && density >= Constante.MINDENSITY
				&& density <= Constante.MAXDENSITY;
	}

	public static Rigidbody CreateBoxBody(float gravityScale, float width, float height, float density,
			boolean isStatic, float restitution, Layer layer, GameObject g, Transform t) {

		float area = width * height;
		restitution = FlatMath.clamp(restitution, 0f, 1f);
		density = FlatMath.clamp(density, Constante.MINDENSITY, Constante.MAXDENSITY);
		float mass = !isStatic ? area * density : 0f;
		float inertia = !isStatic ? (1f / 12f) * mass * (width * width + height * height) : 0f;
		if (CanCreateBoxBody(width, height, density))
			return new Rigidbody(gravityScale, density, mass, inertia, restitution, area, isStatic, 0f, width, height,
					ShapeType.Box, layer, g, t);
		else {
			area = 1f * 1f;
			mass = area * density;
			inertia = (1f / 12f) * mass * (width * width + height * height);
			System.out.println("Body non respecté");
			return new Rigidbody(gravityScale, density, mass, inertia, restitution, area, isStatic, 0f, 1f, 1f,
					ShapeType.Box, layer, g, t);
		}
	}
}
