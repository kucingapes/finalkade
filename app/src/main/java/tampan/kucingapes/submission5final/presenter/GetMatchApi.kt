/*
 * GetMatchApi.kt on Submission5final
 * Developed by Muhammad Utsman
 * Last modified 11/1/18 12:22 AM
 * Copyright (c) 2018 kucingapes
 */

package tampan.kucingapes.submission5final.presenter

import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import tampan.kucingapes.submission5final.MainContract
import tampan.kucingapes.submission5final.model.MatchResponse

class GetMatchApi : MainContract.GetMatchFromApi {
    private val baseUrl = "https://www.thesportsdb.com/api/v1/json/1/events{event}league.php"

    override fun getMatchList(
        onSuccessListener: MainContract.GetMatchFromApi.OnSuccessListener,
        event: String, id: String) {

        AndroidNetworking.get(baseUrl)
            .addPathParameter("event", event)
            .addQueryParameter("id", id)
            .build()
            .getAsObject(MatchResponse::class.java, object : ParsedRequestListener<MatchResponse> {
                override fun onResponse(response: MatchResponse) {
                    onSuccessListener.onFinished(response.events)
                }

                override fun onError(anError: ANError) {
                    onSuccessListener.onFailure(anError)
                }

            })
    }
}