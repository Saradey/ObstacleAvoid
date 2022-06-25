package com.goncharov.evgeny.obstacleavoid.common

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.goncharov.evgeny.obstacleavoid.util.FormatUtils

abstract class BaseScreen : Screen {

    private val logTag = javaClass.simpleName

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

    protected fun debug(message: String) {
        Gdx.app.debug(
            logTag,
            "${FormatUtils.dateFormat.format(FormatUtils.calendar.time)} $message"
        )
    }
}