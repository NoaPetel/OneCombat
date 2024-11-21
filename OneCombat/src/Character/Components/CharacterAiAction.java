package Character.Components;

import java.util.ArrayList;
import java.util.List;

import Interfaces.IAction;
import algo.AStar;
import algo.Matrice;
import algo.Node;
import src.manager.TileManager;
import system.object.component.monobehavior.MonoBehavior;
import system.object.component.transform.Transform;
import system.object.gameobject.GameObject;
import system.physics.FlatVector;

public class CharacterAiAction extends MonoBehavior implements IAction {

	private CharacterAttack characterAttack;
	private CharacterAiMovement characterAiMovement;
	private CharacterHealth characterHealth;
	private CharacterAiHandler characterAiHandler;
	
	public CharacterAiAction(GameObject g, Transform t) {
		super(g, t);
	}
	
	public void start() {
		characterAiMovement = gameObject.getComponent(CharacterAiMovement.class);
		characterAttack = gameObject.getComponent(CharacterAttack.class);
		characterHealth = gameObject.getComponent(CharacterHealth.class);
		characterAiHandler = gameObject.getComponent(CharacterAiHandler.class);

	}

	@Override
	public void Move(RELDIRECTION d) {
		//Todo
	}

	@Override
	public void Move(ABSDIRECTION d) {
		// TODO Auto-generated method stub

	}

	@Override
	public void Jump() {
		characterAiMovement.onJumpInput();
	}

	@Override
	public void Dash(RELDIRECTION d) {
		characterAiMovement.onDashInput();

	}

	@Override
	public void Dash(ABSDIRECTION d) {
		characterAiMovement.onDashInput();

	}

	@Override
	public void Hit() {
		characterAttack.onAttackInput();

	}

	@Override
	public void Hit(RELDIRECTION d) {
		// TODO Auto-generated method stub

	}

	@Override
	public void Hit(ABSDIRECTION d) {
		// TODO Auto-generated method stub

	}

	@Override
	public void Attack(RELDIRECTION d) {
		characterAttack.onAttackInput();

	}

	@Override
	public void Attack(ABSDIRECTION d) {
		// TODO Auto-generated method stub

	}

	@Override
	public void BigAttack(RELDIRECTION d) {
		// TODO Auto-generated method stub

	}

	@Override
	public void BigAttack(ABSDIRECTION d) {
		// TODO Auto-generated method stub

	}
	@Override
	public void Egg() {
		// TODO Auto-generated method stub

	}

	@Override
	public void Egg(RELDIRECTION d) {
		// TODO Auto-generated method stub

	}

	@Override
	public void Egg(ABSDIRECTION d) {
		// TODO Auto-generated method stub

	}

	@Override
	public void Get(RELDIRECTION d) {
		// TODO Auto-generated method stub

	}

	@Override
	public void Get(ABSDIRECTION d) {
		// TODO Auto-generated method stub

	}

	@Override
	public void Explode() {
		// TODO Auto-generated method stub

	}

	@Override
	public void Pick(RELDIRECTION d) {
		// TODO Auto-generated method stub

	}

	@Override
	public void Pick(ABSDIRECTION d) {
		// TODO Auto-generated method stub

	}

	@Override
	public void Pop() {
		characterAttack.onLongAttackInput();

	}

	@Override
	public void Pop(RELDIRECTION d) {
		// TODO Auto-generated method stub

	}

	@Override
	public void Pop(ABSDIRECTION d) {
		// TODO Auto-generated method stub

	}

	@Override
	public void Power() {
		characterAiMovement.resetFollowPoints();
		

	}

	@Override
	public void Protect() {
		characterAiMovement.onDashInput();

	}

