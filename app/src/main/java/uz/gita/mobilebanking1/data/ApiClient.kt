package uz.gita.mobilebanking1.data

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.gita.mobilebanking1.BuildConfig.LOGGING
import uz.gita.mobilebanking1.app.App
import uz.gita.mobilebanking1.presentation.utils.timber

object ApiClient {

    val retrofit: Retrofit = Retrofit.Builder().baseUrl("http://4fd6-188-113-199-131.ngrok.io")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory()).client(getHttpClient()).build()

    private fun getHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().addLogging().build()
    }
}

private fun OkHttpClient.Builder.addLogging(): OkHttpClient.Builder {
    if (LOGGING) {
        val logging = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                timber(message, "HTTP")
            }
        })
        logging.level = HttpLoggingInterceptor.Level.BODY
        addNetworkInterceptor(logging)
        addNetworkInterceptor(ChuckInterceptor(App.instance))

    }
    return this
}