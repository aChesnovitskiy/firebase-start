package com.chesnovitskiy.firebasestart.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.chesnovitskiy.firebasestart.data.Repository
import com.chesnovitskiy.firebasestart.data.User

class UsersViewModel(application: Application) : AndroidViewModel(application) {
    private val users: LiveData<List<User>> = Repository.getAllUsers()

    fun getUsers(): LiveData<List<User>> = users

    fun addUser(user: User) {
        Repository.addUser(user)
    }

    @Suppress("UNCHECKED_CAST")
    class ViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(UsersViewModel::class.java)) {
                return UsersViewModel(application) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}