package system.physics;

import system.Constante;
import system.enumeration.ShapeType;

public class FlatBody implements IBody {

	public FlatVector position;
	public FlatVector linearVelocity;
	public float rotation;
	private float rotationalVelocity;
	
	private FlatVector force;

	public float density;
	public float mass;
	public float restitution;
	public float area;
	public float invMass;

	public boolean isStatic;

	public float radius;
	public float width;
	public float height;
	
	private FlatVector[] vertices;
	public int[] triangles;
	private FlatVector[] transformVertices;
	private FlatAABB aabb;
	
	private boolean transformUpdateRequired;
	private boolean aabbUpdateRequired;

	public ShapeType shapeType;

	private FlatBody(FlatVector position, float density, float mass, float restitution, float area, boolean isStatic,
			float radius, float width, float height, ShapeType shapeType) {

		this.position = position;
		this.linearVelocity = FlatVector.Zero();
		this.rotation = 0;
		this.rotationalVelocity = 0;
		this.force = FlatVector.Zero();
		
		this.density = density;
		this.mass = mass;
		this.restitution = restitution;
		this.area = area;
		this.isStatic = isStatic;
		
		this.radius = radius;
		this.width = width;
		this.height = height;
		this.shapeType = shapeType;
		
		if(!this.isStatic) this.invMass = 1f/ this.mass;
		else this.invMass = 0f;
		
		if(this.shapeType == ShapeType.Box) {
			this.vertices = CreateBoxVertices(this.width, this.height);
			this.triangles = CreateBoxTriangles();
			this.transformVertices = new FlatVector[this.vertices.length];
		} else {
			this.vertices = null;
			this.triangles = null;
			this.transformVertices = null;
		}
		
		this.transformUpdateRequired = true;
		this.aabbUpdateRequired = true;
	}
	
	
	
	
	private static FlatVector[] CreateBoxVertices(float width, float height) {
		float left = -width/2;
		float right = left + width;
		float bottom = -height/2;
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
	
	public FlatVector[] getTransformVertices() {
		if(this.transformUpdateRequired) {
			FlatTransform transform = new FlatTransform(this.position, this.rotation);
			
			for(int i = 0; i < this.vertices.length; i++) {
				FlatVector v = this.vertices[i];
				this.transformVertices[i] = FlatVector.Transform(v, transform);
			}
		}
		this.transformUpdateRequired = false;
		return this.transformVertices;
	}
	
	public FlatAABB getAABB(){
		
		if(this.aabbUpdateRequired) {
			float minX = Float.MAX_VALUE;
			float minY = Float.MAX_VALUE;
			float maxX = Float.MIN_NORMAL;
			float maxY = Float.MIN_NORMAL;
			
			
			if(this.shapeType == ShapeType.Box) {
				
				FlatVector[] vertices = getTransformVertices();
				
				for(int i = 0; i < vertices.length; i++) {
					
					FlatVector v = vertices[i];
					
					if(v.x < minX) minX = v.x;
					if(v.y < minY) minY = v.y;
					if(v.x > maxX) maxX = v.x;
					if(v.y > maxY) maxY = v.y;
					
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
		this.transformUpdateRequired = true;
		this.aabbUpdateRequired = true;
	}
	
	public void moveTo(FlatVector amount) {
		position = amount;
		this.transformUpdateRequired = true;
		this.aabbUpdateRequired = true;
	}
	
	public void rotate(float amount) {
		rotation += amount;
		this.transformUpdateRequired = true;
		this.aabbUpdateRequired = true;
	}
	
	public void rotateTo(float amount) {
		rotation = amount;
		this.transformUpdateRequired = true;
		this.aabbUpdateRequired = true;
	}
	
	public void addForce(FlatVector amount) {
		force = amount;
	}
	
	public void update(float elapsed, FlatVector gravity) {
		
		//force = mass * acc
		//acc = force / mass
		
		if(isStatic) return;
		
		FlatVector acceleration = FlatVector.div(force, mass);
		linearVelocity = FlatVector.plus(linearVelocity, FlatVector.times(acceleration, elapsed));
		
		linearVelocity = FlatVector.plus(linearVelocity, FlatVector.times(gravity, elapsed));
		position = FlatVector.plus(position, FlatVector.times(linearVelocity, elapsed));
		
		rotation += rotationalVelocity * elapsed;
		
		force = FlatVector.Zero();
		this.transformUpdateRequired = true;
		this.aabbUpdateRequired = true;
	}

	public static boolean CanCreateCircleBody(float radius, float density) {

		float area = radius * radius * (float) Math.PI;
		return area >= Constante.MINBODYSIZE && area <= Constante.MAXBODYSIZE && density >= Constante.MINDENSITY
				&& density <= Constante.MAXDENSITY;
	} 

	public static FlatBody CreateCircleBody(float radius, FlatVector position, float density, boolean isStatic,
			float restitution) {
		
		float area = radius * radius * (float) Math.PI;
		restitution = FlatMath.clamp(restitution, 0f, 1f);
		density = FlatMath.clamp(density, Constante.MINDENSITY, Constante.MAXDENSITY);
		float mass = area * density;
		if(CanCreateCircleBody(radius, density)) return new FlatBody(position, density, mass, restitution, area, isStatic, radius, 0f, 0f, ShapeType.Circle);
		else {
			mass = 1f * 1f * (float) Math.PI * density;
			System.out.println("Body non respecté");
			return new FlatBody(position, density, mass, restitution, area, isStatic, 1f, 0f, 0f, ShapeType.Circle);
		}
	}
	
	
	
	public static boolean CanCreateBoxBody(float width, float height, float density) {

		float area = width * height;
		return area >= Constante.MINBODYSIZE && area <= Constante.MAXBODYSIZE && density >= Constante.MINDENSITY
				&& density <= Constante.MAXDENSITY;
	}

	public static FlatBody CreateBoxBody(float width, float height, FlatVector position, float density, boolean isStatic,
			float restitution) {
		
		float area = width * height;
		restitution = FlatMath.clamp(restitution, 0f, 1f);
		density = FlatMath.clamp(density, Constante.MINDENSITY, Constante.MAXDENSITY);
		float mass = area * density;
		if(CanCreateBoxBody(width, height, density)) return new FlatBody(position, density, mass, restitution, area, isStatic, 0f, width, height, ShapeType.Box);
		else {
			area = 1f * 1f;
			System.out.println("Body non respecté");
			return new FlatBody(position, density, mass, restitution, area, isStatic, 0f, 1f, 1f, ShapeType.Box);
		}
	}


}
