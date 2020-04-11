package com.chesnovitskiy.firebasestart.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

object Repository {
    const val TAG = "My_Repository"

    private val database by lazy { Firebase.firestore }

    fun getAllUsers(): LiveData<List<User>> {
        val result = MutableLiveData<List<User>>()

        database.collection("users")
            .addSnapshotListener { snapshot, exception ->
                if (exception != null) {
                    Log.w(TAG, "Listen failed", exception)
                    return@addSnapshotListener
                }

                if (snapshot != null) {
                    val users = mutableListOf<User>()

                    for (user in snapshot) {
                        Log.d(TAG, "${user.id} => ${user.data}")
                        users.add(user.toObject<User>())
                    }

                    result.value = users
                } else {
                    Log.d(TAG, "Current data is null")
                }
            }

        return result
    }

    fun addUser(user: User) {
        database.collection("users")
            .add(user)
            .addOnSuccessListener { reference ->
                Log.d(TAG, "User added with ID: ${reference.id}")
            }
            .addOnFailureListener { error ->
                Log.w(TAG, "Error adding user", error)
            }
    }
}