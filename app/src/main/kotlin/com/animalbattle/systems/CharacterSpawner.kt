package com.animalbattle.systems

import com.animalbattle.core.GameConstants
import com.animalbattle.core.GameState
import com.animalbattle.entities.AnimalCharacter
import com.animalbattle.entities.CharacterType
import com.animalbattle.entities.Position

/**
 * 玩家角色召唤系统 - Player character summoning system
 */
class CharacterSpawner(
    private val gameState: GameState,
    private val moneyManager: MoneyManager
) {
    fun spawnCharacter(type: CharacterType): Boolean {
        if (!moneyManager.canAfford(type.cost)) {
            return false
        }
        
        if (moneyManager.spend(type.cost)) {
            val spawnY = GameConstants.BASE_Y + (Math.random() * 200 - 100).toFloat()
            val character = AnimalCharacter(
                type = type,
                isEnemy = false,
                position = Position(GameConstants.PLAYER_BASE_X - 50f, spawnY)
            )
            gameState.playerCharacters.add(character)
            return true
        }
        
        return false
    }
}
