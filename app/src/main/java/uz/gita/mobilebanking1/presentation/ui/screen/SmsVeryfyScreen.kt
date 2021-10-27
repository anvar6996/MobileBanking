package uz.gita.mobilebanking1.presentation.ui.screen

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.mobilebanking1.R
import uz.gita.mobilebanking1.data.MySharedPreferences
import uz.gita.mobilebanking1.data.requests.SmsVeryfyRequest
import uz.gita.mobilebanking1.databinding.SmsverifyScreenBinding
import uz.gita.mobilebanking1.presentation.ui.viewmodel.impl.SmsVeryfyViewModelImpl

@AndroidEntryPoint
class SmsVeryfyScreen : Fragment(R.layout.smsverify_screen) {
    private val bind by viewBinding(SmsverifyScreenBinding::bind)
    private val viewModel by viewModels<SmsVeryfyViewModelImpl>()
    private val pref = MySharedPreferences.getPref()
    private var correctCode = false
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind.sendCodeSms.setOnClickListener {
            viewModel.sendSmsVeryfy(
                SmsVeryfyRequest(
                    pref.phoneNumber,
                    bind.codeText.text.toString()
                )
            )
        }

        bind.codeText.addTextChangedListener {
            correctCode = it.toString().isNotEmpty() && it.toString().length == 6
            check()
        }
        viewModel.disableLoginLiveData.observe(viewLifecycleOwner, disableRegisterObserver)
        viewModel.enableLoginLiveData.observe(viewLifecycleOwner, enableRegisterObserver)
        viewModel.errorLivaData.observe(viewLifecycleOwner, errorObserver)
        viewModel.successLiveData.observe(viewLifecycleOwner, successObserver)
    }

    private val disableRegisterObserver = Observer<Unit> {
        bind.sendCodeSms.isEnabled = false
    }
    private val enableRegisterObserver = Observer<Unit> {
        bind.sendCodeSms.isEnabled = true
    }
    private val errorObserver = Observer<String> {
        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
    }

    private val successObserver = Observer<Unit> {
//        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        findNavController().navigate(R.id.action_smsVeryfyScreen_to_mainScreen)
    }

    private fun check() {
        bind.sendCodeSms.isEnabled = correctCode
    }
}