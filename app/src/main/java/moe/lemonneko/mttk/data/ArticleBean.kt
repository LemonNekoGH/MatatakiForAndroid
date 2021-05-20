package moe.lemonneko.mttk.data

import com.fasterxml.jackson.annotation.JsonProperty
import moe.lemonneko.mttk.utils.logger


class ArticleBean {
    var id = 0
    var uid = 0
    var author: String? = null
    var title: String? = null
    var hash: String? = null

    @JsonProperty("create_time")
    var createTime: String? = null
    var cover = "/material/default_cover.png"
        set(value) {
            if (value.isNotEmpty()) {
                field = value
            }
        }

    @JsonProperty("require_holdtokens")
    var requireHoldTokens: String? = null

    @JsonProperty("require_buy")
    var requireBuy = 0

    @JsonProperty("is_recommend")
    var isRecommend = 0

    @JsonProperty("short_content")
    var shortContent: String? = null
    var nickname: String? = null
    var avatar: String? = null
    var read = 0
    var likes = 0
    var status = 0

    @JsonProperty("user_is_recommend")
    var useIsRecommend = 0
    var tags: Array<Any>? = null

    @JsonProperty("user_is_token")
    var userIsToken = 0

    @JsonProperty("pay_symbol")
    var paySymbol: String? = null

    @JsonProperty("pay_price")
    var payPrice: String? = null

    @JsonProperty("pay_decimals")
    var payDecimals = 0

    @JsonProperty("pay_stock_quantity")
    var payStockQuantity = 0

    @JsonProperty("pay_platform")
    var payPlatform: String? = null

    @JsonProperty("token_id")
    var tokenId: String? = null

    @JsonProperty("token_amount")
    var tokenAmount: String? = null

    @JsonProperty("token_name")
    var tokenName: String? = null

    @JsonProperty("token_symbol")
    var tokenSymbol: String? = null

    @JsonProperty("token_decimals")
    var tokenDecimals: String? = null

    fun isPublishedOnGitHub() = hash?.startsWith("Gh")


}