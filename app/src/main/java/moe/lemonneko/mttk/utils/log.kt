package moe.lemonneko.mttk.utils

import android.util.Log

internal class Logger<T>(private val cls: Class<T>) {
    fun verbose(msg: Any?) = doLog(Log.VERBOSE, msg)
    fun info(msg: Any?) = doLog(Log.INFO, msg)
    fun debug(msg: Any?) = doLog(Log.DEBUG, msg)
    fun warn(msg: Any?) = doLog(Log.WARN, msg)
    fun error(msg: Any?) = doLog(Log.ERROR, msg)

    private fun doLog(level: Int, msg: Any?) {
        Log.println(level, cls.name, msg.toString())
    }
}

internal val Any.logger
    get() = Logger(this::class.java)