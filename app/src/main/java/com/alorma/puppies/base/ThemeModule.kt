package com.alorma.puppies.base

import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import com.alorma.drawer_modules.ActionsModule
import com.alorma.drawer_modules.actions.SwitchAction

@Composable
fun DarkThemeModule(
    modifier: Modifier = Modifier,
    darkThemeState: MutableState<Boolean>
) {
    ActionsModule(
        modifier = modifier,
        icon = {
            val vector = if (darkThemeState.value) {
                Icons.Default.DarkMode
            } else {
                Icons.Default.LightMode
            }
            Icon(imageVector = vector, contentDescription = "Dark theme module icon")
        },
        title = "Dark mode"
    ) {
        SwitchAction(
            text = "Enabled",
            isChecked = darkThemeState.value,
            onChange = { darkThemeState.value = !darkThemeState.value })
    }
}