package com.app.wiprotest.model.api

import com.app.wiprotest.utils.Constants.Companion.EMPTY_STRING
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Model class for Row Information
 */
class RowInformationModel {
    @SerializedName("title")
    @Expose
    var title: String? = EMPTY_STRING

    @SerializedName("description")
    @Expose
    var description: String? = EMPTY_STRING

    @SerializedName("imageHref")
    @Expose
    var imageHref: String? = EMPTY_STRING
}