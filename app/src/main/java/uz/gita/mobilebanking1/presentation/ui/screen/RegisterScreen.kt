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
import uz.gita.mobilebanking1.data.requests.VeryfyRequest
import uz.gita.mobilebanking1.databinding.RegisterScreenBinding
import uz.gita.mobilebanking1.presentation.ui.viewmodel.RegisterViewModel
import uz.gita.mobilebanking1.presentation.ui.viewmodel.impl.RegisterViewModelImpl

@AndroidEntryPoint
class RegisterScreen : Fragment(R.layout.register_screen) {
    private val bind by viewBinding(RegisterScreenBinding::bind)
    private val viewModel: RegisterViewModel by viewModels<RegisterViewModelImpl>()
    private var boolFirstName = false
    private var boolLastName = false
    private var boolPassword = false
    private var boolPhoneNumber = false
    private var boolConfirmPassword = false
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind.sendRegister.setOnClickListener {
            viewModel.regiter(
                VeryfyRequest(
                    bind.firstName.text.toString(),
                    bind.lastName.text.toString(),
                    bind.passwordRegister.text.toString(),
                    bind.phoneNumberRegister.text.toString(), "0"
                )
            )
        }

        bind.firstName.addTextChangedListener {
            boolFirstName = it.toString().isNotEmpty()
            check()
        }
        bind.lastName.addTextChangedListener {
            boolLastName = it.toString().isNotEmpty()
            check()
        }
        bind.passwordRegister.addTextChangedListener {
            if (it != null) {
                boolPassword = it.toString().isNotEmpty() && it.length > 3
            }
            check()
        }
        bind.confrimPasswordRegister.addTextChangedListener {
            if (it != null) {
                boolConfirmPassword =
                    it.toString().isNotEmpty() && it.toString().equals(bind.passwordRegister)
            }
            check()
        }
        bind.phoneNumberRegister.addTextChangedListener {
            if (it != null) {
                boolPhoneNumber = it.length == 13 && it.toString().startsWith("+998")
                check()
            }
        }
        viewModel.disableLoginLiveData.observe(viewLifecycleOwner, disableRegisterObserver)
        viewModel.enableLoginLiveData.observe(viewLifecycleOwner, enableRegisterObserver)
        viewModel.errorLivaData.observe(viewLifecycleOwner, errorObserver)
        viewModel.successLiveData.observe(viewLifecycleOwner, successObserver)
//        viewModel.progressLiveData.observe(viewLifecycleOwner, progressObserver)
    }

    private fun check() {
        bind.sendRegister.isEnabled =
            boolFirstName && boolLastName && (boolPassword || boolConfirmPassword) && boolPhoneNumber
    }

    private val disableRegisterObserver = Observer<Unit> {
        bind.sendRegister.isEnabled = false
    }
    private val enableRegisterObserver = Observer<Unit> {
        bind.sendRegister.isEnabled = true
    }
    private val errorObserver = Observer<String> {
        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
    }

    private val successObserver = Observer<String> {
        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        findNavController().navigate(R.id.action_registerScreen_to_smsVeryfyScreen)
    }
}