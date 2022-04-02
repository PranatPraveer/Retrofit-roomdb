package com.example.retrofit

import android.app.Application
import com.example.retrofit.db.QuoteDatabase

class QuoteApplication:Application() {
    lateinit var quoteRepository: QuoteRepository

    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {
        val quoteService=RetrofitHelper.getInstance().create(QuotesAPI::class.java)
        var database= QuoteDatabase.getDatabase(applicationContext)
        quoteRepository= QuoteRepository(quoteService,database,applicationContext)
    }
}