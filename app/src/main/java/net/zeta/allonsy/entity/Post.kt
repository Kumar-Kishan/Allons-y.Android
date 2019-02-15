package net.zeta.allonsy.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "tbl_posts")
data class Post(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "_id")
    val id: String,

    @NonNull
    @ColumnInfo(name = "url")
    val url: String,

    @NonNull
    @ColumnInfo(name = "text")
    val text: String,

    @NonNull
    @ColumnInfo(name = "author")
    val author: String,

    @NonNull
    @ColumnInfo(name = "fistbumps")
    val fistbumps: Int
)