package Character.Components;

import java.awt.Color;
import java.awt.Graphics;

import system.Physics;
import system.Time;
import system.enumeration.Layer;
import system.object.component.monobehavior.MonoBehavior;
import system.object.component.renderer.SpriteRenderer;
import system.object.component.rigidbody.Rigidbody;
import system.object.component.transform.Transform;
import system.object.gameobject.GameObject;
import system.physics.FlatMath;
import system.physics.FlatVector;

public class CharacterMovement extends MonoBehavior {

	// State Parameters
	public boolean isFacingRight = true;
	protected boolean isJumping = false;
	protected boolean isDashing = false;

	// Timers (also all fields, could be private and a method returning a bool could
	// be used)
	protected float lastOnGroundTime;

	// Jump
	private boolean isJumpCut;
	private boolean isJumpFalling;
	private int bonusJumpsLeft = 1;

	// Dash
	private int dashesLeft = 1;
	private boolean dashRefilling;
	private FlatVector lastDashDir;
	private boolean isDashAttacking = false;
	private float onDashAttackTime;
	private float onEndDashTime;
	private float onDashRefillTime;

	// Input
	private FlatVector moveInput = FlatVector.Zero();
	public float lastPressedJumpTime;
	public float lastPressedDashTime;

	// Components
	CharacterLocomotion characterLocomotion;
	CharacterSound characterSound;
	CharacterAttack characterAttack;
	CharacterHealth characterHealth;
	SpriteRenderer spriteRenderer;
	Rigidbody rb;

	// Checks parameters
	private FlatVector groundCheckPoint = new FlatVector(0f, 0.72f);
	private FlatVector groundCheckSize = new FlatVector(0.49f, 0.03f);

	// CharacterData
	CharacterData characterData;

	public CharacterMovement(GameObject g, Transform t, CharacterData characterData) {
		super(g, t);
		this.characterData = characterData;
	}

	@Override
	public void start() {
		characterLocomotion = gameObject.getComponent(CharacterLocomotion.class);
		characterAttack = gameObject.getComponent(CharacterAttack.class);
		characterHealth = gameObject.getComponent(CharacterHealth.class);
		characterSound = gameObject.getComponent(CharacterSound.class);
		spriteRenderer = (SpriteRenderer) gameObject.getRenderer();
		rb = gameObject.getRigidbody();
		setGravityScale(characterData.gravityScale);
		isFacingRight = true;
	}

