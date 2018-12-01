/*
 * TeamFavoriteDbHelper.kt on Submission5final
 * Developed by Muhammad Utsman
 * Last modified 10/31/18 5:04 PM
 * Copyright (c) 2018 kucingapes
 */

package tampan.kucingapes.submission5final.dbHelper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*
import tampan.kucingapes.submission5final.Utils

class TeamFavoriteDbHelper(context: Context) : ManagedSQLiteOpenHelper(context, "FavoriteTeam.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(
            Utils.TABLE_FAVORITE_TEAM, true,
            Utils.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            Utils.TEAM_ID to TEXT + UNIQUE,
            Utils.NAME_TEAM to TEXT,
            Utils.BADGE_TEAM to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(Utils.TABLE_FAVORITE_TEAM, true)
    }
}