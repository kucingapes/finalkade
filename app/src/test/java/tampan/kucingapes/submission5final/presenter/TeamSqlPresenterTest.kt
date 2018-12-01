/*
 * TeamSqlPresenterTest.kt on Submission5final
 * Developed by Muhammad Utsman
 * Last modified 10/31/18 5:16 PM
 * Copyright (c) 2018 kucingapes
 */

package tampan.kucingapes.submission5final.presenter

import org.junit.Test

import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import tampan.kucingapes.submission5final.MainContract
import tampan.kucingapes.submission5final.model.TeamsFavorite

class TeamSqlPresenterTest {

    @Mock
    private lateinit var view: MainContract.TeamViewSql

    @Mock
    private lateinit var presenter: TeamSqlPresenter

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        presenter = TeamSqlPresenter(view, GetTeamSql(context = null))
    }

    @Test
    fun requestData() {
        val dataList: MutableList<TeamsFavorite> = mutableListOf()

        presenter.requestData()
        presenter.onFinished(dataList)

        verify(view).setDataList(dataList)
    }
}