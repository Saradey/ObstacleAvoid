package com.goncharov.evgeny.obstacleavoid

import com.badlogic.gdx.Application
import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.utils.viewport.FitViewport
import com.goncharov.evgeny.obstacleavoid.consts.AssetDescriptors
import com.goncharov.evgeny.obstacleavoid.consts.UI_HEIGHT
import com.goncharov.evgeny.obstacleavoid.consts.UI_WIDTH
import com.goncharov.evgeny.obstacleavoid.navigation.KeyNavigation
import com.goncharov.evgeny.obstacleavoid.navigation.Navigation
import com.goncharov.evgeny.obstacleavoid.screens.game.GameScreen
import com.goncharov.evgeny.obstacleavoid.screens.loading.LoadingScreen
import com.goncharov.evgeny.obstacleavoid.screens.menu.HighScoreScreen
import com.goncharov.evgeny.obstacleavoid.screens.menu.MenuScreen
import com.goncharov.evgeny.obstacleavoid.screens.menu.OptionsScreen
import com.goncharov.evgeny.obstacleavoid.util.FormatUtils
import com.goncharov.evgeny.obstacleavoid.util.debug.DebugDrawingFps

class App : Game(), Navigation {

    private val batch by lazy {
        SpriteBatch()
    }
    private val assetManager by lazy {
        AssetManager()
    }
    private val debugRender by lazy {
        ShapeRenderer()
    }

    override fun create() {
        assetManager.load(AssetDescriptors.FPS_FONT_DESCRIPTOR)
        assetManager.finishLoading()
        Gdx.app.logLevel = Application.LOG_DEBUG
        Gdx.app.debug(
            "App",
            "${FormatUtils.dateFormat.format(FormatUtils.calendar.time)} start application"
        )
        navigate(KeyNavigation.LoadingKey)
    }

    override fun resize(width: Int, height: Int) {
        DebugDrawingFps.resize(width, height)
        super.resize(width, height)
    }

    override fun render() {
        super.render()
        DebugDrawingFps.drawFpsMonitor(batch, assetManager[AssetDescriptors.FPS_FONT_DESCRIPTOR])
    }

    override fun dispose() {
        batch.dispose()
        assetManager.dispose()
        Gdx.app.debug(
            "App",
            "${FormatUtils.dateFormat.format(FormatUtils.calendar.time)} dispose"
        )
    }

    override fun navigate(key: KeyNavigation) {
        when (key) {
            KeyNavigation.LoadingKey -> setScreen(
                LoadingScreen(assetManager, debugRender, this)
            )
            KeyNavigation.MenuKey -> setScreen(
                MenuScreen(this, assetManager, batch)
            )
            KeyNavigation.GameKey -> setScreen(
                GameScreen()
            )
            KeyNavigation.HighScoreKey -> setScreen(
                HighScoreScreen(this, assetManager, batch)
            )
            KeyNavigation.OptionsKey -> setScreen(
                OptionsScreen(this, assetManager, batch)
            )
        }
    }
}