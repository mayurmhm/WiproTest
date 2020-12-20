package com.app.wiprotest.utils

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import com.app.wiprotest.R

/**
 * A Dialog Utils class to maintain progress dialogs
 */
class DialogUtils @Throws(ClassCastException::class)

constructor(context: Context) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.custom_progress_dialog_trans)
    }

    /**
     * Companion Object
     */
    companion object {
        /**
         * Instance of [DialogUtils]
         */
        private var customProgress: DialogUtils? = null

        /**
         * Display the progress dialog.
         */
        fun startProgressDialog(context: Context): DialogUtils {
            customProgress = DialogUtils(context)
            customProgress?.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            customProgress?.setCancelable(false)
            customProgress?.show()
            return customProgress!!
        }

        /**
         * Hide the progress dialog.
         */
        fun stopProgressDialog() {
            if (customProgress != null && customProgress!!.isShowing) {
                customProgress?.dismiss()
            }
        }
    }
}
