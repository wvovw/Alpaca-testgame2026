package com.animalbattle.ui

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Color
import android.graphics.RectF
import com.animalbattle.core.GameState
import com.animalbattle.entities.AnimalCharacter

/**
 * UI元素渲染 - UI element rendering
 */
class UIRenderer(private val buttonManager: ButtonManager) {
    private val paint = Paint().apply {
        isAntiAlias = true
    }
    
    fun drawButtons(canvas: Canvas) {
        paint.textSize = 36f
        paint.textAlign = Paint.Align.CENTER
        
        for (button in buttonManager.buttons) {
            // 绘制按钮背景
            paint.color = Color.argb(200, 70, 70, 70)
            paint.style = Paint.Style.FILL
            canvas.drawRoundRect(
                RectF(button.x, button.y, button.x + button.width, button.y + button.height),
                10f, 10f, paint
            )
            
            // 绘制按钮边框
            paint.color = Color.WHITE
            paint.style = Paint.Style.STROKE
            paint.strokeWidth = 3f
            canvas.drawRoundRect(
                RectF(button.x, button.y, button.x + button.width, button.y + button.height),
                10f, 10f, paint
            )
            
            // 绘制文字
            paint.style = Paint.Style.FILL
            paint.color = Color.WHITE
            val emoji = button.type.emoji
            val name = button.type.displayName
            val cost = button.type.cost
            
            canvas.drawText(emoji, button.x + button.width / 2, button.y + 40f, paint)
            paint.textSize = 24f
            canvas.drawText("$name", button.x + button.width / 2, button.y + 65f, paint)
            canvas.drawText("($cost)", button.x + button.width / 2, button.y + 88f, paint)
            paint.textSize = 36f
        }
    }
    
    fun drawCharacters(canvas: Canvas, gameState: GameState) {
        paint.textSize = 48f
        paint.textAlign = Paint.Align.CENTER
        
        // 绘制玩家角色
        for (character in gameState.playerCharacters) {
            drawCharacter(canvas, character, Color.BLUE)
        }
        
        // 绘制敌人角色
        for (character in gameState.enemyCharacters) {
            drawCharacter(canvas, character, Color.RED)
        }
    }
    
    private fun drawCharacter(canvas: Canvas, character: AnimalCharacter, color: Int) {
        // 绘制emoji
        paint.style = Paint.Style.FILL
        paint.color = Color.WHITE
        canvas.drawText(character.type.emoji, character.position.x, character.position.y, paint)
        
        // 绘制血条
        val hpBarWidth = 50f
        val hpBarHeight = 5f
        val hpPercentage = character.getHpPercentage()
        
        // 背景
        paint.color = Color.GRAY
        canvas.drawRect(
            character.position.x - hpBarWidth / 2,
            character.position.y - 35f,
            character.position.x + hpBarWidth / 2,
            character.position.y - 35f + hpBarHeight,
            paint
        )
        
        // 血量
        paint.color = color
        canvas.drawRect(
            character.position.x - hpBarWidth / 2,
            character.position.y - 35f,
            character.position.x - hpBarWidth / 2 + hpBarWidth * hpPercentage,
            character.position.y - 35f + hpBarHeight,
            paint
        )
    }
    
    fun drawBases(canvas: Canvas, gameState: GameState) {
        paint.textSize = 80f
        paint.textAlign = Paint.Align.CENTER
        paint.style = Paint.Style.FILL
        
        // 绘制敌方基地
        paint.color = Color.RED
        canvas.drawText("🏰", gameState.enemyBase.position.x, gameState.enemyBase.position.y, paint)
        
        // 绘制玩家基地
        paint.color = Color.GREEN
        canvas.drawText("🏰", gameState.playerBase.position.x, gameState.playerBase.position.y, paint)
        
        // 绘制基地血条
        drawBaseHpBar(canvas, gameState.enemyBase.position.x, gameState.enemyBase.position.y - 60f, 
                     gameState.enemyBase.getHpPercentage(), Color.RED)
        drawBaseHpBar(canvas, gameState.playerBase.position.x, gameState.playerBase.position.y - 60f,
                     gameState.playerBase.getHpPercentage(), Color.GREEN)
    }
    
    private fun drawBaseHpBar(canvas: Canvas, x: Float, y: Float, hpPercentage: Float, color: Int) {
        val hpBarWidth = 100f
        val hpBarHeight = 10f
        
        // 背景
        paint.color = Color.GRAY
        canvas.drawRect(x - hpBarWidth / 2, y, x + hpBarWidth / 2, y + hpBarHeight, paint)
        
        // 血量
        paint.color = color
        canvas.drawRect(x - hpBarWidth / 2, y, x - hpBarWidth / 2 + hpBarWidth * hpPercentage, 
                       y + hpBarHeight, paint)
    }
}
