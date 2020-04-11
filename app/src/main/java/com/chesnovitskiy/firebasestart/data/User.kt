package com.chesnovitskiy.firebasestart.data

data class User(
    val firstName: String? = null,
    val lastName: String? = null,
    val age: Int? = null,
    val sex: Sex? = null
)

enum class Sex {
    MALE {
        override fun getSex(): String = "Male"
    },
    FEMALE {
        override fun getSex(): String = "Female"
    },
    IT {
        override fun getSex(): String = "It"
    };

    abstract fun getSex(): String
}