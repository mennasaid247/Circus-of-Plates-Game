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
public class MediumStrategy implements Strategy {

    @Override
    public int getSpeed() {
        return 2;
    }

    @Override
    public int maxShapes() {
        return 7;
    }

    @Override
    public int getTimeout() {
        return 1 * 25 * 1000;
    }

}
