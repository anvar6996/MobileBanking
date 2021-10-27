package uz.gita.mobilebanking1.presentation.ui.screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import uz.gita.mobilebanking1.R
import uz.gita.mobilebanking1.databinding.AuthorizationScreenBinding
import uz.gita.mobilebanking1.presentation.ui.viewmodel.AuthorizationViewModel
import uz.gita.mobilebanking1.presentation.ui.viewmodel.impl.AuthorizationViewModelIml
@AndroidEntryPoint
class AuthorizationScreen : Fragment(R.layout.authorization_screen) {
    private val bind by viewBinding(AuthorizationScreenBinding::bind)
    private val viewModel: AuthorizationViewModel by viewModels<AuthorizationViewModelIml>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind.login.setOnClickListener {
            viewModel.nextLogin()
            viewModel.openLoginScreenLiveData.observe(viewLifecycleOwner, openLoginScreen)
        }
        bind.register.setOnClickListener {
            viewModel.nextRegister()
            viewModel.openLoginScreenLiveData.observe(viewLifecycleOwner, openRegisterScreen)
        }
    }

    private val openRegisterScreen = Observer<Unit> {
        findNavController().navigate(R.id.action_authorizationScreen_to_registerScreen)
    }
    private val openLoginScreen = Observer<Unit> {
        findNavController().navigate(R.id.action_authorizationScreen_to_loginScreen)
    }
}