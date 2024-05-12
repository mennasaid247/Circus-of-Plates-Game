package ViewModel;

import ViewModel.Strategy;



public class EasyStrategy implements Strategy {

    @Override
    public int getSpeed() {
        return 1;
    }

    // 3adad el plates 3ala assasha game over
    @Override
    public int maxShapes() {

        return 10;
    }

    @Override
    public int getTimeout() {
        return 1 * 40 * 1000;
    }

}
