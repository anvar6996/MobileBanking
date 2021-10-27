package uz.gita.mobilebanking1.presentation.ui.viewmodel

import androidx.lifecycle.LiveData
import uz.gita.mobilebanking1.data.requests.SmsVeryfyRequest


interface SmsVeryfiyViewModel {
    val enableLoginLiveData: LiveData<Unit>
    val disableLoginLiveData: LiveData<Unit>
    val errorLivaData: LiveData<String>
    val successLiveData: LiveData<Unit>

    fun sendSmsVeryfy(data: SmsVeryfyRequest)
}