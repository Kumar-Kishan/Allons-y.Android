package net.zeta.allonsy.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import net.zeta.allonsy.dao.PostDao
import net.zeta.allonsy.entity.Post


@Database(entities = [Post::class], version = 1, exportSchema = false)
abstract class AllonsyDatabase: RoomDatabase() {

    abstract fun postsDao() : PostDao

    companion object {
        private var INSTANCE: AllonsyDatabase? = null
        fun getDatabase(context: Context) : AllonsyDatabase{
            if(INSTANCE == null){
                synchronized(AllonsyDatabase::class.java){
                    if(INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context,AllonsyDatabase::class.java, "allonsy_database").build()
                    }
                }
            }
            return INSTANCE!!
        }
    }
}