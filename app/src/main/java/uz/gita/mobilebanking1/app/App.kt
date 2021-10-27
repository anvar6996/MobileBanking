package uz.gita.mobilebanking1.app

import android.app.Application
import com.nabinbhandari.android.permissions.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import uz.gita.mobilebanking1.data.MySharedPreferences

@HiltAndroidApp
class App : Application() {
    companion object {
        lateinit var instance: App
            private set
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        MySharedPreferences.init(this)
        instance = this
    }
}