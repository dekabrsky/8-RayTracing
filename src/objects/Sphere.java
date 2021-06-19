package objects;

import common.Material;
import elements.Vector;

public class Sphere extends BaseFigure
{
	private final double r, rsq;
	private final Vector q;

	public Sphere(Vector p, double r, Material material)
	{
		super(p, material);
		
		this.r = r;
		rsq = r * r;
		q = new Vector();
	}

	@Override
	public double getIntersection(Vector u, Vector v)
	{
		q.set(u).minus(getPosition());
		
		double a = v.dot();
		double b = 2 * v.dot(q);
		double c = q.dot() - rsq;
		double d = b * b - 4 * a * c;
		
		if (d <= 0) return Double.NaN;
		
		d = Math.sqrt(d);
		double x1 = (b > 0) ? (-b - d) / (2 * a) : (-b + d) / (2 * a);
		double x2 = c / (a * x1);
		
		if (x1 < 0 && x2 < 0) return Double.NaN;

		return Math.min(x1, x2);
	}
	
	@Override
	public Vector getNormal(Vector p, Vector n)
	{
		return n.set(p).minus(getPosition());
	}
}
