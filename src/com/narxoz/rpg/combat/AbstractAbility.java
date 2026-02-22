package com.narxoz.rpg.combat;

public abstract class AbstractAbility implements Ability {
    private final String name;
    private final int damage;
    private final String description;
    protected AbstractAbility(String name, int damage, String description) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Ability name must not be null or empty");
        }
        if (damage < 0) {
            throw new IllegalArgumentException("Damage must be >= 0");
        }
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description must not be null or empty");
        }
        this.name = name;
        this.damage = damage;
        this.description = description;
    }
    @Override
    public String getName() {
        return name;
    }
    @Override
    public int getDamage() {
        return damage;
    }
    @Override
    public String getDescription() {
        return description;
    }
    @Override
    public Ability clone() {
        // Since fields are immutable, simple new instance or shallow OK
        try {
            return (Ability) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e); // wrap, but shouldn't happen
        }
    }

}
