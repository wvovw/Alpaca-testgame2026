package com.animalbattle.ui

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Color
import com.animalbattle.systems.MoneyManager
import com.animalbattle.entities.BaseEntity

/**
 * 资源显示 - Money & HP display
 */
class ResourceDisplay {
    private val paint = Paint().apply {
        textSize = 48f
        isAntiAlias = true
    }
    
    fun drawMoney(canvas: Canvas, moneyManager: MoneyManager) {
        paint.color = Color.YELLOW
        paint.textAlign = Paint.Align.LEFT
        canvas.drawText("金钱: ${moneyManager.currentMoney}", 50f, 80f, paint)
    }
    
    fun drawBases(canvas: Canvas, playerBase: BaseEntity, enemyBase: BaseEntity) {
        paint.textSize = 32f
        
        // 敌方基地
        paint.color = Color.RED
        paint.textAlign = Paint.Align.LEFT
        canvas.drawText("敌方基地", enemyBase.position.x - 50f, enemyBase.position.y - 80f, paint)
        canvas.drawText("HP: ${enemyBase.hp}/${enemyBase.maxHp}", enemyBase.position.x - 50f, enemyBase.position.y - 50f, paint)
        
        // 玩家基地
        paint.color = Color.GREEN
        paint.textAlign = Paint.Align.RIGHT
        canvas.drawText("玩家基地", playerBase.position.x + 50f, playerBase.position.y - 80f, paint)
        canvas.drawText("HP: ${playerBase.hp}/${playerBase.maxHp}", playerBase.position.x + 50f, playerBase.position.y - 50f, paint)
    }
    
    fun drawGameOver(canvas: Canvas, playerWon: Boolean, width: Float, height: Float) {
        paint.textSize = 120f
        paint.textAlign = Paint.Align.CENTER
        paint.color = if (playerWon) Color.GREEN else Color.RED
        
        val text = if (playerWon) "胜利!" else "失败!"
        canvas.drawText(text, width / 2f, height / 2f, paint)
    }
}
