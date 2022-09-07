package com.example.tiptrip

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_main.*

private const val TAG = "MainActivity"
private const val INITIAL_CUT_PERCENT = 15

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        seekBarTip.progress= INITIAL_CUT_PERCENT
        textView2.text= "$INITIAL_CUT_PERCENT%"

        seekBarTip.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                Log.i (TAG, "TheProgressIs $p1")
                textView2.text = "$p1%"
                computeTipAndTotal()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}

            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })
        etFare.addTextChangedListener(object: TextWatcher {

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                Log.i (TAG, "afterTextChanged $p0")
                computeTipAndTotal()
            }
        })
    }

    private fun computeTipAndTotal() {
        if(etFare.text.isEmpty())
        {
            employerscut1.text= ""
            return
        }
        val baseAmount = etFare.text.toString().toDouble()
        val cutPercent = seekBarTip.progress
        val employerCut = baseAmount*cutPercent/100
        val driversPay = baseAmount-employerCut
        employerscut1.text= "$employerCut"
        driverpay.text= Editable.Factory.getInstance().newEditable(driversPay.toString())
    }
}