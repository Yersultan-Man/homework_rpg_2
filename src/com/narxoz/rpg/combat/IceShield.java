package com.narxoz.rpg.combat;

public class IceShield implements Ability {

    @Override
    public String getName() {
        return "Ледяной щит";
    }

    @Override
    public int getDamage() {
        return 0;
    }

    @Override
    public String getDescription() {
        return "Создаёт щит из льда, снижающий урон и замораживающий атакующих.";
    }

    @Override
    public Ability clone() {
        return new IceShield();
    }
}