/*
 * ViewPagerAdapter.kt on Submission5final
 * Developed by Muhammad Utsman
 * Last modified 10/29/18 11:50 PM
 * Copyright (c) 2018 kucingapes
 */

package tampan.kucingapes.submission5final.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    private val pager : MutableList<Fragment> = mutableListOf()
    private val titlePager: MutableList<String> = mutableListOf()

    override fun getItem(p0: Int): Fragment = pager[p0]

    override fun getCount(): Int = pager.size

    override fun getPageTitle(position: Int): CharSequence? = titlePager[position]

    fun addFragment(fragment: Fragment, title: String) {
        pager.add(fragment)
        titlePager.add(title)
    }
}