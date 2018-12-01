/*
 * MatchSearchPresenterTest.kt on Submission5final
 * Developed by Muhammad Utsman
 * Last modified 11/1/18 1:08 PM
 * Copyright (c) 2018 kucingapes
 */

package tampan.kucingapes.submission5final.presenter

import org.junit.Test

import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import tampan.kucingapes.submission5final.MainContract
import tampan.kucingapes.submission5final.model.Match

class MatchSearchPresenterTest {

    @Mock
    private lateinit var view: MainContract.SearchMatchView

    @Mock
    private lateinit var presenter: MatchSearchPresenter

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        presenter = MatchSearchPresenter(view, GetSearchMatch())
    }

    @Test
    fun requestDataTest() {
        val dataList: MutableList<Match> = mutableListOf()

        presenter.requestData("liverpool")
        presenter.onFinished(dataList)

        verify(view).setDataList(dataList)
    }
}