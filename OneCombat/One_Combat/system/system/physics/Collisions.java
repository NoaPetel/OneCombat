package system.physics;

import system.enumeration.ShapeType;
import system.object.component.rigidbody.Rigidbody;

public class Collisions {
	
	public static float PointSegmentDistance(FlatVector p, FlatVector a, FlatVector b, FlatVector contact) {
		
		FlatVector newContact = FlatVector.Zero();
		FlatVector ab = FlatVector.minus(b, a);
		FlatVector ap = FlatVector.minus(p, a);
		
		float proj = FlatMath.dot(ap,  ab);
		float abLenSq = FlatMath.lengthSquared(ab);
		float d = proj / abLenSq;
		
		if(d <= 0f) {
			newContact = a;
		} else if(d >= 1f) {
			newContact = b;
		} else {
			newContact = FlatVector.plus(a, FlatVector.times(ab, d));
		}
		
		float distanceSquared = FlatMath.distanceSquared(p, newContact);
		contact.x = newContact.x;
		contact.y = newContact.y;
		return distanceSquared;
	}

	public static boolean IntersectAABBs(FlatAABB a, FlatAABB b) {
		if (a.max.x <= b.min.x || b.max.x <= a.min.x || a.max.y <= b.min.y || b.max.y <= a.min.y)
			return false;
		return true;
	}

	public static int FindContactPoints(Rigidbody bodyA, Rigidbody bodyB, FlatVector contact1, FlatVector contact2) {

		FlatVector newContact1 = FlatVector.Zero();
		FlatVector newContact2 = FlatVector.Zero();
		int contactCount = 0;

		ShapeType shapeTypeA = bodyA.shapeType;
		ShapeType shapeTypeB = bodyB.shapeType;

		if (shapeTypeA == ShapeType.Box) {
			if (shapeTypeB == ShapeType.Box) {
				contactCount = FindPolygonsContactPoints(bodyA.getTransformVertices(), bodyB.getTransformVertices(), newContact1, newContact2);
			} else if (shapeTypeB == ShapeType.Circle) {
				newContact1 = FindCirclePolygonContactPoint(bodyB.position, bodyB.radius, bodyA.position, bodyA.getTransformVertices());
				contactCount = 1;
			}
		} else if (shapeTypeA == ShapeType.Circle) {
			if (shapeTypeB == ShapeType.Box) {
				newContact1 = FindCirclePolygonContactPoint(bodyA.position, bodyA.radius, bodyB.position, bodyB.getTransformVertices());
				contactCount = 1;
			} else if (shapeTypeB == ShapeType.Circle) {

				newContact1 = FindCirclesContactPoint(bodyA.position, bodyA.radius, bodyB.position);
				contactCount = 1;
			}
		}
		contact1.x = newContact1.x;
		contact1.y = newContact1.y;
		contact2.x = newContact2.x;
		contact2.y = newContact2.y;
		return contactCount;
	}
	
	private static int FindPolygonsContactPoints(FlatVector[] verticesA, FlatVector[] verticesB, FlatVector contact1, FlatVector contact2) {
		
		FlatVector newContact1 = FlatVector.Zero();
		FlatVector newContact2 = FlatVector.Zero();
		FlatVector cp = FlatVector.Zero();
		float minDistSq = Float.MAX_VALUE;
		int contactCount = 0;
		
		for (int i = 0; i < verticesA.length; i++) {
			FlatVector p = verticesA[i];
			
			for (int j = 0; j < verticesB.length; j++) {
				FlatVector va = verticesB[j];
				FlatVector vb = verticesB[(j + 1) % verticesB.length];
				
				float distSq = PointSegmentDistance(p, va, vb, cp);
				
				if(FlatMath.nearlyEqual(distSq, minDistSq)) {
					if(!FlatMath.nearlyEqual(newContact1, cp)) {
						newContact2 = cp;
						contactCount = 2;
					}
				}
				else if(distSq < minDistSq) {
					minDistSq = distSq;
					newContact1 = cp;
					contactCount = 1;
				}
			}
		}
		
		
		for (int i = 0; i < verticesB.length; i++) {
			FlatVector p = verticesB[i];
			
			for (int j = 0; j < verticesA.length; j++) {
				FlatVector va = verticesA[j];
				FlatVector vb = verticesA[(j + 1) % verticesA.length];
				
				float distSq = PointSegmentDistance(p, va, vb, cp);
				
				if(FlatMath.nearlyEqual(distSq, minDistSq)) {
					if(!FlatMath.nearlyEqual(newContact1, cp)) {
						newContact2 = cp;
						contactCount = 2;
					}
				}
				else if(distSq < minDistSq) {
					minDistSq = distSq;
					newContact1 = cp;
					contactCount = 1;
				}
			}
		}
		contact1.x = newContact1.x;
		contact1.y = newContact1.y;
		contact2.x = newContact2.x;
		contact2.y = newContact2.y;
		return contactCount;
	}

