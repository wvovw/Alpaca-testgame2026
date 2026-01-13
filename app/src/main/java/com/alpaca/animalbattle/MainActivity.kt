package com.alpaca.animalbattle

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

/**
 * 动物大作战 - 主界面Activity
 * 游戏启动入口，提供主菜单功能
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupButtons()
    }

    private fun setupButtons() {
        findViewById<Button>(R.id.startGameButton).setOnClickListener {
            startGame()
        }

        findViewById<Button>(R.id.howToPlayButton).setOnClickListener {
            showHowToPlay()
        }

        findViewById<Button>(R.id.settingsButton).setOnClickListener {
            showSettings()
        }

        findViewById<Button>(R.id.exitButton).setOnClickListener {
            finish()
        }
    }

    private fun startGame() {
        val intent = Intent(this, GameActivity::class.java)
        startActivity(intent)
    }

    private fun showHowToPlay() {
        // TODO: 显示玩法说明对话框
    }

    private fun showSettings() {
        // TODO: 显示设置对话框
    }
}
