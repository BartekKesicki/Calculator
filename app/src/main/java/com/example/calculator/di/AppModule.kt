package com.example.calculator.di

import android.content.Context
import com.example.calculator.app.App
import com.example.calculator.calcmanager.CalcManager
import com.example.calculator.calcmanager.CalcManagerImpl
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideContext(app: App) : Context {
        return app.applicationContext
    }

    @Provides
    fun provideCalcManager() : CalcManager {
        return CalcManagerImpl()
    }
}