package co.test.myhood.data.repository

import co.test.myhood.data.dataSource.ImageHoodDataSource
import co.test.myhood.data.dataSource.LocationDataSource
import co.test.myhood.domain.Location

class LocationHoodsRepository constructor(
    private val locationDataSource: LocationDataSource,
    private val imageHoodDataSource: ImageHoodDataSource
) {

    private suspend fun getCoordinateLocationFromHood(name: String): Location {
        return locationDataSource.getLocationFromHoodName(name)
    }

    suspend fun getHoodImageUrl(name: String): String {
        val location = getCoordinateLocationFromHood(name)
        return imageHoodDataSource.getImageFromHoodLocation(location)

    }
}