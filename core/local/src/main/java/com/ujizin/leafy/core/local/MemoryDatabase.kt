package com.ujizin.leafy.core.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ujizin.leafy.core.local.converter.Converter
import com.ujizin.leafy.core.local.dao.AlarmDao
import com.ujizin.leafy.core.local.dao.AlbumDao
import com.ujizin.leafy.core.local.dao.PlantMemoryDao
import com.ujizin.leafy.core.local.model.AlarmEntity
import com.ujizin.leafy.core.local.model.AlbumEntity
import com.ujizin.leafy.core.local.model.PlantEntity

/** Memory database */
@Database(entities = [PlantEntity::class, AlarmEntity::class, AlbumEntity::class], version = 1)
@TypeConverters(Converter::class)
abstract class MemoryDatabase : RoomDatabase() {

    abstract fun alarmDao(): AlarmDao

    abstract fun albumDao(): AlbumDao

    abstract fun plantDao(): PlantMemoryDao
}
