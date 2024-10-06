package com.example.home.util

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.debugInspectorInfo

fun Modifier.debugComposable(composableName: String): Modifier = composed(
    inspectorInfo = debugInspectorInfo {
        name = "debugComposable"
        value = composableName
    }
) {
    var compositionCount by remember { mutableIntStateOf(0) }

    SideEffect {
        compositionCount += 1
        Log.d("DebugComposition", "Composing $composableName: $compositionCount times")
    }
    this
}

// Example usage in a Composable function
@Composable
fun ParentComponent() {
    Column {
        Text(
            text = "Child 1",
            modifier = Modifier.debugComposable("ChildComponent1")
        )
        Text(
            text = "Child 2",
            modifier = Modifier.debugComposable("ChildComponent2")
        )
    }
}

@Composable
fun DebugComposition(
    composableName: String,
    content: @Composable () -> Unit
) {
    // Track composition count
    var compositionCount by remember { mutableIntStateOf(0) }
    // Update the composition count
    SideEffect {
        compositionCount += 1
        Log.d("DebugComposition", "Composing $composableName: $compositionCount times")
    }
    // Track recomposition skips
    DisposableEffect(Unit) {
        onDispose {
            Log.d("DebugComposition", "$composableName skipped composition")
        }
    }
    // Display the content
    content()
}