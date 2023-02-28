package com.example.mvvpkotlin.ui.quotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvpkotlin.data.QuoteRepository


// The same repository that's needed for QuotesViewModel
// is also passed to the factory

class QuotesViewModelFactory(private val quoteRepository: QuoteRepository)
    : ViewModelProvider.NewInstanceFactory() {


    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return QuotesViewModel(quoteRepository) as T
    }

}