	@Override
	public void update() {
	
		// Update Timers
		lastOnGroundTime -= Time.deltaTime;
		lastPressedJumpTime -= Time.deltaTime;
		lastPressedDashTime -= Time.deltaTime;
		
		onDashAttackTime -= Time.deltaTime;
		onDashRefillTime -= Time.deltaTime;
		onEndDashTime -= Time.deltaTime;

		// Input handler
		if (moveInput.x != 0)
			checkDirectionToFace(moveInput.x > 0);

		// Colision Check
		if (!isDashing && !isJumping) {
			// Ground Check
			FlatVector positionCheckGround = new FlatVector(groundCheckPoint.x, groundCheckPoint.y);
			FlatVector position = FlatVector.plus(transform.position, positionCheckGround);
			if (Physics.OverlapPolygon(position, groundCheckSize.x, groundCheckSize.y, Layer.getGroundLayers())
					.size() > 0 && !isJumping) // checks if set box overlaps with ground
			{
				lastOnGroundTime = characterData.coyoteTime; // if so sets the lastGrounded to coyoteTime
			}
		}

		// Jump Checks
		if (isJumping && rb.linearVelocity.y > 0 && canMove()) {
			isJumping = false;
			isJumpFalling = true;
			characterLocomotion.fall();
		}

		if (lastOnGroundTime > 0 && !isJumping) {
			isJumpCut = false;
			bonusJumpsLeft = characterData.airJumps;

			if (!isJumping)
				land();
		}

		if (!isDashing && canMove()) {
			// Jump
			if (canJump() && lastPressedJumpTime > 0) {
				isJumping = true;
				isJumpCut = false;
				isJumpFalling = false;
				jump();
			}
			// DOUBLE JUMP
			else if (lastPressedJumpTime > 0 && bonusJumpsLeft > 0 && !isJumping) {
				isJumping = true;
				isJumpCut = false;
				isJumpFalling = false;
				bonusJumpsLeft--;
				jump();
			}
		}

		// Dash Check
		if (canDash() && lastPressedDashTime > 0) {
			// Freeze game for split second. Adds juiciness and a bit of forgiveness over
			// directional input
			//sleep(characterData.dashSleepTime);

			// If not direction pressed, dash forward
			lastDashDir = isFacingRight ? FlatVector.right() : FlatVector.left();

			isDashing = true;
			isJumping = false;
			isJumpCut = false;

			startDash(lastDashDir);
		}

		// Gravity
		if (!isDashAttacking) {
			// Higher gravity if we've released the jump input or are falling
			if (characterAttack.isAttacking) {
				rb.linearVelocity.x = 0;
				setGravityScale(characterData.gravityScale);
			} else if (rb.linearVelocity.y > 0 && moveInput.y > 0) {
				// Much higher gravity if holding down
				setGravityScale(characterData.gravityScale * characterData.fastFallGravityMult);
				// Caps maximum fall speed, so when falling over large distances we don't
				// accelerate to insanely high speeds
				rb.linearVelocity = new FlatVector(rb.linearVelocity.x,
						Math.min(rb.linearVelocity.y, characterData.maxFastFallSpeed));
			} else if (isJumpCut) {
				// Higher gravity if jump button released
				setGravityScale(characterData.gravityScale * characterData.jumpCutGravityMult);
				rb.linearVelocity = new FlatVector(rb.linearVelocity.x,
						Math.min(rb.linearVelocity.y, characterData.maxFallSpeed));
			} else if ((isJumping || isJumpFalling)
					&& Math.abs(rb.linearVelocity.y) < characterData.jumpHangTimeThreshold) {
				setGravityScale(characterData.gravityScale * characterData.jumpHangGravityMult);
			} else if (rb.linearVelocity.y > 0) {
				// Higher gravity if falling
				setGravityScale(characterData.gravityScale * characterData.fallGravityMult);
				// Caps maximum fall speed, so when falling over large distances we don't
				// accelerate to insanely high speeds
				rb.linearVelocity = new FlatVector(rb.linearVelocity.x,
						Math.min(rb.linearVelocity.y, characterData.maxFallSpeed));
			} else {
				// Default gravity if standing on a platform or moving upwards
				setGravityScale(characterData.gravityScale);
			}
		} else {
			// No gravity when dashing (returns to normal once initial dashAttack phase
			// over)
			dash();
			setGravityScale(0);
		}

		// Update Movement
		// Handle Run
		if (!isDashing && canMove()) {
			run(1);
		} else if (isDashAttacking) {
			run(characterData.dashEndRunLerp);
		}
		
		//Handle Fall
		if(!isJumping && lastOnGroundTime <= 0 && !isDashing && canMove())
            characterLocomotion.fall();
		
		//Handle Dash
		if(isDashAttacking) {
			if(onDashAttackTime <= 0) {
				endDashAttack();
			} else {
				dash();
			}
		} else if(isDashing) {
			if(onEndDashTime <= 0) {
				endDash();
			}
		}
		if(dashRefilling) {
			if(onDashRefillTime < 0f) refillDash(1);
		}
	}
	
	/*
	 * Input Callback
	 */
	
	//Methods which whandle input detected in Update()
    public void onJumpInput()
	{
		lastPressedJumpTime = characterData.jumpInputBufferTime;
	}

	public void onJumpUpInput()
	{
		if (canJumpCut())
			isJumpCut = true;
	}

	public void onDashInput()
	{
		lastPressedDashTime = characterData.dashInputBufferTime;
	}
	
	
	/*
	 * General Methods
	 */
	public void setGravityScale(float scale)
	{
		rb.gravityScale = scale;
	}
	
	public float lerp(float a, float b, float f)
	{
	    return (float) (a * (1.0 - f) + (b * f));
	}
	
	public float sign(float a) {
		return a >= 0 ? 1f : -1f;
	}
	
