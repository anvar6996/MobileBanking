package uz.gita.mobilebanking1.presentation.ui.viewmodel

import androidx.lifecycle.LiveData

interface AuthorizationViewModel {
    val openLoginScreenLiveData: LiveData<Unit>
    val openRegisterScreenLiveData: LiveData<Unit>
    fun nextLogin()
    fun nextRegister()
}