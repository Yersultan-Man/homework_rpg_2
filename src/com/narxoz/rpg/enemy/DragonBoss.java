package com.narxoz.rpg.enemy;

import com.narxoz.rpg.combat.Ability;
import com.narxoz.rpg.loot.LootTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Example complex boss enemy — THE REASON BUILDER PATTERN EXISTS.
 *
 * ============================================================
 * READ THIS CAREFULLY — THIS IS THE CORE LEARNING MOMENT!
 * ============================================================
 *
 * Look at this constructor. REALLY look at it.
 * Count the parameters. Imagine using it in Main.java.
 * Imagine a teammate trying to understand what each parameter means.
 *
 * This is called the "Telescoping Constructor" anti-pattern.
 * It's the #1 problem that the Builder pattern solves.
 *
 * YOUR MISSION:
 * After studying this horror, you will create an EnemyBuilder
 * that constructs DragonBoss (and other complex enemies)
 * step-by-step, with clear and readable code.
 *
 * Compare:
 *
 *   BEFORE (Telescoping Constructor — current code):
 *   new DragonBoss("Fire Dragon", 50000, 500, 200, 50, "FIRE",
 *       abilities, 30000, 15000, 5000, loot, "AGGRESSIVE",
 *       true, true, 20);
 *   // What does 'true, true, 20' mean? Nobody knows!
 *
 *   AFTER (Builder Pattern — your implementation):
 *   new BossEnemyBuilder()
 *       .setName("Fire Dragon")
 *       .setHealth(50000)
 *       .setDamage(500)
 *       .setDefense(200)
 *       .setSpeed(50)
 *       .setElement("FIRE")
 *       .addAbility(new FlameBreath())
 *       .addAbility(new WingBuffet())
 *       .addPhase(1, 50000)
 *       .addPhase(2, 30000)
 *       .addPhase(3, 15000)
 *       .setLootTable(fireLoot)
 *       .setAI("AGGRESSIVE")
 *       .build();
 *   // Every parameter is labeled! Readable! Maintainable!
 *
 * ============================================================
 * TODO: After implementing your Builder, REFACTOR this class!
 * ============================================================
 * - Remove (or simplify) the telescoping constructor
 * - Make DragonBoss constructable ONLY through a Builder
 * - Make the built DragonBoss IMMUTABLE (no setters!)
 * - The Builder handles all the complexity
 */
public class DragonBoss extends AbstractEnemy {

    private String element;
    private Map<Integer, Integer> phases = new HashMap<>();
    private String aiBehavior;
    private boolean canFly;
    private boolean hasBreathAttack;
    private int wingspan;

    DragonBoss(String name, int health, int damage, int defense, int speed, List<Ability> abilities, LootTable lootTable,
               String element, Map<Integer, Integer> phases, String aiBehavior, boolean canFly, boolean hasBreathAttack, int wingspan) {
        super(name, health, damage, defense, speed, abilities, lootTable);
        this.element = element;
        if (phases != null) {
            this.phases.putAll(phases);
        }
        this.aiBehavior = aiBehavior;
        this.canFly = canFly;
        this.hasBreathAttack = hasBreathAttack;
        this.wingspan = wingspan;
    }

    public String getElement() {
        return element;
    }

    public Map<Integer, Integer> getPhases() {
        return new HashMap<>(phases);  // Defensive copy for encapsulation
    }

    public String getAIBehavior() {
        return aiBehavior;
    }

    public boolean isCanFly() {
        return canFly;
    }

    public boolean isHasBreathAttack() {
        return hasBreathAttack;
    }

    public int getWingspan() {
        return wingspan;
    }

    @Override
    public void displayInfo() {
        System.out.println("=== " + getName() + " (Dragon Boss) ===");
        System.out.println("Health: " + getHealth() + " | Damage: " + getDamage()
                + " | Defense: " + getDefense() + " | Speed: " + getSpeed());
        System.out.println("Element: " + element);
        System.out.println("Abilities (" + getAbilities().size() + "):");
        for (Ability ability : getAbilities()) {
            System.out.println("  - " + ability.getName() + ": " + ability.getDescription());
        }
        System.out.println("Boss Phases: " + phases.size());
        for (Map.Entry<Integer, Integer> phase : phases.entrySet()) {
            System.out.println("  Phase " + phase.getKey()
                    + ": triggers at " + phase.getValue() + " HP");
        }
        System.out.println("AI Behavior: " + aiBehavior);
        System.out.println("Can Fly: " + canFly
                + " | Breath Attack: " + hasBreathAttack
                + " | Wingspan: " + wingspan);
        if (getLootTable() != null) {
            System.out.println("Loot: " + getLootTable().getLootInfo());
        }
    }

    @Override
    public Enemy clone() {
        List<Ability> copiedAbilities = new ArrayList<>();
        for (Ability a : getAbilities()) {
            try {
                copiedAbilities.add(a.clone());
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
        }
        LootTable copiedLoot = getLootTable() != null ? getLootTable().clone() : null;
        Map<Integer, Integer> copiedPhases = new HashMap<>(phases);
        return new DragonBoss(getName(), getHealth(), getDamage(), getDefense(), getSpeed(), copiedAbilities, copiedLoot,
                element, copiedPhases, aiBehavior, canFly, hasBreathAttack, wingspan);
    }

    // Дополнительные setters для Prototype variants (public через интерфейс, но здесь реализация с валидацией)
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

    // Переопределение multiplyStats для босса: также умножаем phases (state transition)
    @Override
    public void multiplyStats(double multiplier) {
        super.multiplyStats(multiplier);
        Map<Integer, Integer> newPhases = new HashMap<>();
        phases.forEach((p, t) -> newPhases.put(p, (int) (t * multiplier)));
        phases = newPhases;
    }

}