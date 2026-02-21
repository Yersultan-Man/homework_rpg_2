package com.narxoz.rpg.combat;

public class Vanish implements Ability {

    @Override
    public String getName() {
        return "Исчезновение";
    }

    @Override
    public int getDamage() {
        return 0;
    }

    @Override
    public String getDescription() {
        return "Исчезает в тенях, повышая уклонение и готовя засаду.";
    }

    @Override
    public Ability clone() {
        return new Vanish();
    }
}
