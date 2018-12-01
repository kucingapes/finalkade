/*
 * MatchFavoriteDbHelper.kt on Submission5final
 * Developed by Muhammad Utsman
 * Last modified 11/1/18 2:05 AM
 * Copyright (c) 2018 kucingapes
 */

package tampan.kucingapes.submission5final.dbHelper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*
import tampan.kucingapes.submission5final.Utils

class MatchFavoriteDbHelper(context: Context) : ManagedSQLiteOpenHelper(context, "FavoriteMatch.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(
            Utils.TABLE_FAVORITE_MATCH, true,
            Utils.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            Utils.MATCH_ID to TEXT + UNIQUE,
            Utils.MATCH_DATE to TEXT,
            Utils.MATCH_TIME to TEXT,
            Utils.HOME_TEAM to TEXT,
            Utils.HOME_ID to TEXT,
            Utils.HOME_SCORE to TEXT,
            Utils.AWAY_TEAM to TEXT,
            Utils.AWAY_ID to TEXT,
            Utils.AWAY_SCORE to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(Utils.TABLE_FAVORITE_MATCH, true)
    }
}