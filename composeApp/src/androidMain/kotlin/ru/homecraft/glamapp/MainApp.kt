package ru.homecraft.glamapp

import android.app.Application
import io.kotzilla.sdk.KotzillaSDK
import io.kotzilla.sdk.analytics.koin.analytics
import org.koin.android.ext.koin.androidContext
import org.koin.core.annotation.KoinApplication
import org.koin.core.context.startKoin
import org.koin.ksp.generated.configurationModules
import ru.homecraft.glamapp.utils.Logger

@KoinApplication
class MainApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApp)
            modules(configurationModules)
            analytics()
        }

        KotzillaSDK.log("INIT!")
        Logger.log("INIT From Logger!")
    }
}