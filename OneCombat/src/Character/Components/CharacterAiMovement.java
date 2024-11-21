package Character.Components;

import java.awt.Graphics;
import java.util.ArrayList;

import system.object.component.monobehavior.MonoBehavior;
import system.object.component.transform.Transform;
import system.object.gameobject.GameObject;
import system.physics.FlatMath;
import system.physics.FlatVector;

public class CharacterAiMovement extends MonoBehavior {

	// Components
	private CharacterMovement characterMovement;

	// Follows Properties
	private ArrayList<FlatVector> followPoints = new ArrayList<FlatVector>();
	private FlatVector currentPoint = FlatVector.Zero();

	// Movement Properties
	FlatVector moveInput = FlatVector.Zero();
	
	//Les points
	
	public static ArrayList<int[]> canGoIndice = new ArrayList<>();
	public static ArrayList<FlatVector> points = new ArrayList<>();


	
	public boolean isMoving = true;

	public CharacterAiMovement(GameObject g, Transform t) {
		super(g, t);
//        for(int i = 0; i < 5; i++) {
//        	float x = (float)Math.random() * 50 - 25f;
//        	float y = (float)Math.random() * 50 - 25f;
//            followPoints.add(new FlatVector(x, y));
//        }	
			points.add(new FlatVector(-29,-1)); //0
			points.add(new FlatVector(-25,-1));//1
			points.add(new FlatVector(-18,1)); //2
			points.add(new FlatVector(-13,-1));//3
			points.add(new FlatVector(-4,0)); //4
			points.add(new FlatVector(14,1));//5
			points.add(new FlatVector(23,-4));//6
			points.add(new FlatVector(-24,10)); //7
			points.add(new FlatVector(-17,8)); //8
			points.add(new FlatVector(-13,9)); //9
			points.add(new FlatVector(-6,9)); //10
			points.add(new FlatVector(2,8)); //11
			points.add(new FlatVector(7,5)); //12
			points.add(new FlatVector(13,7)); //13
			points.add(new FlatVector(19,7)); //14
			points.add(new FlatVector(24,5)); //15
			points.add(new FlatVector(24,12)); //16
			points.add(new FlatVector(17,14)); //17
			
			
			int[] point0 = {1,6};//
	        canGoIndice.add(point0);
	        
	        int[] point1 = {0,2,7};//
	        canGoIndice.add(point1);
	        
	        int[] point2 = {1,3,7};//
	        canGoIndice.add(point2);
	        
	        int[] point3 = {2,4,10};//
	        canGoIndice.add(point3);
	        
	        int[] point4 = {3,11,12};//
	        canGoIndice.add(point4);
	        
	        int[] point5 = {12, 6};//
	        canGoIndice.add(point5);
	        
	        int[] point6 = {5,0};//
	        canGoIndice.add(point6);
	        
	        int[] point7 = {8};//
	        canGoIndice.add(point7);
	        
	        int[] point8 = {7,9};//
	        canGoIndice.add(point8);
	        
	        int[] point9 = {8,10};//
	        canGoIndice.add(point9);
	        
	        int[] point10 = {9,11};//
	        canGoIndice.add(point10);
	        
	        int[] point11 = {10,12};
	        canGoIndice.add(point11);
	        
	        int[] point12 = {11,13};//
	        canGoIndice.add(point12);
	        
	        int[] point13 = {12, 14};//
	        canGoIndice.add(point13);
	        
	        int[] point14 = {13,15,16};//
	        canGoIndice.add(point14);
	        
	        int[] point15 = {14};//
	        canGoIndice.add(point15);
	        
	        int[] point16 = {17};//
	        canGoIndice.add(point16);
	        
	        int[] point17 = {17};//
	        canGoIndice.add(point17);
	        
	        
	}

	public void start() {
		characterMovement = gameObject.getComponent(CharacterMovement.class);
		setNewPoint();
	}

	public void update() {
		if (isMoving && currentPoint != null) {
			setMoveInput();
			checkFollowPoints();
		} else
			characterMovement.setInputVector(FlatVector.Zero());
	}

	public void setMoveInput() {
		if (FlatMath.nearlyClose(transform.position.x, currentPoint.x))
			moveInput.x = 0;
		else if (transform.position.x > currentPoint.x)
			moveInput.x = -1;
		else if (transform.position.x < currentPoint.x)
			moveInput.x = 1;

		if (transform.position.y >= currentPoint.y)
			characterMovement.onJumpInput();

		characterMovement.setInputVector(moveInput);
	}

	public void checkFollowPoints() {
		if (FlatMath.nearlyClose(transform.position, currentPoint)) {
			if (followPoints.size() > 0)
				followPoints.remove(0);
			setNewPoint();
		}
	}

	public void setMoving(boolean isMoving) {
		this.isMoving = isMoving;
	}

	public void setFollowPoint(ArrayList<FlatVector> followPoints) {

		if (followPoints == null)
			return;
		this.followPoints = followPoints;
		setNewPoint();
	}

	public void setNewPoint() {

		if (followPoints.size() > 0) {
			this.currentPoint = followPoints.get(0);
			isMoving = true;
		} else {
			isMoving = false;
		}
	}

	public void setInputVector(FlatVector zero) {
		characterMovement.setInputVector(zero);

	}

	public void onDashInput() {
		characterMovement.onDashInput();
	}

	public void onJumpInput() {
		characterMovement.onJumpInput();

	}

	public boolean isArrived() {
		return followPoints.size() == 0 || !isMoving;
	}

	public void onDrawGizmos(Graphics g) {
		this.drawCircleGizmos(g, currentPoint, 0.1f);
		// this.drawRectRelativeGizmos(g, transform.position, 0.1f, 0.1f, Color.pink,
		// currentPoint, true);
	}
	public void resetFollowPoints() {
		this.followPoints.clear();
	}
	
    public static double calculateDistance(FlatVector point1, FlatVector point2) {
        double dx = point2.x - point1.x;
        double dy = point2.y - point1.y;
        return Math.sqrt(dx * dx + dy * dy);
    }	
    public static int closestZone(FlatVector me, ArrayList<FlatVector> points) {
       

        double minDistance = Double.MAX_VALUE;
        int closestZoneIndex = -1;

        for (int i = 0; i < points.size(); i++) {
            FlatVector point = points.get(i);
            double distance = calculateDistance(me, point);

            if (distance < minDistance) {
                minDistance = distance;
                closestZoneIndex = i;
            }
        }

        return closestZoneIndex;
    }

    public static int closestZone(FlatVector me) {
        

        double minDistance = Double.MAX_VALUE;
        int closestZoneIndex = -1;

        for (int i = 0; i < points.size(); i++) {
            FlatVector point = points.get(i);
            double distance = calculateDistance(me, point);

            if (distance < minDistance) {
                minDistance = distance;
                closestZoneIndex = i;
            }
        }

        return closestZoneIndex;
    }
    
    

    
    public FlatVector NextZone(FlatVector me, FlatVector objectif) {
    	int indice = closestZone(me);
    	int[] nextIndice = canGoIndice.get(indice);
    	ArrayList<FlatVector> accessiblePoints = new ArrayList<FlatVector>();
    	
    	for (int i = 0; i < nextIndice.length; i++) {
    		accessiblePoints.add(points.get(nextIndice[i]));
    	}
    	int bestIndice = closestZone(objectif,accessiblePoints);
    	return accessiblePoints.get(bestIndice);
    }

	public void addPoint(FlatVector nextPoint) {
		followPoints.add(nextPoint);
		
	}

}
