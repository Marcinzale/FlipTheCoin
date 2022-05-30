package marcin.example.cointoss

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {

    private val imgCoin: ImageView by lazy { findViewById(R.id.imgCoin) }
    var totalCounter = 0
    var headsCounter = 0
    var tailsCounter = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

            coinTap()
    }

    private fun coinTap() {
        val tvTotal: TextView = findViewById(R.id.tv_Total)
        val tvCountHeads: TextView = findViewById(R.id.tv_countHeads)
        val tvCountTails: TextView = findViewById(R.id.tv_countTails)
        val btnClear : Button = findViewById(R.id.btn_clear)

        imgCoin.setOnClickListener {
            val randomNumber = (1..2).random()

            if (randomNumber == 1) {
                flipTheCoin(R.drawable.euro_av, "HEADS")
                headsCounter++

            } else {
                flipTheCoin(R.drawable.euro_rev, "TAILS")
                tailsCounter++
                tvCountTails.text = tailsCounter.toString()
            }

            increamentCount()
            tvTotal.text = totalCounter.toString()
            tvCountHeads.text = headsCounter.toString() + " / " + (headsCounter.toDouble()/totalCounter * 100).roundToInt().toString() + "%"
            tvCountTails.text = tailsCounter.toString() + " / " + (tailsCounter.toDouble()/totalCounter * 100).roundToInt().toString() + "%"

            btnClear.setOnClickListener {
                totalCounter = 0
                headsCounter = 0
                tailsCounter = 0
                tvTotal.text = ""
                tvCountHeads.text = ""
                tvCountTails.text = ""
            }
        }
    }

    private fun flipTheCoin(imageId: Int, coinSide: String) {
        imgCoin.animate().apply {
            duration = 1000
            rotationYBy(1800f)

        }.withEndAction {
            imgCoin.setImageResource(imageId)
            Toast.makeText(this, coinSide, Toast.LENGTH_SHORT).show()
            imgCoin.isClickable = true
        }.start()
    }

    private fun increamentCount () {
        totalCounter++
    }




}

