package com.alpaca.animalbattle

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF

/**
 * 动物大作战 - 喵星人玩家角色
 * 具有独特的技能和能力
 */
class CatPlayer(x: Float, y: Float) : GameObject(x, y, 60f, 60f) {

    // 喵星人属性
    var health = 100
    var maxHealth = 100
    var attackPower = 10
    
    // 技能冷却时间
    private var meowAttackCooldown = 0f
    private val meowAttackCooldownTime = 1f
    
    private var nineLivesCooldown = 0f
    private val nineLivesCooldownTime = 30f
    
    private var catnipRageCooldown = 0f
    private val catnipRageCooldownTime = 15f

    // 状态
    private var isInvincible = false
    private var invincibleTime = 0f
    
    private var isRaging = false
    private var rageTime = 0f

    init {
        paint.color = Color.rgb(255, 140, 66) // 橙色喵星人
    }

    override fun update(deltaTime: Float) {
        move(deltaTime)
        
        // 更新技能冷却
        if (meowAttackCooldown > 0) meowAttackCooldown -= deltaTime
        if (nineLivesCooldown > 0) nineLivesCooldown -= deltaTime
        if (catnipRageCooldown > 0) catnipRageCooldown -= deltaTime
        
        // 更新无敌状态
        if (isInvincible) {
            invincibleTime -= deltaTime
            if (invincibleTime <= 0) {
                isInvincible = false
            }
        }
        
        // 更新狂暴状态
        if (isRaging) {
            rageTime -= deltaTime
            if (rageTime <= 0) {
                isRaging = false
                attackPower = 10 // 恢复正常攻击力
            }
        }
        
        // 边界检测（简单示例）
        if (x < 0) x = 0f
        if (y < 0) y = 0f
    }

    override fun render(canvas: Canvas) {
        // 绘制喵星人身体
        val bodyRect = RectF(x, y, x + width, y + height)
        
        // 如果无敌则闪烁
        if (isInvincible && (invincibleTime * 10).toInt() % 2 == 0) {
            paint.alpha = 128
        } else {
            paint.alpha = 255
        }
        
        // 如果狂暴则变红
        if (isRaging) {
            paint.color = Color.RED
        } else {
            paint.color = Color.rgb(255, 140, 66)
        }
        
        canvas.drawOval(bodyRect, paint)
        
        // 绘制耳朵
        val earPaint = Paint(paint).apply {
            color = Color.rgb(255, 100, 46)
        }
        canvas.drawCircle(x + width * 0.25f, y + height * 0.2f, width * 0.15f, earPaint)
        canvas.drawCircle(x + width * 0.75f, y + height * 0.2f, width * 0.15f, earPaint)
        
        // 绘制眼睛
        val eyePaint = Paint().apply {
            color = Color.WHITE
        }
        canvas.drawCircle(x + width * 0.35f, y + height * 0.45f, width * 0.1f, eyePaint)
        canvas.drawCircle(x + width * 0.65f, y + height * 0.45f, width * 0.1f, eyePaint)
        
        // 绘制瞳孔
        val pupilPaint = Paint().apply {
            color = Color.BLACK
        }
        canvas.drawCircle(x + width * 0.35f, y + height * 0.45f, width * 0.05f, pupilPaint)
        canvas.drawCircle(x + width * 0.65f, y + height * 0.45f, width * 0.05f, pupilPaint)
        
        // 绘制血条
        drawHealthBar(canvas)
    }

    private fun drawHealthBar(canvas: Canvas) {
        val barWidth = width
        val barHeight = 5f
        val barY = y - 10f
        
        // 背景
        val bgPaint = Paint().apply {
            color = Color.DKGRAY
        }
        canvas.drawRect(x, barY, x + barWidth, barY + barHeight, bgPaint)
        
        // 血量
        val healthPaint = Paint().apply {
            color = Color.rgb(46, 204, 113)
        }
        val healthWidth = barWidth * (health.toFloat() / maxHealth)
        canvas.drawRect(x, barY, x + healthWidth, barY + barHeight, healthPaint)
    }

    /**
     * 喵喵攻击 - 基础攻击技能
     */
    fun meowAttack(): Boolean {
        if (meowAttackCooldown <= 0) {
            meowAttackCooldown = meowAttackCooldownTime
            // TODO: 发射攻击弹幕
            return true
        }
        return false
    }

    /**
     * 九命神功 - 无敌技能
     */
    fun activateNineLives(): Boolean {
        if (nineLivesCooldown <= 0) {
            nineLivesCooldown = nineLivesCooldownTime
            isInvincible = true
            invincibleTime = 3f // 3秒无敌
            return true
        }
        return false
    }

    /**
     * 猫薄荷狂暴 - 攻击力提升技能
     */
    fun activateCatnipRage(): Boolean {
        if (catnipRageCooldown <= 0) {
            catnipRageCooldown = catnipRageCooldownTime
            isRaging = true
            rageTime = 5f // 5秒狂暴
            attackPower = 30 // 攻击力提升
            return true
        }
        return false
    }

    /**
     * 受到伤害
     */
    fun takeDamage(damage: Int) {
        if (!isInvincible) {
            health -= damage
            if (health < 0) health = 0
        }
    }

    /**
     * 治疗
     */
    fun heal(amount: Int) {
        health += amount
        if (health > maxHealth) health = maxHealth
    }

    fun isDead(): Boolean = health <= 0
}
