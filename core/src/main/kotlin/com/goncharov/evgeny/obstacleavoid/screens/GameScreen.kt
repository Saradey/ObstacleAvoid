package com.goncharov.evgeny.obstacleavoid.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.goncharov.evgeny.obstacleavoid.common.BaseScreen

class GameScreen : BaseScreen() {
    private var batch: SpriteBatch? = null
    private var image: Texture? = null

    override fun show() {
        batch = SpriteBatch()
        image = Texture("libgdx.png")
    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(0.15f, 0.15f, 0.2f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        batch?.begin()
        batch?.draw(image, 140f, 210f)
        batch?.end()
    }

    override fun dispose() {
        batch?.dispose()
        image?.dispose()
    }
}