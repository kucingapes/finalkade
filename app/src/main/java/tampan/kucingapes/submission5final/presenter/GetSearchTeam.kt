/*
 * GetSearchTeam.kt on Submission5final
 * Developed by Muhammad Utsman
 * Last modified 11/1/18 1:51 AM
 * Copyright (c) 2018 kucingapes
 */

package tampan.kucingapes.submission5final.presenter

import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import tampan.kucingapes.submission5final.MainContract
import tampan.kucingapes.submission5final.model.TeamsResponse

class GetSearchTeam : MainContract.GetTeamSearch {
    override fun getSearchTeamList(onSuccessListener: MainContract.GetTeamSearch.OnSuccessListener, query: String) {
        val baseUrl = "https://www.thesportsdb.com/api/v1/json/1/searchteams.php"

        AndroidNetworking.get(baseUrl)
            .addQueryParameter("t", query)
            .build()
            .getAsObject(TeamsResponse::class.java, object : ParsedRequestListener<TeamsResponse> {
                override fun onResponse(response: TeamsResponse?) {
                    onSuccessListener.onFinished(response?.teams)
                }

                override fun onError(anError: ANError) {
                    onSuccessListener.onFailure(anError)
                }

            })
    }
}