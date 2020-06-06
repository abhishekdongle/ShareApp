package com.shareapp.data.local.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

//
// Created by Abhishek.dongle on 31-May-20.
//
@Entity(tableName = "dummy_entity")
data class DummyEntity(
    @PrimaryKey(autoGenerate = true)
    @NotNull
    var id: Long,

    @ColumnInfo(name = "name")
    @NotNull
    var name: String,

    @ColumnInfo(name = "location")
    @NotNull
    var location: String,

    @ColumnInfo(name = "caption")
    @NotNull
    var caption: String,

    @ColumnInfo(name = "img_url")
    @NotNull
    var imageUrl: String
)