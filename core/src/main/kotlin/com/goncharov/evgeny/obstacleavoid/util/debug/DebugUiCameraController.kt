package com.goncharov.evgeny.obstacleavoid.util.debug

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.OrthographicCamera
import com.goncharov.evgeny.obstacleavoid.consts.UI_HEIGHT
import com.goncharov.evgeny.obstacleavoid.consts.UI_WIDTH

object DebugUiCameraController {

    fun update(camera: OrthographicCamera) {
        when {
            Gdx.input.isKeyPressed(Input.Keys.LEFT) -> camera.position.x -= TRANSFORM_POSITION_SPEED
            Gdx.input.isKeyPressed(Input.Keys.RIGHT) -> camera.position.x += TRANSFORM_POSITION_SPEED
            Gdx.input.isKeyPressed(Input.Keys.UP) -> camera.position.y += TRANSFORM_POSITION_SPEED
            Gdx.input.isKeyPressed(Input.Keys.DOWN) -> camera.position.y -= TRANSFORM_POSITION_SPEED
            Gdx.input.isKeyPressed(Input.Keys.Z) -> camera.zoom += ZOOM_SPEED
            Gdx.input.isKeyPressed(Input.Keys.X) -> camera.zoom -= ZOOM_SPEED
            Gdx.input.isKeyPressed(Input.Keys.R) -> {
                camera.position.x = UI_WIDTH / 2f
                camera.position.y = UI_HEIGHT / 2f
                camera.zoom = 1f
            }
        }
    }

    private const val TRANSFORM_POSITION_SPEED = 1f
    private const val ZOOM_SPEED = 0.01f
}