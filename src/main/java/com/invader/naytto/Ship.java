package com.invader.naytto;

import javafx.scene.paint.Color;

public class Ship {
    double size;
    double xcord;
    double ycord;
    Color color = Color.PINK;

    public double getYcord() {
        return ycord;
    }
    public double getXcord() {
        return xcord;
    }

    public double[] getCoordinates(){
        return new double[]{this.getXcord(),this.getYcord()};
    }

    public void setYcord(double ycord) {
        this.ycord = ycord;
    }

    public void setXcord(double xcord) {
        this.xcord = xcord;
    }

    public void setCoordinates(double xcord, double ycord) {
        this.ycord = ycord;
        this.xcord = xcord;
    }

    public double getSize() {
        return size;
    }
    public void setSize(double size) {
        this.size = size;
    }


}
