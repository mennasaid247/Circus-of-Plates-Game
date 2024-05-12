package ViewModel;


import eg.edu.alexu.csd.oop.game.GameObject;

public abstract class State {   //el state design pattern, betetghayar f nos el le3ba

    protected GameObject g;

    public State(GameObject g) {  
        this.g = g;
    }

    public abstract void move(int x, int y);
    
}
