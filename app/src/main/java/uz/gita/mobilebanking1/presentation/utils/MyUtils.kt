package uz.gita.mobilebanking1.presentation.utils

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import timber.log.Timber
import uz.gita.mobilebanking1.data.enum.StartScreenEnum


fun <T : ViewBinding> T.scope(block: T.() -> Unit) {
    block(this)
}


fun timber(message: String, tag: String = "TTT") {
    Timber.tag(tag).d(message)
}
fun Fragment.showToast(message: String) {
    Toast.makeText(this.requireContext(), message, Toast.LENGTH_SHORT).show()
}

fun String.startScreen(): StartScreenEnum {
    return if (this == StartScreenEnum.LOGIN.name) StartScreenEnum.LOGIN
    else StartScreenEnum.MAIN
}