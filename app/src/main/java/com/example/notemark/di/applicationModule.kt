package com.example.notemark.di

import com.example.notemark.MainViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val applicationModule = module {
    viewModelOf(::MainViewModel)
}