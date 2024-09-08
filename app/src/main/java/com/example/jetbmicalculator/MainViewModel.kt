package com.example.jetbmicalculator

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlin.math.pow
import kotlin.math.roundToInt

class MainViewModel: ViewModel() {
    // heightの変数をJetpackComposeにて監視し、変更があれば修正
    var height by mutableStateOf("")
    var weight by mutableStateOf("")
    var bmi by mutableFloatStateOf(0f)

    fun calculateBMI() {
        // divは100で割る
        val heightNumber = height.toFloatOrNull()?.div(100) ?: 0f
        val weightNumber = weight.toFloatOrNull() ?: 0f

        bmi = if (weightNumber > 0f && heightNumber > 0f) {
            (weightNumber / heightNumber.pow(2)* 10) .roundToInt() / 10f
        } else 0f

        Log.d("weight", weight)
        Log.d("height", height)
        Log.d("bmi", bmi.toString())
    }
}