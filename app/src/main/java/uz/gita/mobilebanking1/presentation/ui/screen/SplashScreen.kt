package uz.gita.mobilebanking1.presentation.ui.screen

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.mobilebanking1.R
import uz.gita.mobilebanking1.presentation.ui.viewmodel.impl.SplashViewModel

@AndroidEntryPoint
class SplashScreen : Fragment(R.layout.splash_screen) {

    private val viewModel by viewModels<SplashViewModel>()

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.liveData.observe(this, observer)

    }

    private var observer = Observer<Unit> {
        findNavController().navigate(R.id.action_splashScreen_to_authorizationScreen)
    }
}