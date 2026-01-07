package com.animalbattle.systems

import com.animalbattle.core.GameConstants
import com.animalbattle.core.GameState

/**
 * 敌人自动AI - Enemy automatic AI movement & attack
 */
class EnemyAI(private val gameState: GameState) {
    fun update(deltaTime: Float) {
        // 移动敌人向右（朝向玩家基地）
        for (enemy in gameState.enemyCharacters) {
            if (!enemy.isAlive()) continue
            
            // 检查是否有玩家角色在攻击范围内
            var hasTargetInRange = false
            for (player in gameState.playerCharacters) {
                if (!player.isAlive()) continue
                val distance = enemy.position.distanceTo(player.position)
                if (distance <= enemy.range * 50) { // 转换为像素距离
                    hasTargetInRange = true
                    break
                }
            }
            
            // 如果没有目标在范围内，继续向右移动
            if (!hasTargetInRange) {
                enemy.position.x += enemy.speed * deltaTime * 50
                
                // 检查是否到达玩家基地
                if (enemy.position.x >= GameConstants.PLAYER_BASE_X - 50) {
                    enemy.position.x = GameConstants.PLAYER_BASE_X - 50
                }
            }
        }
    }
}
