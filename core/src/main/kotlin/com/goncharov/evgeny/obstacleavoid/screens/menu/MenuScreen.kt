package com.goncharov.evgeny.obstacleavoid.screens.menu

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.goncharov.evgeny.obstacleavoid.common.BaseStageScreen
import com.goncharov.evgeny.obstacleavoid.common.addListenerKtx
import com.goncharov.evgeny.obstacleavoid.consts.AssetDescriptors
import com.goncharov.evgeny.obstacleavoid.consts.BACKGROUND
import com.goncharov.evgeny.obstacleavoid.navigation.Navigation

class MenuScreen(
    navigation: Navigation,
    assetManager: AssetManager,
    batch: SpriteBatch
) : BaseStageScreen(navigation, assetManager, batch) {

    override fun initUi(): Actor {
        val table = Table()
        val gamePlayAtlas = assetManager[AssetDescriptors.GAME_PLAY_DESCRIPTOR]
        table.background = TextureRegionDrawable(gamePlayAtlas.findRegion(BACKGROUND))

        val playButton = TextButton("PLAY", uiSkin)
        playButton.addListenerKtx {
            play()
        }

        return table
    }

    private fun play() {

    }
}