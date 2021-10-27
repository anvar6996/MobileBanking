package uz.gita.mobilebanking1.presentation.ui.viewmodel

import androidx.lifecycle.LiveData
import uz.gita.mobilebanking1.data.requests.VeryfyRequest

interface RegisterViewModel {
    val enableLoginLiveData: LiveData<Unit>
    val disableLoginLiveData: LiveData<Unit>
    val errorLivaData: LiveData<String>
    val successLiveData: LiveData<String>
    val progressLiveData: LiveData<Boolean>

    fun regiter(data: VeryfyRequest)
}