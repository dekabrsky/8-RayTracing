package materials;

import elements.Vector;
import java.awt.*;

public class ColorMaterial extends BaseMaterial
{
	private final int clr;
	public ColorMaterial(Color clr)
	{
		this(clr, 0, 0);
	}
	
	public ColorMaterial(Color color, double shine, int phong)
	{
		super(shine, phong);
		clr = color.getRGB();
	}

	@Override
	public int getClr(Vector p)
	{
		return clr;
	}
}
