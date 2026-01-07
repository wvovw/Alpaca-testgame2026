package com.animalbattle.core

/**
 * 游戏常量 - Game constants
 */
object GameConstants {
    // 游戏尺寸
    const val GAME_WIDTH = 1920f
    const val GAME_HEIGHT = 1080f
    
    // FPS
    const val TARGET_FPS = 60
    const val FRAME_TIME_MS = 1000L / TARGET_FPS
    
    // 金钱系统
    const val INITIAL_MONEY = 100
    const val MONEY_PER_SECOND = 5
    
    // 基地设置
    const val BASE_INITIAL_HP = 1000
    const val ENEMY_BASE_X = 100f
    const val PLAYER_BASE_X = GAME_WIDTH - 100f
    const val BASE_Y = GAME_HEIGHT / 2f
    
    // 敌人生成
    const val ENEMY_SPAWN_INTERVAL = 3f // 秒 - Enemy spawn every 3 seconds
    const val DIFFICULTY_INCREASE_INTERVAL = 30f // 秒 - Difficulty increases every 30 seconds
    
    // 难度系统
    const val DIFFICULTY_HP_MULTIPLIER = 0.1f // 难度等级HP增长倍数 (每级+10%)
    
    // UI位置
    const val BUTTON_WIDTH = 180f
    const val BUTTON_HEIGHT = 100f
    const val BUTTON_Y = GAME_HEIGHT - 120f
    const val BUTTON_SPACING = 200f
    const val BUTTON_START_X = 400f
    
    // 战斗系统 - Combat system conversion factors
    // 射程和速度使用抽象单位，需要转换为像素
    // Range and speed use abstract units, need to convert to pixels
    const val RANGE_TO_PIXEL_MULTIPLIER = 50f // 1 range unit = 50 pixels
    const val MOVEMENT_SPEED_MULTIPLIER = 50f // 1 speed unit = 50 pixels per second
}
