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

        String element = "OTHER";

        if (!factory.createAbilities().isEmpty()) {

            String firstAbility = factory.createAbilities().get(0).getName();

            if (firstAbility.contains("Fire") || firstAbility.contains("Flame")) {

                element = "FIRE";

            } else if (firstAbility.contains("Frost") || firstAbility.contains("Ice")) {

                element = "ICE";

            } else if (firstAbility.contains("Shadow") || firstAbility.contains("Vanish")) {

                element = "SHADOW";

            }

        }

        return builder

                .setName("Raid Boss Dragon")

                .setHealth(50000)

                .setDamage(500)

                .setDefense(200)

                .setSpeed(50)

                .setElement(element)

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

}