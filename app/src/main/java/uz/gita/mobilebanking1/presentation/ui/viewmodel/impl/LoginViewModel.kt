package uz.gita.mobilebanking1.presentation.ui.viewmodel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.mobilebanking1.data.requests.AuthorizationRequest
import uz.gita.mobilebanking1.domain.AppRepository
import uz.gita.mobilebanking1.domain.AppRepositoryImpl
import uz.gita.mobilebanking1.presentation.ui.viewmodel.LoginScreenViewModel
import uz.gita.mobilebanking1.presentation.utils.isConnected
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(var repository: AppRepository) : ViewModel(),
    LoginScreenViewModel {
    override val enableLoginLiveData = MutableLiveData<Unit>()
    override val disableLoginLiveData = MutableLiveData<Unit>()
    override val progressLiveData = MutableLiveData<Boolean>()
    override val errorLivaData = MutableLiveData<String>()
    override val successLiveData = MutableLiveData<String>()

    override fun send(data: AuthorizationRequest) {
        if (!isConnected()) {
            errorLivaData.value = "Internetga ulanib qayta urining"
            return
        }
        progressLiveData.value = true
        disableLoginLiveData.value = Unit
        repository.userLogin(data).onEach {
            progressLiveData.value = false
            enableLoginLiveData.value = Unit
            it.onSuccess { mess ->
                successLiveData.value = mess
            }
            it.onFailure { throweble ->
                errorLivaData.value = throweble.message
            }
        }.launchIn(viewModelScope)
    }
}