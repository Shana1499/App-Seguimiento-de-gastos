package com.example.appseguimientogastos.ui.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appseguimientogastos.ui.data.item.local.ItemVO
import com.example.appseguimientogastos.ui.data.item.model.ItemDao
import kotlinx.coroutines.launch

class MainViewModel(private val itemDao: ItemDao) :ViewModel(){

    val myItems = itemDao.getAll()

    fun addEntity(item: ItemVO) {
        viewModelScope.launch {
            itemDao.insert(item)
        }
    }




}
