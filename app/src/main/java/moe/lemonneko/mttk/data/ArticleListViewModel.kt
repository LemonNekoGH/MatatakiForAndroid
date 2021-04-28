package moe.lemonneko.mttk.data

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.google.accompanist.swiperefresh.SwipeRefreshState
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import moe.lemonneko.mttk.utils.MatatakiApi
import moe.lemonneko.mttk.utils.logger
import moe.lemonneko.mttk.utils.runOnIoThread
import moe.lemonneko.mttk.utils.runOnUiThread
import java.net.SocketTimeoutException

/**
 * View model for ArticleList
 * @see moe.lemonneko.mttk.components.ArticleList
 */
open class ArticleListViewModel(private val isLatest: Boolean) {
    val data = mutableStateListOf<ArticleBean>()
    val scrollState = LazyListState()
    val refreshState = SwipeRefreshState(false)

    var errorText by mutableStateOf<String?>(null)
    var firstLoad by mutableStateOf(true)

    val isError
        get() = errorText != null

    fun refreshArticles() {
        GlobalScope.launch {
            runOnIoThread {
                runOnUiThread {
                    refreshState.isRefreshing = true
                }

                kotlin.runCatching {
                    data.clear()
                    data.addAll(MatatakiApi.getArticleObjects(isLatest, 1))
                    errorText = null
                    logger.info("Article list size: ${data.size}")
                }.onFailure {
                    var message = it.message
                    if (it is SocketTimeoutException) {
                        message = ViewModel.locale.readTimeout
                    }
                    errorText = """
                        ${ViewModel.locale.getArticleFailed}
                        $message
                        ${ViewModel.locale.clickToRetry}
                    """.trimIndent()
                    logger.info("Get article failed.")
                    it.printStackTrace()
                }
                runOnUiThread {
                    refreshState.isRefreshing = false
                }
            }
        }
    }
}