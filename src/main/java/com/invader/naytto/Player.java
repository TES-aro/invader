package com.invader.naytto;

import java.io.Serializable;

public class Player implements Serializable {
    int score = 0;
    int lives;
    String name = "";

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

}
