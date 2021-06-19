package animation;

import common.Positionable;
import elements.Pane;
import elements.Vector;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Animator
{
	private final Pane panel;
	private final ScheduledExecutorService executor;

	private class WhirlingAnimation implements Runnable // анимация кружения вокруг точки
	{
		private final Vector position, radius, angle, object, delta;

		public WhirlingAnimation(Positionable model, Vector p0, Vector r, Vector delta)
		{
			this.position = p0;
			this.radius = r;
			this.angle = delta;
			
			object = model.getPosition();
			this.delta = new Vector();
		}
		
		@Override
		public void run()
		{
			object.set(radius).rotate3D(delta).add(position);
			delta.add(angle);
			updatePanel();
		}
	}

	public Animator(Pane panel)
	{
		this.panel = panel;
		executor = Executors.newSingleThreadScheduledExecutor();
	}

	public void moveX(Positionable object, double radius, double angle, int period)
	{
		move(object, new Vector(0, radius, radius), new Vector(angle, 0, 0), period);
	}
	
	public void moveY(Positionable object, double radius, double angle, int period)
	{
		move(object, new Vector(radius, 0, radius), new Vector(0, angle, 0), period);
	}
	
	public void moveZ(Positionable object, double radius, double angle, int period)
	{
		move(object, new Vector(radius, radius, 0), new Vector(0, 0, angle), period);
	}
	
	public void move(Positionable object, Vector radius, Vector angle, int period)
	{
		move(object, new Vector(object.getPosition()), radius, angle, period);
	}
	
	public void move(Positionable object, Vector p, Vector r, Vector delta, int period)
	{
		executor.scheduleAtFixedRate(new WhirlingAnimation(object, p, r, delta), 0, period, TimeUnit.MILLISECONDS);
	}

	private void updatePanel()
	{
		panel.invalidate();
		panel.repaint(100);
	}
}
