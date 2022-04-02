package com.example.retrofit

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainViewModel(val repository: QuoteRepository): ViewModel() {

    init {
        GlobalScope.launch(Dispatchers.IO) {
            repository.getQuotes(1)
        }
    }
    val quotes: LiveData<QuoteList>
    get()= repository.quotes
}