/*
 * FragmentMatchFav.kt on Submission5final
 * Developed by Muhammad Utsman
 * Last modified 10/31/18 4:08 PM
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
import org.jetbrains.anko.support.v4.toast
import tampan.kucingapes.submission5final.MainContract
import tampan.kucingapes.submission5final.R
import tampan.kucingapes.submission5final.adapter.MatchFavoriteAdapter
import tampan.kucingapes.submission5final.model.MatchFavorite
import tampan.kucingapes.submission5final.presenter.GetMatchSql
import tampan.kucingapes.submission5final.presenter.MatchSqlPresenter

class FragmentMatchFav : Fragment(), AnkoComponent<Context>,
    MainContract.MatchMainViewSql {

    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var listMatch: RecyclerView
    private lateinit var textEmpty: TextView

    lateinit var presenter: MatchSqlPresenter
    private var matchAdapter: MatchFavoriteAdapter? = null
    private var list: MutableList<MatchFavorite> = mutableListOf()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val ui = context?.let {
            AnkoContext.create(it)
        }?.let {
            createView(it)
        }

        presenter = MatchSqlPresenter(this, GetMatchSql(context))
        progressOn()

        swipeRefresh.onRefresh {
            progressOn()
        }

        return ui
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {

        relativeLayout {
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

            textEmpty = textView("Favorite not found") {
                textSize = 20f
                textColorResource = R.color.colorPrimary
                visibility = View.GONE
            }.lparams { centerInParent() }
        }
    }

    override fun progressOn() {
        swipeRefresh.isRefreshing = true
        list.clear()
        presenter.requestData()
        matchAdapter?.notifyDataSetChanged()
    }

    override fun progressOff() {
        swipeRefresh.isRefreshing = false
    }

    override fun setDataList(dataList: MutableList<MatchFavorite>) {
        list.clear()
        list.addAll(dataList.toMutableList())
        matchAdapter = MatchFavoriteAdapter(list)
        listMatch.adapter = matchAdapter

        if (list.isEmpty()) {
            listMatch.visibility = View.GONE
            textEmpty.visibility = View.VISIBLE
        } else {
            listMatch.visibility = View.VISIBLE
            textEmpty.visibility = View.GONE
        }
    }

    override fun onFailedResponses(anError: ANError) {
        toast(anError.localizedMessage.toString())
    }
}