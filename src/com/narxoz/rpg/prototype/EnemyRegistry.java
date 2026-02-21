package com.narxoz.rpg.prototype;

import com.narxoz.rpg.enemy.Enemy;

import java.util.HashMap;

import java.util.Map;

public class EnemyRegistry {

    private Map<String, Enemy> templates = new HashMap<>();

    public void registerTemplate(String key, Enemy prototype) {

        templates.put(key, prototype);

    }

    public Enemy createFromTemplate(String key) {

        Enemy template = templates.get(key);

        if (template == null) {

            throw new IllegalArgumentException("Unknown template: " + key);

        }

        return template.clone();  // Always return deep clone!

    }

}
