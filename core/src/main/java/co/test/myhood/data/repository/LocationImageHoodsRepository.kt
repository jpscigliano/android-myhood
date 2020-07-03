package co.test.myhood.data.repository

import co.test.myhood.data.dataSource.ImageHoodDataSource
import co.test.myhood.data.dataSource.LocationDataSource
import co.test.myhood.domain.Location

class LocationImageHoodsRepository constructor(
    private val locationDataSource: LocationDataSource,
    private val imageHoodDataSource: ImageHoodDataSource
) {

    suspend fun getImageByLocationName(name: String): String {
        val location = getCoordinateLocationFromHoodName(name)
        return imageHoodDataSource.getImageFromHoodLocation(location)
    }

    private suspend fun getCoordinateLocationFromHoodName(name: String): Location {
        return locationDataSource.getLocationFromHoodName(name)
    }
}