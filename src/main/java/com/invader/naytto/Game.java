package com.invader.naytto;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.shape.Shape;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Game extends Application{

    private void test(fuck lol){
        lol.start();
    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        root.setPrefSize(900,500);
        root.setBackground(Background.fill(new Color(0.1,0.1,0.2,1)));
        Scene scene = new Scene(root);

        Defender defender = new Defender(100,400,20);
        Controls controls = new Controls();

        ArrayList<Invader> invadersList = new ArrayList<>();
        for(int i=1;i<40;i++){
            double size = 20;
            double x = 20+3*size*i+10*Math.sin(i);
            int row = (int)(x / (900-size-20));
            x -= row*890;
            double y = 20 +2*size + 4*size*row;
            System.out.println(x + "  //  " + row);
            invadersList.add(new Invader(x,y,size));
        }
        Invader[] invaders = new Invader[invadersList.size()];
        invaders = invadersList.toArray(invaders);
        Projectile projectile = new Projectile(300,150, invaders);
        projectile.setSource(defender);


        controls.keyDown(scene);
        defender.setController(controls);

        controls.keyPressed.addListener((observableValue, aBoolean, t1) -> {
            if(!aBoolean){
                defender.start();
            } else {
                defender.stop();
            }
        });
        controls.spacePress.addListener((observableValue, aBoolean, t1) -> {
            if(!aBoolean && !projectile.isFired()){
                test(projectile);
            }
        });

        for (Invader value : invaders){
            value.animate();
        }



        root.getChildren().addAll(defender.getGraphic(), projectile.getGraphic());
        for (Invader value : invaders) {
            root.getChildren().add(value.getGraphic());
        }
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
