package ru.homecraft.glamapp.utils

import io.kotzilla.sdk.KotzillaSDK

actual object Logger : KotzillaLogger {

    override fun log(message: String) {
        KotzillaSDK.log(message)
    }

    override fun trace(tag: String, stackTrace: Boolean, block: () -> Unit) {
        KotzillaSDK.trace(
            tag = tag,
            stacktrace = stackTrace,
            code = block
        )
    }
}