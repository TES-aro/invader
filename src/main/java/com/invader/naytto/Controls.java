package com.invader.naytto;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Shape;

public class Controls{
    double speed = 1;
    private final BooleanProperty leftPressed = new SimpleBooleanProperty();
    private final BooleanProperty rightPressed = new SimpleBooleanProperty();
    private final BooleanProperty spacePress = new SimpleBooleanProperty();
    private final BooleanBinding keyPressed = leftPressed.or(rightPressed);


    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getSpeed() {
        return speed;
    }

    public BooleanBinding keyDown() {
        return keyPressed;
    }

    public BooleanProperty spaceDown(){
        return spacePress;
    }

    public Controls(){}

    public void setSpacePress(boolean spacePress) {
        this.spacePress.set(spacePress);
    }

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

    public void keyDown(Scene scene){
        scene.setOnKeyReleased(e -> {
            if(e.getCode() == KeyCode.LEFT || e.getCode() == KeyCode.A){
                this.setLeftPressed(false);
            }
            if(e.getCode() == KeyCode.RIGHT || e.getCode() == KeyCode.D){
                this.setRightPressed(false);
            }
            if(e.getCode() == KeyCode.SPACE || e.getCode() == KeyCode.W || e.getCode() == KeyCode.UP){
                setSpacePress(false);
            }
        });
        scene.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.LEFT || e.getCode() == KeyCode.A) {
                this.setLeftPressed(true);
            }
            if(e.getCode() == KeyCode.RIGHT || e.getCode() == KeyCode.D) {
                this.setRightPressed(true);

            }
            if(e.getCode() == KeyCode.SPACE || e.getCode() == KeyCode.W || e.getCode() == KeyCode.UP){
                spacePress.set(true);
            }
        });
    }

    static boolean isColliding(Shape x, Shape y){
        return x.getBoundsInParent().intersects(y.getBoundsInParent());
    }




}
