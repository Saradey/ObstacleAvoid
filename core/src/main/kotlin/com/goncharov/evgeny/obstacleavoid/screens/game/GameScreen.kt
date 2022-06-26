package com.goncharov.evgeny.obstacleavoid.screens.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.goncharov.evgeny.obstacleavoid.common.BaseScreen
import com.goncharov.evgeny.obstacleavoid.navigation.Navigation
import com.goncharov.evgeny.obstacleavoid.util.GdxUtils

class GameScreen(
    assetManager: AssetManager,
    shapeRenderer: ShapeRenderer,
    batch: SpriteBatch,
    navigation: Navigation
) : BaseScreen() {

    private val gameController = GameController(assetManager, navigation)
    private val gameRender = GameRender(
        shapeRenderer, assetManager, gameController, batch
    )

    override fun show() {
        debug("show")
    }

    override fun render(delta: Float) {
        GdxUtils.clearScreen()
        gameController.update(delta)
        gameRender.render(delta)
    }

    override fun resize(width: Int, height: Int) {
        debug("resize")
        gameRender.resize(width, height)
    }
}