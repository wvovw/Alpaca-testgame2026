package com.animalbattle.entities

/**
 * 5种动物角色类型 - 5 character type definitions
 */
enum class CharacterType(
    val displayName: String,
    val emoji: String,
    val cost: Int,
    val attack: Int,
    val defense: Int,
    val maxHp: Int,
    val speed: Float,
    val range: Float
) {
    CAT_WARRIOR("猫战士", "🐱", 50, 12, 1, 40, 3f, 1f),
    DOG_GUARDIAN("狗守卫", "🐶", 75, 8, 3, 60, 2f, 1f),
    EAGLE_ARCHER("鹰弓手", "🦅", 60, 10, 1, 35, 4f, 3f),
    TURTLE_TANK("龟坦克", "🐢", 100, 6, 4, 100, 1f, 1f),
    RABBIT_MAGE("兔法师", "🐰", 80, 14, 1, 40, 3f, 4f);

    companion object {
        fun random(): CharacterType = values().random()
    }
}
