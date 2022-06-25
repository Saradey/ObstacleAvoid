package com.goncharov.evgeny.obstacleavoid.screens.game

import com.goncharov.evgeny.obstacleavoid.common.BaseScreen
import com.goncharov.evgeny.obstacleavoid.util.GdxUtils

class GameScreen : BaseScreen() {

    override fun show() {
        debug("show")
    }

    override fun render(delta: Float) {
        GdxUtils.clearScreen()
    }
}