	/*
	 * Run Methods
	 */
	private void run(float lerpAmount)
	{
		//Calculate the direction we want to move in and our desired velocity
		float targetSpeed = moveInput.x * characterData.runMaxSpeed;
		//We can reduce are control using Lerp() this smooths changes to are direction and speed
		targetSpeed = (float)lerp(rb.linearVelocity.x, targetSpeed, lerpAmount);

		float accelRate;

		//Gets an acceleration value based on if we are accelerating (includes turning) 
		//or trying to decelerate (stop). As well as applying a multiplier if we're air borne.
		if (lastOnGroundTime > 0)
			accelRate = (Math.abs(targetSpeed) > 0.01f) ? characterData.runAccelAmount : characterData.runDeccelAmount;
		else
			accelRate = (Math.abs(targetSpeed) > 0.01f) ? characterData.runAccelAmount * characterData.accelInAir : characterData.runDeccelAmount * characterData.deccelInAir;


		//Increase are acceleration and maxSpeed when at the apex of their jump, makes the jump feel a bit more bouncy, responsive and natural
		if ((isJumping || isJumpFalling) && Math.abs(rb.linearVelocity.y) < characterData.jumpHangTimeThreshold)
		{
			accelRate *= characterData.jumpHangAccelerationMult;
			targetSpeed *= characterData.jumpHangMaxSpeedMult;

		}
		//We won't slow the player down if they are moving in their desired direction but at a greater speed than their maxSpeed
		if(characterData.doConserveMomentum && Math.abs(rb.linearVelocity.x) > Math.abs(targetSpeed) && sign(rb.linearVelocity.x) == sign(targetSpeed) && Math.abs(targetSpeed) > 0.01f && lastOnGroundTime < 0)
		{
			//Prevent any deceleration from happening, or in other words conserve are current momentum
			//You could experiment with allowing for the player to slightly increae their speed whilst in this "state"
			accelRate = 0; 
		}

		//Calculate difference between current velocity and desired velocity
		float speedDif = targetSpeed - rb.linearVelocity.x;
		//Calculate force along x-axis to apply to thr player

		float movement = speedDif * accelRate;

		//Convert this to a vector and apply to rigidbody
		rb.addForce(FlatVector.times(FlatVector.right(), movement));

        if(lastOnGroundTime > 0 && (targetSpeed > 0.1f || targetSpeed < -0.1f))
            characterLocomotion.run();
        else if(lastOnGroundTime > 0)
        	characterLocomotion.idle();

		/*
		 * For those interested here is what AddForce() will do
		 * rb.linearVelocity = new Vector2(rb.linearVelocity.x + (Time.fixedDeltaTime  * speedDif * accelRate) / RB.mass, rb.linearVelocity.y);
		 * Time.fixedDeltaTime is by default in Unity 0.02 seconds equal to 50 FixedUpdate() calls per second
		*/
	}

	private void turn() {
		// stores scale and flips the player along the x axis,
		isFacingRight = !isFacingRight;
		spriteRenderer.setIsFlip(!isFacingRight);
	}

	/*
	 * Jump Methods
	 */
	private void jump()
	{
		//Ensures we can't call Jump multiple times from one press
		lastPressedJumpTime = 0;
		lastOnGroundTime = 0;

		//We increase the force applied if we are falling
		//This means we'll always feel like we jump the same amount 
		//(setting the player's Y velocity to 0 beforehand will likely work the same, but I find this more elegant :D)
		float force = characterData.jumpForce / Time.deltaTime;
		if (rb.linearVelocity.y > 0)
			//force += rb.linearVelocity.y;
			rb.linearVelocity = new FlatVector(rb.linearVelocity.x, 0);
        else if(rb.linearVelocity.y < 0)
        	rb.linearVelocity = new FlatVector(rb.linearVelocity.x, 0);

		FlatVector forceVector = FlatVector.times(FlatVector.up(), force);
		rb.addForce(forceVector);

        characterLocomotion.jump();
	}

	
	/**
	 * Add one jump
	 * @param dir
	 */
	public void jumpPowerUp(int val) {
		characterData.airJumps += val; 
	}
	
	public void speedPowerUp(float val) {
		characterData.runMaxSpeed += val;
	}
	
