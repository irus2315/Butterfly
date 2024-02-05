package kr.twothumb.butterfly.ui.main.home

import android.app.Application
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kr.twothumb.butterfly.common.Async
import kr.twothumb.butterfly.common.BaseViewModel
import kr.twothumb.butterfly.models.Project
import kr.twothumb.butterfly.models.ResponseData
import kr.twothumb.butterfly.models.Task
import kr.twothumb.butterfly.models.local.LocalProjectDataProvider
import kr.twothumb.butterfly.navigation.DestinationsArgs
import kr.twothumb.butterfly.ui.signup.SignUpUiState
import kr.twothumb.lib.logger.DevLog
import javax.inject.Inject

data class HomeUiState(
    val initialize: Boolean = false,
    val filterType: FilterType = FilterType.COLUMN,
    val projectList: List<Project> = LocalProjectDataProvider.projectList,
    val taskList: List<Task> = LocalProjectDataProvider.taskList,
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    application: Application
) : BaseViewModel(application) {

    private var auth: FirebaseAuth = Firebase.auth
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()
    private val _createProject: MutableStateFlow<Async<ResponseData<Any>>> = MutableStateFlow(Async.Initial)

    init {
        _uiState.update {
            it.copy(initialize = true)
        }
    }

    fun onProjectFilterSelected() {
        _uiState.update {
            it.copy(
                filterType = if (FilterType.values().size == it.filterType.ordinal + 1) {
                    FilterType.values()[it.filterType.ordinal + 1]
                } else {
                    FilterType.values()[0]
                }
            )

//
//            if(it.filterType == FilterType.COLUMN) {
//                it.copy(filterType = FilterType.ROW)
//            }
//            else {
//                it.copy(filterType = FilterType.COLUMN)
//            }
        }
        DevLog.d(uiState.value.filterType)
    }

    fun onTestClick() {
        /**
         * test
         */
        _uiState.update {
            it.copy(initialize = true)
        }
//        val user = auth.currentUser
//        user?.let {
//            it.getIdToken(true).addOnCompleteListener { task ->
//                if (task.isSuccessful) {
//                    task.result.token?.let { tokenString ->
//                        token = tokenString
//                        viewModelScope.launch {
//                            getNetworkModule().onCallApi(url = "initializeProject", body = Project(), flow = _createProject)
//                        }
//                    } ?: run {
//                        onHandleError(task.exception?.localizedMessage)
//                    }
//                } else {
//                    onHandleError(task.exception?.localizedMessage)
//                }
//            }
//        }

    }

    fun onProjectCreate() {

//        contentType(ContentType.Application.Json)
//        setBody(Customer(3, "Jet", "Brains"))
    }


    enum class OnSelected { Test1, Test2 }
}

enum class FilterType {
    COLUMN, ROW, GRID
}