/*
 * SearchTeam.kt on Submission5final
 * Developed by Muhammad Utsman
 * Last modified 11/1/18 1:08 PM
 * Copyright (c) 2018 kucingapes
 */

package tampan.kucingapes.submission5final

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.support.v7.widget.Toolbar
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import com.androidnetworking.error.ANError
import org.jetbrains.anko.*
import tampan.kucingapes.submission5final.adapter.TeamsAdapter
import tampan.kucingapes.submission5final.ankoUi.SearchUi
import tampan.kucingapes.submission5final.model.Teams
import tampan.kucingapes.submission5final.presenter.GetSearchTeam
import tampan.kucingapes.submission5final.presenter.TeamSearchPresenter

class SearchTeam : AppCompatActivity(), MainContract.SearchTeamView, AnkoLogger {

    private var menu: Menu? = null
    private var teamAdapter: TeamsAdapter? = null
    private var data: MutableList<Teams> = mutableListOf()

    private lateinit var toolbar: Toolbar
    private lateinit var searchView: SearchView
    private lateinit var listSearch: RecyclerView

    private lateinit var presenter: TeamSearchPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SearchUi().setContentView(this)

        listSearch = find(R.id.list_search)
        teamAdapter = TeamsAdapter(data)

        toolbar = find(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        presenter = TeamSearchPresenter(this, GetSearchTeam())
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        this.menu = menu
        menu.findItem(R.id.action_search)?.let {
            setupSearch(it)
        }

        return true
    }

    private fun setupSearch(menuItem: MenuItem) {
        searchView = object : SearchView(this) {
            override fun dispatchKeyEventPreIme(event: KeyEvent): Boolean {
                if (event.keyCode == KeyEvent.KEYCODE_BACK &&
                    event.action == KeyEvent.ACTION_UP) {

                    Utils.hideSoftKeyboard(this@SearchTeam)
                }
                return super.dispatchKeyEventPreIme(event)
            }
        }.apply {
            setIconifiedByDefault(false)
        }


        menuItem.actionView = searchView

        val searchPlate = searchView.find<View>(android.support.v7.appcompat.R.id.search_plate)
        val editText = searchView.find<EditText>(android.support.v7.appcompat.R.id.search_src_text)
        searchPlate.backgroundColor = Color.TRANSPARENT
        editText.apply {
            backgroundColorResource = R.color.colorPrimary
            requestFocus()
            hint = "Search team..."
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String): Boolean {
                data.clear()
                presenter.requestData(p0)
                teamAdapter?.notifyDataSetChanged()
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(p0: String): Boolean {
                data.clear()
                presenter.requestData(p0)
                teamAdapter?.notifyDataSetChanged()
                return true
            }

        })
    }

    override fun setDataList(dataList: MutableList<Teams>?) {
        data.clear()
        dataList?.let {
            data.addAll(it)
        }
        teamAdapter?.notifyDataSetChanged()
        listSearch.adapter = teamAdapter
    }

    override fun onFailedResponses(anError: ANError) {
        error { "my_error ${anError.localizedMessage}" }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}