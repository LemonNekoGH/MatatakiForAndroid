package moe.lemonneko.mttk.test

import moe.lemonneko.mttk.utils.MatatakiApi
import moe.lemonneko.mttk.utils.getValue
import moe.lemonneko.mttk.utils.isJson
import org.json.JSONObject
import org.junit.Test
import kotlin.test.expect

class ApiTest {
    @Test
    fun getArticle() {
        expect(true) {
            var passed = true
            val json = MatatakiApi.getArticle(true, 1)
            passed = passed and json.isJson()
            val jsonObj = JSONObject(json)
            passed = passed and (jsonObj.getValue<Int>("code") == 0)

            passed
        }
    }

    @Test
    fun getArticleObjects() {
        expect(true) {
            var passed = true
            val objects = MatatakiApi.getArticleObjects(false, 1)
            passed = passed and objects.isNotEmpty()
            passed
        }
    }
}