package src.platform.builder;

import java.io.IOException;

import src.platform.BasicPlatform;

public interface PlatformBuilder {
	public BasicPlatform build() throws IOException;
	
	public BasicPlatform build(float x, float y) throws IOException;
	
	public BasicPlatform build(float x, float y, float width, float height) throws IOException;
}
