package com.egc.shopping.enums;

public enum RatingLevel {
    ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5);
    int level;

    RatingLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}
