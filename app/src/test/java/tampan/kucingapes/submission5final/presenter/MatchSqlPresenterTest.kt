/*
 * MatchSqlPresenterTest.kt on Submission5final
 * Developed by Muhammad Utsman
 * Last modified 10/31/18 4:31 PM
 * Copyright (c) 2018 kucingapes
 */

package tampan.kucingapes.submission5final.presenter

import org.junit.Test

import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import tampan.kucingapes.submission5final.MainContract
import tampan.kucingapes.submission5final.model.MatchFavorite

class MatchSqlPresenterTest {

    @Mock
    private lateinit var view: MainContract.MatchMainViewSql

    @Mock lateinit var presenter: MatchSqlPresenter

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        presenter = MatchSqlPresenter(view, GetMatchSql(context = null))
    }

    @Test
    fun requestDataTest() {
        val dataList: MutableList<MatchFavorite> = mutableListOf()

        presenter.requestData()
        presenter.onFinished(dataList)

        verify(view).setDataList(dataList)
    }
}