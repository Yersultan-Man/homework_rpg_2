package com.narxoz.rpg.combat;

public class FireShield implements Ability {

    @Override
    public String getName() {
        return "Огненный щит";
    }

    @Override
    public int getDamage() {
        return 0;  // Защитная способность
    }

    @Override
    public String getDescription() {
        return "Создаёт щит из огня, снижающий урон и поджигающий атакующих.";
    }

    @Override
    public Ability clone() {
        return new FireShield();
    }
}
