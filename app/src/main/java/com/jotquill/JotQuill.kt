package com.jotquill

import android.app.Application
import com.jotquill.data.AppDatabase

class JotQuill : Application() {
    override fun onCreate() {
        super.onCreate()

        AppDatabase.getInstance(this)
    }
}