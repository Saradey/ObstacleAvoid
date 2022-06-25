package com.goncharov.evgeny.obstacleavoid.screens.loading

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.utils.viewport.FitViewport
import com.goncharov.evgeny.obstacleavoid.common.BaseScreen
import com.goncharov.evgeny.obstacleavoid.consts.AssetDescriptors
import com.goncharov.evgeny.obstacleavoid.consts.UI_HEIGHT
import com.goncharov.evgeny.obstacleavoid.consts.UI_WIDTH
import com.goncharov.evgeny.obstacleavoid.navigation.Navigation
import com.goncharov.evgeny.obstacleavoid.util.LoggerUtils.debug

class LoadingScreen(
    private val assetManager: AssetManager,
    private val shapeRenderer: ShapeRenderer,
    private val navigation: Navigation
) : BaseScreen() {

    private val camera = OrthographicCamera()
    private val viewport = FitViewport(UI_WIDTH, UI_HEIGHT, camera)
    private var progress = 0f
    private var waitTime = 0.75f
    private var changeScreen = false

    override fun show() {
        debug("show")
        assetManager.load(AssetDescriptors.FONT_DESCRIPTOR)
        assetManager.load(AssetDescriptors.GAME_PLAY_DESCRIPTOR)
        assetManager.load(AssetDescriptors.UI_SKIN_DESCRIPTOR)
        assetManager.load(AssetDescriptors.HIT_SOUND_DESCRIPTOR)
    }

    override fun render(delta: Float) {

    }

    override fun resize(width: Int, height: Int) {
        debug("resize")
        viewport.update(width, height, true)
    }

    override fun hide() {
        debug("hide")
        dispose()
    }

    override fun dispose() {
        debug("dispose")
        shapeRenderer.dispose()
    }

    companion object {
        private const val PROGRESS_BAR_WIDTH = UI_WIDTH / 2f
        private const val PROGRESS_BAR_HEIGHT = 60f
    }
}