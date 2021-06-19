package objects;

import common.Material;
import elements.Vector;

public class Cylinder extends BaseFigure {
    private final double r, rsq;
    private final Vector q;

    public Cylinder(Vector p, double r, Material material)
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

        double a = dotXY(v);
        double b = 2 * dotXY(v, q);
        double c = dotXY(q) - rsq;
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
        return n.set(p).minus(new Vector(getPosition().x, 0, getPosition().z));
    }

    double dotXY(Vector v){
        return v.x * v.x + v.z * v.z; // бесконечный цилиндр определяется двумя координатами, шар - тремя
    }

    double dotXY(Vector v1, Vector v2){
        return v1.x * v2.x + v1.z * v2.z;
    }
}
