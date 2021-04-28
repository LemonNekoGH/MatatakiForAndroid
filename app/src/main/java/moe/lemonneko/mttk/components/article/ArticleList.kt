package moe.lemonneko.mttk.components.article

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.SwipeRefreshState
import moe.lemonneko.mttk.data.ViewModel
import moe.lemonneko.mttk.utils.*

@ExperimentalPagerApi
@Composable
fun ArticleList(
    page: Int
) {
    val viewModel = if (page == 0) {
        ViewModel.MainActivity.ArticleView.HottestArticle
    } else {
        ViewModel.MainActivity.ArticleView.LatestArticle
    }
    SwipeRefresh(
        state = viewModel.refreshState,
        onRefresh = {
            viewModel.refreshArticles()
        },
        modifier = Modifier.fillMaxSize(),
        indicator = { state, refreshTrigger ->
            MatatakiRefreshIndicator(state, refreshTrigger)
        }
    ) content@{
        if (viewModel.isError) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Row(
                    modifier = Modifier
                        .align(Alignment.Center),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = viewModel.errorText!!,
                        textAlign = TextAlign.Center,
                        color = Color.Gray
                    )
                }
            }
            return@content
        }
        LazyColumn(
            state = viewModel.scrollState,
            contentPadding = PaddingValues(
                bottom = 56.dp
            )
        ) {
            if (viewModel.firstLoad && viewModel.data.isEmpty()) {
                viewModel.firstLoad = false
                viewModel.refreshArticles()
            }
            items(viewModel.data.size) { index ->
                ArticleItem(
                    article = viewModel.data[index],
                    modifier = Modifier.padding(
                        top = if (index == 0) 8.dp else 4.dp,
                        bottom = if (index == viewModel.data.lastIndex) 8.dp else 4.dp
                    )
                )
            }
        }
    }
}

@Composable
internal fun MatatakiRefreshIndicator(state: SwipeRefreshState, refreshTrigger: Dp) {
    SwipeRefreshIndicator(
        state = state,
        refreshTriggerDistance = refreshTrigger,
        contentColor = ViewModel.theme.primary,
        backgroundColor = ViewModel.theme.surface
    )
}