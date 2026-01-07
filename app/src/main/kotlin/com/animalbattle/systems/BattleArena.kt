package com.animalbattle.systems

import com.animalbattle.core.GameConstants
import com.animalbattle.core.GameState

/**
 * 战斗竞技场管理 - Battle arena management
 */
class BattleArena(private val gameState: GameState) {
    fun update(deltaTime: Float) {
        // 移动玩家角色向左（朝向敌人基地）
        for (player in gameState.playerCharacters) {
            if (!player.isAlive()) continue
            
            // 检查是否有敌人在攻击范围内
            var hasTargetInRange = false
            for (enemy in gameState.enemyCharacters) {
                if (!enemy.isAlive()) continue
                val distance = player.position.distanceTo(enemy.position)
                if (distance <= player.range * 50) { // 转换为像素距离
                    hasTargetInRange = true
                    break
                }
            }
            
            // 如果没有目标在范围内，继续向左移动
            if (!hasTargetInRange) {
                player.position.x -= player.speed * deltaTime * 50
                
                // 检查是否到达敌人基地
                if (player.position.x <= GameConstants.ENEMY_BASE_X + 50) {
                    player.position.x = GameConstants.ENEMY_BASE_X + 50
                }
            }
            
            // 更新角色状态
            player.update(deltaTime)
        }
        
        // 更新敌人状态
        for (enemy in gameState.enemyCharacters) {
            if (!enemy.isAlive()) continue
            enemy.update(deltaTime)
        }
    }
}
