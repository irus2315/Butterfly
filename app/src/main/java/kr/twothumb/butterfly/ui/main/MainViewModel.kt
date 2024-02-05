package kr.twothumb.butterfly.ui.main

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kr.twothumb.butterfly.navigation.DestinationsArgs
import kr.twothumb.butterfly.ui.signup.SignUpUiState
import kr.twothumb.lib.logger.DevLog
import javax.inject.Inject

/**
 * ViewModel for the task list screen.
 */
data class MainUiState(
    val currentMainScreen: String = "",
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
class MainViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    ) : ViewModel() {

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    private val menu: String = savedStateHandle[DestinationsArgs.MAIN_MENU]?:DestinationsArgs.MENU_PROFILE

    init {
        DevLog.e("menu  : " , menu , " :  " , savedStateHandle[DestinationsArgs.MAIN_MENU])
    }

    fun check() {
        DevLog.e("menu  : " , menu , " :  " , savedStateHandle[DestinationsArgs.MAIN_MENU])
    }
    fun updateCurrentMainScreen() {
        _uiState.update {
            DevLog.e("update : " , menu)
            it.copy(currentMainScreen = menu)
        }
    }
}
