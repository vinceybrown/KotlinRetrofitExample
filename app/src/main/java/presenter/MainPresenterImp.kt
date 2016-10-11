package presenter

import model.rest.StackOverflowQuestions
import model.rest.StackoverflowApi
import retrofit.Callback
import retrofit.Response
import retrofit.Retrofit
import view.MainView

/**
 * Created by vbrown on 11/10/2016.
 */

class MainPresenterImp constructor(view: MainView) : MainPresenter, Callback<StackOverflowQuestions> {

    private val  mainView: MainView
    private val retroMember: Retrofit

    init {
        mainView = view
        retroMember = Retrofit.Builder()
                .baseUrl("https://api.stackexchange.com")
                .addConverterFactory(retrofit.GsonConverterFactory.create())
                .build()
    }

    override fun start() {
    }

    override fun pause() {
    }

    override fun loadClicked() {
        // Call API.
        var api = retroMember.create<StackoverflowApi>(StackoverflowApi::class.java!!)
        val call = api.loadQuestions("android")
        call.enqueue(this)
    }

    override fun onResponse(response: Response<StackOverflowQuestions>?, retrofit: Retrofit?) {
        mainView.update(response?.body()?.items!!)
    }

    override fun onFailure(t: Throwable?) {
        println()
    }

}