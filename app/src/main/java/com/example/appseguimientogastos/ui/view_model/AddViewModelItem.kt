package com.example.appseguimientogastos.ui.view_model

import com.example.appseguimientogastos.domain.ItemsRepository
import com.example.appseguimientogastos.domain.model.Item
import com.example.appseguimientogastos.domain.model.Type
import com.example.appseguimientogastos.ui.view_model.utils.ItemBaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AddViewModelItem(itemsRepository: ItemsRepository) :
    ItemBaseViewModel(itemsRepository = itemsRepository) {

    // UI state
    private val _uiState = MutableStateFlow(MainState())//detecta cuando hay un cambio para repintar
    val uiState: StateFlow<MainState> = _uiState.asStateFlow()

    // pasar por parametro la screen
    fun onAddItem(origin: String, price: String, month: String, type: Type, onAddItemCompleted:()->Unit) {
        val newItem = Item(origin = origin, price = price.toDoubleOrNull()?:0.0, month = month,type=type.typeName)
        coroutinesUtils.runBG {
            itemsRepository.addItem(newItem)
            coroutinesUtils.runMain{
                onAddItemCompleted()
            }
        }


    }





}

