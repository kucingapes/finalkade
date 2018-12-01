/*
 * MainContract.kt on Submission5final
 * Developed by Muhammad Utsman
 * Last modified 11/1/18 1:28 AM
 * Copyright (c) 2018 kucingapes
 */

package tampan.kucingapes.submission5final

import com.androidnetworking.error.ANError
import tampan.kucingapes.submission5final.model.*

interface MainContract {
    interface MatchPresenter {
        fun requestData(event: String, id: String)
    }

    interface MatchMainView {
        fun progressOn()
        fun progreesOff()
        fun setDataList(dataList: MutableList<Match>)
        fun onFailedResponses(anError: ANError)
    }

    interface GetMatchFromApi {
        interface OnSuccessListener {
            fun onFinished(dataList: MutableList<Match>)
            fun onFailure(anError: ANError)
        }

        fun getMatchList(
            onSuccessListener: OnSuccessListener,
            event: String,
            id: String
        )
    }

    // match sql
    interface MatchFavoritePresenter {
        fun requestData()
    }

    interface MatchMainViewSql {
        fun progressOn()
        fun progressOff()
        fun setDataList(dataList: MutableList<MatchFavorite>)
        fun onFailedResponses(anError: ANError)
    }

    interface GetMatchFromSql {
        interface OnSuccessListener {
            fun onFinished(dataList: MutableList<MatchFavorite>)
            fun onFailure(anError: ANError)
        }

        fun getMacthList(onSuccessListener: OnSuccessListener)
    }


    // list team

    interface TeamPresenter {
        fun requestData(league: String)
    }

    interface TeamsMainView {
        fun progressOn()
        fun progressOff()
        fun setDataList(dataList: MutableList<Teams>)
        fun onFailedResponses(anError: ANError)
    }

    interface GetTeamsFromApi {
        interface OnSuccessListener {
            fun onFinished(dataList: MutableList<Teams>)
            fun onFailure(anError: ANError)
        }

        fun getTeamList(onSuccessListener: OnSuccessListener, league: String)
    }

    // team sql
    interface TeamPresenterSql {
        fun requestData()
    }

    interface TeamViewSql {
        fun progressOn()
        fun progressOff()
        fun setDataList(dataList: MutableList<TeamsFavorite>)
        fun onFailedResponses(anError: ANError)
    }

    interface GetTeamsFromSql {
        interface OnSuccessListener {
            fun onFinished(dataList: MutableList<TeamsFavorite>)
            fun onFailure(anError: ANError)
        }

        fun getTeamList(onSuccessListener: OnSuccessListener)
    }

    // list player
    interface PlayerPresenter {
        fun requestData(idTeam: String)
    }

    interface PlayerView {
        fun setDataList(dataList: MutableList<Player>)
        fun onFailedResponses(anError: ANError)
    }

    interface GetPlayerList {
        interface OnSuccessListener {
            fun onFinished(dataList: MutableList<Player>)
            fun onFailure(anError: ANError)
        }

        fun getPlayerList(onSuccessListener: OnSuccessListener, idTeam: String)
    }

    // search match
    interface SearchMatchPresenter {
        fun requestData(query: String)
    }

    interface SearchMatchView {
        fun setDataList(dataList: MutableList<Match>?)
        fun onFailedResponses(anError: ANError)
    }

    interface GetMatchSearch {
        interface OnSuccessListener {
            fun onFinished(dataList: MutableList<Match>?)
            fun onFailure(anError: ANError)
        }

        fun getSearchMatchList(onSuccessListener: OnSuccessListener, query: String)
    }

    // search team
    interface SearchTeamPresenter {
        fun requestData(query: String)
    }

    interface SearchTeamView {
        fun setDataList(dataList: MutableList<Teams>?)
        fun onFailedResponses(anError: ANError)
    }

    interface GetTeamSearch {
        interface OnSuccessListener {
            fun onFinished(dataList: MutableList<Teams>?)
            fun onFailure(anError: ANError)
        }

        fun getSearchTeamList(onSuccessListener: OnSuccessListener, query: String)
    }
}