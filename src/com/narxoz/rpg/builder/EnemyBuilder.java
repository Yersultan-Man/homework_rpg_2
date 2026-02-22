package com.narxoz.rpg.builder;
import com.narxoz.rpg.combat.Ability;
import com.narxoz.rpg.enemy.Enemy;
import com.narxoz.rpg.loot.LootTable;
import java.util.List;

public interface EnemyBuilder {
    EnemyBuilder setName(String name);
    EnemyBuilder setHealth(int health);
    EnemyBuilder setDamage(int damage);
    EnemyBuilder setDefense(int defense);
    EnemyBuilder setSpeed(int speed);
    EnemyBuilder setAbilities(List<Ability> abilities);
    EnemyBuilder addAbility(Ability ability);
    EnemyBuilder setLootTable(LootTable lootTable);
    EnemyBuilder setElement(String element);
    EnemyBuilder addPhase(int phase, int threshold);
    EnemyBuilder setAIBehavior(String aiBehavior);
    EnemyBuilder setCanFly(boolean canFly);
    EnemyBuilder setHasBreathAttack(boolean hasBreathAttack);
    EnemyBuilder setWingspan(int wingspan);
    Enemy build();  // Factory Method!
}
