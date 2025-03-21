package com.invader.naytto;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Game extends Application{
    double prefX;

    private void test(startStop lol){
        lol.start();
    }

    Invader[] invaders(){
        ArrayList<Invader> invadersList = new ArrayList<>();
        for(int i=0;i<40;i++){
            double size = 20;
            double x = 4*size*i+10*Math.sin(i);
            int row = (int)(x / (prefX-2*size));
            x -= row* (prefX-size);
            double y = 3*size + 4*size*row;
            invadersList.add(new Invader(x,y,size,row));
        }
        Invader[] invaders = new Invader[invadersList.size()];
        invaders = invadersList.toArray(invaders);
        return invaders;
    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        UI ui = new UI();
        prefX =ui.getPrefX();

        Defender defender = new Defender(100,400,20);
        Controls controls = new Controls();
        Invader[] invaders = invaders();

        Projectile projectile = new Projectile(300,150, invaders);
        projectile.setSource(defender);


        controls.keyDown(ui.getScene());
        defender.setController(controls);

        controls.keyDown().addListener((observableValue, aBoolean, t1) -> {
            if(!aBoolean){
                defender.start();
            } else {
                defender.stop();
            }
        });
        controls.spaceDown().addListener((observableValue, aBoolean, t1) -> {
            if(!aBoolean && !projectile.isFired()){
                test(projectile);
            }
        });

        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                Invader.descend(invaders);
                for(Invader invader : invaders){
                    invader.animation(invaders);
                }
            }
        };
        animationTimer.start();


        ui.setGraphics(new graphic[]{defender,projectile});
        ui.setGraphics(invaders);
        primaryStage.setScene(ui.getScene());
        primaryStage.show();
    }
}
