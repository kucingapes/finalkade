/*
 * FragmentTeams.kt on Submission5final
 * Developed by Muhammad Utsman
 * Last modified 11/1/18 11:37 AM
 * Copyright (c) 2018 kucingapes
 */

package tampan.kucingapes.submission5final.fragment

import android.content.Context
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.androidnetworking.error.ANError
import org.jetbrains.anko.*
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout
import tampan.kucingapes.submission5final.MainContract
import tampan.kucingapes.submission5final.R
import tampan.kucingapes.submission5final.SearchTeam
import tampan.kucingapes.submission5final.Utils.myToolbar
import tampan.kucingapes.submission5final.adapter.TeamsAdapter
import tampan.kucingapes.submission5final.model.Teams
import tampan.kucingapes.submission5final.presenter.GetTeamsApi
import tampan.kucingapes.submission5final.presenter.TeamApiPresenter

class FragmentTeams : Fragment(), AnkoComponent<Context>,
    MainContract.TeamsMainView, AnkoLogger {

    private lateinit var toolbar: Toolbar
    private lateinit var spinner: Spinner
    private lateinit var recyclerTeam: RecyclerView
    private lateinit var swipeRefresh: SwipeRefreshLayout


    private lateinit var presenter: TeamApiPresenter
    private lateinit var league: String

    private var teamsAdapter: TeamsAdapter? = null
    private var listTeam: MutableList<Teams> = mutableListOf()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val ui = context?.let {
            AnkoContext.create(it)
        }?.let {
            createView(it)
        }

        toolbar.inflateMenu(R.menu.menu_search)
        val searchMenu = toolbar.menu.findItem(R.id.action_search)
        searchMenu.setOnMenuItemClickListener {
            context?.startActivity<SearchTeam>()
            true
        }

        val spinnerItem = resources.getStringArray(R.array.league)
        val spinnerAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, spinnerItem)
        spinner.adapter = spinnerAdapter


        presenter = TeamApiPresenter(
            this,
            GetTeamsApi()
        )

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                league = spinner.selectedItem.toString()
                progressOn()

            }

        }

        swipeRefresh.onRefresh {
            progressOn()
        }

        return ui
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        coordinatorLayout {
            verticalLayout {
                spinner = spinner()
                swipeRefresh = swipeRefreshLayout {
                    id = R.id.swipe_refresh
                    setColorSchemeResources(
                        R.color.colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light)
                    recyclerTeam = recyclerView {
                        id = R.id.list_team
                        lparams(matchParent, matchParent) {
                            topPadding = dip(6)
                            bottomPadding = dip(6)
                            clipToPadding = false
                            layoutManager = LinearLayoutManager(context)
                        }
                    }
                }.lparams(matchParent, matchParent)
            }.lparams(matchParent, matchParent) {
                behavior = AppBarLayout.ScrollingViewBehavior()
            }

            appBarLayout {
                toolbar = myToolbar().lparams(matchParent, wrapContent) {
                    scrollFlags = AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS
                }
            }.lparams(matchParent, wrapContent)
        }
    }


    override fun progressOn() {
        swipeRefresh.isRefreshing = true
        listTeam.clear()
        presenter.requestData(league)
        teamsAdapter?.notifyDataSetChanged()
    }

    override fun progressOff() {
        swipeRefresh.isRefreshing = false
    }

    override fun setDataList(dataList: MutableList<Teams>) {
        listTeam.clear()
        listTeam.addAll(dataList)
        teamsAdapter = TeamsAdapter(listTeam)
        recyclerTeam.adapter = teamsAdapter
    }

    override fun onFailedResponses(anError: ANError) {
        error { "my_error ${anError.localizedMessage}" }
    }
}