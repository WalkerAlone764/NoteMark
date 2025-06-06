package com.example.core.presentation.util

import com.example.core.presentation.util.UiText.StringResource
import com.example.core.util.DataError
import com.example.notemark.R

fun DataError.asUiText(): UiText {
    return when (this) {
        DataError.Local.DISK_FULL -> StringResource(
            R.string.error_disk_full
        )

        DataError.Network.REQUEST_TIMEOUT -> StringResource(
            R.string.error_request_timeout
        )

        DataError.Network.TOO_MANY_REQUESTS -> StringResource(
            R.string.error_too_many_requests
        )

        DataError.Network.NO_INTERNET -> StringResource(
            R.string.error_no_internet
        )

        DataError.Network.PAYLOAD_TOO_LARGE -> StringResource(
            R.string.error_payload_too_large
        )

        DataError.Network.SERVER_ERROR -> StringResource(
            R.string.error_server_error
        )

        DataError.Network.SERIALIZATION -> StringResource(
            R.string.error_serialization
        )

        DataError.Network.UNAUTHORIZED -> StringResource(R.string.error_unauthorized)
//        DataError.Network.CONFLICT -> TODO()
//        DataError.Network.UNKNOWN -> TODO()
        else -> StringResource(R.string.error_unknown)

    }
}