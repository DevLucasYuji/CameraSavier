package com.ujizin.leafy.core.repository.implementation

import com.ujizin.leafy.core.repository.datasource.UserDataSource
import com.ujizin.leafy.core.repository.mapper.UserMapper
import com.ujizin.leafy.domain.dispatcher.IoDispatcher
import com.ujizin.leafy.domain.model.User
import com.ujizin.leafy.domain.repository.UserRepository
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

internal class UserRepositoryImpl
@Inject
constructor(
    private val dataSource: UserDataSource,
    private val userMapper: UserMapper,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) : UserRepository {

    override fun getUser(): Flow<User> =
        dataSource.getUser().map(userMapper::toDomain).flowOn(dispatcher)

    override fun updateUser(user: User): Flow<Unit> =
        flow { emit(dataSource.updateUser(userMapper.toData(user))) }.flowOn(dispatcher)
}
