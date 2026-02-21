package com.narxoz.rpg;
import com.narxoz.rpg.builder.BasicEnemyBuilder;
import com.narxoz.rpg.builder.BossEnemyBuilder;
import com.narxoz.rpg.builder.EnemyBuilder;
import com.narxoz.rpg.builder.EnemyDirector;
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
        printFactoryComponents("Лёд",   iceFactory);
        printFactoryComponents("Тень",  shadowFactory);

        // ───────────────────────────────────────────────────────────────
        // PART 2: BUILDER + FACTORY METHOD — создание врагов
        // ───────────────────────────────────────────────────────────────
        System.out.println("\nЧАСТЬ 2: BUILDER + FACTORY METHOD — Создание врагов");
        System.out.println("───────────────────────────────────────────────\n");
        // Простой враг через BasicEnemyBuilder
        EnemyBuilder basicBuilder = new BasicEnemyBuilder();
        Enemy forestGoblin = basicBuilder
                .setName("Лесной Гоблин")
                .setHealth(120)
                .setDamage(18)
                .setDefense(6)
                .setSpeed(40)
                .setAbilities(fireFactory.createAbilities())
                .setLootTable(fireFactory.createLootTable())
                .build();
        System.out.println("Создан простой враг (BasicEnemyBuilder):");
        forestGoblin.displayInfo();
        System.out.println();
        // Сложный босс через BossEnemyBuilder + тематическая фабрика
        EnemyBuilder bossBuilder = new BossEnemyBuilder();
        Enemy fireDragon = bossBuilder
                .setName("Древний Огненный Дракон")
                .setHealth(48000)
                .setDamage(480)
                .setDefense(180)
                .setSpeed(45)
                .setElement("ОГОНЬ")
                .setAbilities(fireFactory.createAbilities())
                .setLootTable(fireFactory.createLootTable())
                .setAIBehavior(fireFactory.createAIBehavior())
                .addPhase(1, 48000)
                .addPhase(2, 32000)
                .addPhase(3, 16000)
                .setCanFly(true)
                .setHasBreathAttack(true)
                .setWingspan(18)
                .build();
        System.out.println("Создан босс (BossEnemyBuilder + FireFactory):");
        fireDragon.displayInfo();
        System.out.println();
        // Директор — пресеты
        EnemyDirector director = new EnemyDirector(new BossEnemyBuilder());
        Enemy shadowRaidBoss = director.createRaidBoss(shadowFactory);
        System.out.println("Создан рейд-босс через Director + ShadowFactory:");
        shadowRaidBoss.displayInfo();
        System.out.println();
        // ───────────────────────────────────────────────────────────────
        // PART 3: PROTOTYPE — клонирование и варианты
        // ───────────────────────────────────────────────────────────────
        System.out.println("\nЧАСТЬ 3: PROTOTYPE — Клонирование и варианты");
        System.out.println("───────────────────────────────────────────────\n");
        EnemyRegistry registry = new EnemyRegistry();
        // Регистрируем шаблоны
        registry.registerTemplate("goblin", forestGoblin);
        registry.registerTemplate("fire-dragon", fireDragon);
        // Создаём элитного гоблина (2× характеристики + доп. способность)
        Enemy eliteGoblin = registry.createFromTemplate("goblin");
        eliteGoblin.multiplyStats(2.0);
        eliteGoblin.addAbility(fireFactory.createAbilities().get(0)); // добавляем FlameBreath
        System.out.println("Элитный Гоблин (клон + модификация):");
        eliteGoblin.displayInfo();
        System.out.println();
        // Проверяем, что оригинал не изменился (доказательство глубокого копирования)
        System.out.println("Оригинальный гоблин после клонирования (должен быть без изменений):");
        forestGoblin.displayInfo();
        System.out.println();
        // Клон дракона → чемпион (5× характеристики)
        Enemy championDragon = registry.createFromTemplate("fire-dragon");
        championDragon.multiplyStats(5.0);
        System.out.println("Чемпион Дракон (клон ×5):");
        championDragon.displayInfo();
        System.out.println();
        // ───────────────────────────────────────────────────────────────
        // PART 4: ВСЁ ВМЕСТЕ — полный пайплайн
        // ───────────────────────────────────────────────────────────────
        System.out.println("\nЧАСТЬ 4: ВСЁ ВМЕСТЕ — Полный пайплайн паттернов");
        System.out.println("───────────────────────────────────────────────\n");
        // 1. Abstract Factory → тематические компоненты
        EnemyComponentFactory iceComp = new IceComponentFactory();
        // 2. Builder + Factory Method → собираем босса
        Enemy iceBoss = new BossEnemyBuilder()
                .setName("Ледяной Владыка")
                .setHealth(42000)
                .setDamage(420)
                .setDefense(160)
                .setSpeed(50)
                .setElement("ЛЁД")
                .setAbilities(iceComp.createAbilities())
                .setLootTable(iceComp.createLootTable())
                .setAIBehavior(iceComp.createAIBehavior())
                .addPhase(1, 42000)
                .addPhase(2, 28000)
                .addPhase(3, 14000)
                .setCanFly(true)
                .setHasBreathAttack(true)
                .setWingspan(16)
                .build();
        // 3. Prototype → регистрируем и клонируем
        registry.registerTemplate("ice-boss", iceBoss);
        Enemy greaterIceBoss = registry.createFromTemplate("ice-boss");
        greaterIceBoss.multiplyStats(1.8);
        System.out.println("Оригинальный Ледяной Владыка:");
        iceBoss.displayInfo();
        System.out.println();
        System.out.println("Усиленный Ледяной Владыка (клон ×1.8):");
        greaterIceBoss.displayInfo();
        // ───────────────────────────────────────────────────────────────
        // Итоговая сводка
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
