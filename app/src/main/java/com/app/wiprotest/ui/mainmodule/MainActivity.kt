package com.app.wiprotest.ui.mainmodule

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.wiprotest.R
import com.app.wiprotest.base.BaseActivity
import com.app.wiprotest.databinding.ActivityMainBinding
import com.app.wiprotest.model.api.RowInformationModel
import com.app.wiprotest.ui.mainmodule.adapter.MainAdapter
import com.app.wiprotest.utils.Constants.Companion.ERROR_MESSAGE
import com.app.wiprotest.utils.Constants.Companion.LANDSCAPE_MODE
import com.app.wiprotest.utils.Constants.Companion.PORTRAIT_MODE
import com.app.wiprotest.utils.Constants.Companion.WELCOME
import com.app.wiprotest.utils.DialogUtils
import com.app.wiprotest.utils.NetworkHelper.Companion.networkHelperInstance
import com.app.wiprotest.utils.NetworkStatus
import es.dmoral.toasty.Toasty
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

/**
 * Main Activity which will display the list of items fetched from the server.
 */
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(), MainNavigator {

    /**
     * Instance of [ActivityMainBinding]
     */
    var activityMainBinding: ActivityMainBinding? = null

    /**
     * Instance of [MainViewModel]
     */
    private val mainViewModel: MainViewModel by viewModel()

    /**
     * Instance of [MainAdapter]
     */
    var mainAdapter: MainAdapter? = null

    /**
     * Instance of [MainNavigator]
     */
    var mainNavigator: MainNavigator? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialization
        activityMainBinding = getViewDataBinding()
        activityMainBinding?.viewModel = this
        mainNavigator = this

        // request data from the API
        requestData()

        // swipe to refresh listener
        activityMainBinding?.swipe?.setOnRefreshListener {
            requestData()
            activityMainBinding?.swipe?.isRefreshing = false;
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun getViewModel(): MainViewModel {
        return mainViewModel
    }

    override fun setUp(savedInstanceState: Bundle?) {
    }

    /**
     * This method fetches the data from the API if the device has a valid network connection.
     */
    private fun requestData() {
        if (networkHelperInstance?.isConnected(this@MainActivity)!!) {
            // Valid Network -> Show Progress Bar
            DialogUtils.startProgressDialog(this@MainActivity)

            // Observer which observes on the data received from the API
            mainViewModel.callApi().observe(this, Observer {
                if (it != null) {
                    // Set Title to the toolbar
                    activityMainBinding?.txtMainTitle?.text = it.title.toString()

                    // Stop Progress Bar
                    DialogUtils.stopProgressDialog()

                    // Initialize the main adapter
                    mainAdapter = MainAdapter(
                        this@MainActivity,
                        it.rows as ArrayList<RowInformationModel>
                    )
                    activityMainBinding?.itemList?.setHasFixedSize(true)

                    // Initialize recycler view
                    val mLayoutManager: RecyclerView.LayoutManager =
                        LinearLayoutManager(this@MainActivity)
                    activityMainBinding?.itemList?.layoutManager = mLayoutManager
                    activityMainBinding?.itemList?.itemAnimator = DefaultItemAnimator()
                    activityMainBinding?.itemList?.adapter = mainAdapter
                } else {
                    // Stop Progress Bar
                    DialogUtils.stopProgressDialog()
                }
                Toasty.success(this@MainActivity, WELCOME, Toasty.LENGTH_LONG).show()
            })
        } else {
            // Invalid Network
            startActivity(Intent(this, NetworkStatus::class.java))
        }
    }

    override fun onError() {
        DialogUtils.stopProgressDialog()
        Toasty.error(this@MainActivity, ERROR_MESSAGE, Toasty.LENGTH_LONG).show()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        // Checks the orientation of the screen
        if (newConfig.orientation === Configuration.ORIENTATION_LANDSCAPE) {
            Toasty.info(this, LANDSCAPE_MODE, Toasty.LENGTH_SHORT).show()
        } else if (newConfig.orientation === Configuration.ORIENTATION_PORTRAIT) {
            Toasty.info(this, PORTRAIT_MODE, Toasty.LENGTH_SHORT).show()
        }
    }

}