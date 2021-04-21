package moe.lemonneko.mttk.data

import androidx.compose.runtime.mutableStateOf

open class ArticleTabViewModel {
    private val _selected = mutableStateOf(0)

    var selected
        get() = _selected.component1()
        set(value) {
            _selected.component2()(value)
        }
}