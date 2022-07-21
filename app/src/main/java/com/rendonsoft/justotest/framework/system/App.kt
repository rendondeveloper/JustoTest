package com.rendonsoft.justotest.framework.system

import android.app.Application
import com.rendonsoft.justotest.BuildConfig
import com.rendonsoft.justotest.di.allModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level

class App : Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(if(BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@App)
            modules(allModules)
        }
    }
}