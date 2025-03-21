package com.invader.naytto;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public class Ship implements graphic{
    double size;
    double xcord;
    double ycord;
    Color color = Color.PINK;
    Shape graphic;

    public Shape getGraphic() {
        return graphic;
    }

    public void setColor(Color color) {
        this.color = color;
    }

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
