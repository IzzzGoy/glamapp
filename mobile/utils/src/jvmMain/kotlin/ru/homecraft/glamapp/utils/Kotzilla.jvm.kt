package ru.homecraft.glamapp.utils

import io.kotzilla.sdk.KotzillaCoreSDK

val KotzillaSDK: io.kotzilla.sdk.KotzillaCore = KotzillaCoreSDK.setup(
    version = "1.0.0",
    apiToken = "***************************",
).attachDefaultKoin()

actual object Logger : KotzillaLogger {
    override fun log(message: String) {
        KotzillaSDK.log(message)
    }

    override fun<T: Any> trace(tag: String, stackTrace: Boolean, block: () -> T): T {
        return KotzillaSDK.trace(
            tag = tag,
            stacktrace = stackTrace,
            code = block,
        )
    }
}