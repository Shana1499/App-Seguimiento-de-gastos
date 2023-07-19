package com.example.appseguimientogastos.ui.view_model.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


class CoroutinesUtils() {

    val main: CoroutineDispatcher = Dispatchers.Main
     val bg: CoroutineDispatcher = Dispatchers.IO

    fun exec(coroutineContext: CoroutineContext, work: suspend (() -> Unit)): Job =
        CoroutineScope(coroutineContext).launch { work() }

    fun runMain(work: suspend (() -> Unit)): Job = exec(main, work)
    fun runBG(work: suspend (() -> Unit)): Job = exec(bg, work)

}