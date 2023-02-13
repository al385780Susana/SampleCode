package com.al385780.sampletest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity(), ISearchView {

    lateinit var lastDateButton: Button
    lateinit var customerNameButton: Button
    lateinit var firstDateButton: Button
    lateinit var jobsButton: Button
    lateinit var presenter: SearchPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lastDateButton = findViewById(R.id.lastDateButton)
        lastDateButton.setOnClickListener{presenter.lastDateSearch()}
        customerNameButton = findViewById(R.id.customerNameButton)
        customerNameButton.setOnClickListener{presenter.customerNameSearch()}
        firstDateButton = findViewById(R.id.firstDateButton)
        firstDateButton.setOnClickListener{presenter.firstDateSearch()}
        jobsButton = findViewById(R.id.jobsButton)
        jobsButton.setOnClickListener{presenter.jobsSearch()}

        presenter = SearchPresenter(this, Model(this))
    }

    override fun startResultsActivity(resultsInfo: Any) {
        TODO("Not yet implemented")
    }

}