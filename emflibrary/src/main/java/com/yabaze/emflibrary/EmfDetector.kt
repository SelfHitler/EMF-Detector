package com.yabaze.emflibrary

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Build
import kotlin.math.roundToInt
import kotlin.math.sqrt

/**
 * Created by yabaze.t on 16/04/2020
 */
abstract class EmfDetector {

    private lateinit var sensor: Sensor
    private lateinit var sensorManager: SensorManager
    var xValue: Float = 0.0f
    var yValue: Float = 0.0f
    var zValue: Float = 0.0f
    var emfValue: String = "NA"

    fun checkForSensor(context: Context) {
        sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        if (sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD) != null) {
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)
            registerListener()
        }else{
            returnEmfValue(EmfStatus.NOT_AVAILABLE,0.0F,EmfUnit.μT)
        }
    }

    fun registerListener() {
        sensor.also { magneticField ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                val mEventListener = object : SensorEventListener {
                    override fun onSensorChanged(event: SensorEvent?) {
                        if (event != null) {
                            if (event.sensor.type == Sensor.TYPE_MAGNETIC_FIELD) {
                                xValue = event.values[0]
                                yValue = event.values[1]
                                zValue = event.values[2]

                                val sumOfSquares: Float = (xValue * xValue) + (yValue * yValue) + (zValue * zValue)
                                emfValue = sqrt(sumOfSquares).roundToInt().toString() + " μT"
                                returnEmfValue(EmfStatus.SUCCESS,sumOfSquares,EmfUnit.μT)
                                sensorManager.unregisterListener(this)
                            }else{
                                returnEmfValue(EmfStatus.FAILURE,0.0F,EmfUnit.μT)
                            }
                        }else{
                            returnEmfValue(EmfStatus.FAILURE,0.0F,EmfUnit.μT)
                        }
                    }

                    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {}
                }

                sensorManager.registerListener(
                    mEventListener,
                    magneticField,
                    SensorManager.SENSOR_DELAY_NORMAL,
                    SensorManager.SENSOR_DELAY_UI
                )
            }else{
                returnEmfValue(EmfStatus.NOT_AVAILABLE,0.0F,EmfUnit.μT)
            }
        }
    }

    abstract fun returnEmfValue(status : EmfStatus,emfValue: Float,unit: EmfUnit)
}