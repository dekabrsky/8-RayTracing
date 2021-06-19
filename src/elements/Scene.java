package elements;

import common.Traceable;
import draw.Light;

import java.awt.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Scene
{
	private final Color ambient;
	private final Set<Traceable> models;
	private final Set<Light> lights;

	public Scene()
	{
		this(Color.BLACK);
	}
	
	public Scene(Color ambient)
	{
		this.ambient = ambient;
		models = new HashSet<>();
		lights = new HashSet<>();
	}

	public Color getAmbient()
	{
		return ambient;
	}
	
	public void addObject(Traceable object)
	{
		models.add(object);
	}
	
	public Set<Traceable> getObjects()
	{
		return Collections.unmodifiableSet(models);
	}
	
	public void addLight(Light light)
	{
		lights.add(light);
	}
	
	public Set<Light> getLights()
	{
		return Collections.unmodifiableSet(lights);
	}
}
