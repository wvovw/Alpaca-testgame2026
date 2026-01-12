package com.animalbattle

import android.app.Activity
import android.os.Bundle
import android.view.WindowManager
import com.animalbattle.ui.GameView

/**
 * 主活动 - Main Activity
 */
class MainActivity : Activity() {
    private lateinit var gameView: GameView
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // 保持屏幕常亮
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        
        // 创建游戏视图
        gameView = GameView(this)
        setContentView(gameView)
    }
    
    override fun onPause() {
        super.onPause()
        // GameView 会自动处理暂停
    }
    
    override fun onResume() {
        super.onResume()
        // GameView 会自动处理恢复
    }
}
