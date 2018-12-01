/*
 * PlayerApiPresenter.kt on Submission5final
 * Developed by Muhammad Utsman
 * Last modified 10/31/18 11:54 AM
 * Copyright (c) 2018 kucingapes
 */

package tampan.kucingapes.submission5final.presenter

import com.androidnetworking.error.ANError
import tampan.kucingapes.submission5final.MainContract
import tampan.kucingapes.submission5final.model.Player

class PlayerApiPresenter(private var playerView: MainContract.PlayerView,
                         private var getPlayerList: MainContract.GetPlayerList) :
        MainContract.PlayerPresenter, MainContract.GetPlayerList.OnSuccessListener {
    override fun requestData(idTeam: String) {
        getPlayerList.getPlayerList(this, idTeam)
    }

    override fun onFinished(dataList: MutableList<Player>) {
        playerView.setDataList(dataList)
    }

    override fun onFailure(anError: ANError) {
        playerView.onFailedResponses(anError)
    }
}