package view

import android.content.Context
import model.rest.Question

/**
 * Created by vbrown on 11/10/2016.
 */
interface MainView {
    fun getContext() : Context
    fun update(someData: List<Question>)
}