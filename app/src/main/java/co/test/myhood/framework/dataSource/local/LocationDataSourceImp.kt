package co.test.myhood.framework.dataSource.local

import co.test.myhood.data.dataSource.LocationDataSource
import co.test.myhood.domain.Location
import co.test.myhood.framework.dataSource.remote.api.HoodsAPI
import javax.inject.Inject

class LocationDataSourceImp @Inject constructor(
    private val hoodsAPI: HoodsAPI
) : LocationDataSource {


    override suspend fun getLocationFromHoodName(name: String): Location {
        //todo use geocoder to get Location
        return Location("52.50820", "13.449358", name)
    }
}