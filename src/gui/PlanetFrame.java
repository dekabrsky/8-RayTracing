package gui;

import animation.Animator;
import draw.Light;
import elements.Scene;
import elements.Pane;
import elements.Vector;
import materials.ColorMaterial;
import objects.Plane;
import objects.Sphere;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class PlanetFrame extends JFrame
{
	private static final double angleSample = 2 * Math.PI / 360;

	public PlanetFrame(){
		super();
		
		setLocationByPlatform(true);
		setSize(300, 200);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		Scene scene = new Scene(new Color(50, 50, 50));

		Plane floor = new Plane(new Vector(), new Vector(0, 1, 0), new ColorMaterial(Color.CYAN, 0.5, 50));
		scene.addObject(floor);

		Sphere planet = new Sphere(new Vector(0, 300, 1000), 200, new ColorMaterial(Color.YELLOW, 0.5, 50));
		scene.addObject(planet);
		
		Sphere orange = new Sphere(new Vector(0, 300, 1000), 30, new ColorMaterial(Color.ORANGE, 0.5, 50));
		scene.addObject(orange);
		
		Sphere pink = new Sphere(new Vector(0, 300, 1000), 50, new ColorMaterial(Color.PINK, 0.5, 50));
		scene.addObject(pink);

		Sphere blue = new Sphere(new Vector(0, 300, 1000), 60, new ColorMaterial(Color.BLUE, 0.5, 50));
		scene.addObject(blue);

		Sphere red = new Sphere(new Vector(0, 300, 1000), 45, new ColorMaterial(Color.RED, 0.5, 50));
		scene.addObject(red);

		Sphere comet = new Sphere(new Vector(0, 300, 1000), 10, new ColorMaterial(Color.WHITE, 0, 50));
		scene.addObject(comet);
		Light cometLight = new Light(new Vector(-11, 311, 1011), Color.WHITE); //источник света, летающий рядом с кометой
		scene.addLight(cometLight);

		
		Light l1 = new Light(new Vector(-500, 500, 1000), Color.DARK_GRAY);
		scene.addLight(l1);
		
		Light l2 = new Light(new Vector(0, 500, 1000), Color.GRAY);
		scene.addLight(l2);
		
		Pane panel = new Pane(scene);
		Animator animator = new Animator(panel);

		// анимация крутящихся планет
		animator.moveY(orange, 200, angleSample, 20);
		animator.moveY(pink, 300, angleSample, 25);
		animator.moveY(blue, 400, angleSample, 30);
		animator.moveY(red, 500, angleSample, 40);

		// анимация кометы
		animator.moveY(comet, 250, angleSample, 60);
		animator.moveZ(comet, 250, angleSample, 60);
		animator.moveY(cometLight, 450, angleSample, 60);
		animator.moveZ(cometLight, 450, angleSample, 60);
		
		setContentPane(panel);
	}

	public static void main(String[] args) throws IOException {
		new PlanetFrame().setVisible(true);
	}
}
