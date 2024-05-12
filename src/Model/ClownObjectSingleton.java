package Model;


import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import eg.edu.alexu.csd.oop.game.GameObject;

public class ClownObjectSingleton implements GameObject {

    private static final int MAX_MSTATE = 1;
    private BufferedImage[] spriteImages = new BufferedImage[MAX_MSTATE];
    private int x;
    private int y;
    private boolean visible;
    private static ClownObjectSingleton clownSingle;  //null

    private ClownObjectSingleton(int posX, int posY, String path) {
        this.x = posX;
        this.y = posY;
        this.visible = true;
        // create a bunch of buffered images and place into an array, to be displayed sequentially
        try {
            spriteImages[0] = ImageIO.read(getClass().getResourceAsStream(path));
        } catch (IOException e) {

        }
    }

    public static ClownObjectSingleton getInstance(int x, int y) {
        if (clownSingle == null) {
            clownSingle = new ClownObjectSingleton(x, y, "/thisClown.png");
        }
        return clownSingle;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setX(int mX) {
        this.x = mX;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setY(int mY) {  //clown sabet fl y 
    }

    @Override
    public BufferedImage[] getSpriteImages() {
        return spriteImages;
    }

    @Override
    public int getWidth() {
        return spriteImages[0].getWidth();
    }

    @Override
    public int getHeight() {
        return spriteImages[0].getHeight();
    }

    @Override
    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
