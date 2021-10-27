package uz.gita.mobilebanking1.data

import android.content.Context
import uz.gita.mobilebanking1.data.enum.StartScreenEnum
import uz.gita.mobilebanking1.presentation.utils.startScreen

class MySharedPreferences private constructor(context: Context) {
    companion object {
        private lateinit var instance: MySharedPreferences

        fun init(context: Context) {
            instance = MySharedPreferences(context)
        }

        fun getPref(): MySharedPreferences = instance
    }

    private val pref = context.getSharedPreferences("ContactApp", Context.MODE_PRIVATE)

    var token: String
        set(value) {
            pref.edit().putString("token", value).apply()
        }
        get() = pref.getString("token", "")!!


    var firtstName: String
        set(value) = pref.edit().putString("firtstName", value).apply()
        get() = pref.getString("firtstName", "")!!

    var lastName: String
        set(value) = pref.edit().putString("lastName", value).apply()
        get() = pref.getString("lastName", "")!!

    var phoneNumber: String
        set(value) = pref.edit().putString("phoneNumber", value).apply()
        get() = pref.getString("phoneNumber", "")!!

    var startScreen: StartScreenEnum
        set(value) = pref.edit().putString("startScreen", value.name).apply()
        get() = pref.getString("startScreen", StartScreenEnum.LOGIN.name)!!.startScreen()

    var smsCode: String
        set(value) = pref.edit().putString("smsCode", value).apply()
        get() = pref.getString("smsCode", "")!!
}