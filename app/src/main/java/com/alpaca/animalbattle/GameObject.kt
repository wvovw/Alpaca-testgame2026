package com.alpaca.animalbattle

import android.graphics.Canvas
import android.graphics.Paint

/**
 * 动物大作战 - 游戏对象基类
 * 所有游戏中的实体都继承此类
 */
abstract class GameObject(
    var x: Float,
    var y: Float,
    var width: Float = 50f,
    var height: Float = 50f
) {
    var velocityX = 0f
    var velocityY = 0f
    var isActive = true

    protected val paint = Paint().apply {
        isAntiAlias = true
    }

    /**
     * 更新游戏对象状态
     * @param deltaTime 距离上一帧的时间（秒）
     */
    abstract fun update(deltaTime: Float)

    /**
     * 渲染游戏对象
     * @param canvas 画布
     */
    abstract fun render(canvas: Canvas)

    /**
     * 检测与另一个对象是否碰撞
     */
    fun intersects(other: GameObject): Boolean {
        return x < other.x + other.width &&
                x + width > other.x &&
                y < other.y + other.height &&
                y + height > other.y
    }

    /**
     * 移动对象
     */
    protected fun move(deltaTime: Float) {
        x += velocityX * deltaTime
        y += velocityY * deltaTime
    }
}
