package com.cremasfood.app.navigation.manager

import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.navOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class NavigationManager(private val scope: CoroutineScope) {

    var navigationEvent: MutableSharedFlow<NavigationCommand> = MutableSharedFlow()
        private set

    fun navigate(route: String, builder: (NavOptionsBuilder.() -> Unit)? = null) {
        scope.launch {
            navigationEvent.emit(NavigationCommand.Navigate(destination = route,
                type = NavigationType.NavigateTo,
                navOptions = builder?.let { navOptions(it) }))
        }
    }

    fun navigateUp() {
        scope.launch {
            navigationEvent.emit(NavigationCommand.NavigateUp)
        }
    }

    fun popBackStack(
        route: String, inclusive: Boolean, savedStateHandle: Pair<String, Any?>? = null
    ) {
        scope.launch {
            navigationEvent.emit(
                NavigationCommand.Navigate(
                    destination = route, type = NavigationType.PopUpTo(
                        inclusive = inclusive, savedStateHandle = savedStateHandle
                    )
                )
            )
        }
    }

    fun popBackStack() {
        scope.launch {
            navigationEvent.emit(NavigationCommand.PopStackBack)
        }
    }

    fun onBackPressed() {
        scope.launch {
            navigationEvent.emit(NavigationCommand.OnBackPressed)
        }
    }
}

sealed class NavigationType {

    object NavigateTo : NavigationType()

    class PopUpTo(val inclusive: Boolean, val savedStateHandle: Pair<String, Any?>? = null) :
        NavigationType()
}

sealed class NavigationCommand {

    data class Navigate(
        val destination: String, val navOptions: NavOptions? = null, val type: NavigationType
    ) : NavigationCommand()

    data object NavigateUp : NavigationCommand()

    data object PopStackBack : NavigationCommand()

    data object OnBackPressed : NavigationCommand()
}