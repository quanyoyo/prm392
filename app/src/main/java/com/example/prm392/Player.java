package com.example.prm392;

public class Player {
    private int hp;
    private int money;

    public Player(int hp, int money) {
        this.hp = hp;
        this.money = money;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
