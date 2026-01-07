package com.alpaca.animalbattle

import android.os.Bundle
import android.view.SurfaceView
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

/**
 * 动物大作战 - 游戏Activity
 * 管理游戏循环和游戏状态
 */
class GameActivity : AppCompatActivity() {

    private lateinit var gameView: SurfaceView
    private lateinit var gameEngine: GameEngine
    private lateinit var scoreText: TextView
    private lateinit var levelText: TextView
    private lateinit var livesText: TextView
    private lateinit var pauseButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        initializeViews()
        setupGameEngine()
    }

    private fun initializeViews() {
        gameView = findViewById(R.id.gameView)
        scoreText = findViewById(R.id.scoreText)
        levelText = findViewById(R.id.levelText)
        livesText = findViewById(R.id.livesText)
        pauseButton = findViewById(R.id.pauseButton)

        pauseButton.setOnClickListener {
            togglePause()
        }
    }

    private fun setupGameEngine() {
        gameEngine = GameEngine(gameView.holder)
        gameEngine.setGameStateListener(object : GameEngine.GameStateListener {
            override fun onScoreChanged(score: Int) {
                runOnUiThread {
                    scoreText.text = getString(R.string.score, score)
                }
            }

            override fun onLevelChanged(level: Int) {
                runOnUiThread {
                    levelText.text = getString(R.string.level, level)
                }
            }

            override fun onLivesChanged(lives: Int) {
                runOnUiThread {
                    livesText.text = getString(R.string.lives, lives)
                }
            }

            override fun onGameOver() {
                runOnUiThread {
                    showGameOver()
                }
            }
        })
    }

    private fun togglePause() {
        if (gameEngine.isPaused()) {
            gameEngine.resume()
            pauseButton.text = getString(R.string.pause)
        } else {
            gameEngine.pause()
            pauseButton.text = getString(R.string.resume)
        }
    }

    private fun showGameOver() {
        // TODO: 显示游戏结束对话框
        finish()
    }

    override fun onResume() {
        super.onResume()
        gameEngine.start()
    }

    override fun onPause() {
        super.onPause()
        gameEngine.pause()
    }
}
