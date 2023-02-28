package com.example.mvvpkotlin.ui.quotes

import androidx.lifecycle.ViewModel
import com.example.mvvpkotlin.data.Quote
import com.example.mvvpkotlin.data.QuoteRepository

// QuoteRepository dependency will again be passed in the
// constructor using dependency injection
class QuotesViewModel(private val quoteRepository: QuoteRepository)
    : ViewModel() {

    fun getQuotes() = quoteRepository.getQuotes()

    fun addQuote(quote: Quote) = quoteRepository.addQuote(quote)
}