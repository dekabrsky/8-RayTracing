package common;

import elements.Vector;

public interface Traceable
{
	double getIntersection(Vector u, Vector v);
	Vector getNormal(Vector p, Vector n);
	Material getMaterial();
}
