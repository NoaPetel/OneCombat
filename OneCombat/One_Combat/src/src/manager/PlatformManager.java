package src.manager;

import java.io.IOException;
import java.util.Random;
import src.platform.BasicPlatform;
import src.platform.actions.PlatformRotate;
import system.Time;
import system.enumeration.PlatformType;
import system.object.component.monobehavior.MonoBehavior;
import system.object.component.transform.Transform;
import system.object.gameobject.GameObject;

public class PlatformManager extends MonoBehavior{
	public static PlatformManager instance;
	private float timer = 0f;
	private float time = 5f;
	
	private float timerMerge = 0f;
	private float timeMerge = 5f;
	public int currentActivePlatform;
	public boolean prioritizeMerge;
	private Random rand;
	
	public PlatformManager(GameObject gameObject, Transform transform) {
		super(gameObject, transform);
		this.rand = new Random();
		instance = this;
		this.currentActivePlatform = 0;
		this.prioritizeMerge = true;
	}
	
	@Override
	public void start() {
		for(int index = 0; index < GameManager.instance.gameSettings.initialPlatformNumber; index++) {
			try {
				this.generateRandomPlatform();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void update() {
		if(this.currentActivePlatform < GameManager.instance.gameSettings.maximalPlatformNumber) {
			this.timer += Time.deltaTime;
			if(this.timer > this.time) {
				try {
					this.generateRandomPlatform();
				} catch(IOException e) {
					e.printStackTrace();
				}
				this.timer = 0;
			}
		}
		
		this.timerMerge += Time.deltaTime;
		if(this.timerMerge > this.timeMerge) {
			if(this.prioritizeMerge == true) {
				this.prioritizeMerge = false;
			}
			else {
				this.prioritizeMerge = true;
			}
			this.timerMerge = 0;
		}
	}
	
	public void generateRandomPlatform() throws IOException {
		PlatformType[] platformArray = PlatformType.values();
		int indexRandom = this.rand.nextInt(7);
		
		// Random X and Y Pos Generation
		float xPos = this.rand.nextFloat()*40;
		xPos -= 15;
		float yPos = this.rand.nextFloat()*60;
		yPos -= 30;
		
		BasicPlatform resPlatform = platformArray[indexRandom].buildNewPlatform(xPos, yPos);
		resPlatform.addComponents(new PlatformRotate(resPlatform, resPlatform.transform));
		this.currentActivePlatform++;
	}
	
	public void generateRandomPlatformAtPos(float xPos, float yPos, float width, float height) throws IOException {
		PlatformType[] platformArray = PlatformType.values();
		int indexRandom = this.rand.nextInt(7);
		platformArray[indexRandom].buildNewPlatform(xPos, yPos, width, height);
		this.currentActivePlatform++;
	}
}
