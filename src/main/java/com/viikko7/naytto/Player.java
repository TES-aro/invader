package com.viikko7.naytto;

public class Player {
    boolean pause = false;
    boolean ingame = false;
    int score;
    int lives;
    String name;

    public void setPause(boolean pause) {
        this.pause = pause;
    }

    public int getLives() {
        return lives;
    }

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

    public void setLives(int lives) {
        this.lives = lives;
    }

    public boolean isIngame() {
        return ingame;
    }

    public boolean isPause() {
        return pause;
    }

    public void setIngame(boolean ingame) {
        this.ingame = ingame;
    }
}