	/*
	 * Dash Methodes
	 */
	private void startDash(FlatVector dir)
	{
		characterLocomotion.dash();
		characterSound.dash();
		//Overall this method of dashing aims to mimic Celeste, if you're looking for
		// a more physics-based approach try a method similar to that used in the jump

		lastOnGroundTime = 0;
		lastPressedDashTime = 0;

//		float startTime = Time.time;

		dashesLeft--;
		isDashAttacking = true;
		isDashing = true;
		
		setGravityScale(0);
		
		onDashAttackTime = characterData.dashAttackTime;

		//We keep the player's velocity at the dash speed during the "attack" phase (in celeste the first 0.15s)
//		while (Time.time - startTime <= Player.instance.Data.dashAttackTime)
//		{
//			RB.velocity = dir.normalized * Player.instance.Data.dashSpeed;
//			//Pauses the loop until the next frame, creating something of a Update loop. 
//			//This is a cleaner implementation opposed to multiple timers and this coroutine approach is actually what is used in Celeste :D
//			yield return null;
//		}
//
//		startTime = Time.time;
//
//		isDashAttacking = false;
//
//		//Begins the "end" of our dash where we return some control to the player but still limit run acceleration (see Update() and Run())
//		setGravityScale(characterData.gravityScale);
//		rb.linearVelocity = FlatVector.times(FlatMath.normalize(dir), characterData.dashEndSpeed);

//		while (Time.time - startTime <= characterData.dashEndTime)
//		{
//			yield return null;
//		}

		//Dash over
		
	}
	
	private void dash() {
		FlatVector dir = isFacingRight ? FlatVector.right() : FlatVector.left();
		rb.linearVelocity = FlatVector.times(dir, characterData.dashSpeed);
	}
	
	private void endDashAttack() {
		FlatVector dir = isFacingRight ? FlatVector.right() : FlatVector.left();
		setGravityScale(characterData.gravityScale);
		rb.linearVelocity = FlatVector.times(FlatMath.normalize(dir), characterData.dashEndSpeed);
		isDashAttacking = false;
	}
	
	private void endDash() {
		isDashing = false;
		dashRefilling = true;
		onDashRefillTime = characterData.dashRefillTime;
	}

	//Short period before the player is able to dash again
	private void refillDash(int amount)
	{
		dashRefilling = false;
		dashesLeft = Math.min(characterData.dashAmount, dashesLeft + amount);
	}
	
	/*
	 * Other Methods
	 */
	private void land()
    {
        //if(isJumpFalling)
            //playerParticle.PlayParticle(PlayerParticle.Particle.LandParticle);
        isJumpFalling = false;
    }
	
	/*
	 * Checks Methods
	 */
	public void checkDirectionToFace(boolean isMovingRight) {
		if (isMovingRight != isFacingRight && canTurn())
			turn();
	}

	private boolean canTurn() {
		return canMove() && !isDashing;
	}

	private boolean canJump() {
		return lastOnGroundTime > 0 && !isJumping;
	}

	private boolean canJumpCut() {
		return isJumping && rb.linearVelocity.y < 0;
	}

		private boolean canDash()
		{
			if (!isDashing && dashesLeft < characterData.dashAmount &&lastOnGroundTime > 0 && !dashRefilling && canMove())
			{
				//StartCoroutine(nameof(refillDash), 1);
			}
			return dashesLeft > 0 && canMove();
		}


	    public boolean canAttack()
	    {
	        return(!isDashing);
	    }
	    
	    public boolean canMove() {
	    	return !characterAttack.isAttacking && characterHealth.canBeMovable();
	    }
	    
	    /*
	     * Setters Methods
	     */
	    public void setInputVector(FlatVector currentInputVector)
	    {
	        moveInput = currentInputVector;
	    }

	    public void isAttack()
	    {
	        isDashing = false;
	        isJumping = false;
	        isJumpCut = false;
	        //rb.linearVelocity = FlatVector.Zero();
	    }
	    
	    public void isHiting() {
	        isJumping = false;
	        isJumpCut = false;
	    }

	    public boolean isGrounded()
	    {
	        return lastOnGroundTime > 0;
	    }
	
		public void onDrawGizmos(Graphics g) {
			drawRectRelativeGizmos(g, groundCheckPoint, groundCheckSize.x, groundCheckSize.y, Color.red, transform.position, isFacingRight);
		}

}
