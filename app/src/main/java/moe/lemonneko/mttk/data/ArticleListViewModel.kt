package moe.lemonneko.mttk.data

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.mutableStateListOf

open class ArticleListViewModel(val isLatest: Boolean) {
    val data = mutableStateListOf<ArticleBean>()
    val state = LazyListState()
}