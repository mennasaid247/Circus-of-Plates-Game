package ViewModel;


import java.util.LinkedList;
import java.util.List;
import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;
import java.awt.Color;
import javax.swing.JOptionPane;
import Model.*;

public class CircusWorld implements World {

    //private static final int MAX_TIME = 1 * 60 * 1000;	// 1 minute
    private int score = 0;
    private final long startTime = System.currentTimeMillis();
    private final int width;   //beta3et el le3ba
    private final int height;
    private final List<GameObject> constant = new LinkedList<GameObject>();
    private final List<GameObject> moving = new LinkedList<GameObject>();
    private final List<GameObject> control = new LinkedList<GameObject>();
    private List<GameObject> left = new LinkedList<GameObject>();
    private List<GameObject> right = new LinkedList<GameObject>();
    private Strategy gameStrategy;
//    private int j = 0;
//    private int k = 0;
    private int size;
    private GameObjectIterator g = new GameObjectIterator(moving);
    boolean end = false;
    int flagRefresh = 0;

    public CircusWorld(int screenWidth, int screenHeight, Strategy gameStrategy) {
        this.gameStrategy = gameStrategy;
        width = screenWidth;
        height = screenHeight;

        //factory design pattern 2
        GameObjectFactorySingleton factory = GameObjectFactorySingleton.getInstance();
        control.add(factory.getShape("clown", screenWidth / 3, (int) (screenHeight * 0.6)));
        constant.add(factory.getShape("bar", 0, 50));
        constant.add(factory.getShape("bar", width - 250, 50));

        for (int i = 0; i < 20; i++) {
            //factory design pattern 2
            int x = (int) (Math.random() * 1000) % 2 + 1;
            if (x == 1) {
                moving.add(GameObjectFactorySingleton.getShape("plate", -70 * i, 43));
            } else {
                moving.add(GameObjectFactorySingleton.getShape("Ovale", -70 * i, 43));
            }
        }
        // moving objects (enemy)
        for (int i = 0; i < 20; i++) {
            // factry design pattern 2
            int x = (int) (Math.random() * 1000) % 2 + 1;
            if (x == 1) {
                PlateObject p = (PlateObject) GameObjectFactorySingleton.getShape("plate", width + 70 * i, 43);
                // PlateObject p = new PlateObject(width + 70 * i, 43, "/plate" + ((int) (Math.random() * 1000) % 4 + 1) + ".png");
                p.setLeftOrRightBar(1);  // 1 ya3ni right bar
                moving.add(p);
            } else {
                OvaleObject o = (OvaleObject) GameObjectFactorySingleton.getShape("Ovale", width + 70 * i, 43);
                o.setLeftOrRightBar(1);  // 1 ya3ni right bar
                moving.add(o);
            }
        }
    }

    private boolean intersect(GameObject o1, GameObject o2) {
        return (Math.abs((o1.getX() + o1.getWidth() / 2) - (o2.getX() + o2.getWidth() / 2)) <= o1.getWidth()) && (Math.abs((o1.getY() + o1.getHeight() / 2) - (o2.getY() + o2.getHeight() / 2)) <= o1.getHeight());
    }

