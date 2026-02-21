package com.narxoz.rpg.loot;
import java.util.ArrayList;
import java.util.List;
public class ShadowLootTable implements LootTable {

    private static final List<String> ITEMS = List.of(
            "Shadow Gem",
            "Dark Essence",
            "Shadow Rune",
            "Void Shard"
    );

    @Override
    public List<String> getItems() {
        // Возвращаем защитную копию, чтобы никто не модифицировал оригинальный список
        return new ArrayList<>(ITEMS);
    }

    @Override
    public int getGoldDrop() {
        return 650;
    }

    @Override
    public int getExperienceDrop() {
        return 1350;
    }

    @Override
    public String getLootInfo() {
        return "Теневая добыча: " + String.join(", ", getItems()) +
                " | Золото: " + getGoldDrop() +
                " | Опыт: " + getExperienceDrop();
    }

    @Override
    public LootTable clone() {
        return new ShadowLootTable();
    }
}
