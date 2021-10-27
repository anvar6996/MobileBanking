package uz.gita.mobilebanking1.presentation.ui.viewmodel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.mobilebanking1.presentation.ui.viewmodel.AuthorizationViewModel
import javax.inject.Inject

@HiltViewModel
class AuthorizationViewModelIml @Inject constructor() : AuthorizationViewModel, ViewModel() {
    override val openLoginScreenLiveData = MutableLiveData<Unit>()
    override val openRegisterScreenLiveData = MutableLiveData<Unit>()
    init {
        openLoginScreenLiveData.value = Unit
        openRegisterScreenLiveData.value = Unit
    }
    override fun nextLogin() {
        openLoginScreenLiveData.value = Unit
    }

    override fun nextRegister() {
        openRegisterScreenLiveData.value = Unit
    }
}