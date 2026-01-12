package com.animalbattle.systems

import com.animalbattle.core.GameConstants

/**
 * 金钱管理系统 - Automatic money growth system
 */
class MoneyManager {
    var currentMoney: Int = GameConstants.INITIAL_MONEY
        private set
    
    private var moneyAccumulator: Float = 0f
    
    fun update(deltaTime: Float) {
        // 每秒增加金钱
        moneyAccumulator += deltaTime
        if (moneyAccumulator >= 1f) {
            currentMoney += GameConstants.MONEY_PER_SECOND
            moneyAccumulator -= 1f
        }
    }
    
    fun canAfford(cost: Int): Boolean = currentMoney >= cost
    
    fun spend(cost: Int): Boolean {
        if (canAfford(cost)) {
            currentMoney -= cost
            return true
        }
        return false
    }
    
    fun reset() {
        currentMoney = GameConstants.INITIAL_MONEY
        moneyAccumulator = 0f
    }
}
