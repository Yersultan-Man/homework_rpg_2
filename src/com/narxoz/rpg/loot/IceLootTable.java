package com.narxoz.rpg.loot;

import java.util.ArrayList;
import java.util.List;

public class IceLootTable implements LootTable {

    private static final List<String> ITEMS = List.of(
            "Ice Gem",
            "Frost Scale",
            "Ice Rune",
            "Glacial Crystal"
    );

    @Override
    public List<String> getItems() {
        return new ArrayList<>(ITEMS);
    }

    @Override
    public int getGoldDrop() {
        return 480;
    }

    @Override
    public int getExperienceDrop() {
        return 950;
    }

    @Override
    public String getLootInfo() {
        return "Ледяная добыча: " + String.join(", ", getItems()) +
                " | Золото: " + getGoldDrop() +
                " | Опыт: " + getExperienceDrop();
    }

    @Override
    public LootTable clone() {
        return new IceLootTable();
    }
}
