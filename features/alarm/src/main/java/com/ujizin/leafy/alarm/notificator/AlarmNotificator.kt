package com.ujizin.leafy.alarm.notificator

import android.app.Notification
import android.app.PendingIntent
import androidx.core.app.NotificationCompat

/** Create alarm notification for Android system. */
interface AlarmNotificator {
    fun createChannel()

    fun getNotification(
        title: String,
        description: String,
        fullScreenIntent: PendingIntent? = null,
        contentIntent: PendingIntent? = null,
        notificationActions: List<NotificationCompat.Action>,
    ): Notification

    fun cancelNotification(notificationId: Int)
}
