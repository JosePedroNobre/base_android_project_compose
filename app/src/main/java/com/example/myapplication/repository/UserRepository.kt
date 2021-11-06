package com.example.myapplication.repository

import com.example.myapplication.model.UserResponse
import com.example.myapplication.network.ApiInterface
import com.example.myapplication.utils.Resource
import dagger.hilt.android.scopes.ActivityScoped
import java.lang.Exception
import javax.inject.Inject

@ActivityScoped
class UserRepository @Inject constructor(private val apiInterface: ApiInterface) {

    suspend fun getUserResponse(): Resource<List<UserResponse>> {
        val response = try {
            apiInterface.getUserData()
        } catch (e: Exception) {
            return Resource.Error("Error occured : ${e.localizedMessage}")
        }
        return Resource.Success(response)
    }
}