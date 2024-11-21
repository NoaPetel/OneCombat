package system.automate;

import Interfaces.IEntity;
import system.enumeration.KeysEnum;

public class AutCondKey extends AutCondition{
	private int key;
	
	public AutCondKey(String key) {
		this.key = this.getEnum(key);
	}
	
	public boolean eval(IEntity entity) {
		return entity.getCondition().Key(this.key);
	}
	
	public int getEnum(String string) {
		switch(string) {
			case "1":
				return KeysEnum.ONE.getValue();
				
			case "2":
				return KeysEnum.TWO.getValue();
				
			case "3":
				return KeysEnum.THREE.getValue();
				
			case "4":
				return KeysEnum.FOUR.getValue();
				
			case "5":
				return KeysEnum.FIVE.getValue();
				
			case "6":
				return KeysEnum.SIX.getValue();
				
			case "7":
				return KeysEnum.SEVEN.getValue();
				
			case "8":
				return KeysEnum.HEIGHT.getValue();
			case "9":
				return KeysEnum.NINE.getValue();
				
			default:
				return KeysEnum.valueOf(string.toUpperCase()).getValue();
		}
	}
}
