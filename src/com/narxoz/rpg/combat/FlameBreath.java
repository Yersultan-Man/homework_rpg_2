package com.narxoz.rpg.combat;

/**
 * Способность "Дыхание пламени" — огненная атака.
 * Immutable: fixed значения, нет setters.
 */
public class FlameBreath implements Ability {

    @Override
    public String getName() {
        return "Дыхание пламени";
    }

    @Override
    public int getDamage() {
        return 100;  // Fixed damage
    }

    @Override
    public String getDescription() {
        return "Выдыхает конус пламени, нанося урон по площади и поджигая врагов.";
    }

    @Override
    public Ability clone() {
        return new FlameBreath();  // Глубокая копия: новый объект (immutable)
    }
}
