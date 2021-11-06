package com.example.myapplication.network

import com.example.myapplication.model.UserResponse
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface ApiInterface {

    @GET("todos")
    suspend fun getUserData(
    ): List<UserResponse>

}