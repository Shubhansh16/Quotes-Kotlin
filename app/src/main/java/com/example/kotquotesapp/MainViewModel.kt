package com.example.kotquotesapp

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson

class MainViewModel(val context: Context):ViewModel() {
    private var quoteList:Array<Quote> = emptyArray()
    private var index=0

    init {
        quoteList=loadQuoteFromAssets()
    }

    private fun loadQuoteFromAssets(): Array<Quote> {
        val inputStream=context.assets.open("quotes.json") //assets file se data read krega
        val size:Int= inputStream.available()  //size dega
        val buffer=ByteArray(size)   //size ko store krega
        inputStream.read(buffer)
        inputStream.close()
        val json= String(buffer, Charsets.UTF_8)  //bytes array ko string format mei return krega json
        val gson=Gson()
        return gson.fromJson(json, Array<Quote>::class.java)
    }


    fun getQuote()= quoteList[index]

    fun nextQuote()= quoteList[++index]

    fun previousQuote()= quoteList[--index]
}