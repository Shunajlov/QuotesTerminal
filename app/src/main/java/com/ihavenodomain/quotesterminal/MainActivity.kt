package com.ihavenodomain.quotesterminal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ihavenodomain.quotesterminal.quoteslist.presentation.view.SecuritiesListMainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, SecuritiesListMainFragment.newInstance())
                .commitNow()
        }
    }
}