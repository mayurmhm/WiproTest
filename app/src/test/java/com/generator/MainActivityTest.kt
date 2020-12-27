package com.generator

import android.app.Application
import android.content.Context
import com.app.wiprotest.model.api.CityInformationModel
import com.app.wiprotest.model.api.RowInformationModel
import com.app.wiprotest.ui.mainmodule.MainActivity
import com.app.wiprotest.ui.mainmodule.MainViewModel
import com.app.wiprotest.utils.NetworkHelper.Companion.networkHelperInstance
import com.generator.DataGenerator.Companion.FAILURE_BOOLEAN
import com.generator.DataGenerator.Companion.SUCCESS_BOOLEAN
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * Tests [MainActivity]
 */
class MainActivityTest {

    /**
     * Instance of [CityInformationModel]
     */
    @MockK
    private lateinit var cityInformationModel: CityInformationModel

    /**
     * Instance of [RowInformationModel]
     */
    @MockK
    private lateinit var rowInformationModel: RowInformationModel

    /**
     * Instance of [MainActivity]
     */
    @MockK
    private lateinit var mainActivity: MainActivity

    /**
     * Instance of [MainViewModel]
     */
    @MockK
    private lateinit var mainViewModel: MainViewModel

    /**
     * Instance of [Context]
     */
    @MockK
    private lateinit var context: Context

    /**
     * Instance of [Application]
     */
    @MockK
    private lateinit var application: Application

    @Before
    fun setup() {
        // Arrange
        MockKAnnotations.init(this)

        cityInformationModel = CityInformationModel()
        rowInformationModel = RowInformationModel()
        application = Application()
        mainActivity = MainActivity()
        mainViewModel = MainViewModel(application)

        every { networkHelperInstance?.isConnected(context) } returns true
    }

    /**
     * Handles Failed Network Status
     */
    @Test
    fun networkIssueTest() {
        // Arrange
        every { networkHelperInstance?.isConnected(context) } returns false

        val status = networkHelperInstance?.isConnected(context)

        // Assert
        assertEquals(FAILURE_BOOLEAN, status)
    }

    /**
     * Handles Successful Network Check
     */
    @Test
    fun validNetworkTest() {
        // Arrange
        every { networkHelperInstance?.isConnected(context) } returns true

        // Action
        val status = networkHelperInstance?.isConnected(context)

        // Assert
        assertEquals(SUCCESS_BOOLEAN, status)
    }
}