    @Override
    public boolean refresh() {
        boolean timeout = System.currentTimeMillis() - startTime > gameStrategy.getTimeout(); // true when the game is over
//        timeFlag = 0;
        if (!end) {
            GameObject clown = control.get(0);
            // ClownObjectSingleton c = (ClownObjectSingleton) control.get(0);
            // moving starts

            // for (int i = 0; i < moving.size(); i++) {
            while (g.hasNext()) {
                // GameObject m = moving.get(i);
                GameObject m = g.next();
                Shape s = (Shape) m;
                int x = m.getX();
                int y = m.getY();
                //y = y + getSpeed();
                y = y + (int) (Math.random() * 1000) % 5;
                if (s.getLeftOrRightBar() == 0) {
                    x = x + getSpeed();//x increases in left bar
                    if (m.getX() + 150 > width / 2) {
                        s.setCurrentstState(new MovingState(s));
                    }
                } else {
                    x = x - getSpeed();// x deacreases in right bar
                    if (m.getX() < width - 300) {
                        s.setCurrentstState(new MovingState(s));
                    }
                }
                s.getCurrentstState().move(x, y);
                if (left.isEmpty()) {
                    
                    if (clownIntersectleft(m)) {
                        //empty(1,m);
                       // flagRefresh=1;
                        RefreshMethods.empty(1,clown.getY() - s.getHeight(),m, left, right, control, moving);
                       // System.out.println("LEFT SIZE ="+ size);
                        size++;
                        
//                        moving.remove(m);
//                        s.setClown((ClownObjectSingleton) clown);
//                        s.setY(clown.getY() - s.getHeight());
//                        s.setHorizontalOnly(true);
//                        s.setType(1);
//                        control.add(m);
//                        left.add(m);
                    }
                } else {
                    if (intersect(m, left.get(left.size() - 1))) {
                        //flagRefresh=3;
                        RefreshMethods.empty(1,left.get(left.size() - 1).getY() - s.getHeight(),m, left, right, control, moving);
//                        moving.remove(m);
//                        s.setClown((ClownObjectSingleton) clown);
//                        s.setY(left.get(left.size() - 1).getY() - s.getHeight());
//                        s.setHorizontalOnly(true);
//                        s.setType(1);
//                        control.add(m);
//                        left.add(m);
                        size++;
                    }
                }
                if (m.getY() > height) {
                    returnShapes.ReturnShapes(width,m,control,moving);
//                    moving.remove(s);
//                    s.setCurrentstState(new BarState(s));
//                    s.setY(43);
//                    if (s.getLeftOrRightBar() == 0) {
//                        s.setX(-30 * j);
//                        //Bar.move(-30 * j , 43);
//                        j++;
//                    } else {
//                        s.setX(width + 30 * k);
//                        // Bar.move(width + 30 * k,43)
//                        k++;
//                    }
//                    moving.add(s);
                }
                updateLeft();
                if (right.isEmpty()) {
                   if (clownIntersectRight(m)) {
                      // empty(2,m);
                       
                       System.out.println("RIGHT SIZE ="+ size);
                        size++;
                        //flagRefresh=2;
                        RefreshMethods.empty(2,0,m, left, right, control, moving);
////                    System.out.println("%%%%%");
//                        moving.remove(m);
//                        // PlateObject p = (PlateObject) m;
//                        s.setClown((ClownObjectSingleton) clown);
//                        s.setHorizontalOnly(true);
//                        s.setType(2);
//                        control.add(m);
//                        right.add(m);
                    }
                } else {
                    if (intersect(m, right.get(right.size() - 1))) {
                        //flagRefresh=4;
                        RefreshMethods.empty(2, right.get(right.size() - 1).getY() - s.getHeight() ,m, left, right, control, moving);
//                        moving.remove(m);
//                        //PlateObject p = (PlateObject) m;
//                        s.setClown((ClownObjectSingleton) clown);
//                        s.setHorizontalOnly(true);
//                        s.setY(right.get(right.size() - 1).getY() - s.getWidth() - 10);
//                        s.setType(2);
//                        control.add(m);
//                        right.add(m);
                        size++;
                    }
                }
                updateRight();
                if (left.size() == gameStrategy.maxShapes() || right.size() == gameStrategy.maxShapes()) {   // JOptionPane.showMessageDialog(null,"HARD LUCK! our clown held the maximum number of shapes.");
                    end = true;
                    return false;
                }
//            if (left.size() == 10||right.size()==10) {
//                return false;
//            }
                if (size == 40) {

                    // JOptionPane.showMessageDialog(null, " PROUD CLOWN =) !\n You passed this level.");
                    return false;

                }
                g.index++;
            }
            g.index = 0;
        }
        if (timeout) {
            end = true;
        }
        return !timeout;

    }

    @Override
    public int getSpeed() {
        return gameStrategy.getSpeed();
    }

    @Override
    public int getControlSpeed() {
        return 20;
    }

    @Override
    public List<GameObject> getConstantObjects() {
        return constant;
    }

