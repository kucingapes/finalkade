/*
 * TeamSqlPresenter.kt on Submission5final
 * Developed by Muhammad Utsman
 * Last modified 10/31/18 3:35 PM
 * Copyright (c) 2018 kucingapes
 */

package tampan.kucingapes.submission5final.presenter

import com.androidnetworking.error.ANError
import tampan.kucingapes.submission5final.MainContract
import tampan.kucingapes.submission5final.model.TeamsFavorite

class TeamSqlPresenter(private var teamViewSql: MainContract.TeamViewSql,
                       private var getTeamsFromSql: MainContract.GetTeamsFromSql) :
        MainContract.TeamPresenterSql,
        MainContract.GetTeamsFromSql.OnSuccessListener{
    override fun requestData() {
        getTeamsFromSql.getTeamList(this)
    }

    override fun onFinished(dataList: MutableList<TeamsFavorite>) {
        teamViewSql.setDataList(dataList)
        teamViewSql.progressOff()
    }

    override fun onFailure(anError: ANError) {
        teamViewSql.onFailedResponses(anError)
        teamViewSql.progressOff()
    }


}