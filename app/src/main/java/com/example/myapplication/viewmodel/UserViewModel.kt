package com.example.myapplication.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.UserResponse
import com.example.myapplication.repository.UserRepository
import com.example.myapplication.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    // create the flag variables
    var isLoading = mutableStateOf(false)

    var getUserData: MutableLiveData<List<UserResponse>> =
        MutableLiveData<List<UserResponse>>()

    var getErrorMessage: MutableLiveData<String> = MutableLiveData("")

    suspend fun getUserData(): Resource<List<UserResponse>> {

        //talk with the repository
        val result = userRepository.getUserResponse()

        //set the loading flag to true
        isLoading.value = true

        //check state of the result that comes
        // from the repository and set the flag variables in the viewmodel
        if (result is Resource.Success) {
            getUserData.value = result.data!!
        } else if (result is Resource.Error) {
            getErrorMessage.value = result.message!!
        }

        return result
    }
}