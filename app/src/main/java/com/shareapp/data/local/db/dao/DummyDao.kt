package com.shareapp.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.shareapp.data.local.db.entity.DummyEntity

//
// Created by Abhishek.dongle on 31-May-20.
//
@Dao
interface DummyDao {

    @Insert
    fun insertData(dummyEntity: DummyEntity)

    @Query("SELECT * FROM dummy_entity")
    fun getDummyList(): List<DummyEntity>
}