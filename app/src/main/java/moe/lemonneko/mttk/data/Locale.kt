package moe.lemonneko.mttk.data

class Locale(
    val article: String = "文章",
    val fanTicket: String = "Fan 票",
    val hottest: String = "最热门",
    val latest: String = "最新",
    val titleOfArticleLoading: String = "文章标题加载中...",
    val briefOfArticleLoading: String = "文章简要加载中...",
    val authorOfArticleLoading: String = "文章作者加载中...",
)

object Locales {
    val cn = Locale()
    val en = Locale(
        article = "Article"
    )
}