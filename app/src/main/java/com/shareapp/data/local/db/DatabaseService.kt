package com.shareapp.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.shareapp.data.local.db.dao.DummyDao
import com.shareapp.data.local.db.entity.DummyEntity
import javax.inject.Singleton

//
// Created by Abhishek.dongle on 31-May-20.
//

@Singleton
@Database(entities = [DummyEntity::class], exportSchema = false, version = 1)
abstract class DatabaseService : RoomDatabase() {
    abstract fun dummyDao(): DummyDao
}