    @Override
    public List<GameObject> getMovableObjects() {
        return moving;
    }

    @Override
    public List<GameObject> getControlableObjects() {
        return control;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public String getStatus() {

        return "Score=" + score + "   |   Time=" + Math.max(0, (gameStrategy.getTimeout() - (System.currentTimeMillis() - startTime)) / 1000);	// update status
    }

    private boolean clownIntersectleft(GameObject o) {
        ClownObjectSingleton clown = (ClownObjectSingleton) control.get(0);
        return (Math.abs(clown.getX() - o.getX()) <= o.getWidth() - 10
                && Math.abs(o.getY() - control.get(0).getY()) <= 10);
    }

    private boolean clownIntersectRight(GameObject o) {
        ClownObjectSingleton clown = (ClownObjectSingleton) control.get(0);
        return (Math.abs(clown.getX() + clown.getWidth() - 65 - o.getX()) <= o.getWidth() + 20
                && Math.abs(o.getY() - control.get(0).getY()) <= 10);
    }

    private void updateLeft() {
        if (left.size() >= 3) {
            Shape s1 = (Shape) left.get(left.size() - 1);
            Shape s2 = (Shape) left.get(left.size() - 2);
            Shape s3 = (Shape) left.get(left.size() - 3);
            char pp1 = s1.getPath().charAt(6);
            char pp2 = s2.getPath().charAt(6);
            char pp3 = s3.getPath().charAt(6);
             boolean checkred = ((pp1 == '1' ) && (pp2 == '1') && (pp3 == '1'));
            boolean checkblue = ((pp1 == '2') && (pp2 == '2') && (pp3 == '2' ));
            if (checkred || checkblue) {
                left.remove(left.size() - 1);
                left.remove(left.size() - 1);
                left.remove(left.size() - 1);
                control.remove(s1);
                control.remove(s2);
                control.remove(s3);
                score++;
            }
        }
    }

    private void updateRight() {
        if (right.size() >= 3) {
            Shape s1 = (Shape) right.get(right.size() - 1);
            Shape s2 = (Shape) right.get(right.size() - 2);
            Shape s3 = (Shape) right.get(right.size() - 3);
            char pp1 = s1.getPath().charAt(6);
            char pp2 = s2.getPath().charAt(6);
            char pp3 = s3.getPath().charAt(6);
            boolean checkred = ((pp1 == '1' || pp1 == '1') && (pp2 == '1' || pp2 == '1') && (pp3 == '1' || pp3 == '1'));
            boolean checkblue = ((pp1 == '2' || pp1 == '2') && (pp2 == '2' || pp2 == '2') && (pp3 == '2' || pp3 == '2'));
            if (checkred || checkblue) {
                right.remove(right.size() - 1);
                right.remove(right.size() - 1);
                right.remove(right.size() - 1);
                control.remove(s1);
                control.remove(s2);
                control.remove(s3);
                score++;
            }
        }
    }
//    private void empty(int type,GameObject m)
//    {
//         Shape s = (Shape) m;
//         GameObject clown = control.get(0);
//                       moving.remove(m);
//                        s.setClown((ClownObjectSingleton) clown);
//                        s.setY(clown.getY() - s.getHeight());
//                        s.setHorizontalOnly(true);
//                        s.setType(type);
//                        control.add(m);
//                        if(type ==1)
//                        left.add(m);
//                        else if (type ==2)
//                        right.add(m);
//                        size++;   
//    }
//    private void intersectRefresh(int type,GameObject m)
//    {
//        Shape s = (Shape) m;
//          GameObject clown = control.get(0);
//                        moving.remove(m);
//                        s.setClown((ClownObjectSingleton) clown);
//                        if(type==1)
//                        s.setY(left.get(left.size() - 1).getY() - s.getHeight());
//                        else if (type ==2)
//                      s.setY(right.get(right.size() - 1).getY() - s.getWidth() - 10);
//
//                        s.setHorizontalOnly(true);
//                        s.setType(type);
//                        control.add(m);
//                        if(type ==1)
//                        left.add(m);
//                        else  if (type==2)
//                                right.add(m);
//                                        }
}