package ru.homecraft.glamapp.utils

import io.kotzilla.sdk.KotzillaSDK

actual object Logger : KotzillaLogger {

    override fun log(message: String) {
        KotzillaSDK.log(message)
    }

    override fun<T: Any> trace(tag: String, stackTrace: Boolean, block: () -> T): T {
        return KotzillaSDK.trace(
            tag = tag,
            stacktrace = stackTrace,
            code = block
        )
    }

    override suspend fun<T: Any> suspendTrace(tag: String, stackTrace: Boolean, block: suspend () -> T): T {
        return KotzillaSDK.suspendTrace(
            tag = tag,
            stacktrace = stackTrace,
            code = block
        )
    }
}