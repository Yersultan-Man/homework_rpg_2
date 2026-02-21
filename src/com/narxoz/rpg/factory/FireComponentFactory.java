package com.narxoz.rpg.factory;

import com.narxoz.rpg.combat.Ability;
import com.narxoz.rpg.combat.FlameBreath;
import com.narxoz.rpg.combat.FireShield;
import com.narxoz.rpg.loot.LootTable;
import com.narxoz.rpg.loot.FireLootTable;
import java.util.List;
public class FireComponentFactory implements EnemyComponentFactory {
    @Override
    public List<Ability> createAbilities() {
        return List.of(new FlameBreath(), new FireShield());
    }
    @Override
    public LootTable createLootTable() {
        return new FireLootTable();
    }
    @Override
    public String createAIBehavior() {
        return "AGGRESSIVE";
    }
}