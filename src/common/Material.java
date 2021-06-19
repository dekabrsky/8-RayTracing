package common;

import elements.Vector;

public interface Material
{
	int getClr(Vector p);
	double getShine(Vector p);
	int getPhong(Vector p);
}
