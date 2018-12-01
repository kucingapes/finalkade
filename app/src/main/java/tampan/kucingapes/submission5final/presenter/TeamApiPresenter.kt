/*
 * TeamApiPresenter.kt on Submission5final
 * Developed by Muhammad Utsman
 * Last modified 10/31/18 3:35 PM
 * Copyright (c) 2018 kucingapes
 */

package tampan.kucingapes.submission5final.presenter

import com.androidnetworking.error.ANError
import tampan.kucingapes.submission5final.MainContract
import tampan.kucingapes.submission5final.model.Teams

class TeamApiPresenter(
    private var teamsMainView: MainContract.TeamsMainView,
    private var getTeamsFromApi: MainContract.GetTeamsFromApi
) :
    MainContract.TeamPresenter,
    MainContract.GetTeamsFromApi.OnSuccessListener {
    override fun requestData(league: String) {
        getTeamsFromApi.getTeamList(this, league)
    }

    override fun onFinished(dataList: MutableList<Teams>) {
        teamsMainView.setDataList(dataList)
        teamsMainView.progressOff()
    }

    override fun onFailure(anError: ANError) {
        teamsMainView.onFailedResponses(anError)
        teamsMainView.progressOff()
    }
}