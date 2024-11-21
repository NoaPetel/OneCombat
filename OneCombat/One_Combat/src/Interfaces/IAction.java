package Interfaces;

import system.physics.FlatVector;

public interface IAction {



	public enum ABSDIRECTION{
		NORTH, SOUTH, EAST, WEST;

		public static ABSDIRECTION toEnum(String enumString) {
			switch (enumString) {
			case "N":
				return NORTH;
			case "S":
				return SOUTH;
			case "E":
				return EAST;
			case "W":
				return WEST;
			default:
				return null;
			}
		}
	}

	public enum RELDIRECTION {
		FRONT, RIGHT, LEFT, BACK;

		public static RELDIRECTION toEnum(String enumString) {
			switch (enumString) {
			case "F":
				return FRONT;
			case "R":
				return RIGHT;
			case "L":
				return LEFT;
			case "B":
				return BACK;
			default:
				return null;
			}
		}

		public static FlatVector toVector(RELDIRECTION rel) {
			switch (rel) {
			case FRONT:
				return FlatVector.up();
			case RIGHT:
				return FlatVector.right();
			case LEFT:
				return FlatVector.left();
			case BACK:
				return FlatVector.down();
			default:
				return FlatVector.Zero();
			}
		}
	}

	public void Move(RELDIRECTION d);

	public void Move(ABSDIRECTION d);

	public void Move();

	public void Jump();

	public void Dash();

	public void Dash(RELDIRECTION d);

	public void Dash(ABSDIRECTION d);

	public void Hit();

	public void Hit(RELDIRECTION d);

	public void Hit(ABSDIRECTION d);

	public void Attack();

	public void Attack(RELDIRECTION d);

	public void Attack(ABSDIRECTION d);

	public void BigAttack();

	public void BigAttack(RELDIRECTION d);

	public void BigAttack(ABSDIRECTION d);

	public void Egg();

	public void Egg(RELDIRECTION d);

	public void Egg(ABSDIRECTION d);

	public void Get();

	public void Get(RELDIRECTION d);

	public void Get(ABSDIRECTION d);

	public void Explode();

	public void Pick();

	public void Pick(RELDIRECTION d);

	public void Pick(ABSDIRECTION d);

	public void Pop();

	public void Pop(RELDIRECTION d);

	public void Pop(ABSDIRECTION d);

	public void Power();

	public void Protect();

	public void Store();

	public void Turn(ABSDIRECTION d);

	public void Turn(RELDIRECTION d);

	public void Throw();

	public void Throw(ABSDIRECTION d);

	public void Throw(RELDIRECTION d);

	public void Wait();

	public void Wizz();

	public void Wizz(RELDIRECTION d);

	public void Wizz(ABSDIRECTION d);
}
