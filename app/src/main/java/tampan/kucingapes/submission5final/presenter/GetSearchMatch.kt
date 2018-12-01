/*
 * GetSearchMatch.kt on Submission5final
 * Developed by Muhammad Utsman
 * Last modified 11/1/18 12:31 AM
 * Copyright (c) 2018 kucingapes
 */

package tampan.kucingapes.submission5final.presenter

import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import tampan.kucingapes.submission5final.MainContract
import tampan.kucingapes.submission5final.model.SearchEvent

class GetSearchMatch : MainContract.GetMatchSearch {
    override fun getSearchMatchList(onSuccessListener: MainContract.GetMatchSearch.OnSuccessListener, query: String) {
        val baseUrl = "https://www.thesportsdb.com/api/v1/json/1/searchevents.php"

        AndroidNetworking.get(baseUrl)
            .addQueryParameter("e", query)
            .build()
            .getAsObject(SearchEvent::class.java, object : ParsedRequestListener<SearchEvent> {
                override fun onResponse(response: SearchEvent?) {
                    onSuccessListener.onFinished(response?.event)

                }

                override fun onError(anError: ANError) {
                    onSuccessListener.onFailure(anError)
                }

            })
    }
}