package com.goncharov.evgeny.obstacleavoid.common

import com.badlogic.gdx.Screen

abstract class BaseScreen : Screen {

    override fun show() {}

    override fun resize(width: Int, height: Int) {}

    override fun pause() {}

    override fun resume() {}

    override fun hide() {}

    override fun dispose() {}
}