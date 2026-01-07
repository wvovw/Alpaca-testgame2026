package com.animalbattle.entities

/**
 * 基地实体类 - Base class for left enemy & right player bases
 */
class BaseEntity(
    val isPlayerBase: Boolean,
    val position: Position,
    var hp: Int = 1000
) {
    val maxHp: Int = 1000
    
    fun takeDamage(damage: Int) {
        hp = maxOf(0, hp - damage)
    }
    
    fun isAlive(): Boolean = hp > 0
    
    fun getHpPercentage(): Float = hp.toFloat() / maxHp
}
