package moe.lemonneko.mttk.utils

import com.fasterxml.jackson.databind.ObjectMapper
import moe.lemonneko.mttk.BuildConfig
import moe.lemonneko.mttk.data.ArticleBean
import moe.lemonneko.mttk.data.ViewModel
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Proxy
import java.net.UnknownHostException

object MatatakiApi : EventListener() {
    private val httpClient = OkHttpClient.Builder()
        .eventListener(this)
        .build()

    /**
     * Get articles, return the json like this.
     * <pre>
     *     {
     *       "code": 0,
     *       "message": 成功,
     *       "data": {
     *          "count": 20,
     *          "list": {
     *              ...
     *          }
     *       }
     *     }
     * </pre>
     * @param latest If true, get article which sort by time, if false, get the articles which sort by read count
     */
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
        val response = try {
            httpClient.newCall(request).execute()
        } catch (e: Exception) {
            val message = when (e) {
                is UnknownHostException -> ViewModel.locale.unknownHostError
                else -> ViewModel.locale.unknownError
            }
            throw MatatakiApiException(-1, message)
        }
        return if (response.code == 200) {
            if (response.body != null) {
                response.body!!.charStream().readText()
            } else {
                """{"code": 204}"""
            }
        } else {
            """{"code": ${response.code}}"""
        }
    }

    /**
     * get article and convert json to objects.
     * @see moe.lemonneko.mttk.data.ArticleBean
     * @throws moe.lemonneko.mttk.utils.MatatakiApiException
     */
    @Throws(MatatakiApiException::class)
    fun getArticleObjects(latest: Boolean, page: Int): Array<ArticleBean> {
        val json = getArticle(latest, page)
        val jsonObj = JSONObject(json)
        val statusCode = jsonObj.getInt("code")
        var message: String
        ViewModel.locale.apply {
            message = when (statusCode) {
                204 -> emptyResponse
                500 -> internalServerError
                else -> unknownError
            }
        }
        if (statusCode != 0) {
            throw MatatakiApiException(statusCode, message)
        }
        val data = jsonObj.getJSONObject("data")
            .getJSONArray("list")
            .toString()
        val mapper = ObjectMapper()
        try {
            return mapper.readValue(data, Array<ArticleBean>::class.java)
        } catch (e: Exception) {
            message = ViewModel.locale.jsonFormatError
            throw MatatakiApiException(statusCode, message)
        }
    }

    override fun callFailed(call: Call, ioe: IOException) {
        logger.info("call failed. ${call.request().url}")
        ioe.printStackTrace()
    }

    override fun connectStart(call: Call, inetSocketAddress: InetSocketAddress, proxy: Proxy) {
        logger.info("start connecting ${call.request().url}")
    }

    override fun connectEnd(
        call: Call,
        inetSocketAddress: InetSocketAddress,
        proxy: Proxy,
        protocol: Protocol?
    ) {
        logger.info("connect successful: ${call.request().url}")
    }

    override fun requestFailed(call: Call, ioe: IOException) {
        logger.info("request failed: ${call.request().url}")
        ioe.printStackTrace()
    }
}

class MatatakiApiException(val code: Int, override val message: String) : IOException(message)