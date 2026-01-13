package com.alpaca.animalbattle

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import kotlin.random.Random

/**
 * 动物大作战 - 敌人基类
 */
abstract class Enemy(x: Float, y: Float, width: Float = 50f, height: Float = 50f) : 
    GameObject(x, y, width, height) {
    
    abstract var health: Int
    abstract var damage: Int
    abstract var scoreValue: Int
}

/**
 * 狗狗敌人 - 基础敌人
 */
class DogEnemy(x: Float, y: Float) : Enemy(x, y, 55f, 55f) {
    override var health = 30
    override var damage = 5
    override var scoreValue = 100

    init {
        paint.color = Color.rgb(139, 69, 19) // 棕色
        velocityX = -100f // 向左移动
    }

    override fun update(deltaTime: Float) {
        move(deltaTime)
        
        // 简单的巡逻AI
        if (x < 0) {
            isActive = false
        }
    }

    override fun render(canvas: Canvas) {
        // 绘制狗狗身体
        val bodyRect = RectF(x, y, x + width, y + height)
        canvas.drawRect(bodyRect, paint)
        
        // 绘制眼睛
        val eyePaint = Paint().apply {
            color = Color.RED
        }
        canvas.drawCircle(x + width * 0.3f, y + height * 0.35f, width * 0.1f, eyePaint)
        canvas.drawCircle(x + width * 0.7f, y + height * 0.35f, width * 0.1f, eyePaint)
    }
}

/**
 * 机器人敌人 - 中级敌人
 */
class RobotEnemy(x: Float, y: Float) : Enemy(x, y, 60f, 70f) {
    override var health = 50
    override var damage = 10
    override var scoreValue = 200

    private var shootCooldown = 0f
    private val shootInterval = 2f

    init {
        paint.color = Color.GRAY
        velocityX = -80f
        velocityY = Random.nextFloat() * 40f - 20f // 随机Y轴移动
    }

    override fun update(deltaTime: Float) {
        move(deltaTime)
        
        shootCooldown -= deltaTime
        if (shootCooldown <= 0) {
            shoot()
            shootCooldown = shootInterval
        }
        
        // 边界反弹
        if (y < 0 || y > 600) { // 假设屏幕高度
            velocityY = -velocityY
        }
        
        if (x < 0) {
            isActive = false
        }
    }

    override fun render(canvas: Canvas) {
        // 绘制机器人身体
        val bodyRect = RectF(x, y, x + width, y + height)
        canvas.drawRect(bodyRect, paint)
        
        // 绘制天线
        val antennaPaint = Paint().apply {
            color = Color.YELLOW
            strokeWidth = 3f
        }
        canvas.drawLine(x + width / 2, y, x + width / 2, y - 10f, antennaPaint)
        canvas.drawCircle(x + width / 2, y - 15f, 5f, antennaPaint)
        
        // 绘制眼睛
        val eyePaint = Paint().apply {
            color = Color.CYAN
        }
        canvas.drawRect(x + width * 0.2f, y + height * 0.3f, 
                       x + width * 0.4f, y + height * 0.5f, eyePaint)
        canvas.drawRect(x + width * 0.6f, y + height * 0.3f,
                       x + width * 0.8f, y + height * 0.5f, eyePaint)
    }

    private fun shoot() {
        // TODO: 发射子弹
    }
}

/**
 * Boss敌人 - 高级敌人
 */
class BossEnemy(x: Float, y: Float) : Enemy(x, y, 100f, 120f) {
    override var health = 200
    override var damage = 20
    override var scoreValue = 1000

    private var phase = 1 // Boss战阶段
    private var actionTimer = 0f

    init {
        paint.color = Color.rgb(128, 0, 128) // 紫色
        velocityX = -50f
    }

    override fun update(deltaTime: Float) {
        actionTimer += deltaTime
        
        // 根据血量改变阶段
        when {
            health > 150 -> phase = 1
            health > 80 -> phase = 2
            else -> phase = 3
        }
        
        // 不同阶段的行为
        when (phase) {
            1 -> phase1Behavior(deltaTime)
            2 -> phase2Behavior(deltaTime)
            3 -> phase3Behavior(deltaTime)
        }
        
        move(deltaTime)
    }

    override fun render(canvas: Canvas) {
        // Boss光环效果
        val auraPaint = Paint().apply {
            color = Color.rgb(255, 0, 255)
            alpha = 50
        }
        canvas.drawCircle(x + width / 2, y + height / 2, width * 0.8f, auraPaint)
        
        // 绘制Boss身体
        val bodyRect = RectF(x, y, x + width, y + height)
        canvas.drawOval(bodyRect, paint)
        
        // 绘制王冠
        val crownPaint = Paint().apply {
            color = Color.YELLOW
        }
        val crownPoints = floatArrayOf(
            x + width * 0.3f, y,
            x + width * 0.35f, y - 15f,
            x + width * 0.5f, y - 5f,
            x + width * 0.65f, y - 15f,
            x + width * 0.7f, y
        )
        for (i in 0 until crownPoints.size - 2 step 2) {
            canvas.drawLine(crownPoints[i], crownPoints[i + 1],
                          crownPoints[i + 2], crownPoints[i + 3], crownPaint)
        }
        
        // 绘制血条
        drawHealthBar(canvas)
    }

    private fun drawHealthBar(canvas: Canvas) {
        val barWidth = width
        val barHeight = 8f
        val barY = y - 15f
        
        val bgPaint = Paint().apply { color = Color.DKGRAY }
        canvas.drawRect(x, barY, x + barWidth, barY + barHeight, bgPaint)
        
        val healthPaint = Paint().apply { color = Color.RED }
        val healthWidth = barWidth * (health.toFloat() / 200)
        canvas.drawRect(x, barY, x + healthWidth, barY + barHeight, healthPaint)
    }

    private fun phase1Behavior(deltaTime: Float) {
        // 阶段1：缓慢移动，定期射击
        if (actionTimer > 3f) {
            // TODO: 发射攻击
            actionTimer = 0f
        }
    }

    private fun phase2Behavior(deltaTime: Float) {
        // 阶段2：更快的移动和攻击
        velocityX = -70f
        if (actionTimer > 2f) {
            // TODO: 发射更多攻击
            actionTimer = 0f
        }
    }

    private fun phase3Behavior(deltaTime: Float) {
        // 阶段3：最快的移动，狂暴攻击
        velocityX = -100f
        velocityY = if ((actionTimer * 2).toInt() % 2 == 0) 50f else -50f
        if (actionTimer > 1f) {
            // TODO: 发射大量攻击
            actionTimer = 0f
        }
    }
}
