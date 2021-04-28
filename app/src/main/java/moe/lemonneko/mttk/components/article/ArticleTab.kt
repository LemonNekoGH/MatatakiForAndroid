package moe.lemonneko.mttk.components.article

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import moe.lemonneko.mttk.data.ViewModel

@ExperimentalPagerApi
@Composable
private fun ArticleTabIndicator(
    modifier: Modifier
) {
    Box(modifier) {
        Box(
            Modifier
                .align(Alignment.Center)
                .height(2.dp)
                .width(30.dp)
                .background(color = ViewModel.theme.primary)
        )
    }
}

@ExperimentalPagerApi
@Composable
fun ArticleTab(
    pagerState: PagerState
) {
    val tabNames = arrayOf(
        ViewModel.locale.hottest,
        ViewModel.locale.latest
    )
    Column {
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            modifier = Modifier.height(48.dp),
            indicator = { tabPositions ->
                ArticleTabIndicator(
                    modifier = Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
                )
            }
        ) {
            tabNames.forEachIndexed { index, name ->
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = {
                        ViewModel.MainActivity.ArticleView.targetPage = index
                    },
                    modifier = Modifier
                        .background(ViewModel.theme.surface),
                    selectedContentColor = ViewModel.theme.primary,
                    text = {
                        Text(text = name)
                    }
                )
            }
        }
        Divider()
    }
}