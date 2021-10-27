package uz.gita.mobilebanking1.presentation.ui.viewmodel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.mobilebanking1.data.requests.VeryfyRequest
import uz.gita.mobilebanking1.domain.AppRepository
import uz.gita.mobilebanking1.domain.AppRepositoryImpl
import uz.gita.mobilebanking1.presentation.ui.viewmodel.RegisterViewModel
import uz.gita.mobilebanking1.presentation.utils.isConnected
import javax.inject.Inject

@HiltViewModel
class RegisterViewModelImpl @Inject constructor(var repository: AppRepositoryImpl) : RegisterViewModel,
    ViewModel() {
    override val enableLoginLiveData = MutableLiveData<Unit>()

    override val disableLoginLiveData = MutableLiveData<Unit>()

    override val progressLiveData = MutableLiveData<Boolean>()

    override val errorLivaData = MutableLiveData<String>()

    override val successLiveData = MutableLiveData<String>()


    override fun regiter(data: VeryfyRequest) {
        if (!isConnected()) {
            errorLivaData.value = "Internetga ulanib qayta urining"
            return
        }
        progressLiveData.value = true
        disableLoginLiveData.value = Unit
        repository.regiter(data).onEach {
            progressLiveData.value = false
            enableLoginLiveData.value = Unit
            it.onFailure { th ->
                errorLivaData.value = th.message
            }
            it.onSuccess { mes ->
                successLiveData.value = mes
            }
        }.launchIn(viewModelScope)
    }
}