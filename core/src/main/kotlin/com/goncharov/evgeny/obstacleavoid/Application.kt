package com.goncharov.evgeny.obstacleavoid

import com.badlogic.gdx.Game
import com.goncharov.evgeny.obstacleavoid.screens.GameScreen

class Application : Game() {

    override fun create() {
        setScreen(GameScreen())
    }
}