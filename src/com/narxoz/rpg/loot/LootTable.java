package com.narxoz.rpg.loot;

import java.util.List;

public interface LootTable {

    List<String> getItems();

    int getGoldDrop();

    int getExperienceDrop();

    String getLootInfo();

    LootTable clone(); // for Prototype

}
