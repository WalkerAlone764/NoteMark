package com.example.setting.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.setting.domain.repository.SettingRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SettingViewModel(
    private val settingRepository: SettingRepository
) : ViewModel() {

    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(SettingState())
    val state = _state.onStart {
            if (!hasLoadedInitialData) {
                /** Load initial data here **/
                hasLoadedInitialData = true
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = SettingState()
        )

    fun onAction(action: SettingAction) {
        when (action) {
            SettingAction.OnClickBack -> {
                //No need to handle here
            }
            SettingAction.OnClickLogout -> logout()
        }
    }

    private fun logout() {
        viewModelScope.launch {
            settingRepository.logout()
        }
    }

}