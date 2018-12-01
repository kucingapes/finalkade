/*
 * DetailMatch.kt on Submission5final
 * Developed by Muhammad Utsman
 * Last modified 11/1/18 2:32 AM
 * Copyright (c) 2018 kucingapes
 */

package tampan.kucingapes.submission5final

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import org.jetbrains.anko.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import tampan.kucingapes.submission5final.Utils.setBadgeImage
import tampan.kucingapes.submission5final.Utils.setupText
import tampan.kucingapes.submission5final.ankoUi.DetailMatchUi
import tampan.kucingapes.submission5final.dbHelper.MatchFavoriteDbHelper
import tampan.kucingapes.submission5final.model.Match
import tampan.kucingapes.submission5final.model.MatchFavorite
import tampan.kucingapes.submission5final.model.MatchResponse

class DetailMatch : AppCompatActivity(), AnkoLogger {
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    private var match: Match? = null
    private lateinit var idEvent: String
    private lateinit var dateMatch: String
    private lateinit var database: MatchFavoriteDbHelper


    private val baseUrlEvent = "https://www.thesportsdb.com/api/v1/json/1/lookupevent.php"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DetailMatchUi().setContentView(this)
        AndroidNetworking.initialize(this)
        database = MatchFavoriteDbHelper(this)

        val toolbar = find<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "Match Detail"

        idEvent = intent.getStringExtra("id_event")
        dateMatch = intent.getStringExtra("date_match")

        AndroidNetworking.get(baseUrlEvent)
            .addQueryParameter("id", idEvent)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsObject(MatchResponse::class.java, object : ParsedRequestListener<MatchResponse> {
                override fun onResponse(response: MatchResponse) {
                    match = response.events[0]
                    setupEvent()
                    find<ProgressBar>(R.id.progress_loader).visibility = View.GONE
                    find<LinearLayout>(R.id.container_detail).visibility = View.VISIBLE
                }

                override fun onError(anError: ANError) {
                    error { "my_error" + anError.message }
                    toast("error! \n" + anError.message).show()
                }
            })

        favoriteState()
    }

    private fun setupEvent() {
        setupText(find(R.id.date_match), dateMatch)

        setupText(find(R.id.home_team), match?.strHomeTeam)
        setupText(find(R.id.home_score), match?.intHomeScore)
        setupText(find(R.id.home_goals_player), match?.strHomeGoalDetails)
        setupText(find(R.id.home_shots), match?.intHomeShots)
        setupText(find(R.id.home_yellow), match?.strHomeYellowCards)
        setupText(find(R.id.home_red), match?.strHomeRedCards)
        setupText(find(R.id.home_kiper), match?.strHomeLineupGoalkeeper)
        setupText(find(R.id.home_df), match?.strHomeLineupDefense)
        setupText(find(R.id.home_md), match?.strHomeLineupMidfield)
        setupText(find(R.id.home_fw), match?.strHomeLineupForward)
        setupText(find(R.id.home_sub), match?.strHomeLineupSubstitutes)

        setupText(find(R.id.away_team), match?.strAwayTeam)
        setupText(find(R.id.away_score), match?.intAwayScore)
        setupText(find(R.id.away_goals_player), match?.strAwayGoalDetails)
        setupText(find(R.id.away_shots), match?.intAwayShots)
        setupText(find(R.id.away_yellow), match?.strAwayYellowCards)
        setupText(find(R.id.away_red), match?.strAwayRedCards)
        setupText(find(R.id.away_kiper), match?.strAwayLineupGoalkeeper)
        setupText(find(R.id.away_df), match?.strAwayLineupDefense)
        setupText(find(R.id.away_md), match?.strAwayLineupMidfield)
        setupText(find(R.id.away_fw), match?.strAwayLineupForward)
        setupText(find(R.id.away_sub), match?.strAwayLineupSubstitutes)

        setBadgeImage(match?.idHomeTeam, find(R.id.home_badge))
        setBadgeImage(match?.idAwayTeam, find(R.id.away_badge))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> onBackPressed()

            R.id.favorite_action -> {
                if (isFavorite) removeFromFavorite()
                else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun addToFavorite() {
        try {
            database.use {
                insert(
                    Utils.TABLE_FAVORITE_MATCH,
                    Utils.ID to match?.idEvent?.toLong(),
                    Utils.MATCH_DATE to match?.dateEvent,
                    Utils.MATCH_TIME to match?.strTime,
                    Utils.MATCH_ID to match?.idEvent,
                    Utils.HOME_TEAM to match?.strHomeTeam,
                    Utils.HOME_ID to match?.idHomeTeam,
                    Utils.HOME_SCORE to match?.intHomeScore,
                    Utils.AWAY_TEAM to match?.strAwayTeam,
                    Utils.AWAY_ID to match?.idAwayTeam,
                    Utils.AWAY_SCORE to match?.intAwayScore)
            }
            toast("Added to favorite")
        } catch (e: SQLiteConstraintException) {
            toast(e.toString())
        }
    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete(Utils.TABLE_FAVORITE_MATCH, "${Utils.ID} = {id}", "id" to idEvent)
            }
            toast("Removed from favorite")
        } catch (e: SQLiteConstraintException) {
            error { "my_error ${e.localizedMessage}" }
        }
    }

    private fun setFavorite() {
        if (isFavorite) menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this,
            R.drawable.ic_star_fill
        )
        else menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this,
            R.drawable.ic_star_border
        )
    }

    private fun favoriteState() {
        database.use {
            val result = select(Utils.TABLE_FAVORITE_MATCH)
                .whereArgs("(${Utils.ID}) = {id}", "id" to idEvent)
            val favorite =  result.parseList(classParser<MatchFavorite>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }
}