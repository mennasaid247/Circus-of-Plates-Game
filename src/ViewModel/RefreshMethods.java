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
public class RefreshMethods {

    static void empty(int type,int y ,GameObject m, List<GameObject> left, List<GameObject> right, List<GameObject> control, List<GameObject> moving) {
        Shape s = (Shape) m;
        GameObject clown = control.get(0);
        moving.remove(m);
        s.setClown((ClownObjectSingleton) clown);
        if(y!=0)
            s.setY(y);
        s.setHorizontalOnly(true);
        s.setType(type);
        control.add(m);
        if (type == 1) {
            left.add(m);
        } else if (type == 2) {
            right.add(m);
        }
    }
    

}
