package moe.lemonneko.mttk.utils

import com.fasterxml.jackson.databind.ObjectMapper
import moe.lemonneko.mttk.BuildConfig
import moe.lemonneko.mttk.data.ArticleBean
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject

object MatatakiApi {
    private val httpClient = OkHttpClient()

    fun getArticle(latest: Boolean, page: Int): String {
        logger.info("start to get articles, latest: $latest, page: $page")
        if (page < 1) return "{\"code\": -1}"
        var url = BuildConfig.API_BASE_URL + "/posts/"
        url += if (latest) {
            "timeRanking"
        } else {
            "scoreRanking"
        }
        url += "?channel=1&page=$page"
        val request = Request
            .Builder()
            .get()
            .url(url)
            .build()
        val body = httpClient.newCall(request).execute().body ?: return "{\"code\": -1}"
        return body.charStream().readText()
    }

    fun getArticleObjects(latest: Boolean, page: Int): Array<ArticleBean> {
        val json = getArticle(latest, page)
        val jsonObj = JSONObject(json)
        if (jsonObj.getInt("code") != 0) {
            return arrayOf()
        }
        val data = jsonObj.getJSONObject("data")
            .getJSONArray("list")
            .toString()
        val mapper = ObjectMapper()
        return mapper.readValue(data, Array<ArticleBean>::class.java)
    }
}