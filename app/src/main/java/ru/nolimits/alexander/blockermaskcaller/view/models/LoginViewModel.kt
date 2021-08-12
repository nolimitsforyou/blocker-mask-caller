package ru.nolimits.alexander.blockermaskcaller.view.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import ru.nolimits.alexander.blockermaskcaller.data.remote.FirebaseUserLiveData

class LoginViewModel : ViewModel() {

    enum class AuthenticationState {
        AUTHENTICATED, UNAUTHENTICATED, INVALID_AUTHENTICATION
    }

    val authenticationState = FirebaseUserLiveData().map { user ->
        if (user != null) {
            AuthenticationState.AUTHENTICATED
        } else {
            AuthenticationState.UNAUTHENTICATED
        }
    }
}