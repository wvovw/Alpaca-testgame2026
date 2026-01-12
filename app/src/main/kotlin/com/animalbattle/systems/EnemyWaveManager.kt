package com.animalbattle.systems

import com.animalbattle.core.GameConstants
import com.animalbattle.core.GameState
import com.animalbattle.entities.AnimalCharacter
import com.animalbattle.entities.CharacterType
import com.animalbattle.entities.Position
import kotlin.random.Random

/**
 * 敌人自动生成系统 - Enemy auto-generation
 */
class EnemyWaveManager(private val gameState: GameState) {
    private var spawnTimer: Float = 0f
    private var difficultyLevel: Int = 1
    private var difficultyTimer: Float = 0f
    
    fun update(deltaTime: Float) {
        // 更新生成计时器
        spawnTimer += deltaTime
        if (spawnTimer >= GameConstants.ENEMY_SPAWN_INTERVAL) {
            spawnEnemy()
            spawnTimer = 0f
        }
        
        // 更新难度计时器
        difficultyTimer += deltaTime
        if (difficultyTimer >= GameConstants.DIFFICULTY_INCREASE_INTERVAL) {
            difficultyLevel++
            difficultyTimer = 0f
        }
    }
    
    private fun spawnEnemy() {
        val type = CharacterType.random()
        val spawnY = GameConstants.BASE_Y + (Random.nextFloat() * 200 - 100)
        
        val enemy = AnimalCharacter(
            type = type,
            isEnemy = true,
            position = Position(GameConstants.ENEMY_BASE_X + 50f, spawnY)
        )
        
        // 根据难度等级增强敌人
        enemy.hp = (enemy.hp * (1 + (difficultyLevel - 1) * GameConstants.DIFFICULTY_HP_MULTIPLIER)).toInt()
        
        gameState.enemyCharacters.add(enemy)
    }
    
    fun reset() {
        spawnTimer = 0f
        difficultyLevel = 1
        difficultyTimer = 0f
    }
    
    fun getDifficultyLevel(): Int = difficultyLevel
}
