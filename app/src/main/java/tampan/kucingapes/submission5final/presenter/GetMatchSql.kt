/*
 * GetMatchSql.kt on Submission5final
 * Developed by Muhammad Utsman
 * Last modified 10/31/18 5:04 PM
 * Copyright (c) 2018 kucingapes
 */

package tampan.kucingapes.submission5final.presenter

import android.content.Context
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import tampan.kucingapes.submission5final.MainContract
import tampan.kucingapes.submission5final.dbHelper.MatchFavoriteDbHelper
import tampan.kucingapes.submission5final.Utils
import tampan.kucingapes.submission5final.model.MatchFavorite

class GetMatchSql(private val context: Context?) : MainContract.GetMatchFromSql {
    override fun getMacthList(onSuccessListener: MainContract.GetMatchFromSql.OnSuccessListener) {
        val database = context?.let { MatchFavoriteDbHelper(it) }
        database?.use {
            val result = select(Utils.TABLE_FAVORITE_MATCH)
            val favorite = result.parseList(classParser<MatchFavorite>())
            onSuccessListener.onFinished(favorite.toMutableList())
        }
    }
}