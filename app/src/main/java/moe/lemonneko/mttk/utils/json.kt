@file:Suppress("UNCHECKED_CAST")

package moe.lemonneko.mttk.utils

import org.json.JSONException
import org.json.JSONObject

/**
 * return true if the string is JSON.
 */
internal fun String.isJson(): Boolean {
    return try {
        JSONObject(this)
        true
    } catch (e: JSONException) {
        false
    }
}

internal fun <T : Any> JSONObject.getValue(key: String): T {
    return get(key) as T
}