package net.zeta.allonsy.services

import com.google.gson.JsonObject
import io.reactivex.Observable
import retrofit2.http.*

interface AllonsyService {

    @POST(value= "identities")
    fun registerDevice(@Body payload: JsonObject) : Observable<JsonObject>

    @POST(value= "login")
    fun login(@Body payload: JsonObject) : Observable<JsonObject>

    @GET(value ="posts")
    fun getPosts(@Query(value = "count") count: Int,@Query(value = "skip") skip: Int) : Observable<JsonObject>
}