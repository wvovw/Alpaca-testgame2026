package com.animalbattle.systems

import com.animalbattle.core.GameConstants
import com.animalbattle.core.GameState

/**
 * 自动战斗系统 - Automatic combat & damage calculation
 */
class CombatSystem(
    private val gameState: GameState,
    private val physicsEngine: PhysicsEngine
) {
    fun update(deltaTime: Float) {
        // 玩家角色攻击
        for (player in gameState.playerCharacters) {
            if (!player.isAlive() || !player.canAttack()) continue
            
            // 查找攻击范围内的敌人
            var targetEnemy: com.animalbattle.entities.AnimalCharacter? = null
            var minDistance = Float.MAX_VALUE
            
            for (enemy in gameState.enemyCharacters) {
                if (!enemy.isAlive()) continue
                val distance = player.position.distanceTo(enemy.position)
                if (distance <= player.range * 50 && distance < minDistance) {
                    targetEnemy = enemy
                    minDistance = distance
                }
            }
            
            // 攻击敌人
            if (targetEnemy != null) {
                val damage = maxOf(1, player.attack - targetEnemy.defense)
                targetEnemy.takeDamage(damage)
                player.performAttack()
            } else {
                // 检查是否到达敌人基地
                val distanceToBase = player.position.distanceTo(gameState.enemyBase.position)
                if (distanceToBase <= player.range * 50) {
                    gameState.enemyBase.takeDamage(player.attack)
                    player.performAttack()
                }
            }
        }
        
        // 敌人角色攻击
        for (enemy in gameState.enemyCharacters) {
            if (!enemy.isAlive() || !enemy.canAttack()) continue
            
            // 查找攻击范围内的玩家角色
            var targetPlayer: com.animalbattle.entities.AnimalCharacter? = null
            var minDistance = Float.MAX_VALUE
            
            for (player in gameState.playerCharacters) {
                if (!player.isAlive()) continue
                val distance = enemy.position.distanceTo(player.position)
                if (distance <= enemy.range * 50 && distance < minDistance) {
                    targetPlayer = player
                    minDistance = distance
                }
            }
            
            // 攻击玩家角色
            if (targetPlayer != null) {
                val damage = maxOf(1, enemy.attack - targetPlayer.defense)
                targetPlayer.takeDamage(damage)
                enemy.performAttack()
            } else {
                // 检查是否到达玩家基地
                val distanceToBase = enemy.position.distanceTo(gameState.playerBase.position)
                if (distanceToBase <= enemy.range * 50) {
                    gameState.playerBase.takeDamage(enemy.attack)
                    enemy.performAttack()
                }
            }
        }
    }
}
