package com.goncharov.evgeny.obstacleavoid.common

import com.badlogic.gdx.Screen
import com.goncharov.evgeny.obstacleavoid.util.LoggerUtils
import com.goncharov.evgeny.obstacleavoid.util.LoggerUtils.debug

abstract class BaseScreen : Screen {

    init {
        LoggerUtils.init(javaClass.simpleName)
    }

    override fun show() = Unit

    override fun render(delta: Float) = Unit

    override fun resize(width: Int, height: Int) = Unit

    override fun pause() {
        debug("pause")
    }

    override fun resume() {
        debug("resume")
    }

    override fun hide() = Unit

    override fun dispose() = Unit
}