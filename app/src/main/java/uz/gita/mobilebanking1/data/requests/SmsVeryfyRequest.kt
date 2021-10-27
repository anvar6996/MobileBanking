package uz.gita.mobilebanking1.data.requests

data class SmsVeryfyRequest constructor(val phone: String, val code: String)