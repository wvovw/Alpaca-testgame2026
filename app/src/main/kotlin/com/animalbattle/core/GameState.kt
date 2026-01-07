package com.animalbattle.core

import com.animalbattle.entities.AnimalCharacter
import com.animalbattle.entities.BaseEntity
import com.animalbattle.entities.Position

/**
 * 游戏状态管理 - Game state management
 */
class GameState {
    // 基地
    val playerBase = BaseEntity(true, Position(GameConstants.PLAYER_BASE_X, GameConstants.BASE_Y))
    val enemyBase = BaseEntity(false, Position(GameConstants.ENEMY_BASE_X, GameConstants.BASE_Y))
    
    // 角色列表
    val playerCharacters = mutableListOf<AnimalCharacter>()
    val enemyCharacters = mutableListOf<AnimalCharacter>()
    
    // 游戏状态
    var isGameOver = false
    var playerWon = false
    var gameTime: Float = 0f
    
    fun update(deltaTime: Float) {
        gameTime += deltaTime
        
        // 检查游戏结束条件
        if (!isGameOver) {
            if (!playerBase.isAlive()) {
                isGameOver = true
                playerWon = false
            } else if (!enemyBase.isAlive()) {
                isGameOver = true
                playerWon = true
            }
        }
    }
    
    fun removeDeadCharacters() {
        playerCharacters.removeAll { !it.isAlive() }
        enemyCharacters.removeAll { !it.isAlive() }
    }
}
