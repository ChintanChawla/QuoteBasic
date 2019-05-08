package emulatordetector.glowing.com.quote_lecture

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_add_quote.*
import java.io.PrintStream

class AddQuote : AppCompatActivity() {
    private val QUOTE_FILE = "extraQuotes.txt"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_quote)

        val quoteAddButton = findViewById<Button>(R.id.add)
        quoteAddButton.setOnClickListener() {

            var quote_add = getQuote.text.toString() //getting the quote and the author
            var author_add = getAuthor.text.toString()
            var line = author_add + "  " + quote_add
            val outStream = PrintStream(openFileOutput(QUOTE_FILE, Context.MODE_PRIVATE))
            outStream.use {
                outStream.println(line)
            } // adding the quote in txt file
            val myIntent = Intent()
            myIntent.putExtra("quote", quote_add)
            myIntent.putExtra("author", author_add) //sends the data to previous activity

            setResult(1, myIntent)
            finish()

        }


    }
}
