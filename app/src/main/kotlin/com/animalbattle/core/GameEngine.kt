package com.animalbattle.core

import com.animalbattle.systems.*

/**
 * 游戏引擎 - 60FPS game loop
 */
class GameEngine {
    val gameState = GameState()
    
    // 系统
    val moneyManager = MoneyManager()
    val characterSpawner = CharacterSpawner(gameState, moneyManager)
    val enemyWaveManager = EnemyWaveManager(gameState)
    val physicsEngine = PhysicsEngine()
    val combatSystem = CombatSystem(gameState, physicsEngine)
    val enemyAI = EnemyAI(gameState)
    val battleArena = BattleArena(gameState)
    
    private var lastUpdateTime = System.currentTimeMillis()
    
    fun update() {
        val currentTime = System.currentTimeMillis()
        val deltaTime = (currentTime - lastUpdateTime) / 1000f
        lastUpdateTime = currentTime
        
        if (gameState.isGameOver) {
            return
        }
        
        // 更新所有系统
        gameState.update(deltaTime)
        moneyManager.update(deltaTime)
        enemyWaveManager.update(deltaTime)
        battleArena.update(deltaTime)
        enemyAI.update(deltaTime)
        combatSystem.update(deltaTime)
        
        // 清理死亡角色
        gameState.removeDeadCharacters()
    }
    
    fun reset() {
        gameState.playerBase.hp = GameConstants.BASE_INITIAL_HP
        gameState.enemyBase.hp = GameConstants.BASE_INITIAL_HP
        gameState.playerCharacters.clear()
        gameState.enemyCharacters.clear()
        gameState.isGameOver = false
        gameState.playerWon = false
        gameState.gameTime = 0f
        moneyManager.reset()
        enemyWaveManager.reset()
        lastUpdateTime = System.currentTimeMillis()
    }
}
