package com.example.appseguimientogastos.domain.item

import com.example.appseguimientogastos.data.item.AppRepository
import com.example.appseguimientogastos.domain.item.model.Item
import com.example.appseguimientogastos.domain.item.model.toItemEntity
import javax.inject.Inject

class DeleteItem @Inject constructor(private val appRepository: AppRepository) {

    suspend operator fun invoke(item: Item) {
        appRepository.deleteItem(itemEntity = item.toItemEntity())
    }
}