package com.ujizin.leafy

import android.app.Application
import com.ujizin.leafy.alarm.notificator.AlarmNotificator
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class LeafyApplication : Application() {

    @Inject lateinit var alarmNotificator: AlarmNotificator

    override fun onCreate() {
        super.onCreate()
        alarmNotificator.createChannel()
    }
}
