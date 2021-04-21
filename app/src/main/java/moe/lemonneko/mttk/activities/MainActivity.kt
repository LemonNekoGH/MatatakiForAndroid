package moe.lemonneko.mttk.activities

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import moe.lemonneko.mttk.components.ArticleList
import moe.lemonneko.mttk.components.ArticleTab
import moe.lemonneko.mttk.components.BottomBar
import moe.lemonneko.mttk.data.ViewModel
import moe.lemonneko.mttk.theme.MatatakiTheme

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MatatakiTheme {
                MainView()
            }
        }
    }
}

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

@Preview(
    showSystemUi = true
)
@Composable
fun MainViewPreview() {
    MainView()
}

@Composable
fun ArticleView() {
    ViewModel.MainActivity.ArticleView.apply {
        Column {
            ArticleTab(viewModel = ViewModel.MainActivity.ArticleView.Tab)
            if (ViewModel.MainActivity.ArticleView.Tab.selected == 0) {
                ArticleList(ViewModel.MainActivity.ArticleView.HottestArticle)
            } else {
                ArticleList(ViewModel.MainActivity.ArticleView.LatestArticle)
            }
        }
    }
}

@Composable
fun FanTicketView() {
    Text(text = ViewModel.locale.fanTicket)
}