package com.ujizin.leafy.core.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ujizin.leafy.core.local.converter.Converter
import com.ujizin.leafy.core.local.dao.AlarmDao
import com.ujizin.leafy.core.local.dao.AlbumDao
import com.ujizin.leafy.core.local.dao.PlantDao
import com.ujizin.leafy.core.local.model.AlarmEntity
import com.ujizin.leafy.core.local.model.AlbumEntity
import com.ujizin.leafy.core.local.model.PlantEntity

/** Database class. */
@Database(entities = [AlarmEntity::class, AlbumEntity::class, PlantEntity::class], version = 1)
@TypeConverters(Converter::class)
abstract class Database : RoomDatabase() {

    abstract fun alarmDao(): AlarmDao

    abstract fun albumDao(): AlbumDao

    abstract fun plantDao(): PlantDao

    companion object {
        internal const val NAME = "camera_reminder_db"
    }
}
