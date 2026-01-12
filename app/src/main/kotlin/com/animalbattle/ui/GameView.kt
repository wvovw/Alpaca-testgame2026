package com.animalbattle.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import com.animalbattle.core.GameConstants
import com.animalbattle.core.GameEngine

/**
 * 游戏画布和主渲染 - Game canvas & main rendering
 */
class GameView(context: Context) : SurfaceView(context), SurfaceHolder.Callback {
    private val gameEngine = GameEngine()
    private val buttonManager = ButtonManager(gameEngine.characterSpawner)
    private val uiRenderer = UIRenderer(buttonManager)
    private val resourceDisplay = ResourceDisplay()
    
    private var gameThread: GameThread? = null
    
    init {
        holder.addCallback(this)
        isFocusable = true
    }
    
    override fun surfaceCreated(holder: SurfaceHolder) {
        gameThread = GameThread(holder)
        gameThread?.isRunning = true
        gameThread?.start()
    }
    
    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
        // Not needed
    }
    
    override fun surfaceDestroyed(holder: SurfaceHolder) {
        var retry = true
        gameThread?.isRunning = false
        while (retry) {
            try {
                gameThread?.join()
                retry = false
            } catch (e: InterruptedException) {
                Thread.currentThread().interrupt() // Preserve interrupted status
                e.printStackTrace()
            }
        }
    }
    
    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            if (gameEngine.gameState.isGameOver) {
                // 重新开始游戏
                gameEngine.reset()
            } else {
                // 处理按钮点击
                buttonManager.handleTouch(event.x, event.y)
            }
            return true
        }
        return super.onTouchEvent(event)
    }
    
    private fun draw(canvas: Canvas) {
        // 清空画布
        canvas.drawColor(Color.argb(255, 30, 30, 30))
        
        // 绘制基地
        uiRenderer.drawBases(canvas, gameEngine.gameState)
        
        // 绘制角色
        uiRenderer.drawCharacters(canvas, gameEngine.gameState)
        
        // 绘制资源显示
        resourceDisplay.drawMoney(canvas, gameEngine.moneyManager)
        resourceDisplay.drawBases(canvas, gameEngine.gameState.playerBase, gameEngine.gameState.enemyBase)
        
        // 绘制按钮
        uiRenderer.drawButtons(canvas)
        
        // 如果游戏结束，显示结束画面
        if (gameEngine.gameState.isGameOver) {
            resourceDisplay.drawGameOver(canvas, gameEngine.gameState.playerWon, width.toFloat(), height.toFloat())
        }
    }
    
    private inner class GameThread(private val surfaceHolder: SurfaceHolder) : Thread() {
        var isRunning = false
        
        override fun run() {
            while (isRunning) {
                val startTime = System.currentTimeMillis()
                
                var canvas: Canvas? = null
                try {
                    canvas = surfaceHolder.lockCanvas()
                    if (canvas != null) {
                        synchronized(surfaceHolder) {
                            // 更新游戏状态
                            gameEngine.update()
                            
                            // 渲染游戏
                            draw(canvas)
                        }
                    }
                } finally {
                    if (canvas != null) {
                        surfaceHolder.unlockCanvasAndPost(canvas)
                    }
                }
                
                // 控制帧率
                val frameTime = System.currentTimeMillis() - startTime
                val sleepTime = GameConstants.FRAME_TIME_MS - frameTime
                if (sleepTime > 0) {
                    try {
                        sleep(sleepTime)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                }
            }
        }
    }
}
