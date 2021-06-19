package draw;

import common.Positionable;
import elements.Vector;

import java.awt.*;

public class Light implements Positionable
{
	private final Vector p;
	
	private final Color clr;

	public Light(Vector p0, Color color)
	{
		this.p = p0;
		this.clr = color;
	}

	@Override
	public Vector getPosition()
	{
		return p;
	}

	public Color getClr()
	{
		return clr;
	}
}
