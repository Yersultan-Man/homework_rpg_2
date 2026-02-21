package com.narxoz.rpg.enemy;

import com.narxoz.rpg.combat.Ability;
import com.narxoz.rpg.loot.LootTable;

import java.util.List;

/**
 * Base interface for all enemies in the RPG system.
 *
 * Every enemy — from a lowly Goblin to an Ancient Dragon — shares
 * certain characteristics: they have stats, abilities, and loot.
 * But HOW they are created varies dramatically.
 *
 * ============================================================
 * WHY THIS INTERFACE MATTERS FOR DESIGN PATTERNS:
 * ============================================================
 *
 * Builder Pattern:
 *   Complex enemies have many fields (stats, abilities, phases, loot, AI).
 *   The Builder pattern constructs enemies step-by-step instead of
 *   cramming everything into one monstrous constructor.
 *   → Think: Should Enemy be immutable once built? (Hint: YES!)
 *
 * Prototype Pattern:
 *   This interface includes a clone() method. Enemies must be CLONABLE
 *   so we can create variants efficiently:
 *     Base Goblin → Elite Goblin → Goblin Champion → Goblin King
 *   → Think: What needs DEEP copying? What can be SHALLOW copied?
 *
 * Factory Method:
 *   The Builder's build() method IS a factory method — it produces
 *   Enemy objects. Different builders produce different enemy types.
 *
 * Abstract Factory:
 *   Enemy components (abilities, loot) come from themed factories.
 *   A FireComponentFactory guarantees all components match the fire theme.
 *
 * ============================================================
 * YOUR TASKS:
 * ============================================================
 *
 * TODO: Decide — should this be an interface or abstract class?
 *   - Interface: If implementations are very different
 *   - Abstract class: If you want shared fields (name, health, etc.)
 *   Hint: An abstract class with shared stat fields might be cleaner.
 *
 * TODO: Define the core enemy contract.
 *   Every enemy should provide:
 *   - Basic stats (health, damage, defense, speed)
 *   - Abilities they can use
 *   - Loot they drop when defeated
 *   - Information display (for the demo)
 *   - Clone method (for Prototype pattern)
 *
 * TODO: Think about immutability.
 *   - Once built by the Builder, should enemy stats change?
 *   - Should clone() return a mutable or immutable copy?
 *   - How do you allow Prototype to modify cloned stats?
 */
public interface Enemy {

    // TODO: Define core stat methods
    // - String getName()
    // - int getHealth()
    // - int getDamage()
    // - int getDefense()
    // - int getSpeed()

    // TODO: Define ability methods
    // - List<Ability> getAbilities()

    // TODO: Define loot methods
    // - LootTable getLootTable()

    // TODO: Define display method
    // - void displayInfo()   (shows all stats, abilities, loot)

    // TODO: Define clone method for Prototype pattern
    // - Enemy clone()
    //
    // CRITICAL: This must perform DEEP COPY!
    // If you do shallow copy, cloned enemies will share ability
    // and loot references with the original — causing bugs!
    //
    // Test your clone: modify the clone's abilities.
    // Does the original change? If yes → your copy is too shallow!

    String getName();

    int getHealth();

    int getDamage();

    int getDefense();

    int getSpeed();

    List<Ability> getAbilities();

    LootTable getLootTable();

    void displayInfo();

    Enemy clone();  // For Prototype pattern, with DEEP COPY!

    // Добавлены для Prototype variants: модификация клонов
    void multiplyStats(double multiplier);

    void addAbility(Ability ability);

}
