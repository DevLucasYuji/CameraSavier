package com.ujizin.leafy.preferences

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ujizin.leafy.domain.model.User
import com.ujizin.leafy.domain.usecase.user.update.UpdateUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.launchIn

@HiltViewModel
class PreferencesViewModel @Inject constructor(private val updateUser: UpdateUserUseCase) :
    ViewModel() {

    fun update(user: User) {
        updateUser(user).launchIn(viewModelScope)
    }
}
