package com.example.retrofit

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.retrofit.db.QuoteDatabase
import com.example.retrofit.utils.NetworkUtils

class QuoteRepository(private val QuotesAPI: QuotesAPI, private val quoteDatabase: QuoteDatabase, private val applicationContext: Context) {

    private val quotesLiveData= MutableLiveData<QuoteList>()
    val quotes:LiveData<QuoteList>
        get() =quotesLiveData

    suspend fun getQuotes(page:Int) {
        if (NetworkUtils.isInternetAvailable(applicationContext)) {
            val result = QuotesAPI.getQuotes(page)
            if (result.body() != null) {
                quoteDatabase.quoteDao().addQuotes(result.body()!!.results)
                quotesLiveData.postValue(result.body())
            }
        }
        else{
            val quotes=quoteDatabase.quoteDao().getQuotes()
            val quoteList=QuoteList(1,1,1,quotes,1,1)
            quotesLiveData.postValue(quoteList)
        }
    }
}
