package com.example.talktome.di

import com.example.talktome.common.pref.Preference
import com.example.talktome.data.authorizedUserData.AuthUserRepository
import com.example.talktome.data.authorization.repository.AuthorizationRepository
import com.example.talktome.data.blog.repository.BlogRepository
import com.example.talktome.data.doctors.repository.DoctorsRepository
import com.example.talktome.domain.authorization.MakeLoginUseCase
import com.example.talktome.domain.authorization.MakeRegisterUseCase
import com.example.talktome.domain.authorizedUser.*
import com.example.talktome.domain.blog.*
import com.example.talktome.domain.doctorsUseCase.GetAllDoctorsUseCase
import com.example.talktome.domain.doctorsUseCase.GetDoctorsByTagUseCase
import com.example.talktome.ui.authorized.MainActivityViewModel
import com.example.talktome.ui.authorized.blog.addBlog.AddBlogViewModel
import com.example.talktome.ui.authorized.blog.blogDetail.BlogDetailViewModel
import com.example.talktome.ui.authorized.blog.main.BlogViewModel
import com.example.talktome.ui.authorized.chat.ChatViewModel
import com.example.talktome.ui.authorized.doctors.detail.DoctorsDetailViewModel
import com.example.talktome.ui.authorized.doctors.main.DoctorsViewModel
import com.example.talktome.ui.authorized.main.MainViewModel
import com.example.talktome.ui.authorized.profile.ProfileViewModel
import com.example.talktome.ui.authorized.session.SessionViewModel
import com.example.talktome.ui.unauthorized.ui.survey.SurveyViewModel
import com.example.talktome.ui.launcher.main.LauncherViewModel
import com.example.talktome.ui.unauthorized.ui.chooseRole.ChooseRoleViewModel
import com.example.talktome.ui.unauthorized.ui.login.LoginViewModel
import com.example.talktome.ui.unauthorized.ui.registry.RegisterViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single {
        Preference(androidContext())
    }
}

val useCaseModule = module {
    factory {
        MakeLoginUseCase(get())
    }
    factory {
        MakeRegisterUseCase(get())
    }
    factory {
        GetTokenUseCase(get())
    }
    factory {
        SetTokenUseCase(get())
    }
    factory {
        GetUserRoleUseCase(get())
    }
    factory {
        SetUserRoleUseCase(get())
    }
    factory {
        GetUserIdUseCase(get())
    }
    factory {
        SetUserIdUseCase(get())
    }
    factory {
        GetAllDoctorsUseCase(get())
    }
    factory {
        GetDoctorsByTagUseCase(get())
    }
    factory {
        MakeClearUserDataUseCase(get())
    }
    factory {
        MakeCreateNewBlogUseCase(get())
    }
    factory {
        GetAllBlogsUseCase(get())
    }
    factory {
        GetDoctorBlogsUseCase(get())
    }
    factory {
        GetBlogsByIdUseCase(get())
    }
    factory {
        MakeUpdateBlogUseCase(get())
    }
    factory {
        MakeDeleteBlogUseCase(get())
    }
}

val repositoryModule = module {
    factory {
        AuthorizationRepository(get())
    }
    factory {
        BlogRepository(get())
    }
    factory {
        AuthUserRepository(get())
    }
    factory {
        DoctorsRepository(get())
    }
}

val viewModelModule = module {
    viewModel {
        LauncherViewModel(get())
    }
    viewModel {
        LoginViewModel(get(), get(), get(), get())
    }
    viewModel {
        RegisterViewModel()
    }
    viewModel {
        ChooseRoleViewModel()
    }
    viewModel {
        SurveyViewModel(get(), get(), get(), get())
    }
    viewModel {
        MainActivityViewModel()
    }
    viewModel {
        MainViewModel()
    }
    viewModel {
        DoctorsViewModel(get(), get())
    }
    viewModel {
        BlogViewModel(get(), get())
    }
    viewModel {
        ChatViewModel()
    }
    viewModel {
        ProfileViewModel(get())
    }
    viewModel {
        AddBlogViewModel(get(), get(), get())
    }
    viewModel {
        BlogDetailViewModel(get(), get())
    }
    viewModel {
        DoctorsDetailViewModel()
    }
    viewModel{
        SessionViewModel()
    }
}