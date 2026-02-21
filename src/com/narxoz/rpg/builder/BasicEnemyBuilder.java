package com.narxoz.rpg.builder;

import com.narxoz.rpg.combat.Ability;

import com.narxoz.rpg.enemy.Enemy;

import com.narxoz.rpg.enemy.Goblin;

import com.narxoz.rpg.loot.LootTable;

import java.util.ArrayList;

import java.util.List;

public class BasicEnemyBuilder implements EnemyBuilder {

    private String name;

    private int health;

    private int damage;

    private int defense;

    private int speed;

    private List<Ability> abilities = new ArrayList<>();

    private LootTable lootTable;

    @Override

    public EnemyBuilder setName(String name) {

        this.name = name;

        return this;

    }

    @Override

    public EnemyBuilder setHealth(int health) {

        this.health = health;

        return this;

    }

    @Override

    public EnemyBuilder setDamage(int damage) {

        this.damage = damage;

        return this;

    }

    @Override

    public EnemyBuilder setDefense(int defense) {

        this.defense = defense;

        return this;

    }

    @Override

    public EnemyBuilder setSpeed(int speed) {

        this.speed = speed;

        return this;

    }

    @Override

    public EnemyBuilder setAbilities(List<Ability> abilities) {

        this.abilities = new ArrayList<>(abilities);

        return this;

    }

    @Override

    public EnemyBuilder addAbility(Ability ability) {

        abilities.add(ability);

        return this;

    }

    @Override

    public EnemyBuilder setLootTable(LootTable lootTable) {

        this.lootTable = lootTable;

        return this;

    }

    @Override

    public EnemyBuilder setElement(String element) {

        throw new UnsupportedOperationException("Not supported for basic enemies");

    }

    @Override

    public EnemyBuilder addPhase(int phase, int threshold) {

        throw new UnsupportedOperationException("Not supported for basic enemies");

    }

    @Override

    public EnemyBuilder setAIBehavior(String aiBehavior) {

        throw new UnsupportedOperationException("Not supported for basic enemies");

    }

    @Override

    public EnemyBuilder setCanFly(boolean canFly) {

        throw new UnsupportedOperationException("Not supported for basic enemies");

    }

    @Override

    public EnemyBuilder setHasBreathAttack(boolean hasBreathAttack) {

        throw new UnsupportedOperationException("Not supported for basic enemies");

    }

    @Override

    public EnemyBuilder setWingspan(int wingspan) {

        throw new UnsupportedOperationException("Not supported for basic enemies");

    }

    @Override

    public Enemy build() {

        if (name == null || name.isEmpty()) {

            throw new IllegalStateException("Name is required!");

        }

        if (health <= 0) {

            throw new IllegalStateException("Health must be positive!");

        }

        // Default to 0 if not set

        return new Goblin(name, health, damage, defense, speed, abilities, lootTable);

    }

}
