/*
 * GetTeamSql.kt on Submission5final
 * Developed by Muhammad Utsman
 * Last modified 10/31/18 5:04 PM
 * Copyright (c) 2018 kucingapes
 */

package tampan.kucingapes.submission5final.presenter

import android.content.Context
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import tampan.kucingapes.submission5final.MainContract
import tampan.kucingapes.submission5final.dbHelper.TeamFavoriteDbHelper
import tampan.kucingapes.submission5final.Utils
import tampan.kucingapes.submission5final.model.TeamsFavorite

class GetTeamSql(private val context: Context?) : MainContract.GetTeamsFromSql {

    override fun getTeamList(onSuccessListener: MainContract.GetTeamsFromSql.OnSuccessListener) {
        val database = context?.let { TeamFavoriteDbHelper(it) }
        database?.use {
            val result = select(Utils.TABLE_FAVORITE_TEAM)
            val favorite = result.parseList(classParser<TeamsFavorite>())
            onSuccessListener.onFinished(favorite.toMutableList())
        }
    }
}