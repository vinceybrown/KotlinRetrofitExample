package com.intelematics.android.kotlinexample

import android.app.ListActivity
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.AppCompatButton
import android.view.View
import android.widget.ArrayAdapter
import model.rest.Question
import presenter.MainPresenter
import presenter.MainPresenterImp
import view.MainView
import java.util.*

class MainActivity : ListActivity(), MainView {

    private var mainPresenter: MainPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter: ArrayAdapter<Question> = ArrayAdapter(this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                ArrayList<Question>())
        listAdapter = adapter

        mainPresenter = MainPresenterImp(this)

        val loadButton: AppCompatButton = findViewById(R.id.load) as AppCompatButton
        loadButton.setOnClickListener(startButtonClicked())
    }

    private fun startButtonClicked(): View.OnClickListener {
        return object : View.OnClickListener {
            override fun onClick(v: View) {
                mainPresenter?.loadClicked()
            }
        }
    }

    override fun getContext(): Context {
        return this
    }

    override fun onStart() {
        super.onStart()
        mainPresenter?.start()
    }

    override fun onPause() {
        super.onPause()
        mainPresenter?.pause()
    }

    override fun update(someData: List<Question>) {
        val adapter: ArrayAdapter<Question> = listAdapter as ArrayAdapter<Question>
        adapter.clear()
        adapter.addAll(someData)
    }
}
