/*
 * DetailTeam.kt on Submission5final
 * Developed by Muhammad Utsman
 * Last modified 10/31/18 5:04 PM
 * Copyright (c) 2018 kucingapes
 */

package tampan.kucingapes.submission5final

import android.annotation.SuppressLint
import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import org.jetbrains.anko.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import tampan.kucingapes.submission5final.adapter.ViewPagerAdapter
import tampan.kucingapes.submission5final.ankoUi.DetailTeamUi
import tampan.kucingapes.submission5final.dbHelper.TeamFavoriteDbHelper
import tampan.kucingapes.submission5final.fragment.pagerTeam.FragmentTeamDetail
import tampan.kucingapes.submission5final.fragment.pagerTeam.FragmentTeamPlayer
import tampan.kucingapes.submission5final.model.Teams
import tampan.kucingapes.submission5final.model.TeamsFavorite
import tampan.kucingapes.submission5final.model.TeamsResponse

class DetailTeam : AppCompatActivity(), AnkoLogger {

    private lateinit var toolbar: Toolbar
    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout

    private lateinit var badge: ImageView
    private lateinit var name: TextView
    private lateinit var stadium: TextView
    private lateinit var year: TextView
    private lateinit var manager: TextView

    private lateinit var idTeam: String

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private lateinit var database: TeamFavoriteDbHelper
    private var teams: Teams? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DetailTeamUi().setContentView(this)

        idTeam = intent.getStringExtra("idTeam")
        database = TeamFavoriteDbHelper(this)

        toolbar = find(R.id.toolbar)
        viewPager = find(R.id.view_pager)
        tabLayout = find(R.id.tabs)
        badge = find(R.id.team_badge)
        name = find(R.id.name_team)
        stadium = find(R.id.stadium)
        year = find(R.id.year_team)
        manager = find(R.id.manager)

        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar?.title = ""
        actionBar?.setDisplayHomeAsUpEnabled(true)


        val baseUrlTeam = "https://www.thesportsdb.com/api/v1/json/1/lookupteam.php"
        AndroidNetworking.get(baseUrlTeam)
            .addQueryParameter("id", idTeam)
            .build()
            .getAsObject(TeamsResponse::class.java, object : ParsedRequestListener<TeamsResponse> {
                override fun onResponse(response: TeamsResponse) {
                    teams = response.teams[0]
                    setupData(teams)
                }

                override fun onError(anError: ANError?) {}

            })

        favoriteState()
    }

    @SuppressLint("SetTextI18n")
    private fun setupData(teams: Teams?) {
        val pagerAdapter = ViewPagerAdapter(supportFragmentManager)
        val fragmentTeamDetail = FragmentTeamDetail.withData(idTeam, teams?.strDescriptionEN)
        val fragmentPlayer = FragmentTeamPlayer.withData(idTeam)
        pagerAdapter.addFragment(fragmentTeamDetail, "Overview")
        pagerAdapter.addFragment(fragmentPlayer, "Player")

        viewPager.adapter = pagerAdapter
        tabLayout.setupWithViewPager(viewPager)

        Utils.setBadgeImage(idTeam, badge)
        name.text = teams?.strTeam
        stadium.text = teams?.strStadium
        year.text = teams?.intFormedYear
        manager.text = "Manager: ${teams?.strManager}"

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
                    Utils.TABLE_FAVORITE_TEAM,
                    Utils.ID to teams?.idTeam?.toLong(),
                    Utils.TEAM_ID to teams?.idTeam,
                    Utils.NAME_TEAM to teams?.strTeam,
                    Utils.BADGE_TEAM to teams?.strTeamBadge
                )
            }
            toast("Added to favorite")
        } catch (e: SQLiteConstraintException) {
            error {"failed \n${e.localizedMessage}"}
            toast("failed \n${e.localizedMessage}")
        }
    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete(Utils.TABLE_FAVORITE_TEAM, "${Utils.ID} = {id}", "id" to idTeam)
            }
            toast("Removed from favorite")
        } catch (e: SQLiteConstraintException) {
            error {"failed \n${e.localizedMessage}"}
            toast("failed \n${e.localizedMessage}")
        }
    }

    private fun setFavorite() {
        if (isFavorite) menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this,
            R.drawable.ic_star_fill)
        else menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this,
            R.drawable.ic_star_border)
    }

    private fun favoriteState() {
        database.use {
            val result = select(Utils.TABLE_FAVORITE_TEAM)
                .whereArgs("(${Utils.ID}) = {id}", "id" to idTeam)
            val favorite = result.parseList(classParser<TeamsFavorite>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }
}