	@Override
	public void Store() {
		//Pathfinding 
		//debut timer
		if (!characterAiHandler.isBloquedCheck())
			return;
		characterAiHandler.onStartPathFinding();
//		
//		int[][] grid = TileManager.getGrid();
//		int[][] normalizeGrid = Matrice.normalise(grid);
//	    AStar algorithm = new AStar(normalizeGrid);
//	    
		
		FlatVector me = transform.getPosition();
		FlatVector cible = new FlatVector(0f, 0f);
		
		try {
          cible = characterAiHandler.getClosestCharacterPos();
            // Rest of your code
        } catch (Exception e) {
            // Handle the exception

            System.out.println("An exception occurred: " + e.getMessage());
        }		//TODO ennemi le closest
//		
//		int length = (int)TileManager.getLength();
//		int height = (int)TileManager.getHeight();
//
//		
//		int xy[] = TileManager.getTile(me);
//		
//		int xydest[] = TileManager.getTile(cible);
//		int x_dest = xydest[0];
//		int y_dest = xydest[1];
//		
//		
//		int x = xy[0];
//		int y = xy[1];
//		x = (x+length)%length ;
//		y = (y+ height)%height;
//		x_dest = (x_dest+length)%length ;
//		y_dest = (y_dest+ height)%height;
//		int x_dest = (x+1+ length)%length ;
//		int y_dest = (y+ height)%height;
		
		//Matrice.debugMatrix(normalizeGrid, x, y , x_dest, y_dest);

//		while (!(Matrice.isValid(x_dest, y_dest, normalizeGrid))) {
		    // not valid
//		    y_dest = ((y_dest + 1) % height);
		    //Matrice.debugMatrix(normalizeGrid, x, y , x_dest, y_dest);

		
		
		
//		List<Node> nodeList = algorithm.findPath(x, y, x_dest, y_dest);

//		Matrice.debugMatrix(normalizeGrid, x, y , x_dest, y_dest);
//
//		
//		ArrayList<FlatVector> vecList = Node.toVector(nodeList);
//		vecList = FlatVector.delPoints(vecList);
		
		
		
//		    vecList = Node.toVector(nodeList);
		    //printVectorList(vecList);
			FlatVector nextPoint = characterAiMovement.NextZone(me, cible);
			//System.out.println(nextPoint.x + " "+ nextPoint.y);
			ArrayList<FlatVector> vecList = new ArrayList<FlatVector>();
			vecList.add(nextPoint);
			characterAiMovement.resetFollowPoints();
			characterAiMovement.setFollowPoint(vecList);
			characterAiMovement.setMoving(true);

		
		

	}
	public static void printVectorList(ArrayList<FlatVector> vectorList) {
		// System.out.println("vectorList :  ");
        for (FlatVector vector : vectorList) {
          //  System.out.println("x: " + vector.x + ", y: " + vector.y);
        }
    }

	@Override
	public void Turn(ABSDIRECTION d) {
		// TODO Auto-generated method stub

	}

	@Override
	public void Turn(RELDIRECTION d) {
		// TODO Auto-generated method stub

	}

	@Override
	public void Throw(ABSDIRECTION d) {
		// TODO Auto-generated method stub

	}

	@Override
	public void Throw(RELDIRECTION d) {
		// TODO Auto-generated method stub

	}

	@Override
	public void Wait() {
	//System.out.println("Wait");
		

	}

	@Override
	public void Wizz() {
		characterAttack.onSpecialAttackInput();

	}

	@Override
	public void Wizz(RELDIRECTION d) {
		// TODO Auto-generated method stub

	}

	@Override
	public void Wizz(ABSDIRECTION d) {
		// TODO Auto-generated method stub

	}

	@Override
	public void Move() {
		characterAiMovement.setMoving(true);

	}

	@Override
	public void Get() {
		// TODO Auto-generated method stub

	}

	@Override
	public void Throw() {
		characterAiMovement.setMoving(false);

	}

	@Override
	public void Dash() {
		// TODO Auto-generated method stub

	}

	@Override
	public void Attack() {
		// TODO Auto-generated method stub

	}

	@Override
	public void BigAttack() {
		// TODO Auto-generated method stub

	}

	@Override
	public void Pick() {
		// TODO Auto-generated method stub

	}

}
