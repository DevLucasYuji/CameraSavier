package com.ujizin.leafy.core.repository.implementation

import android.content.Context
import android.media.RingtoneManager
import com.ujizin.leafy.domain.dispatcher.IoDispatcher
import com.ujizin.leafy.domain.model.Ringtone
import com.ujizin.leafy.domain.repository.RingtoneRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

internal class RingtoneRepositoryImpl
@Inject
constructor(
    @ApplicationContext private val context: Context,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) : RingtoneRepository {

    override fun getRingtones(): Flow<List<Ringtone>> =
        flow {
                val ringtoneManager = RingtoneManager(context)
                ringtoneManager.setType(RingtoneManager.TYPE_RINGTONE)

                val cursor = ringtoneManager.cursor

                val ringtoneList = mutableListOf<Ringtone>()

                if (cursor != null && cursor.moveToFirst()) {
                    do {
                        val title = cursor.getString(RingtoneManager.TITLE_COLUMN_INDEX)
                        val uri = cursor.getString(RingtoneManager.URI_COLUMN_INDEX)
                        val id = cursor.getString(RingtoneManager.ID_COLUMN_INDEX)

                        ringtoneList.add(Ringtone(id, title, "$uri/$id"))
                    } while (cursor.moveToNext())
                }

                cursor?.close()

                emit(ringtoneList)
            }
            .flowOn(dispatcher)
}
