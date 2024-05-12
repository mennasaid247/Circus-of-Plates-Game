package ViewModel;
import Model.*;
import eg.edu.alexu.csd.oop.game.GameObject;
import java.awt.Color;

public class GameObjectFactorySingleton {

    private static GameObjectFactorySingleton singleFactory = null;

    private GameObjectFactorySingleton() {
    }

    public static GameObjectFactorySingleton getInstance() {
        if (singleFactory == null) // single_instance = new Singleton(new ClownObject(x,y,paths));
        {
            singleFactory = new GameObjectFactorySingleton();
        }

        return singleFactory;

    }

    public static GameObject getShape(String type, int x, int y) {
        if (type == null) {
            return null;
        }
        if (type.equals("plate")) {
            return new PlateObject(x, y, "/plate" + ((int) (Math.random() * 1000) % 2 + 1) + ".png");
        }
        if (type.equals("Ovale")) {

            return new OvaleObject(x, y, "/Ovale" + ((int) (Math.random() * 1000) % 2 + 1) + ".png");

        }
        if (type.equals("clown")) {
            ClownObjectSingleton clon = ClownObjectSingleton.getInstance(x, y);
            return clon;
            //return (GameObject) Singleton.getInstance(x, y, "/thisClown.png");
            //  return new ClownObject(x,y,"/thisClown.png");
        }
        if (type.equals("bar")) {
            return new BarObject(x, y, 250, true, Color.BLACK);
        }
        return null;
    }
}
