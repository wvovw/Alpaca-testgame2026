package com.animalbattle.ui

import com.animalbattle.entities.CharacterType
import com.animalbattle.systems.CharacterSpawner

/**
 * 按钮管理器 - 5 button management
 */
class ButtonManager(private val spawner: CharacterSpawner) {
    data class Button(
        val type: CharacterType,
        val x: Float,
        val y: Float,
        val width: Float,
        val height: Float
    ) {
        fun contains(touchX: Float, touchY: Float): Boolean {
            return touchX >= x && touchX <= x + width &&
                   touchY >= y && touchY <= y + height
        }
    }
    
    val buttons = listOf(
        Button(CharacterType.CAT_WARRIOR, 400f, 980f, 180f, 100f),
        Button(CharacterType.DOG_GUARDIAN, 600f, 980f, 180f, 100f),
        Button(CharacterType.EAGLE_ARCHER, 800f, 980f, 180f, 100f),
        Button(CharacterType.TURTLE_TANK, 1000f, 980f, 180f, 100f),
        Button(CharacterType.RABBIT_MAGE, 1200f, 980f, 180f, 100f)
    )
    
    fun handleTouch(x: Float, y: Float): Boolean {
        for (button in buttons) {
            if (button.contains(x, y)) {
                return spawner.spawnCharacter(button.type)
            }
        }
        return false
    }
}
