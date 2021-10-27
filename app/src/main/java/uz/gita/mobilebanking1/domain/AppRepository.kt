package uz.gita.mobilebanking1.domain

import kotlinx.coroutines.flow.Flow
import uz.gita.mobilebanking1.data.requests.AuthorizationRequest
import uz.gita.mobilebanking1.data.requests.SmsVeryfyRequest
import uz.gita.mobilebanking1.data.requests.VeryfyRequest

interface AppRepository {
    fun userLogin(authRequest: AuthorizationRequest): Flow<Result<String>>
    fun regiter(request: VeryfyRequest): Flow<Result<String>>
    fun sendSmsVeryfy(request: SmsVeryfyRequest): Flow<Result<Unit>>
}