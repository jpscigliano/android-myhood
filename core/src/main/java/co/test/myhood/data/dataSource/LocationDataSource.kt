package co.test.myhood.data.dataSource

import co.test.myhood.domain.Location

interface LocationDataSource {
    suspend fun getLocationFromHoodName(name: String): Location
}