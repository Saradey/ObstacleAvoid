package com.goncharov.evgeny.obstacleavoid.common

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener

fun Actor.addListenerKtx(action: () -> Unit) {
    this.addListener(object : ChangeListener() {
        override fun changed(event: ChangeEvent?, actor: Actor?) {
            action()
        }
    })
}