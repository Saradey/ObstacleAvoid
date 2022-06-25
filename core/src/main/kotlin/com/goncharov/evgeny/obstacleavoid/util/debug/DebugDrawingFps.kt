package com.goncharov.evgeny.obstacleavoid.util.debug

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.viewport.FitViewport
import com.goncharov.evgeny.obstacleavoid.consts.UI_HEIGHT
import com.goncharov.evgeny.obstacleavoid.consts.UI_WIDTH

object DebugDrawingFps {
    private val camera = OrthographicCamera()
    private val viewport = FitViewport(UI_WIDTH, UI_HEIGHT, camera)

    fun resize(width: Int, height: Int) {
        viewport.update(width, height, true)
    }

    fun drawFpsMonitor(batch: SpriteBatch, fpsFont: BitmapFont) {
        when {
            Gdx.graphics.displayMode.refreshRate / 3 > Gdx.graphics.framesPerSecond -> {
                fpsFont.color = Color.RED
            }
            Gdx.graphics.displayMode.refreshRate / 1.5 > Gdx.graphics.framesPerSecond -> {
                fpsFont.color = Color.YELLOW
            }
            else -> {
                fpsFont.color = Color.GREEN
            }
        }
        batch.begin()
        fpsFont.draw(
            batch,
            FPS.format(Gdx.graphics.framesPerSecond),
            UI_WIDTH - 110f,
            UI_HEIGHT - 20f
        )
        batch.end()
    }

    private const val FPS = "FPS:%d"
}