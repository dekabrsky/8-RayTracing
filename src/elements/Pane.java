package elements;

import draw.Tracer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.MemoryImageSource;

public class Pane extends JComponent
{
	private final Scene scene;
	private final Tracer tracer;
	private Dimension size;
	private int[] pixels;
	private MemoryImageSource imageSource;
	private Image image;
	
	public Pane(Scene scene)
	{
		this.scene = scene;
		tracer = new Tracer(scene);
		
		addComponentListener(new ComponentAdapter()
		{
			@Override
			public void componentResized(ComponentEvent event)
			{
				size = null;
				invalidate();
			}
		});
	}

	@Override
	protected void paintComponent(Graphics graphics)
	{
		if (image == null)
			createImage();
		graphics.drawImage(image, 0, 0, this);
	}

	@Override
	public void invalidate()
	{
		super.invalidate();
		image = null;
	}

	private void createImage()
	{
		if (size == null)
		{
			size = new Dimension(getSize());
			pixels = new int[size.width * size.height];
			imageSource = new MemoryImageSource(size.width, size.height, pixels, 0, size.width);
		}

		tracer.trace(pixels, size.width, size.height);
		
		image = createImage(imageSource);
	}
}
