package com.ujizin.leafy.alarm

import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import android.os.CountDownTimer
import android.os.IBinder
import android.os.PowerManager
import androidx.core.app.NotificationCompat
import androidx.core.app.TaskStackBuilder
import com.ujizin.leafy.alarm.extensions.getAlarmIntent
import com.ujizin.leafy.alarm.extensions.orDefaultRingtone
import com.ujizin.leafy.alarm.notificator.AlarmNotificator
import com.ujizin.leafy.alarm.receiver.StopPlantServiceActivity
import com.ujizin.leafy.core.components.R as CR
import com.ujizin.leafy.core.navigation.Destination
import com.ujizin.leafy.core.ui.props.RequestCode
import com.ujizin.leafy.features.alarm.R
import dagger.hilt.android.AndroidEntryPoint
import java.io.IOException
import javax.inject.Inject
import kotlin.time.Duration.Companion.minutes

@AndroidEntryPoint
class AlarmService : Service() {

    private var mediaPlayer: MediaPlayer? = null

    @Inject lateinit var alarmNotificator: AlarmNotificator

    private val Intent.plantId
        get() = getLongExtra(PLANT_ID, -1)

    private val Intent.ringtoneUri
        get() = getStringExtra(RINGTONE_URI_STRINGIFY_ARG)?.let(Uri::parse).orDefaultRingtone()

    private val countDownTimer =
        object : CountDownTimer(TIME_OUT_IN_MILLISECONDS, TICK_IN_MILLISECONDS) {
            override fun onTick(millisUntilFinished: Long) = Unit /* no-op */

            override fun onFinish() {
                mediaPlayer?.pause()
            }
        }

    private val wakeLock: PowerManager.WakeLock by lazy {
        (getSystemService(POWER_SERVICE) as PowerManager).run {
            newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, ALARM_WAKE_LOCK_TAG)
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            STOP_ACTION -> stopService()
            else -> intent?.let(::startAlarm)
        }

        return super.onStartCommand(intent, flags, startId)
    }

    private fun stopService() {
        stopForeground()
        stopSelf()
    }

    @Suppress("DEPRECATION")
    private fun stopForeground() =
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.N -> stopForeground(STOP_FOREGROUND_REMOVE)
            else -> stopForeground(true)
        }

    private fun startAlarm(intent: Intent) {
        wakeLock.acquire(1.minutes.inWholeMilliseconds)
        intent.ringtoneUri.play()
        countDownTimer.start()
        startForeground(NOTIFICATION_ID, intent.getNotification())
    }

    private fun Intent.getNotification(): Notification {
        val title = getStringExtra(TITLE_ARG) ?: getString(CR.string.app_name)
        val description = getStringExtra(DESCRIPTION_ARG) ?: getString(CR.string.alarm)
        val contentIntent = getPlantPendingIntent(plantId)
        val stopIntent = getAlarmPendingIntent(action = STOP_ACTION)

        return alarmNotificator.getNotification(
            title = title,
            description = description,
            contentIntent = contentIntent,
            notificationActions =
                listOf(NotificationCompat.Action(0, getString(R.string.stop), stopIntent)),
        )
    }

    private fun getAlarmPendingIntent(action: String) =
        PendingIntent.getService(
            this,
            RequestCode.ALARM,
            getAlarmIntent(action),
            PendingIntent.FLAG_IMMUTABLE,
        )

    private fun Uri.play() {
        try {
            val audioAttributes =
                AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_ALARM)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build()

            mediaPlayer =
                MediaPlayer().apply {
                    setDataSource(this@AlarmService, this@play)
                    setAudioAttributes(audioAttributes)
                    prepareAsync()
                    setOnPreparedListener { start() }
                }
        } catch (_: IOException) {}
    }

    private fun getPlantPendingIntent(plantId: Long) =
        TaskStackBuilder.create(this).run {
            val deeplinkIntent =
                Intent(Intent.ACTION_VIEW, Destination.PlantDetails.createDeeplink(plantId)).apply {
                    putExtra(ALARM_SERVICE_ARG, true)
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                }
            val stopService = StopPlantServiceActivity.getIntent(this@AlarmService)
            addNextIntentWithParentStack(deeplinkIntent)
            addNextIntent(stopService)
            getPendingIntent(0, PendingIntent.FLAG_IMMUTABLE)
        }

    override fun onDestroy() {
        wakeLock.release()
        countDownTimer.cancel()
        alarmNotificator.cancelNotification(NOTIFICATION_ID)
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? = null

    companion object {
        private const val TIME_OUT_IN_MILLISECONDS = 60000L
        private const val TICK_IN_MILLISECONDS = 1000L
        private const val ALARM_WAKE_LOCK_TAG = "leafy:alarm_wake_lock"
        private const val NOTIFICATION_ID = 1
        internal const val TITLE_ARG = "alarm_title"
        internal const val DESCRIPTION_ARG = "alarm_description"
        internal const val RINGTONE_URI_STRINGIFY_ARG = "alarm_ringtone_uri_stringify"
        const val ALARM_SERVICE_ARG = "alarm_service"
        const val PLANT_ID = "alarm_plant_id"
        const val STOP_ACTION = "alarm_service_stop"
    }
}
