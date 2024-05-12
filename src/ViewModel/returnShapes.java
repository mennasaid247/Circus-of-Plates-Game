package ViewModel;
import Model.*;


import eg.edu.alexu.csd.oop.game.GameObject;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Mahinour
 */
public class returnShapes {
    private static int j = 0;
    private static int k = 0;
    static void ReturnShapes(int width,GameObject m, List<GameObject> control,List<GameObject> moving)
    {
      Shape s = (Shape) m;
      GameObject clown = control.get(0);
                    moving.remove(s);
                    s.setCurrentstState(new BarState(s));
                    s.setY(43);
                    if (s.getLeftOrRightBar() == 0) {
                        s.setX(-30 * j);
                        //Bar.move(-30 * j , 43);
                        j++;
                    } else {
                        s.setX(width + 30 * k);
                        // Bar.move(width + 30 * k,43)
                        k++;
                    }
                    moving.add(s);
      
      
    
    }
}
