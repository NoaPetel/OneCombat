package src.platform;

import java.awt.Color;
import java.io.IOException;
import java.util.Random;

import src.platform.actions.PlatformBump;
import src.platform.actions.PlatformHealth;
import src.platform.actions.PlatformMerge;
import src.platform.actions.PlatformMove;
import src.platform.actions.PlatformSplit;
import system.enumeration.Layer;
import system.object.component.monobehavior.MonoBehavior;
import system.object.component.renderer.RectRenderer;
import system.object.component.rigidbody.Rigidbody;
import system.object.gameobject.GameObject;

public class BasicPlatform extends GameObject{
	
	private static final float DEFAULT_WIDTH = 10f;
	private static final float DEFAULT_HEIGHT = 1f;
	private static final float DEFAULT_HEALTH = 1f;
	private static final float DEFAULT_SPEED = 1f;
	private Random rand = new Random();
    
    public float width;
    public float height;
    public float health = 1;
    public float speed = 1;
    
    public BasicPlatform() throws IOException {
        super();
        this.width = rand.nextFloat()*DEFAULT_WIDTH;
        if(this.width < 5) {
        	this.width += 5;
        }
        this.height = DEFAULT_HEIGHT;
        this.health = DEFAULT_HEALTH;
        this.speed = DEFAULT_SPEED;
        
        this.renderer = new RectRenderer(this, this.transform, Color.BLUE, width, height);
        this.rb = Rigidbody.CreateBoxBody(0, this.width, this.height, 1, false, 0.5f, Layer.Platform, this, this.transform);//0.5 pour collision    
        
        this.components.add(new PlatformSplit(this, this.transform));
        this.components.add(new PlatformHealth(this, this.transform, this.health));
        this.components.add(new PlatformBump(this, this.transform));
        this.components.add(new PlatformMove(this, this.transform, this.speed));
        this.components.add(new PlatformMerge(this, this.transform));
        rb.freezePosition = true;
    }
    
    /* Pour le builder
     * 
     */
    public BasicPlatform(Color color) throws IOException {
        super();
        this.width = rand.nextFloat()*DEFAULT_WIDTH;
        if(this.width < 5) {
        	this.width += 5;
        }
        this.height = DEFAULT_HEIGHT;
        this.health = DEFAULT_HEALTH;
        this.speed = DEFAULT_SPEED;
        
        this.renderer = new RectRenderer(this, this.transform, color, width, height);
        this.rb = Rigidbody.CreateBoxBody(0, this.width, this.height, 1, false, 0.5f, Layer.Platform, this, this.transform);//0.5 pour collision    
        
        this.components.add(new PlatformSplit(this, this.transform));
        this.components.add(new PlatformHealth(this, this.transform, this.health));
        this.components.add(new PlatformBump(this, this.transform));
        this.components.add(new PlatformMove(this, this.transform, this.speed));
        this.components.add(new PlatformMerge(this, this.transform));
        rb.freezePosition = true;
    }
    
    public BasicPlatform(float xPos, float yPos, Color color) throws IOException {
        super();
        this.width = rand.nextFloat()*DEFAULT_WIDTH;
        if(this.width < 5) {
        	this.width += 5;
        }
        this.height = DEFAULT_HEIGHT;
        this.health = DEFAULT_HEALTH;
        this.speed = DEFAULT_SPEED;
        
        this.setPosition(xPos, yPos);
        this.renderer = new RectRenderer(this, this.transform, color, this.width, this.height);
        this.rb = Rigidbody.CreateBoxBody(0, this.width, this.height, 1, false, 0.5f, Layer.Platform, this, this.transform);//0.5 pour collision 
        
        this.components.add(new PlatformSplit(this, this.transform));
        this.components.add(new PlatformHealth(this, this.transform, this.health));
        this.components.add(new PlatformBump(this, this.transform));
        this.components.add(new PlatformMove(this, this.transform, this.speed));
        this.components.add(new PlatformMerge(this, this.transform));
        rb.freezePosition = true;
    }
    
    /* Constructeur pour le split
     * 
     */
    public BasicPlatform(float xPos, float yPos, Color color, float width, float height) throws IOException {
        super(); //TODO CORRIGER LE TP
        this.width = width;
        this.height = height;
        this.health = DEFAULT_HEALTH;
        this.speed = DEFAULT_SPEED;
        
        this.setPosition(xPos, yPos);
        this.renderer = new RectRenderer(this, this.transform, color, this.width, this.height);
        this.rb = Rigidbody.CreateBoxBody(0, width, height, 1, false, 0.5f, Layer.Platform, this, this.transform);//0.5 pour collision 
        
        this.components.add(new PlatformSplit(this, this.transform));
        this.components.add(new PlatformHealth(this, this.transform, this.health));
        this.components.add(new PlatformBump(this, this.transform));
        this.components.add(new PlatformMove(this, this.transform, this.speed));
        this.components.add(new PlatformMerge(this, this.transform));
        rb.freezePosition = true;
    }
    
    public void addComponents(MonoBehavior script) {
    	this.components.add(script);
    }
    
    public void setPosition(float x, float y) {
    	this.transform.setPositionX(x);
    	this.transform.setPositionY(y);
    }
}
