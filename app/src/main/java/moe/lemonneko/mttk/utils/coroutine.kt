package moe.lemonneko.mttk.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun runOnUiThread(block: suspend CoroutineScope.() -> Unit) {
    withContext(Dispatchers.Main, block)
}

suspend fun runOnIoThread(block: suspend CoroutineScope.() -> Unit) {
    withContext(Dispatchers.IO, block)
}