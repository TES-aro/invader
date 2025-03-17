package com.invader.naytto;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

public class Controls{
    double speed = 0.2;
    BooleanProperty leftPressed = new SimpleBooleanProperty();
    BooleanProperty rightPressed = new SimpleBooleanProperty();
    BooleanBinding keyPressed = leftPressed.or(rightPressed);

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getSpeed() {
        return speed;
    }

    public Controls(){}
    public boolean isLeftPressed() {
        return leftPressed.get();
    }

    public boolean isRightPressed() {
        return rightPressed.get();
    }

    public void setLeftPressed(boolean leftPressed) {
        this.leftPressed.set(leftPressed);
    }

    public void setRightPressed(boolean rightPressed) {
        this.rightPressed.set(rightPressed);
    }

    public void keyDown(Scene scene, Ship controlTarget){
        scene.setOnKeyReleased(e -> {
            if(e.getCode() == KeyCode.LEFT){
                this.setLeftPressed(false);
            }
            if(e.getCode() == KeyCode.RIGHT){
                this.setRightPressed(false);
            }
        });
        scene.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.LEFT) {
                if (controlTarget.getXcord() > controlTarget.getSize()) {
                    this.setLeftPressed(true);
                }
            }
            if(e.getCode() == KeyCode.RIGHT) {
                if (controlTarget.getXcord() < 900 - controlTarget.getSize()) {
                    this.setRightPressed(true);
                }
            }
        });
    }

    public void fire(Scene scene){
        scene.setOnKeyPressed(e-> {
            if(e.getCode() == KeyCode.SPACE){
                System.out.println("FIRE!");
            }
        });
    }
}
