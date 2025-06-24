package com.example.assignment3;

public class Player {
    private String name;
    private int abilityLevel;

    public Player(String name, int abilityLevel) {
        this.name = name;
        this.abilityLevel = abilityLevel;
    }

    public String getName() {
        return name;
    }

    public int getAbilityLevel() {
        return abilityLevel;
    }
}
