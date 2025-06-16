package com.example.auth.presentation.registration

import com.example.core.presentation.util.UiText

interface RegistrationEvent {
    data object OnSuccessfullyRegistration : RegistrationEvent
    data class OnError(val uiText: UiText) : RegistrationEvent
    data object OnClickAlreadyHaveAnAccount : RegistrationEvent

}