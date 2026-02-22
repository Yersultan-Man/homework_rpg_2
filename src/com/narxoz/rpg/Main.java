package com.narxoz.rpg;
import com.narxoz.rpg.builder.BasicEnemyBuilder;
import com.narxoz.rpg.builder.BossEnemyBuilder;
import com.narxoz.rpg.builder.EnemyBuilder;
import com.narxoz.rpg.builder.EnemyDirector;
import com.narxoz.rpg.combat.FlameBreath;
import com.narxoz.rpg.enemy.Enemy;
import com.narxoz.rpg.factory.EnemyComponentFactory;
import com.narxoz.rpg.factory.FireComponentFactory;
import com.narxoz.rpg.factory.IceComponentFactory;
import com.narxoz.rpg.factory.ShadowComponentFactory;
import com.narxoz.rpg.prototype.EnemyRegistry;
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Демонстрация системы врагов RPG — 4 порождающих паттерна ===\n");
        // ───────────────────────────────────────────────────────────────
        // PART 1: ABSTRACT FACTORY — тематические компоненты
        // ───────────────────────────────────────────────────────────────
        System.out.println("ЧАСТЬ 1: ABSTRACT FACTORY — Тематические компоненты");
        System.out.println("───────────────────────────────────────────────\n");

        EnemyComponentFactory fireFactory   = new FireComponentFactory();
        EnemyComponentFactory iceFactory    = new IceComponentFactory();
        EnemyComponentFactory shadowFactory = new ShadowComponentFactory();

        printFactoryComponents("Огонь", fireFactory);
        printFactoryComponents("Лёд", iceFactory);
        printFactoryComponents("Тень", shadowFactory);

        // ───────────────────────────────────────────────────────────────
        // PART 2: BUILDER — сборка врагов
        // ───────────────────────────────────────────────────────────────
        System.out.println("\nЧАСТЬ 2: BUILDER — Пошаговая сборка врагов");
        System.out.println("───────────────────────────────────────────────\n");

        Enemy goblin = new BasicEnemyBuilder()
                .setName("Лесной Гоблин")
                .setHealth(100)
                .setDamage(15)
                .setDefense(5)
                .setSpeed(35)
                .setAbilities(shadowFactory.createAbilities())
                .setLootTable(shadowFactory.createLootTable())
                .build();
        goblin.displayInfo();

        Enemy dragon = new BossEnemyBuilder()
                .setName("Огненный Дракон")
                .setHealth(50000)
                .setDamage(500)
                .setDefense(200)
                .setSpeed(50)
                .setElement("FIRE")
                .setAbilities(fireFactory.createAbilities())
                .setLootTable(fireFactory.createLootTable())
                .setAIBehavior(fireFactory.createAIBehavior())
                .addPhase(1, 50000)
                .addPhase(2, 30000)
                .addPhase(3, 15000)
                .setCanFly(true)
                .setHasBreathAttack(true)
                .setWingspan(20)
                .build();
        dragon.displayInfo();

        // Director
        EnemyDirector director = new EnemyDirector(new BossEnemyBuilder());
        Enemy raidBoss = director.createRaidBoss(iceFactory);
        raidBoss.displayInfo();

        // ───────────────────────────────────────────────────────────────
        // PART 3: PROTOTYPE — клонирование и варианты
        // ───────────────────────────────────────────────────────────────
        // ЧАСТЬ 3: PROTOTYPE — Клонирование и варианты
        System.out.println("\nЧАСТЬ 3: PROTOTYPE — Клонирование и варианты");
        System.out.println("───────────────────────────────────────────────\n");

        EnemyRegistry registry = new EnemyRegistry();
        registry.registerTemplate("goblin", goblin);
        registry.registerTemplate("dragon", dragon);

        Enemy eliteGoblin = registry.createFromTemplate("goblin");
        eliteGoblin.multiplyStats(2.0);
        eliteGoblin.addAbility(new FlameBreath()); // модификация клону
        System.out.println("Элитный Гоблин (клон + модификация):");
        eliteGoblin.displayInfo();

        System.out.println("\nОригинальный Гоблин (не изменился, deep copy):");
        goblin.displayInfo();

        // ───────────────────────────────────────────────────────────────
        // PART 4: ВСЕ ПАТТЕРНЫ ВМЕСТЕ
        // ───────────────────────────────────────────────────────────────
        System.out.println("\nЧАСТЬ 4: ВСЕ ПАТТЕРНЫ ВМЕСТЕ");
        System.out.println("───────────────────────────────────────────────\n");

        Enemy demon = new BossEnemyBuilder()
                .setName("Демон Тени")
                .setHealth(80000)
                .setDamage(800)
                .setDefense(250)
                .setSpeed(40)
                .setElement("SHADOW")
                .setAbilities(shadowFactory.createAbilities())
                .setLootTable(shadowFactory.createLootTable())
                .setAIBehavior(shadowFactory.createAIBehavior())
                .addPhase(1, 80000)
                .addPhase(2, 40000)
                .addPhase(3, 20000)
                .setCanFly(false)
                .setHasBreathAttack(false)
                .setWingspan(0)
                .build();

        registry.registerTemplate("demon", demon);
        Enemy greaterDemon = registry.createFromTemplate("demon");
        greaterDemon.multiplyStats(1.5);
        greaterDemon.displayInfo();

        // ───────────────────────────────────────────────────────────────
        // СВОДКА
        // ───────────────────────────────────────────────────────────────
        System.out.println("\n═══════════════════════════════════════════════");
        System.out.println("СВОДКА ИСПОЛЬЗОВАННЫХ ПАТТЕРНОВ");
        System.out.println("═══════════════════════════════════════════════");
        System.out.println("• Abstract Factory   — тематические наборы (огонь/лёд/тень)");
        System.out.println("• Builder            — пошаговая сборка сложных врагов");
        System.out.println("• Factory Method     — метод build() в билдерах + Director");
        System.out.println("• Prototype          — шаблоны + глубокое клонирование");
        System.out.println("\n=== Демонстрация завершена ===\n");
    }
    /**
     * Вспомогательный метод — красивый вывод компонентов фабрики
     */
    private static void printFactoryComponents(String theme, EnemyComponentFactory factory) {
        System.out.println("Тема: " + theme);
        System.out.println("  Способности:");
        factory.createAbilities().forEach(a ->
                System.out.println("    • " + a.getName() + " (" + a.getDescription() + ")")
        );
        System.out.println("  Лут: " + factory.createLootTable().getLootInfo());
        System.out.println("  ИИ: " + factory.createAIBehavior());
        System.out.println();
    }
}
