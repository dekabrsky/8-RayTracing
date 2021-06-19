package gui;

import common.Orientation;
import draw.Light;
import elements.Pane;
import elements.Scene;
import elements.Vector;
import materials.ColorMaterial;
import materials.ImageMaterial;
import objects.Cylinder;
import objects.Plane;
import objects.Sphere;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BallsFrame extends JFrame {
    public BallsFrame() throws IOException {
        super();

        setLocationByPlatform(true);
        setSize(300, 200);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Scene scene = new Scene(new Color(50, 50, 50));

        Plane surface = new Plane(new Vector(), new Vector(0, 1, 0), new ColorMaterial(Color.GRAY, 1, 50));
        scene.addObject(surface);

        File grass= new File("src\\images\\1.jpg");
        BufferedImage grassImg = ImageIO.read(grass);
        Plane surface2 = new Plane(
                new Vector(-1000, 1, -1000),
                new Vector(0, 1, 0),
                new ImageMaterial(grassImg, 0, 50, Orientation.Y));
        scene.addObject(surface2);

        File ball = new File("src\\images\\2.jpg");
        BufferedImage ballImg = ImageIO.read(ball);

        Sphere sphere1 = new Sphere(
                new Vector(-200, 500, 600), 200,
                new ColorMaterial(Color.BLUE, 0.5, 50));
        scene.addObject(sphere1);

        Sphere sphere2 = new Sphere(
                new Vector(200, 200, 300), 200,
                new ColorMaterial(Color.RED, 0.5, 50));
        scene.addObject(sphere2);

        Cylinder barbell = new Cylinder(
                new Vector(0, 0, 800), 100,
                new ColorMaterial(Color.WHITE, 0.3, 30));
        scene.addObject(barbell); // будем считать это штангой

        Sphere sphere3 = new Sphere(
                new Vector(-200, 100, 400), 100,
                new ImageMaterial(ballImg, 0, 50, Orientation.Z));
        scene.addObject(sphere3);

        Light cometLight = new Light(new Vector(-300, 1000, 250), Color.WHITE);
        scene.addLight(cometLight);

        Pane panel = new Pane(scene);
        setContentPane(panel);
    }

    public static void main(String[] args) throws IOException {
        new BallsFrame().setVisible(true);
    }
}
