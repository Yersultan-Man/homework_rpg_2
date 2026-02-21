package com.narxoz.rpg.enemy;

import com.narxoz.rpg.combat.Ability;
import com.narxoz.rpg.loot.LootTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Example basic enemy implementation — a simple Goblin.
 *
 * This is provided as a REFERENCE to show enemy structure.
 * Study this implementation, then create more enemies.
 *
 * Notice:
 * - Simple stats (low health, low damage)
 * - Basic constructor (only a few parameters — no Builder needed!)
 * - This is intentionally simple to contrast with DragonBoss.java
 *
 * PROTOTYPE PATTERN NOTE:
 * Goblin is a GREAT candidate for Prototype pattern!
 * Imagine you need 50 goblins for a dungeon. Instead of:
 *     new Goblin("Goblin 1"), new Goblin("Goblin 2"), ...
 *
 * You can:
 *     Goblin template = new Goblin("Goblin");
 *     Enemy goblin1 = template.clone();  // Fast!
 *     Enemy goblin2 = template.clone();  // Fast!
 *
 * And for variants:
 *     Enemy elite = template.clone();
 *     // modify elite's stats to 2x
 *
 * Implemented clone() with DEEP COPY.
 * Primitive stats → shallow copy fine
 * Ability list → deep copied
 * LootTable → deep copied
 */
public class Goblin extends AbstractEnemy {

    Goblin(String name) {
        super(name, 100, 15, 5, 35, new ArrayList<>(), null);
        // Default abilities and loot can be added here if needed
    }

    Goblin(String name, int health, int damage, int defense, int speed, List<Ability> abilities, LootTable lootTable) {
        super(name, health, damage, defense, speed, abilities, lootTable);
    }

    @Override
    public void displayInfo() {
        System.out.println("=== " + getName() + " (Goblin) ===");
        System.out.println("Health: " + getHealth() + " | Damage: " + getDamage()
                + " | Defense: " + getDefense() + " | Speed: " + getSpeed());
        System.out.println("Abilities: " + getAbilities().size() + " ability(ies)");
        for (Ability ability : getAbilities()) {
            System.out.println("  - " + ability.getName() + ": " + ability.getDescription());
        }
        if (getLootTable() != null) {
            System.out.println("Loot: " + getLootTable().getLootInfo());
        }
    }

    @Override
    public Enemy clone() {
        List<Ability> copiedAbilities = new ArrayList<>();
        for (Ability a : getAbilities()) {
            copiedAbilities.add(a.clone());
        }
        LootTable copiedLoot = getLootTable() != null ? getLootTable().clone() : null;
        return new Goblin(getName(), getHealth(), getDamage(), getDefense(), getSpeed(), copiedAbilities, copiedLoot);
    }

}

