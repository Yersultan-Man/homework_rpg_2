package com.narxoz.rpg.loot;

import java.util.List;

/**
 * Interface for enemy loot/drop tables in the RPG system.
 *
 * Each enemy can have a loot table that determines what items they drop
 * when defeated. Loot tables are themed:
 * - Fire enemies drop fire-related items (Fire Gem, Dragon Scale)
 * - Ice enemies drop ice-related items (Ice Gem, Frost Scale)
 * - Shadow enemies drop shadow-related items (Shadow Gem, Dark Essence)
 *
 * Abstract Factory Connection:
 * Each themed EnemyComponentFactory creates a matching LootTable.
 * This guarantees Fire enemies always drop fire loot — no mixing!
 *
 * Prototype Pattern Note:
 * Loot tables must be DEEP-COPYABLE! When you clone an enemy,
 * its loot table must be an independent copy. Otherwise, modifying
 * a cloned enemy's loot will affect the original.
 */
public interface LootTable {

    List<String> getItems();

    int getGoldDrop();

    int getExperienceDrop();

    String getLootInfo();

    LootTable clone();  // Critical for Prototype pattern!

}
