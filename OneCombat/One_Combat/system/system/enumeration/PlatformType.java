package system.enumeration;

import java.awt.Color;
import java.io.IOException;

import src.platform.BasicPlatform;
import src.platform.builder.BuilderPlateformDamage;
import src.platform.builder.BuilderPlateformExplosion;
import src.platform.builder.BuilderPlateformHeal;
import src.platform.builder.BuilderPlateformPowerDown;
import src.platform.builder.BuilderPlateformPowerUp;
import src.platform.builder.BuilderPlateformSpeed;
import src.platform.builder.BuilderPlateformSpring;
import src.platform.builder.PlatformBuilder;

public enum PlatformType {
	TypeHEAL(new BuilderPlateformHeal(Color.GREEN)),
	TypeDAMAGE(new BuilderPlateformDamage(Color.RED)),
	TypePOWERUP(new BuilderPlateformPowerUp(Color.YELLOW)),
	TypePOWERDOWN(new BuilderPlateformPowerDown(Color.ORANGE)),
	TypeSPEED(new BuilderPlateformSpeed(Color.CYAN)),
	TypeSPRING(new BuilderPlateformSpring(Color.BLUE)),
	TypeEXPLOSION(new BuilderPlateformExplosion(Color.BLACK));
	
	private PlatformBuilder builderPlatform;
	
	private PlatformType(PlatformBuilder builder) {
		this.builderPlatform = builder;
	}
	
	public BasicPlatform buildNewPlatform() throws IOException{
		return this.builderPlatform.build();
	}
	
	public BasicPlatform buildNewPlatform(float x, float y) throws IOException{
		return this.builderPlatform.build(x, y);
	}
	
	public BasicPlatform buildNewPlatform(float x, float y, float width, float height) throws IOException{
		return this.builderPlatform.build(x, y, width, height);
	}
}
