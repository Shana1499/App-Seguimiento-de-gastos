package com.example.appseguimientogastos.di

import android.content.Context
import com.example.appseguimientogastos.ui.view_model.AddViewModelItem
import com.example.appseguimientogastos.ui.view_model.ExpenseViewModelItem
import com.example.appseguimientogastos.ui.view_model.IncomeViewModelItem
import com.example.appseguimientogastos.ui.view_model.MainViewModelItem
import com.example.appseguimientogastos.ui.view_model.SavingsViewModelItem
import com.example.appseguimientogastos.ui.view_model.utils.ItemBaseViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

class DIApp {
    fun createAppModule(context: Context) = module {
        single { context }

        // Define ViewModel instances
        viewModel { ItemBaseViewModel(get()) }
        viewModel { MainViewModelItem(get()) }
        viewModel { IncomeViewModelItem(get()) }
        viewModel { ExpenseViewModelItem(get()) }
        viewModel { SavingsViewModelItem(get()) }
        viewModel { AddViewModelItem(get()) }

    }
}