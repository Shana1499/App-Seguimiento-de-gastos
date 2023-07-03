package com.example.appseguimientogastos.domain

import com.example.appseguimientogastos.data.item.AppRepository
import com.example.appseguimientogastos.domain.item.model.Item
import com.example.appseguimientogastos.domain.item.model.toItemEntity
import javax.inject.Inject

class UpdateItem @Inject constructor(private val appRepository: AppRepository) {

    suspend operator fun invoke(item: Item) {
        appRepository.updateItem(itemEntity = item.toItemEntity())
    }
}