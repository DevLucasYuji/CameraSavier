package com.ujizin.leafy.domain

import com.ujizin.leafy.core.test.TestDispatcherRule
import com.ujizin.leafy.domain.model.Ringtone
import com.ujizin.leafy.domain.repository.RingtoneRepository
import com.ujizin.leafy.domain.usecase.ringtone.load.LoadRingtonesUseCase
import com.ujizin.leafy.domain.usecase.ringtone.load.LoadRingtonesUseCaseImpl
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class RingtoneUseCaseTest {

    @get:Rule var mainDispatcherRule = TestDispatcherRule()

    @MockK private lateinit var ringtoneRepository: RingtoneRepository

    private lateinit var loadRingtonesUseCase: LoadRingtonesUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        loadRingtonesUseCase = LoadRingtonesUseCaseImpl(ringtoneRepository)
    }

    @Test
    fun `given loadRingtonesUseCase, when called, then should return ringtone list`() = runTest {
        // Given
        every { ringtoneRepository.getRingtones() } returns flowOf(ringtones)

        // When
        val result = loadRingtonesUseCase().last()

        // Then
        Assert.assertEquals(ringtones, result)
    }

    companion object {
        private val ringtones = List(10) { Ringtone("$it", "title-$it", "uriContent-$it") }
    }
}
