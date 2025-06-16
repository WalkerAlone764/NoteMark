package com.example.auth.presentation.login

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Observer
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.auth.presentation.login.LoginEvent
import com.example.auth.presentation.login.component.LoginLandscape
import com.example.auth.presentation.login.component.LoginMobilePortrait
import com.example.auth.presentation.login.component.LoginTabletPortrait
import com.example.core.presentation.designsystem.theme.NoteMarkTheme
import com.example.core.presentation.util.DeviceType.LANDSCAPE
import com.example.core.presentation.util.DeviceType.MOBILE_PORTRAIT
import com.example.core.presentation.util.DeviceType.TABLET_PORTRAIT
import com.example.core.presentation.util.ObserveAsEvents
import com.example.core.presentation.util.getDeviceType
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreenRoot(
    onSuccessfullyLogin: () -> Unit,
    onClickDoNotHaveAccount: () -> Unit,
    viewModel: LoginScreenViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val context = LocalContext.current
    ObserveAsEvents(viewModel.event) { event ->
        when(event) {
            is LoginEvent.OnError -> {
                Toast.makeText(context,event.error.asString(context), Toast.LENGTH_SHORT).show()
            }
            LoginEvent.OnSuccess -> {
                Toast.makeText(context,"Success Login", Toast.LENGTH_SHORT).show()
                onSuccessfullyLogin()
            }

            LoginEvent.OnClickNotHaveAnAccount -> {
                onClickDoNotHaveAccount()
            }
        }
    }

    LoginScreenScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
fun LoginScreenScreen(
    state: LoginScreenState,
    onAction: (LoginScreenAction) -> Unit,
) {

    val isDeviceType = getDeviceType()
    when (isDeviceType) {
        MOBILE_PORTRAIT -> LoginMobilePortrait(
            state = state,
            onAction = onAction
        )
        TABLET_PORTRAIT -> LoginTabletPortrait(
            state = state,
            onAction = onAction
        )
        LANDSCAPE -> LoginLandscape(
            state = state,
            onAction = onAction
        )
    }

}

@Preview
@Composable
private fun LoginScreenPreview() {
    NoteMarkTheme {
        LoginScreenScreen(
            state = LoginScreenState(),
            onAction = {}
        )
    }
}