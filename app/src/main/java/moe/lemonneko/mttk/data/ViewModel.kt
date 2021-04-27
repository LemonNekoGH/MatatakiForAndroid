package moe.lemonneko.mttk.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import moe.lemonneko.mttk.theme.matatakiLight

object ViewModel {
    var locale by mutableStateOf(Locales.cn)
    var theme by mutableStateOf(matatakiLight())

    object MainActivity {
        object BottomBar : BottomBarViewModel()

        @ExperimentalPagerApi
        object ArticleView {
            @ExperimentalPagerApi
            val pagerState = PagerState(2)
            var currentPage by mutableStateOf(pagerState.currentPage)

            object HottestArticle : ArticleListViewModel(false)
            object LatestArticle : ArticleListViewModel(true)
        }
    }
}