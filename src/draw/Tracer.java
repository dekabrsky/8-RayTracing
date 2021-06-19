package draw;

import common.Material;
import common.Traceable;
import elements.Pixel;
import elements.Scene;
import elements.Vector;

import static java.lang.Math.pow;

public class Tracer
{
	private static final boolean shadowsOn = true;
	private static final Pixel blackPixel = new Pixel();
	private final Scene scene;
	private final Traceable[] models;
	private final Light[] lights;
	private final Vector p, u, v, n, l;
	private final Pixel pixel;

	public Tracer(Scene scene)
	{
		this.scene = scene;
		
		this.models = this.scene.getObjects().toArray(new Traceable[0]);
		this.lights = this.scene.getLights().toArray(new Light[0]);
		
		p = new Vector();
		u = new Vector();
		v = new Vector();
		n = new Vector();
		l = new Vector();
		pixel = new Pixel();
	}

	public void trace(int[] pixels, int width, int height)
	{
		int x0 = width / 2;
		int y0 = height / 2;
		int i = 0;
		
		for (double y = 0; y < height; y++)
			for (double x = 0; x < width; x++)
			{
				u.set(0, 50, 0);
				v.set(x - x0, 50 + y0 - y, 500);
				pixels[i++] = getPixel(u, v, null).getRGB();
			}
	}

	private Pixel getPixel(Vector u, Vector v, Traceable current)
	{
		Traceable traceableToClose = null;
		double minValue = Double.MAX_VALUE;
		double currentMin;
		
		for (Traceable object : models)
			if (object != current)
			{
				currentMin = object.getIntersection(u, v);
				if (!Double.isNaN(currentMin) && (currentMin < minValue))
				{
					minValue = currentMin;
					traceableToClose = object;
				}
			}

		if (traceableToClose == null)
			return blackPixel;

		p.set(v).multiply(minValue).add(u);
		traceableToClose.getNormal(p, n).normalize();
		light(u, v, traceableToClose);

		Material material = traceableToClose.getMaterial();
		pixel.scale(material.getClr(p));

		double shine = material.getShine(p);
		if (shine == 0)
			return pixel;

		u.set(p);
		v.minus(2 * v.dot(n), n);
		Pixel newPixel = new Pixel(pixel.getRGB());
		newPixel.mix(getPixel(u, v, traceableToClose).getRGB(), shine);

		return newPixel;
	}

	private void light(Vector u, Vector v, Traceable closest)
	{
		pixel.set(scene.getAmbient());

		Material material = closest.getMaterial();
		int phong = material.getPhong(p);
		u.set(v).normalize();
		
		for (Light light : lights)
		{
			l.set(light.getPosition()).minus(p);
			if (!shadowsOn || !intersects(p, l, closest))
			{
				l.normalize();
				
				diffuse(light);
				phongOperator(light, phong, u);
			}
		}
	}

	private void diffuse(Light light)
	{
		double d = n.dot(l);
		
		if (d > 0)
			pixel.mix(light.getClr(), d);
	}

	private void phongOperator(Light light, int phong, Vector u)
	{
		if (phong > 0)
		{
			double d = l.minus(2 * l.dot(n), n).dot(u);
			if (d > 0)
				pixel.mix(light.getClr(), pow(d, phong));
		}
	}

	private boolean intersects(Vector u, Vector v, Traceable ignore)
	{
		for (Traceable object : models)
			if (object != ignore && !Double.isNaN(object.getIntersection(u, v)))
				return true;
		return false;
	}
}
