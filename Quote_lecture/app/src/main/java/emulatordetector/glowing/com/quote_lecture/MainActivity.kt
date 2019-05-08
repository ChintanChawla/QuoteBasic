package emulatordetector.glowing.com.quote_lecture


import android.content.Intent
import android.net.sip.SipErrorCode
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.LogPrinter
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.util.*
import java.util.logging.Logger

class MainActivity : AppCompatActivity() {
    private var QuoteNumber = 6
    private val Intent_Code = 123
    private val quotes = mutableListOf<String>()
    private val authors = mutableListOf<String>()
    private var quotesCollection = hashMapOf<String, String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fun readFile() {
            var file = Scanner(resources.openRawResource(R.raw.quotess))
            while (file.hasNextLine()) {
                val line = file.nextLine()
                val peice = line.split("          ")
                if (peice.size >= 2) {

                    quotesCollection.put(peice[1], peice[0])

                    quotes.add(peice[1])
                    authors.add(peice[0])

                }


            }


        }

        fun getQuote(): String {
            val randomNumber = (0..QuoteNumber).shuffled().first() //Gets the 5 quotes from the collection
            Log.d("rNumber", randomNumber.toString())
            Log.d("qNumber", QuoteNumber.toString())
            return quotes[randomNumber]

        }
        readFile()

        val quoteView = findViewById<TextView>(R.id.quote)
        val authorView = findViewById<TextView>(R.id.author)
        val qButton = findViewById<Button>(R.id.nextQuote)
        qButton.setOnClickListener() {
            var quoteDisplay = getQuote()
            var authorDisplay = quotesCollection.get(quoteDisplay)
            quoteView.text = quoteDisplay
            authorView.text = authorDisplay


        }
        val addQuote = findViewById<Button>(R.id.AddQuote)
        addQuote.setOnClickListener()
        {
            val myIntent = Intent(this, AddQuote::class.java) // Opens the next activity to add quotes
            startActivityForResult(myIntent, Intent_Code)

        }

    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,          //adds the recived quote from the activity
        myIntent: Intent?
    ) {

        if (resultCode == 1) {
            if (myIntent != null) {

                val quote_add = myIntent.getStringExtra("quote")
                val author_add = myIntent.getStringExtra("author")
                quotesCollection.put(quote_add, author_add)
                quotes.add(quote_add)
                authors.add(author_add)
                QuoteNumber += 1
                Log.d("quoteAdd", quotes[QuoteNumber])

            }
        }


    }
}
