package com.example.prm392;

public class Player {
    private int attack;
    private int defence;

    public Player(int attack, int defence) {
        this.attack = attack;
        this.defence = defence;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }
}
