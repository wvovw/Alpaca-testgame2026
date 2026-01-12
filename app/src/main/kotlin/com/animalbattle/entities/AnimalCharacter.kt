package com.animalbattle.entities

import kotlin.random.Random

/**
 * 动物角色基类 - Animal character base class
 */
class AnimalCharacter(
    val type: CharacterType,
    val isEnemy: Boolean,
    val position: Position,
    var hp: Int = type.maxHp
) {
    companion object {
        private const val ID_RANDOM_RANGE = 1000000
    }
    
    val id: Long = System.nanoTime() + Random.nextInt(ID_RANDOM_RANGE)
    private var attackCooldown: Float = 0f
    private val attackSpeed: Float = 1f // 每秒攻击1次
    
    val attack: Int = type.attack
    val defense: Int = type.defense
    val speed: Float = type.speed
    val range: Float = type.range
    val maxHp: Int = type.maxHp
    
    fun update(deltaTime: Float) {
        // 减少攻击冷却时间
        if (attackCooldown > 0) {
            attackCooldown -= deltaTime
        }
    }
    
    fun canAttack(): Boolean = attackCooldown <= 0
    
    fun performAttack() {
        attackCooldown = 1f / attackSpeed
    }
    
    /**
     * 受到伤害（伤害已经是计算后的实际伤害）
     * Takes damage (damage is already the calculated actual damage)
     */
    fun takeDamage(damage: Int) {
        hp = maxOf(0, hp - damage)
    }
    
    fun isAlive(): Boolean = hp > 0
    
    fun getHpPercentage(): Float = hp.toFloat() / maxHp
}
