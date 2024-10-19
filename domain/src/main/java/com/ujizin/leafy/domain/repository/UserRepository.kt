package com.ujizin.leafy.domain.repository

import com.ujizin.leafy.domain.model.User
import kotlinx.coroutines.flow.Flow

/** Interface to User repository implementation */
interface UserRepository {

    /**
     * Get user
     *
     * @return return an user
     */
    fun getUser(): Flow<User>

    /**
     * Update user
     *
     * @param user user to be updated
     */
    fun updateUser(user: User): Flow<Unit>
}
