package com.animalbattle.entities

/**
 * 动物角色基类 - Animal character base class
 */
class AnimalCharacter(
    val type: CharacterType,
    val isEnemy: Boolean,
    val position: Position,
    var hp: Int = type.maxHp
) {
    val id: Long = System.nanoTime() + (Math.random() * 1000000).toLong()
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
    
    fun takeDamage(damage: Int) {
        val actualDamage = maxOf(1, damage - defense)
        hp = maxOf(0, hp - actualDamage)
    }
    
    fun isAlive(): Boolean = hp > 0
    
    fun getHpPercentage(): Float = hp.toFloat() / maxHp
}