	private static FlatVector FindCirclePolygonContactPoint(FlatVector circleCenter, float circleRadius, FlatVector polygonCenter, FlatVector[] polygonVertices) {
		
		FlatVector contact = FlatVector.Zero();
		FlatVector cp = FlatVector.Zero();
		float minDistSq = Float.MAX_VALUE;
		
		for(int i = 0; i < polygonVertices.length; i++) {
			FlatVector va = polygonVertices[i];
			FlatVector vb = polygonVertices[(i + 1) % polygonVertices.length];
			
			float distSq = PointSegmentDistance(circleCenter, va, vb, contact);
			
			if(distSq < minDistSq) {
				minDistSq = distSq;
				cp = contact;
			}
		}
		return cp;
	}

	private static FlatVector FindCirclesContactPoint(FlatVector centerA, float radiusA, FlatVector centerB) {

		FlatVector ab = FlatVector.minus(centerB, centerA);
		FlatVector dir = FlatMath.normalize(ab);
		FlatVector cp = FlatVector.plus(centerA, FlatVector.times(dir, radiusA));
		return cp;
	}

	public static float Collide(Rigidbody bodyA, Rigidbody bodyB, FlatVector normal) {
		FlatVector newNormal = FlatVector.Zero();
		float depth = 0f;

		ShapeType shapeTypeA = bodyA.shapeType;
		ShapeType shapeTypeB = bodyB.shapeType;

		if (shapeTypeA == ShapeType.Box) {
			if (shapeTypeB == ShapeType.Box) {
				depth = Collisions.IntersectPolygons(bodyA.getTransformVertices(), bodyB.getTransformVertices(),
						bodyA.position, bodyB.position, newNormal);
			} else if (shapeTypeB == ShapeType.Circle) {
				depth = Collisions.IntersectCirclePolygon(bodyB.position, bodyB.radius, bodyA.getTransformVertices(),
						bodyA.position, newNormal);
				newNormal = FlatVector.unaryMinus(newNormal);
			}
		} else if (shapeTypeA == ShapeType.Circle) {
			if (shapeTypeB == ShapeType.Box) {
				depth = Collisions.IntersectCirclePolygon(bodyA.position, bodyA.radius, bodyB.getTransformVertices(),
						bodyB.position, newNormal);
			} else if (shapeTypeB == ShapeType.Circle) {
				depth = Collisions.IntersectCircles(bodyA.position, bodyB.position, bodyA.radius, bodyB.radius,
						newNormal);
			}
		}

		normal.x = newNormal.x;
		normal.y = newNormal.y;
		return depth;

	}

	public static float IntersectCirclePolygon(FlatVector circleCenter, float circleRadius, FlatVector[] vertices,
			FlatVector polygonCenter, FlatVector normal) {

		FlatVector newNormal = FlatVector.Zero();
		float depth = Float.MAX_VALUE;
		float axisDepth = 0f;

		FlatVector axis = FlatVector.Zero();
		float[] minmaxA;
		float[] minmaxB;

		for (int i = 0; i < vertices.length; i++) {
			FlatVector va = vertices[i];
			FlatVector vb = vertices[(i + 1) % vertices.length];

			FlatVector edge = FlatVector.minus(vb, va);
			axis = new FlatVector(-edge.y, edge.x);
			axis = FlatMath.normalize(axis);

			minmaxA = ProjectVertices(vertices, axis);
			minmaxB = ProjectCircle(circleCenter, circleRadius, axis);

			if (minmaxA[0] >= minmaxB[1] || minmaxB[0] >= minmaxA[1]) {
				return 0f;
			}

			axisDepth = Math.min(minmaxB[1] - minmaxA[0], minmaxA[1] - minmaxB[0]);
			if (axisDepth < depth) {
				depth = axisDepth;
				newNormal = axis;
			}
		}

		int cpIndex = FindClosestPointOnPolygon(circleCenter, vertices);
		FlatVector cp = vertices[cpIndex];

		axis = FlatVector.minus(cp, circleCenter);
		axis = FlatMath.normalize(axis);

		minmaxA = ProjectVertices(vertices, axis);
		minmaxB = ProjectCircle(circleCenter, circleRadius, axis);

		if (minmaxA[0] >= minmaxB[1] || minmaxB[0] >= minmaxA[1]) {
			return 0f;
		}

		axisDepth = Math.min(minmaxB[1] - minmaxA[0], minmaxA[1] - minmaxB[0]);
		if (axisDepth < depth) {
			depth = axisDepth;
			newNormal = axis;
		}

		FlatVector direction = FlatVector.minus(polygonCenter, circleCenter);
		if (FlatMath.dot(direction, newNormal) < 0f) {
			newNormal = FlatVector.unaryMinus(newNormal);
		}

		normal.x = newNormal.x;
		normal.y = newNormal.y;

		return depth;
	}

