package net.zeta.allonsy.repository

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import net.zeta.allonsy.dao.PostDao
import net.zeta.allonsy.database.AllonsyDatabase
import net.zeta.allonsy.entity.Post
import java.lang.ref.WeakReference

class PostRepository(application: Application) {

    private var postDao : PostDao = AllonsyDatabase.getDatabase(context = application).postsDao()


    fun getAll() : DataSource.Factory<Int, Post>{
        return postDao.getAll()
    }

    fun addPosts(postList: MutableList<Post>){
        InsertPostsTask(dao = postDao).execute(postList)
    }

    companion object {
        class InsertPostsTask(dao: PostDao): AsyncTask<List<Post>, Void, Unit>(){
            private val daoRef = WeakReference<PostDao>(dao)
            override fun doInBackground(vararg params: List<Post>?) {
                params[0]?.forEach {
                    daoRef.get()?.insert(post = it)
                }
            }
        }

        class DeletePostsTask(dao: PostDao): AsyncTask<Void,Void,Unit>(){
            private val daoRef = WeakReference<PostDao>(dao)
            override fun doInBackground(vararg params: Void?) {
                daoRef.get()?.deletePosts()
            }
        }
    }
}