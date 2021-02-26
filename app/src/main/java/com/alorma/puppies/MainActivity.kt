/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alorma.puppies

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.alorma.drawer_base.DebugDrawerLayout
import com.alorma.drawer_base.DebugDrawerValue
import com.alorma.drawer_base.rememberDebugDrawerState
import com.alorma.drawer_modules.BuildModule
import com.alorma.drawer_modules.DeviceModule
import com.alorma.puppies.base.DarkThemeModule
import com.alorma.puppies.extensions.getPuppyIdArgument
import com.alorma.puppies.ui.screen.PuppiesListScreen
import com.alorma.puppies.ui.screen.PuppyDetailScreen
import com.alorma.puppies.ui.theme.DarkColorPalette
import com.alorma.puppies.ui.theme.PuppiesTheme
import dev.chrisbanes.accompanist.insets.ProvideWindowInsets
import dev.chrisbanes.accompanist.insets.statusBarsPadding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PuppiesTheme { darkModeState ->
                ProvideWindowInsets {
                    mainContent(darkModeState)
                }
            }
        }
    }
}

@Composable
fun mainContent(darkModeState: MutableState<Boolean>) {
    DebugDrawerLayout(
        isDebug = { true },
        drawerColors = DarkColorPalette,
        debugDrawerState = rememberDebugDrawerState(DebugDrawerValue.Closed),
        drawerModules = {
            Column(modifier = Modifier.statusBarsPadding()) {
                val modulesModifier = Modifier
                    .padding(4.dp)
                    .clip(shape = MaterialTheme.shapes.medium)
                    .background(color = MaterialTheme.colors.surface)

                DarkThemeModule(modifier = modulesModifier, darkThemeState = darkModeState)
                DeviceModule(modifier = modulesModifier)
                BuildModule(modifier = modulesModifier)
            }
        }
    ) {
        buildNavGraph()
    }
}

@Composable
private fun buildNavGraph() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Navigation.NAV_PUPPY_LIST
    ) {
        composable(route = Navigation.NAV_PUPPY_LIST) {
            PuppiesListScreen(navController = navController)
        }
        composable(
            route = Navigation.NAV_PUPPY_DETAIL,
            arguments = listOf(
                navArgument(Navigation.NAV_PUPPY_DETAIL_ARGUMENT) {
                    type = NavType.IntType
                }
            )
        ) {
            PuppyDetailScreen(
                navController = navController,
                puppyId = it.getPuppyIdArgument(Navigation.NAV_PUPPY_DETAIL_ARGUMENT)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PuppiesTheme {
        mainContent(remember { mutableStateOf(true) })
    }
}
