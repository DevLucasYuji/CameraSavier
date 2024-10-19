package com.ujizin.leafy.domain.usecase.user.update

import com.ujizin.leafy.domain.model.User
import com.ujizin.leafy.domain.repository.UserRepository

internal class UpdateUserUseCaseImpl(private val userRepository: UserRepository) :
    UpdateUserUseCase {

    override fun invoke(user: User) = userRepository.updateUser(user)
}
