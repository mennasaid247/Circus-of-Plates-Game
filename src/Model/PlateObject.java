package Model;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import eg.edu.alexu.csd.oop.game.GameObject;

public class PlateObject extends Shape  {

  public PlateObject(int posX, int posY, String path) {
        super(posX, posY, path, 0);
    }
}
