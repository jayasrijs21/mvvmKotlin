package com.example.mvvpkotlin.ui.quotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.mvvpkotlin.R
import com.example.mvvpkotlin.data.Quote
import com.example.mvvpkotlin.utilities.InjectorUtils

class MainActivity : AppCompatActivity() {

    var display: TextView?=null
    var click: Button?= null
    var fname: EditText?=null
    var sname: EditText?= null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        display = findViewById<TextView>(R.id.textView_quotes)
        click = findViewById<Button>(R.id.button_add_quote)
        fname = findViewById<EditText>(R.id.editText_quote)
        sname = findViewById<EditText>(R.id.editText_author)


        initializeUi()

    }
    private fun initializeUi() {
        // Get the QuotesViewModelFactory with all of it's dependencies constructed
        val factory = InjectorUtils.provideQuotesViewModelFactory()
        // Use ViewModelProviders class to create / get already created QuotesViewModel
        // for this view (activity)
        val viewModel = ViewModelProviders.of(this, factory)
            .get(QuotesViewModel::class.java)

        // Observing LiveData from the QuotesViewModel which in turn observes
        // LiveData from the repository, which observes LiveData from the DAO ☺
        viewModel.getQuotes().observe(this, Observer { quotes ->
            val stringBuilder = StringBuilder()
            quotes.forEach { quote ->
                stringBuilder.append("$quote\n\n")
            }
            display?.text = stringBuilder.toString()
        })

        // When button is clicked, instantiate a Quote and add it to DB through the ViewModel
        click?.setOnClickListener {
            val quote = Quote(fname?.text.toString(), sname?.text.toString())
            viewModel.addQuote(quote)
            fname?.setText("")
            sname?.setText("")
        }
    }
}