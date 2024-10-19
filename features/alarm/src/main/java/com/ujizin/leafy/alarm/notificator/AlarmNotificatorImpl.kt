package com.ujizin.leafy.alarm.notificator

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.ujizin.leafy.core.components.R

internal class AlarmNotificatorImpl(private val context: Context) : AlarmNotificator {

    private val vibrateValues = longArrayOf(800, 500, 600, 300)

    private val notificationManagerCompat = NotificationManagerCompat.from(context)

    override fun getNotification(
        title: String,
        description: String,
        fullScreenIntent: PendingIntent?,
        contentIntent: PendingIntent?,
        notificationActions: List<NotificationCompat.Action>,
    ) =
        NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notification_monochrome)
            .setContentTitle(title)
            .setContentText(description)
            .setVibrate(vibrateValues)
            .setOngoing(true)
            .setAutoCancel(false)
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setCategory(NotificationCompat.CATEGORY_ALARM)
            .setFullScreenIntent(fullScreenIntent, true)
            .setContentIntent(contentIntent)
            .apply { notificationActions.forEach(::addAction) }
            .build()

    override fun cancelNotification(notificationId: Int) {
        notificationManagerCompat.cancel(notificationId)
    }

    override fun createChannel() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            return
        }

        val notificationChannel =
            NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH)

        notificationManagerCompat.createNotificationChannel(notificationChannel)
    }

    private companion object {
        const val CHANNEL_NAME = "leafy_alarm_channel"
        const val CHANNEL_ID = "alarm_channel"
    }
}
