package com.narxoz.rpg.enemy;

import com.narxoz.rpg.combat.Ability;
import com.narxoz.rpg.loot.LootTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class for shared enemy logic.
 * Provides encapsulation (private fields + getters), inheritance base.
 * Public methods for Prototype variants (multiplyStats, addAbility) — чтобы модифицировать клоны в demo.
 */
public abstract class AbstractEnemy implements Enemy {

    private String name;
    private int health;
    private int damage;
    private int defense;
    private int speed;
    private List<Ability> abilities = new ArrayList<>();
    private LootTable lootTable;

    protected AbstractEnemy(String name, int health, int damage, int defense, int speed, List<Ability> abilities, LootTable lootTable) {
        this.name = name;
        setHealth(health);  // Валидация состояния
        this.damage = damage;
        this.defense = defense;
        this.speed = speed;
        if (abilities != null) {
            this.abilities.addAll(abilities);
        }
        this.lootTable = lootTable;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public int getDefense() {
        return defense;
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public List<Ability> getAbilities() {
        return new ArrayList<>(abilities);  // Защитная копия для инкапсуляции
    }

    @Override
    public LootTable getLootTable() {
        return lootTable;
    }

    @Override
    public abstract void displayInfo();  // Subclasses override for extra info

    @Override
    public abstract Enemy clone();  // Subclasses implement deep copy

    // Public для Prototype: модификация клонов (state transitions с валидацией)
    @Override
    public void multiplyStats(double multiplier) {
        if (multiplier <= 0) {
            throw new IllegalArgumentException("Multiplier must be positive");
        }
        setHealth((int) (health * multiplier));
        this.damage = (int) (damage * multiplier);
        this.defense = (int) (defense * multiplier);
        this.speed = (int) (speed * multiplier);
    }

    @Override
    public void addAbility(Ability ability) {
        if (ability == null) {
            throw new IllegalArgumentException("Ability cannot be null");
        }
        abilities.add(ability);
    }

    // Protected setter для internal state checks (encapsulation)
    protected void setHealth(int health) {
        if (health <= 0) {
            throw new IllegalArgumentException("Health must be positive");
        }
        this.health = health;
    }

    // Другие protected setters, если нужно (например, setDamage и т.д.)
    protected void setDamage(int damage) {
        this.damage = damage;
    }

    protected void setDefense(int defense) {
        this.defense = defense;
    }

    protected void setSpeed(int speed) {
        this.speed = speed;
    }

}

