package com.example.appseguimientogastos.ui.view_model.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class CoroutinesUtils {

    private val mainScope = CoroutineScope(Dispatchers.Main)
    private val bgScope = CoroutineScope(Dispatchers.IO)


    fun runMain(work: suspend (()->Unit)){
        mainScope.launch {
            work()
        }

    }

    fun runBG(work: suspend (()->Unit)){
        bgScope.launch {
            work()
        }

    }
}