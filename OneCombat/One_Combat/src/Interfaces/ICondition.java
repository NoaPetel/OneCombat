package Interfaces;

import Interfaces.IAction.ABSDIRECTION;
import Interfaces.IAction.RELDIRECTION;

public interface ICondition {
	
	public enum  TYPE { 
		ADVERSAIRE, CLUE, DANGER, GATE, JUMPABLE, MISSILE, OBSTACLE, PICKABLE, TEAM, VOID, PLAYER, ANY;
		
		public static ICondition.TYPE toEnum(String stringEnum){
			switch(stringEnum) {
				case "A":
					return ADVERSAIRE;
					
				case "C":
					return CLUE;
					
				case "D":
					return DANGER;
					
				case "G":
					return GATE;
					
				case "J":
					return JUMPABLE;
					
				case "M":
					return MISSILE;
					
				case "O":
					return OBSTACLE;
					
				case "P":
					return PICKABLE;
					
				case "T":
					return TEAM;
					
				case "V":
					return VOID;
					
				case "@":
					return PLAYER;
					
				case "_":
					return ANY;
					
				default:
					return null;
			}
		}
	}

	public boolean isEmpty();

	public boolean isNotEmpty();

	public boolean gotPower();

	public boolean hasAdversaire();

	public boolean True();

	public boolean hasTeammate();

	public boolean hasMissile();
	
	public boolean Key(int c);
	
	public boolean MyDir(RELDIRECTION d);
	
	public boolean MyDir(ABSDIRECTION d);
	
	public boolean Cell(ABSDIRECTION d, TYPE t );
	
	public boolean gotStuff();
	
	public boolean Closest(TYPE t, ABSDIRECTION d);
	
	public boolean Closest(TYPE t, RELDIRECTION d);

}
