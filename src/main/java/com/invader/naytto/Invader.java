package com.invader.naytto;

import javafx.animation.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

import java.util.Timer;
import java.util.TimerTask;

public class Invader extends Ship{
    private Circle graphic = new Circle();
    double speed = 0.1;
    double startPosition;


    public Invader(double xcord, double ycord, double size){
        this.setCoordinates(xcord,ycord);
        setSize(size);
        graphic.setCenterX(xcord);
        graphic.setCenterY(ycord);
        graphic.setRadius(size);
        color = Color.RED;
        graphic.setFill(color);
        graphic.setStroke(Color.BLACK);
        graphic.setStrokeWidth(2);
    }

    public void update(){
        graphic.setCenterY(this.getYcord());
        graphic.setCenterX(this.getXcord());
        graphic.setRadius(this.getSize());
        graphic.setFill(color);
    }

    /*
    public void animate(){
        final Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(5),
                new KeyValue(graphic.translateXProperty(), 500)));
        timeline.setCycleCount(1);
        timeline.play();
    }
    */
    AnimationTimer descend = new AnimationTimer() {
        @Override
        public void handle(long l) {
            if(ycord - startPosition > size*2){
                stop();
                sideways.start();
            }
            else{
                ycord += speed*0.5;
                update();
            }

        }
    };
    AnimationTimer sideways = new AnimationTimer() {
        boolean goingRight = true;

        @Override
        public void handle(long l) {
            if (goingRight) {
                if (xcord > 900 - 1.2 * size) {
                    goingRight = false;
                    stop();
                    startPosition = ycord;
                    descend.start();
                } else {
                    xcord += speed;
                    update();
                }
            } else {
                if (xcord < 1.2 * size) {
                    goingRight = true;
                    speed = 1.1*speed;
                    startPosition = ycord;
                    stop();
                    descend.start();
                } else {
                    xcord -= speed;
                    update();
                }

            }
        }
    };
    public void animate() {
        sideways.start();
        double startSpeed = speed;
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                speed += 0.0014*startSpeed;
            }
        };
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(timerTask,10,10);
    }




    public void setGraphic(Circle graphic) {
        this.graphic = graphic;
    }
    public Circle getGraphic() {
        return graphic;
    }
}
