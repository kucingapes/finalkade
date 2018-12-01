/*
 * MatchApiPresenterTest.kt on Submission5final
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

class MatchApiPresenterTest {

    @Mock
    private lateinit var view: MainContract.MatchMainView

    @Mock
    private lateinit var presenter: MatchApiPresenter

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        presenter = MatchApiPresenter(view, GetMatchApi())
    }

    @Test
    fun requestDataTest() {
        val dataList: MutableList<Match> = mutableListOf()

        presenter.requestData("past","4331")
        presenter.onFinished(dataList)

        verify(view).setDataList(dataList)
    }
}