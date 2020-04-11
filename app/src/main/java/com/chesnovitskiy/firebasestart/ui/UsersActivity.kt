package com.chesnovitskiy.firebasestart.ui

import android.app.Application
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.chesnovitskiy.firebasestart.R
import com.chesnovitskiy.firebasestart.data.Sex
import com.chesnovitskiy.firebasestart.data.User
import kotlinx.android.synthetic.main.activity_users.*

class UsersActivity : AppCompatActivity() {
    private val userAdapter: UsersAdapter by lazy { UsersAdapter() }
    private val viewModel: UsersViewModel by lazy {
        val vmFactory = UsersViewModel.ViewModelFactory(Application())
        ViewModelProvider(this, vmFactory).get(UsersViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)

        with(rv_users) {
            adapter = userAdapter
            layoutManager = LinearLayoutManager(this@UsersActivity)
        }

        viewModel.getUsers().observe(this, Observer {
            userAdapter.setUsers(it)
        })

        fab_add.setOnClickListener {
            startActivityForResult(Intent(this, AddUserActivity::class.java), 1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (data == null) return

        val firstName = data.getStringExtra("firstName")
        val lastName = data.getStringExtra("lastName")
        val age = data.getStringExtra("age")
        val sex = data.getStringExtra("sex")

        Log.d("My_UsersActivity", "Get user: $firstName, $lastName, $age, $sex")
        val user = User(firstName, lastName, age?.toInt(), getSexFromString(sex!!))
        viewModel.addUser(user)
    }

    private fun getSexFromString(sex: String): Sex? {
        return when(sex.toLowerCase()) {
            "male" -> Sex.MALE
            "female" -> Sex.FEMALE
            "it" -> Sex.IT
            else -> error("Wrong sex")
        }
    }
}
