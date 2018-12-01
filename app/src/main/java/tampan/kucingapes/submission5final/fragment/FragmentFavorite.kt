/*
 * FragmentFavorite.kt on Submission5final
 * Developed by Muhammad Utsman
 * Last modified 10/30/18 9:15 PM
 * Copyright (c) 2018 kucingapes
 */

package tampan.kucingapes.submission5final.fragment

import android.content.Context
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.themedTabLayout
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.support.v4.viewPager
import org.jetbrains.anko.wrapContent
import tampan.kucingapes.submission5final.R
import tampan.kucingapes.submission5final.Utils.myToolbar
import tampan.kucingapes.submission5final.adapter.ViewPagerAdapter
import tampan.kucingapes.submission5final.fragment.pagerFavorite.FragmentMatchFav
import tampan.kucingapes.submission5final.fragment.pagerFavorite.FragmentTeamsFav

class FragmentFavorite : Fragment(), AnkoComponent<Context> {
    private lateinit var mViewPager: ViewPager
    private lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewPagerAdapter = ViewPagerAdapter(childFragmentManager)
        val pageMatch = FragmentMatchFav()
        val pageTeams = FragmentTeamsFav()
        viewPagerAdapter.addFragment(pageMatch, "Matches")
        viewPagerAdapter.addFragment(pageTeams, "Teams")


        return context?.let {
            AnkoContext.create(it)
        }?.let {
            createView(it)
        }
    }


    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        coordinatorLayout {
            mViewPager = viewPager {
                id = R.id.view_pager
                adapter = viewPagerAdapter
            }.lparams(matchParent, matchParent) {
                behavior = AppBarLayout.ScrollingViewBehavior()
            }

            appBarLayout {
                myToolbar().lparams(matchParent, wrapContent) {
                    scrollFlags = AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS
                }

                themedTabLayout(R.style.ThemeOverlay_AppCompat_Dark) {
                    id = R.id.tabs
                    setupWithViewPager(mViewPager)
                }.lparams {
                    width = matchParent
                    height = wrapContent
                    scrollFlags = AppBarLayout.LayoutParams.SCROLL_FLAG_SNAP
                }
            }.lparams(matchParent, wrapContent)

        }
    }
}