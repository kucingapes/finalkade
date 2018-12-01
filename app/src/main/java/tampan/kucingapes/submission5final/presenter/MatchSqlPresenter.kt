/*
 * MatchSqlPresenter.kt on Submission5final
 * Developed by Muhammad Utsman
 * Last modified 10/30/18 8:48 PM
 * Copyright (c) 2018 kucingapes
 */

package tampan.kucingapes.submission5final.presenter

import com.androidnetworking.error.ANError
import tampan.kucingapes.submission5final.MainContract
import tampan.kucingapes.submission5final.model.MatchFavorite

class MatchSqlPresenter(private var mainViewSql: MainContract.MatchMainViewSql,
                        private var getMatchFromSql: MainContract.GetMatchFromSql) :
        MainContract.MatchFavoritePresenter, MainContract.GetMatchFromSql.OnSuccessListener {
    override fun requestData() {
        getMatchFromSql.getMacthList(this)
    }

    override fun onFinished(dataList: MutableList<MatchFavorite>) {
        mainViewSql.setDataList(dataList)
        mainViewSql.progressOff()
    }

    override fun onFailure(anError: ANError) {
        mainViewSql.onFailedResponses(anError)
        mainViewSql.progressOff()
    }

}