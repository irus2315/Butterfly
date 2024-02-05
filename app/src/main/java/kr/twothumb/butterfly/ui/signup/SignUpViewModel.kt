package kr.twothumb.butterfly.ui.signup

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kr.twothumb.butterfly.ui.splash.SplashUiState
import javax.inject.Inject

data class SignUpUiState(
    val email: String = "",
    val name: String = "",
    val company: String = "",
    val phoneNumber: String = "",
    val termsAgree: Boolean = false,
    val marketingAgree: Boolean = false,
    val navigateSignUpCompleteScreen: Boolean = false,
    val navigateBackScreen: Boolean = false,
    )

@HiltViewModel
class SignUpViewModel @Inject constructor(
) : ViewModel() {

    private val _uiState = MutableStateFlow(SignUpUiState())
    val uiState: StateFlow<SignUpUiState> = _uiState.asStateFlow()

    init {

    }

    enum class OnSelected { SignIn, SignInGuest }
}
