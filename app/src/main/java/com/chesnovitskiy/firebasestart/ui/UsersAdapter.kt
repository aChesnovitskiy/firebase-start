package com.chesnovitskiy.firebasestart.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chesnovitskiy.firebasestart.R
import com.chesnovitskiy.firebasestart.data.User
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_user.*

class UsersAdapter : RecyclerView.Adapter<UsersAdapter.ViewHolder>() {
    private var users: List<User> = mutableListOf()

    fun setUsers(users: List<User>) {
        this.users = users
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.item_user, parent, false))
    }

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(users[position])

    inner class ViewHolder(containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {
        override val containerView: View?
            get() = itemView

        fun bind(user: User) {
            tv_user_first_name.text = user.firstName
            tv_user_last_name.text = user.lastName
            tv_user_age.text = user.age.toString()
            tv_user_sex.text = user.sex?.getSex()
        }
    }
}