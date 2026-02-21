package com.narxoz.rpg.factory;

import com.narxoz.rpg.combat.Ability;
import com.narxoz.rpg.combat.FrostBreath;    // ← свои классы
import com.narxoz.rpg.combat.IceShield;
import com.narxoz.rpg.loot.IceLootTable;
import com.narxoz.rpg.loot.LootTable;

import java.util.List;

public class IceComponentFactory implements EnemyComponentFactory {

    @Override
    public List<Ability> createAbilities() {
        return List.of(
                new FrostBreath(),
                new IceShield()
                // можно добавить Blizzard и т.д.
        );
    }

    @Override
    public LootTable createLootTable() {
        return new IceLootTable();
    }

    @Override
    public String createAIBehavior() {
        return "DEFENSIVE";  // оборонительное поведение для ледяной темы
    }
}
