package com.example.auth.presentation.registration

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.auth.presentation.registration.component.RegistrationLandscape
import com.example.auth.presentation.registration.component.RegistrationMobilePortrait
import com.example.auth.presentation.registration.component.RegistrationTabletPortrait
import com.example.core.presentation.designsystem.theme.NoteMarkTheme
import com.example.core.presentation.util.DeviceType.LANDSCAPE
import com.example.core.presentation.util.DeviceType.MOBILE_PORTRAIT
import com.example.core.presentation.util.DeviceType.TABLET_PORTRAIT
import com.example.core.presentation.util.ObserveAsEvents
import com.example.core.presentation.util.getDeviceType
import org.koin.androidx.compose.koinViewModel

@Composable
fun RegistrationScreenRoot() {
    val viewModel = koinViewModel<RegistrationViewModel>()
    val state: RegistrationState = viewModel.state.collectAsStateWithLifecycle().value

    val context = LocalContext.current

    ObserveAsEvents(viewModel.event) { event ->
        when (event) {
            RegistrationEvent.OnSuccessfullyRegistration -> {
                Toast.makeText(context, "Registration Successful", Toast.LENGTH_SHORT).show()
            }

            is RegistrationEvent.OnError -> {
                Toast.makeText(context, event.uiText.asString(context), Toast.LENGTH_SHORT).show()
            }
        }
    }

    RegistrationScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
fun RegistrationScreen(
    state: RegistrationState,
    onAction: (RegistrationAction) -> Unit
) {
    val deviceType = getDeviceType()

    when (deviceType) {
        MOBILE_PORTRAIT -> RegistrationMobilePortrait(state = state, onAction = onAction)
        TABLET_PORTRAIT -> RegistrationTabletPortrait(state = state, onAction = onAction)
        LANDSCAPE -> RegistrationLandscape(state = state, onAction = onAction)
    }

}

@Preview
@Composable
private fun RegistrationScreenPreview() {
    NoteMarkTheme {
        RegistrationScreen(
            state = RegistrationState(),
            onAction = {}
        )
    }
}