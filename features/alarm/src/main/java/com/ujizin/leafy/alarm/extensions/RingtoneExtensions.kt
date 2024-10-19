package com.ujizin.leafy.alarm.extensions

import android.content.Context
import android.media.RingtoneManager
import com.ujizin.leafy.core.ui.components.selector.ModalValue
import com.ujizin.leafy.domain.model.Ringtone

fun ModalValue<Ringtone>?.orDefault(context: Context) =
    this
        ?: run {
            val default = getDefaultRingtone(context)
            ModalValue(default.title, default)
        }

fun getDefaultRingtone(context: Context): Ringtone {
    val uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)
    val ringtone = RingtoneManager.getRingtone(context, uri)
    return Ringtone("0", ringtone.getTitle(context), uri.toString())
}
