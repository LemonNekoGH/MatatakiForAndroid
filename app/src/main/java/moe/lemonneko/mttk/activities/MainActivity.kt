package moe.lemonneko.mttk.activities

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import moe.lemonneko.mttk.components.BottomBar
import moe.lemonneko.mttk.components.article.ArticleList
import moe.lemonneko.mttk.components.article.ArticleTab
import moe.lemonneko.mttk.data.ViewModel
import moe.lemonneko.mttk.theme.MatatakiTheme

class MainActivity : BaseActivity() {
    @ExperimentalPagerApi
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MatatakiTheme {
                MainView()
            }
        }
    }
}

@ExperimentalPagerApi
@ExperimentalMaterialApi
@Composable
fun MainView() {
    Scaffold(
        bottomBar = {
            BottomBar(ViewModel.MainActivity.BottomBar)
        },
    ) {
        when (ViewModel.MainActivity.BottomBar.selected) {
            0 -> ArticleView()
            1 -> FanTicketView()
        }
    }
}

@ExperimentalPagerApi
@ExperimentalMaterialApi
@Preview(
    showSystemUi = true
)
@Composable
fun MainViewPreview() {
    MainView()
}

@ExperimentalPagerApi
@ExperimentalMaterialApi
@Composable
fun ArticleView() {
    ViewModel.MainActivity.ArticleView.apply {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            ArticleTab(
                pagerState = pagerState
            )
            HorizontalPager(state = pagerState) { page ->
                ArticleList(page)
            }
        }
    }
}

@Composable
fun FanTicketView() {
    Text(text = ViewModel.locale.fanTicket)
}