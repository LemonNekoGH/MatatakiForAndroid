package moe.lemonneko.mttk.data

class Locale(
    val article: String = "文章",
    val fanTicket: String = "Fan 票",
    val hottest: String = "最热门",
    val latest: String = "最新",
    val titleOfArticleLoading: String = "文章标题加载中...",
    val briefOfArticleLoading: String = "文章简要加载中...",
    val authorOfArticleLoading: String = "文章作者加载中...",
    val tryAgainLater: String = "请稍后再试",
    val getArticleFailed: String = "文章加载失败",
    val readTimeout: String = "连接超时",
    val emptyResponse: String = "服务器返回了空内容",
    val internalServerError: String = "服务器出了点岔子",
    val unknownError: String = "不知道是什么错误",
    val jsonFormatError: String = "JSON格式化出错"
)

object Locales {
    val cn = Locale()
    val en = Locale(
        article = "Article"
    )
}