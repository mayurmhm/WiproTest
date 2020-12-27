package com.generator

import com.app.wiprotest.model.api.CityInformationModel
import com.app.wiprotest.model.api.RowInformationModel

/**
 * Generator class providing all the required objects like Json, Response, Entity, etc for Unit Testing
 */
class DataGenerator {
    companion object {
        /**
         * Indicates success status
         */
        var SUCCESS_BOOLEAN = true

        /**
         * Indicates failure status
         */
        var FAILURE_BOOLEAN = false

        /**
         * Generates a [CityInformationModel]
         */
        fun getCityInfo(): CityInformationModel {
            return CityInformationModel()
        }

        /**
         * Generates a [RowInformationModel]
         */
        fun getRowInfo(): RowInformationModel {
            return RowInformationModel()
        }
    }
}