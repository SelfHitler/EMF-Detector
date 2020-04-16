package com.yabaze.emf

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.yabaze.emflibrary.EmfDetector
import com.yabaze.emflibrary.EmfStatus
import com.yabaze.emflibrary.EmfUnit
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var emfValue: Float = 0F

    val emfDetector = object : EmfDetector() {

        override fun returnEmfValue(status: EmfStatus, emfValue: Float, unit: EmfUnit) {

            this@MainActivity.emfValue = emfValue

            when(status){
                EmfStatus.SUCCESS->{
                    emfValueTextView.text = "$emfValue ${unit.name}"
                }
                EmfStatus.FAILURE->{
                    Toast.makeText(this@MainActivity,"Failed To Get EMF Value",Toast.LENGTH_LONG).show()
                    emfValueTextView.text = "$emfValue ${unit.name}"
                }
                EmfStatus.NOT_AVAILABLE->{
                    Toast.makeText(this@MainActivity,"Magnetic Sensor Not Available in your Device",Toast.LENGTH_LONG).show()
                    emfValueTextView.text = "$emfValue ${unit.name}"
                }
            }

        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun getEmfValue(view: View) {
        emfDetector.checkForSensor(this)
    }
}
