package src.bots;

import Interfaces.IAction;
import src.bots.actions.BotAttack;
import src.bots.actions.BotHeal;
import src.bots.actions.BotHealth;
import src.bots.actions.BotHit;
import src.bots.actions.BotMovement;
import src.bots.actions.BotThrow;
import system.object.component.monobehavior.MonoBehavior;
import system.object.component.transform.Transform;
import system.object.gameobject.GameObject;

public class BotAction extends MonoBehavior implements IAction {

	private BotHealth botHealth;
	private BotAttack botAttack;
	private BotMovement botMovement;
	private BotThrow botThrow;
	private BotHit botHit;
	private BotHeal botHeal;

	public BotAction(GameObject g, Transform t) {
		super(g, t);
	}
	
	public void start() {
		botMovement = gameObject.getComponent(BotMovement.class);
		botAttack = gameObject.getComponent(BotAttack.class);
		botHealth = gameObject.getComponent(BotHealth.class);
		botThrow = gameObject.getComponent(BotThrow.class);
		botHit = gameObject.getComponent(BotHit.class);
		botHeal = gameObject.getComponent(BotHeal.class);
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
		
	}

	@Override
	public void Dash(RELDIRECTION d) {

	}

	@Override
	public void Dash(ABSDIRECTION d) {

	}

	@Override
	public void Hit() {
		botHit.hit();
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
		botHeal.Healing();
	}

	@Override
	public void Store() {


	}

	@Override
	public void Turn(ABSDIRECTION d) {
		

	}

	@Override
	public void Turn(RELDIRECTION d) {
		botMovement.moveTurn();
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
		botAttack.attack();

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
		botMovement.run(); 

	}

	@Override
	public void Get() {
		// TODO Auto-generated method stub

	}

	@Override
	public void Throw() {
		botThrow.onShoot();
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
