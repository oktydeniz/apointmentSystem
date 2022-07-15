package com.example.appointmentsystem.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel(application: Application) : AndroidViewModel(application),
    CoroutineScope {

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main // Coroutine ne yapacagini yazmamiz gerekiyor. job kismi bittikten sonra
    //Main Thread geri don demek islem arka planda yapildiktan sonra main thread geri donecegiz
    //bu sinifta hicbir islem yapilmayacagi obje olusturmayacagimiz ve sadece implement edecegimiz icin
    // bu sinifi abstract class seklinde tanimlana bilir.

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

}