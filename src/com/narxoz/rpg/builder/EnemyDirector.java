package com.narxoz.rpg.builder;

import com.narxoz.rpg.enemy.Enemy;

import com.narxoz.rpg.factory.EnemyComponentFactory;

public class EnemyDirector {

    private EnemyBuilder builder;

    public EnemyDirector(EnemyBuilder builder) {

        this.builder = builder;

    }

    public Enemy createMinion(EnemyComponentFactory factory) {

        return builder

                .setName("Minion Goblin")

                .setHealth(100)

                .setDamage(15)

                .setDefense(5)

                .setSpeed(35)

                .setAbilities(factory.createAbilities())

                .setLootTable(factory.createLootTable())

                .build();

    }

    public Enemy createRaidBoss(EnemyComponentFactory factory) {

        return builder

                .setName("Raid Boss Dragon")

                .setHealth(50000)

                .setDamage(500)

                .setDefense(200)

                .setSpeed(50)

                .setElement(factory.createAbilities().get(0).getName().contains("Fire") ? "FIRE" : "OTHER")  // Simple theme match

                .setAbilities(factory.createAbilities())

                .setLootTable(factory.createLootTable())

                .setAIBehavior(factory.createAIBehavior())

                .addPhase(1, 50000)

                .addPhase(2, 30000)

                .addPhase(3, 15000)

                .setCanFly(true)

                .setHasBreathAttack(true)

                .setWingspan(20)

                .build();

    }

    // Can add more presets like createElite, createMiniBoss

}
