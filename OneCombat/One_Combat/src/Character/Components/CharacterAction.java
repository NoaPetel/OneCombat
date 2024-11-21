package Character.Components;


import Interfaces.IAction;
import system.object.component.monobehavior.MonoBehavior;
import system.object.component.transform.Transform;
import system.object.gameobject.GameObject;
import system.physics.FlatVector;



public class CharacterAction extends MonoBehavior implements IAction {

	// Components
	


	private CharacterAttack characterAttack;
	private CharacterMovement characterMovement;
	private CharacterHealth characterHealth;

	public CharacterAction(GameObject g, Transform t) {
		super(g, t);
	}
	
	public void start() {
		characterMovement = gameObject.getComponent(CharacterMovement.class);
		characterAttack = gameObject.getComponent(CharacterAttack.class);
		characterHealth = gameObject.getComponent(CharacterHealth.class);
	}

	@Override
	public void Move(RELDIRECTION d) {
		
		characterMovement.setInputVector(RELDIRECTION.toVector(d));

		

	}

	@Override
	public void Move(ABSDIRECTION d) {
		// TODO Auto-generated method stub

	}

	@Override
	public void Jump() {
		characterMovement.onJumpInput();

	}

	@Override
	public void Dash(RELDIRECTION d) {
		characterMovement.onDashInput();

	}

	@Override
	public void Dash(ABSDIRECTION d) {
		characterMovement.onDashInput();

	}

	@Override
	public void Hit() {
		characterAttack.onAttackInput();
		
//	 	TEST ET DEBUG
		
		FlatVector me = transform.getPosition();
		//System.out.println("me :" +me.x +" "+ me.y);
//		int[] a = TileManager.getTile(me);
//		System.out.println("Place in tile: "+a[0]+ " " + a[1]);
//		float diffx = a[0]-me.x;
//		float diffy = a[1]-me.y;
//		System.out.println("Difference: " +diffx + " "+ diffy);

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
		//debug position
		
		

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
		// TODO Auto-generated method stub

	}

	@Override
	public void Protect() {
		characterMovement.onDashInput();

	}

	@Override
	public void Store() {
	
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
		characterHealth.hit();
		

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
		// TODO Auto-generated method stub

	}

	@Override
	public void Get() {
		// TODO Auto-generated method stub

	}

	@Override
	public void Throw() {
		characterMovement.setInputVector(FlatVector.Zero());

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
