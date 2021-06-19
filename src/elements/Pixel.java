package elements;

import java.awt.*;

public class Pixel
{
	private static final int f = 0xFF;
	private int r, g, b, a;

	public Pixel()
	{
		this(Color.BLACK);
	}
	
	public Pixel(Color color)
	{
		this(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
	}
	
	public Pixel(int rgb)
	{
		this((rgb >> 16) & f, (rgb >> 8) & f, rgb & f);
	}
	
	public Pixel(int r, int g, int b)
	{
		this(r, g, b, f);
	}
	
	public Pixel(int r, int g, int b, int a)
	{
		set(r, g, b, a);
	}
	
	public Pixel set(Color color)
	{
		return set(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
	}
	
	public Pixel set(int r, int g, int b, int a)
	{
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
		return this;
	}

	public Pixel mix(Color color, double brightness)
	{
		return mix(color.getRGB(), brightness);
	}
	
	public Pixel mix(int rgb, double brightness)
	{
		r += ((rgb >> 16) & f) * brightness;
		g += ((rgb >> 8) & f) * brightness;
		b += (rgb & f) * brightness;
		
		return this;
	}
	
	public Pixel scale(int rgb)
	{
		r *= (double) ((rgb >> 16) & f) / f;
		g *= (double) ((rgb >> 8) & f) / f;
		b *= (double) (rgb & f) / f;
		
		return this;
	}
	
	public int getRGB()
	{
		if (r > f)
			r = f;
		if (g > f)
			g = f;
		if (b > f)
			b = f;
		return (a << 24) | (r << 16) | (g << 8) | b;
	}
}
