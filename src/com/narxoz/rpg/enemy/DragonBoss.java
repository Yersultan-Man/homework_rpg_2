package com.narxoz.rpg.enemy;

import com.narxoz.rpg.combat.Ability;
import com.narxoz.rpg.combat.FlameBreath;
import com.narxoz.rpg.loot.LootTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DragonBoss extends AbstractEnemy {

    protected String element;
    protected Map<Integer, Integer> phases = new HashMap<>();
    protected String aiBehavior;
    protected boolean canFly;
    protected boolean hasBreathAttack;
    protected int wingspan;

    public DragonBoss(String name, int health, int damage, int defense, int speed, List<Ability> abilities, LootTable lootTable,
                      String element, Map<Integer, Integer> phases, String aiBehavior,
                      boolean canFly, boolean hasBreathAttack, int wingspan) {
        super(name, health, damage, defense, speed, abilities, lootTable);
        if (element == null || element.isEmpty()) throw new IllegalArgumentException("Element required");
        if (phases.isEmpty()) throw new IllegalStateException("Phases required");
        this.element = element;
        this.phases.putAll(phases);
        this.aiBehavior = aiBehavior;
        this.canFly = canFly;
        this.hasBreathAttack = hasBreathAttack;
        this.wingspan = wingspan;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Element: " + element);
        System.out.println("AI Behavior: " + aiBehavior);
        System.out.println("Can Fly: " + canFly + " | Has Breath Attack: " + hasBreathAttack + " | Wingspan: " + wingspan);
        System.out.println("Phases:");
        phases.forEach((p, t) -> System.out.println("  Phase " + p + ": " + t + " HP"));
    }

    @Override
    public Enemy clone() {
        List<Ability> copiedAbilities = new ArrayList<>();
        for (Ability a : abilities) {
            copiedAbilities.add(a.clone());
        }
        Map<Integer, Integer> copiedPhases = new HashMap<>(phases);
        LootTable copiedLoot = lootTable != null ? lootTable.clone() : null;
        return new DragonBoss(name, health, damage, defense, speed, copiedAbilities, copiedLoot,
                element, copiedPhases, aiBehavior, canFly, hasBreathAttack, wingspan);
    }

    // Setters for variants with validation
    public void setElement(String element) {
        if (element == null || element.isEmpty()) {
            throw new IllegalArgumentException("Element cannot be empty");
        }
        this.element = element;
    }

    public void addPhase(int phase, int threshold) {
        if (threshold <= 0) {
            throw new IllegalArgumentException("Threshold must be positive");
        }
        if (phases.containsKey(phase)) {
            throw new IllegalArgumentException("Phase already exists");
        }
        phases.put(phase, threshold);
    }

    public void setAIBehavior(String aiBehavior) {
        this.aiBehavior = aiBehavior;
    }

    @Override
    public void multiplyStats(double multiplier) {
        super.multiplyStats(multiplier);
        Map<Integer, Integer> newPhases = new HashMap<>();
        phases.forEach((p, t) -> newPhases.put(p, (int) (t * multiplier)));
        phases = newPhases;
    }

    @Override
    public void addAbility(FlameBreath flameBreath) {

    }

}