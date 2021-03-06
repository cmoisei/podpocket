package com.furkanaskin.app.podpocket.ui.profile.account_detail

import androidx.databinding.ObservableField
import com.furkanaskin.app.podpocket.core.BaseViewModel
import com.furkanaskin.app.podpocket.db.AppDatabase
import com.furkanaskin.app.podpocket.db.entities.UserEntity
import com.furkanaskin.app.podpocket.service.PodpocketAPI
import com.google.firebase.database.FirebaseDatabase
import javax.inject.Inject
import org.jetbrains.anko.doAsync

/**
 * Created by Furkan on 17.05.2019
 */

class AccountDetailViewModel @Inject constructor(api: PodpocketAPI, appDatabase: AppDatabase) :
    BaseViewModel(api, appDatabase) {
    var item: ObservableField<String> = ObservableField("")

    var userData: UserEntity? = null

    fun getUserName(): String = userData?.userName ?: ""

    fun getName(): String = userData?.name ?: ""

    fun getSurname(): String = userData?.surname ?: ""

    fun changeUserData(user: UserEntity) {
        doAsync {
            db?.userDao()?.updateUser(user)
        }
    }

    fun equalizeFirebase(updateUser: UserEntity) {
        val usersRef = FirebaseDatabase.getInstance().getReference("users")
        usersRef.child(user?.uniqueId ?: mAuth.currentUser?.uid ?: "").setValue(updateUser)
    }
}