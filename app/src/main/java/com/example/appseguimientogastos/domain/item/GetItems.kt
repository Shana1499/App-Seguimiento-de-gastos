package com.example.appseguimientogastos.domain.item

import com.example.appseguimientogastos.data.item.AppRepository
import com.example.appseguimientogastos.domain.item.model.Item
import com.example.appseguimientogastos.domain.item.model.toItem
import javax.inject.Inject

class GetItems @Inject constructor(private val appRepository: AppRepository) {

    suspend operator fun invoke(): List<Item> {
        return appRepository.getAllData().map { it.toItem() }
    }
}