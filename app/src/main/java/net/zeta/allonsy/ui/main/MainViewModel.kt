package net.zeta.allonsy.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import net.zeta.allonsy.entity.Post
import net.zeta.allonsy.managers.NetworkManager
import net.zeta.allonsy.repository.PostRepository


class MainViewModel(application: Application) : AndroidViewModel(application) {
    private var offset : Int = 0
    private var count: Int = 100
    private val postRepository = PostRepository(application= application)
    var allPosts : LiveData<PagedList<Post>> = LivePagedListBuilder(postRepository.getAll(),10).build()

    fun loadMore(){
        NetworkManager.allonsyService.getPosts(count = count,skip = offset).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeBy (
            onNext = {
                response ->
                    var postList = mutableListOf<Post>()
                    response["items"].asJsonArray.map { it.asJsonObject }.mapTo(postList) { postItem ->
                        Post(id= postItem["_id"].asString,text = postItem["text"].asString, fistbumps = postItem["fistbumps"].asInt,url = postItem["url"].asString, author = postItem["author"].asJsonObject["name"].asString)
                    }
                    offset = response["offset"].asInt
                    postRepository.addPosts(postList)
                    return@subscribeBy
            },
            onError = {
                error ->
                    error.printStackTrace()
            }
        )
    }

}
