package Character.Components;

import system.Constante;
import system.physics.FlatMath;
import system.physics.FlatVector;

public class CharacterData {

	public String name;

	public String getName() {
		return name;
	}
	/*
	 * Gravity
	 */

	public float gravityStrength = 1; // Downwards force (gravity) needed for the desired jumpHeight and
										// jumpTimeToApex.
	public float gravityScale = 18; // Strength of the player's gravity as a multiplier of gravity (set in
									// ProjectSettings/Physics2D).
	// Also the value the player's rigidbody2D.gravityScale is set to.

	public float fallGravityMult = 1; // Multiplier to the player's gravityScale when falling.
	public float maxFallSpeed = 18; // Maximum fall speed (terminal velocity) of the player when falling.

	public float fastFallGravityMult = 1; // Larger multiplier to the player's gravityScale when they are falling and a
											// downwards input is pressed.
											// Seen in games such as Celeste, lets the player fall extra fast if they
											// wish.
	public float maxFastFallSpeed = 20; // Maximum fall speed(terminal velocity) of the player when performing a faster
										// fall.

	/*
	 * Run
	 */

	public float runMaxSpeed = 3; // Target speed we want the player to reach.
	public float runAcceleration = 3; // The speed at which our player accelerates to max speed, can be set to
										// runMaxSpeed for instant acceleration down to 0 for none at all
	public float runAccelAmount; // The actual force (multiplied with speedDiff) applied to the player.
	public float runDecceleration = 3f; // The speed at which our player decelerates from their current speed, can be
										// set to runMaxSpeed for instant deceleration down to 0 for none at all
	public float runDeccelAmount; // Actual force (multiplied with speedDiff) applied to the player .

	public float accelInAir = 1; // Multipliers applied to acceleration rate when airborne.

	public float deccelInAir = 1;

	public boolean doConserveMomentum = true;

	/*
	 * Jump
	 */

	public float jumpMagnitude = 1f;
	public float jumpHeight = -1.8f; // Height of the player's jump
	public float jumpTimeToApex = 0.3f; // Time between applying the jump force and reaching the desired jump height.
										// These values also control the player's gravity and jump force.
	public float jumpForce; // The actual force applied (upwards) to the player when they jump.
	public int airJumps = 1;

	/*
	 * Both jump
	 */

	public float jumpCutGravityMult = 1; // Multiplier to increase gravity if the player releases thje jump button while
											// still jumping
	public float jumpHangGravityMult = 1; // Reduces gravity while close to the apex (desired max height) of the jump
	public float jumpHangTimeThreshold = 0; // Speeds (close to 0) where the player will experience extra "jump hang".
											// The
											// player's velocity.y is closest to 0 at the jump's apex (think of the
											// gradient
											// of a parabola or quadratic function)
	public float jumpHangAccelerationMult = 1;
	public float jumpHangMaxSpeedMult = 1;

	/*
	 * Assists
	 */

	public float coyoteTime = 0.1f; // Grace period after falling off a platform, where you can still jump
	public float jumpInputBufferTime = 0.1f; // Grace period after pressing jump where a jump will be automatically
												// performed
												// once the requirements (eg. being grounded) are met.

	/*
	 * Dash
	 */

	public int dashAmount = 1;
	public float dashSpeed = 8;
	public float dashSleepTime = 0.05f; // Duration for which the game freezes when we press dash but before we read
										// directional input and apply a force

	public float dashAttackTime = 0.500f;

	public float dashEndTime = 0.15f; // Time after you finish the inital drag phase, smoothing the transition back to
										// idle (or any standard state)
	public FlatVector dashEndSpeed = new FlatVector(2f, 0f); // Slows down player, makes dash feel more responsive (used
																// in Celeste)
	public float dashEndRunLerp = 0; // Slows the affect of player movement while dashing

	public float dashRefillTime = 1f;
	public float dashInputBufferTime = 0.1f;

	/*
	 * Attack
	 */
	public float attackMultiplicator = 1;
	public float attackGravityMult;
	// Simple Attack
	public float attackInputBufferTime;
	public float attackTime;
	public float attackEffectTime;
	public float attackDamage;
	public float attackEnergy;
	public FlatVector attackRange;
	public FlatVector attackPosition;
	public FlatVector attackEndPosition = FlatVector.Zero();
	// Long Attack
	public float longAttackInputBufferTime;
	public float longAttackTime;
	public float longAttackEffectTime;
	public float longAttackDamage;
	public float longAttackEnergy;
	public FlatVector longAttackRange;
	public FlatVector longAttackPosition;
	public FlatVector longAttackEndPosition = FlatVector.Zero();
	// SpecialAttack
	public float specialAttackInputBufferTime;
	public float specialAttackTime;
	public float specialAttackEnergy;

	/*
	 * Health
	 */
	public float startHealth = 100;
	public float maxHealth = 200;
	public float damageBufferTime = 1f;

	/*
	 * Energy
	 */
	public float startEnergy = 10;
	public float maxEnergy = 100;

	protected void onValidate() {
		// Calculate gravity strength using the formula (gravity = 2 * jumpHeight /
		// timeToJumpApex^2)
		gravityStrength = -(2 * jumpHeight) / (jumpTimeToApex * jumpTimeToApex);

		// Calculate the rigidbody's gravity scale (ie: gravity strength relative to
		// unity's gravity value, see project settings/Physics2D)
		gravityScale = gravityStrength / Constante.GRAVITY;

		// Calculate are run acceleration & deceleration forces using formula: amount =
		// ((1 / Time.fixedDeltaTime) * acceleration) / runMaxSpeed
		runAccelAmount = (50 * runAcceleration) / runMaxSpeed;
		runDeccelAmount = (50 * runDecceleration) / runMaxSpeed;

		// Calculate jumpForce using the formula (initialJumpVelocity = gravity *
		// timeToJumpApex)
		jumpForce = Math.abs(gravityStrength) * jumpTimeToApex * jumpMagnitude * Constante.CURRENTITERATIONS;

		runAcceleration = FlatMath.clamp(runAcceleration, 0.01f, runMaxSpeed);
		runDecceleration = FlatMath.clamp(runDecceleration, 0.01f, runMaxSpeed);
	}
}
