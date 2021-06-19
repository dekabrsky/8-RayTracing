package objects;

import common.Positionable;
import common.Material;
import common.Traceable;
import elements.Vector;

public abstract class BaseFigure implements Positionable, Traceable
{
	protected final Vector position;
	protected final Material material;

	public BaseFigure(Vector p, Material material)
	{
		this.position = p;
		this.material = material;
	}

	@Override
	public Vector getPosition()
	{
		return position;
	}

	@Override
	public Material getMaterial()
	{
		return material;
	}
}
