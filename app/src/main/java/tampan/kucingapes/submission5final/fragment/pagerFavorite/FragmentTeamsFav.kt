/*
 * FragmentTeamsFav.kt on Submission5final
 * Developed by Muhammad Utsman
 * Last modified 10/31/18 4:11 PM
 * Copyright (c) 2018 kucingapes
 */

package tampan.kucingapes.submission5final.fragment.pagerFavorite

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.androidnetworking.error.ANError
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout
import tampan.kucingapes.submission5final.MainContract
import tampan.kucingapes.submission5final.R
import tampan.kucingapes.submission5final.adapter.TeamsFavoriteAdapter
import tampan.kucingapes.submission5final.model.TeamsFavorite
import tampan.kucingapes.submission5final.presenter.GetTeamSql
import tampan.kucingapes.submission5final.presenter.TeamSqlPresenter

class FragmentTeamsFav : Fragment(), AnkoComponent<Context>, MainContract.TeamViewSql {

    private lateinit var recyclerTeam: RecyclerView
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var textEmpty: TextView

    private lateinit var presenter: MainContract.TeamPresenterSql

    private var teamsAdapter: TeamsFavoriteAdapter? = null
    private var listTeam: MutableList<TeamsFavorite> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val ui = context?.let {
            AnkoContext.create(it)
        }?.let {
            createView(it)
        }

        presenter = TeamSqlPresenter(this, GetTeamSql(context))
        progressOn()

        swipeRefresh.onRefresh {
            progressOn()
        }

        return ui
    }


    override fun createView(ui: AnkoContext<Context>): View = with(ui){
        relativeLayout {
            swipeRefresh = swipeRefreshLayout {
                id = R.id.swipe_refresh
                setColorSchemeResources(
                    R.color.colorAccent,
                    android.R.color.holo_green_light,
                    android.R.color.holo_orange_light,
                    android.R.color.holo_red_light)
                recyclerTeam = recyclerView {
                    lparams(matchParent, matchParent) {
                        topPadding = dip(6)
                        bottomPadding = dip(6)
                        clipToPadding = false
                        layoutManager = LinearLayoutManager(context)
                    }
                }
            }.lparams(matchParent, matchParent)

            textEmpty = textView("Favorite not found") {
                textSize = 20f
                textColorResource = R.color.colorPrimary
                visibility = View.GONE
            }.lparams { centerInParent() }
        }
    }

    override fun progressOn() {
        swipeRefresh.isRefreshing = true
        listTeam.clear()
        presenter.requestData()
        teamsAdapter?.notifyDataSetChanged()
    }

    override fun progressOff() {
        swipeRefresh.isRefreshing = false
    }

    override fun setDataList(dataList: MutableList<TeamsFavorite>) {
        listTeam.clear()
        listTeam.addAll(dataList)
        teamsAdapter = TeamsFavoriteAdapter(listTeam)
        recyclerTeam.adapter = teamsAdapter

        if (listTeam.isEmpty()) {
            recyclerTeam.visibility = View.GONE
            textEmpty.visibility = View.VISIBLE
        } else {
            recyclerTeam.visibility = View.VISIBLE
            textEmpty.visibility = View.GONE
        }
    }

    override fun onFailedResponses(anError: ANError) {
        error { "my_error ${anError.localizedMessage}" }
    }
}