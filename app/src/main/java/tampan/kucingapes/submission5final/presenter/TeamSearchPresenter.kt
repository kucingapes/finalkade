/*
 * TeamSearchPresenter.kt on Submission5final
 * Developed by Muhammad Utsman
 * Last modified 11/1/18 1:28 AM
 * Copyright (c) 2018 kucingapes
 */

package tampan.kucingapes.submission5final.presenter

import com.androidnetworking.error.ANError
import tampan.kucingapes.submission5final.MainContract
import tampan.kucingapes.submission5final.model.Teams

class TeamSearchPresenter(private var searchTeamView: MainContract.SearchTeamView,
                          private var getTeamSearch: MainContract.GetTeamSearch) :
        MainContract.SearchTeamPresenter,
        MainContract.GetTeamSearch.OnSuccessListener {
    override fun requestData(query: String) {
        getTeamSearch.getSearchTeamList(this, query)
    }

    override fun onFinished(dataList: MutableList<Teams>?) {
        searchTeamView.setDataList(dataList)
    }

    override fun onFailure(anError: ANError) {
        searchTeamView.onFailedResponses(anError)
    }
}