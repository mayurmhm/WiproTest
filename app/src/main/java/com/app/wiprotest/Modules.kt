package com.app.wiprotest

import com.app.wiprotest.ui.mainmodule.MainViewModel
import com.app.wiprotest.ui.splashmodule.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * Initialized KOIN
 */
fun injectFeature() = loadFeature

/**
 * Top-level lazy property used to load koin modules.
 * Code inside the lazy block is executed only once, no matter how many times the property is accessed.
 * Can be used later through activity & fragment in dynamic feature approach for the feature & its respective modules.
 * */
private val loadFeature by lazy {
    loadKoinModules(
        listOf(
            viewModelModule

        )
    )
}

/**
 * Contains View Model of the Project
 */
private val viewModelModule: Module = module {
    viewModel { MainViewModel(application = get()) }
    viewModel { SplashViewModel(application = get()) }
}