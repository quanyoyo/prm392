package com.example.prm392;

public class Player {
    private int hp;
    private int money;
    public Player() {
    }

    public Player(int hp, int money) {
        this.hp = hp;
        this.money = money;
    }

    public int getHp() {
        setHp(10);
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMoney() {
        setMoney(100);
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
