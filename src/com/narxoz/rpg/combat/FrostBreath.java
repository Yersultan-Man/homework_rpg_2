package com.narxoz.rpg.combat;

public class FrostBreath implements Ability {

    @Override
    public String getName() {
        return "Дыхание мороза";
    }

    @Override
    public int getDamage() {
        return 90;
    }

    @Override
    public String getDescription() {
        return "Выдыхает морозный воздух, нанося урон и замедляя врагов.";
    }

    @Override
    public Ability clone() {
        return new FrostBreath();
    }
}
