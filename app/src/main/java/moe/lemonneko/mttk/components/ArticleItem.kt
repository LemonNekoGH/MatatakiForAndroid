package moe.lemonneko.mttk.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fasterxml.jackson.databind.ObjectMapper
import moe.lemonneko.mttk.data.ArticleBean
import moe.lemonneko.mttk.data.ViewModel

@Composable
fun ArticleItem(
    article: ArticleBean,
    modifier: Modifier
) {
    article.apply {
        Card(
            modifier = modifier
                .padding(
                    start = 8.dp,
                    end = 8.dp
                )
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .clickable { }
            ) {
                Column(
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(
                        text = title ?: ViewModel.locale.titleOfArticleLoading,
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    Text(
                        text = shortContent ?: ViewModel.locale.briefOfArticleLoading,
                        style = MaterialTheme.typography.body2,
                        color = Color.Gray,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    Text(
                        text = article.nickname ?: article.author
                        ?: ViewModel.locale.authorOfArticleLoading,
                        style = MaterialTheme.typography.body2
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ArticleItemPreview() {
    val articleJson = """
        {
        "id": 102580,
        "uid": 1411,
        "author": "willyandor",
        "title": "Hello, Mirror!",
        "status": 0,
        "hash": "QmSPGzjcJMJ5WskkDfW6FoXefNfBsSLhUXd2qYBaNmqCZv",
        "create_time": "2021-04-13T08:29:54.000Z",
        "cover": "",
        "require_holdtokens": 0,
        "require_buy": 0,
        "short_content": "Hey! I’m Miguel, and last week I dropped out of university to work on crypto. I’ll be using Mirror to share my crypto journey and talk about projects I work on and things I learn.",
        "is_recommend": 0,
        "nickname": "USER-Q34KKo7xr0",
        "avatar": "/avatar/2021/04/13/610008f73b1037994637355dd2fb591d.png",
        "user_is_recommend": 0,
        "read": 1,
        "likes": 0,
        "pay_platform": null,
        "pay_symbol": null,
        "pay_price": null,
        "pay_decimals": null,
        "pay_stock_quantity": null,
        "token_id": null,
        "token_amount": null,
        "token_name": null,
        "token_symbol": null,
        "token_decimals": null,
        "tags": [],
        "user_is_token": 0
        }
    """.trimIndent()
    val article = ObjectMapper().readValue(articleJson, ArticleBean::class.java)
    ArticleItem(article = article, modifier = Modifier)
}