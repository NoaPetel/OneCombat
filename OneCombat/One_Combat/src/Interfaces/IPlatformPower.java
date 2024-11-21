package Interfaces;

public interface IPlatformPower {

	public enum POWER {
		SPRING, HEAL, DAMAGE, POWERUP, POWERDOWN, EXPLOSION;
	}

	public void power();

}
