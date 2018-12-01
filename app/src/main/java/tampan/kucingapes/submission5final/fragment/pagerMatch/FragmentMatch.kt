/*
 * FragmentMatch.kt on Submission5final
 * Developed by Muhammad Utsman
 * Last modified 10/31/18 6:17 PM
 * Copyright (c) 2018 kucingapes
 */

package tampan.kucingapes.submission5final.fragment.pagerMatch

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.androidnetworking.error.ANError
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout
import org.jetbrains.anko.support.v4.toast
import tampan.kucingapes.submission5final.MainContract
import tampan.kucingapes.submission5final.R
import tampan.kucingapes.submission5final.adapter.MatchAdapter
import tampan.kucingapes.submission5final.model.Match
import tampan.kucingapes.submission5final.model.LeagueWithId
import tampan.kucingapes.submission5final.presenter.GetMatchApi
import tampan.kucingapes.submission5final.presenter.MatchApiPresenter

class FragmentMatch : Fragment(), AnkoComponent<Context>,
    MainContract.MatchMainView {

    private lateinit var spinner: Spinner
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var listMatch: RecyclerView

    lateinit var presenter: MatchApiPresenter
    private var matchAdapter: MatchAdapter? = null
    private var list: MutableList<Match> = mutableListOf()

    private lateinit var event: String
    private lateinit var idLeague: String

    companion object {
        fun withData(event: String) : FragmentMatch {
            val fragmentLast = FragmentMatch()
            val bundle = Bundle()
            bundle.putString("event", event)
            fragmentLast.arguments = bundle
            return fragmentLast
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            event = it.getString("event", "")
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val ui = context?.let {
            AnkoContext.create(it)
        }?.let {
            createView(it)
        }

        val leagueWithId: MutableList<LeagueWithId> = mutableListOf()
        leagueWithId.add(LeagueWithId("English Premier League", "4328"))
        leagueWithId.add(LeagueWithId("English League Championship", "4329"))
        leagueWithId.add(LeagueWithId("German Bundesliga", "4331"))
        leagueWithId.add(LeagueWithId("Italian Serie A", "4332"))
        leagueWithId.add(LeagueWithId("French Ligue 1", "4334"))
        leagueWithId.add(LeagueWithId("Spanish La Liga", "4335"))


        val spinnerAdapter = ArrayAdapter(context,
            android.R.layout.simple_spinner_dropdown_item, leagueWithId)
        spinner.adapter = spinnerAdapter

        presenter = MatchApiPresenter(
            this,
            GetMatchApi()
        )

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val titleWithId = parent.getItemAtPosition(position) as LeagueWithId
                idLeague = titleWithId.id
                progressOn()
            }
        }

        swipeRefresh.onRefresh {
            progressOn()
        }

        return ui
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        verticalLayout {
            lparams(matchParent, matchParent)

            spinner = spinner {
                id = R.id.spinner
            }
            swipeRefresh = swipeRefreshLayout {
                id = R.id.swipe_refresh
                setColorSchemeResources(
                    R.color.colorAccent,
                    android.R.color.holo_green_light,
                    android.R.color.holo_orange_light,
                    android.R.color.holo_red_light)

                listMatch = recyclerView {
                    id = R.id.list_match
                    lparams(matchParent, matchParent)
                    topPadding = dip(6)
                    bottomPadding = dip(6)
                    clipToPadding = false

                    layoutManager = LinearLayoutManager(context)
                }
            }.lparams(matchParent, matchParent)
        }
    }

    override fun progressOn() {
        swipeRefresh.isRefreshing = true
        list.clear()
        presenter.requestData(event, idLeague)
        matchAdapter?.notifyDataSetChanged()
    }

    override fun progreesOff() {
        swipeRefresh.isRefreshing = false
    }

    override fun setDataList(dataList: MutableList<Match>) {
        list.clear()
        list.addAll(dataList)
        matchAdapter = MatchAdapter(list)
        listMatch.adapter = matchAdapter
    }

    override fun onFailedResponses(anError: ANError) {
        toast(anError.localizedMessage.toString())
    }

}