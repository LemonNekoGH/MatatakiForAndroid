package moe.lemonneko.mttk.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import moe.lemonneko.mttk.data.ViewModel

@Composable
fun MatatakiTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = ViewModel.theme
    ) {
        content()
    }
}