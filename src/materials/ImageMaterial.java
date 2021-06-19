package materials;

import common.Orientation;
import elements.Vector;

import java.awt.image.BufferedImage;

public class ImageMaterial extends BaseMaterial{
    BufferedImage image;
    Orientation orientation;

    public ImageMaterial(BufferedImage image, double shine, int phong, Orientation orientation)
    {
        super(shine, phong);
        this.image = image;
        this.orientation = orientation;
    }

    @Override
    public int getClr(Vector p) {
        if (orientation == Orientation.Y)
            return image.getRGB(
                    (int)(Math.abs(p.x)) % image.getWidth(),
                    (int)(Math.abs(p.z)) % image.getHeight()
            );
        else if (orientation == Orientation.X)
            return image.getRGB(
                    (int)(Math.abs(p.y)) % image.getWidth(),
                    (int)(Math.abs(p.z)) % image.getHeight()
            );
        else if (orientation == Orientation.Z)
            return image.getRGB(
                    (int)(Math.abs(p.x)) % image.getWidth(),
                    (int)(Math.abs(p.y)) % image.getHeight()
            );
        return 1;
    }
}
