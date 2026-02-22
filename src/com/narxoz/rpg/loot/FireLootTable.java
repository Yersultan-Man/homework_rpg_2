package com.narxoz.rpg.loot;

import java.util.ArrayList;

import java.util.List;

public class FireLootTable implements LootTable {

    private static final List<String> ITEMS = List.of("Fire Gem", "Dragon Scale", "Flame Rune");

    @Override

    public List<String> getItems() {

        return new ArrayList<>(ITEMS);

    }

    @Override

    public int getGoldDrop() {

        return 500;

    }

    @Override

    public int getExperienceDrop() {

        return 1000;

    }

    @Override

    public String getLootInfo() {

        return "Огненная добыча: " + String.join(", ", getItems()) + " | Золото: " + getGoldDrop() + " | Опыт: " + getExperienceDrop();

    }

    @Override

    public LootTable clone() {

        return new FireLootTable();

    }

}
