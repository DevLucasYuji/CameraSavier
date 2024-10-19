package com.ujizin.leafy.domain

import com.ujizin.leafy.core.test.TestDispatcherRule
import com.ujizin.leafy.domain.model.Language
import com.ujizin.leafy.domain.model.Theme
import com.ujizin.leafy.domain.model.User
import com.ujizin.leafy.domain.repository.UserRepository
import com.ujizin.leafy.domain.result.Result
import com.ujizin.leafy.domain.usecase.user.load.LoadUserUseCase
import com.ujizin.leafy.domain.usecase.user.load.LoadUserUseCaseImpl
import com.ujizin.leafy.domain.usecase.user.update.UpdateUserUseCase
import com.ujizin.leafy.domain.usecase.user.update.UpdateUserUseCaseImpl
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class UserUseCaseTest {

    @get:Rule var mainDispatcherRule = TestDispatcherRule()

    @MockK lateinit var userRepository: UserRepository

    private lateinit var loadUserUseCase: LoadUserUseCase

    private lateinit var updateUserUseCase: UpdateUserUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        loadUserUseCase = LoadUserUseCaseImpl(userRepository)
        updateUserUseCase = UpdateUserUseCaseImpl(userRepository)
    }

    @Test
    fun `when loadUserUseCase then return the correct user`() = runTest {
        // Given
        every { userRepository.getUser() } returns flowOf(user)

        // When
        val result = loadUserUseCase().last()

        // Then
        Assert.assertEquals(Result.Success(user), result)
    }

    @Test
    fun `when updateUserUseCase then update the user`() = runTest {
        // Given
        every { userRepository.updateUser(user) } returns flowOf(Unit)

        // When
        val result = updateUserUseCase(user).last()

        // Then
        verify { userRepository.updateUser(user) }
    }

    companion object {
        private val user =
            User(nickname = "nickname", settings = User.Settings(Theme.Dark, Language.EN, true))
    }
}
