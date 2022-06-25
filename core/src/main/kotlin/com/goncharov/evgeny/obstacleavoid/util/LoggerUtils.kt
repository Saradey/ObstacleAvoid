package com.goncharov.evgeny.obstacleavoid.util

import com.badlogic.gdx.Gdx
import java.text.SimpleDateFormat
import java.util.*

object LoggerUtils {

    private var logTag: String? = null

    private val calendar = Calendar.getInstance()

    @Suppress("SimpleDateFormat")
    private val dateFormat = SimpleDateFormat("dd MMM yyyy HH:mm:ss")

    fun init(tag: String) {
        logTag = tag
    }

    fun debug(message: String) {
        Gdx.app.debug(logTag, "${dateFormat.format(calendar.time)} $message")
    }
}