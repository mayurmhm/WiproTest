package com.app.wiprotest.model.api

import com.app.wiprotest.utils.Constants.Companion.EMPTY_STRING
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Model class for City Information
 */
class CityInformationModel {
    @SerializedName("title")
    @Expose
    var title: String = EMPTY_STRING

    @SerializedName("rows")
    @Expose
    var rows: List<RowInformationModel?> = emptyList()
}