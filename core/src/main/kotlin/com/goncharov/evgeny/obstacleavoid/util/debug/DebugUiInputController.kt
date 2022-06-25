package com.goncharov.evgeny.obstacleavoid.util.debug

import com.badlogic.gdx.Input
import com.badlogic.gdx.scenes.scene2d.Stage
import com.goncharov.evgeny.obstacleavoid.common.BaseInputProcessor

class DebugUiInputController(private val stage: Stage) : BaseInputProcessor() {

    override fun keyDown(keycode: Int): Boolean {
        when {
            keycode == Input.Keys.C && stage.isDebugAll.not() -> {
                stage.isDebugAll = true
            }
            keycode == Input.Keys.C && stage.isDebugAll -> {
                stage.isDebugAll = false
            }
        }
        return true
    }
}