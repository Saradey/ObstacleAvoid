package com.goncharov.evgeny.obstacleavoid.navigation

sealed class KeyNavigation {
    object LoadingKey : KeyNavigation()
    object MenuKey : KeyNavigation()
}