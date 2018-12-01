/*
 * FragmentSchedule.kt on Submission5final
 * Developed by Muhammad Utsman
 * Last modified 11/1/18 1:51 AM
 * Copyright (c) 2018 kucingapes
 */

package tampan.kucingapes.submission5final.fragment

import android.content.Context
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.widget.Toolbar
import android.view.*
import org.jetbrains.anko.*
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.themedTabLayout
import org.jetbrains.anko.support.v4.viewPager
import org.jetbrains.anko.startActivity
import tampan.kucingapes.submission5final.R
import tampan.kucingapes.submission5final.R.style.ThemeOverlay_AppCompat_Dark
import tampan.kucingapes.submission5final.SearchSchedule
import tampan.kucingapes.submission5final.Utils.myToolbar
import tampan.kucingapes.submission5final.adapter.ViewPagerAdapter
import tampan.kucingapes.submission5final.fragment.pagerMatch.FragmentMatch

class FragmentSchedule : Fragment(), AnkoComponent<Context> {
    private lateinit var mViewPager: ViewPager
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private lateinit var toolbar: Toolbar


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val ui = context?.let {
            AnkoContext.create(it)
        }?.let {
            createView(it)
        }

        viewPagerAdapter = ViewPagerAdapter(childFragmentManager)
        val pageLast = FragmentMatch.withData("past")
        val pageNext = FragmentMatch.withData("next")
        viewPagerAdapter.addFragment(pageNext, "Next")
        viewPagerAdapter.addFragment(pageLast, "Last")
        mViewPager.adapter = viewPagerAdapter

        toolbar.inflateMenu(R.menu.menu_search)
        val searchMenu = toolbar.menu.findItem(R.id.action_search)
        searchMenu.setOnMenuItemClickListener {
            context?.startActivity<SearchSchedule>()
            true
        }

        return ui
    }


    override fun createView(ui: AnkoContext<Context>): View = with(ui){
        coordinatorLayout {
            mViewPager = viewPager {
                id = R.id.view_pager
            }.lparams(matchParent, matchParent) {
                behavior = AppBarLayout.ScrollingViewBehavior()
            }

            appBarLayout {
                toolbar = myToolbar().lparams(matchParent, wrapContent) {
                    scrollFlags = AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS
                }

                themedTabLayout(ThemeOverlay_AppCompat_Dark) {
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