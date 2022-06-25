package com.goncharov.evgeny.obstacleavoid.screens.menu

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.Actor
import com.goncharov.evgeny.obstacleavoid.common.BaseStageScreen
import com.goncharov.evgeny.obstacleavoid.navigation.Navigation

class OptionsScreen(
    navigation: Navigation,
    assetManager: AssetManager,
    batch: SpriteBatch
) : BaseStageScreen(navigation, assetManager, batch) {

    override fun initUi(): Actor {
        return Actor()
    }
}