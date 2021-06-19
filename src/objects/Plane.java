package objects;

import common.Material;
import elements.Vector;

public class Plane extends BaseFigure
{
	private final Vector n, q;

	public Plane(Vector p, Vector n, Material material)
	{
		super(p, material);
		this.n = n;
        q = new Vector();
	}

	@Override
	public double getIntersection(Vector u, Vector v)
	{
		double d = v.dot(n);
		if (d == 0) return Double.NaN;

		double res = q.set(getPosition()).minus(u).dot(n) / d;
		if (res < 0) return Double.NaN;

		return res;
	}

	@Override
	public Vector getNormal(Vector p, Vector n)
	{
		return n.set(this.n);
	}
}
