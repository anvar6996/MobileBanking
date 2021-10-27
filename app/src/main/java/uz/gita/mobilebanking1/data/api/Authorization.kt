package uz.gita.mobilebanking1.data.api

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import uz.gita.mobilebanking1.data.requests.AuthorizationRequest
import uz.gita.mobilebanking1.data.requests.SmsVeryfyRequest
import uz.gita.mobilebanking1.data.requests.VeryfyRequest
import uz.gita.mobilebanking1.data.responce.LoginResponce
import uz.gita.mobilebanking1.data.responce.LogoutResponse
import uz.gita.mobilebanking1.data.responce.RegisterResponse
import uz.gita.mobilebanking1.data.responce.VeriyfyResponce


interface Authorization {

    @POST("/api/v1/auth/login")
    suspend fun login(@Body data: AuthorizationRequest): Response<LoginResponce>

    @POST("/api/v1/auth/logout")
    fun logout(@Body data: AuthorizationRequest): Call<LogoutResponse>

    @POST("/api/v1/auth/reset")
    fun reset(@Body data: AuthorizationRequest): Call<RegisterResponse>

    @POST("/api/v1/auth/register")
    suspend fun register(@Body data: VeryfyRequest): Response<RegisterResponse>

    @POST("/api/v1/auth/verify")
    suspend fun veryfyCode(@Body data: SmsVeryfyRequest): Response<VeriyfyResponce>
}