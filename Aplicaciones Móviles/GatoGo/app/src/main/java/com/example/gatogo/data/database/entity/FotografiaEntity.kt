package com.example.gatogo.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "fotografia",
    foreignKeys = [
        ForeignKey(
            entity = AvistamientoEntity::class,
            parentColumns = ["id_avistamiento"],
            childColumns = ["id_avistamiento"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["id_avistamiento"])]
)
data class FotografiaEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_fotografia") val idFotografia: Int = 0,
    @ColumnInfo(name = "id_avistamiento") val idAvistamiento: Int,
    @ColumnInfo(name = "url_fotografia") val urlFotografia: String
)
