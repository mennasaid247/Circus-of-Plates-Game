package ViewModel;

import ViewModel.Strategy;



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Mahinour
 */
public class HardStrategy implements Strategy {

    @Override
    public int getSpeed() {
        return 3;
    }

    @Override
    public int maxShapes() {
        return 4;
    }

    @Override
    public int getTimeout() {
        return 1 * 15 * 1000;
    }

}
