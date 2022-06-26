package com.goncharov.evgeny.obstacleavoid.screens.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.GlyphLayout
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.viewport.FitViewport
import com.goncharov.evgeny.obstacleavoid.consts.*
import com.goncharov.evgeny.obstacleavoid.consts.AssetDescriptors.FONT_DESCRIPTOR
import com.goncharov.evgeny.obstacleavoid.consts.AssetDescriptors.GAME_PLAY_DESCRIPTOR
import com.goncharov.evgeny.obstacleavoid.util.GdxUtils
import com.goncharov.evgeny.obstacleavoid.util.LogDebugUtils.debug

class GameRender(
    private val renderer: ShapeRenderer,
    private val assetManager: AssetManager,
    private val gameController: GameController,
    private val batch: SpriteBatch
) {

    private val camera = OrthographicCamera()
    private val viewport = FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera)
    private val uiCamera = OrthographicCamera()
    private val uiViewport = FitViewport(UI_WIDTH, UI_HEIGHT, uiCamera)
    private val font = assetManager[FONT_DESCRIPTOR]
    private val layout = GlyphLayout()
    private val gamePlayAtlas = assetManager[GAME_PLAY_DESCRIPTOR]
    private val playerRegion = gamePlayAtlas.findRegion(PLAYER)
    private val obstacleRegion = gamePlayAtlas.findRegion(OBSTACLE)
    private val backgroundRegion = gamePlayAtlas.findRegion(BACKGROUND)

    fun render(delta: Float) {
        touchedUpdate()
        GdxUtils.clearScreen()
        renderGamePlay()
        renderUi()
        renderDebug()
    }

    fun resize(width: Int, height: Int) {
        viewport.update(width, height, true)
        uiViewport.update(width, height, true)
    }

    private fun touchedUpdate() {
        if (Gdx.input.isTouched && gameController.isGameOver().not()) {
            val worldTouch =
                viewport.unproject(Vector2(Gdx.input.x.toFloat(), Gdx.input.y.toFloat()))
            gameController.player.x =
                MathUtils.clamp(worldTouch.x, 0f, WORLD_WIDTH - gameController.player.width)
        }
    }

    private fun renderGamePlay() {
        viewport.apply()
        batch.projectionMatrix = camera.combined
        batch.begin()
        val background = gameController.background
        batch.draw(
            backgroundRegion,
            background.x,
            background.y,
            background.width,
            background.height
        )
        val player = gameController.player
        batch.draw(playerRegion, player.x, player.y, player.width, player.height)
        gameController.obstacles.forEach { obstacle ->
            batch.draw(obstacleRegion, obstacle.x, obstacle.y, obstacle.width, obstacle.height)
        }
        batch.end()
    }

    private fun renderUi() {
        uiViewport.apply()
        batch.projectionMatrix = uiCamera.combined
        batch.begin()
        val liveText = LIVE_TEXT.format(gameController.lives)
        layout.setText(font, liveText)
        font.draw(batch, liveText, 20f, UI_HEIGHT - layout.height)
        val scoreText = SCORE_TEXT.format(gameController.displayScore)
        layout.setText(font, scoreText)
        font.draw(batch, scoreText, UI_WIDTH - layout.width - 20f, UI_HEIGHT - layout.height)
        batch.end()
    }

    private fun renderDebug() {
        viewport.apply()
        renderer.projectionMatrix = camera.combined
        renderer.begin(ShapeRenderer.ShapeType.Line)
        gameController.player.drawDebug(renderer)
        gameController.obstacles.forEach { obstacle ->
            obstacle.drawDebug(renderer)
        }
        renderer.end()
    }

    companion object {
        private const val LIVE_TEXT = "LIVES: %d"
        private const val SCORE_TEXT = "SCORE: %d"
    }
}