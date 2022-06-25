package com.goncharov.evgeny.obstacleavoid.screens.menu

import com.goncharov.evgeny.obstacleavoid.common.BaseScreen
import com.goncharov.evgeny.obstacleavoid.util.GdxUtils
import com.goncharov.evgeny.obstacleavoid.util.LoggerUtils.debug

class MenuScreen : BaseScreen() {

    override fun show() {
        debug("show")
    }

    override fun render(delta: Float) {
        GdxUtils.clearScreen()
    }
}