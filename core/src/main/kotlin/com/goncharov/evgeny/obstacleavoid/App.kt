package com.goncharov.evgeny.obstacleavoid

import com.badlogic.gdx.Application
import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.goncharov.evgeny.obstacleavoid.navigation.KeyNavigation
import com.goncharov.evgeny.obstacleavoid.navigation.Navigation
import com.goncharov.evgeny.obstacleavoid.screens.loading.LoadingScreen
import com.goncharov.evgeny.obstacleavoid.screens.menu.MenuScreen
import com.goncharov.evgeny.obstacleavoid.util.FormatUtils

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
        Gdx.app.logLevel = Application.LOG_DEBUG
        Gdx.app.debug(
            "App",
            "${FormatUtils.dateFormat.format(FormatUtils.calendar.time)} start application"
        )
        navigate(KeyNavigation.LoadingKey)
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
        }
    }
}