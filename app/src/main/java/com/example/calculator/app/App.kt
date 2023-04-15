package com.example.calculator.app

import android.app.Application
import com.example.calculator.di.AppComponent
import com.example.calculator.di.DaggerAppComponent

open class App : Application() {

    val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    open fun initializeComponent(): AppComponent {
        return DaggerAppComponent.factory().create(applicationContext)
    }
}