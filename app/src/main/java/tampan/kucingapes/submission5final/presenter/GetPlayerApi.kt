/*
 * GetPlayerApi.kt on Submission5final
 * Developed by Muhammad Utsman
 * Last modified 10/31/18 1:10 PM
 * Copyright (c) 2018 kucingapes
 */

package tampan.kucingapes.submission5final.presenter

import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import tampan.kucingapes.submission5final.MainContract
import tampan.kucingapes.submission5final.model.PlayerResponses

class GetPlayerApi : MainContract.GetPlayerList {
    override fun getPlayerList(onSuccessListener: MainContract.GetPlayerList.OnSuccessListener, idTeam: String) {
        val baseUrlPlayer = "https://www.thesportsdb.com/api/v1/json/1/lookup_all_players.php"

        AndroidNetworking.get(baseUrlPlayer)
            .addQueryParameter("id", idTeam)
            .build()
            .getAsObject(PlayerResponses::class.java, object : ParsedRequestListener<PlayerResponses> {
                override fun onResponse(response: PlayerResponses) {
                    onSuccessListener.onFinished(response.player)
                }

                override fun onError(anError: ANError) {
                    onSuccessListener.onFailure(anError)
                }

            })
    }

}