package Interfaces;

public interface IDamagable {
	
	void setDamage(float amount);
	void death();
	void addHealth(float amount);
	boolean canTakeDamage();

	
}
