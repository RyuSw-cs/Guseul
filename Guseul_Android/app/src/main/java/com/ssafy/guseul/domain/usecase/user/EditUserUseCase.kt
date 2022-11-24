package com.ssafy.guseul.domain.usecase.user

import android.provider.ContactsContract.CommonDataKinds.Nickname
import com.ssafy.guseul.data.remote.datasource.user.model.UserRequest
import com.ssafy.guseul.data.remote.datasource.user.model.UserResponse
import com.ssafy.guseul.domain.entity.user.UserEntity
import com.ssafy.guseul.domain.repository.UserRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EditUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend fun editUserAdditionalInfo(nickname: String, address : String) : UserEntity{
        val request = UserRequest(nickname, address)
        return userRepository.editUserAdditionalInfo(request)
    }
}