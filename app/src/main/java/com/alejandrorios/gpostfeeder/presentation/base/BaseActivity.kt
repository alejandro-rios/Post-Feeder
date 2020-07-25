package com.alejandrorios.gpostfeeder.presentation.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * Created by Alejandro Rios on 7/25/20
 */
abstract class BaseActivity : AppCompatActivity() {

    abstract fun prepareActivityBuilder()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        prepareActivityBuilder()
    }
}
