/*
 * MatchSearchPresenter.kt on Submission5final
 * Developed by Muhammad Utsman
 * Last modified 11/1/18 12:31 AM
 * Copyright (c) 2018 kucingapes
 */

package tampan.kucingapes.submission5final.presenter

import com.androidnetworking.error.ANError
import tampan.kucingapes.submission5final.MainContract
import tampan.kucingapes.submission5final.model.Match

class MatchSearchPresenter(private var view: MainContract.SearchMatchView,
                           private var getMatchSearch: MainContract.GetMatchSearch) :
        MainContract.SearchMatchPresenter,
        MainContract.GetMatchSearch.OnSuccessListener {
    override fun requestData(query: String) {
        getMatchSearch.getSearchMatchList(this, query)
    }

    override fun onFinished(dataList: MutableList<Match>?) {
        view.setDataList(dataList)
    }

    override fun onFailure(anError: ANError) {
        view.onFailedResponses(anError)
    }
}