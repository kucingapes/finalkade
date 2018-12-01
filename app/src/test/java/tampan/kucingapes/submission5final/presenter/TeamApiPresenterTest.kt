/*
 * TeamApiPresenterTest.kt on Submission5final
 * Developed by Muhammad Utsman
 * Last modified 10/31/18 5:04 PM
 * Copyright (c) 2018 kucingapes
 */

package tampan.kucingapes.submission5final.presenter

import org.junit.Test

import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import tampan.kucingapes.submission5final.MainContract
import tampan.kucingapes.submission5final.model.Teams

class TeamApiPresenterTest {

    @Mock
    private lateinit var view: MainContract.TeamsMainView

    @Mock
    private lateinit var presenter: TeamApiPresenter

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        presenter = TeamApiPresenter(view, GetTeamsApi())
    }

    @Test
    fun requestDataTest() {
        val dataList: MutableList<Teams> = mutableListOf()

        presenter.requestData("4328")
        presenter.onFinished(dataList)

        verify(view).setDataList(dataList)
    }
}