package com.example.talktome.di

import com.example.talktome.ui.authorized.survey.SurveyViewModel
import com.example.talktome.ui.launcher.main.LauncherViewModel
import com.example.talktome.ui.unauthorized.ui.chooseRole.ChooseRoleViewModel
import com.example.talktome.ui.unauthorized.ui.login.LoginViewModel
import com.example.talktome.ui.unauthorized.ui.registry.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModel = module {
    viewModel {
        LauncherViewModel()
    }
    viewModel {
        LoginViewModel()
    }
    viewModel {
        RegisterViewModel()
    }
    viewModel {
        ChooseRoleViewModel()
    }
    viewModel {
        SurveyViewModel()
    }
}