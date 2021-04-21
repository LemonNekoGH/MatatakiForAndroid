package moe.lemonneko.mttk.data

import androidx.compose.runtime.mutableStateOf
import moe.lemonneko.mttk.theme.matatakiLight

object ViewModel {
    private val _locale = mutableStateOf(Locales.cn)
    private val _theme = mutableStateOf(matatakiLight())

    var locale
        get() = _locale.component1()
        set(value) {
            _locale.component2()(value)
        }

    var theme
        get() = _theme.component1()
        set(value) {
            _theme.component2()(value)
        }

    object MainActivity {
        object BottomBar : BottomBarViewModel()
        object ArticleView {
            object Tab : ArticleTabViewModel()
            object HottestArticle : ArticleListViewModel(false)
            object LatestArticle : ArticleListViewModel(true)
        }
    }
}