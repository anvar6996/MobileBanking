package uz.gita.mobilebanking1.domain

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.ResponseBody
import uz.gita.mobilebanking1.data.ApiClient
import uz.gita.mobilebanking1.data.MySharedPreferences
import uz.gita.mobilebanking1.data.api.Authorization
import uz.gita.mobilebanking1.data.enum.StartScreenEnum
import uz.gita.mobilebanking1.data.requests.AuthorizationRequest
import uz.gita.mobilebanking1.data.requests.SmsVeryfyRequest
import uz.gita.mobilebanking1.data.requests.VeryfyRequest
import uz.gita.mobilebanking1.data.responce.Erroresponse
import uz.gita.mobilebanking1.data.responce.RegisterResponse
import uz.gita.mobilebanking1.presentation.utils.timber
import javax.inject.Inject


class AppRepositoryImpl @Inject constructor() : AppRepository {
    private val api = ApiClient.retrofit.create(Authorization::class.java)
    private val pref = MySharedPreferences.getPref()
    private val gson = Gson()

    override fun userLogin(authRequest: AuthorizationRequest): Flow<Result<String>> = flow {
        try {
            val response = api.login(authRequest)
            timber(response.code().toString())
            if (response.code() in 200..299) {
                timber("body = ${response.body()}")
                response.body()?.let {
                    pref.phoneNumber = authRequest.phoneNumber
                    pref.startScreen = StartScreenEnum.SMS_VERYFY
                    emit(Result.success(response.body()!!.message))
                }

            } else {
                var st = "Serverga ulanishda xatolik bo'ldi"
                ResponseBody
                response.errorBody()?.let {
                    st = gson.fromJson(it.string(), Erroresponse::class.java).message
                }
                emit(Result.failure<String>(Throwable(st)))
            }
        } catch (e: Exception) {

            emit(Result.failure<String>(Throwable("Serverga ulanishda xatolik boldi")))
            timber(e.message.toString())
        }
    }.flowOn(Dispatchers.IO)


    override fun regiter(request: VeryfyRequest): Flow<Result<String>> = flow {
        try {
            val response = api.register(request)
            timber("code = ${response.code()}")
            if (response.code() in 200..299) {
                timber("body = ${response.body()}")
                response.body()?.let {
                    pref.phoneNumber = it.message
                    pref.startScreen = StartScreenEnum.SMS_VERYFY
                }
                emit(Result.success(response.body()!!.message))
            } else {
                var st = "Serverga ulanishda xatolik bo'ldi else"
                timber("error = ${response.errorBody()}")
                response.errorBody()?.let {
                    st = gson.fromJson(it.string(), RegisterResponse::class.java).message
                }
                emit(Result.failure<String>(Throwable(st)))
            }
        } catch (exp: Exception) {

            emit(Result.failure<String>(Throwable(exp.message)))
            timber(exp.message.toString())
        }
    }.flowOn(Dispatchers.IO)

    override fun sendSmsVeryfy(request: SmsVeryfyRequest): Flow<Result<Unit>> = flow {
        try {
            val responce = api.veryfyCode(request)
            timber("sms responce code->${request.phoneNumber}")
            timber("sms responce code->${request.password}")
            if (responce.code() in 200..299) {
                timber("sms responce body = ${responce.body()}")
                responce.body()?.let {
                    pref.token = it.token.substring(7)
                    pref.startScreen = StartScreenEnum.MAIN
                }
                emit(Result.success(Unit))
            } else {
                var st = "Serverga ulanishda xatolik bo'ldi else"
                timber("error = ${responce.errorBody()}")
                responce.errorBody()?.let {
                    st = gson.fromJson(it.toString(), Erroresponse::class.java).message
                }
                emit(Result.failure<Unit>(Throwable(st)))
            }
        } catch (exc: java.lang.Exception) {
            emit(Result.failure(Throwable(exc.message)))
            timber(exc.message.toString())
        }
    }.flowOn(Dispatchers.IO)
}