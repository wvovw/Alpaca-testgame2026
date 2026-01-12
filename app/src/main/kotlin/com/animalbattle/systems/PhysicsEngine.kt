package com.animalbattle.systems

import com.animalbattle.entities.AnimalCharacter
import com.animalbattle.entities.Position

/**
 * 碰撞检测引擎 - Collision detection
 */
class PhysicsEngine {
    fun checkCollision(pos1: Position, range1: Float, pos2: Position): Boolean {
        val distance = pos1.distanceTo(pos2)
        return distance <= range1
    }
    
    fun findNearestEnemy(character: AnimalCharacter, enemies: List<AnimalCharacter>): AnimalCharacter? {
        var nearest: AnimalCharacter? = null
        var minDistance = Float.MAX_VALUE
        
        for (enemy in enemies) {
            if (!enemy.isAlive()) continue
            
            val distance = character.position.distanceTo(enemy.position)
            if (distance < minDistance) {
                minDistance = distance
                nearest = enemy
            }
        }
        
        return nearest
    }
}
