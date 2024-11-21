package system.physics;

import system.object.component.rigidbody.Rigidbody;

public final class FlatManifold {

	public final Rigidbody bodyA;
	public final Rigidbody bodyB;
	public final FlatVector normal;
	public final float depth;
	public final FlatVector contact1;
	public final FlatVector contact2;
	public final int contactCount;

	public FlatManifold(Rigidbody bodyA, Rigidbody bodyB, FlatVector normal, float depth, FlatVector contact1,
			FlatVector contact2, int contactCount) {
		this.bodyA = bodyA;
		this.bodyB = bodyB;
		this.normal = normal;
		this.depth = depth;
		this.contact1 = contact1;
		this.contact2 = contact2;
		this.contactCount = contactCount;
	}
}
