package moe.lemonneko.mttk.components.article

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.accompanist.glide.rememberGlidePainter
import moe.lemonneko.mttk.data.ArticleBean
import moe.lemonneko.mttk.data.ViewModel

private const val CDN = "https://ssimg.frontenduse.top/"

@ExperimentalAnimationApi
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
                modifier = Modifier.clickable {

                }
            ) {
                Column {
                    ArticleItemHeader(
                        avatar = avatar,
                        author = if (nickname.isNullOrEmpty()) {author} else {nickname},
                        modifier = Modifier.padding(start = 8.dp)
                    )
                    Image(
                        painter = rememberGlidePainter(request = CDN + cover),
                        contentDescription = "cover",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(160.dp),
                        contentScale = ContentScale.FillWidth
                    )
                    Text(
                        text = title ?: ViewModel.locale.titleOfArticleLoading,
                        style = MaterialTheme.typography.subtitle1,
                        modifier = Modifier.padding(bottom = 8.dp, start = 8.dp, top = 8.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun ArticleItemHeader(
    modifier: Modifier = Modifier,
    avatar: String?,
    author: String?
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.height(48.dp)
    ) {
        val useAvatar = if (avatar.isNullOrEmpty()) {
            "https://cdntest.frontenduse.top/test/img/default_avatar.9873fd7.png"
        } else {
            CDN + avatar
        }
        Image(
            painter = rememberGlidePainter(request = useAvatar),
            contentDescription = "avatar",
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape),
        )
        Text(
            text = author ?: "",
            modifier = Modifier.padding(start = 8.dp),
            style = MaterialTheme.typography.subtitle1
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ArticleItemHeaderPreview() {
    ArticleItemHeader(
        avatar = "https://ssimg.frontenduse.top/avatar/2021/01/26/0f71cb63a098421270b054470d03cb34.jpg",
        author = "LemonNeko"
    )
}

@ExperimentalAnimationApi
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
        "short_content": "Hey! I???m Miguel, and last week I dropped out of university to work on crypto. I???ll be using Mirror to share my crypto journey and talk about projects I work on and things I learn.",
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