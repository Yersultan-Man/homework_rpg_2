package com.narxoz.rpg.factory;

import com.narxoz.rpg.combat.Ability;
import com.narxoz.rpg.combat.ShadowStrike;   // ← свои классы
import com.narxoz.rpg.combat.Vanish;
import com.narxoz.rpg.loot.ShadowLootTable;
import com.narxoz.rpg.loot.LootTable;

import java.util.List;

public class ShadowComponentFactory implements EnemyComponentFactory {

    @Override
    public List<Ability> createAbilities() {
        return List.of(
                new ShadowStrike(),
                new Vanish()
                // можно добавить DarkNova и т.д.
        );
    }

    @Override
    public LootTable createLootTable() {
        return new ShadowLootTable();
    }

    @Override
    public String createAIBehavior() {
        return "TACTICAL";  // тактическое/хитрое поведение для теневой темы
    }
}
