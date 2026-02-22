package com.narxoz.rpg.enemy;

import com.narxoz.rpg.combat.Ability;
import com.narxoz.rpg.loot.LootTable;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractEnemy implements Enemy {

    protected String name;
    protected int health;
    protected int damage;
    protected int defense;
    protected int speed;
    protected List<Ability> abilities = new ArrayList<>();
    protected LootTable lootTable;

    protected AbstractEnemy(String name, int health, int damage, int defense, int speed, List<Ability> abilities, LootTable lootTable) {
        if (name == null || name.isEmpty()) throw new IllegalArgumentException("Name required");
        if (health <= 0) throw new IllegalArgumentException("Health positive");
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.defense = defense;
        this.speed = speed;
        if (abilities != null) this.abilities.addAll(abilities);
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
        return new ArrayList<>(abilities); // defensive copy
    }

    @Override
    public LootTable getLootTable() {
        return lootTable;
    }

    @Override
    public void displayInfo() {
        System.out.println("=== " + name + " ===");
        System.out.println("Health: " + health + " | Damage: " + damage + " | Defense: " + defense + " | Speed: " + speed);
        System.out.println("Abilities:");
        for (Ability a : abilities) {
            System.out.println("- " + a.getName() + ": " + a.getDescription());
        }
        if (lootTable != null) {
            System.out.println("Loot: " + lootTable.getLootInfo());
        }
    }

    public void multiplyStats(double multiplier) {
        if (multiplier <= 0) throw new IllegalArgumentException("Multiplier positive");
        health = (int) (health * multiplier);
        damage = (int) (damage * multiplier);
        defense = (int) (defense * multiplier);
        speed = (int) (speed * multiplier);
    }

    protected void addAbility(Ability ability) {
        if (ability == null) throw new IllegalArgumentException("Ability not null");
        abilities.add(ability);
    }

    @Override
    public abstract Enemy clone();
}