	private static int FindClosestPointOnPolygon(FlatVector circleCenter, FlatVector[] vertices) {
		int result = -1;
		float minDistance = Float.MAX_VALUE;

		for (int i = 0; i < vertices.length; i++) {
			FlatVector v = vertices[i];
			float distance = FlatMath.distance(v, circleCenter);

			if (distance < minDistance) {
				minDistance = distance;
				result = i;
			}
		}
		return result;
	}

	private static float[] ProjectCircle(FlatVector center, float radius, FlatVector axis) {
		FlatVector direction = FlatMath.normalize(axis);
		FlatVector directionAndRadius = FlatVector.times(direction, radius);

		FlatVector p1 = FlatVector.plus(center, directionAndRadius);
		FlatVector p2 = FlatVector.minus(center, directionAndRadius);

		float min = FlatMath.dot(p1, axis);
		float max = FlatMath.dot(p2, axis);

		if (min > max) {
			float t = min;
			min = max;
			max = t;
		}

		float[] minmax = { min, max };
		return minmax;
	}

	public static float IntersectPolygons(FlatVector[] verticesA, FlatVector[] verticesB, FlatVector centerA,
			FlatVector centerB, FlatVector normal) {

		FlatVector newNormal = FlatVector.Zero();
		float depth = Float.MAX_VALUE;

		for (int i = 0; i < verticesA.length; i++) {
			FlatVector va = verticesA[i];
			FlatVector vb = verticesA[(i + 1) % verticesA.length];

			FlatVector edge = FlatVector.minus(vb, va);
			FlatVector axis = new FlatVector(-edge.y, edge.x);
			axis = FlatMath.normalize(axis);

			float[] minmaxA = ProjectVertices(verticesA, axis);
			float[] minmaxB = ProjectVertices(verticesB, axis);

			if (minmaxA[0] >= minmaxB[1] || minmaxB[0] >= minmaxA[1]) {
				return 0f;
			}

			float axisDepth = Math.min(minmaxB[1] - minmaxA[0], minmaxA[1] - minmaxB[0]);
			if (axisDepth < depth) {
				depth = axisDepth;
				newNormal = axis;
			}
		}

		for (int i = 0; i < verticesB.length; i++) {
			FlatVector va = verticesB[i];
			FlatVector vb = verticesB[(i + 1) % verticesB.length];

			FlatVector edge = FlatVector.minus(vb, va);
			FlatVector axis = new FlatVector(-edge.y, edge.x);
			axis = FlatMath.normalize(axis);

			float[] minmaxA = ProjectVertices(verticesA, axis);
			float[] minmaxB = ProjectVertices(verticesB, axis);

			if (minmaxA[0] >= minmaxB[1] || minmaxB[0] >= minmaxA[1]) {
				return 0f;
			}

			float axisDepth = Math.min(minmaxB[1] - minmaxA[0], minmaxA[1] - minmaxB[0]);
			if (axisDepth < depth) {
				depth = axisDepth;
				newNormal = axis;
			}
		}

		FlatVector direction = FlatVector.minus(centerB, centerA);
		if (FlatMath.dot(direction, newNormal) < 0f) {
			newNormal = FlatVector.unaryMinus(newNormal);
		}

		normal.x = newNormal.x;
		normal.y = newNormal.y;

		return depth;
	}

	private static float[] ProjectVertices(FlatVector[] vertices, FlatVector axis) {
		float min = Float.MAX_VALUE;
		float max = Float.MIN_NORMAL;

		for (int i = 0; i < vertices.length; i++) {
			FlatVector v = vertices[i];
			float proj = FlatMath.dot(v, axis);

			if (proj < min) {
				min = proj;
			}
			if (proj > max) {
				max = proj;
			}
		}
		float[] minmax = { min, max };
		return minmax;
	}

	public static float IntersectCircles(FlatVector centerA, FlatVector centerB, float radiusA, float radiusB,
			FlatVector normal) {
		float distance = FlatMath.distance(centerA, centerB);
		float radii = radiusA + radiusB;

		if (distance >= radii)
			return 0f;

		FlatVector newNormal = FlatMath.normalize(FlatVector.minus(centerB, centerA));
		float depth = radii - distance;
		normal.x = newNormal.x;
		normal.y = newNormal.y;

		return depth;
	}
}
