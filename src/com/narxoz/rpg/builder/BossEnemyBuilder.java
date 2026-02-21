package com.narxoz.rpg.builder;

import com.narxoz.rpg.combat.Ability;

import com.narxoz.rpg.enemy.DragonBoss;

import com.narxoz.rpg.enemy.Enemy;

import com.narxoz.rpg.loot.LootTable;

import java.util.ArrayList;

import java.util.HashMap;

import java.util.List;

import java.util.Map;

public class BossEnemyBuilder implements EnemyBuilder {

    private String name;

    private int health;

    private int damage;

    private int defense;

    private int speed;

    private List<Ability> abilities = new ArrayList<>();

    private LootTable lootTable;

    private String element;

    private Map<Integer, Integer> phases = new HashMap<>();

    private String aiBehavior;

    private boolean canFly;

    private boolean hasBreathAttack;

    private int wingspan;

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

        this.element = element;

        return this;

    }

    @Override

    public EnemyBuilder addPhase(int phase, int threshold) {

        phases.put(phase, threshold);

        return this;

    }

    @Override

    public EnemyBuilder setAIBehavior(String aiBehavior) {

        this.aiBehavior = aiBehavior;

        return this;

    }

    @Override

    public EnemyBuilder setCanFly(boolean canFly) {

        this.canFly = canFly;

        return this;

    }

    @Override

    public EnemyBuilder setHasBreathAttack(boolean hasBreathAttack) {

        this.hasBreathAttack = hasBreathAttack;

        return this;

    }

    @Override

    public EnemyBuilder setWingspan(int wingspan) {

        this.wingspan = wingspan;

        return this;

    }

    @Override

    public Enemy build() {

        if (name == null || name.isEmpty()) {

            throw new IllegalStateException("Name is required!");

        }

        if (health <= 0) {

            throw new IllegalStateException("Health must be positive!");

        }

        if (phases.isEmpty()) {

            throw new IllegalStateException("Boss must have at least one phase!");

        }

        return new DragonBoss(name, health, damage, defense, speed, abilities, lootTable, element, phases, aiBehavior, canFly, hasBreathAttack, wingspan);

    }

}
