package com.narxoz.rpg.combat;

public class ShadowStrike implements Ability {

    @Override
    public String getName() {
        return "Теневой удар";
    }

    @Override
    public int getDamage() {
        return 120;
    }

    @Override
    public String getDescription() {
        return "Быстрый удар из тени, наносящий высокий урон одной цели и ослепляющий.";
    }

    @Override
    public Ability clone() {
        return new ShadowStrike();
    }
}
