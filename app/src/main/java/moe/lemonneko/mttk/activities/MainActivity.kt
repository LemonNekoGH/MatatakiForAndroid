package moe.lemonneko.mttk.activities

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import moe.lemonneko.mttk.components.BottomBar
import moe.lemonneko.mttk.components.article.ArticleList
import moe.lemonneko.mttk.components.article.ArticleTab
import moe.lemonneko.mttk.data.ViewModel
import moe.lemonneko.mttk.theme.MatatakiTheme
import moe.lemonneko.mttk.utils.DoAnimate

class MainActivity : BaseActivity() {
    @ExperimentalAnimationApi
    @ExperimentalPagerApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MatatakiTheme {
                MainView()
            }
        }
    }
}

@ExperimentalAnimationApi
@ExperimentalPagerApi
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

@ExperimentalAnimationApi
@ExperimentalPagerApi
@Preview(
    showSystemUi = true
)
@Composable
fun MainViewPreview() {
    MainView()
}

@ExperimentalAnimationApi
@ExperimentalPagerApi
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
            if (targetPage != -1) {
                DoAnimate {
                    pagerState.animateScrollToPage(targetPage)
                    targetPage = -1
                }
            }
        }
    }
}

@Composable
fun FanTicketView() {
    Column {
        Surface(
            elevation = 4.dp
        ) {
            Row(
                modifier = Modifier
                    .height(48.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = ViewModel.locale.fanTicket,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.h6
                )
            }
        }
    }
}