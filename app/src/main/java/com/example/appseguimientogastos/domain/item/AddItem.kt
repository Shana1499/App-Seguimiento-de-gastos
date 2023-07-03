package com.example.appseguimientogastos.domain.item

import com.example.appseguimientogastos.data.item.AppRepository
import com.example.appseguimientogastos.domain.item.model.Item
import com.example.appseguimientogastos.domain.item.model.toItem
import com.example.appseguimientogastos.domain.item.model.toItemEntity
import javax.inject.Inject


class AddItem @Inject constructor(private val appRepository: AppRepository) {

    suspend operator fun invoke(item: Item) {
         appRepository.insertItem(itemEntity = item.toItemEntity())
    }
}

