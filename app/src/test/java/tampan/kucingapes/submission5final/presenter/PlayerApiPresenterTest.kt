/*
 * PlayerApiPresenterTest.kt on Submission5final
 * Developed by Muhammad Utsman
 * Last modified 10/31/18 4:58 PM
 * Copyright (c) 2018 kucingapes
 */

package tampan.kucingapes.submission5final.presenter

import org.junit.Test

import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import tampan.kucingapes.submission5final.MainContract
import tampan.kucingapes.submission5final.model.Player

class PlayerApiPresenterTest {

    @Mock
    private lateinit var view: MainContract.PlayerView

    @Mock
    private lateinit var presenter: PlayerApiPresenter

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        presenter = PlayerApiPresenter(view, GetPlayerApi())
    }

    @Test
    fun requestDataTest() {
        val dataList: MutableList<Player> = mutableListOf()

        presenter.requestData("133604")
        presenter.onFinished(dataList)

        verify(view).setDataList(dataList)
    }
}