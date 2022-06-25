package com.goncharov.evgeny.obstacleavoid

import com.badlogic.gdx.Application
import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.goncharov.evgeny.obstacleavoid.screens.loading.LoadingScreen

class App : Game() {

    private val batch by lazy {
        SpriteBatch()
    }
    private val assetManager by lazy {
        AssetManager()
    }

    override fun create() {
        Gdx.app.logLevel = Application.LOG_DEBUG
        setScreen(LoadingScreen())
    }

    override fun dispose() {
        batch.dispose()
        assetManager.dispose()
    }
}