package com.example.auth.di

import com.example.auth.data.EmailPatternValidator
import com.example.auth.data.repository.AuthRepositoryImpl
import com.example.auth.domain.PatternValidator
import com.example.auth.domain.UserDataValidator
import com.example.auth.domain.repository.AuthRepository
import com.example.auth.presentation.registration.RegistrationViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val authModule = module {

    single<AuthRepository> {
        AuthRepositoryImpl(get())
    }

    single<PatternValidator> {
        EmailPatternValidator()
    }

    singleOf(::UserDataValidator)

    viewModelOf(::RegistrationViewModel)
}