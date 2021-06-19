package elements;

import java.util.Objects;

public class Vector
{
	public double x, y, z;

	public Vector()
	{
		this(0, 0, 0);
	}
	
	public Vector(Vector v)
	{
		this(v.x, v.y, v.z);
	}
	
	public Vector(double i)
	{
		this(i, i, i);
	}
	
	public Vector(double x, double y, double z)
	{
		set(x, y, z);
	}

	public Vector set(Vector v)
	{
		x = v.x;
		y = v.y;
		z = v.z;
		
		return this;
	}
	
	public Vector set(double x, double y, double z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		
		return this;
	}
	
	public Vector add(Vector v)
	{
		x += v.x;
		y += v.y;
		z += v.z;
		
		return this;
	}
	
	public Vector minus(Vector v)
	{
		x -= v.x;
		y -= v.y;
		z -= v.z;
		
		return this;
	}
	
	public Vector minus(double s, Vector v)
	{
		x -= s * v.x;
		y -= s * v.y;
		z -= s * v.z;
		
		return this;
	}
	
	public Vector multiply(double s)
	{
		x *= s;
		y *= s;
		z *= s;
		
		return this;
	}

	public Vector rotate3D(Vector a)
	{
		return rotateX(a.x).rotateY(a.y).rotateZ(a.z);
	}
	
	public Vector rotateX(double a)
	{
		double sin = Math.sin(a);
		double cos = Math.cos(a);
		
		double oy = y;
		y = oy * cos - z * sin;
		z = oy * sin + z * cos;
		
		return this;
	}

	public Vector rotateY(double a)
	{
		double sin = Math.sin(a);
		double cos = Math.cos(a);

		double ox = x;
		x = ox * cos - z * sin;
		z = ox * sin + z * cos;

		return this;
	}

	public Vector rotateZ(double a)
	{
		double sin = Math.sin(a);
		double cos = Math.cos(a);

		double ox = x;
		x = ox * cos - y * sin;
		y = ox * sin + y * cos;

		return this;
	}
	
	public Vector normalize()
	{
		double mod = Math.sqrt(x * x + y * y + z * z);
		
		x /= mod;
		y /= mod;
		z /= mod;
		
		return this;
	}
	
	public double dot()
	{
		return x * x + y * y + z * z;
	}
	
	public double dot(Vector v)
	{
		return x * v.x + y * v.y + z * v.z;
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(x, y, z);
	}
	
	@Override
	public boolean equals(Object object)
	{
		if (!(object instanceof Vector))
			return false;
		
		Vector v = (Vector) object;
		return x == v.x && y == v.y && z == v.z;
	}
}
