package com.alpaca.animalbattle

import android.graphics.Canvas
import android.view.SurfaceHolder

/**
 * 动物大作战 - 游戏引擎
 * 管理游戏循环、渲染和游戏逻辑
 */
class GameEngine(private val surfaceHolder: SurfaceHolder) : Thread() {

    @Volatile
    private var running = false
    @Volatile
    private var paused = false

    private var score = 0
    private var level = 1
    private var lives = 3

    private val gameObjects = mutableListOf<GameObject>()
    private val catPlayer = CatPlayer(100f, 100f)
    
    private var gameStateListener: GameStateListener? = null

    interface GameStateListener {
        fun onScoreChanged(score: Int)
        fun onLevelChanged(level: Int)
        fun onLivesChanged(lives: Int)
        fun onGameOver()
    }

    init {
        gameObjects.add(catPlayer)
        notifyScoreChanged()
        notifyLevelChanged()
        notifyLivesChanged()
    }

    fun setGameStateListener(listener: GameStateListener) {
        gameStateListener = listener
    }

    override fun run() {
        var lastUpdateTime = System.currentTimeMillis()
        
        while (running) {
            if (!paused) {
                val currentTime = System.currentTimeMillis()
                val deltaTime = (currentTime - lastUpdateTime) / 1000f
                lastUpdateTime = currentTime

                update(deltaTime)
                render()
            }

            try {
                sleep(16) // ~60 FPS
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
    }

    private fun update(deltaTime: Float) {
        // 更新所有游戏对象
        gameObjects.forEach { it.update(deltaTime) }

        // 碰撞检测
        checkCollisions()

        // 生成敌人逻辑
        spawnEnemiesIfNeeded()
    }

    private fun render() {
        var canvas: Canvas? = null
        try {
            canvas = surfaceHolder.lockCanvas()
            if (canvas != null) {
                synchronized(surfaceHolder) {
                    // 清空画布
                    canvas.drawRGB(135, 206, 235) // 天蓝色背景

                    // 渲染所有游戏对象
                    gameObjects.forEach { it.render(canvas) }
                }
            }
        } finally {
            canvas?.let {
                surfaceHolder.unlockCanvasAndPost(it)
            }
        }
    }

    private fun checkCollisions() {
        // TODO: 实现碰撞检测逻辑
    }

    private fun spawnEnemiesIfNeeded() {
        // TODO: 实现敌人生成逻辑
    }

    fun addScore(points: Int) {
        score += points
        notifyScoreChanged()
        
        // 每1000分升级
        if (score / 1000 > level - 1) {
            level = score / 1000 + 1
            notifyLevelChanged()
        }
    }

    fun loseLife() {
        lives--
        notifyLivesChanged()
        if (lives <= 0) {
            gameStateListener?.onGameOver()
            stopGame()
        }
    }

    private fun notifyScoreChanged() {
        gameStateListener?.onScoreChanged(score)
    }

    private fun notifyLevelChanged() {
        gameStateListener?.onLevelChanged(level)
    }

    private fun notifyLivesChanged() {
        gameStateListener?.onLivesChanged(lives)
    }

    fun startGame() {
        running = true
        start()
    }

    fun pause() {
        paused = true
    }

    fun resume() {
        paused = false
    }

    fun isPaused(): Boolean = paused

    fun stopGame() {
        running = false
    }
}
