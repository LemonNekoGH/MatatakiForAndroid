package moe.lemonneko.mttk.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Article
import androidx.compose.material.icons.filled.ConfirmationNumber
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import moe.lemonneko.mttk.data.BottomBarViewModel
import moe.lemonneko.mttk.data.ViewModel

/**
 * Simplify BottomNavigationItem
 */
@Composable
fun RowScope.BottomNavigationItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: ImageVector,
    label: String
) {
    BottomNavigationItem(
        selected = selected,
        onClick = onClick,
        icon = @Composable {
            Icon(imageVector = icon, contentDescription = label)
        },
        label = @Composable {
            Text(text = label)
        })
}

@Composable
fun BottomBar(
    viewModel: BottomBarViewModel
) {
    val icons = arrayOf(
        Icons.Filled.Article,
        Icons.Filled.ConfirmationNumber
    )
    val labels = arrayOf(
        ViewModel.locale.article,
        ViewModel.locale.fanTicket
    )

    BottomNavigation(
        backgroundColor = ViewModel.theme.surface,
        contentColor = ViewModel.theme.primary
    ) {
        for (index in icons.indices) {
            BottomNavigationItem(
                selected = viewModel.selected == index,
                onClick = {
                    viewModel.selected = index
                },
                icon = icons[index],
                label = labels[index]
            )
        }
    }
}