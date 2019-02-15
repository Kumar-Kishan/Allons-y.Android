package net.zeta.allonsy.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import net.zeta.allonsy.entity.Post


@Dao
interface PostDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(post: Post)

    @Query("SELECT * FROM tbl_posts")
    fun getAll(): DataSource.Factory<Int, Post>

    @Query("SELECT * FROM tbl_posts WHERE _id = :id")
    fun getById(id: String): LiveData<Post>

    @Query("DELETE FROM tbl_posts")
    fun deletePosts()
}