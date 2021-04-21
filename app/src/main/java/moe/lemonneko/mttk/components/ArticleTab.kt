package moe.lemonneko.mttk.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import moe.lemonneko.mttk.data.ArticleTabViewModel
import moe.lemonneko.mttk.data.ViewModel

@Composable
private fun ArticleTabIndicator(
    tabPositions: List<TabPosition>,
    viewModel: ArticleTabViewModel
) {
    Box(
        Modifier
            .tabIndicatorOffset(tabPositions[viewModel.selected])
    ) {
        Box(
            Modifier
                .align(Alignment.Center)
                .height(2.dp)
                .width(30.dp)
                .background(color = ViewModel.theme.primary)
        )
    }
}

@Composable
fun ArticleTab(
    viewModel: ArticleTabViewModel
) {
    val tabNames = arrayOf(
        ViewModel.locale.hottest,
        ViewModel.locale.latest
    )
    Column {
        TabRow(
            selectedTabIndex = viewModel.selected,
            modifier = Modifier.height(48.dp),
            indicator = { tabPositions ->
                ArticleTabIndicator(
                    tabPositions = tabPositions,
                    viewModel = viewModel
                )
            }
        ) {
            for (index in tabNames.indices) {
                Tab(
                    selected = viewModel.selected == index,
                    onClick = {
                        viewModel.selected = index
                    },
                    modifier = Modifier
                        .background(ViewModel.theme.surface),
                    selectedContentColor = ViewModel.theme.primary
                ) {
                    Text(text = tabNames[index])
                }
            }
        }
        Divider()
    }
}

@Composable
@Preview(showSystemUi = true)
fun ArticleTabPreview() {
    ArticleTab(viewModel = ArticleTabViewModel())
}