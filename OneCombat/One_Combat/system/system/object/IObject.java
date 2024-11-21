package system.object;

import system.object.component.Component;

public interface IObject {
	
	public void destroy();

	public <T> T getComponent (Class<?> c);

	public <T> boolean tryGetComponent(T t);
}
