/*
 * GetTeamsApi.kt on Submission5final
 * Developed by Muhammad Utsman
 * Last modified 10/31/18 11:54 AM
 * Copyright (c) 2018 kucingapes
 */

package tampan.kucingapes.submission5final.presenter

import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import tampan.kucingapes.submission5final.MainContract
import tampan.kucingapes.submission5final.model.TeamsResponse

class GetTeamsApi : MainContract.GetTeamsFromApi {

    override fun getTeamList(onSuccessListener: MainContract.GetTeamsFromApi.OnSuccessListener, league: String) {
        val baseUrl = "https://www.thesportsdb.com/api/v1/json/1/search_all_teams.php"

        AndroidNetworking.get(baseUrl)
            .addQueryParameter("l", league)
            .build()
            .getAsObject(TeamsResponse::class.java, object : ParsedRequestListener<TeamsResponse> {
                override fun onResponse(response: TeamsResponse) {
                    onSuccessListener.onFinished(response.teams)
                }

                override fun onError(anError: ANError) {
                    onSuccessListener.onFailure(anError)
                }

            })
    }


}