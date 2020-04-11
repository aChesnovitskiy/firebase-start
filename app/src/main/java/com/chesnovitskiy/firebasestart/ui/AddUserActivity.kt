package com.chesnovitskiy.firebasestart.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.chesnovitskiy.firebasestart.R
import com.chesnovitskiy.firebasestart.data.Sex
import com.chesnovitskiy.firebasestart.data.User
import kotlinx.android.synthetic.main.activity_add_user.*

class AddUserActivity : AppCompatActivity() {
    private var isSexValidate = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_user)

        et_sex.addTextChangedListener { editable ->
            val sex = editable.toString()
            isSexValidate = sexValidation(sex)
            if (isSexValidate || sex.isEmpty()) {
                wr_sex.apply {
                    isErrorEnabled = false
                    error = null
                }
            } else {
                wr_sex.apply {
                    isErrorEnabled = true
                    error = "Sex must be Male, Female or It"
                }
            }
        }

        btn_save.setOnClickListener {
            if (!isSexValidate) et_sex.setText("")

            val firstName = et_first_name.text.toString()
            val lastName = et_last_name.text.toString()
            val age = et_age.text.toString()
            val sex = et_sex.text.toString()

            if (firstName.isNotEmpty() && lastName.isNotEmpty() && age.isNotEmpty() && sex.isNotEmpty()) {
                saveUser(firstName, lastName, age, sex)
            } else {
                Toast.makeText(this, "All fields must be filled", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun saveUser(firstName: String, lastName: String, age: String, sex: String) {
        val intent = Intent().apply {
            putExtra("firstName", firstName)
            putExtra("lastName", lastName)
            putExtra("age", age)
            putExtra("sex", sex)
        }
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    private fun sexValidation(sex: String): Boolean {
        val sexLowerCase = sex.toLowerCase()
        return sexLowerCase == "male" || sexLowerCase == "female" || sexLowerCase == "it"
    }
}
