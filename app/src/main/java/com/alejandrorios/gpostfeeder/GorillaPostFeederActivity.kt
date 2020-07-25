package com.alejandrorios.gpostfeeder

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alejandrorios.gpostfeeder.presentation.feeds.FeedsActivity

/**
 * Created by Alejandro Rios on 7/25/20
 */
class GorillaPostFeederActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        startActivity(Intent(this@GorillaPostFeederActivity, FeedsActivity::class.java))
        finish()
    }
}
