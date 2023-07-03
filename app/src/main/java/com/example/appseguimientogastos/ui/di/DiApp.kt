package com.example.appseguimientogastos.ui.di

import android.content.Context
import org.koin.dsl.module

class DIApp {
    fun createAppModule(context: Context) = module {
        single { context }
       /* viewModel { (initialParams: LoginInitialParams) ->
            LoginViewModel(get(), get(), get(), get(), initialParams)
        }
        viewModel { (initialParams: PaymentOptionInitialParams) ->
            PaymentOptionViewModel(get(), get(), get(), get(), initialParams)
        }
        viewModel { (initialParams: PaymentConfigurationInitialParams) ->
            PaymentConfigurationViewModel(get(), get(), get(), get(), initialParams)
        }
        viewModel { (initialParams: PaymentResultInitialParams) ->
            PaymentResultViewModel(get(), get(), get(), get(), initialParams)
        }*/
    }
}