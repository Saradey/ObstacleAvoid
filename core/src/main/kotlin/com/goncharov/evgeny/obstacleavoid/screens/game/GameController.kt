package com.goncharov.evgeny.obstacleavoid.screens.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.utils.Array
import com.badlogic.gdx.utils.Pools
import com.goncharov.evgeny.obstacleavoid.consts.*
import com.goncharov.evgeny.obstacleavoid.consts.AssetDescriptors.HIT_SOUND_DESCRIPTOR
import com.goncharov.evgeny.obstacleavoid.entity.Background
import com.goncharov.evgeny.obstacleavoid.entity.Obstacle
import com.goncharov.evgeny.obstacleavoid.entity.Player
import com.goncharov.evgeny.obstacleavoid.managers.GameManager
import com.goncharov.evgeny.obstacleavoid.util.LogDebugUtils.debug
import kotlin.math.min

class GameController(
    private val assetManager: AssetManager
) {
    val player = Player()
    val background = Background()
    val obstacles = Array<Obstacle>()
    private var obstacleTimer = 0f
    private var scoreTimer = 0f
    var lives = LIVES_START
    private var score = 0
    var displayScore = 0
    private val obstaclePool = Pools.get(Obstacle::class.java, 40)
    private val hit = assetManager[HIT_SOUND_DESCRIPTOR]

    init {
        player.setPosition(START_PLAYER_X, START_PLAYER_Y)
    }

    fun update(delta: Float) {
        if (isGameOver()) return
        updatePlayer()
        updateObstacles(delta)
        updateScore(delta)
        updateDisplayScore(delta)
        updateGameRules()
    }

    private fun updatePlayer() {
        var playerX = player.x
        when {
            Gdx.input.isKeyPressed(Input.Keys.A) -> {
                playerX += MAX_PLAYER_X_SPEED
            }
            Gdx.input.isKeyPressed(Input.Keys.D) -> {
                playerX -= MAX_PLAYER_X_SPEED
            }
        }
        player.x = MathUtils.clamp(playerX, 0f, WORLD_WIDTH - player.width)
    }

    private fun updateObstacles(delta: Float) {
        obstacles.forEach { obstacle ->
            obstacle.update()
        }
        obstacleTimer += delta
        if (obstacleTimer >= OBSTACLE_SPAWN_TIME) {
            val min = 0f
            val max = WORLD_WIDTH - OBSTACLE_SIZE
            val obstacleX = MathUtils.random(min, max)
            val obstacleY = WORLD_WIDTH
            val obstacle = obstaclePool.obtain()
            val difficultyLevel = GameManager.getDifficultyLevel()
            obstacle.ySpeed = difficultyLevel.obstacleSpeed
            obstacle.setPosition(obstacleX, obstacleY)
            obstacles.add(obstacle)
            obstacleTimer = 0f
        }
        if (obstacles.size > 0) {
            val first = obstacles.first()
            if (first.y < -OBSTACLE_SIZE) {
                obstacles.removeValue(first, true)
                obstaclePool.free(first)
            }
        }
    }

    private fun updateScore(delta: Float) {
        scoreTimer += delta
        if (scoreTimer > SCORE_MAX_TIME) {
            score += MathUtils.random(1, 5)
            scoreTimer = 0.0f
        }
    }

    private fun updateDisplayScore(delta: Float) {
        if (displayScore < score) {
            displayScore = min(score, (displayScore + 40 * delta).toInt())
        }
    }

    private fun updateGameRules() {
        if (isPlayerCollidingWithObstacle()) {
            debug("GameController", "Collision detected")
            lives--
            if (isGameOver()) {
                debug("GameController", "Game Over!!!")
                GameManager.updateHighScore(score)
            } else {
                restart()
            }
        }
    }

    private fun isPlayerCollidingWithObstacle(): Boolean {
        obstacles.forEach { obstacle ->
            if (obstacle.isNotHit() && obstacle.isPlayerColliding(player))
                return true
        }
        return false
    }

    private fun restart() {
        obstaclePool.freeAll(obstacles)
        obstacles.clear()
        player.setPosition(START_PLAYER_X, START_PLAYER_Y)
    }

    fun isGameOver() = lives <= 0
}