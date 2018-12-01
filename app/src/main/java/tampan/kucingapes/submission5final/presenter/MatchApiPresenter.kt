/*
 * MatchApiPresenter.kt on Submission5final
 * Developed by Muhammad Utsman
 * Last modified 11/1/18 12:10 AM
 * Copyright (c) 2018 kucingapes
 */

package tampan.kucingapes.submission5final.presenter

import com.androidnetworking.error.ANError
import tampan.kucingapes.submission5final.MainContract
import tampan.kucingapes.submission5final.model.Match

class MatchApiPresenter(
    private var matchMainView: MainContract.MatchMainView,
    private var getDataMatchFromApi: MainContract.GetMatchFromApi
) :
    MainContract.MatchPresenter,
    MainContract.GetMatchFromApi.OnSuccessListener {

    override fun requestData(event: String, id: String) {
        getDataMatchFromApi.getMatchList(this, event, id)
    }

    override fun onFinished(dataList: MutableList<Match>) {
        matchMainView.setDataList(dataList)
        matchMainView.progreesOff()
    }

    override fun onFailure(anError: ANError) {
        matchMainView.onFailedResponses(anError)
        matchMainView.progreesOff()
    }
}