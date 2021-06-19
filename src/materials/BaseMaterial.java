package materials;

import common.Material;
import elements.Vector;

public abstract class BaseMaterial implements Material
{
	private final double shine;
	private final int phong;

	public BaseMaterial()
	{
		this(0, 0);
	}
	
	public BaseMaterial(double shine, int phong)
	{
		this.shine = shine;
		this.phong = phong;
	}
	
	@Override
	public double getShine(Vector p)
	{
		return shine;
	}
	
	@Override
	public int getPhong(Vector p)
	{
		return phong;
	}
}
