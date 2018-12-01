/*
 * FragmentTeamPlayer.kt on Submission5final
 * Developed by Muhammad Utsman
 * Last modified 11/1/18 12:02 PM
 * Copyright (c) 2018 kucingapes
 */

package tampan.kucingapes.submission5final.fragment.pagerTeam

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.androidnetworking.error.ANError
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.nestedScrollView
import tampan.kucingapes.submission5final.MainContract
import tampan.kucingapes.submission5final.R
import tampan.kucingapes.submission5final.adapter.PlayerAdapter
import tampan.kucingapes.submission5final.model.Player
import tampan.kucingapes.submission5final.presenter.GetPlayerApi
import tampan.kucingapes.submission5final.presenter.PlayerApiPresenter

class FragmentTeamPlayer : Fragment(), AnkoComponent<Context>,
    MainContract.PlayerView, AnkoLogger {

    private var listPlayer: MutableList<Player> = mutableListOf()

    private lateinit var list: RecyclerView
    private lateinit var playerAdapter: PlayerAdapter

    private lateinit var idTeam: String

    companion object {
        fun withData(idTeam: String) : FragmentTeamPlayer {
            val fragmentPlayer = FragmentTeamPlayer()
            val bundle = Bundle()
            bundle.putString("idTeam", idTeam)
            fragmentPlayer.arguments = bundle
            return fragmentPlayer
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            idTeam = it.getString("idTeam", null)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val presenter = PlayerApiPresenter(this, GetPlayerApi())
        presenter.requestData(idTeam)

        playerAdapter = PlayerAdapter(listPlayer)
        return context?.let {
            AnkoContext.create(it)
        }?.let {
            createView(it)
        }
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        nestedScrollView {
            list = recyclerView {
                id = R.id.list_player
                layoutManager = LinearLayoutManager(context)
                adapter = playerAdapter
            }
        }
    }

    override fun setDataList(dataList: MutableList<Player>) {
        listPlayer.addAll(dataList)
        playerAdapter.notifyDataSetChanged()
    }

    override fun onFailedResponses(anError: ANError) {
        error { "my_error ${anError.localizedMessage}" }
    }
}