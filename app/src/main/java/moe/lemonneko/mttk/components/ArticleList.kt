package moe.lemonneko.mttk.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moe.lemonneko.mttk.data.ArticleListViewModel
import moe.lemonneko.mttk.utils.MatatakiApi
import moe.lemonneko.mttk.utils.logger

@Composable
fun ArticleList(
    viewModel: ArticleListViewModel
) {
    if (viewModel.data.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                viewModel.data.addAll(MatatakiApi.getArticleObjects(viewModel.isLatest, 1))
                logger.info("Article list size: ${viewModel.data.size}")
            }
        }
    } else {
        LazyColumn(
            state = viewModel.state,
            contentPadding = PaddingValues(
                bottom = 56.dp
            )
        ) {
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