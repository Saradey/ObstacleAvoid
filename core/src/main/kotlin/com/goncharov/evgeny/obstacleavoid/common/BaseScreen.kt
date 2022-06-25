package com.goncharov.evgeny.obstacleavoid.common

import com.badlogic.gdx.Screen
import com.goncharov.evgeny.obstacleavoid.util.LoggerUtils

abstract class BaseScreen : Screen {

    init {
        LoggerUtils.init(javaClass.simpleName)
    }

    override fun show() = Unit

    override fun render(delta: Float) = Unit

    override fun resize(width: Int, height: Int) = Unit

    override fun pause() = Unit

    override fun resume() = Unit

    override fun hide() = Unit

    override fun dispose() = Unit
}